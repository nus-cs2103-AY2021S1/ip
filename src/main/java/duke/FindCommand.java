package duke;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FindCommand implements Command {
    TaskList taskList;
    String text;

    public FindCommand(TaskList taskList, String text) {
        this.taskList = taskList;
    }

    public void execute() {
        ArrayList<Task> Tasks = taskList.find(text);
        Tasks.forEach(task -> {
            System.out.println(task.toString());
        });
    }
}
