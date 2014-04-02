package com.scispike.ws;

public class MyWebServiceProxy implements com.scispike.ws.MyWebService {
  private String _endpoint = null;
  private com.scispike.ws.MyWebService myWebService = null;
  
  public MyWebServiceProxy() {
    _initMyWebServiceProxy();
  }
  
  public MyWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initMyWebServiceProxy();
  }
  
  private void _initMyWebServiceProxy() {
    try {
      myWebService = (new com.scispike.ws.MyWebServiceServiceLocator()).getmyWebService();
      if (myWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)myWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)myWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (myWebService != null)
      ((javax.xml.rpc.Stub)myWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.scispike.ws.MyWebService getMyWebService() {
    if (myWebService == null)
      _initMyWebServiceProxy();
    return myWebService;
  }
  
  public java.lang.String whatIsTheAnswer(java.lang.String interviewer) throws java.rmi.RemoteException{
    if (myWebService == null)
      _initMyWebServiceProxy();
    return myWebService.whatIsTheAnswer(interviewer);
  }
  
  
}