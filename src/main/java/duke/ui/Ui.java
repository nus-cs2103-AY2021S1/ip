package duke.ui;

import java.util.Scanner;

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

    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void showLoadingError() {
        System.out.println("Failed to load from file. Initiating new instace.");
    }
}
