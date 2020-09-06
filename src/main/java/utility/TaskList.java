package utility;

import java.util.ArrayList;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class TaskList {
    /**
     * Main ArrayList containing all tasks
     */
    private ArrayList<Task> taskList;

    /**
     * Data structure for managing tasks.
     * @param taskList arraylist of tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Used when no file is found and new TaskList needs to be made.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * returns the index of a specified character (first instance) in a string
     * @param s The String input.
     * @param target The character input.
     * @return index.
     */
    public static int getPosition(String s, char target) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Prints out the task added to the Bot.
     * @param ls an arraylist of tasks
     */
    public static String printAddedTask(ArrayList<Task> ls) {
        String out = "Got it. I've added this task:\n";
        out += ls.get(ls.size() - 1) + "\n";
        out += "Now you have " + ls.size() + " tasks in the list.\n";
        return out;
    }

    /**
     * Returns the taskList as an ArrayList
     * @return arraylist of tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Display all tasks in a list format.
     */
    public String listAllTasks() {
        StringBuilder out = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            out.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
        }
        return String.valueOf(out);
    }

    /**
     * Search for tasks for the same name.
     * @param input the name of the task to search for.
     */
    public String findTasks(String input) {
        assert input != null : "Input is null";
        String query = input.substring(5);
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            if (t.getDescription().contains(query)) {
                out.append(i + 1).append(". ").append(t);
            }
        }
        return String.valueOf(out);
    }

    /**
     * Mark a task as done.
     * @param input the number of the task to be marked as complete.
     */
    public String completeTask(String input) {
        assert input != null : "Input is null";
        // mark task as done
        int position = Integer.parseInt(input.substring(5));
        taskList.get(position - 1).markDone();
        return "Nice! I've marked this task as done:\n" + taskList.get(position - 1);
    }

    /**
     * Add an Event Task, which contains a datetime as well.
     * @param input name of the event task
     * @throws DukeException Custom Duke Exception
     */
    public String addEvent(String input) throws DukeException {
        assert input != null : "Input is null";
        if (input.length() <= 6) {
            throw new DukeException("Exception occurred: Name not found for Tasks.Event.");
        }
        int pos = getPosition(input, '/');
        String description = input.substring(6, pos);
        String at = input.substring(pos + 4);
        taskList.add(new Event(description, at));
        return printAddedTask(taskList);
    }

    /**
     * Add a Deadline Task, which contains a datetime as well.
     * @param input name of deadline task
     * @throws DukeException Custom Duke Exception
     */
    public String addDeadline(String input) throws DukeException {
        assert input != null : "Input is null";
        if (input.length() <= 9) {
            throw new DukeException("Exception occurred: Name not found for Tasks.Deadline.");
        }
        int pos = getPosition(input, '/');
        String description = input.substring(9, pos);
        String by = input.substring(pos + 4);
        System.out.println("Description: " + description);
        System.out.println("By: " + by);
        taskList.add(new Deadline(description, by));
        return printAddedTask(taskList);
    }

    /**
     * Add a Todo task.
     *
     * @param input name of Todo task.
     */
    public String addTodo(String input) {
        assert input != null : "Input is null";
        taskList.add(new Todo(input.substring(5)));
        return printAddedTask(taskList);
    }

    /**
     * Remove a task from the bot.
     *
     * @param input position of task to be deleted.
     * @throws DukeException Custom Duke Exception.
     */
    public String deleteTask(String input) throws DukeException {
        assert input != null : "Input is null";
        if (input.length() <= 7) {
            throw new DukeException("Exception occurred: Kindly enter a number for deletion.");
        }
        int position = Integer.parseInt(input.substring(7));
        String out = "Noted. I've removed this task:\n" + this.taskList.get(position - 1);
        this.taskList.remove(position - 1);
        return out;
    }


}
