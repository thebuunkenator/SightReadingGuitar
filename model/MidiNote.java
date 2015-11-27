package model;

public class MidiNote {

    private int midiNumber;
    private String doubleFlat;
    private String flat;
    private String normal;
    private String sharp;
    private String doubleSharp;

    MidiNote(int _midiNumber, String _doubleFlat, String _flat, String _normal, String _sharp, String _doubleSharp) {
        midiNumber = _midiNumber;
        doubleFlat = _doubleFlat;
        flat = _flat;
        normal = _normal;
        sharp = _sharp;
        doubleSharp = _doubleSharp;
    }

    public int getMidiNumber() {
        return midiNumber;
    }

    public String getDoubleFlat() {
        return doubleFlat;
    }
    public String getFlat() {
        return flat;
    }
    public String getNormal() {
        return normal;
    }
    public String getSharp() {
        return sharp;
    }
    public String getDoubleSharp() {
        return doubleSharp;
    }


}