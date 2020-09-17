package manager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import exceptions.InvalidCommandException;
import exceptions.InvalidNumberException;

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
     * @param input obtained
     * @return output as a string from converter
     */
    public String handleUserInput(String input) {

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        PrintStream old = System.out;
        System.setOut(printStream);

        if (input.equals("bye")) {
            return null;
        }

        try {
            if (input.equals("list")) {
                this.parseList();
            } else if (input.equals("help")) {
                this.parseHelp();
            } else if (input.equals("stats")) {
                this.parseStatistics();
            } else if (input.equals("delete all")) {
                this.parseDeleteAll();
            } else if (input.startsWith("find")) {
                this.parseFind(input);
            } else if (isValidNumberCommand(input)) {
                this.parseNumberCommand(input);
            } else {
                this.parseTaskCommand(input);
            }
            this.converter.storeTasks();

        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        }

        System.out.flush();
        System.setOut(old);
        return output.toString();
    }

    /**
     * Handles user input obtained from a Scanner object.
     */
    public void handleUserInput() {

        Scanner sc = new Scanner(System.in);
        parseSavedTasks();
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println(handleUserInput(input));
            if (sc.hasNextLine()) {
                input = sc.nextLine();
                this.converter.storeTasks();
            } else {
                break;
            }
        }
        sc.close();
    }

    private void parseList() {
        this.converter.convertAction(Commands.LIST, 0, "");
    }

    private void parseHelp() {
        this.converter.convertAction(Commands.HELP, 0, "");
    }

    private void parseStatistics() {
        this.converter.convertAction(Commands.STATS, 0, "");
    }

    private void parseDeleteAll() {
        this.converter.convertAction(Commands.DELETE_ALL, 0, "");
    }

    private void parseFind(String input) {
        String toFind = input.substring("find".length()).trim();
        this.converter.convertAction(Commands.FIND, 0, toFind);
    }

    private void parseNumberCommand(String input) throws InvalidCommandException {

        String[] words = input.split(" ");

        if (words[0].equals("done")) {
            int index = Integer.parseInt(words[1]) - 1;
            this.converter.convertAction(Commands.DONE, index, "");

        } else if (words[0].equals("delete")) {
            int index = Integer.parseInt(words[1]) - 1;
            this.converter.convertAction(Commands.DELETE, index, "");

        } else if (words[0].startsWith("#")) {
            int index = Integer.parseInt(words[1]) - 1;
            this.converter.convertAction(Commands.TAG, index, words[0].substring("#".length()));

        } else {
            throw new InvalidCommandException(
                    "Oohh I'm Mr. Meeseeks, your command is invalid. Try again?");
        }
    }

    private void parseTaskCommand(String input) throws InvalidCommandException {

        if (input.startsWith("deadline") && input.contains("/by")) {
            this.converter.passTask(this.converter.convertTask(Commands.DEADLINE, input));

        } else if (input.startsWith("event") && input.contains("/at")) {
            this.converter.passTask(this.converter.convertTask(Commands.EVENT, input));

        } else if (input.startsWith("todo")) {
            this.converter.passTask(this.converter.convertTask(Commands.TODO, input));

        } else {
            throw new InvalidCommandException("Oohh, I have to fulfill my purpose so I can go away! "
                    + "Please ensure your command format is correct and try again.");
        }
    }

    /**
     * Obtains the saved tasks from the converter.
     */
    public void parseSavedTasks() {
        this.converter.getSavedTasks();
    }

    private boolean isValidNumberCommand(String input) throws InvalidNumberException {

        String[] words = input.split(" ");
        boolean isTwoWords = words.length == 2;
        boolean hasBackNumber = words[words.length - 1].chars().allMatch(Character::isDigit);
        boolean isNumberedCommand = isTwoWords && hasBackNumber;

        if (isNumberedCommand) {
            int index = Integer.parseInt(words[1]) - 1;
            boolean isValidNumber = index < this.converter.getTotalTasks() && index >= 0;

            if (!isValidNumber) {
                throw new InvalidNumberException("Oohhh, the number entered is invalid, but don't worry! "
                        + "You have " + this.converter.getTotalTasks() + " tasks in your list.");
            }
            return true;

        } else {
            return false;
        }
    }

}
