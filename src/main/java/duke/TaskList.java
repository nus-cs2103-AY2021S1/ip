package duke;

import static duke.DukeCommandType.UPDATE;

import java.util.ArrayList;

/**
 * TaskList class contains the task list and all operations that modify task in the list.
 */
public class TaskList {

    /** User's list of tasks */
    protected static ArrayList<Task> tasks;

    /**
     * Initialises user's list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds task into user's list of tasks.
     *
     * @param task
     */

    public static String addTask(Task task) {
        tasks.add(task);
        String str = "Got it. I've added this task:"
                + "\n" + task
                + "\nNow you have " + tasks.size() + " task(s) in the list.";
        return str;
    }

    /**
    * Adds task from listOfTasks.txt into user's list of tasks.
    *
    * @param task
    */
    public static void addStoredTask(Task task) {
        tasks.add(task);
    }

    /**
     * Prints user's list of tasks.
     */
    public static String getListOfTasks() {
        String str = "";
        if (tasks.isEmpty()) {
            try {
                throw new DukeException("", DukeExceptionType.EMPTY_LIST);
            } catch (DukeException e) {
                System.err.println(e);
                str += e;
            }
        } else {
            String lst = "Here are the tasks in your list:";
            for (int i = 0; i < tasks.size(); i++) {
                int index = i + 1;
                lst += "\n" + index + ". " + tasks.get(i);
            }
            str = lst;
        }
        System.out.println(str);
        return str;
    }

    /**
     * Marks a task as done when user completes it.
     *
     * @param index
     */
    public static String markDone(Integer index) {

        tasks.get(index - 1).markAsDone();
        String str = "Nice! I've marked this task as done:\n      "
                + tasks.get(index - 1);
        return str;
    }

    /**
     * Deletes a task from the list.
     *
     * @param index
     */
    public static String deleteTask(Integer index) {
        String str = "Noted. I've removed this task:\n      "
                + tasks.remove(index - 1)
                + "\n    Now you have " + tasks.size() + " task(s) in the list.";
        return str;
    }

    /**
     * Finds matching tasks based on keyword provided by user.
     *
     * @param keyword
     */
    public static String findTasks(String keyword) {
        String str = "";
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            try {
                throw new DukeException("", DukeExceptionType.NO_MATCHING_FOUND);
            } catch (DukeException e) {
                str += e;
            }
        } else {
            int index = 1;
            str += "Here are the matching tasks in your list:";
            for (Task task : matchingTasks) {
                str += "\n" + index + ". " + task;
                index++;
            }
        }
        return str;
    }

    /**
     * Updates task's description or date
     *
     * @param index
     * @param type
     * @param edit
     * @return String of updated task
     */
    public static String updateTask(Integer index, String type, String edit) {
        String str = "";
        if (index > tasks.size()) {
            try {
                throw new DukeException("", DukeExceptionType.INVALID_INDEX, UPDATE);
            } catch (DukeException e) {
                str += e;
            }
        } else {
            Task currTask = tasks.get(index);
            Task updatedTask = null;
            if (currTask instanceof ToDo) {
                if (type.equals("desc")) {
                    updatedTask = new ToDo(edit, currTask.isDone);
                    tasks.set(index, updatedTask);
                    str += "Okay! I've updated this task to:\n"
                            + tasks.get(index);
                } else if (type.equals("date")) {
                    str += "ToDo have no deadline.";
                }
            } else if (currTask instanceof Deadline) {
                if (type.equals("desc")) {
                    updatedTask = new Deadline(edit, ((Deadline) currTask).deadline, currTask.isDone);
                    tasks.set(index, updatedTask);
                    str += "Okay! I've updated this task to:\n"
                            + tasks.get(index);
                } else if (type.equals("date")) {
                    try {
                        updatedTask = new Deadline(currTask.description, edit, currTask.isDone);
                        tasks.set(index, updatedTask);
                        str += "Okay! I've updated this task to:\n"
                                + tasks.get(index);
                    } catch (RuntimeException exception) {
                        try {
                            throw new DukeException("", DukeExceptionType.WRONG_TIME_FORMAT, UPDATE);
                        } catch (DukeException e) {
                            System.err.println(e);
                            str += e;
                        }
                    }
                }
            } else if (currTask instanceof Event) {
                if (type.equals("desc")) {
                    updatedTask = new Event(edit, ((Event) currTask).scheduled, currTask.isDone);
                    tasks.set(index, updatedTask);
                    str += "Okay! I've updated this task to:\n"
                            + tasks.get(index);
                } else if (type.equals("date")) {
                    try {
                        updatedTask = new Deadline(currTask.description, edit, currTask.isDone);
                        tasks.set(index, updatedTask);
                        str += "Okay! I've updated this task to:\n"
                                + tasks.get(index);
                    } catch (RuntimeException e) {
                        try {
                            throw new DukeException("", DukeExceptionType.WRONG_TIME_FORMAT, UPDATE);
                        } catch (DukeException exception) {
                            System.err.println(e);
                            str += e;
                        }
                    }
                }
            }
        }
        return str;
    }
}
