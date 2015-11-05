package views;/*
    GUI is the main GUI Frame
 */

import javax.swing.*;
import java.awt.*;

// THIS IS the VIEW

public class GUI extends JFrame {

    final static String APP_NAME = "Sightreading";

    private ToolbarPanel toolbarPanel;
    private StaffPanel staffPanel;
    private FretboardPanel fretboardPanel;

    public GUI(){
        setTitle(APP_NAME);
        setLookAndFeel();
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", APP_NAME);

        //Create a new Frame
        setSize(800, 600); //The window Dimensions
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        //create panel with border layout
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(0, 0, 600, 600);

        fretboardPanel = new FretboardPanel();
        panel.add(fretboardPanel, BorderLayout.CENTER);
//        processing.core.PApplet sketch = new ProcessingSketch();
//        panel.add(sketch, BorderLayout.CENTER);

        staffPanel = new StaffPanel();
        staffPanel.setPreferredSize(new Dimension(200,200));
        panel.add(staffPanel, BorderLayout.SOUTH);

        toolbarPanel = new ToolbarPanel();
        panel.add(toolbarPanel, BorderLayout.NORTH);

        //add Panel to Window Frame
        add(panel);

        //add Menu

        setJMenuBar(new MainMenu());

        setVisible(true);
    }

    /*
    Getters and setters
     */

    public ToolbarPanel getToolbarPanel() {
        return toolbarPanel;
    }

    public StaffPanel getStaffPanel() {
        return staffPanel;
    }

    public FretboardPanel getFretboardPanel() {
        return fretboardPanel;
    }


    private void setLookAndFeel () {
        // set the look and feel
        try {
            //Native platform Look and Feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            //Cross platform Look and Feel
//            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        //TODO: De volgende werkt nog niet (appname in title balk)

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

