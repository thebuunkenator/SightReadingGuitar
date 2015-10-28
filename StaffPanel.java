
import de.erichseifert.vectorgraphics2d.SVGGraphics2D;

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
    private Image halfNoteImage;
    private Image wholeNoteImage;
    private Image svgImage;
    private SVGGraphics2D svg;

    public StaffPanel(SRGModel model) {
        this.model = model;
    }


    private void doDrawing(Graphics g) {




        Graphics2D g2d = (Graphics2D) g;
        int margin = 50;
        int lineSpacing  = 17;

        g2d.setBackground(Color.white);
        g2d.setPaint(Color.black);

        int w = getWidth();
        int h = getHeight();


        //draw Staff
        for(int i = 0; i<5 ; i++) {
            g2d.drawLine(margin, margin+i*lineSpacing, w-margin, margin+i*lineSpacing);
        }

        // drawKeySignature
        g2d.drawString(model.getKeysArray()[model.currentKey], 20, 30);


        // drawClef
        loadImage();
        int clefSizeX = 63; //74*17/20
        int clefSizeY = (int)(clefSizeX * 1.93);
        g2d.drawImage(trebleClefImage, margin, 27, clefSizeX, clefSizeY, null);
        g2d.drawImage(svgImage, 0  , 0, clefSizeX, clefSizeY, null);

        //svg = new SVGGraphics2D(0,0,300,300);

        //svg.drawImage(svgImage, 0,0,null);


        //in: noot hoogte in midi
        //      noot naam
        //bepaal afstand tot basisnoot (bijv middel c)
        //bepaal aantal octaven. div 12. tussen noten -> elk octaaf is een vaste afstand 8*
        //bepaal afstan van c nar de noot
        /**
         * c-d 1
         * c-e 2
         * c-f 3
         * c-g 4
         * c-a 5
         * c-b 6
         *
         * deze afstand * lineSpacing is de afstand die de noot hoger is (dus eraftreekkn)
         *
         * heeft de noot een b, #, x, of bb dan deze ervoor plaatsen
         */

        //c: 48


        // indine hoger dan.. en lager dan.. teken met streepje


        //drawNote

        int currentNote = 50;
        String currentNoteString = "d";
        int c_note = 48;
//      int currentNote = model.currentNote;
        //double c_y= (float) (-1.0*(48-54)*(lineSpacing/2)+margin + 1.5*lineSpacing);
        double c_y = 126;
        System.out.println(c_y);

        for(int i=0; i<24; i++) {
            int delta_note = i;
            double y = c_y - (0.5 * lineSpacing * delta_note);
            //Todo omrekenen naar echte positie (rekening houdend met halve en hele noot overgangen.

            double x = w / 2;

            //wholeNoteImage.getHeight();

            g2d.drawImage(wholeNoteImage, (int) (x+25*i), (int) (y), wholeNoteImage.getWidth(null), wholeNoteImage.getHeight(null), null);

            if(currentNote ) {

            }
            //streepjes moeten niet elke keer
            //g2d.drawLine((int)(x+25*i-10), (int)y+9, (int)(x+25*i+35),(int)y+9);
        }

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

    private void loadImage() {
        //TODO relatif pad gebruiken
        trebleClefImage = new ImageIcon("/Users/erik/IdeaProjects/SightReadingGuitar/out/production/SightReadingGuitar/TrebleClef.png").getImage();
        halfNoteImage = new ImageIcon("/Users/erik/IdeaProjects/SightReadingGuitar/src/img/half_note.png").getImage();
        wholeNoteImage = new ImageIcon("/Users/erik/IdeaProjects/SightReadingGuitar/src/img/whole_note.png").getImage();
        svgImage = new ImageIcon("/Users/erik/IdeaProjects/SightReadingGuitar/illustrator/wholenote.svg").getImage();
    }
}
