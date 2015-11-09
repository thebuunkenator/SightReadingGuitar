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

  public FingeringPosition getName() {
    return name;
  }

  public void setName(FingeringPosition name) {
    this.name = name;
  }

  public String getKeyNote() {
    return keyNote;
  }

  public void setKeyNote(String keyNote) {
    this.keyNote = keyNote;
  }

  public int getKeyString() {
    return keyString;
  }

  public void setKeyString(int keyString) {
    this.keyString = keyString;
  }

  public int getStartBox() {
    return startBox;
  }

  public void setStartBox(int startBox) {
    this.startBox = startBox;
  }

  public int getEndBox() {
    return endBox;
  }

  public void setEndBox(int endBox) {
    this.endBox = endBox;
  }

  @Override
    public String toString() {
        return name.toString();
    }


}


