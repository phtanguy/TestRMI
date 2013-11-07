package fr.philippe_tanguy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;

public class ClientVMSideServer
{
  //-----------------------------------------------------------------------------
  /*
   * Permet de fixer facilement le timeout lors de la connexion sur la partie
   * serveur. Le timeout doit être positif ou nul. La valeur 0 indique un timeout
   * infini.
   * À explorer : la valeur maxi semble être de 20 secondes. Toute valeur
   * supérieure (comme la valeur 0 = infini) ne génère pas de timout supérieur.
   * Dépendant de la plateforme ?
   */
  static
  {
    try
    {
      RMISocketFactory.setSocketFactory(new RMISocketFactory()
      {
        private int timeout = 1000;
        public Socket createSocket(String host, int port) throws IOException
        {
          Socket socket = new Socket();
          socket.setSoTimeout(timeout);
          socket.setSoLinger(false, 0);
          socket.connect(new InetSocketAddress(host, port), timeout);
          return socket;
        }

        public ServerSocket createServerSocket(int port) throws IOException
        {
          return new ServerSocket(port);
        }
      });
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  private String host = "192.168.1.9";
  private int    port = 10000;
  //-----------------------------------------------------------------------------
  public ClientVMSideServer() throws RemoteException, NotBoundException
  {
    new Thread(new TestCall()).start();

//    String s = "abcdefg";
//    System.out.println("Appel : vmSide1.toUpperCase(" + s + ") ---> " + vmSide1.toUpperCase(s));
//
//    System.out.println("Appel : vmSide2.add(4, 3) ---> " + vmSide2.add(4, 3));
//    
//    DataObject1 dataObject1 = new DataObject1(7);
//    System.out.println("Appel : vmSide2.transform1to2(" + dataObject1 + ") ---> " + vmSide2.transform1to2(dataObject1));
}
  //-----------------------------------------------------------------------------
  private IVMSide2 register() throws RemoteException, NotBoundException
  {
    Registry remoteRegistry = LocateRegistry.getRegistry(host, port);
    IVMSide2 vmSide2 = (IVMSide2) remoteRegistry.lookup("vmSide2");
    System.out.println("Service VMSide2 récupéré.");
    return vmSide2;
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
  
  
  
  
  
  
  
  //-----------------------------------------------------------------------------
  private class TestCall implements Runnable
  {
    private IVMSide2 vmSide2 = null;

    @Override
    public void run()
    {
      int cpt = 0;
      while(true)
      {
        if(vmSide2 != null)
        {
          try
          {
            System.out.println("---> " + cpt + " - " + vmSide2.add(cpt, cpt));
            cpt++;
          }
          catch (RemoteException e)
          {
            vmSide2 = null;
          }
        }
        else
        {
          try
          {
            vmSide2 = register();
          }
          catch (RemoteException | NotBoundException e)
          {
            System.out.println("Server not yet ready");
          }
        }
        try { Thread.sleep(500); } catch (InterruptedException e) {}
      }
    }
  }
  //-----------------------------------------------------------------------------
}
