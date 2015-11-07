package controller;

import abc.notation.Note;
import model.SRGModel;
import model.Scale;
import views.FretboardPanel;
import views.GUI;
import views.StaffPanel;
import views.ToolbarPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller
 *
 * @author erik
 * @version %I%, %G%
 * @since 1.0
 * date:      31/10/15
 */
//package controller;

public class Controller {

    //Views and models
    private final GUI mainGui = new GUI();
    private SRGModel model = new SRGModel();
    private ToolbarPanel toolbar;
    private StaffPanel staff;
    private FretboardPanel fretboard;


    public Controller() {

        toolbar = mainGui.getToolbarPanel();
        staff = mainGui.getStaffPanel();
        fretboard = mainGui.getFretboardPanel();

        /* Populate the comboboxes*/
        toolbar.populateKeysCombo(model.getKeysArray());
        toolbar.populateScalesCombo(model.getScalesArray());
        toolbar.populateFingeringSystemCombobox(model.getFingeringSystemsArray());

        /* Initialize fretboard quiznotes */
        fretboard.setQuizNotes(model.getQuizNotes());

        /* Actions */
        ActionListener startExcercise = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Controller recieved action: Starting Excercise");
            }
        };

        ActionListener changeExcercise = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Controller recieved action: Change in comboboxes");

                //Key
                String key = (String) toolbar.getCmbKey().getSelectedItem();
                key = key.trim();
                System.out.println("Key:" + key);
                Note tmpNote = model.getNoteWithName(key);


                //Scale
                String scale = (String) toolbar.getCmbScale().getSelectedItem();
                System.out.println("Scale: " + scale);
                Scale tmpScale = model.getScaleWithName(scale);


                //Fingering system
                String fingering = (String) toolbar.getCmbFingeringSystem().getSelectedItem();
                System.out.println("Fingering: " + fingering);
                //TODO: model.setCurrentFingeringSystem(tmpFS);

                if (tmpNote != null && tmpScale != null) {
                    //update
                    staff.resetTune(key, scale);

                    //update model
                    model.setCurrentKey(tmpNote);
                    model.setCurrentScale(tmpScale);

                    // get quiznotes and draw them on the fretboard
                    model.generateQuizNotes();
                    fretboard.setQuizNotes(model.getQuizNotes());

                } else System.err.println("Error selecting key or scale. Either one is note found");
            }
        };


        /* connect actions to the items in the toolbar*/
        toolbar.addButtonActionListener(startExcercise);
        toolbar.addComboActionListener(changeExcercise);



    }

}

