package controller;

import abc.notation.Note;
import model.FingeringSystem;
import model.SRGModel;
import model.Scale;
import views.FretboardPanel;
import views.GUI;
import views.StaffPanel;
import views.ToolbarPanel;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private Quiz quiz;

    public Controller() {

        toolbar = mainGui.getToolbarPanel();
        staff = mainGui.getStaffPanel();
        fretboard = mainGui.getFretboardPanel();

        /* Populate the comboboxes */
        toolbar.populateKeysCombo(model.getKeysArray());
        toolbar.populateScalesCombo(model.getScalesArray());
        toolbar.populateFingeringSystemCombobox(model.getFingeringSystemsArray());

        /* Initialize fretboard quiznotes */
        fretboard.setQuizNotes(model.getQuizNotes());


        /* Actions */
        ActionListener startExcercise = e -> {
            //System.out.println("Controller recieved action: Starting Excercise");

            if (toolbar.getBtnStart().getText().equals("Start")) {


                if (quiz != null && quiz.isRunning()) {
                    System.out.println("You have to wait");
                } else {

                    int delay = (int) toolbar.getIntervalSpinner().getValue();
                    int numQuestions = (int) toolbar.getQuestionsSpinner().getValue();

                    quiz = new Quiz(model.getQuizNotes(), delay, numQuestions);
                    quiz.setFretboard(fretboard);
                    quiz.setStaf(staff);
                    quiz.setToolbar(toolbar);

                }
                toolbar.getBtnStart().setText("Stop");
            } else {
                quiz.cancel();
                fretboard.setQuizNotes(model.getQuizNotes());
            }
        };

        ActionListener changeExcercise = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Controller recieved action: Change in comboboxes");

                //Key
                String key = (String) toolbar.getCmbKey().getSelectedItem();
                key = key.trim();
//                System.out.println("Key:" + key);
                Note tmpNote = model.getNoteWithName(key);
                fretboard.setKey(key.toLowerCase());

                //Scale
                String scale = (String) toolbar.getCmbScale().getSelectedItem();
//                System.out.println("Scale: " + scale);
                Scale tmpScale = model.getScaleWithName(scale);


                //Fingering system
                String fingering = (String) toolbar.getCmbFingeringSystem().getSelectedItem();
//                System.out.println("Fingering: " + fingering);
                //zoek fingering
                FingeringSystem fSystem = model.findFingeringSystemByName(fingering);
                if(fSystem!=null) {
                    model.setCurrentFingeringSystem(fSystem);
                }
                else {
                    fSystem = model.getCurrentFingeringSystem();
                }

                // op basis van fingering, de key note zoeken op de juiste snaar
                int keyString = fSystem.getKeyString();
                int fret = model.findNoteOnString(key, keyString);
                // op basis daarvan linker en rechte box bepalen
                int start = fret + fSystem.getStartBox();
                int end = fret + fSystem.getEndBox();

                if(start<0) {
                    start += 12;
                    end += 12;
                }
                // dan deze instellen
                model.setPosition(start, end);
                fretboard.setPosition(start, end);

                if (tmpNote != null && tmpScale != null) {
                    //update
                    staff.resetTune(key, scale , key, "");

                    //update model
                    model.setCurrentKey(tmpNote);
                    model.setCurrentScale(tmpScale);

                    // get quiznotes and draw them on the fretboard
                    model.generateQuizNotes(true);
                    fretboard.setQuizNotes(model.getQuizNotes());

                } else {
                    System.err.println("Error selecting key or scale. Either one is note found");
                }
                toolbar.getBtnStart().setEnabled(true);
            }
        };
//
//        final ChangeListener quizSettings =  e -> {
//
//            int delay = (int)toolbar.getIntervalSpinner().getValue();
//            int numQuestions = (int)toolbar.getQuestionsSpinner().getValue();
//
//            if(quiz != null )
//            {
//                quiz.setDelay(delay);
//                quiz.setNumQuestions(numQuestions);
//            }
//        };

        /* connect actions to the items in the toolbar*/
        toolbar.addButtonActionListener(startExcercise);
        toolbar.addComboActionListener(changeExcercise);

//        toolbar.addQuizChangeListener(quizSettings);
    }
}

