/**
 * MyWebServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.scispike.ws;

public class MyWebServiceServiceLocator extends org.apache.axis.client.Service implements com.scispike.ws.MyWebServiceService {

    public MyWebServiceServiceLocator() {
    }


    public MyWebServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MyWebServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for myWebService
    private java.lang.String myWebService_address = "http://localhost:8080/myWebService/services/myWebService";

    public java.lang.String getmyWebServiceAddress() {
        return myWebService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String myWebServiceWSDDServiceName = "myWebService";

    public java.lang.String getmyWebServiceWSDDServiceName() {
        return myWebServiceWSDDServiceName;
    }

    public void setmyWebServiceWSDDServiceName(java.lang.String name) {
        myWebServiceWSDDServiceName = name;
    }

    public com.scispike.ws.MyWebService getmyWebService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(myWebService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getmyWebService(endpoint);
    }

    public com.scispike.ws.MyWebService getmyWebService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.scispike.ws.MyWebServiceSoapBindingStub _stub = new com.scispike.ws.MyWebServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getmyWebServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setmyWebServiceEndpointAddress(java.lang.String address) {
        myWebService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.scispike.ws.MyWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.scispike.ws.MyWebServiceSoapBindingStub _stub = new com.scispike.ws.MyWebServiceSoapBindingStub(new java.net.URL(myWebService_address), this);
                _stub.setPortName(getmyWebServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("myWebService".equals(inputPortName)) {
            return getmyWebService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.scispike.com", "myWebServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.scispike.com", "myWebService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("myWebService".equals(portName)) {
            setmyWebServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
