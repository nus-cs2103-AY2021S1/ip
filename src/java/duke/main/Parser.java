package duke.main;

import duke.exception.EmptyTaskException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingDateException;

import duke.task.Task;

public class Parser {
    private final TaskList taskList;

    Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Interprets user input and executes commands.
     * The method supports commands for adding, deleting, viewing and completing tasks.
     * If invalid input is given, users are informed and told to try again.
     *
     * @param input User command in console.
     * @return Output message upon execution of command.
     */
    String parse(String input) {
        StringBuilder reply = new StringBuilder();
        try {
            if (input.equals("list")) {
                reply.append("Here are the tasks in your list:")
                        .append(System.lineSeparator())
                        .append(taskList);
            } else if (input.startsWith("done")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                Task completed = taskList.complete(index);
                reply.append("Well done! The following task is complete:")
                        .append(System.lineSeparator())
                        .append(completed);
            } else if (input.startsWith("delete")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                Task deleted = taskList.delete(index);
                reply.append("Noted, I have removed the below task:")
                        .append(System.lineSeparator())
                        .append(deleted)
                        .append(System.lineSeparator())
                        .append("Now you have ")
                        .append(taskList.size())
                        .append(" task(s) left");
            } else {
                Task newTask = taskList.add(input);
                reply.append("Got it, I have added this task:")
                        .append(System.lineSeparator())
                        .append(newTask)
                        .append(System.lineSeparator())
                        .append("You now have ")
                        .append(taskList.size())
                        .append(" task(s) in this list");
            }
        } catch (InvalidIndexException | InvalidDateException | InvalidCommandException |
                MissingDateException | EmptyTaskException e) {
            reply.append(e.toString());
        }
        return reply.toString();
    }
}
