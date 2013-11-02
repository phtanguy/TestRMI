package fr.philippe_tanguy;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientVMSideServer
{
  //-----------------------------------------------------------------------------
  public ClientVMSideServer() throws RemoteException, NotBoundException
  {
    // Connexion en tant que client : PaparazziTransmitter se connecte à uav3i.
    Registry remoteRegistry = LocateRegistry.getRegistry("192.168.1.77", 10000);
    //Registry remoteRegistry = LocateRegistry.getRegistry("127.0.1.1", 10000);
    
    String[] services = remoteRegistry.list();
    for(String service : services)
      System.out.println("---> Service disponible : " + service);
    
    IVMSide1 vmSide = (IVMSide1) remoteRegistry.lookup("vmSide");
    System.out.println("Serveur proxy récupéré.");
    String s = "abcdefg";
    System.out.println("Appel : " + s + " ---> " + vmSide.toUpperCase(s));
  }
  //-----------------------------------------------------------------------------
  public static void main(String[] args)
  {
    try
    {
      new ClientVMSideServer();
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }
  }
  //-----------------------------------------------------------------------------
}
