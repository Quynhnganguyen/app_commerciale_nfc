/**
 * MyWebServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.scispike.ws;

public interface MyWebServiceService extends javax.xml.rpc.Service {
    public java.lang.String getmyWebServiceAddress();

    public com.scispike.ws.MyWebService getmyWebService() throws javax.xml.rpc.ServiceException;

    public com.scispike.ws.MyWebService getmyWebService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
