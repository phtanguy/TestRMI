package fr.philippe_tanguy;

public class DataObjectUtils
{
  //-----------------------------------------------------------------------------
  public static DataObject1 toDataObject1(DataObject2 dataObject2)
  {
    return new DataObject1(dataObject2.getValue());
  }
  //-----------------------------------------------------------------------------
  public static DataObject2 toDataObject2(DataObject1 dataObject1)
  {
    return new DataObject2(dataObject1.getValue());
  }
  //-----------------------------------------------------------------------------
}
