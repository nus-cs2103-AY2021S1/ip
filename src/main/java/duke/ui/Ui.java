package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.model.task.Task;

public class Ui {
    private final String WELCOME = "Hello. I am Claude! What may I do for you today?";
    private final String GOODBYE = "Goodbye! Hope to see you again soon!";
    private final String LINE = "______________________________";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        if (sc.hasNext()){
            return sc.nextLine();
        } else {
            return "";
        }
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        System.out.println(WELCOME);
    }

    public void showGoodbye() {
        System.out.println(GOODBYE);
    }

    public void showDetails(String s) {
        System.out.println(s);
    }

    public void showTaskList(ArrayList<Task> taskList){
        String s = "";
        for (int i= 0; i < taskList.size(); i++) {
            s = s = s + (i + 1) + ". " + taskList.get(i) + "\n";
        }
        showDetails(s);
    }

    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void showLoadingError() {
        System.out.println("Failed to load from file. Initiating new instace.");
    }
}
