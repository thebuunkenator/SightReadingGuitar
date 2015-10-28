
import abc.notation.AccidentalType;
import abc.notation.KeySignature;
import abc.notation.Tune;
import abc.notation.Note;
import abc.ui.swing.JScoreComponent;
import com.sun.tools.corba.se.idl.constExpr.Not;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import  java.lang.System;
import java.util.Iterator;
//import java.util.Random;


/**
 * Created by erik on 19/10/15.
 */
public class StaffPanel extends JPanel implements ActionListener {

//    private final int DELAY = 150;
//    private Timer timer;
    private SRGModel model;
    private KeySignature key;
    private Tune tune;
    private Tune.Music music;
    JScoreComponent scoreUI;


    public StaffPanel(SRGModel model) {
        this.model = model;

        tune = new Tune();
        key = new KeySignature(Note.D, KeySignature.MAJOR);

        tune.getMusic().addElement(key);
        music = tune.getMusic();

        music.addElement(new Note(Note.g, AccidentalType.FLAT, (byte) -1));

        scoreUI =new JScoreComponent();
        scoreUI.setSize(75);
        scoreUI.setJustification(true);

        scoreUI.setTune(tune);

        add(scoreUI);
    }



//    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        //doDrawing(g);
        scoreUI.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void setNote (Note note) {
        System.out.println("setNote");
        String notes = tune.getNotes();

        music.removeAllElements();
        music.addElement(note);
        scoreUI.setTune(tune);
        repaint();
    }

//    public Note getNote() {
//        return (Note)music.getElementAt(0);
//    }
//
//    public void setKey (KeySignature key) {
//        System.out.println("setKey");
//        Note note = getNote();
//
//        music.removeAllElements();
//
//        tune = new Tune();
//
//        tune.getMusic().addElement(key);
//        music = tune.getMusic().addElement(note);
//
//
//        scoreUI.setTune(tune);
//        repaint();
//    }


}
