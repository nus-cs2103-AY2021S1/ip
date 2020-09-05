package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import duke.command.DukeIndexOutOfBoundsException;
import duke.command.DukeInvalidCommandException;

public class TaskList {
    /**
     * The class TaskList denotes a task list.
     *
     * @author Alvin Chee
     */
    private static final int STARTINDEX = 1;
    private static List<Task> taskList;

    /**
     * Constructs a TaskList.
     *
     * @param taskList  An ArrayList of tasks to emulate.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns size of taskList.
     *
     * @return Size of taskList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Gets the task based on index.
     *
     * @param index  Index of task in taskList.
     * @return Task that is retrieved.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Removes the task based on index.
     *
     * @param index  Index of task in taskList.
     * @return Task that is removed.
     */
    public Task remove(int index) {
        return taskList.remove(index);
    }

    /**
     * Adds the task based at specified index.
     *
     * @param index  Index of task in taskList.
     * @param task  Task to be added to taskList.
     */
    public void add(int index, Task task) {
        taskList.add(index, task);
    }

    /**
     * Adds the task based at last index
     *
     * @param task  Task to be added to taskList.
     * @return String of words duke say in response.
     */
    public String addTask(Task task) {
        taskList.add(task);
        return "\tGot it. I've added this task:" + "\n\t\t" + task
            + String.format("\n\tNow you have %d tasks in the list.", taskList.size());
    }

    /**
     * Deletes the task based on index in taskInfo
     *
     * @param taskInfo  Task information with task index information.
     * @return String of words duke say in response.
     * @throws DukeIndexOutOfBoundsException
     */
    public String deleteTask(String taskInfo) throws DukeIndexOutOfBoundsException {
        String delete = "delete";
        if (taskInfo.trim().length() <= delete.length()) {
            throw new DukeIndexOutOfBoundsException("The task you want to delete is invalid");
        }
        String taskNoString = taskInfo.replace("delete", "").trim();
        int taskNo;
        try {
            taskNo = Integer.parseInt(taskNoString);
        } catch (NumberFormatException err) {
            throw new DukeNumberFormatException("Please input a number for the task you want to delete.");
        }
        if (taskNo < STARTINDEX || taskNo > taskList.size()) {
            throw new DukeIndexOutOfBoundsException("The task you want to delete is invalid");
        }
        int index = taskNo - 1;
        Task t = taskList.remove(index);
        return "\tNoted. I've removed this task:" + "\n\t\t" + t
            + String.format("\n\tNow you have %d tasks in the list.", taskList.size());
    }

    /**
     * Returns a list of task matching the keyword.
     *
     * @param matchWords  All the keywords from user
     */
    public List<Task> returnMatchingTasks(String ... matchWords) {
        List<Task> matchList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            for (int j = 0; j < matchWords.length; j++) {
                //In case of having alot of spaces in between two words, skip the iteration.
                if (matchWords[j].equals("")) {
                    continue;
                }
                String pattern = "\\b" + matchWords[j] + "\\b";
                try {
                    Pattern p = Pattern.compile(pattern);
                    Matcher m = p.matcher(taskList.get(i).toString());
                    if (m.find()) {
                        matchList.add(taskList.get(i));
                    }
                } catch (PatternSyntaxException e) {
                    throw new DukeInvalidCommandException("Sorry handsome but I'm not sure about this command :)");
                }
            }
        }
        return matchList;
    }
}
