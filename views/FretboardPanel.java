package views;

import java.awt.*;
import java.lang.*;
import java.lang.System;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


/**
 * Created by buu1 on 22-10-2015.
 */
public class FretboardPanel extends views.Canvas {

    float margin;
    int extraRuimteKam;
    int marginTop;
    int marginLeft;
    int fretboardWidth;
    int fretboardHeight;
    double stringLength;
    float snaarhoogte;
    double scale;
    int numFrets = 21;
    int distanceFromEdge=16; // afstand tussen de snaar en de rand van de hals
    boolean useFrame;
    String[] tuning = { "e", "b", "g", "d", "a", "e" };
    String[] currentScaleNotes = {"c", "d", "e", "f", "g", "a", "b"};
    private int[] position = { 1, 5};

    HashMap <String, Integer> noteNumbers = new HashMap<String, Integer>();

    private ArrayList<QuizNote> quizNotes =  new ArrayList<>();

    /**
     * In tegenstelling tot Processing wordt draw niet continu aangeroepen
     */
    private void draw() {

        background(125,0,0);

        Dimension size = new Dimension();
        getSize(size);

        margin = 5;
        extraRuimteKam = 32;
        //TODO Extra ruimte voor de kam (nul-fret) van 32 px
        // nog wat te weinig ruimte links om noten te plaatsen.
        // ook nog wat ruimte rechts over laten
        fretboardWidth = (int)(size.width - 2* margin - extraRuimteKam);
        marginLeft=(int)margin+extraRuimteKam;

        fretboardHeight = 160;
        if(size.height<160) {
            fretboardHeight = size.height;
        }

        //TODO scale is nog een klein beetje onnauwkeurig
        //Varieer de lengte van de hals, afhankelijk van het scherm. Naar analogie van Earmaster
        if(size.width<=685) {
            numFrets = 12;
            scale = fretboardWidth * 2;
        }

        if(size.width > 685 && size.width <= 892 ) {
            numFrets = 16;
            scale = fretboardWidth * 1.8;
        }

        if(size.width > 892 && size.width <= 1125 ) {
            numFrets = 20;
            scale = fretboardWidth * 1.6;
        }

        if(size.width > 1125) {
            numFrets = 24;
            scale = fretboardWidth*1.33;
        }

        snaarhoogte =(int) ((fretboardHeight - 32)/5);
        stringLength = fretboardWidth;


        //center fretboard verticaal
        marginTop = (int)((size.height - fretboardHeight)*0.5);

        //teken de onderdelen
        drawWood();
        drawFrets();
        drawStrings();
        drawDots();
        drawFingerFrame();

        drawAllQuizNotes();


//        drawNoteOnString("e",5);
//
//        for(int snaar = 1; snaar <= 6 ; snaar ++){
//            for(String scaleNote : currentScaleNotes) {
//
//                drawNoteOnString(scaleNote, snaar);
//            }
//        }
    }


    //==========FRETBOARD==========



    //Draw the finger box
    void drawFingerFrame() {
        if(useFrame) {
            //println("position: " + position[0], position[1]);
            rectMode(CORNER);
            stroke(255,0,0);
            noFill();
            strokeWeight(5);
            if(position[0]==0) {
                rect(   margin,
                        marginTop,
                        (float)(distanceFromNut(scale,position[1])+extraRuimteKam), //marginLeft ivm 0 op eerste parameter
                        fretboardHeight);
            }
            else
            {
                rect(   (float)(distanceFromNut(scale,position[0]-1)+marginLeft),
                        marginTop,
                        (float)(distanceFromNut(scale,position[1])-distanceFromNut(scale,position[0]-1)),
                        fretboardHeight);
                //rect((float)(margin + distanceFromNut(scale,position[0]-1)),fretboardHeight-margin*1/3, (float)(margin + distanceFromNut(scale,position[1])), margin*1/3);
            }
            strokeWeight(1);
        }
    }

    void drawWood() {
        //fill(161,123, 88); //licht bruin (ear master)
        fill(112, 74,55);
        stroke(216,181, 120);
        rectMode(CORNER);
        rect(margin, marginTop, fretboardWidth + marginLeft-margin, fretboardHeight);

    }

    void drawStrings() {



        stroke(214,214,214);
        strokeWeight(1);
        for (int i = 0; i<6; i++)
        {
            if (i ==3 || i==4)
                strokeWeight(2);
            if (i==5)
                strokeWeight(3);
            line((int)margin, (int)(marginTop+snaarhoogte*i)+distanceFromEdge,
                    (int)fretboardWidth+extraRuimteKam+marginLeft,(int)(marginTop+snaarhoogte*i)+distanceFromEdge);
        }

        //shadow
        strokeWeight(1);
        stroke(0, (float)0.25);
        for (int i = 0; i<6; i++)
        {
            if (i ==3 || i==4) {
                strokeWeight(2);
            }
            if (i==5) {
                strokeWeight(3);
            }
            line(0, (int) (marginTop + snaarhoogte * i) + 16 + 3, (int) fretboardWidth, (int) (marginTop + snaarhoogte * i) + 16 + 3);
        }
        strokeWeight(1);
    }

    void drawFrets() {
        stroke(206,181,139);

        for (int i = 0; i<numFrets+1; i++)
        {
            if (i==0) // kam iets breder
                strokeWeight(7);
            else
                strokeWeight(4);
            line((int)(margin + extraRuimteKam + distanceFromNut(scale,i)),(int)(marginTop),
                    (int)(margin + extraRuimteKam + distanceFromNut(scale,i)), (int)(fretboardHeight+marginTop));
        }
        strokeWeight(1);
        //0-fret
          //ca 32 pixels ervoor vrij houden

//        stroke (240,240,240);
//        strokeWeight(7);
//        line((int)(margin + distanceFromNut(scale,0)),(int)(fretboardHeight-margin*2/3), (int)(margin + distanceFromNut(scale,0)),(int)(margin*2/3));
//        stroke(152,152,152);
    }

