import exception.EmptyTaskException;
import exception.InvalidCommandException;
import exception.InvalidDateException;
import exception.InvalidIndexException;
import exception.MissingDateException;

import task.Task;

public class Parser {
    private final TaskList taskList;

    Parser(TaskList taskList) {
        this.taskList = taskList;
    }

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
                        .append("Now you have ")
                        .append(taskList.size())
                        .append(" task(s) left");
            }
        } catch (InvalidIndexException | InvalidDateException | InvalidCommandException
                | EmptyTaskException | MissingDateException e) {
            reply.append(e.toString());
        }
        return reply.toString();
    }
}
