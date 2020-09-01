package ui;

import java.util.Scanner;

import parser.Parser;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /** Parser for parsing user's inputs */
    private Parser parser;

    /** Boolean to determine if Cait should stop running */
    private boolean isExit = false;

    /**
     * Constructs a new Ui object.
     * @param parser the parser that deals with user input
     */
    public Ui(Parser parser) {
        this.parser = parser;
    }

    /**
     * Determines whether or not Cait should stop running.
     * @return true if Cait should stop running
     */
    public boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Shows Cait's greeting when the user runs the bot.
     */
    public void showGreeting() {
        String logo = "_________     _____  .______________\n"
                + "\\_   ___ \\   /  _  \\ |   \\__    ___/\n"
                + "/    \\  \\/  /  /_\\  \\|   | |    |   \n"
                + "\\     \\____/    |    \\   | |    |   \n"
                + " \\______  /\\____|__  /___| |____|   \n"
                + "        \\/         \\/               \n";
        System.out.println("Hi! I'm\n" + logo);
        System.out.println("What can I help you with?");
    }

    /**
     * Shows a line to separate the user and Cait's messages.
     */
    public void showLine() {
        System.out.println("***********************************************************************");
    }

    /**
     * Shows Cait's goodbye message when the user exits the bot.
     */
    protected void showBye() {
        System.out.println("Bye! Let's talk again soon!");
    }

    /**
     * Reads the user's inputs and then passes them to parser for parsing.
     */
    public void readInput() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            showLine();
            if (command.equals("bye") || command.equals("Bye")) {
                this.isExit = true;
                showBye();
                sc.close();
                break;
            } else {
                String reply = parser.manageTask(command);
                System.out.println(reply);
            }
            showLine();
        }
    }

}
