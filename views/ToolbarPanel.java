package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.*;
//import java.lang.System;


public class ToolbarPanel extends JPanel {

    private JComboBox cmbKey; //A.b.c etcd
    private JComboBox cmbScale; //major, dorian,,,
    private JComboBox cmbFingeringSystem; // C A G E D

    public JButton getBtnStart() {
        return btnStart;
    }

    private JButton btnStart;

    public ToolbarPanel() {

        // Set up Combobox Keys
        cmbKey = new JComboBox();
        cmbScale = new JComboBox();
        cmbFingeringSystem = new JComboBox();
        btnStart =  new JButton("Start");
        btnStart.setEnabled(false);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        // Add items to toolbar
        add(new JLabel("Key"));
        add(cmbKey);
        add(new JLabel("Scale:"));
        add(cmbScale);
        add(new JLabel("System:"));
        add(cmbFingeringSystem);
        add(btnStart);
    }

    public void populateKeysCombo(String[] list ) {
        //cmbKey
        DefaultComboBoxModel keysModel = new DefaultComboBoxModel();

        for (String currentKey : list) {
//            System.out.println(currentKey);
            keysModel.addElement(currentKey);
        }
        cmbKey.setModel(keysModel);
        cmbKey.setSelectedIndex(0);
    }

    public void populateScalesCombo(String[] list ) {
        //cmbKey
        DefaultComboBoxModel scalesModel = new DefaultComboBoxModel();

        for (String currentKey : list) {
//            System.out.println(currentKey);
            scalesModel.addElement(currentKey);
        }
        cmbScale.setModel(scalesModel);
        cmbScale.setSelectedIndex(0);
    }

    public void populateFingeringSystemCombobox(String[] list ) {
        //cmbKey
        DefaultComboBoxModel fingering = new DefaultComboBoxModel();

        for (String currentKey : list) {
//            System.out.println(currentKey);
            fingering.addElement(currentKey);
        }
        cmbFingeringSystem.setModel(fingering);
        cmbFingeringSystem.setSelectedIndex(0);
    }

    /*
     * Set the action listeners
     */
    public void addButtonActionListener(ActionListener listener) {
        btnStart.addActionListener(listener);
    }

    public void addComboActionListener(ActionListener listener) {
        cmbFingeringSystem.addActionListener(listener);
        cmbScale.addActionListener(listener);
        cmbKey.addActionListener(listener);
    }

    // Getters
    public JComboBox getCmbKey() {
        return cmbKey;
    }

    public JComboBox getCmbScale() {
        return cmbScale;
    }

    public JComboBox getCmbFingeringSystem() {
        return cmbFingeringSystem;
    }
}
