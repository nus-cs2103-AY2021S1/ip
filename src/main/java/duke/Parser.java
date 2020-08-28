package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private static String combineWords(List<String> words) {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            if (i == 0) {
                message.append(words.get(i));
            } else {
                message.append((" ".concat(words.get(i))));
            }
        }
        return message.toString();
    }

    private static int extractTaskNumber(String command) {
        List<String> words = Arrays.asList(command.split(" "));
        if (words.size() != 2) {
            throw new IllegalArgumentException("Please type \"[action] [task number]\"");
        } else {
            // convert the task number into int
            int taskNumber = Integer.parseInt(words.get(1));
            return taskNumber;
        }
    }

    public static Task parseAddCommand(String command) throws DukeException {
        List<String> words = Arrays.asList(command.split(" "));
        String taskType = words.get(0);
        Task task;
        if (taskType.equalsIgnoreCase("todo")) {
            if (words.size() <= 1) {
                throw new DukeException(":( Oops!!! The description of a Todo task cannot be empty. :-(");
            }
            String taskDescription = combineWords(words.subList(1, words.size()));
            task = new Todo(taskDescription);
        } else if (taskType.equalsIgnoreCase("deadline")) {
            int index = words.indexOf("/by");
            if (words.size() <= 1 || index == 1) {
                throw new DukeException(":( Oops!!! The description of a Deadline task cannot be empty. :-(");
            }
            if (index == -1 || words.size() - index <= 1) {
                throw new DukeException(":( Oops!!! Please type \"deadline [task description] /by [yyyy-mm-dd]\" to add a Deadline task");
            }
            String taskDescription = combineWords(words.subList(1, index));
            String by = combineWords(words.subList(index + 1, words.size()));
            if (!by.matches("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))")) {
                throw new DukeException(":( Oops!!! Please type \"deadline [task description] /by [yyyy-mm-dd]\" to add a Deadline task");
            }
            task = new Deadline(taskDescription, by);
        } else if (taskType.equalsIgnoreCase("event")) {
            int index = words.indexOf("/at");
            if (words.size() <= 1 || index == 1) {
                throw new DukeException(":( Oops!!! The description of a Event task cannot be empty. :-(");
            }
            if (index == -1 || words.size() - index <= 1) {
                throw new DukeException(":( Oops!!! Please type \"event [task description] /at [event time] \" to add a Event task");
            }
            String taskDescription = combineWords(words.subList(1, index));
            String at = combineWords(words.subList(index + 1, words.size()));
            task = new Event(taskDescription, at);
        } else {
            throw new DukeException(":( Oops!!! I'm sorry, but I don't know what that means :-(\n\tCommands: list | done | delete | todo | deadline | event");
        }
        return task;
    }

    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("done")) {
            int taskNumber = extractTaskNumber(fullCommand);
            return new DoneCommand(taskNumber);
        } else if (fullCommand.startsWith("delete")) {
            int taskNumber = extractTaskNumber(fullCommand);
            return new DeleteCommand(taskNumber);
        } else if (fullCommand.equalsIgnoreCase("bye")){
            return new ByeCommand();
        } else if (fullCommand.startsWith("find")) {
            if (fullCommand.length() <= 5) {
                throw new DukeException(":( Oops!!! Please type \"find [keyword]\" to find a task");
            }
            String query = fullCommand.substring(5);
            return new FindCommand(query);
        } else {
            Task newTask = parseAddCommand(fullCommand);
            return new AddCommand(newTask);
        }
    }
}
