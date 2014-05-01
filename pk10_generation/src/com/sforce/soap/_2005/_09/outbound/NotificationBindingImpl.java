/**
 * NotificationBindingImpl.java
 *
 */

package com.sforce.soap._2005._09.outbound;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Date;
import java.util.logging.Logger;

public class NotificationBindingImpl implements com.sforce.soap._2005._09.outbound.NotificationPort{
	public static Logger LOGGER = Logger.getLogger("InfoLogging");
    public boolean notifications(java.lang.String organizationId, java.lang.String actionId, java.lang.String sessionId, java.lang.String enterpriseUrl, java.lang.String partnerUrl, com.sforce.soap._2005._09.outbound.ACS_Certificate__cNotification[] notification) throws java.rmi.RemoteException {

		// Generate password and write to pass.txt
	    PrintWriter writer, netwriter;
		try {
			
			File oldFile;
			oldFile = new File("C:\\sfautomation\\pass.txt");
			if( oldFile.exists())
				if( !oldFile.delete() )
					LOGGER.info("YL -- unable to delete C:\\sfautomation\\pass.txt and begin \n");
		   	DateFormat	dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		   	Date	date = new Date();
		   	String	subDir = dateFormat.format(date);
		   	
			writer = new PrintWriter("C:\\sfautomation\\pass.txt", "UTF-8");
			String	 path = "Z:\\" + notification[0].getSObject().getName() + "\\" + subDir;
			File theDir = new File(path); 
			if (!theDir.exists()) {
				boolean result = theDir.mkdir();
			    if(result) {    
			    	LOGGER.info(path + " DIR created");  
			    }
			}
		
			netwriter = new PrintWriter(path+"\\pass.txt", "UTF-8");
			String	passwd1 = randGen(10);
		    LOGGER.info("Password 1:" + passwd1);
		    String	passwd2 = randGen(9);
		    writer.print(passwd1+passwd2);
		    netwriter.print(passwd1+passwd2);
		    LOGGER.info("Password 2:" + passwd2);
		    writer.close();
		    netwriter.close();
		    
		    LOGGER.info("YL -- finished password generation and saved in files\n");
		    
		    // P10 step
		    getP10();
		    
		    LOGGER.info("YL -- finished pk10 generation\n");
		    
		    // Log notification message
		    logNotification(notification);
		    
		    copyNet2drive(path);
		    
		    LOGGER.info("YL -- pk10_generation Copy pass.txt, .pkcs10 and .key over\n");
		    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	    	
    	return true;
    }
    
    public	static String	randGen(int length)
    {
    	
    	char[]	text = new char[length];
    	try {
    	    Thread.sleep(200);
    	} catch(InterruptedException ex) {
    	    Thread.currentThread().interrupt();
    	}
    	Date	dt = new Date();
    	long	seed = dt.getTime();
    	Random	random = new Random(seed);
    	for(int i=0; i<9; i++){
    		double	rng	= random.nextDouble();
    		if( rng < (double) 10/62 )
    			text[i] = (char)((Math.floor(rng*62))+48);
    		else if( rng < (double) 36/62 )
    			text[i] = (char)((Math.floor(rng*62))+55);
    		else
    			text[i] = (char)((Math.floor(rng*62))+61);
    	}
    	if(length==10)
    		text[9] = (char)(10);
    	return new String(text);
    }
    
    public void getP10()
    {    	
    	LOGGER.info("YL -- getP10()");
    	
    	try {
            
    		ProcessBuilder builder = new ProcessBuilder("C:\\OpenSSL\\bin\\openssl.exe",
    				"req",
    				"-newkey",
    				"rsa:1024",
    				"-passout",
    				"file:C:\\sfautomation\\pass.txt",
    				"-config",
    				"C:\\sfautomation\\getp10.cfg",
    				"-out",
    				"C:\\sfautomation\\operator.pkcs10",
    				"-keyout",
    				"C:\\sfautomation\\operator.key");
    		Process p = builder.start();
    		LOGGER.info("After builder.start() called");
    		
    		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
    		String line;
            while (true) {
                line = r.readLine();
                if (line == null) { 
                	break; 
                }
                else
                	LOGGER.info(line);
            }
            LOGGER.info("Finished the getP10 Block execution");

    	}
		catch (IOException e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			String s = writer.toString();
			LOGGER.info("getP10 IOException: " + s);
		}    	
    	
    }
    
    public	void	logNotification(com.sforce.soap._2005._09.outbound.ACS_Certificate__cNotification[] notification)
    {
    	ACS_Certificate__cNotification[] localNotification = notification.clone();
	   	if(localNotification.length <=0)
	   		LOGGER.info("logNotification: no Dlogics__ACS_Certificate__c object"); 
	   	else 
	   	{
	   		String	output = localNotification[0].getSObject().getFulfillment_URL__c();  		
	   		LOGGER.info("logNotification: fulfillment URL =" + output);
	   		output = localNotification[0].getSObject().getId();
	   		LOGGER.info("logNotification: Id =" + output);
	   		LOGGER.info("logNotification: Account_Number =" + output);	   		
	   		output = localNotification[0].getSObject().getAccount_Number__c();
	   		LOGGER.info("logNotification: Account_Number =" + output);
	   		output = localNotification[0].getSObject().getName();
	   		LOGGER.info("logNotification: Name =" + output);		   		
	   	}
    }
    
    public void copyNet2drive(String	dest_path)
    {
    	try {
    		LOGGER.info("getP10 copyNet2drive is called");		
    		File	keyFile = new File("C:\\sfautomation\\operator.key");
    		File	destFile = new File(dest_path+"\\operator.key");
    		if(destFile.exists())
    			destFile.delete();    		
    		Files.copy(keyFile.toPath(), destFile.toPath());

    		File	pkcs10File = new File("C:\\sfautomation\\operator.pkcs10");
    		destFile = new File(dest_path+"\\operator.pkcs10");
    		if(destFile.exists())
    			destFile.delete();    		
    		Files.copy(pkcs10File.toPath(), destFile.toPath());    		
            LOGGER.info("Finished copyNet2drive in PK10 Block execution");

    	}
		catch (IOException e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			String s = writer.toString();
			LOGGER.info("getP10 copyNet2drive IOException: " + s);
		}		
    }		
}