package model;

import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;

import abc.notation.AccidentalType;
import abc.notation.KeySignature;
import abc.notation.Note;

import static processing.core.PApplet.*;

// THIS IS THE MODEL

public class SRGModel {

    final String scaleNotesDefault[] = {"c", "d", "e", "f", "g", "a", "b"};

    //midituning: begint bij snaar 0; in de rest van de code wort uitgegaan van snaar 1 voor hoge e snaar.
    int[] midiTuning = {64, 59, 55, 50, 45, 40}; // midi noot toon hoogte nodig om vertaling naar notenschrift te maken

//    int[] majorScale = {2, 2, 1, 2, 2, 2, 1};
    int[] position = {0, 5};

    String sharps[] = {"-", "f#", "c#", "g#", "d#", "a#", "e#", "b#"};
    String flats[] = {"-", "bb", "eb", "ab", "db", "gb", "cb", "fb"};
    String scaleNotes[] = {"c", "d", "e", "f", "g", "a", "b"};



    // Current Settings
    Scale currentScale;
    Note currentKey;
    FingeringSystem currentFingeringSystem;


    private HashMap<String, Integer> noteNumbers = new HashMap<String, Integer>();
    private HashMap<Integer, String> midiNoteNumbers = new HashMap<Integer, String>();
    private ArrayList<Key> keys = new ArrayList<Key>();
    private ArrayList<FingeringSystem> fingeringSystems = new ArrayList<>();
    private ArrayList<MidiNote> midiNotes = new ArrayList();
    private ArrayList<Scale> scales  = new ArrayList();
    private ArrayList<QuizNote> quizNotes =  new ArrayList<>();

    public SRGModel() {
        initKeys();
        initSystems();
        initScales();
        initMidiNotes();
        generateQuizNotes(false);
    }

    private void initScales() {
        scales.add(new Scale(KeySignature.MAJOR, "Major"));
//        scales.add(new Scale(KeySignature.MINOR, "Minor"));
//        scales.add(new Scale(KeySignature.AEOLIAN, "Aeolian"));
//        scales.add(new Scale(KeySignature.DORIAN, "Dorian"));
//        scales.add(new Scale(KeySignature.IONIAN, "Ionian"));
//        scales.add(new Scale(KeySignature.LOCRIAN, "Locrian"));
//        scales.add(new Scale(KeySignature.LYDIAN, "Lydian"));
//        scales.add(new Scale(KeySignature.MIXOLYDIAN, "Mixolydian"));
//        scales.add(new Scale(KeySignature.PHRYGIAN, "Phrygian"));

    }

    private void initKeys() {
        keys.add(new Key(new Note(Note.C), "C", 0, 0));
        keys.add(new Key(new Note(Note.G), "G", 1, 0));
        keys.add(new Key(new Note(Note.D), "D", 2, 0));
        keys.add(new Key(new Note(Note.A), "A", 3, 0));
        keys.add(new Key(new Note(Note.E), "E", 4, 0));
        keys.add(new Key(new Note(Note.B, AccidentalType.NONE), "B", 5, 0));
        keys.add(new Key(new Note(Note.F, AccidentalType.SHARP), "F#", 6, 0));
        keys.add(new Key(new Note(Note.C, AccidentalType.SHARP), "C#", 7, 0));
        keys.add(new Key(new Note(Note.F), "F", 0, 1));
        keys.add(new Key(new Note(Note.B, AccidentalType.FLAT), "Bb", 0, 2));
        keys.add(new Key(new Note(Note.E, AccidentalType.FLAT), "Eb", 0, 3));
        keys.add(new Key(new Note(Note.A, AccidentalType.FLAT), "Ab", 0, 4));
        keys.add(new Key(new Note(Note.D, AccidentalType.FLAT), "Db", 0, 5));
        keys.add(new Key(new Note(Note.G, AccidentalType.FLAT), "Gb", 0, 6));
    }

    void initSystems() {
        fingeringSystems.add(new FingeringSystem(FingeringPosition.C_CAGED, 5, -3, 0));
        fingeringSystems.add(new FingeringSystem(FingeringPosition.A_CAGED, 5, -1, 3));
        fingeringSystems.add(new FingeringSystem(FingeringPosition.G_CAGED, 6, -4, 0));
        fingeringSystems.add(new FingeringSystem(FingeringPosition.E_CAGED, 6, -1, 2));
        fingeringSystems.add(new FingeringSystem(FingeringPosition.D_CAGED, 4, -1, 3));
//        fingeringSystems.add(new FingeringSystem(FingeringPosition.E_3_PER_STRING, 6, 0, 5));
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
        midiNotes.add(new MidiNote(50, "ef", "", "d", "", "cx"));  //d
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

    public String[] getKeysArray() {
        String [] s;
        s = new String[keys.size()];

        for (int i = 0; i<keys.size(); i++) {
            s[i] = keys.get(i).toString();
        }


        return s;
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
            //System.out.print(s[i]);
        }
        return s;

    }

