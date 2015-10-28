
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SelectionPanel extends JPanel{
    private JLabel lblKey;
    private JLabel lblSystem;
    private JLabel lblEmpty;


    private  JComboBox cmbKey;
    private  JComboBox cmbSystem;

    private JButton btnOK;

    public SelectionPanel(SRGModel model) {

        String[] keys = model.getKeysArray();
        String[] systems = model.getScaleSystemsArray();

        cmbKey = new JComboBox(keys);
        cmbKey.setSelectedIndex(0);
        cmbKey.setPreferredSize(new Dimension(10,10));

        cmbSystem = new JComboBox(systems);
        cmbSystem.setSelectedIndex(0);

        btnOK =  new JButton("OK");

        this.setLayout(new GridLayout(2,3));

        add(new JLabel("Key:"));
        add(new JLabel("System:"));
        add(new JLabel(""));
        add(cmbKey);
        add(cmbSystem);

        add(btnOK);
    }
}
