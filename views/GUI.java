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

        //Create a new Frame
        setSize(840, 600); //The window Dimensions
        setMinimumSize(new Dimension(600,450));
        setLocationRelativeTo(null); //center window
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        //create panel with border layout
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(0, 0, 600, 600);

        fretboardPanel = new FretboardPanel();
        panel.add(fretboardPanel, BorderLayout.CENTER);

        staffPanel = new StaffPanel();
        staffPanel.setPreferredSize(new Dimension(200,200));
        panel.add(staffPanel, BorderLayout.SOUTH);

        toolbarPanel = new ToolbarPanel();
        panel.add(toolbarPanel, BorderLayout.NORTH);

        //add Panel to Window Frame
        add(panel);

        //add Menu
        // at the moment the menu is not used
//        setJMenuBar(new MainMenu());

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
        //TODO: De volgende werkt nog niet (appname in title balk)

        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", APP_NAME);

        try {
            //Native platform Look and Feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            //Cross platform Look and Feel
//            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch ( ClassNotFoundException | InstantiationException |
                  UnsupportedLookAndFeelException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

    }


}


