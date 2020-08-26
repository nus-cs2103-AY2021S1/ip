package dd.parser;

import dd.commands.*;
import dd.exception.DukeException;
import dd.ui.Ui;

public class Parser {

    public static Command parse(String input) throws DukeException {
        Command c;

        if (input.equals("bye")) {
            c = new ExitCommand("exit", "");
        }
        else if (input.equals("list")) {
            new Ui().startList();
            c = new ListCommand("list", "");
        }
        else if (input.startsWith("todo")) {
            if (input.length() < 5) {
                throw new DukeException("To-do item cannot be empty!");
            }
            else {
                c = new AddCommand("todo", input.substring(5));
            }
        }
        else if (input.startsWith("deadline")) {
            if (input.length() < 9) {
                throw new DukeException("Deadline item cannot be empty!");
            }
            else {
                c = new AddCommand("deadline", input.substring(9));
            }
        }
        else if (input.startsWith("event")) {
            if (input.length() < 6) {
                throw new DukeException("Event item cannot be empty!");
            }
            else {
                c = new AddCommand("event", input.substring(6));
            }
        }
        else if (input.startsWith("done")) {
            if (input.length() < 5) {
                throw new DukeException("Item number cannot be empty!");
            }
            else {
                c = new DoneCommand("done", input.substring(5));
            }
        }
        else if (input.startsWith("delete")) {
            if (input.length() < 7) {
                throw new DukeException("Item number cannot be empty!");
            }
            else {
                c = new DeleteCommand("delete", input.substring(7));
            }
        }
        else if (input.startsWith("check")) {
            if (input.length() < 6) {
                throw new DukeException("Query date cannot be empty!");
            }
            else {
                c = new ListCommand("check", input.substring(6));
            }
        } else if (input.startsWith("find")) {
            // list find command
            if (input.length() < 5) {
                throw new DukeException("Query description cannot be empty!");
            } else {
                c = new ListCommand("find", input.substring(5));
            }
        }
        else {
            // not valid task
            throw new DukeException("Invalid command, I don't understand :(");
        }
        return c;
    }
}
