

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

    private JButton btnOK;

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

        btnOK =  new JButton("OK");

        ActionListener updateActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                staffPanel.setKey(new KeySignature(Note.E, KeySignature.LOCRIAN));
//                staffPanel.setNote(new Note(Note.A));

//                staffPanel.setKey(new KeyS)

                System.out.println("Updating...");
                System.out.println(cmbKey.getSelectedItem());
                Note tmpNote = model.getNoteWithName((String)cmbKey.getSelectedItem());

                if(tmpNote != null) {
                    System.out.println("setting key to: " + tmpNote.toString());
                    //deze werkt nog niet
                    //staffPanel.setKey(new KeySignature(tmpNote.getHeight(), KeySignature.MAJOR ));
                    //deze werkt al wel
                    //staffPanel.setNote(tmpNote);
                    staffPanel.resetTune(tmpNote.getHeight(), tmpNote.getAccidental(), KeySignature.MAJOR, tmpNote);
                }
                System.out.println(cmbScale.getSelectedItem());
                System.out.println(cmbFingeringSystem.getSelectedItem());


            }
        };

        btnOK.addActionListener(updateActionListener);
        cmbKey.addActionListener(updateActionListener);
        cmbScale.addActionListener(updateActionListener);
        cmbFingeringSystem.addActionListener(updateActionListener);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(new JLabel("Key"));
        add(cmbKey);
        add(new JLabel("Scale:"));
        add(cmbScale);
        add(new JLabel("Fingering System:"));
        add(cmbFingeringSystem);

        add(btnOK);
    }
}
