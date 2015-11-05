package views;

import abc.notation.AccidentalType;
import abc.notation.KeySignature;
import abc.notation.Tune;
import abc.notation.Note;
import abc.parser.TuneParser;
import abc.ui.swing.JScoreComponent;

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
    public void resetTune(String key, String scale) {

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
                "K:" + key + scale + "\n" + /*accidental + */ key.substring(0,1) ;
        //System.out.println(tuneAsString);
        Tune tune = new TuneParser().parse(tuneAsString);
        scoreUI.setTune(tune);
    }

}
