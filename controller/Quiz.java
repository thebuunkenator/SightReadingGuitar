package controller;

import model.QuizNote;
import views.FretboardPanel;
import views.StaffPanel;
import views.ToolbarPanel;

import javax.sound.midi.*;
import java.util.*;

/**
 * Quiz
 *
 * @author erik
 * @version %I%, %G%
 * @since 1.0
 * date:      08/11/15
 */
public class Quiz {

    private Timer timer;
    private int delay =1000; //in milli seconds
    private int numQuestions;
    private boolean isRunning;
    private ArrayList<QuizNote> quizNotes = new ArrayList<>();
    private StaffPanel staf;
    private FretboardPanel fretboard;

    private ToolbarPanel toolbar;

    public void setFretboard(FretboardPanel fretboard) {
        this.fretboard = fretboard;
    }

    public void setStaf(StaffPanel staf) {
        this.staf = staf;
    }

    public void setToolbar(ToolbarPanel toolbar) {
        this.toolbar = toolbar;
    }

    public Quiz(ArrayList<QuizNote> qns, int delay, int numQuestions) {
        this.delay = delay;
        this. numQuestions = numQuestions;

        timer = new Timer();

        quizNotes = (ArrayList<QuizNote>)qns.clone();

        timer.schedule(new showQuizNote(), 1000, this.delay);  //subsequent rate
        isRunning = true;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void cancel() {
        timer.cancel();
        isRunning =  false;
        toolbar.getBtnStart().setText("Start");
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setNumQuestions(int numQuestions) {
        this.numQuestions = numQuestions;
    }


    // quiz subclass
    class showQuizNote extends TimerTask {
        boolean question = true;
        QuizNote randomNote;

        int currentQuestion = 1;

        public void run() {

            if (question) {

                toolbar.getBtnStart().setText("Stop");
                randomNote = selectRandomNote(); //returns quiznote do something with it
                staf.singleQuizNote(randomNote);
                fretboard.clearQuizNotes();

                //clear


            } else {
                currentQuestion++;
                fretboard.setSingleQuizNotes(randomNote);
                playNote(randomNote.getMidiNumber());

            }


            if((currentQuestion > numQuestions + 1) && question) {

                this.cancel();

                isRunning =  false;
                //reset de Quiznote in Model
                //Button moet weer ingesteld worden
                fretboard.setQuizNotes(quizNotes);
                toolbar.getBtnStart().setText("Start");
            }

            question = !question;

        }

        private QuizNote selectRandomNote(){
            Random randomGenerator = new Random();

            int count = quizNotes.size();
            int randomNote = randomGenerator.nextInt(count);
            System.out.println(randomNote);

            return quizNotes.get(randomNote);

        }

    }

    private void playNote(int midiNote){
        try {
            Synthesizer syn = null;

            syn = MidiSystem.getSynthesizer();
            syn.open();
            final MidiChannel[] mc = syn.getChannels();

            Instrument[] instr = syn.getDefaultSoundbank().getInstruments();

            //TODO: instrument change werkt nog niet
            syn.loadInstrument(instr[24]);
//            System.out.println(instr[24].getName());

            for (int i = 0; i < mc.length; i++) {
                mc[i].allNotesOff();
            }
            mc[5].allNotesOff();
            mc[5].noteOn(midiNote,92);
        }
        catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }



}
