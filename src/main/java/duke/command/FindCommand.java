package duke.command;
import java.util.ArrayList;
import java.util.stream.IntStream;

import duke.logic.CommandInteractionUi;
import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.task.DukeTask;



/**
 * Represents a Find Command by the user.
 * Apart from the parent's implementation,
 * it contains a <code>String</code> keyword
 * to be found from the <code>TaskList</code>.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public boolean getExitStatus() {
        return false;
    }

    /**
     * Searches for <code>DukeTask</code> with the keyword in the <code>TaskList</code> and prints feedback.
     * If the task is a form of GUI command, sets response to the result instead.
     *
     * @param taskList       <code>TaskList</code> object containing the user's <code>DukeTask</code>.
     * @param uiManager      <code>UIManager</code> object to handle printing feedback to user.
     * @param storageManager <code>StorageManager</code> object to saving/loading user data.
     * @param isGuiTask      <code>boolean</code> object to denote GUI task.
     */
    @Override
    public void execute(TaskList taskList, CommandInteractionUi uiManager,
                        StorageManager storageManager, boolean isGuiTask) {
        assert uiManager != null : "FindCommand must have a uiManager";
        assert taskList != null : "FindCommand must have a taskList";
        ArrayList<DukeTask> filteredList = searchTaskList(taskList);
        if (filteredList.size() == 0) { // The TaskList does not contain anything with keyword
            if (isGuiTask) {
                response = uiManager.getKeywordCannotBeFound(keyword);
            } else {
                uiManager.printKeywordCannotBeFound(keyword);
            }
        } else {
            if (isGuiTask) {
                response = buildResponseString(filteredList, uiManager);
            } else {
                uiManager.printKeywordFoundResult(keyword, filteredList.size() > 1);
                IntStream.range(0, filteredList.size())
                        .forEach(i -> uiManager.printNumberedTask(filteredList.get(i), i));
            }
        }
    }

    /**
     * Returns a list of DukeTasks containing the keyword
     * @param taskList TaskList of DukeTasks to be searched
     * @return ArrayList of DukeTasks that contain the keyword
     */
    private ArrayList<DukeTask> searchTaskList(TaskList taskList) {
        ArrayList<DukeTask> filteredList = new ArrayList<>();
        taskList.getTaskList().forEach(task -> {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(task);
            }
        });
        return filteredList;
    }

    /**
     * Returns a String denoting output of the Command response
     * @param tasks ArrayList of the DukeTasks
     * @param uiManager UIManager in charge of returning Strings related to the response
     * @return String of the response
     */
    private String buildResponseString(ArrayList<DukeTask> tasks, CommandInteractionUi uiManager) {
        StringBuilder output = new StringBuilder(
                uiManager.getKeywordFoundResult(keyword, tasks.size() > 1) + "\n");
        IntStream.range(0, tasks.size())
                .forEach(i -> output.append(uiManager.getNumberedTask(tasks.get(i), i)).append("\n"));
        return output.toString();
    }
}

