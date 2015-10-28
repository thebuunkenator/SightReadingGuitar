public class System {

  String name;
  String keyNote;
  int keyString;
  int startBox;
  int endBox;
//  intervallen van toonladder

  System (String _name, int _keyString, int _startBox, int _endBox)
  {
    name=_name;
    keyNote = _name.substring(0,1).toLowerCase();
    keyString = _keyString;
    startBox = _startBox;
    endBox = _endBox;
  }

    @Override
    public String toString() {
        return name;
    }
}


