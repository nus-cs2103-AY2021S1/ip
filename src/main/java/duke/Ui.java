package duke;

import java.util.Scanner;

public class Ui {
    private static final String BORDER = "_____________________________________________________________";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the given String with borders above and below it.
     *
     * @param s The given String
     */
    public void say(String s) {
        System.out.println(BORDER);
        System.out.println(s);
        System.out.println(BORDER);
    }

    /**
     * Prints a loading error.
     */
    public void showLoadingError() {
        say("Unable to load tasks.");
    }

    /**
     * Prints the welcome text.
     */
    public void showWelcome() {
        String logo = "█████████████████████████████"
                + "████████████████████████████████\n"
                + "█░░░░░░░░░░░░░░███░░░░░░░░░░░░░"
                + "░░░░░░░░░░░░█░░░░░░░░░░░░░░███\n"
                + "█░░▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀▄"
                + "▀▄▀▄▀▄▀▄▀▄░░█░░▄▀▄▀▄▀▄▀▄▀░░███\n"
                + "█░░▄▀░░░░░░▄▀░░███░░▄▀░░░░░░░░░"
                + "░░░░░░░░▄▀░░█░░▄▀░░░░░░▄▀░░███\n"
                + "█░░▄▀░░██░░▄▀░░███░░▄▀░░███████"
                + "██████░░▄▀░░█░░▄▀░░██░░▄▀░░███\n"
                + "█░░▄▀░░░░░░▄▀░░░░█░░▄▀░░███████"
                + "██████░░▄▀░░█░░▄▀░░░░░░▄▀░░░░█\n"
                + "█░░▄▀▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░█░░█░██"
                + "█░█░░█░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░█\n"
                + "█░░▄▀░░░░░░░░▄▀░░█░░▄▀░░█████░░"
                + "░█████░░▄▀░░█░░▄▀░░░░░░░░▄▀░░█\n"
                + "█░░▄▀░░████░░▄▀░░█░░▄▀░░███████"
                + "██████░░▄▀░░█░░▄▀░░████░░▄▀░░█\n"
                + "█░░▄▀░░░░░░░░▄▀░░█░░▄▀░░░░░░░░░"
                + "░░░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░█\n"
                + "█░░▄▀▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄"
                + "▀▄▀▄▀▄▀▄▀▄░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░█\n"
                + "█░░░░░░░░░░░░░░░░█░░░░░░░░░░░░░"
                + "░░░░░░░░░░░░█░░░░░░░░░░░░░░░░█\n"
                + "███████████████████████████████"
                + "██████████████████████████████";
        String welcome = "Hello I am\n" + logo + "\nPlease say something.";
        say(welcome);
    }

    /**
     * Returns the user input as a String.
     *
     * @return User input.
     */
    public String readCommand() {
        String input = sc.nextLine();
        return input;
    }

    /**
     * Prints Goodbye and closes the scanner.
     */
    public void end() {
        say("Goodbye!");
        sc.close();
    }

}
