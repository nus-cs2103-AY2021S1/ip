package duke;

import task.Deadline;
import task.Event;
import task.Todo;
import task.TaskType;
import task.Task;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static List<Task> taskList = new ArrayList<>();

    /**
     * Creates a task and adds it to the task list: todo
     *
     * @param type     task type
     * @param taskName task name
     * @return messages of the added task or errors occurred
     * @throws IOException if error occurs in file accessing
     */
    public static String add(TaskType type, String taskName) throws IOException {
        try {
            if (taskList.size() == 0) {
                reloadList();
            }
            Todo todo = new Todo(taskName);
            taskList.add(todo);
            Storage.addToList(TaskType.TODO, 0, taskName, "");
            String result = "Got it. I've added the task:\n" + todo.toString() + "\n";
            result += "Now you have " + taskList.size() + " tasks in the list.";
            return Ui.userMessage(result);
        } catch (IOException e) {
            throw new IOException("File access failed");
        }
    }

    /**
     * Creates a task and adds it to the task list: deadline or event
     *
     * @param type     task type
     * @param taskName task name
     * @param time     time of the task
     * @return messages of the added task or errors occurred
     * @throws IOException if error occurs in file accessing
     */
    public static String add(TaskType type, String taskName, String time) throws IOException {
        try {
            if (taskList.size() == 0) {
                reloadList();
            }
            String result = "Got it. I've added the task:\n";
            if (type == TaskType.DEADLINE) {
                Deadline deadline = new Deadline(taskName, time);
                taskList.add(deadline);
                Storage.addToList(TaskType.DEADLINE, 0, taskName, time);
                result += deadline.toString();
            } else {
                Event event = new Event(taskName, time);
                taskList.add(event);
                Storage.addToList(TaskType.EVENT, 0, taskName, time);
                result += event.toString();
            }
            result += "\nNow you have " + taskList.size() + " tasks in the list.";
            return Ui.userMessage(result);
        } catch (IOException e) {
            throw new IOException("File access failed");
        }

    }

    private static void reloadList() throws IOException {
        taskList = Storage.readList();
    }

    /**
     * Finds tasks that contain a given keyword
     *
     * @param word keyword of the task
     * @return a string representation of the tasks found
     * @throws IOException               if error occurs in file accessing
     * @throws InvalidParameterException if word is blank
     */
    public static String findTask(String word) throws IOException, InvalidParameterException {
        if (word.isBlank()) {
            throw new InvalidParameterException("Invalid input");
        }
        int n = taskList.size();
        if (n == 0) { // empty or uninitialized
            reloadList();
            n = taskList.size();
            if (n == 0) {
                return Ui.listError();
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:");
        int count = 1;
        boolean found = false;
        for (int i = 1; i <= n; i++) {
            String task = taskList.get(i - 1).toString();
            if (task.contains(word)) {
                sb.append("\n").append(count).append(". ").append(task);
                found = true;
                count++;
            }
        }
        if (!found) {
            return Ui.userMessage("There is no match in the list :(");
        } else {
            return Ui.userMessage(sb.toString());
        }
    }

    /**
     * Updates the task of a given index to "done"
     *
     * @param index index of task to be updated
     * @return messages of the done task or errors occurred
     * @throws InvalidParameterException if index is out of bound for task list
     * @throws IOException               if error occurs in file accessing
     */

    public static String setDone(int index) throws InvalidParameterException, IOException {
        if (taskList.size() == 0) {
            reloadList();
        }
        if (index > taskList.size() || index <= 0) {
            throw new InvalidParameterException("Out of bound");
        }
        Storage.setDone(index - 1);
        taskList.get(index - 1).setDone();
        return Ui.userMessage("Well done! I've marked this as done:\n"
                + taskList.get(index - 1).toString());
    }

    /**
     * Deletes the task of a given index
     *
     * @param index index of task to be updated
     * @return messages of the deleted task or errors occurred
     * @throws InvalidParameterException if index is out of bound for task list
     * @throws IOException               if error occurs in file accessing
     */
    public static String delete(int index) throws InvalidParameterException, IOException {
        if (taskList.size() == 0) {
            reloadList();
        }
        if (index > taskList.size() || index <= 0) {
            throw new InvalidParameterException("Out of bound");
        }
        Storage.delete(index - 1);
        String result = "Noted! I've removed this task:\n";
        result += taskList.get(index - 1).toString();
        taskList.remove(index - 1);
        result += "\nNow you have " + taskList.size() + " tasks in the list.";
        return Ui.userMessage(result);
    }

    /**
     * Prints out the task list
     *
     * @return a string representation of all tasks listed
     * @throws IOException if error occurs in file accessing
     */
    public static String printList() throws IOException {
        try {
            int n = taskList.size();
            if (n == 0) { // empty or uninitialized
                reloadList();
                n = taskList.size();
                if (n == 0) {
                    return Ui.listError();
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                sb.append(i).append(". ").append(taskList.get(i - 1).toString());
                if (i != n) {
                    sb.append("\n");
                }
            }
            return Ui.userMessage(sb.toString());
        } catch (IOException e) {
            return Ui.fileError();
        }
    }

}
