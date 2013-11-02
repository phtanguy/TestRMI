package fr.philippe_tanguy;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class VMSideServer
{
  //-----------------------------------------------------------------------------
  public static int portNumber = 10000;
  //-----------------------------------------------------------------------------
  public VMSideServer() throws RemoteException, UnknownHostException
  {
    InetAddress thisIp = InetAddress.getLocalHost(); 
    System.out.println("Mon adresse IP : " + thisIp);

    // Enregistrement de la partie serveur : PaparazziTransmitter pourra se connecter à uav3i.
    Registry localRegistry = LocateRegistry.createRegistry(portNumber);
    IVMSide1 vmSide1 = (IVMSide1) UnicastRemoteObject.exportObject(new VMSide1Impl(), portNumber);
    localRegistry.rebind("vmSide1", vmSide1);
    IVMSide2 vmSide2 = (IVMSide2) UnicastRemoteObject.exportObject(new VMSide2Impl(), portNumber);
    localRegistry.rebind("vmSide2", vmSide2);

    System.out.println("####### vmSide started on port " + portNumber + ".");
  }
  //-----------------------------------------------------------------------------
  public static void main(String[] args)
  {
    try
    {
      new VMSideServer();
    }
    catch (RemoteException | UnknownHostException e)
    {
      e.printStackTrace();
    }
  }
  //-----------------------------------------------------------------------------
}
