package views;

import abc.midi.TunePlayerListenerInterface;
import abc.notation.AccidentalType;
import abc.notation.KeySignature;
import abc.notation.Tune;
import abc.notation.Note;
import abc.parser.TuneParser;
import abc.ui.swing.JScoreComponent;
import model.QuizNote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by erik on 19/10/15.
 */
public class StaffPanel extends JPanel implements ActionListener {

    private KeySignature key;
    private Tune tune;
    private Tune.Music music;
    private String currentScale;
    private String currentKey;
    JScoreComponent scoreUI;

    public StaffPanel() {

        tune = new Tune();
        key = new KeySignature(Note.B, KeySignature.MAJOR);
        Note note =  new Note(Note.b, AccidentalType.NONE);

        music = tune.getMusic();
        music.addElement(key);
        music.addElement(note);

        scoreUI =new JScoreComponent();
        scoreUI.setSize(75);
        scoreUI.setJustification(true);

        scoreUI.setTune(tune);

        String tuneAsString = "X:1\n" +
                "T:Excercise\n" +
                "K:Cmajor\n" +
                "C";
        Tune tune = new TuneParser().parse(tuneAsString);

        scoreUI.setTune(tune);
        add(scoreUI);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        scoreUI.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

//    public void resetTune(byte keyNote, byte keyAccidental, byte mode, Note noteToDraw ) {
    public void resetTune(String key, String scale, String note, String octave) {

        currentScale = scale;
        currentKey = key;

        if (note.equals("")) {
            note = key.substring(0,1);
        }

        // TODO checken of we dit hier willen doen.
        String accidental ="";

        if(key.length() > 1) {
            if (key.substring(1, 2).equals("#")) {
                accidental = "^";
            } else if (key.substring(1, 2).equals("b")) {
                accidental = "_";
            }
        }
        //Cb -> ziet hij als 2 noten
        String tuneAsString = "X:1\n" +
                "T:Excercise\n" +
                "K:" + key + scale + "\n" + /*accidental + */ note.substring(0,1) + octave;
        //System.out.println(tuneAsString);
        Tune tune = new TuneParser().parse(tuneAsString);
        scoreUI.setTune(tune);

    }

    public void singleQuizNote(QuizNote qn) {

        String octave = "";
        int midiNr = qn.getMidiNumber();

        //dit kan natuurlijk netter;
        if(midiNr<48) {
            octave = ",,";
        }

        if(midiNr>=48 && midiNr < 60) {
            octave = ",";
        }

        if(midiNr>=60 && midiNr < 72) {
            octave = "";
        }

        if(midiNr>=72 && midiNr<84) {
            octave = "'";
        }

        if(midiNr>=84 && midiNr<96) {
            octave = "''";
        }

        System.out.println(qn.getNoteName()+octave);
        resetTune(currentKey, currentScale, qn.getNoteName(), octave);
    }

}
