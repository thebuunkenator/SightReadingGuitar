import java.lang.*;
import java.lang.System;
import java.util.ArrayList;
import java.util.HashMap;

import static processing.core.PApplet.pow;
import static processing.core.PApplet.arrayCopy;

import abc.notation.AccidentalType;
import abc.notation.KeySignature;
import abc.notation.Note;


public class SRGModel {

    public final static int F_SELECT = 0;
    final static String scaleNotesDefault[] = {
            "c", "d", "e", "f", "g", "a", "b"
    };
    boolean LOG = true;
    float margin;
    int fretboardWidth;
    int fretboardHeight;
    double stringLength;
    float snaarhoogte;
    double scale;
    int numFrets = 21;
    String[] tuning = {"e", "b", "g", "d", "a", "e" };

    int[] midiTuning = {
            64, 59, 55, 50, 45, 40
    }; // midi noot toon hoogte nodig om vertaling naar notenschrift te maken

    int[] majorScale = {
            2, 2, 1, 2, 2, 2, 1
    };
    int[] position = {
            0, 5
    };
    boolean useFrame;
    String sharps[] = {
            "-", "f#", "c#", "g#", "d#", "a#", "e#", "b#"
    };
    String flats[] = {
            "-", "bb", "eb", "ab", "db", "gb", "cb", "fb"
    };

    String scaleNotes[] = {
            "c", "d", "e", "f", "g", "a", "b"
    };

    int currentSystem = 0;
    int currentKey = 0;
    int currentNote = 59;//b
    //int currentNote = 64;

    private HashMap<String, Integer> noteNumbers = new HashMap<String, Integer>();
    private HashMap<Integer, String> midiNoteNumbers = new HashMap<Integer, String>();
    private ArrayList<Key> keys = new ArrayList<Key>();
    private ArrayList<FingeringSystem> fingeringSystems = new ArrayList<>();
    private ArrayList<MidiNote> midiNotes = new ArrayList();
    private ArrayList<Scale> scales  = new ArrayList();

    public SRGModel() {
        initKeys();
        initSystems();
        initScales();
        addNoteNumbers();
        initMidiNotes();

        fretboardWidth = 1200;
        fretboardHeight = 300;
        currentSystem = 0;
        currentKey = 0;

        margin = 50;
        numFrets = 15;
        useFrame = true;

        stringLength = fretboardWidth - 2 * margin;
        snaarhoogte = (fretboardHeight - 2 * margin) / 5;

        scale = stringLength / distanceFromNut(1, numFrets);


    }

    private void initScales() {
        scales.add(new Scale(KeySignature.MAJOR, "Major"));
        scales.add(new Scale(KeySignature.MINOR, "Minor"));
        scales.add(new Scale(KeySignature.AEOLIAN, "Aeolian"));
        scales.add(new Scale(KeySignature.DORIAN, "Dorian"));
        scales.add(new Scale(KeySignature.IONIAN, "Ionian"));
        scales.add(new Scale(KeySignature.LOCRIAN, "Locrian"));
        scales.add(new Scale(KeySignature.LYDIAN, "Lydian"));
        scales.add(new Scale(KeySignature.MIXOLYDIAN, "Mixolydian"));
        scales.add(new Scale(KeySignature.PHRYGIAN, "Phrygian"));

    }

    private void initKeys() {
        keys.add(new Key(new Note(Note.C), "C ", 0, 0));
        keys.add(new Key(new Note(Note.G), "G ", 1, 0));
        keys.add(new Key(new Note(Note.D), "D ", 2, 0));
        keys.add(new Key(new Note(Note.A), "A ", 3, 0));
        keys.add(new Key(new Note(Note.B), "E ", 4, 0));
        keys.add(new Key(new Note(Note.B), "B ", 5, 0));
        keys.add(new Key(new Note(Note.F, AccidentalType.SHARP), "F# ", 6, 0));
        keys.add(new Key(new Note(Note.F, AccidentalType.SHARP), "C# ", 7, 0));
        keys.add(new Key(new Note(Note.F), "F ", 0, 1));
        keys.add(new Key(new Note(Note.B, AccidentalType.FLAT), "Bb ", 0, 2));
        keys.add(new Key(new Note(Note.E, AccidentalType.FLAT), "Eb ", 0, 3));
        keys.add(new Key(new Note(Note.A, AccidentalType.FLAT), "Ab ", 0, 4));
        keys.add(new Key(new Note(Note.D, AccidentalType.FLAT), "Db ", 0, 5));
        keys.add(new Key(new Note(Note.G, AccidentalType.FLAT), "Gb ", 0, 6));
        keys.add(new Key(new Note(Note.C, AccidentalType.FLAT), "Cb ", 0, 7));
    }

