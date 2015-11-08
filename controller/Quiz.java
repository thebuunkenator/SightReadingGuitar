package controller;

import model.QuizNote;
import views.FretboardPanel;
import views.StaffPanel;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;

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
    private int delay; //in seconds
    private int numQuestions;
    private boolean isRunning;
    private ArrayList<QuizNote> quizNotes = new ArrayList<>();
    private StaffPanel staf;
    private FretboardPanel fretboard;

    public void setFretboard(FretboardPanel fretboard) {
        this.fretboard = fretboard;
    }

    public void setStaf(StaffPanel staf) {
        this.staf = staf;
    }

    public Quiz(ArrayList<QuizNote> qns) {
        timer = new Timer();
        delay = 1;
        numQuestions = 10;
        quizNotes = (ArrayList<QuizNote>)qns.clone();

        timer.schedule(new showQuizNote(),
                delay*1000,   //initial delay
                delay*1000);  //subsequent rate

        isRunning = true;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void cancel() {
        timer.cancel();
        isRunning =  false;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getNumQuestions() {
        return numQuestions;
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
                System.out.println("Question" + currentQuestion);
                randomNote = selectRandomNote(); //returns quiznote do something with it
                staf.singleQuizNote(randomNote);
                fretboard.clearQuizNotes();

                //clear


            } else {
                System.out.println("Answer");
                //show on fretboard
                currentQuestion++;
                fretboard.setSingleQuizNotes(randomNote);
            }
            question = !question;

            if(currentQuestion > numQuestions) {
                this.cancel();
                System.out.println("Cancelling quiz.");
                isRunning =  false;
                //reset de Quiznote in Model
            }

        }

        private QuizNote selectRandomNote(){
            Random randomGenerator = new Random();

            int count = quizNotes.size();
            int randomNote = randomGenerator.nextInt(count);
            System.out.println(randomNote);

            return quizNotes.get(randomNote);

        }

    }



}
