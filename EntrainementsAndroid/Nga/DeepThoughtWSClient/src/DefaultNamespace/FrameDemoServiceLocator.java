/**
 * FrameDemoServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package DefaultNamespace;

public class FrameDemoServiceLocator extends org.apache.axis.client.Service implements DefaultNamespace.FrameDemoService {

    public FrameDemoServiceLocator() {
    }


    public FrameDemoServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FrameDemoServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FrameDemo
    private java.lang.String FrameDemo_address = "http://localhost:8080/DeepThoughtWS/services/FrameDemo";

    public java.lang.String getFrameDemoAddress() {
        return FrameDemo_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FrameDemoWSDDServiceName = "FrameDemo";

    public java.lang.String getFrameDemoWSDDServiceName() {
        return FrameDemoWSDDServiceName;
    }

    public void setFrameDemoWSDDServiceName(java.lang.String name) {
        FrameDemoWSDDServiceName = name;
    }

    public DefaultNamespace.FrameDemo getFrameDemo() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FrameDemo_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFrameDemo(endpoint);
    }

    public DefaultNamespace.FrameDemo getFrameDemo(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            DefaultNamespace.FrameDemoSoapBindingStub _stub = new DefaultNamespace.FrameDemoSoapBindingStub(portAddress, this);
            _stub.setPortName(getFrameDemoWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFrameDemoEndpointAddress(java.lang.String address) {
        FrameDemo_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (DefaultNamespace.FrameDemo.class.isAssignableFrom(serviceEndpointInterface)) {
                DefaultNamespace.FrameDemoSoapBindingStub _stub = new DefaultNamespace.FrameDemoSoapBindingStub(new java.net.URL(FrameDemo_address), this);
                _stub.setPortName(getFrameDemoWSDDServiceName());
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
        if ("FrameDemo".equals(inputPortName)) {
            return getFrameDemo();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://DefaultNamespace", "FrameDemoService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://DefaultNamespace", "FrameDemo"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FrameDemo".equals(portName)) {
            setFrameDemoEndpointAddress(address);
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
