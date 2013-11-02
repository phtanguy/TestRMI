package fr.philippe_tanguy;

import java.io.Serializable;

public class DataObject1 implements Serializable
{
  //-----------------------------------------------------------------------------
  private static final long serialVersionUID = 7459249521789592591L;
  private              int  value;
  //-----------------------------------------------------------------------------
  public DataObject1(int value)
  {
    setValue(value);
  }
  //-----------------------------------------------------------------------------
  public int  getValue()          { return value;       }
  public void setValue(int value) { this.value = value; }
  //-----------------------------------------------------------------------------
  @Override
  public String toString()
  {
    return "DataObject1 [value=" + value + "]";
  }
  //-----------------------------------------------------------------------------
}
