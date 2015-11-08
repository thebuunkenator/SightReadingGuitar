package controller;

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


    public Quiz() {
        timer = new Timer();
        delay = 1;
        numQuestions = 10;

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

        int currentQuestion = 1;

        public void run() {

            if (question) {
                System.out.println("Question" + currentQuestion);
                //

            } else {
                System.out.println("Answer");
                currentQuestion++;
            }
            question = !question;

            if(currentQuestion > numQuestions) {
                this.cancel();
                System.out.println("Cancelling quiz.");
                isRunning =  false;
            }

        }

    }

}
