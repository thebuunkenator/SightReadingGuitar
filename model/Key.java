package model;

import abc.notation.Note;

public class Key {

    private Note note;
    private String name = "Unknown";
    private String keyNote = "C";
    private int numFlats = 0;
    private int numSharps = 0;




    Key (Note note, String _name, int _numSharps, int _numFlats)
    {
        this.note = note;

        name = _name;

        //deze klopt nog niet helemaal
        //moet eigenlijk eerste spatie zoeken.
 //       int spaceLocation = _name.indexOf(" ");

//        keyNote = _name.toLowerCase().substring(0,spaceLocation);
        numFlats = _numFlats;
        numSharps = _numSharps;
    }

    ////// Getters and Setters //////

    public Note getNote() {
        return note;
    }
    public String getName() {
        return name;
    }
    public int getNumFlats() {
        return numFlats;
    }

    public int getNumSharps() {
        return numSharps;
    }

    @Override
    public String toString() {
        return name;
    }


}



