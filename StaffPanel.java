
import abc.notation.KeySignature;
import abc.notation.Tune;
import abc.notation.Note;
import abc.ui.swing.JScoreComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import  java.lang.System;
//import java.util.Random;


/**
 * Created by erik on 19/10/15.
 */
public class StaffPanel extends JPanel implements ActionListener {

//    private final int DELAY = 150;
//    private Timer timer;
    private SRGModel model;

    private Image trebleClefImage;


    public StaffPanel(SRGModel model) {
        this.model = model;

        //TODO insert code abc4j
        Tune tune = new Tune();

        KeySignature key = new KeySignature(Note.D, KeySignature.MAJOR);
        tune.getMusic().addElement(key);
        Tune.Music music = tune.getMusic();
        music.addElement(new Note(Note.C));
        music.addElement(new Note(Note.D));
        music.addElement(new Note(Note.E));
        music.addElement(new Note(Note.F));
        music.addElement(new Note(Note.G));
        music.addElement(new Note(Note.A));
        music.addElement(new Note(Note.B));
        music.addElement(new Note(Note.c));
        music.addElement(new Note(Note.d));
        music.addElement(new Note(Note.e));
        music.addElement(new Note(Note.f));
        music.addElement(new Note(Note.g));
        music.addElement(new Note(Note.g));
        music.addElement(new Note(Note.f));
        music.addElement(new Note(Note.e));
        music.addElement(new Note(Note.d));
        music.addElement(new Note(Note.c));
        music.addElement(new Note(Note.B));
        music.addElement(new Note(Note.A));
        music.addElement(new Note(Note.G));
        music.addElement(new Note(Note.F));
        music.addElement(new Note(Note.E));
        music.addElement(new Note(Note.D));
        music.addElement(new Note(Note.C));
        JScoreComponent scoreUI =new JScoreComponent();
        scoreUI.setTune(tune);

        add(scoreUI);
    }


    private void doDrawing(Graphics g) {




        Graphics2D g2d = (Graphics2D) g;
        int margin = 50;
        int lineSpacing  = 17;

        g2d.setBackground(Color.white);
        g2d.setPaint(Color.black);

        int w = getWidth();
        int h = getHeight();


    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }


}
