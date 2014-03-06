/**
 * FrameDemoService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package DefaultNamespace;

public interface FrameDemoService extends javax.xml.rpc.Service {
    public java.lang.String getFrameDemoAddress();

    public DefaultNamespace.FrameDemo getFrameDemo() throws javax.xml.rpc.ServiceException;

    public DefaultNamespace.FrameDemo getFrameDemo(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
