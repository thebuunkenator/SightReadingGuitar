package model;

public class FingeringSystem {

  private FingeringPosition name;
  private String keyNote;
  private int keyString;
  private int startBox;
  private int endBox;
//  intervallen van toonladder
  FingeringSystem(FingeringPosition _name, int _keyString, int _startBox, int _endBox)
  {
    name=_name;
    keyNote = _name.toString().substring(0,1).toLowerCase();
    keyString = _keyString;
    startBox = _startBox;
    endBox = _endBox;
  }

  public int getKeyString() {
    return keyString;
  }
  public int getStartBox() {
    return startBox;
  }
  public int getEndBox() {
    return endBox;
  }

  @Override
    public String toString() {
        return name.toString();
    }


}


