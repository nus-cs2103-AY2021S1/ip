package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {
    public Scanner sc;

    public  Ui(){
        this.sc = new Scanner(System.in);
    }

    public static String showWelcome(){
        return "What's new scooby doo?\n" + "How can I help you today?";
    }



}