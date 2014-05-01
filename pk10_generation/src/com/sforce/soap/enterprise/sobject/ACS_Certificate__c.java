/**
 * ACS_Certificate__c.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sforce.soap.enterprise.sobject;

public class ACS_Certificate__c  extends com.sforce.soap.enterprise.sobject.SObject  implements java.io.Serializable {
    private java.lang.String account_Number__c;

    private java.lang.String fulfillment_URL__c;

    private java.lang.String name;

    public ACS_Certificate__c() {
    }

    public ACS_Certificate__c(
           java.lang.String[] fieldsToNull,
           java.lang.String id,
           java.lang.String account_Number__c,
           java.lang.String fulfillment_URL__c,
           java.lang.String name) {
        super(
            fieldsToNull,
            id);
        this.account_Number__c = account_Number__c;
        this.fulfillment_URL__c = fulfillment_URL__c;
        this.name = name;
    }


    /**
     * Gets the account_Number__c value for this ACS_Certificate__c.
     * 
     * @return account_Number__c
     */
    public java.lang.String getAccount_Number__c() {
        return account_Number__c;
    }


    /**
     * Sets the account_Number__c value for this ACS_Certificate__c.
     * 
     * @param account_Number__c
     */
    public void setAccount_Number__c(java.lang.String account_Number__c) {
        this.account_Number__c = account_Number__c;
    }


    /**
     * Gets the fulfillment_URL__c value for this ACS_Certificate__c.
     * 
     * @return fulfillment_URL__c
     */
    public java.lang.String getFulfillment_URL__c() {
        return fulfillment_URL__c;
    }


    /**
     * Sets the fulfillment_URL__c value for this ACS_Certificate__c.
     * 
     * @param fulfillment_URL__c
     */
    public void setFulfillment_URL__c(java.lang.String fulfillment_URL__c) {
        this.fulfillment_URL__c = fulfillment_URL__c;
    }


    /**
     * Gets the name value for this ACS_Certificate__c.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ACS_Certificate__c.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ACS_Certificate__c)) return false;
        ACS_Certificate__c other = (ACS_Certificate__c) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.account_Number__c==null && other.getAccount_Number__c()==null) || 
             (this.account_Number__c!=null &&
              this.account_Number__c.equals(other.getAccount_Number__c()))) &&
            ((this.fulfillment_URL__c==null && other.getFulfillment_URL__c()==null) || 
             (this.fulfillment_URL__c!=null &&
              this.fulfillment_URL__c.equals(other.getFulfillment_URL__c()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getAccount_Number__c() != null) {
            _hashCode += getAccount_Number__c().hashCode();
        }
        if (getFulfillment_URL__c() != null) {
            _hashCode += getFulfillment_URL__c().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ACS_Certificate__c.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sobject.enterprise.soap.sforce.com", "ACS_Certificate__c"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("account_Number__c");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sobject.enterprise.soap.sforce.com", "Account_Number__c"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fulfillment_URL__c");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sobject.enterprise.soap.sforce.com", "Fulfillment_URL__c"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sobject.enterprise.soap.sforce.com", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
