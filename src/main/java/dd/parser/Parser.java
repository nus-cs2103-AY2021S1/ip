package dd.parser;

import dd.commands.*;
import dd.exception.DukeException;
import dd.ui.Ui;

public class Parser {

    /**
     * Parses and returns a appropriate command based on input given by user.
     *
     * @param input Input given by user.
     * @return A Command based on the input given.
     * @throws DukeException If no empty item description is given,
     * or if an invalid command is given.
     */
    public static Command parse(String input) throws DukeException {
        Command c;

        if (input.equals("bye")) {
            // exit command
            c = new ExitCommand("exit", "");
        } else if (input.equals("list")) {
            // list command
            new Ui().startList();

            c = new ListCommand("list", "");
        } else if (input.startsWith("todo")) {
            // add to-do command
            if (input.length() < 5) {
                throw new DukeException("To-do item cannot be empty!");
            } else {
                c = new AddCommand("todo", input.substring(5));
            }
        } else if (input.startsWith("deadline")) {
            // add deadline command
            if (input.length() < 9) {
                throw new DukeException("Deadline item cannot be empty!");
            } else {
                c = new AddCommand("deadline", input.substring(9));
            }
        } else if (input.startsWith("event")) {
            // add event command
            if (input.length() < 6) {
                throw new DukeException("Event item cannot be empty!");
            } else {
                c = new AddCommand("event", input.substring(6));
            }
        } else if (input.startsWith("done")) {
            // done command
            if (input.length() < 5) {
                throw new DukeException("Item number cannot be empty!");
            } else {
                c = new DoneCommand("done", input.substring(5));
            }
        } else if (input.startsWith("delete")) {
            // delete command
            if (input.length() < 7) {
                throw new DukeException("Item number cannot be empty!");
            } else {
                c = new DeleteCommand("delete", input.substring(7));
            }
        } else if (input.startsWith("check")) {
            // check list command
            if (input.length() < 6) {
                throw new DukeException("Query date cannot be empty!");
            } else {
                c = new ListCommand("check", input.substring(6));
            }
        } else if (input.startsWith("find")) {
            // list find command
            if (input.length() < 5) {
                throw new DukeException("Query description cannot be empty!");
            } else {
                c = new ListCommand("find", input.substring(5));
            }
        } else {
            // not valid task
            throw new DukeException("Invalid command, I don't understand :(");
        }

        return c;
    }
}
