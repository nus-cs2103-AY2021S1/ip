package duke;

import duke.command.*;
import duke.task.TaskType;

public class Parser {

    public static boolean isEmpty(String input, int x) {
        return input.length() <= x || input.substring(x).trim().isEmpty();
    }

    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.trim().equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("done ") || input.equals("done")) {
            if (isEmpty(input, 5)) {
                throw new DukeException("Which task do you want to mark as done?");
            }
            String item = input.substring(5).trim();
            return new DoneCommand(item);
        } else if (input.startsWith("delete ") || input.equals("delete")) {
            if (isEmpty(input, 7)) {
                throw new DukeException("Which task do you want to delete?");
            }
            String item = input.substring(7).trim();
            return new DeleteCommand(item);
        } else if (input.startsWith("todo ") || input.equals("todo")) {
            if (isEmpty(input, 5)) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            String name = input.substring(5).trim();
            return new AddCommand(TaskType.TODO, name);
        } else if (input.startsWith("deadline ") || input.equals("deadline")) {
            if (isEmpty(input, 9)) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String details = input.substring(9).trim();
            String[] split = details.split(" /by ");
            if (split.length != 2) {
                throw new DukeException("Please use the format: deadline (name) /by "
                        + "(yyyy-mm-dd)");
            }
            String name = split[0];
            String time = split[1];
            return new AddCommand(TaskType.DEADLINE, name, time);
        } else if (input.startsWith("event ") || input.equals("event")) {
            if (isEmpty(input, 6)) {
                throw new DukeException("The description of a event cannot be empty.");
            }
            String details = input.substring(6).trim();
            String[] split = details.split(" /at ");
            if (split.length != 2) {
                throw new DukeException("Please use the format: event (name) /at "
                        + "(yyyy-mm-dd)");
            }
            String name = split[0];
            String time = split[1];
            return new AddCommand(TaskType.EVENT, name, time);
        } else {
            return new NoCommand();
        }
    }
}