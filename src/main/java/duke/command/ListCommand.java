package duke.command;

import java.util.StringJoiner;

import duke.TaskList;

/**
 * Command to list all tasks in the task list.
 */
public class ListCommand implements Command {

    private final TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String executeWithResponse() {
        StringJoiner responseBuilder = new StringJoiner("\n");
        responseBuilder.add("Here are the tasks in your list:");
        String tasksString = taskList.getAllTasks();
        if (tasksString.length() == 0) {
            responseBuilder.add("No tasks found.");
        } else {
            responseBuilder.add(tasksString);
        }
        return responseBuilder.toString();
    }

    @Override
    public boolean continueDuke() {
        return true;
    }

}
