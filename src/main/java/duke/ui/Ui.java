package duke.ui;

import duke.*;
import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.Scanner;

public class Ui {
    public void showLine() {
        System.out.println("----------------------------------------------------------------------------------------");
    }



    public void showWelcome() {
        System.out.println("\tHello!\n\tI am Baymax, your personal idle time companion." +
                "\n\tHow may I help you?");
    }

    public void showFarewell() {
        System.out.println("\tList saved!\n\tIt was my pleasure assisting you.\n\tSee you next Time!");
    }

    public void showList() {
        System.out.println("\tHere are the tasks in your list:");
    }

    public void showDone(Task task) {
        System.out.println("\tYou have finished " + task + "!\n\tMove on to the next one:");
    }

    public void showAdd(Task task) {
        System.out.println("\tYou have added " + task + "!\n\tNow you have these tasks:");
    }

    public void showDelete(Task task) {
        System.out.println("\t" + task + " deleted. \n\tCheck out other tasks:");
    }

    public void showDateFilterList() {
        System.out.println("\tYou have these tasks on this date:");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showLoadingError() {
        System.out.println("\tError loading file.\n\tNew Duke Todo list created!");
    }
}