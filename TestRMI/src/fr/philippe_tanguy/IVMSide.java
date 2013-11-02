package fr.philippe_tanguy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IVMSide extends Remote
{
  //-----------------------------------------------------------------------------
  public String toUpperCase(String s) throws RemoteException;
  //-----------------------------------------------------------------------------
}
