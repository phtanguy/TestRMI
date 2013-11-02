package fr.philippe_tanguy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IVMSide1 extends Remote
{
  //-----------------------------------------------------------------------------
  public String toUpperCase(String s) throws RemoteException;
  //-----------------------------------------------------------------------------
}
