package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskList {
    /**
     * The class TaskList denotes a task list.
     *
     * @author Alvin Chee
     */
    public static List<Task> taskList;

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
     */
    public void addTask(Task task) {
        taskList.add(task);
        System.out.println("\tGot it. I've added this task:" + "\n\t\t" + task);
        System.out.println(String.format("\tNow you have %d tasks in the list.", taskList.size()));
    }

    /**
     * Deletes the task based on index in taskInfo
     *
     * @param taskInfo  Task information with task index information.
     */
    public void deleteTask(String taskInfo) throws DukeIndexOutOfBoundsException{
        if (taskInfo.length() <= 7) {
            throw new DukeIndexOutOfBoundsException("The task you want to delete is invalid");
        }
        int taskNo = Character.getNumericValue(taskInfo.charAt(7));
        if (taskNo < 1 || taskNo > taskList.size()) {
            throw new DukeIndexOutOfBoundsException("The task you want to delete is invalid");
        }
        int index = taskNo - 1;
        Task t = taskList.remove(index);
        System.out.println("\tNoted. I've removed this task:" + "\n\t\t" + t);
        System.out.println(String.format("\tNow you have %d tasks in the list.", taskList.size()));
    }

    public List<Task> returnMatchingTasks(String[] matchWords) {
        List<Task> matchList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            for (int j = 0; j < matchWords.length; j++) {
                    String pattern = "\\b" + matchWords[j] + "\\b";
                    Pattern p = Pattern.compile(pattern);
                    Matcher m = p.matcher(taskList.get(i).toString());
                    if (m.find()) {
                        matchList.add(taskList.get(i));
                    }
            }
        }
        return matchList;
    }
}
