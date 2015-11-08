package model;

import java.util.ArrayList;

/**
 * QuizNotes
 *
 * @author erik
 * @version %I%, %G%
 * @since 1.0
 * date:      08/11/15
 */
public class QuizNotes  {

    private ArrayList<QuizNote> quizNotes =  new ArrayList<>();
    private static int id = 1;

    public QuizNotes() {
    }

    public void add(QuizNote quizNote) {
        quizNotes.add(quizNote);
        id ++;
    }

    public void clear()
    {
        quizNotes.clear();
        id = 1;
    }

    public boolean isEmpty() {
        return quizNotes.isEmpty();
    }


//    public getQuizNote(int id) {
//        for(QuizNote qn : quizNotes) {
//            if (id == qn.getID())
//        }
//    }

}