    void initSystems() {

        fingeringSystems.add(new FingeringSystem("C (CAGED)", 5, -3, 0));
        fingeringSystems.add(new FingeringSystem("A (CAGED)", 5, -1, 3));
        fingeringSystems.add(new FingeringSystem("G (CAGED)", 6, -4, 0));
        fingeringSystems.add(new FingeringSystem("E (CAGED)", 6, -1, 2));
        fingeringSystems.add(new FingeringSystem("D (CAGED)", 4, -1, 3));
        fingeringSystems.add(new FingeringSystem("E (3 per string)", 6, 0, 5));
    }


    private void addNoteNumbers() {
        noteNumbers.put("b#", 0);
        noteNumbers.put("c", 0);
        noteNumbers.put("c#", 1);
        noteNumbers.put("db", 1);
        noteNumbers.put("d", 2);
        noteNumbers.put("d#", 3);
        noteNumbers.put("eb", 3);
        noteNumbers.put("e", 4);
        noteNumbers.put("fb", 4);
        noteNumbers.put("e#", 5);
        noteNumbers.put("f", 5);
        noteNumbers.put("f#", 6);
        noteNumbers.put("gb", 6);
        noteNumbers.put("g", 7);
        noteNumbers.put("ab", 8);
        noteNumbers.put("g#", 8);
        noteNumbers.put("a", 9);
        noteNumbers.put("a#", 10);
        noteNumbers.put("bb", 10);
        noteNumbers.put("b", 11);
        noteNumbers.put("cb", 11);
    }


