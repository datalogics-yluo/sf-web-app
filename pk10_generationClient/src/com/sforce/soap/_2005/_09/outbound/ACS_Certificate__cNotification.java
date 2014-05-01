/**
 * ACS_Certificate__cNotification.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sforce.soap._2005._09.outbound;

public class ACS_Certificate__cNotification  implements java.io.Serializable {
    private java.lang.String id;

    private com.sforce.soap.enterprise.sobject.ACS_Certificate__c sObject;

    public ACS_Certificate__cNotification() {
    }

    public ACS_Certificate__cNotification(
           java.lang.String id,
           com.sforce.soap.enterprise.sobject.ACS_Certificate__c sObject) {
           this.id = id;
           this.sObject = sObject;
    }


    /**
     * Gets the id value for this ACS_Certificate__cNotification.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this ACS_Certificate__cNotification.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the sObject value for this ACS_Certificate__cNotification.
     * 
     * @return sObject
     */
    public com.sforce.soap.enterprise.sobject.ACS_Certificate__c getSObject() {
        return sObject;
    }


    /**
     * Sets the sObject value for this ACS_Certificate__cNotification.
     * 
     * @param sObject
     */
    public void setSObject(com.sforce.soap.enterprise.sobject.ACS_Certificate__c sObject) {
        this.sObject = sObject;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ACS_Certificate__cNotification)) return false;
        ACS_Certificate__cNotification other = (ACS_Certificate__cNotification) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.sObject==null && other.getSObject()==null) || 
             (this.sObject!=null &&
              this.sObject.equals(other.getSObject())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getSObject() != null) {
            _hashCode += getSObject().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ACS_Certificate__cNotification.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.sforce.com/2005/09/outbound", "ACS_Certificate__cNotification"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://soap.sforce.com/2005/09/outbound", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SObject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://soap.sforce.com/2005/09/outbound", "sObject"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:sobject.enterprise.soap.sforce.com", "ACS_Certificate__c"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
