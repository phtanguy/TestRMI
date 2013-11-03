package fr.philippe_tanguy;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientVMSideServer
{
  //-----------------------------------------------------------------------------
  public ClientVMSideServer() throws RemoteException, NotBoundException, MalformedURLException
  {
//    if(System.getSecurityManager() == null)
//      System.setSecurityManager(new RMISecurityManager());

    // Connexion en tant que client : PaparazziTransmitter se connecte à uav3i.
    Registry remoteRegistry = LocateRegistry.getRegistry("192.168.1.77", 10000);
    //Registry remoteRegistry = LocateRegistry.getRegistry("192.168.1.7", 10000);
    
    String[] services = remoteRegistry.list();
    for(String service : services)
      System.out.println("---> Service disponible : " + service);
    
    //IVMSide1 vmSide1 = (IVMSide1) Naming.lookup("rmi://192.168.110.130/vmSide1");
    IVMSide1 vmSide1 = (IVMSide1) remoteRegistry.lookup("vmSide1");
    System.out.println("Service VMSide1 récupéré.");
    IVMSide2 vmSide2 = (IVMSide2) remoteRegistry.lookup("vmSide2");
    System.out.println("Service VMSide2 récupéré.");

    String s = "abcdefg";
    System.out.println("Appel : vmSide1.toUpperCase(" + s + ") ---> " + vmSide1.toUpperCase(s));

    System.out.println("Appel : vmSide2.add(4, 3) ---> " + vmSide2.add(4, 3));
    
    DataObject1 dataObject1 = new DataObject1(7);
    System.out.println("Appel : vmSide2.transform1to2(" + dataObject1 + ") ---> " + vmSide2.transform1to2(dataObject1));
}
  //-----------------------------------------------------------------------------
  public static void main(String[] args)
  {
    try
    {
      new ClientVMSideServer();
    }
    catch (RemoteException | NotBoundException | MalformedURLException e)
    {
      e.printStackTrace();
    }
  }
  //-----------------------------------------------------------------------------
}
