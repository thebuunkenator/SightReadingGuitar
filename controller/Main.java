package controller;

import controller.Controller;

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


        // using the java 8 lambda expression:
//        SwingUtilities.invokeLater(() -> new Controller());

        //using Method reference:
        SwingUtilities.invokeLater(Controller::new);
    }
}
