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
  @Override
  public DataObject2 transform1to2(DataObject1 dataObject1) throws RemoteException
  {
    System.out.println("VMSide2Impl --> transform1to2(" + dataObject1 + ")");
    return DataObjectUtils.toDataObject2(dataObject1);
  }
  //-----------------------------------------------------------------------------
  @Override
  public DataObject1 transform2to1(DataObject2 dataObject2) throws RemoteException
  {
    System.out.println("VMSide2Impl --> transform2to1(" + dataObject2 + ")");
    return DataObjectUtils.toDataObject1(dataObject2);
  }
  //-----------------------------------------------------------------------------
}
