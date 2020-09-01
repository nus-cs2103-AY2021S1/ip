package duke;

import duke.tasks.Task;
import duke.textstoreandprint.TextPrinter;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds an task object to this TaskList object
     * Prints out a graphic saying the task has been added and how many there are total
     *
     * @param task
     */
    public void addTask(Task task) {
        taskList.add(task);
        TextPrinter.standardPrint("Got it. I've added this task:\n  " +
                task.toString() + "\n" +
                "Now you have " + size() + " tasks in the list.");
    }

    /**
     * Adds an task object to this TaskList object
     * Does not print anything
     *
     * @param task
     */
    public void loadInTask(Task task) {
        taskList.add(task);
    }

    public int size() {
        return taskList.size();
    }

    /**
     * Prints out all the tasks in this list
     * Prints along with the top and bottom graphic from TextStore
     */
    public void printOut() {
        StringBuilder temp = new StringBuilder("Here are the tasks in your list:");
        int counter = 1;
        for (Task item: taskList) {
            temp.append("\n");
            temp.append(counter).append(". ").append(item.toString());
            counter++;
        }
        TextPrinter.standardPrint(temp.toString());
    }

    /**
     * Finds and returns all tasks with descriptions that contains the string provided
     *
     * @param string
     */
    public void search(String string) {
        StringBuilder temp = new StringBuilder("Here are the matching tasks in your list:");
        int counter = 1;
        for (Task item: taskList) {
            if (item.getDescription().contains(string)) {
                temp.append("\n");
                temp.append(counter).append(". ").append(item.toString());
                counter++;
            }
        }
        TextPrinter.standardPrint(temp.toString());
    }

    /**
     * Constructs a string from tasks that can be saved into a text file and can be read by FileManager
     *
     * @return A long string of all the tasks to be saved
     */
    public String allSaveString() {
        if (taskList.size() == 0) {
            return "";
        } else {
            StringBuilder temp = new StringBuilder();
            for (Task task : taskList) {
                temp.append("\n");
                temp.append(task.saveString());
            }
            return temp.substring(1);
        }
    }

    public void markDone(int position) {
        try {

            if (taskList.get(position - 1).markDone()) {
                TextPrinter.standardPrint("beri gude, finish that thing liao\n  " +
                        taskList.get(position - 1).toString());
            } else {
                TextPrinter.standardPrint("Task alr finish liao\n  " +
                        taskList.get(position - 1).toString());
            }

        } catch (IndexOutOfBoundsException e) {
            TextPrinter.printTaskNotFoundError();
        }
    }

    public void deleteTask(int position) {
        try {

            Task task = taskList.get(position - 1);
            taskList.remove(position - 1);
            TextPrinter.standardPrint("Noted. I've removed this task:\n  " +
                    task.toString() + "\n" +
                    "Now you have " + size() + " tasks in the list.");

        } catch (IndexOutOfBoundsException e) {
            TextPrinter.printTaskNotFoundError();
        }
    }
}
