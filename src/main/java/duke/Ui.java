package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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

    public void fileCreationSuccess() {
        String message = " Woof! I am Yuki your assigned Task Manager!\n"
                +" I have just created a new file to store all your tasks!\n"
                +" So... What is my first assignment? *Woof woof*\n";
        print(message);
    }

    public void welcome() {
        String message = " Hello! I'm Yuki *Woof*\n What can I do for you? *Woof woof*\n";
        print(message);
    }

    public void goodBye(){
        String message = " Bye. Hope to see you again soon! *Woof woof*\n";
        print(message);
    }

    public String displayTotal(int i) {
        return " Now you have " + i + " tasks in the list. Keep going!!\n";
    }

    public String accessFileFailure() {
        return printFormat(" Unable to access file... *woof*\n");
    }

    public String noSuchTask() {
        return printFormat(" *Woof!* This task does not exist!\n");
    }

    public String wrongDeleteInput() {
        return printFormat(" *Woof!* Please enter an integer value! I can't really read...\n");
    }

    public void deleteSuccess(String t, int i) {
        print(" *WOOF* I have removed:\n   " + t + "\n" + displayTotal(i));
    }

    public void addSuccess(String t, int i) {
        print(" *WOOF* I have added:\n   " + t + "\n" + displayTotal(i));
    }

    public void noTask() {
        print(" You have no task to complete! *WOOF*\n");
    }

    public void listHeader() {
        System.out.print(lines);
        System.out.println(" Here are the tasks in your list *Woof*:");
    }

    public void listBody(int ind, String task) {
        System.out.println("   " + ind + "." + task);
    }

    public void line() {
        System.out.println(lines);
    }

    public void noSameDate() {
        print(" You have no event on this day! Have a good break! *Woof*\n");
    }

    public void sameDateHeader(LocalDate date) {
        System.out.print(lines);
        System.out.println(" Here is the list of ongoing events on "
                + DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(date) + ":");
    }

    public String inputCorrectCheckDateFormat() {
       return printFormat(" Please enter date in YYYY/MM/DD format! *Woof woof*\n");
    }

    public void markDoneSuccess(String description) {
        print(" Good Job!!! You cleared this task:\n" + description);
    }

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
        System.out.println(" Here is the list of matching task in your list: ");
    }
}
