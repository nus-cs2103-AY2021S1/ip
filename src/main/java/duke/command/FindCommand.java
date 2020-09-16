package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;
import duke.ui.UiForGui;

/**
 * Encapsulates a find command for the Duke program. This is the command that finds tasks in the task list that contain
 * specific word(s) specified by the user. The format for this command is: "find key_words".
 */
public class FindCommand extends Command {

    /** Valid words to invoke the find command */
    public static final List<String> COMMAND_WORDS = new ArrayList<>(List.of("find", "f"));

    private String keyWords;

    /**
     * Creates and initializes an FindCommand object.
     *
     * @param keyWords The key words for which the user is asking to find tasks that contain them.
     */
    public FindCommand(String keyWords) {
        this.keyWords = keyWords;
    }

    /**
     * Executes the command in the CLI version of Duke. If successful, it will find and return all tasks from the task
     * list that contain the key words specified by the user.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The Ui object being used in the program.
     * @param storage The Storage object being used in the program.
     */
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

    /**
     * Executes the command in the GUI version of Duke. If successful, it will find and return all tasks from the task
     * list that contain the key words specified by the user.
     *
     * @param tasks The list of tasks in the program.
     * @param uiForGui The UiForGui object being used in the program.
     * @param storage The Storage object being used in the program.
     */
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
