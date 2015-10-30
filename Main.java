/**
 * Main
 *
 * @author      Erik Buunk
 * @date        19/10/15
 * @version     %I%, %G%
 * @since       1.0
 */

public class Main {

    public static void main(String[] args) {
        // write your code here
        SRGModel model = new SRGModel();
        new GUI(model).setVisible(true);
    }
}
