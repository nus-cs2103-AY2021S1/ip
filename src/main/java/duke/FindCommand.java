package duke;

import java.util.ArrayList;

public class FindCommand implements Command {
    private TaskList taskList;
    private String text;

    /**
     * Creates a find command for execution
     * @param taskList
     * @param text
     */
    public FindCommand(TaskList taskList, String text) {
        this.taskList = taskList;
        this.text = text;
    }

    /**
     * Executes command
     * @return the result of executing
     */
    public String execute() {
        ArrayList<Task> tasks = taskList.find(text);
        String taskStrings = "";
        for (Task task : tasks) {
            taskStrings += ("\n" + task.toString());
        }
        return taskStrings;
    }
}
