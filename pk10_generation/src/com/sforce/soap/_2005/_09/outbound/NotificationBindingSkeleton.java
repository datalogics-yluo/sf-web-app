/**
 * NotificationBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sforce.soap._2005._09.outbound;

public class NotificationBindingSkeleton implements com.sforce.soap._2005._09.outbound.NotificationPort, org.apache.axis.wsdl.Skeleton {
    private com.sforce.soap._2005._09.outbound.NotificationPort impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://soap.sforce.com/2005/09/outbound", "OrganizationId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:enterprise.soap.sforce.com", "ID"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://soap.sforce.com/2005/09/outbound", "ActionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:enterprise.soap.sforce.com", "ID"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://soap.sforce.com/2005/09/outbound", "SessionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://soap.sforce.com/2005/09/outbound", "EnterpriseUrl"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://soap.sforce.com/2005/09/outbound", "PartnerUrl"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://soap.sforce.com/2005/09/outbound", "Notification"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://soap.sforce.com/2005/09/outbound", "ACS_Certificate__cNotification"), com.sforce.soap._2005._09.outbound.ACS_Certificate__cNotification[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("notifications", _params, new javax.xml.namespace.QName("http://soap.sforce.com/2005/09/outbound", "Ack"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://soap.sforce.com/2005/09/outbound", "notifications"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("notifications") == null) {
            _myOperations.put("notifications", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("notifications")).add(_oper);
    }

    public NotificationBindingSkeleton() {
        this.impl = new com.sforce.soap._2005._09.outbound.NotificationBindingImpl();
    }

    public NotificationBindingSkeleton(com.sforce.soap._2005._09.outbound.NotificationPort impl) {
        this.impl = impl;
    }
    public boolean notifications(java.lang.String organizationId, java.lang.String actionId, java.lang.String sessionId, java.lang.String enterpriseUrl, java.lang.String partnerUrl, com.sforce.soap._2005._09.outbound.ACS_Certificate__cNotification[] notification) throws java.rmi.RemoteException
    {
        boolean ret = impl.notifications(organizationId, actionId, sessionId, enterpriseUrl, partnerUrl, notification);
        return ret;
    }

}
