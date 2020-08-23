package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * <code>Ui</code> deals with all the interactions with the user. A <code>Ui</code> object contains all the methods
 * required to print out messages to users, specifically, it specifies a format of print in the <code>print</code>
 * method that takes in a <code>String</code> object and prints the string in the desired format to users.
 */
public class Ui {
    private static final String lines = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n";

    public void print (String message) {
        System.out.println(lines + message + lines);
    }

    public void printErr (String message) {
        System.err.println(lines + message + lines);
    }

    public String printFormat (String message) {
        return lines + message + lines;
    }

    public void fileCreationError(String s) {
        printErr(s);
    }

    /**
     * Prints a message to inform user that a new file has been successfully created.
     */
    public void fileCreationSuccess() {
        String message = " Woof! I am Yuki your assigned Task Manager!\n"
                +" I have just created a new file to store all your tasks!\n"
                +" So... What is my first assignment? *Woof woof*\n";
        print(message);
    }

    /**
     * Prints a message to welcome users back.
     */
    public void welcome() {
        String message = " Hello! I'm Yuki *Woof*\n What can I do for you? *Woof woof*\n";
        print(message);
    }

    /**
     * Prints a farewell message.
     */
    public void goodBye(){
        String message = " Bye. Hope to see you again soon! *Woof woof*\n";
        print(message);
    }

    /**
     * Returns a print format specifying the total number of <code>Task</code> in the list.
     *
     * @param i the number of <code>Task</code> in a list
     * @return a print format specifying the total number of <code>Task</code> in the list
     */
    public String displayTotal(int i) {
        return " Now you have " + i + " tasks in the list. Keep going!!\n";
    }

    /**
     * Returns a print format specifying that the system has failed to access a file.
     *
     * @return a print format specifying that the system has failed to access a file
     */
    public String accessFileFailure() {
        return printFormat(" Unable to access file... *woof*\n");
    }

    /**
     * Returns a print format specifying that a particular <code>Task</code> that user is trying to access does not
     * exists in the file.
     *
     * @return a print format specifying that a particular <code>Task</code> that user is trying to access does not
     * exists in the file
     */
    public String noSuchTask() {
        return printFormat(" *Woof!* This task does not exist!\n");
    }

    /**
     * Returns a print format specifying that a particular <code>Task</code> command is invalid.
     *
     * @return a print format specifying that a particular <code>Task</code> command is invalid
     */
    public String wrongDeleteInput() {
        return printFormat(" *Woof!* Please enter an integer value! I can't really read...\n");
    }

    /**
     * Prints a message to inform user that a <code>Task</code> has been deleted successfully.
     *
     * @param task <code>Task</code> object in string format
     * @param i total number of <code>Task</code> in the list
     */
    public void deleteSuccess(String task, int i) {
        print(" *WOOF* I have removed:\n   " + task + "\n" + displayTotal(i));
    }

    /**
     * Prints a message to inform user that a <code>Task</code> has been added successfully.
     *
     * @param task <code>Task</code> object in string format
     * @param i total number of <code>Task</code> in the list
     */
    public void addSuccess(String task, int i) {
        print(" *WOOF* I have added:\n   " + task + "\n" + displayTotal(i));
    }

    /**
     * Prints a message to inform user that they have no <code>Task</code>.
     */
    public void noTask() {
        print(" You have no task to complete! *WOOF*\n");
    }

    /**
     * Prints the header for listing all <code>Task</code>
     */
    public void listHeader() {
        System.out.print(lines);
        System.out.println(" Here are the tasks in your list *Woof*:");
    }

    /**
     * Prints the given <code>Task</code> out in specified format.
     *
     * @param ind Index of <code>Task</code> in the list
     * @param task <Task>Task</Task> object in string format
     */
    public void listBody(int ind, String task) {
        System.out.println("   " + ind + "." + task);
    }

    /**
     * Prints out the divider
     */
    public void line() {
        System.out.println(lines);
    }

    /**
     * Prints a message to inform users that they have no <code>Task</code> on the specified date.
     */
    public void noSameDate() {
        print(" You have no task on this day! Have a good break! *Woof*\n");
    }

    /**
     * Prints the header before listing the list of <code>Task</code> on a specified date.
     *
     * @param date the specified date
     */
    public void sameDateHeader(LocalDate date) {
        System.out.print(lines);
        System.out.println(" Here is the list of ongoing tasks on "
                + DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(date) + ":");
    }

    /**
     * Returns a print format informing user to input date in the correct format.
     *
     * @return a print format informing user to input date in the correct format
     */
    public String inputCorrectCheckDateFormat() {
       return printFormat(" Please enter date in YYYY/MM/DD format! *Woof woof*\n");
    }

    /**
     * Prints a message to inform user that the <code>Task</code> is marked as done.
     *
     * @param description Description of <code>Task</code> marked as done
     */
    public void markDoneSuccess(String description) {
        print(" Good Job!!! You cleared this task:\n" + description);
    }

    /**
     * Prints a message to inform user that the <code>Task</code> has already been marked as done.
     */
    public void markDoneRepeat() {
        print(" You have already completed this task! *Woof woof*\n");
    }

    public String searchFail() {
        return printFormat(" Please enter a keyword that you wish to search for...\n"
                + " Or you can just enter *list* to see all your task! *Woof woof!*\n");
    }

    public void noRelevantTask() {
        print(" I can't seem to find any task with this keyword! *woof*\n");
    }

    public void relevantTaskHeader() {
        System.out.print(lines);
        System.out.println(" Here is the list of matching tasks in your storage: ");
    }

}
