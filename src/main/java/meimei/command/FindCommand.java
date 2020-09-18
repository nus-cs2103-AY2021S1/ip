package meimei.command;

import java.util.ArrayList;
import java.util.List;

import meimei.Storage;
import meimei.TaskList;
import meimei.Ui;
import meimei.task.Task;

/**
 * Command that finds tasks that match a keyword or keywords (either partially or fully)
 * when executed.
 */
public class FindCommand extends Command {
    /** String to be searched for */
    private final String description;

    /**
     * Public constructor. Converts string entered by user to lower case
     * for simplicity.
     *
     * @param description Keyword that user wants to search for amongst tasks.
     */
    public FindCommand(String description) {
        this.description = description.toLowerCase();
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        List<Task> resultList = findMatchingTasks(tasks);

        String finalString = "";
        int counter = 0;
        for (Task task : resultList) {
            counter++;
            finalString += "\n" + counter + "." + task.toString();
        }

        return ui.returnFindReply(finalString);
    }

    private List<Task> findMatchingTasks(TaskList tasks) {
        String[] keywords = description.split(" ");
        List<Task> resultList = new ArrayList<>();

        for (int i = 1; i <= tasks.getListLength(); i++) {
            Task task = tasks.getTask(i);
            if (hasMatch(keywords, task)) {
                resultList.add(task);
            }
        }

        return resultList;
    }

    private boolean hasMatch(String[] keywords, Task task) {
        boolean isMatching = false;
        String simpleTaskName = task.getTaskName().toLowerCase();
        for (String keyword : keywords) {
            isMatching = isMatching || simpleTaskName.contains(keyword);
        }
        return isMatching;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