    private void initMidiNotes() {
        midiNotes.add(new MidiNote(0, "df", "", "c", "b#", ""));
        midiNotes.add(new MidiNote(1, "", "db", "", "c#", "bx"));
        midiNotes.add(new MidiNote(2, "ef", "", "d", "", "cx"));
        midiNotes.add(new MidiNote(3, "ff", "eb", "", "d#", ""));
        midiNotes.add(new MidiNote(4, "", "fb", "e", "", "dx"));
        midiNotes.add(new MidiNote(5, "gf", "", "f", "e#", ""));
        midiNotes.add(new MidiNote(6, "", "gb", "", "f#", "ex"));
        midiNotes.add(new MidiNote(7, "af", "", "g", "", "fx"));
        midiNotes.add(new MidiNote(8, "", "ab", "", "g#", ""));
        midiNotes.add(new MidiNote(9, "bf", "", "a", "", "gx"));
        midiNotes.add(new MidiNote(10, "cf", "bb", "", "a#", ""));
        midiNotes.add(new MidiNote(11, "", "cb", "b", "", "ax"));
        midiNotes.add(new MidiNote(12, "df", "", "c", "b#", ""));
        midiNotes.add(new MidiNote(13, "", "db", "", "c#", "bx"));
        midiNotes.add(new MidiNote(14, "ef", "", "d", "", "cx"));
        midiNotes.add(new MidiNote(15, "ff", "eb", "", "d#", ""));
        midiNotes.add(new MidiNote(16, "", "fb", "e", "", "dx"));
        midiNotes.add(new MidiNote(17, "gf", "", "f", "e#", ""));
        midiNotes.add(new MidiNote(18, "", "gb", "", "f#", "ex"));
        midiNotes.add(new MidiNote(19, "af", "", "g", "", "fx"));
        midiNotes.add(new MidiNote(20, "", "ab", "", "g#", ""));
        midiNotes.add(new MidiNote(21, "bf", "", "a", "", "gx"));
        midiNotes.add(new MidiNote(22, "cf", "bb", "", "a#", ""));
        midiNotes.add(new MidiNote(23, "", "cb", "b", "", "ax"));
        midiNotes.add(new MidiNote(24, "df", "", "c", "b#", ""));
        midiNotes.add(new MidiNote(25, "", "db", "", "c#", "bx"));
        midiNotes.add(new MidiNote(26, "ef", "", "d", "", "cx"));
        midiNotes.add(new MidiNote(27, "ff", "eb", "", "d#", ""));
        midiNotes.add(new MidiNote(28, "", "fb", "e", "", "dx"));
        midiNotes.add(new MidiNote(29, "gf", "", "f", "e#", ""));
        midiNotes.add(new MidiNote(30, "", "gb", "", "f#", "ex"));
        midiNotes.add(new MidiNote(31, "af", "", "g", "", "fx"));
        midiNotes.add(new MidiNote(32, "", "ab", "", "g#", ""));
        midiNotes.add(new MidiNote(33, "bf", "", "a", "", "gx"));
        midiNotes.add(new MidiNote(34, "cf", "bb", "", "a#", ""));
        midiNotes.add(new MidiNote(35, "", "cb", "b", "", "ax"));
        midiNotes.add(new MidiNote(36, "df", "", "c", "b#", ""));
        midiNotes.add(new MidiNote(37, "", "db", "", "c#", "bx"));
        midiNotes.add(new MidiNote(38, "ef", "", "d", "", "cx"));
        midiNotes.add(new MidiNote(39, "ff", "eb", "", "d#", ""));
        midiNotes.add(new MidiNote(40, "", "fb", "e", "", "dx")); //lage e
        midiNotes.add(new MidiNote(41, "gf", "", "f", "e#", ""));
        midiNotes.add(new MidiNote(42, "", "gb", "", "f#", "ex"));
        midiNotes.add(new MidiNote(43, "af", "", "g", "", "fx"));
        midiNotes.add(new MidiNote(44, "", "ab", "", "g#", ""));
        midiNotes.add(new MidiNote(45, "bf", "", "a", "", "gx"));  //a
        midiNotes.add(new MidiNote(46, "cf", "bb", "", "a#", ""));
        midiNotes.add(new MidiNote(47, "", "cb", "b", "", "ax"));
        midiNotes.add(new MidiNote(48, "df", "", "c", "b#", ""));
        midiNotes.add(new MidiNote(49, "", "db", "", "c#", "bx"));
        midiNotes.add(new MidiNote(50, "ef", "", "d", "", "cx")); //d
        midiNotes.add(new MidiNote(51, "ff", "eb", "", "d#", ""));
        midiNotes.add(new MidiNote(52, "", "fb", "e", "", "dx"));
        midiNotes.add(new MidiNote(53, "gf", "", "f", "e#", ""));
        midiNotes.add(new MidiNote(54, "", "gb", "", "f#", "ex"));
        midiNotes.add(new MidiNote(55, "af", "", "g", "", "fx")); //g snaar
        midiNotes.add(new MidiNote(56, "", "ab", "", "g#", ""));
        midiNotes.add(new MidiNote(57, "bf", "", "a", "", "gx"));
        midiNotes.add(new MidiNote(58, "cf", "bb", "", "a#", ""));
        midiNotes.add(new MidiNote(59, "", "cb", "b", "", "ax")); //b snaar
        midiNotes.add(new MidiNote(60, "df", "", "c", "b#", ""));
        midiNotes.add(new MidiNote(61, "", "db", "", "c#", "bx"));
        midiNotes.add(new MidiNote(62, "ef", "", "d", "", "cx"));
        midiNotes.add(new MidiNote(63, "ff", "eb", "", "d#", ""));
        midiNotes.add(new MidiNote(64, "", "fb", "e", "", "dx")); //hoge e
        midiNotes.add(new MidiNote(65, "gf", "", "f", "e#", ""));
        midiNotes.add(new MidiNote(66, "", "gb", "", "f#", "ex"));
        midiNotes.add(new MidiNote(67, "af", "", "g", "", "fx"));
        midiNotes.add(new MidiNote(68, "", "ab", "", "g#", ""));
        midiNotes.add(new MidiNote(69, "bf", "", "a", "", "gx")); //440Hz a
        midiNotes.add(new MidiNote(70, "cf", "bb", "", "a#", ""));
        midiNotes.add(new MidiNote(71, "", "cb", "b", "", "ax"));
        midiNotes.add(new MidiNote(72, "df", "", "c", "b#", ""));
        midiNotes.add(new MidiNote(73, "", "db", "", "c#", "bx"));
        midiNotes.add(new MidiNote(74, "ef", "", "d", "", "cx"));
        midiNotes.add(new MidiNote(75, "ff", "eb", "", "d#", ""));
        midiNotes.add(new MidiNote(76, "", "fb", "e", "", "dx")); //12 fret e op esnaar
        midiNotes.add(new MidiNote(77, "gf", "", "f", "e#", ""));
        midiNotes.add(new MidiNote(78, "", "gb", "", "f#", "ex"));
        midiNotes.add(new MidiNote(79, "af", "", "g", "", "fx"));
        midiNotes.add(new MidiNote(80, "", "ab", "", "g#", ""));
        midiNotes.add(new MidiNote(81, "bf", "", "a", "", "gx"));
        midiNotes.add(new MidiNote(82, "cf", "bb", "", "a#", ""));
        midiNotes.add(new MidiNote(83, "", "cb", "b", "", "ax")); //hoge noot klassiek
        midiNotes.add(new MidiNote(84, "df", "", "c", "b#", ""));
        midiNotes.add(new MidiNote(85, "", "db", "", "c#", "bx"));
        midiNotes.add(new MidiNote(86, "ef", "", "d", "", "cx"));
        midiNotes.add(new MidiNote(87, "ff", "eb", "", "d#", ""));
        midiNotes.add(new MidiNote(88, "", "fb", "e", "", "dx")); //24 frets
        midiNotes.add(new MidiNote(89, "gf", "", "f", "e#", ""));
        midiNotes.add(new MidiNote(90, "", "gb", "", "f#", "ex"));
        midiNotes.add(new MidiNote(91, "af", "", "g", "", "fx"));
        midiNotes.add(new MidiNote(92, "", "ab", "", "g#", ""));
        midiNotes.add(new MidiNote(93, "bf", "", "a", "", "gx"));
        midiNotes.add(new MidiNote(94, "cf", "bb", "", "a#", ""));
        midiNotes.add(new MidiNote(95, "", "cb", "b", "", "ax"));
        midiNotes.add(new MidiNote(96, "df", "", "c", "b#", ""));
        midiNotes.add(new MidiNote(97, "", "db", "", "c#", "bx"));
        midiNotes.add(new MidiNote(98, "ef", "", "d", "", "cx"));
        midiNotes.add(new MidiNote(99, "ff", "eb", "", "d#", ""));
        midiNotes.add(new MidiNote(100, "", "fb", "e", "", "dx"));
        midiNotes.add(new MidiNote(101, "gf", "", "f", "e#", ""));
        midiNotes.add(new MidiNote(102, "", "gb", "", "f#", "ex"));
        midiNotes.add(new MidiNote(103, "af", "", "g", "", "fx"));
        midiNotes.add(new MidiNote(104, "", "ab", "", "g#", ""));
        midiNotes.add(new MidiNote(105, "bf", "", "a", "", "gx"));
        midiNotes.add(new MidiNote(106, "cf", "bb", "", "a#", ""));
        midiNotes.add(new MidiNote(107, "", "cb", "b", "", "ax"));
        midiNotes.add(new MidiNote(108, "df", "", "c", "b#", ""));
        midiNotes.add(new MidiNote(109, "", "db", "", "c#", "bx"));
        midiNotes.add(new MidiNote(110, "ef", "", "d", "", "cx"));
        midiNotes.add(new MidiNote(111, "ff", "eb", "", "d#", ""));
        midiNotes.add(new MidiNote(112, "", "fb", "e", "", "dx"));
        midiNotes.add(new MidiNote(113, "gf", "", "f", "e#", ""));
        midiNotes.add(new MidiNote(114, "", "gb", "", "f#", "ex"));
        midiNotes.add(new MidiNote(115, "af", "", "g", "", "fx"));
        midiNotes.add(new MidiNote(116, "", "ab", "", "g#", ""));
        midiNotes.add(new MidiNote(117, "bf", "", "a", "", "gx"));
        midiNotes.add(new MidiNote(118, "cf", "bb", "", "a#", ""));
        midiNotes.add(new MidiNote(119, "", "cb", "b", "", "ax"));
        midiNotes.add(new MidiNote(120, "df", "", "c", "b#", ""));
        midiNotes.add(new MidiNote(121, "", "db", "", "c#", "bx"));
        midiNotes.add(new MidiNote(122, "ef", "", "d", "", "cx"));
        midiNotes.add(new MidiNote(123, "ff", "eb", "", "d#", ""));
        midiNotes.add(new MidiNote(124, "", "fb", "e", "", "dx"));
        midiNotes.add(new MidiNote(125, "gf", "", "f", "e#", ""));
        midiNotes.add(new MidiNote(126, "", "gb", "", "f#", "ex"));
        midiNotes.add(new MidiNote(127, "af", "", "g", "", "fx"));
    }

