/**
 * NotificationBindingImpl.java
 *
 */

package com.sforce.soap._2005._09.outbound;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class NotificationBindingImpl implements com.sforce.soap._2005._09.outbound.NotificationPort{
	public static Logger LOGGER = Logger.getLogger("InfoLogging");
	public	String	fulfillmentURL;
	public	String	endDate;
    public boolean notifications(java.lang.String organizationId, java.lang.String actionId, java.lang.String sessionId, java.lang.String enterpriseUrl, java.lang.String partnerUrl, com.sforce.soap._2005._09.outbound.ACS_Certificate__cNotification[] notification) throws java.rmi.RemoteException {
		try{
			logNotification(notification);
			
    		File	oldFile, urlFile; 
    		oldFile = new File("C:\\sfautomation\\operator.cer");
   			if( oldFile.exists() )
   				if( !oldFile.delete() )
   					throw new Exception("YL -- unable to delete operator.cer in pk12 generation \n");

   			oldFile = new File("C:\\sfautomation\\operator.pem");
   			if( oldFile.exists() )
   				if( !oldFile.delete() )
   					throw new Exception("YL -- unable to delete operator.pem in pk12 generation \n");
   			
    		oldFile = new File("C:\\sfautomation\\operator.p12");
   			if( oldFile.exists() )
   				if( !oldFile.delete() )
   					throw new Exception("YL -- unable to delete operator.p12 in pk12 generation \n");
   			
   			urlFile = new File("C:\\sfautomation\\url.txt");
   			if( urlFile.exists() )
   				if( !urlFile.delete() )
   					throw new Exception("YL -- unable to delete url.txt in pk12 generation \n");	
   						
   		   	DateFormat	dateFormat = new SimpleDateFormat("MM-dd-yyyy");
   		   	Date	date = new Date();
   		   	String	subDir = dateFormat.format(date);
   		   	
	    	// Read the file from the folder.
	    	String	path = "Z:\\" + notification[0].getSObject().getName() + "\\" + subDir;	    	
    		File	cerFile = new File("C:\\sfautomation\\operator.cer");
    		File	destFile = new File(path+"\\operator.cer");
    		try{
    			Files.copy(destFile.toPath(), cerFile.toPath());
    			LOGGER.info("YL -- successfully copied operater.cer from " + path);
    		}
    		catch(IOException e){
    			LOGGER.info("YL -- unable to copy operater.cer from " + path);
    		}
   			
	    	// P12 X509 step
	    	getP12_X509();
	    	
    		// Log expiration date
    		getP12_date();
    		
   			PrintWriter writer = new PrintWriter("C:\\sfautomation\\url.txt", "UTF-8");
   			writer.println(fulfillmentURL);
   			writer.println(endDate);
   			writer.close();
   			
	    	// P12 step
	    	getP12_PKCS();
	    	
	    	LOGGER.info("YL -- saveall files to network \n");
	    	copyNet2drive(path);
	    	LOGGER.info("YL -- save a copy to ftp drive \n");
	    	copy2FTPDrive(notification);
    	}
    	catch(Exception e){
    		LOGGER.info("pk12 IOException: " + e.toString());
    		System.out.println(e.toString());
    	}
		return true;
    }
	
	public void getP12_X509()
    {
    	try {
    		LOGGER.info("P12 X509 is called");
    		ProcessBuilder builder = new ProcessBuilder("C:\\OpenSSL\\bin\\openssl.exe",
    				"x509",
    				"-in",
    				"C:\\sfautomation\\operator.cer",
    				"-inform",
    				"DER",
    				"-out",
    				"C:\\sfautomation\\operator.pem",
    				"-outform",
    				"PEM");
    		Process p = builder.start();
    		LOGGER.info("After P12 X509 builder.start() called");
    		
    		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
    		String line;
            while (true) {
                line = r.readLine();
                if (line == null) {                 	
                	break; 
                }
                else{                	
                	LOGGER.info(line);
                }
            }
            LOGGER.info("Finished the P12 x509 Block execution");            

    	}
		catch (IOException e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			String s = writer.toString();
			LOGGER.info("getP12_X509 IOException: " + s);
		}		
    }
	
	public void getP12_date()
    {
    	try {
    		LOGGER.info("P12 getP12_date is called");
  
    		ProcessBuilder builder = new ProcessBuilder("openssl",
    				"x509",
    				"-in",
    				"C:\\sfautomation\\operator.pem",
    				"-noout",   				
    				"-enddate");
 //    		ProcessBuilder builder = new ProcessBuilder("C:\\YLuo-Test\\simpleOutput.exe");
    		Process p = builder.start();
    		LOGGER.info("After P12 getP12_date builder.start() called");
    		
    		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
    		String line;
    		boolean	quit = true;
            while (quit) {
                line = r.readLine();                
                if(line.startsWith("notAfter")) {
					endDate = line;
					LOGGER.info(line);					
                }
				else  {
					endDate = "NOT getting endDate";
					LOGGER.info(line);
				}
                quit = false;
            }
    	}
		catch (IOException e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			String s = writer.toString();
			LOGGER.info("getP12_date IOException: " + s);
		}		
    }
	
	public void getP12_PKCS()
    {
    	try {
    		LOGGER.info("P12 PKCS is called");
    		ProcessBuilder builder = new ProcessBuilder("C:\\OpenSSL\\bin\\openssl.exe",
    				"pkcs12",
    				"-export",
    				"-in",
    				"C:\\sfautomation\\operator.pem",
    				"-inkey",
    				"C:\\sfautomation\\operator.key",
    				"-name",
    				"operator",
    				"-out",
    				"C:\\sfautomation\\operator.p12",
    				"-passin",
    				"file:C:\\sfautomation\\pass.txt",
    				"-passout",
    				"file:C:\\sfautomation\\pass.txt");
    		
    		Process p = builder.start();
    		LOGGER.info("After P12 PKCS builder.start() called");
    		
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
            LOGGER.info("Finished the P12 PKCS Block execution");

    	}
		catch (IOException e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			String s = writer.toString();
			LOGGER.info("getP12_PKCS IOException: " + s);
		}		
    }
	public void copyNet2drive(String	dest_path)
    {
    	try {
    		LOGGER.info("copyNet2drive is called");		
    		
    		File	pemFile = new File("C:\\sfautomation\\operator.pem");
    		File 	destFile = new File(dest_path+"\\operator.pem");
    		if(destFile.exists())
    			destFile.delete();
    		Files.copy(pemFile.toPath(), destFile.toPath());   
    		
    		File	p12File = new File("C:\\sfautomation\\operator.p12");
    		destFile = new File(dest_path+"\\operator.p12");
    		if(destFile.exists())
    			destFile.delete();    		
    		Files.copy(p12File.toPath(), destFile.toPath());
    		
    		File	urlFile = new File("C:\\sfautomation\\url.txt");
    		destFile = new File(dest_path+"\\url.txt");
    		if(destFile.exists())
    			destFile.delete();    		
    		Files.copy(urlFile.toPath(), destFile.toPath());      		
            LOGGER.info("Finished copyNet2drive in PK12 Block execution");            
            
    	}
		catch (IOException e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			String s = writer.toString();
			LOGGER.info("copyNet2drive IOException: " + s);
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
	   		fulfillmentURL = output;
	   		output = localNotification[0].getSObject().getId();
	   		LOGGER.info("logNotification: Id =" + output);	   		
	   		output = localNotification[0].getSObject().getAccount_Number__c();
	   		LOGGER.info("logNotification: Account_Number =" + output);
	   		output = localNotification[0].getSObject().getName();
	   		LOGGER.info("logNotification: Name =" + output);		   		
	   	}
    }
	public	void	copy2FTPDrive(com.sforce.soap._2005._09.outbound.ACS_Certificate__cNotification[] notification)
    {
		
    	ACS_Certificate__cNotification[] localNotification = notification.clone();
    	String	accountNumber="#####";
	   	if(localNotification.length <=0)
	   	{
	   		LOGGER.info("copy2FTPDrive return: logNotification: no Dlogics__ACS_Certificate__c object!!!");
	   		return;
	   	}
	   	else 
	   	{		
	   		accountNumber = localNotification[0].getSObject().getAccount_Number__c();
	   		LOGGER.info("logNotification: Account_Number =" + accountNumber);   		
	   	}
	   	DateFormat	dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	   	Date	date = new Date();
	   	String	subDir = dateFormat.format(date);
	   	String	FTPDrive = "Y:\\ftp"+accountNumber+"\\"+ subDir;
	   	LOGGER.info("##### FTP Drive is " + FTPDrive);
		File theDir = new File(FTPDrive); 
		if (!theDir.exists()) {
			boolean result = theDir.mkdir();
		    if(result) {    
		    	LOGGER.info(FTPDrive + " DIR created");  
		    }
		}


	   	try {
	   		File 	srcFile, destFile;
			
	   		srcFile = new File("C:\\sfautomation\\operator.p12");
	   		destFile = new File(FTPDrive+"\\operator.p12");
			Files.copy(srcFile.toPath(), destFile.toPath());				
		} catch (IOException e) {
			e.printStackTrace();
		}   

    }
}
