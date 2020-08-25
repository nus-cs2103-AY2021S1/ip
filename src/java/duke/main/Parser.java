package duke.main;

import duke.exception.EmptyTaskException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingDateException;

import duke.task.Task;

public class Parser {
    private final TaskList taskList;
    private StringBuilder reply = new StringBuilder();

    Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    String parse(String input) {
        try {
            if (input.equals("list")) {
                list();
            } else if (input.startsWith("done")) {
                done(input);
            } else if (input.startsWith("delete")) {
                delete(input);
            } else if (input.startsWith("find")) {
                find(input);
            } else {
                add(input);
            }
        } catch (InvalidCommandException e) {
            reply.append(e.toString());
        }
        StringBuilder output = reply;
        reply = new StringBuilder();
        return output.toString();
    }

    private void list() {
        reply.append("Here are the tasks in your list:")
                .append(System.lineSeparator())
                .append(taskList);
    }

    private void done(String input) throws InvalidCommandException {
        if (input.length() != 6) {
            throw new InvalidCommandException();
        } else {
            try {
                int index = Integer.parseInt(input.substring(5)) - 1;
                Task completed = taskList.complete(index);
                reply.append("Well done! The following task is complete:")
                        .append(System.lineSeparator())
                        .append(completed);
            } catch (InvalidIndexException e) {
                reply.append(e.toString());
            }
        }
    }

    private void delete(String input) throws InvalidCommandException {
        if (input.length() != 8) {
            throw new InvalidCommandException();
        } else {
            try {
                int index = Integer.parseInt(input.substring(7)) - 1;
                Task deleted = taskList.delete(index);
                reply.append("Noted, I have removed the below task:")
                        .append(System.lineSeparator())
                        .append(deleted)
                        .append(System.lineSeparator())
                        .append("Now you have ")
                        .append(taskList.size())
                        .append(" task(s) left");
            } catch (InvalidIndexException e) {
                reply.append(e.toString());
            }
        }
    }

    private void find(String input) throws InvalidCommandException {
        if (input.length() <= 5) {
            throw new InvalidCommandException();
        }
        String searchString = input.substring(5);
        TaskList searchResults = taskList.find(searchString);
        if (searchResults.size() > 0) {
            reply.append("The following task(s) match your search:")
                    .append(System.lineSeparator())
                    .append(searchResults);
        } else {
            reply.append("Sorry, there are no tasks that match your description!");
        }
    }

    private void add(String input) throws InvalidCommandException {
        try {
            Task newTask = taskList.add(input);
            reply.append("Got it, I have added this task:")
                    .append(System.lineSeparator())
                    .append(newTask)
                    .append(System.lineSeparator())
                    .append("You now have ")
                    .append(taskList.size())
                    .append(" task(s) in this list");
        } catch (InvalidDateException | EmptyTaskException | MissingDateException e) {
            reply.append(e.toString());
        }
    }
}
