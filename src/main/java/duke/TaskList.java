package duke;

import task.Deadline;
import task.Event;
import task.Todo;
import task.TaskType;
import task.Task;
import utility.NullListException;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static List<Task> taskList = new ArrayList<>();

    /**
     * Gets the number of tasks in list
     *
     * @return the number of tasks
     * @throws IOException if error occurs in file accessing
     */
    public static int getTaskListSize() throws IOException {
        int size = taskList.size();
        if (size == 0) {
            reloadList();
            size = taskList.size();
        }
        return size;
    }

    private static void reloadList() throws IOException {
        taskList = Storage.readList();
    }

    /**
     * Creates a task and adds it to the task list: todo
     *
     * @param type     task type
     * @param taskName task name
     * @return a string representation of the added task
     * @throws IOException if error occurs in file accessing
     */
    public static String add(TaskType type, String taskName) throws IOException {
        try {
            getTaskListSize();
            Todo todo = new Todo(taskName);
            taskList.add(todo);
            Storage.addToList(TaskType.TODO, 0, taskName, "");
            return todo.toString();
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
     * @return a string representation of the added task
     * @throws IOException if error occurs in file accessing
     */
    public static String add(TaskType type, String taskName, String time) throws IOException {
        try {
            getTaskListSize();
            if (type == TaskType.DEADLINE) {
                Deadline deadline = new Deadline(taskName, time);
                taskList.add(deadline);
                Storage.addToList(TaskType.DEADLINE, 0, taskName, time);
                return deadline.toString();
            } else {
                Event event = new Event(taskName, time);
                taskList.add(event);
                Storage.addToList(TaskType.EVENT, 0, taskName, time);
                return event.toString();
            }
        } catch (IOException e) {
            throw new IOException("File access failed");
        }

    }


    /**
     * Finds tasks that contain a given keyword
     *
     * @param word keyword of the task
     * @return a string representation of the tasks found
     * @throws IOException               if error occurs in file accessing
     * @throws InvalidParameterException if word is blank
     */
    public static String findTask(String word)
            throws IOException, InvalidParameterException, NullListException {
        if (word.isBlank()) {
            throw new InvalidParameterException("Invalid input");
        }
        int size = getTaskListSize();
        if (size == 0) {
             throw new NullListException();
        }
        StringBuilder sb = new StringBuilder();
        int count = 1;
        boolean isFound = false;
        for (int i = 1; i <= size; i++) {
            String task = taskList.get(i - 1).toString();
            if (task.contains(word)) {
                sb.append("\n").append(count).append(". ").append(task);
                isFound = true;
                count++;
            }
        }
        if (!isFound) {
            return "";
        } else {
            return sb.toString();
        }
    }

    /**
     * Updates the task of a given index to "done"
     *
     * @param index index of task to be updated
     * @return a string representation of the done task
     * @throws InvalidParameterException if index is out of bound for task list
     * @throws IOException               if error occurs in file accessing
     */
    public static String setDone(int index)
            throws InvalidParameterException, IOException, NullListException {
        int size = getTaskListSize();
        if (size == 0) {
            throw new NullListException();
        }
        if (index > taskList.size() || index <= 0) {
            throw new InvalidParameterException("Out of bound");
        }
        Storage.setDone(index - 1);
        taskList.get(index - 1).setDone();
        return taskList.get(index - 1).toString();
    }

    /**
     * Deletes the task of a given index
     *
     * @param index index of task to be updated
     * @return a string representation of the deleted task
     * @throws InvalidParameterException if index is out of bound for task list
     * @throws IOException               if error occurs in file accessing
     */
    public static String delete(int index)
            throws InvalidParameterException, IOException, NullListException {
        int size = getTaskListSize();
        if (size == 0) {
            throw new NullListException();
        }
        if (index > taskList.size() || index <= 0) {
            throw new InvalidParameterException("Out of bound");
        }
        Storage.delete(index - 1);
        String result = taskList.get(index - 1).toString();
        taskList.remove(index - 1);
        return result;
    }

    /**
     * Prints out the task list
     *
     * @return a string representation of all tasks listed
     * @throws IOException if error occurs in file accessing
     */
    public static String printList() throws IOException, NullListException {
        int size = getTaskListSize();
        if (size == 0) {
            throw new NullListException();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            sb.append(i).append(". ").append(taskList.get(i - 1).toString());
            if (i != size) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

}
