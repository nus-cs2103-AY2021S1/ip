package main.java.manager;

import main.java.exceptions.InvalidCommandException;
import main.java.exceptions.InvalidNumberException;

import java.util.Scanner;

/**
 * Represents a parser that parses the user's input and
 * translate them into commands, to be passed to the converter.
 * If a command is invalid, the InvalidCommandException is thrown and caught.
 */
public class Parser {

    private final Ui converter = new Ui();

    /**
     * Handles the user input by parsing them into commands
     * and sending them to the converter.
     */
    public void handleUserInput() {

        Scanner sc = new Scanner(System.in);
        this.converter.getSavedTasks();
        String input = sc.nextLine();

        while (!input.equals("bye")) {

            try {

                if (input.equals("list")) {
                    this.converter.convertAction(Commands.LIST, 0, "");

                } else if (input.equals("delete all")) {
                    this.converter.convertAction(Commands.DELETE_ALL, 0, "");

                } else if (input.startsWith("find")) {
                    String toFind = input.substring("find".length()).trim();
                    this.converter.convertAction(Commands.FIND, 0, toFind);

                } else if (isNumberedCommand(input)) {
                    String[] words = input.split(" ");

                    if (words[0].equals("done")) {
                        int index = Integer.parseInt(words[1]) - 1;
                        this.converter.convertAction(Commands.DONE, index, "");

                    } else if (words[0].equals("delete")) {
                        int index = Integer.parseInt(words[1]) - 1;
                        this.converter.convertAction(Commands.DELETE, index, "");

                    } else {
                        throw new InvalidCommandException("Command is invalid. Try again?");
                    }

                } else {

                    if (input.startsWith("deadline") && input.contains("/by")) {
                        this.converter.passTask(this.converter.convertTask(Commands.DEADLINE, input));

                    } else if (input.startsWith("event") && input.contains("/at")) {
                        this.converter.passTask(this.converter.convertTask(Commands.EVENT, input));

                    } else if (input.startsWith("todo")) {
                        this.converter.passTask(this.converter.convertTask(Commands.TODO, input));

                    } else {
                        throw new InvalidCommandException("Not sure what you mean. " +
                                "Please ensure your command format is correct and try again.");
                    }

                }

                this.converter.storeTasks();

            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }

            if (sc.hasNextLine()) {
                input = sc.nextLine();
                this.converter.storeTasks();

            } else {
                break;
            }
        }

        sc.close();
    }

    private boolean isNumberedCommand(String input) throws InvalidNumberException {

        String[] words = input.split(" ");
        boolean checkBackNumber = words.length == 2 &&
                words[1].chars().allMatch(Character::isDigit);

        if (checkBackNumber) {
            int index = Integer.parseInt(words[1]) - 1;
            boolean isValidNumber = index < this.converter.getTotalTasks();

            if (!isValidNumber) {
                throw new InvalidNumberException("The number entered is invalid. " +
                        "You have " + this.converter.getTotalTasks() + " tasks in your list.");
            }
            return true;

        } else {
            return false;
        }
    }

}
