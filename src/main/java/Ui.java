package main.java;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * User Interface class where interactions with user are handled
 */
public class Ui {
    private Scanner input;
    private final static String UNDERSCORE = "____________________________________________________________ \n";

    /**
     * Initializes the Ui object
     */
    public Ui() { }

    /**
     * Displays a welcome message
     *
     * @param list in which the tasks are stored
     */
    public void welcomeMessage(TaskList list) {
        System.out.println("____________________________________________________________ \n"
                + "Hello! Welcome to Duke, your personal task manager! \nWhat can I do for you?"
        );
        if (list.getList().size() > 0) {
            System.out.println("You have outstanding tasks. Type 'list' to view your current tasks.");
        }
        System.out.println("____________________________________________________________ \n");
    }

    /**
     * Displays a message to show that the task has been added
     */
    public void showAdded () {
        System.out.println("Okay! I've added it to the list." +
                " To view your current tasks, type 'list'");
    }

    /**
     * Displays the list of tasks stored.
     *
     * @param list in which tasks are stored
     */

    public void showList(ArrayList<Task> list) {
        System.out.println(UNDERSCORE);
        if(list.size() == 0){
            System.out.println("you do not have any tasks yet");
        } else {
            for (int i = 0; i < list.size(); i++) {
                int number = i + 1;
                System.out.println(" " + number + "." + list.get(i));
            }
        }
        System.out.println(UNDERSCORE);
    }

    /**
     * Displays a message to the user indicating that the command is invalid
     */
    public void showInvalidCommand() {
        System.out.println("I'm sorry I don't understand :(");
    }

    /**
     * Reads the next command
     * @return a String representing the command
     */
    public String readCommand(){
        if(input.hasNextLine()){
            return input.nextLine();
        } else {
            return null;
        }
    }

    /**
     * Displays a message indicating that the program has come to an end.
     */
    public void showEnd() {
        System.out.println(UNDERSCORE + " Bye. Hope to see you again soon!" + "\n" + UNDERSCORE);
    }

    /**
     * Closes the Scanner object
     */
    public void close(){
        input.close();
    }

    /**
     * Starts the Scanner object
     */
    public void start() {
        input = new Scanner(System.in);
    }

    /**
     * Displays a message indicating an error has occurred with the
     * specific error message
     *
     * @param err in which the exception has occurred
     */
    public void showError(Exception err) {
        System.out.println(err.getMessage());
    }
}
