

import abc.notation.KeySignature;
import abc.notation.Note;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
//import java.lang.System;


public class ToolbarPanel extends JPanel {

    private StaffPanel staffPanel;
    private JLabel lblKey;
    private JLabel lblFingeringSystem;
    private JLabel lblEmpty;

    private JComboBox cmbKey; //A.b.c etcd
    private  JComboBox cmbScale; //major, dorian,,,
    private  JComboBox cmbFingeringSystem;

    private JButton btnStart;

    public ToolbarPanel(final SRGModel model, final StaffPanel staffPanel) {

        this.staffPanel = staffPanel;

        String[] keys = model.getKeysArray();
        String[] fingeringsSystems = model.getFingeringSystemsArray();
        final String[] scales = model.getScalesArray();

        JToolBar toolbar = new JToolBar();
        toolbar.setRollover(true);

        // Set up Combobox Keys
        cmbKey = new JComboBox();


        //cmbKey
        DefaultComboBoxModel keysModel = new DefaultComboBoxModel();

//
        for (String currentKey : keys) {
            System.out.println(currentKey);
            keysModel.addElement(currentKey);
        }
        cmbKey.setModel(keysModel);
        cmbKey.setSelectedIndex(0);


        //setup Combobox Scales
        cmbScale = new JComboBox(scales);
        cmbScale.setSelectedIndex(0);

        lblFingeringSystem = new JLabel("System:");
        cmbFingeringSystem = new JComboBox(fingeringsSystems);
        cmbFingeringSystem.setSelectedIndex(0);

        btnStart =  new JButton("Start");

        /* Listener s*/
        ActionListener updateActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Updating...");

                //Scale
                System.out.println("Scale: " +cmbScale.getSelectedItem());
                Scale tmpScale = model.getScaleWithName((String)cmbScale.getSelectedItem());

                //Key
                System.out.println("Key:" + cmbKey.getSelectedItem());
                Note tmpNote = model.getNoteWithName((String)cmbKey.getSelectedItem());

                //Fingering system
                System.out.println(cmbFingeringSystem.getSelectedItem());
                // TODO: change position

                if(tmpNote != null && tmpScale != null) {
                    System.out.println("setting key to: " + tmpNote.toString());
                    staffPanel.resetTune(tmpNote.getHeight(), tmpNote.getAccidental(), tmpScale.getId(), tmpNote);
                    //TODO send message to controller and update model
                }
                else System.err.println("Error selecting key or scale. Either one is nto found");




            }
        };

        btnStart.addActionListener(updateActionListener);
        cmbKey.addActionListener(updateActionListener);
        cmbScale.addActionListener(updateActionListener);
        cmbFingeringSystem.addActionListener(updateActionListener);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        // Add items to toolbar
        toolbar.add(new JLabel("Key"));
        toolbar.add(cmbKey);
        toolbar.add(new JLabel("Scale:"));
        toolbar.add(cmbScale);
        toolbar.add(new JLabel("System:"));
        toolbar.add(cmbFingeringSystem);
        toolbar.add(btnStart);

        add(toolbar);
    }
}
