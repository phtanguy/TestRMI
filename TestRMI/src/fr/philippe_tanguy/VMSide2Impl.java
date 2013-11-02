package fr.philippe_tanguy;

import java.rmi.RemoteException;

public class VMSide2Impl implements IVMSide2
{
  //-----------------------------------------------------------------------------
  @Override
  public int add(int i1, int i2) throws RemoteException
  {
    System.out.println("VMSide2Impl --> add(" + i1 + ", " + i2 + ")");
    return (i1 + i2);
  }
  //-----------------------------------------------------------------------------
}
