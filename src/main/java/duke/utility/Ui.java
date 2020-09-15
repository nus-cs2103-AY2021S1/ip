package duke.utility;

import java.util.Scanner;

public class Ui {

    /** Scanner object used to receive inputs from the user **/
    private Scanner sc;
    /** Line separator to beautify duke bot **/
    private String line = "____________________________\n"
                        + "____________________________\n";

    /** Logo for duke bot **/
    private String logo = "****** ****** ****** ******\n"
                        + "   *   *      *      *\n"
                        + "   *   ****** ****** ******\n"
                        + "*  *   *      *      *\n"
                        + "***    ****** *      *\n";

    /**
     *Class constructor
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints loading error message
     */
    public void showLoadingError() {
        System.out.println("******LOADING ERROR******");
    }

    /**
     * Prints saving error message
     */
    public void showSavingError() {
        System.out.println("******SAVING ERROR******");
    }

    /**
     * Prints line separator
     */
    public void printLine() {
        System.out.println(line);
    }

    /**
     * Prints Logo
     */
    public void printLogo() {
        System.out.println(logo);
    }

    /**
     * Prints the start of the program
     */
    public void printStarting() {
        System.out.println("My name is\n");
        printLogo();
        System.out.println("What do you want?");
        printLine();
    }


    /**
     * Reads the in all the inputs
     * Splits the inputs into duke.command, duke.task description and if there is Time/Date for the duke.task
     *
     * @return An array of strings containing the duke.command, duke.task description and if there is Time/Date for the duke.task
     */

    public String[] readCommand() {
        return sc.nextLine().trim().split(" ", 2);
    }
}
