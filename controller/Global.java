package controller;

import java.lang.System;

//Generic functions
public class Global {

    private boolean LOG ;

    public  Global ()
    {
        LOG = true;
    }


    public void log(String _msg) {
        if (LOG) {

            System.out.println(_msg);
        }
    }

}

