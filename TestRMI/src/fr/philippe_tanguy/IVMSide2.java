package fr.philippe_tanguy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IVMSide2 extends Remote
{
  //-----------------------------------------------------------------------------
  public int add(int i1, int i2) throws RemoteException;
  //-----------------------------------------------------------------------------
  public DataObject1 transform2to1(DataObject2 dataObject2) throws RemoteException;
  public DataObject2 transform1to2(DataObject1 dataObject1) throws RemoteException;
  //-----------------------------------------------------------------------------
}
