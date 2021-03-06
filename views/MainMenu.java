package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * MainMenu
 *
 * @author erik
 * @version %I%, %G%
 * @since 1.0
 * date 31/10/15
 */
public class MainMenu extends JMenuBar {

    public MainMenu() {

        ///File Menu
        JMenu fileMenu = new JMenu("File");

        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.setMnemonic(KeyEvent.VK_N);
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
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
                KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
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
                KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

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
        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        JMenuItem redoMenuItem = new JMenuItem("Redo");
        JMenuItem cutMenuItem = new JMenuItem("Cut");
        cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        JMenuItem copyMenuItem = new JMenuItem("Copy");
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

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
