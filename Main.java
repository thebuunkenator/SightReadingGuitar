import javax.swing.*;

/**
 * Main
 *
 * @author      Erik Buunk
 * date        19/10/15
 * @version     %I%, %G%
 * @since       1.0
 */

public class Main {

    public static void main(String[] args) {


        // start application in separate thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // initialize model
                SRGModel model = new SRGModel();
                new GUI(model).setVisible(true);
            }
        });

    }
}
