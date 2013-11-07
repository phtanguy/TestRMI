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
    // Pour une utilisation avec VMware...
    // Le fonctionnement de RMI est problématique sur des machines avec plusieurs
    // adresses IP (cas d'un ordinateur hébergeant une machine virtuelle) :
    // l'adresse IP utilisée pour les stubs RMI n'est alors pas la bonne.
    // Voir : http://www.chipkillmar.net/2011/06/22/multihomed-hosts-and-java-rmi/
    // Il faut alors, côté serveur, renseigner les propiétés système
    // "java.rmi.server.hostname" et "java.rmi.server.useLocalHostName".
    // Deux options sont possibles :
    //   - Définir les propriétés de manière programmatique : System.setProperty(...).
    //   - Lancer le serveur avec les oprions -Djava.rmi.server.hostname=... et
    //     -Djava.rmi.server.useLocalHostName=true.
    
//    System.setProperty("java.rmi.server.hostname",         "192.168.1.7");
//    //System.setProperty("java.rmi.server.hostname",         "192.168.1.77");
//    System.setProperty("java.rmi.server.useLocalHostName", "true");

//    System.out.println("sun.rmi.transport.connectionTimeout = " + System.getProperty("sun.rmi.transport.connectionTimeout"));
//    System.setProperty("sun.rmi.transport.tcp.responseTimeout", "10");
//    System.setProperty("sun.rmi.transport.proxy.connectTimeout", "1000");
//    System.setProperty("sun.rmi.transport.connectionTimeout", "1000");

    String ipAddress = InetAddress.getLocalHost().getHostAddress(); 
    System.out.println("Mon adresse IP : " + ipAddress);

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