    void drawDots() {
        dot(3);
        dot(5);
        dot(7);
        doubleDot(12);
        dot(15);
        dot(17);
        dot(19);
        doubleDot(24);
    }

    void dot(int n)
    {
        ellipseMode(CENTER);
        fill(230, 192,111);
        stroke(255,255,255);
        double gemiddelde = (distanceFromNut(scale,n)+distanceFromNut(scale,n-1))/2;
        ellipse ((int)(marginLeft+gemiddelde), (int)(fretboardHeight*0.5+marginTop),12,12);

    }

    void doubleDot(int n)
    {
        ellipseMode(CENTER);
        double gemiddelde = (distanceFromNut(scale,n)+distanceFromNut(scale,n-1))/2;
        ellipse ((int)(marginLeft+gemiddelde), (int)(marginTop+16+snaarhoogte*1.5),12,12);
        ellipse ((int)(marginLeft+gemiddelde), (int)(marginTop+16+snaarhoogte*3.5),12,12);
    }


    void drawNoteWithName(int stringNumber, int fret, String name)
    {

        double gemiddelde;

        fill(255, 255,255);
        stroke(0,0,0);
        if (fret == 0 ){
            gemiddelde= ( extraRuimteKam)-1.35*marginLeft;
        } else {
            gemiddelde = (distanceFromNut(scale,fret)+distanceFromNut(scale,fret-1))/2;
        }
        ellipse ((int)(margin+ extraRuimteKam+ gemiddelde), (int)((marginTop+distanceFromEdge+snaarhoogte*(stringNumber-1))),
                (int)snaarhoogte, (int)snaarhoogte-5);
        //textSize(16);
        fill(0,0,0);
        //TODO correcties op x en y zijn eigenlijk gevolg van niet goed centreren; 5 en 2
        text(name, (int)(margin + extraRuimteKam +gemiddelde), (int)(marginTop+distanceFromEdge+ snaarhoogte*(stringNumber-1)));
    }


    //TODO: integreren met draw Note with Name
    void drawNoteOnString(String noteName, int stringNumber)
    {
        //draw note on string
        //f.e. draw g on 6st string
        //get note from open string
        int openString = noteNumbers.get(tuning[stringNumber-1]);

        int noteToDraw = noteNumbers.get(noteName);

        if(noteToDraw != -1 && openString != -1)
        {

            int fret = noteToDraw - openString;

            if (fret<0) {
                fret+=12;
            }

            //TODO: alleen tonen binnen het vlak werkt nog niet
            int octave = fret + 12;
            //if (useFrame == true && fret >= position[0] && fret<=position[1])
            {
                drawNoteWithName(stringNumber, fret, noteName);


                if (octave<numFrets)
                {
                    //if (useFrame == true && octave >= position[0] && octave<=position[1])
                    {
                        drawNoteWithName(stringNumber, octave, noteName);
                    }
                }
            }

        }
        else
        {
            System.out.println("not found");
            System.out.println(openString);
            System.out.println(noteToDraw);
        }


    }


    double distanceFromNut (double s, int n){
        //float s = 100;
        //d distance from nut, s scale length, n = fretnumber
        return s-(s/Math.pow(2.0, (n / 12.0)));


    }

    int getFretbyNoteOnString(String _note, int _stringNumber)
    {
        //dit wordt de nieuwe generieke functie
        System.out.println("=>getFretbyNoteOnString");
        System.out.println("note: " + _note.toLowerCase());
        System.out.println("string: " + _stringNumber);
        String opString =tuning[_stringNumber-1];

        int noteToDraw = noteNumbers.get(_note);
        int openString = noteNumbers.get(opString);
        System.out.println("noteToDraw: " + noteToDraw);
        System.out.println("openString: " + openString);

        int fret = -1;

        if(noteToDraw != -1 && openString != -1)
        {
            //both found
            fret = noteToDraw - openString;
            if (fret<0) {
                fret+=12;
            }
        }
        else
        {
            System.out.println("...not found");

        }
        return fret;
    }

    // Necessities
    public FretboardPanel() {

        fretboardWidth = 1200;
        fretboardHeight = 300;

        margin = 50;
        marginLeft = 50;
        marginTop = 50;
        numFrets=15;
        useFrame=true;
        textAlign(CENTER, CENTER);

        addNoteNumbers();

    }

    public void addNoteNumbers() {
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

    public void setPosition(int start, int eind) {
        position[0] = start;
        position[1] = eind;

    }

    public void setTuning(String [] s) {
        currentScaleNotes = s;
    }



    public void drawAllQuizNotes() {
        if(quizNotes ==  null || quizNotes.isEmpty()) {
            println("empty array");

            return;
        }

        for (QuizNote currentNote : quizNotes) {

            drawNoteWithName(currentNote.getString(), currentNote.getFret(), currentNote.getNoteName());
        }
    }

    public void setQuizNotes(ArrayList<QuizNote> quizNotes) {
        this.quizNotes = quizNotes;
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        if(g!=null) {
            setDC((Graphics2D) g);
            draw();
        } else {
//            System.out.println("Error getting Graphics context");
        }
    }

    @Override
    public String toString() {
        return "FretboardPanel{" +
                "numFrets=" + numFrets +
                ", tuning=" + Arrays.toString(tuning) +
                '}';
    }
}