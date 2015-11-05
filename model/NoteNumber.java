package model;

/**
 * Created by erik on 19/10/15.
 */
public class NoteNumber {



    private String note;
    private int number;

    NoteNumber (String _note, int _number)
    {
        note=_note;
        number=_number;
    }

    @Override
    public String toString() {
        return "NoteNumber{" +
                "note='" + note + '\'' +
                ", number=" + number +
                '}';
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
