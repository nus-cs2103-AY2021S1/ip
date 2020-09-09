package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.UiForGui;
import duke.task.Event;
import duke.task.Task;

public class FindCommand extends Command {

    public static final List<String> COMMAND_WORDS = new ArrayList<>(List.of("find", "f"));
    private String keyWords;

    public FindCommand(String keyWords) {
        this.keyWords = keyWords;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundTasks = new TaskList();
        for (Task task : tasks.getTaskList()) {
            if (task.getDescription().contains(keyWords)) {
                foundTasks.addTask(task);
            } else if (task instanceof Event && ((Event) task).getAt().contains(keyWords)) {
                foundTasks.addTask(task);
            }
        }
        ui.showFoundTaskList(foundTasks, keyWords);
    }

    @Override
    public String execute(TaskList tasks, UiForGui uiForGui, Storage storage) {
        TaskList foundTasks = new TaskList();
        for (Task task : tasks.getTaskList()) {
            if (task.getDescription().contains(keyWords)) {
                foundTasks.addTask(task);
            } else if (task instanceof Event && ((Event) task).getAt().contains(keyWords)) {
                foundTasks.addTask(task);
            }
        }
        return uiForGui.showFoundTaskList(foundTasks, keyWords);
    }
}
