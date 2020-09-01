package duke;

import java.util.Scanner;

public class Ui {
    /**
     * getInput() is in charge of asking for input from user
     * and return the input as a string to be used for something else.
     * @param sc Scanner object
     * @return a string representing user input
     */
    public static String getInput(Scanner sc) {
        String user_input = sc.nextLine().trim(); // Read user input
        return user_input;
    }





}
