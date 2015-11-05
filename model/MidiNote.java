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

    public void setMidiNumber(int midiNumber) {
        this.midiNumber = midiNumber;
    }

    public String getDoubleFlat() {
        return doubleFlat;
    }

    public void setDoubleFlat(String doubleFlat) {
        this.doubleFlat = doubleFlat;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    public String getSharp() {
        return sharp;
    }

    public void setSharp(String sharp) {
        this.sharp = sharp;
    }

    public String getDoubleSharp() {
        return doubleSharp;
    }

    public void setDoubleSharp(String doubleSharp) {
        this.doubleSharp = doubleSharp;
    }

}