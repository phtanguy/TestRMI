package fr.philippe_tanguy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IVMSide2 extends Remote
{
  //-----------------------------------------------------------------------------
  public int add(int i1, int i2) throws RemoteException;
  //-----------------------------------------------------------------------------
}
