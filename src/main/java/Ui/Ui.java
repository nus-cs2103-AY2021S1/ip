package ui;

import parser.Parser;

import java.util.Scanner;

public class Ui {

    private boolean isExit = false;

    protected Parser parser;

    public Ui(Parser parser) {
        this.parser = parser;
    }

    public boolean isExit() {
        return this.isExit;
    }

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

    protected void showLine() {
        System.out.println("***********************************************************************");
    }

    protected void showBye() {
        System.out.println("Bye! Let's talk again soon!");
    }

    public void readInput() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                this.isExit = true;
                //showLine();
                showBye();
                //showLine();
                sc.close();
                break;
            } else {
                //showLine();
                parser.manageTask(command);
                //showLine();
            }
        }
    }

}
