package duke.component;

import duke.command.*;
import duke.task.*;

public class Parser {
    /**
     * Parses a DoneCommand to tell which task to mark as done.
     * @param cmd the given input command
     * @param count the current number of tasks in list
     * @return the index of the task to mark as done
     * @throws InvalidCommandException if the input is invalid, including non-integer, negative values, 0 or large
     * numbers
     */
    public static int isValidDone(String cmd, int count) throws InvalidCommandException {
        if (cmd.startsWith("done ")) {
            if (cmd.length() < 6) {
                throw new InvalidCommandException("\u2639 OOPS!!! The task to mark as done cannot be empty.");
            }
            try {
                int n = Integer.parseInt(cmd.substring(5));
                if (n < 1) {
                    throw new InvalidCommandException("\u2639 OOPS!!! The task index should be a positive integer.");
                } else if (n > count) {
                    throw new InvalidCommandException("\u2639 OOPS!!! The task index does not exist.");
                }
                return n;
            } catch (NumberFormatException e) {
                throw new InvalidCommandException("\u2639 OOPS!!! The task index should be a number.");
            }
        } else {
            return -1;
        }
    }

    /**
     * Parses a DeleteCommand to tell which task to delete.
     * @param cmd the given input command
     * @param count the current number of tasks in list
     * @return the index of the task to delete
     * @throws InvalidCommandException if the input is invalid, including non-integer, negative values, 0 or large
     * numbers
     */
    public static int isValidDelete(String cmd, int count) throws InvalidCommandException {
        if (cmd.startsWith("delete ")) {
            if (cmd.length() < 8) {
                throw new InvalidCommandException("\u2639 OOPS!!! The task to mark to delete cannot be empty.");
            }
            try {
                int n = Integer.parseInt(cmd.substring(7));
                if (n < 1) {
                    throw new InvalidCommandException("\u2639 OOPS!!! The task index should be a positive integer.");
                } else if (n > count) {
                    throw new InvalidCommandException("\u2639 OOPS!!! The task index does not exist.");
                }
                return n;
            } catch (NumberFormatException e) {
                throw new InvalidCommandException("\u2639 OOPS!!! The task index should be a number.");
            }
        } else {
            return -1;
        }
    }

    /**
     * Parses an AddCommand to tell what is the task need to be added.
     * @param cmd the given input command
     * @return the task to be added according to the command
     * @throws InvalidCommandException if the command does not make sense
     */
    public static Task generate(String cmd) throws InvalidCommandException {
        if (cmd.startsWith("todo")) {
            if (cmd.length() < 5) {
                throw new InvalidCommandException("\u2639 OOPS!!! The description of a todo cannot be empty.");
            } else if (cmd.charAt(4) != ' ') {
                throw new InvalidCommandException("Do you mean 'todo " + cmd.substring(4) + "'");
            } else if (cmd.length() < 6) {
                throw new InvalidCommandException("\u2639 OOPS!!! The description of a todo cannot be empty.");
            }
            return new ToDo(cmd.substring(5));
        } else if (cmd.startsWith("deadline")) {
            if (cmd.length() < 9) {
                throw new InvalidCommandException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
            } else if (cmd.charAt(8) != ' ') {
                throw new InvalidCommandException("Do you mean 'deadline " + cmd.substring(8) + "'");
            } else if (cmd.length() < 10) {
                throw new InvalidCommandException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
            }
            String description = cmd.substring(9);
            int s = description.indexOf("/by");
            if (s == -1) {
                throw new InvalidCommandException("\u2639 OOPS!!! Time should be specified");
            }
            if (description.length() - s < 4) {
                throw new InvalidCommandException("The time specification cannot be empty.");

            }
            String time = description.substring(s + 4);
            description = description.substring(0, s - 1);
            return new Deadline(description, time);
        } else if (cmd.startsWith("event")) {
            if (cmd.length() < 6) {
                throw new InvalidCommandException("\u2639 OOPS!!! The description of an event cannot be empty.");
            } else if (cmd.charAt(5) != ' ') {
                throw new InvalidCommandException("Do you mean 'event " + cmd.substring(5) + "'");
            } else if (cmd.length() < 7) {
                throw new InvalidCommandException("\u2639 OOPS!!! The description of an event cannot be empty.");
            }
            String description = cmd.substring(6);
            int s = description.indexOf("/at");
            if (s == -1) {
                throw new InvalidCommandException("\u2639 OOPS!!! Time should be specified");
            }
            if (description.length() - s < 4) {
                throw new InvalidCommandException("The time specification cannot be empty.");

            }
            String time = description.substring(s + 4);
            description = description.substring(0, s - 1);
            return new Event(description, time);
        } else {
            throw new InvalidCommandException();
        }
    }

    public static Command parse(String input) {
        if (input.equals("bye")) {
            return new ByeCommand(input);
        } else if (input.equals("list")) {
            return new ListCommand(input);
        } else if (input.startsWith("delete ")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("done ")) {
            return new DoneCommand(input);
        } else if (input.startsWith("happen ")) {
            return new HappenCommand(input);
        } else {
            return new AddCommand(input);
        }
    }
}
