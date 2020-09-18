package duke.task;

import java.util.ArrayList;
import java.util.Iterator;
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
        return "\tYou got it Poppins! I've added this task:" + "\n\t\t" + task
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
            throw new DukeIndexOutOfBoundsException("The task you want to delete is invalid :(");
        }
        String taskNoString = taskInfo.replace("delete", "").trim();
        int taskNo;
        try {
            taskNo = Integer.parseInt(taskNoString);
        } catch (NumberFormatException err) {
            throw new DukeNumberFormatException("Please input a number for the task you want to delete :D");
        }
        if (taskNo < STARTINDEX || taskNo > taskList.size()) {
            throw new DukeIndexOutOfBoundsException("The task you want to delete is invalid :(");
        }
        int index = taskNo - 1;
        Task t = taskList.remove(index);
        return "\tYou got it Poppins! I've removed this task:" + "\n\t\t" + t
            + String.format("\n\tNow you have %d tasks in the list.", taskList.size());
    }

    /**
     * Returns a list of task matching the keyword.
     *
     * @param matchWords  All the keywords from user
     */
    public List<Task> returnMatchingTasks(String ... matchWords) {
        List<Task> matchList = new ArrayList<>();
        Iterator<Task> iterator = taskList.iterator();
        iterator.forEachRemaining(matchTask -> {
            for (int j = 0; j < matchWords.length; j++) {
                //In case of having alot of spaces in between two words, skip the iteration.
                if (matchWords[j].equals("")) {
                    continue;
                }
                String pattern = "\\b" + matchWords[j] + "\\b";
                try {
                    Pattern p = Pattern.compile(pattern);
                    assert p != null : "PatternSyntaxException not thrown,"
                        + " unknown exception occured without being caught.";
                    Matcher m = p.matcher(matchTask.toString());
                    if (m.find()) {
                        matchList.add(matchTask);
                    }
                } catch (PatternSyntaxException e) {
                    throw new DukeInvalidCommandException("Sorry Poppins "
                        + "but I don't understand what I need to find :(");
                }
            }
        });
        return matchList;
    }

    /**
     * Returns a list of duplicate tasks.
     */
    public List<String> detectDuplicates() {
        List<String> duplicateList = new ArrayList<>();
        List<String> finalDuplicateList = new ArrayList<>();
        for (int i = 0; i < taskList.size() - 1; i++) {
            for (int j = i + 1; j < taskList.size(); j++) {
                if (taskList.get(i).returnTaskInfo().equalsIgnoreCase(taskList.get(j).returnTaskInfo())) {
                    duplicateList.add(taskList.get(i).returnTaskInfo());
                }
            }
        }
        duplicateList.sort(null);
        String taskAdded = "";
        for (int i = 0; i < duplicateList.size(); i++) {
            String currentTask = duplicateList.get(i);
            if (!taskAdded.equalsIgnoreCase(currentTask)) {
                taskAdded = currentTask;
                finalDuplicateList.add(duplicateList.get(i));
            }
        }
        return finalDuplicateList;
    }

    /**
     * Removes all duplicate tasks except first copies.
     */
    public void removeDuplicatesExceptFirst() {
        List<String> duplicates = detectDuplicates();
        List<String> removedDuplicates = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            for (int j = 0; j < duplicates.size(); j++) {
                String taskInfo = taskList.get(i).returnTaskInfo();
                if (taskInfo.equalsIgnoreCase(duplicates.get(j))) {
                    if (!removedDuplicates.contains(taskInfo)) {
                        removedDuplicates.add(taskInfo);
                    } else {
                        taskList.set(i, null);
                    }
                    break;
                }
            }
        }
        taskList.removeIf(task -> task == null);
    }

    public void clear() {
        taskList.clear();
    }
}
