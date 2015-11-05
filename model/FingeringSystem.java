package model;

public class FingeringSystem {

  FingeringPosition name;
  String keyNote;
  int keyString;
  int startBox;
  int endBox;
//  intervallen van toonladde
  FingeringSystem(FingeringPosition _name, int _keyString, int _startBox, int _endBox)
  {
    name=_name;
    keyNote = _name.toString().substring(0,1).toLowerCase();
    keyString = _keyString;
    startBox = _startBox;
    endBox = _endBox;
  }

    @Override
    public String toString() {
        return name.toString();
    }


}


