package duke;

import java.util.ArrayList;

public class FindCommand implements Command {
    TaskList taskList;
    String text;

    public FindCommand(TaskList taskList, String text) {
        this.taskList = taskList;
    }

    public String execute() {
        ArrayList<Task> Tasks = taskList.find(text);
        String taskStrings = "";
        for (Task task : Tasks) {
            taskStrings += "\n" + task.toString();
        }
        return taskStrings;
    }
}
