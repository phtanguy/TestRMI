package fr.philippe_tanguy;

import java.rmi.RemoteException;

public class VMSide2Impl implements IVMSide2
{
  //-----------------------------------------------------------------------------
  @Override
  public int add(int i1, int i2) throws RemoteException
  {
    return (i1 + i2);
  }
  //-----------------------------------------------------------------------------
}
