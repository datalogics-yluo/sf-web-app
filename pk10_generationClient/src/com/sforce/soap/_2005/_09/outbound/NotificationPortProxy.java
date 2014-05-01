package com.sforce.soap._2005._09.outbound;

public class NotificationPortProxy implements com.sforce.soap._2005._09.outbound.NotificationPort {
  private String _endpoint = null;
  private com.sforce.soap._2005._09.outbound.NotificationPort notificationPort = null;
  
  public NotificationPortProxy() {
    _initNotificationPortProxy();
  }
  
  public NotificationPortProxy(String endpoint) {
    _endpoint = endpoint;
    _initNotificationPortProxy();
  }
  
  private void _initNotificationPortProxy() {
    try {
      notificationPort = (new com.sforce.soap._2005._09.outbound.NotificationServiceLocator()).getNotification();
      if (notificationPort != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)notificationPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)notificationPort)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (notificationPort != null)
      ((javax.xml.rpc.Stub)notificationPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.sforce.soap._2005._09.outbound.NotificationPort getNotificationPort() {
    if (notificationPort == null)
      _initNotificationPortProxy();
    return notificationPort;
  }
  
  public boolean notifications(java.lang.String organizationId, java.lang.String actionId, java.lang.String sessionId, java.lang.String enterpriseUrl, java.lang.String partnerUrl, com.sforce.soap._2005._09.outbound.ACS_Certificate__cNotification[] notification) throws java.rmi.RemoteException{
    if (notificationPort == null)
      _initNotificationPortProxy();
    return notificationPort.notifications(organizationId, actionId, sessionId, enterpriseUrl, partnerUrl, notification);
  }
  
  
}