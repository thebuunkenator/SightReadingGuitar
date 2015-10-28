public class Key {

    private String name = "Unknown";
    private String keyNote = "C";
    private int numFlats = 0;
    private int numSharps = 0;


    Key (String _name, int _numSharps, int _numFlats)
    {
        name = _name;

        //deze klopt nog niet helemaal
        //moet eigenlijk eerste spatie zoeken.
        int spaceLocation = _name.indexOf(" ");

        keyNote = _name.toLowerCase().substring(0,spaceLocation);
        numFlats = _numFlats;
        numSharps = _numSharps;
    }

    ////// Getters and Setters //////
    public String getName() {
        return name;
    }

    public String getKeyNote() {
        return keyNote;
    }

    public int getNumFlats() {
        return numFlats;
    }

    public int getNumSharps() {
        return numSharps;
    }

    public void setNumSharps(int numSharps) {
        this.numSharps = numSharps;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKeyNote(String keyNote) {
        this.keyNote = keyNote;
    }

    public void setNumFlats(int numFlats) {
        this.numFlats = numFlats;
    }


    @Override
    public String toString() {
        return name;
    }
}



