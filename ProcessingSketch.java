import processing.core.*;

/**
 * Created by erik on 19/10/15.
 */

public class ProcessingSketch extends PApplet {


    public void setup() {
        SRGModel g = new SRGModel();
        size(1200, 550);

        textAlign(CENTER, CENTER);

        //draw Notes on the fretboard.
        // norw zijn afhankelijk van key
//        for (int string = 1; string <= 6; string++) {
//            println("String " + string);
//            for (int fret = 0; fret <= g.numFrets + 1; fret++) {
//                //int midinote =
//                String note = midiNoteNumbers.get(midiTuning[string - 1] + fret);
//                if (note != null) {
//                    println(fret, midiTuning[string - 1] + fret, note);
//                }
//            }
//        }


    }

    public void draw() {
        background(50);
        strokeCap(SQUARE);


//
//        drawFretboard();
        rect(mouseX,mouseY,200,200);
        //drawChord(3,0,0,0,2,3);
        //drawChord(0,0,2,2,2,0);

        //draw Notes on the fretboard.
        // norw zijn afhankelijk van key
        // for (int string=1; string<=6; string++)
        // {
        //  for(int noteNumber = 0; noteNumber<scaleNotes.length; noteNumber++)
        //  {
        //    drawNoteOnString(scaleNotes[noteNumber], string );
        //
        //  }
        // }


        //scaleNote --> midiNotes
    }



//draw Fretboard en

//    void drawFretboard() {
//        drawWood();
//        drawFrets();
//        drawStrings();
//        drawDots();
//        drawFingerFrame();
//    }
//
//    void drawChord(int i1, int i2, int i3, int i4, int i5, int i6) {
//        drawNoteWithName(1, i1, "");
//        drawNoteWithName(2, i2, "");
//        drawNoteWithName(3, i3, "");
//        drawNoteWithName(4, i4, "");
//        drawNoteWithName(5, i5, "");
//        drawNoteWithName(6, i6, "");
//    }
//
//
//    void drawNoteWithName(int stringNumber, int fret, String name) {
//
//        double gemiddelde;
//
//        fill(255, 255, 255);
//        stroke(0, 0, 0);
//        if (fret == 0) {
//            gemiddelde = -margin / 2;
//        } else {
//            gemiddelde = (distanceFromNut(scale, fret) + distanceFromNut(scale, fret - 1)) / 2;
//        }
//        ellipse((int) (margin + gemiddelde), (int) (margin + snaarhoogte * (stringNumber - 1)), 30, 30);
//        textSize(16);
//        fill(0);
//        text(name, (int) (margin + gemiddelde), (int) (margin + snaarhoogte * (stringNumber - 1)));
//    }
//
//
//    //Draw the finger box
//    void drawFingerFrame() {
//        if (useFrame) {
//            //println("position: " + position[0], position[1]);
//            rectMode(CORNERS);
//            stroke(255, 0, 0);
//            noFill();
//            strokeWeight(5);
//            if (position[0] == 0) {
//                rect(margin * 1 / 8, fretboardHeight - margin * 1 / 3, (float) (margin + distanceFromNut(scale, position[1])), margin * 1 / 3);
//            } else {
//                rect((float) (margin + distanceFromNut(scale, position[0] - 1)), fretboardHeight - margin * 1 / 3, (float) (margin + distanceFromNut(scale, position[1])), margin * 1 / 3);
//            }
//            strokeWeight(1);
//        }
//    }
//
//
//    //TODO: integreren met
//    void drawNoteOnString(String noteName, int stringNumber) {
//        //draw note on string
//        //f.e. draw g on 6st string
//        //get note from open string
//        int openString = noteNumbers.get(tuning[stringNumber - 1]);
//
//        int noteToDraw = noteNumbers.get(noteName);
//
//        if (noteToDraw != -1 && openString != -1) {
//
//            int fret = noteToDraw - openString;
//
//            if (fret < 0) {
//                fret += 12;
//            }
//
//            //TODO: alleen tonen binnen het vlak werkt nog niet
//            int octave = fret + 12;
//            //if (useFrame == true && fret >= position[0] && fret<=position[1])
//            {
//                drawNoteWithName(stringNumber, fret, noteName);
//
//
//                if (octave < numFrets) {
//                    //if (useFrame == true && octave >= position[0] && octave<=position[1])
//                    {
//                        drawNoteWithName(stringNumber, octave, noteName);
//                    }
//                }
//            }
//
//        } else {
//            log("not found");
//            log(openString);
//            log(noteToDraw);
//        }
//
//
//    }
//
//
//    double distanceFromNut(double s, int n) {
//        //float s = 100;
//        //d distance from nut, s scale length, n = fretnumber
//        return s - (s / pow(2.0, (n / 12.0)));
//
//
//    }
//
//
//    int getFretbyNoteOnString(String _note, int _stringNumber) {
//        //dit wordt de nieuwe generieke functie
//        log("=>getFretbyNoteOnString");
//        log("note: " + _note.toLowerCase());
//        log("string: " + _stringNumber);
//        String opString = tuning[_stringNumber - 1];
//
//        int noteToDraw = noteNumbers.get(_note);
//        int openString = noteNumbers.get(opString);
//        log("noteToDraw: " + noteToDraw);
//        log("openString: " + openString);
//
//        int fret = -1;
//
//        if (noteToDraw != -1 && openString != -1) {
//            //both found
//            fret = noteToDraw - openString;
//            if (fret < 0) {
//                fret += 12;
//            }
//        } else {
//            log("...not found");
//
//        }
//        return fret;
//    }

//==========FRETBOARD==========

//    void drawWood() {
//        fill(112, 74, 55);
//        stroke(0, 0, 0);
//        rectMode(CORNER);
//        rect(margin, margin * 2 / 3, fretboardWidth - margin, fretboardHeight - (margin * 4 / 3));
//        stroke(223, 223, 223);
//    }
//
//    void drawStrings() {
//        strokeWeight(5);
//        for (int i = 0; i < 6; i++) {
//            line(0, margin + snaarhoogte * i, fretboardWidth, margin + snaarhoogte * i);
//        }
//
//        //shadow
//        strokeWeight(5);
//        stroke(0, 25);
//        for (int i = 0; i < 6; i++) {
//            line(0, margin + snaarhoogte * i + 10, fretboardWidth, margin + snaarhoogte * i + 10);
//        }
//        strokeWeight(1);
//    }
//
//    void drawFrets() {
//        strokeWeight(10);
//        for (int i = 0; i < numFrets + 1; i++) {
//            line((float) (margin + distanceFromNut(scale, i)), fretboardHeight - margin * 2 / 3, (float) (margin + distanceFromNut(scale, i)), margin * 2 / 3);
//        }
//
//        //0-fret
//        stroke(240);
//        strokeWeight(15);
//        line((float) (margin + distanceFromNut(scale, 0)), fretboardHeight - margin * 2 / 3, (float) (margin + distanceFromNut(scale, 0)), margin * 2 / 3);
//        stroke(152, 152, 152);
//    }
//
//    void drawDots() {
//        dot(3);
//        dot(5);
//        dot(7);
//        doubleDot(12);
//    }
//
//    void dot(int n) {
//        ellipseMode(CENTER);
//        fill(188, 188, 188);
//        stroke(0, 0, 0);
//        double gemiddelde = (distanceFromNut(scale, n) + distanceFromNut(scale, n - 1)) / 2;
//        ellipse(margin + (int) gemiddelde, fretboardHeight / 2, 15, 15);
//    }
//
//    void doubleDot(int n) {
//        ellipseMode(CENTER);
//        double gemiddelde = (distanceFromNut(g.scale, n) + distanceFromNut(g.scale, n - 1)) / 2;
//        ellipse(margin + (int) gemiddelde, margin + snaarhoogte * 1.5, 15, 15);
//        ellipse(margin + (int) gemiddelde, margin + snaarhoogte * 3.5, 15, 15);
//    }

}
