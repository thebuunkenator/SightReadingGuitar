
import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.lang.System;


/**
 * Created by buu1 on 22-10-2015.
 */
public class FretboardPanel extends Canvas {

    int IMG_WIDTH  = 778;
    int IMG_HEIGHT = 487;

    int POS0_FRET  = 43; //was 29
    int POS12_FRET = 532;
    int POS22_FRET = 745;

    int MARGIN_TOP = 216; //was 30

    int MARGIN_RIGHT = IMG_WIDTH - POS22_FRET;
    int MARGIN_BOTTOM = IMG_HEIGHT - 278;

    //int margin;// = 29;
    int marginLeft;
    int marginRight;
    int marginTop;
    int marginBottom;

    double rcTop;
    double rcBottom;

    int fretboardWidth;
    int fretboardHeight;
    int snaarhoogte;
    int numFrets = 22;
    double scale;// = ((516-38)*2);
    double ratio;

    private Image fretboard;

    /**
     * Initialisaties
     */
    private void setup() {
        println("Setup...");

    }

    /**
     * In tegenstelling tot Processing wordt draw niet continu aangeroepen
     */
    private void draw() {

        Dimension size = new Dimension();
        getSize(size);

        ratio = size.getWidth()/IMG_WIDTH;

        //scale = ((516-38)*2);
//        Dimension size = new Dimension(IMG_WIDTH,IMG_HEIGHT);
//        setPreferredSize(size);

        background(83,0,0);

        // drawClef
        loadImage();


//        int width = (int)(size.getWidth());
//        int height = (int)(size.getWidth()*ratio);
        int width = (int)(IMG_WIDTH*ratio);
        int height = (int)(IMG_HEIGHT*ratio);
        int y = (int)((getHeight() -height)*0.5); // center in panel
        //int y = 0;

        drawImage(fretboard, 0, y, width, height);

        //margin =
        marginLeft = (int)(POS0_FRET*ratio);
        marginRight = (int)(16*ratio);
        marginTop = (int)(MARGIN_TOP*ratio+y);
        marginBottom = 0;
        fretboardWidth=(int)((POS22_FRET-POS0_FRET)*ratio);
        fretboardHeight=(int)((278-MARGIN_TOP)*ratio);
        snaarhoogte = (int)(fretboardHeight/6.0);
        scale = (int)(((POS12_FRET-POS0_FRET)*2)*ratio);

//        drawWood();
        drawFrets();
//        drawDots();
//        drawStrings();
        calculate();
    }

    private void loadImage() {
        if (fretboard == null) {
            fretboard = new ImageIcon("img/gitaar_part.png").getImage();
        }

    }



    //==========FRETBOARD==========

    void drawWood() {
        fill(112, 74, 55);
        stroke(0, 0, 0);
        rectMode(CORNER);
        rect(marginLeft, marginTop , fretboardWidth, fretboardHeight );

    }

    void drawStrings() {
        strokeWeight(5);
        stroke(223, 223, 223);

        for (int i = 0; i < 6; i++) {
            line(marginLeft, (int)(marginTop + snaarhoogte * (i+0.5)), fretboardWidth+marginLeft, (int)(marginTop + snaarhoogte * (i+0.5)));
        }

        //shadow
        strokeWeight(5);
        stroke(0, (float)0.25);
        for (int i = 0; i < 6; i++) {
            line(marginLeft, (int)(marginTop + snaarhoogte * (i+0.5)+snaarhoogte*0.25), fretboardWidth+marginLeft, (int)(marginTop + snaarhoogte * (i+0.5)+snaarhoogte*0.25));
        }
        strokeWeight(1);
    }
    //
    void drawFrets() {
        strokeWeight((int)(4*ratio));
        stroke (200,200,200);
        for (int i = 0; i < numFrets + 1; i++) {
            line((int)(marginLeft + distanceFromNut(scale, i)),  (int) (marginTop) , (int) (marginLeft + distanceFromNut(scale, i)), (int)((fretboardHeight)+ marginTop ));
        }

        //0-fret
//        stroke(240, 240, 240);
//        strokeWeight(15);
//        line((int) (margin + distanceFromNut(scale, 0)), fretboardHeight - margin * 2 / 3, (int) (margin + distanceFromNut(scale, 0)), margin * 2 / 3);
//        stroke(152, 152, 152);
    }


