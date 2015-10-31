/*
    GUI is the main GUI Frame
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GUI extends JFrame {

    final static String APP_NAME = "Sightreading";
    private JMenuBar menuBar;

    public GUI(SRGModel model){

        setLookAndFeel();

        setTitle(APP_NAME);

        //Create a new Frame
        setSize(800, 600); //The window Dimensions
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        //create panel with border layout
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(0, 0, 600, 600);

        FretboardPanel fretboardPanel = new FretboardPanel();
        panel.add(fretboardPanel, BorderLayout.CENTER);
//        processing.core.PApplet sketch = new ProcessingSketch();
//        panel.add(sketch, BorderLayout.CENTER);



        StaffPanel staffPanel = new StaffPanel();
        staffPanel.setPreferredSize(new Dimension(200,200));
        panel.add(staffPanel, BorderLayout.SOUTH);

        ToolbarPanel toolbarPanel = new ToolbarPanel(model, staffPanel);
        panel.add(toolbarPanel, BorderLayout.NORTH);


        //add Panel to Window Frame
        add(panel);

        //add Menu
        setJMenuBar(createMenuBar());

        //start the sketch
//        sketch.init(); //this is the function used to start the execution of the sketch

        setVisible(true);
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
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", APP_NAME);
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

    private JMenuBar createMenuBar() {

        //use Mac Menu bar
        System.setProperty("apple.laf.useScreenMenuBar", "true");

        menuBar = new JMenuBar();

        ///File Menu
        JMenu fileMenu = new JMenu("File");

        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.setMnemonic(KeyEvent.VK_N);
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
        //newMenuItem.setActionCommand("New");

        JMenuItem openMenuItem = new JMenuItem("Open");
//        openMenuItem.setMnemonic(KeyEvent.VK_O);
//        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));

        //Action for open File
        Action openAction = new AbstractAction("Open") {

            @Override
            public void actionPerformed(ActionEvent e) {

                /**
                 * Default java dialog
                 */
                // use FileDialog.SAVE voor save dialog
                FileDialog fd = new FileDialog((Frame)null, "Choose a file", FileDialog.LOAD); //lukt niet om de frame te krijgen
                fd.setDirectory(".");
                //  fd.setFilenameFilter((dir, name) -> name.endsWith(".txt"));
                fd.setFile("*.svg");
                fd.setVisible(true);
                String filename = fd.getDirectory() + fd.getFile();
                if (filename == null)
                    System.out.println("You cancelled the choice");
                else {
                    System.out.println("Opening files: " + filename);
                    //centerPanel.appendText("file:" + filename); /* werkt niet... weet nog niet waarom */

                }

                /**
                 * Swing Dialog
                 */
//                JFileChooser fileChooser = new JFileChooser();
//                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
//                int result = fileChooser.showOpenDialog(null);
//                if (result == JFileChooser.APPROVE_OPTION) {
//                    File selectedFile = fileChooser.getSelectedFile();
//                    System.out.println("Opening file: " + selectedFile.getAbsolutePath());
//                    centerPanel.appendText("Opening file: " + selectedFile.getAbsolutePath());
//                    //todo do something with file/send message
//                }
            }
        };

        openAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));

        openAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_O);
        openMenuItem.setAction(openAction);
        /**
         * save
         */

        JMenuItem saveMenuItem = new JMenuItem("Save As ...");

        //Action for open File
        Action saveAction = new AbstractAction("Save As ...") {

            @Override
            public void actionPerformed(ActionEvent e) {

                /**
                 * Default java dialog
                 */
                // use FileDialog.SAVE voor save dialog
                FileDialog fd = new FileDialog((Frame)null, "Choose a filename", FileDialog.SAVE);
                fd.setDirectory(".");
                //  fd.setFilenameFilter((dir, name) -> name.endsWith(".txt"));
                fd.setFile("*.svg");
                fd.setVisible(true);
                String filename = fd.getDirectory() + fd.getFile();
                if (filename == null)
                    System.out.println("You cancelled the choice");
                else {
                    System.out.println("file:" + filename);

                }

            }
        };

        saveAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));

        saveAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
        saveMenuItem.setAction(saveAction);

        /**
         * Exit
         */
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_F4);
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, Event.ALT_MASK));

        /**
         * Add File Menu items
         */
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);


        ///Edit Menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem undoMenuItem = new JMenuItem("Undo");
        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK));
        JMenuItem redoMenuItem = new JMenuItem("Redo");
        JMenuItem cutMenuItem = new JMenuItem("Cut");
        cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
        JMenuItem copyMenuItem = new JMenuItem("Copy");
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK));

        editMenu.add(undoMenuItem);
        editMenu.add(redoMenuItem);
        editMenu.addSeparator();
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);

        // Window Menu
        JMenu windowMenu = new JMenu("Window");
        JMenuItem minimizeMenuItem = new JMenuItem("Minimize");
        JMenuItem zoomMenuItem = new JMenuItem("Zoom");
        windowMenu.add(minimizeMenuItem);
        windowMenu.add(zoomMenuItem);

        //Help Menu
        JMenu helpMenu = new JMenu("Help");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(windowMenu);
        menuBar.add(helpMenu);


        return menuBar;
    }

}


