package fr.philippe_tanguy;

import java.rmi.RemoteException;

public class VMSide1Impl implements IVMSide1
{
  //-----------------------------------------------------------------------------
  @Override
  public String toUpperCase(String s) throws RemoteException
  {
    System.out.println("VMSide1Impl --> toUpperCase(\"" + s + "\")");
    return s.toUpperCase();
  }
  //-----------------------------------------------------------------------------
}