    double distanceFromNut(double s, int n) {
        //float s = 100;
        //d distance from nut, s scale length, n = fretnumber
        return s - (s / Math.pow(2.0, (n / 12.0)));


    }

    void drawDots() {
        dot(3);
        dot(5);
        dot(7);
        doubleDot(12);
    }

    void dot(int n) {
        ellipseMode(CENTER);
        fill(188, 188, 188);
        stroke(0, 0, 0);
        double gemiddelde = (distanceFromNut(scale, n) + distanceFromNut(scale, n - 1)) / 2;
        ellipse(marginLeft + (int) gemiddelde, fretboardHeight / 2, 15, 15);
    }
    //
    void doubleDot(int n) {
        ellipseMode(CENTER);
        double gemiddelde = (distanceFromNut(scale, n) + distanceFromNut(scale, n - 1)) / 2;
        ellipse((int)(marginLeft + gemiddelde), (int)(marginTop + snaarhoogte * 1.5), 15, 15);
        ellipse((int)(marginLeft + gemiddelde), (int)(marginTop + snaarhoogte * 3.5), 15, 15);
    }


    void calculate()
    {

        // sommige variabelen hernoemen tot iets logisch

        /**
         * in:
         * numFrets in de foto
         * POS_FINGER - omdat er geen -1 fret is en je hem soms handmatig wil bepalen
         * POS0_FRET - xwaarde-pixel in de foto
         * POS12_FRET x waarde pixel in foto
         * POS22_FRET x waarde pixel in defoto
         * y waarde pixel elke snaar op 0 fret en 22 fret
         */

        int[] fretPositions = new int[numFrets+1];
        int[] fingerPositions = new int[numFrets+1];
        int[][] stringPositions = new int[6][numFrets+1];
        int POS0_FINGER = 43; // deze hard meenemen

        //fretPositions - positie van de fret
        for(int i=0; i<23; i++) {
            fretPositions[i] =  (int)(POS0_FRET+distanceFromNut(((POS12_FRET-POS0_FRET)*2), i));
//            System.out.println("Fret: " + i + ": " + fretPositions [i]);

        }

        //x posities van de vinger (aanduiding van noot)
        fingerPositions[0] = POS0_FINGER;
        for(int i=1; i<=numFrets; i++) {
            fingerPositions[i] = (int)((fretPositions [i] + fretPositions[i-1])/2);

//            System.out.println("Finger: " + i + ": " + fingerPositions[i]);
        }

        // y posities van de snaar op fret 0 en 22
        int[][] s = {   {1,	221, 209},
                        {2, 232, 224},
                        {3,	242, 239},
                        {4,	253, 253},
                        {5,	263, 268},
                        {6,	274, 283}};

        double rc_b[][] = new double [6][6]; //[richtingscoefficent a][b]

        for(int snaar = 0;snaar<6; snaar++){
            rc_b[snaar][0]=((double)(s[snaar][2]-s[snaar][1])/(double)(POS22_FRET-POS0_FRET));
            rc_b[snaar][1] = (s[snaar][1])-(rc_b[snaar][0]*POS0_FRET);;
//            System.out.println("y=" + rc_b[snaar][0] + "x + " + rc_b[snaar][1]);
        }

        for(int snaar = 0; snaar<6; snaar ++) {
            for (int i = 0; i <= numFrets; i++) {
                stringPositions[snaar][i] = (int) (rc_b[snaar][0] * fretPositions[i] + rc_b[snaar][1]);
//                System.out.println("string " + (snaar + 1) +" pos: " + i + ": " + stringPositions[snaar][i]);
            }
        }

    }


    // Necessities
    public FretboardPanel() {
        setup();
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
}