    public ArrayList<Integer> getMidiNotes(String noteToFind) {
        ArrayList<Integer> noteList = new ArrayList<Integer>();

        for (MidiNote element : midiNotes) {
            if (element.getDoubleFlat().equals(noteToFind)) {
                noteList.add((Integer) element.getMidiNumber());
                midiNoteNumbers.put(element.getMidiNumber(), noteToFind);
            }
            if (element.getFlat().equals(noteToFind)) {
                noteList.add((Integer) element.getMidiNumber());
                midiNoteNumbers.put(element.getMidiNumber(), noteToFind);
            }
            if (element.getNormal().equals(noteToFind)) {
                noteList.add((Integer) element.getMidiNumber());
                midiNoteNumbers.put(element.getMidiNumber(), noteToFind);
            }
            if (element.getSharp().equals(noteToFind)) {
                noteList.add((Integer) element.getMidiNumber());
                midiNoteNumbers.put(element.getMidiNumber(), noteToFind);
            }
            if (element.getDoubleSharp().equals(noteToFind)) {
                noteList.add((Integer) element.getMidiNumber());
                midiNoteNumbers.put(element.getMidiNumber(), noteToFind);
            }
        }
        //println(noteList);
        return noteList;
    }

    public ArrayList<Key> getKeys() {
        return keys;
    }

