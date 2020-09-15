package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Duke is an interactive chat-bot that allows the user to
 * add, modify or delete tasks from a to-do list, which is
 * saved locally as a .txt file.
 * @author Ng Weng Fai
 */
public class Duke {
    private TaskList taskList;
    private Storage storage;

    public Duke() {
        taskList = TaskList.startList();
        storage = new Storage("data/duke.txt");
        try {
            storage.populateList(taskList);
        } catch (Exception e) {
            //Ui.displayMessage(e.getMessage());
        }
    }
    /**
     * Instantiates Duke, which loads data from a previously
     * saved  duke.txt, if it exists.
     * @param filepath file path of duke.txt.
     */
    public Duke(String filepath) {
        taskList = TaskList.startList();
        storage = new Storage(filepath);
        try {
            storage.populateList(taskList);
        } catch (Exception e) {
            Ui.displayMessage(e.getMessage());
        }
    }

    /**
     * Starts up Duke.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        Ui.displayMessage("Hello! I'm Duke\n\tWhat can I do for you?");
        String input;
        String msg;
        boolean cont = true;
        while (cont) {
            input = sc.nextLine();

            try {
                String[] parsedInput = Parser.parseInput(input);

                switch (parsedInput[0]) {
                case "bye":
                    msg = "Bye. Hope to see you again soon!";
                    cont = false;
                    break;

                case "list":
                    msg = taskList.toString();
                    break;

                case "find":
                    msg = taskList.find(parsedInput[1]);
                    break;

                case "done":
                    int n;
                    try {
                        n = Integer.parseInt(parsedInput[1]);
                    } catch (Exception e) {
                        throw new InvalidTaskNumberException();
                    }
                    msg = taskList.markAsDone(n);
                    storage.writeToFile(taskList.listToTxt());
                    break;

                case "delete":
                    int m;
                    try {
                        m = Integer.parseInt(parsedInput[1]);
                    } catch (Exception e) {
                        throw new InvalidTaskNumberException();
                    }
                    msg = taskList.remove(m);
                    storage.writeToFile(taskList.listToTxt());
                    break;

                case "todo":
                    msg = taskList.addToList(new Todo(parsedInput[1]));
                    storage.writeToFile(taskList.listToTxt());
                    break;

                case "deadline":
                    msg = taskList.addToList(new Deadline(parsedInput[1], parsedInput[2]));
                    storage.writeToFile(taskList.listToTxt());
                    break;

                case "event":
                    msg = taskList.addToList(new Event(parsedInput[1], parsedInput[2]));
                    storage.writeToFile(taskList.listToTxt());
                    break;

                default:
                    throw new InvalidInputException();

                }

            } catch (DateTimeParseException e) {
                msg = "Please enter a valid date/time in the format: yyyy-MM-ddTHH:mm";
            } catch (Exception e) {
                msg = e.getMessage();
            }
            Ui.displayMessage(msg);
        }

    }

    public String getResponse(String input) {
        String msg;
        try {
            String[] parsedInput = Parser.parseInput(input);

            switch (parsedInput[0]) {
                case "bye":
                    msg = "Bye. Hope to see you again soon!";
                    break;

                case "list":
                    msg = taskList.toString();
                    break;

                case "find":
                    msg = taskList.find(parsedInput[1]);
                    break;

                case "done":
                    int n;
                    try {
                        n = Integer.parseInt(parsedInput[1]);
                    } catch (Exception e) {
                        throw new InvalidTaskNumberException();
                    }
                    msg = taskList.markAsDone(n);
                    storage.writeToFile(taskList.listToTxt());
                    break;

                case "delete":
                    int m;
                    try {
                        m = Integer.parseInt(parsedInput[1]);
                    } catch (Exception e) {
                        throw new InvalidTaskNumberException();
                    }
                    msg = taskList.remove(m);
                    storage.writeToFile(taskList.listToTxt());
                    break;

                case "todo":
                    msg = taskList.addToList(new Todo(parsedInput[1]));
                    storage.writeToFile(taskList.listToTxt());
                    break;

                case "deadline":
                    msg = taskList.addToList(new Deadline(parsedInput[1], parsedInput[2]));
                    storage.writeToFile(taskList.listToTxt());
                    break;

                case "event":
                    msg = taskList.addToList(new Event(parsedInput[1], parsedInput[2]));
                    storage.writeToFile(taskList.listToTxt());
                    break;

                default:
                    throw new InvalidInputException();

            }

        } catch (DateTimeParseException e) {
            msg = "Please enter a valid date/time in the format: yyyy-MM-ddTHH:mm";
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;

    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
