/*
    GUI is the main GUI Frame
 */

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    public GUI(SRGModel model){

        //Create a new Frame
        setSize(600, 600); //The window Dimensions
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //create panel with border layout
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(0, 0, 600, 600);

        FretboardPanel fretboardPanel = new FretboardPanel();
        panel.add(fretboardPanel, BorderLayout.CENTER);
//        processing.core.PApplet sketch = new ProcessingSketch();
//        panel.add(sketch, BorderLayout.CENTER);

        SelectionPanel selectionPanel = new SelectionPanel(model);
        panel.add(selectionPanel, BorderLayout.NORTH);

        StaffPanel staffPanel = new StaffPanel(model);
        staffPanel.setPreferredSize(new Dimension(200,200));
        panel.add(staffPanel, BorderLayout.SOUTH);


        //add Panel to Window Frame
        add(panel);

        //start the sketch
//        sketch.init(); //this is the function used to start the execution of the sketch

        setVisible(true);
    }


//User Interaction

//Mouse Interactions


//    //Initialize GUI
//
//
//    void recalculateBox() {
//        resetScaleNotesArray();
//        // add sharps
//        for (int i = 1; i <= keys.get(currentKey).numSharps; i++) {
//            replaceNotesInArray(sharps[i]);
//        }
//
//        // add flats
//        for (int i = 1; i <= keys.get(currentKey).numFlats; i++) {
//            replaceNotesInArray(flats[i]);
//        }
//
//        String note = keys.get(currentKey).keyNote;
//        String opString = tuning[scaleSystems.get(currentSystem).keyString - 1];
//
//        int noteToDraw = noteNumbers.get(note);
//        int openString = noteNumbers.get(opString);
//
//        if (noteToDraw != -1 && openString != -1) {
//            int fret = noteToDraw - openString;
//            if (fret < 0) {
//                fret += 12;
//            }
//            if (fret + scaleSystems.get(currentSystem).startBox < 0) {
//                fret += 12;
//            }
//
//            position[0] = fret + scaleSystems.get(currentSystem).startBox;
//            position[1] = fret + scaleSystems.get(currentSystem).endBox;
//        } else {
//            log("note note found");
//        }

//    }
}


