package fr.philippe_tanguy;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class VMSideServer
{
  //-----------------------------------------------------------------------------
  public static int portNumber = 10000;
  //-----------------------------------------------------------------------------
  public VMSideServer() throws RemoteException
  {
    // Enregistrement de la partie serveur : PaparazziTransmitter pourra se connecter à uav3i.
    Registry localRegistry = LocateRegistry.createRegistry(portNumber);
    IVMSide vmSide = (IVMSide) UnicastRemoteObject.exportObject(new VMSideImpl(), portNumber);
    localRegistry.rebind("vmSide", vmSide);

    System.out.println("####### vmSide started on port " + portNumber + ".");
  }
  //-----------------------------------------------------------------------------
  public static void main(String[] args)
  {
    try
    {
      new VMSideServer();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }
  //-----------------------------------------------------------------------------
}
