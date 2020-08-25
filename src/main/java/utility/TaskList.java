package utility;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

public class TaskList {
    /**
     * Main ArrayList containing all tasks
     */
    private ArrayList<Task> taskList;

    /**
     * Data structure for managing tasks.
     * @param taskList
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
     * @param ls
     */
    public static void printAddedTask(ArrayList<Task> ls) {
        System.out.println("Got it. I've added this task:");
        System.out.println(ls.get(ls.size() - 1));
        System.out.println("Now you have " + ls.size() + " tasks in the list.");
    }

    /**
     * Returns the taskList as an ArrayList
     * @return
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Display all tasks in a list format.
     */
    public void listAllTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).isDone()) {
                System.out.println((i+1)+  ". " + taskList.get(i));
            } else {
                System.out.println((i+1)+  ". " + taskList.get(i));
            }
        }
    }

    /**
     * Search for tasks for the same name.
     * @param input
     */
    public void findTasks(String input) {
        String query = input.substring(5);
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            if (t.getDescription().contains(query)) {
                System.out.println((i+1) + ". " + t);
            }
        }
    }

    /**
     * Mark a task as done.
     * @param input
     */
    public void completeTask(String input) {
        // mark task as done
        int position = Integer.parseInt(input.substring(5));
        taskList.get(position-1).markDone();

        // print out
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(position-1));
    }

    /**
     * Add an Event Task, which contains a datetime as well.
     * @param input
     * @throws DukeException
     */
    public void addEvent(String input) throws DukeException {
        if (input.length() <= 6) {
            throw new DukeException("Exception occurred: Name not found for Tasks.Event.");
        }
        int pos = getPosition(input, '/');
        String description = input.substring(6, pos);
        String at = input.substring(pos + 4);
        taskList.add(new Event(description, at));
        printAddedTask(taskList);
    }

    /**
     * Add a Deadline Task, which contains a datetime as well.
     * @param input
     * @throws DukeException
     */
    public void addDeadline(String input) throws DukeException {
        if (input.length() <= 9) {
            throw new DukeException("Exception occurred: Name not found for Tasks.Deadline.");
        }
        int pos = getPosition(input, '/');
        String description = input.substring(9, pos);
        String by = input.substring(pos + 4);
        taskList.add(new Deadline(description, by));
        printAddedTask(taskList);
    }

    /**
     * Add a Todo task.
     * @param input
     */
    public void addTodo(String input) {
        taskList.add(new Todo(input.substring(5)));
        printAddedTask(taskList);
    }

    /**
     * Remove a task from the bot.
     * @param input
     * @throws DukeException
     */
    public void deleteTask(String input) throws DukeException {
        if (input.length() <= 7) {
            throw new DukeException("Exception occurred: Kindly enter a number for deletion.");
        }
        int position = Integer.parseInt(input.substring(7));
        System.out.println("Noted. I've removed this task:");
        System.out.println(this.taskList.get(position-1));
        this.taskList.remove(position-1);
    }


}
