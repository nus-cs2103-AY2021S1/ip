package duke.main;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingDateException;
import duke.task.Task;

/**
 * Parser is a class used by Duke to interpret user inputs as commands.
 */
public class Parser {
    private static final String BYE = "All changes saved, hope to see you again!";
    private static final String NO_TASK_FOUND = "Sorry, there are no tasks that "
            + "match your description!";

    private final Storage storage;
    private final TaskList taskList;
    private StringBuilder reply = new StringBuilder();

    Parser(Storage storage, TaskList taskList) {
        this.storage = storage;
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
    public String parse(String input) {
        if (input.equals("list")) {
            list();
        } else if (input.equals("bye")) {
            close();
        } else {
            operateOnTaskList(input);
        }

        StringBuilder output = reply;
        reply = new StringBuilder();
        return output.toString();
    }

    private void operateOnTaskList(String input) {
        try {
            String command = extractCommand(input);
            switch (command) {
            case "done":
                done(input.substring(5));
                break;
            case "delete":
                delete(input.substring(7));
                break;
            case "find":
                find(input.substring(5));
                break;
            case "todo":
                addToDo(input.substring(5));
                break;
            case "event":
                addEvent(input.substring(6));
                break;
            case "deadline":
                addDeadline(input.substring(9));
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) {
            reply.append(e);
        }
    }

    private String extractCommand(String input) throws InvalidCommandException {
        int spaceIndex = input.indexOf(' ');
        if (spaceIndex > 0) {
            return input.substring(0, spaceIndex);
        } else {
            throw new InvalidCommandException();
        }
    }

    private void list() {
        reply.append("Here are the tasks in your list:")
                .append(System.lineSeparator())
                .append(taskList);
    }

    private void close() {
        storage.writeToFile(taskList);
        reply.append(BYE);
    }

    private void done(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            Task completed = taskList.complete(index);
            reply.append("Well done! The following task is complete:")
                    .append(System.lineSeparator())
                    .append(completed);
        } catch (InvalidIndexException e) {
            reply.append(e.toString());
        }
    }

    private void delete(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
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

    private void find(String searchString) {
        TaskList searchResults = taskList.find(searchString);
        if (searchResults.size() > 0) {
            reply.append("The following task(s) match your search:")
                    .append(System.lineSeparator())
                    .append(searchResults);
        } else {
            reply.append(NO_TASK_FOUND);
        }
    }

    private void addToDo(String details) {
        Task toDo = taskList.addToDo(details);
        generateAddTaskMessage(toDo);
    }

    private void addEvent(String details) {
        try {
            Task event = taskList.addEvent(details);
            generateAddTaskMessage(event);
        } catch (MissingDateException | InvalidDateException e) {
            reply.append(e);
        }
    }

    private void addDeadline(String details) {
        try {
            Task deadline = taskList.addDeadline(details);
            generateAddTaskMessage(deadline);
        } catch (MissingDateException | InvalidDateException e) {
            reply.append(e);
        }
    }

    private void generateAddTaskMessage(Task task) {
        assert (task != null);
        reply.append("Got it, I have added this task:")
                .append(System.lineSeparator())
                .append(task)
                .append(System.lineSeparator())
                .append("You now have ")
                .append(taskList.size())
                .append(" task(s) in this list");
    }
}
