package DefaultNamespace;

public class FrameDemoProxy implements DefaultNamespace.FrameDemo {
  private String _endpoint = null;
  private DefaultNamespace.FrameDemo frameDemo = null;
  
  public FrameDemoProxy() {
    _initFrameDemoProxy();
  }
  
  public FrameDemoProxy(String endpoint) {
    _endpoint = endpoint;
    _initFrameDemoProxy();
  }
  
  private void _initFrameDemoProxy() {
    try {
      frameDemo = (new DefaultNamespace.FrameDemoServiceLocator()).getFrameDemo();
      if (frameDemo != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)frameDemo)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)frameDemo)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (frameDemo != null)
      ((javax.xml.rpc.Stub)frameDemo)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public DefaultNamespace.FrameDemo getFrameDemo() {
    if (frameDemo == null)
      _initFrameDemoProxy();
    return frameDemo;
  }
  
  public void main(java.lang.String[] args) throws java.rmi.RemoteException{
    if (frameDemo == null)
      _initFrameDemoProxy();
    frameDemo.main(args);
  }
  
  
}