package model;

import abc.notation.Note;

/**
 * QuizNote
 *
 * @author erik
 * @version %I%, %G%
 * @since 1.0
 * date:      07/11/15
 */
public class QuizNote {

    int string;
    int fret;
    int midiNumber;
    String noteName;
    private static int id;
//  Note note;

    public QuizNote(int string, int fret, int midiNumber, String noteName) {
        this.string = string;
        this.fret = fret;
        this.midiNumber = midiNumber;
        this.noteName = noteName;
        id++;
    }

    //Getters and Setters
    public int getString() {
        return string;
    }

    public int getFret() {
        return fret;
    }

    public int getMidiNumber() {
        return midiNumber;
    }

    public int getID() {
        return id;
    }

    public String getNoteName() {
        return noteName;
    }

    @Override
    public String toString() {
        return "QuizNote{" +
                "string=" + string +
                ", fret=" + fret +
                ", noteName='" + noteName + '\'' +
                ", midiNumber=" + midiNumber +
                '}';
    }
}
