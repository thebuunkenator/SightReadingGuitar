
import abc.notation.*;
import abc.notation.Note;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import java.lang.System;
import java.util.ArrayList;

public class SelectionPanel extends JPanel{

    private StaffPanel staffPanel;
    private JLabel lblKey;
    private JLabel lblSystem;
    private JLabel lblEmpty;

    private JComboBox cmbKey; //A.b.c etcd
    private  JComboBox cmbScale; //major, dorian,,,
    private  JComboBox cmbSystem;

    private JButton btnOK;

    public SelectionPanel(SRGModel model, final StaffPanel staffPanel) {

        this.staffPanel = staffPanel;

        String[] keys = model.getKeysArray();
        String[] systems = model.getScaleSystemsArray();
        String[] scales = model.getScalesArray();

        cmbKey = new JComboBox(keys);
        cmbKey.setSelectedIndex(0);
        cmbKey.setPreferredSize(new Dimension(10,10));

        cmbScale = new JComboBox(scales);
        cmbScale.setSelectedIndex(0);
        cmbScale.setPreferredSize(new Dimension(10,10));

        cmbSystem = new JComboBox(systems);
        cmbSystem.setSelectedIndex(0);

        btnOK =  new JButton("OK");
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                staffPanel.setKey(new KeySignature(Note.E, KeySignature.LOCRIAN));
                staffPanel.setNote(new Note(Note.A));


//                System.out.println("changing note");
            }
        });

        this.setLayout(new GridLayout(2,4));

        add(new JLabel("Key"));
        add(new JLabel("Scale:"));
        add(new JLabel("System:"));
        add(new JLabel(""));


        add(cmbKey);
        add(cmbScale);
        add(cmbSystem);

        add(btnOK);
    }
}
