package fr.philippe_tanguy;

import java.rmi.RemoteException;

public class VMSideImpl implements IVMSide
{
  //-----------------------------------------------------------------------------
  @Override
  public String toUpperCase(String s) throws RemoteException
  {
    return s.toUpperCase();
  }
  //-----------------------------------------------------------------------------
}