    public void setCurrentScale(Scale currentScale) {
        this.currentScale = currentScale;

    }


    public void setCurrentKey(Note currentKey) {
        this.currentKey = currentKey;
        //replace notes in array

        resetScaleNotesArray();
        Key key = getKey(currentKey);
        //println("flats and sharps" + key.getNumFlats() + key.getNumSharps());
        for(int i=1; i<=key.getNumSharps(); i++) {
            replaceNotesInArray(sharps[i]);
        }
        for(int i=1; i<=key.getNumFlats(); i++) {
            replaceNotesInArray(flats[i]);
        }
        System.out.println(getScalesArray());
        // update Notes in notes to draw
        // update frame position
    }

    public FingeringSystem getCurrentFingeringSystem() {
        return currentFingeringSystem;
    }

    public void setCurrentFingeringSystem(FingeringSystem currentFingeringSystem) {
        this.currentFingeringSystem = currentFingeringSystem;
    }

    private void replaceNotesInArray(String sharpflat) {
        for (int i = 0; i < scaleNotes.length; i++)

        {
            if (scaleNotes[i].equals(sharpflat.substring(0, 1))) {
                scaleNotes[i] = sharpflat;
            }
        }
    }

    private void resetScaleNotesArray() {

        arrayCopy(scaleNotesDefault, scaleNotes);
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

    public Scale getScaleWithName(String s) {
        for (Scale currentScale : scales) {
            if (currentScale.getName().equals(s)) {
                return currentScale;
            }
        }
        return null;
    }

    private Key getKey(Note note) {
        for(Key key :keys) {
            if(note.equals(key.getNote())) {
                return key;
            }
        }
        return null;
    }

    public void setPosition(int start, int end) {
        position[0] =  start;
        position[1] = end;
    }

    public void printTooladder() {
        String s;
        for (int i =0; i< scaleNotes.length; i++) {
            System.out.println (scaleNotes[i]);
        }

    }
    public void generateQuizNotes(boolean useBox) {
        int start = 0;
        int end = 24;

        if (useBox){
            start = position[0];
            end = position[1];
        }

        quizNotes.clear();
        for(int snaar = 1; snaar <= 6; snaar ++) {
           // System.out.println("Snaar " + snaar);
            for(int fret = start; fret <= end; fret ++){ // alle frets -> moet worden frets in view
                int currentMidiNote = midiTuning[snaar-1]+fret;
                    String match = matchNoteMetMidiPitch(currentMidiNote, scaleNotes);
                    if(!match.equals("")) {
                        QuizNote tmpQuizNote = new QuizNote(snaar,fret,currentMidiNote,match);
                        quizNotes.add(tmpQuizNote);
                    }
            }
        }

    }

    private String matchNoteMetMidiPitch(int midiPitch, String [] s) {
        MidiNote note = findMidiNoteByPitch(midiPitch);

        if(note ==  null) {
            return "";
        }

        for (int i =0; i< s.length; i++) { // alle scale notes
            if(note.getNormal().equals(s[i])){
                return s[i];
            }

            if(note.getDoubleFlat().equals(s[i])){
                return s[i];
            }
            if(note.getDoubleSharp().equals(s[i])){
                return s[i];
            }
            if(note.getFlat().equals(s[i])){
                return s[i];
            }
            if(note.getSharp().equals(s[i])){
                return s[i];
            }
        }

        return  "";
    }


    private String matchNoteMetMidiPitch(int midiPitch, String s) {
        MidiNote note = findMidiNoteByPitch(midiPitch);

        if(note ==  null) {
            return "";
        }
        if(note.getNormal().equals(s)){
            return s;
        }

        if(note.getDoubleFlat().equals(s)){
            return s;
        }
        if(note.getDoubleSharp().equals(s)){
            return s;
        }
        if(note.getFlat().equals(s)){
            return s;
        }
        if(note.getSharp().equals(s)){
            return s;
        }
        return  "";
    }

    private MidiNote findMidiNoteByPitch (int pitch) {

        for(MidiNote currentMidiNote : midiNotes) {
            if(currentMidiNote.getMidiNumber() == pitch) {
                return currentMidiNote;
            }
        }

        return null;
    }

    public ArrayList<QuizNote> getQuizNotes() {
        return quizNotes;
    }

    public int findNoteOnString(String note, int snaar) { //returns fretNumber

        for(int fret = 0; fret <= 24; fret ++){ // alle frets -> moet worden frets in view
            int currentMidiNote = midiTuning[snaar-1]+fret;
            String match = matchNoteMetMidiPitch(currentMidiNote, note.toLowerCase());
            if(!match.equals("")) {
                return fret;
           }
        }
        return -1;
    }

    public FingeringSystem findFingeringSystemByName(String name) {
        System.out.println("Finding fs: "+name);
        for (FingeringSystem f : fingeringSystems) {
            System.out.print(f.toString());
            if(f.toString().toLowerCase().equals(name.toLowerCase())){
                return f;
            }

        }
        return null;

    }

}