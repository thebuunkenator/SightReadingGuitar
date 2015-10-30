

import abc.notation.Note;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import java.lang.System.*;


public class ToolbarPanel extends JPanel {

    private StaffPanel staffPanel;
    private JLabel lblKey;
    private JLabel lblSystem;
    private JLabel lblEmpty;

    private JComboBox cmbKey; //A.b.c etcd
    private  JComboBox cmbScale; //major, dorian,,,
    private  JComboBox cmbSystem;

    private JButton btnOK;

    public ToolbarPanel(SRGModel model, final StaffPanel staffPanel) {

        this.staffPanel = staffPanel;

        String[] keys = model.getKeysArray();
        String[] systems = model.getScaleSystemsArray();
        String[] scales = model.getScalesArray();

        JToolBar toolbar = new JToolBar();
        toolbar.setRollover(true);

        cmbKey = new JComboBox(keys);
        cmbKey.setSelectedIndex(0);

        cmbScale = new JComboBox(scales);
        cmbScale.setSelectedIndex(0);

        cmbSystem = new JComboBox(systems);
        cmbSystem.setSelectedIndex(0);

        btnOK =  new JButton("OK");

        ActionListener updateActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // staffPanel.setKey(new KeySignature(Note.E, KeySignature.LOCRIAN));
                staffPanel.setNote(new Note(Note.A));

                //System.println("Updating...");

            }
        };

        btnOK.addActionListener(updateActionListener);
        cmbKey.addActionListener(updateActionListener);
        cmbScale.addActionListener(updateActionListener);
        cmbSystem.addActionListener(updateActionListener);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(new JLabel("Key"));
        add(cmbKey);
        add(new JLabel("Scale:"));
        add(cmbScale);
        add(new JLabel("System:"));
        add(cmbSystem);

        add(btnOK);
    }
}
