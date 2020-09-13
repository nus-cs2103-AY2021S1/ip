package main.java.Duke;

import java.util.Scanner;

public class Ui {
    Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next input from the user using a Scanner.
     * @return User input in a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Greets the user when the program is executed.
     */
    public String greetingMessage() {
        return ("Hello! I'm Fandesma. What can I do for you?");
    }

    /**
     * Prompts the user for an input
     */
    public void prompt() {
        System.out.println("Input:");
    }

    /**
     * When the user input is invalid, prints the list of possible input for the user.
     */
    public String badInput() {
        return (
                "Please input:\n"
                + "1)list - to access the list\n"
                + "2)todo - to create a todo task\n"
                + "3)deadline - to create a deadline\n"
                + "4)event - to schedule an event\n"
                + "5)done - to mark tasks as done\n"
                + "6)delete - to delete tasks from the list\n"
                + "7)find - to find tasks from the list\n"
                + "\n-FOR EXPENSES-\n\n"
                + "1)expense - to add an expense\n"
                + "2)listExpense - to list all expenses\n"
                + "3)deleteExpense - to delete expense from the list"
        );
    }

    /**
     * Prints the exception message whenever an exception is thrown.
     *
     * @param str Exception message.
     */
    public String showException(String str){
        return (str);
    }

}