    public String[] getKeysArray() {
        String [] s;
        s = new String[keys.size()];

        for (int i = 0; i<keys.size(); i++) {
            s[i] = keys.get(i).toString();
            System.out.println("adding: " + i + "-" + keys.get(i).toString());
        }


        return s;
    }

    public ArrayList<FingeringSystem> getFingeringSystems() {
        return fingeringSystems;
    }

    public String[] getFingeringSystemsArray() {

        String [] s;
        s = new String[fingeringSystems.size()];

        for (int i = 0; i< fingeringSystems.size(); i++) {
            s[i] = fingeringSystems.get(i).toString();

        }

        return s;
    }

    public String[] getScalesArray() {
        String [] s;
        s = new String[scales.size()];

        for (int i = 0; i<scales.size(); i++) {
            s[i] = scales.get(i).toString();

        }

        return s;

    }

    private void replaceNotesInArray(String sharpflat) {
        for (int i = 0; i < scaleNotes.length; i++)

        {
            //log(scaleNotes[i] +  "<>" + sharpflat.substring(0,1));
            if (scaleNotes[i].equals(sharpflat.substring(0, 1))) {
                //log("replacing");
                scaleNotes[i] = sharpflat;
            }
        }
        //log(scaleNotes);
    }

    private void resetScaleNotesArray() {

        arrayCopy(scaleNotesDefault, scaleNotes);
    }
    /**
     * Method returns the the distance of the fret from the nut, in the same unit as scale
     *
     * @param s scale for example in pixels
     * @param n fretnumber
     * @return  distance in unit
     *
     */
    double distanceFromNut (double s, int n){
        //float s = 100;
        //d distance from nut, s scale length, n = fretnumber
        return s-(s/pow((float)2.0, (float)(n / 12.0)));
    }

    // tijdelijke functie totdat we betere manier hebben om direct id terug te krijgen
    public Note getNoteWithName(String s) {
        for (Key currentKey : keys) {
            if(currentKey.getName().equals(s)) {
                return currentKey.getNote();
            }
        }
        return null;
    }
}