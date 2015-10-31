import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * MainMenu
 *
 * @author erik
 * @version %I%, %G%
 * @date 31/10/15
 * @since 1.0
 */
public class MainMenu extends JMenuBar {

    public MainMenu() {




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
                FileDialog fd = new FileDialog((Frame) null, "Choose a file", FileDialog.LOAD); //lukt niet om de frame te krijgen
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
                FileDialog fd = new FileDialog((Frame) null, "Choose a filename", FileDialog.SAVE);
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

        /*
         * Add File Menu items
         */
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(saveMenuItem);


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

        add(fileMenu);
        add(editMenu);
        add(windowMenu);
        add(helpMenu);

    }
}
