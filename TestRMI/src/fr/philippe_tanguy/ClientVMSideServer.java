package fr.philippe_tanguy;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
    Registry remoteRegistry = LocateRegistry.getRegistry("192.168.1.7", 10000);
    IVMSide vmSide = (IVMSide) remoteRegistry.lookup("vmSide");
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
