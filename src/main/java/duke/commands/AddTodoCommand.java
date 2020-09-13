package duke.commands;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.MessageManager;
import duke.TaskManager;
import duke.exceptions.DukeException;
import duke.patterns.InputPattern;
import duke.tasks.Todo;

/**
 * Represents a command to add a todo.
 */
public class AddTodoCommand extends Command {
    /**
     * Initializes an add todo command.
     *
     * @param input User input.
     */
    public AddTodoCommand(String input) {
        this.input = input;
        this.isExit = false;
    }

    /**
     * Execution instructions for the command.
     * Adds a todo to storage and returns the message for Duke to show.
     *
     * @param taskManager TaskManager.
     * @return String response of command.
     */
    @Override
    public String execute(TaskManager taskManager) {
        Pattern r = Pattern.compile(InputPattern.ADD_TODO);
        Matcher m = r.matcher(input);
        m.find();
        String content = m.group("content");
        String priority = m.group("priority");
        try {
            Todo todo = taskManager.addTodo(content, priority);
            return MessageManager.getAddSuccessMessage(todo, taskManager);
        } catch (DukeException | IOException exception) {
            this.isError = true;
            return exception.getMessage();
        }
    }
}
