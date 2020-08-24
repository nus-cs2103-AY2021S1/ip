package seedu.duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that stores all the tasks in an arraylist.
 */
public class TaskList {
    private ArrayList<Task> taskLists;

    /**
     * Initialize an instance of Tasklist.
     *
     * @param ls Arraylist of Tasks to be completed.
     */
    public TaskList(ArrayList<Task> ls) {
        this.taskLists = ls;
    }

    /**
     * Check if a input for a new task is valid.
     *
     * @param input Name of the task.
     * @param task Task type.
     * @throws DukeException if empty input is given.
     */
    public void checkForItem(String input, String task) throws DukeException {
        if (input.isBlank()) {
            throw new DukeException("The description of a " + task + " cannot be empty!");
        }
    }

    /**
     * Mark the task as done in a list.
     *
     * @param userInput Task to be completed.
     */
    public void completeTask(String userInput) {
        try {
            int index = userInput.charAt(5) - '0';
            if (index < 1 || index > taskLists.size()) {
                Ui.print("Index out of range! Try Again.\n");
            } else {
                if (taskLists.get(index - 1).getIsDone()) {
                    Ui.print("This task has already been completed!");
                } else {
                    taskLists.get(index - 1).markAsDone();
                    Storage.completeTask(index - 1, taskLists.size());
                    String info = "  Nice! I have marked this task as done:\n";
                    info += taskLists.get(index - 1).toString() + "\n";
                    Ui.print(info);
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            Ui.print("Index out of range! Try again.\n");
        }
    }

    /**
     * Task to be deleted from tasklist.
     *
     * @param userInput String of the task to be deleted.
     */
    public void deleteTask(String userInput) {
        int index = userInput.charAt(7) - '0';
        if (index < 1 || index > taskLists.size()) {
            Ui.print("Index out of range! Try Again.\n");
        } else {
            String info = "Noted. I have removed this task:\n";
            info += "  " + taskLists.get(index - 1).toString() + "\n";
            taskLists.remove(index - 1);
            Storage.deleteTask(index - 1, taskLists.size());
            info += "Now you have " + taskLists.size() + " tasks in the list" + "\n";
            Ui.print(info);
        }
    }

    /**
     * Todo to be added to tasklist.
     *
     * @param userInput String of the todo.
     */
    public void addToDo(String userInput) {
        try {
            this.checkForItem(userInput.substring(4), "todo");
            String task = userInput.substring(5);
            String info = ("Got it. I have added this task:\n");
            Todo temp = new Todo(task);
            Storage.addTask(temp.getStorageString("T"));
            info += "  " + temp.toString() + "\n";
            this.taskLists.add(temp);
            info += "Now you have " + taskLists.size() + " tasks in the list\n";
            Ui.print(info);
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deadline to be added to tasklist.
     *
     * @param userInput String of the deadline.
     */
    public void addDeadline(String userInput) {
        try {
            checkForItem(userInput.substring(8), "deadline");
            int dateIndex = userInput.indexOf("/");
            String task = userInput.substring(9, dateIndex);
            String time = userInput.substring(dateIndex + 1);
            String info = "Got it. I have added this task:\n";
            Deadline temp = new Deadline(task, time);
            String formatDate = temp.getFormattedDate();
            info += "  " + temp.toString() + "\n";
            taskLists.add(temp);
            Storage.addTask(temp.getStorageString("D", formatDate));
            info += "Now you have " + taskLists.size() + " tasks in the list\n";
            Ui.print(info);
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Event to be added to the tasklist.
     *
     * @param userInput String of the event.
     */
    public void addEvent(String userInput) {
        try {
            checkForItem(userInput.substring(5), "event");
            int dateIndex = userInput.indexOf("/");
            String task = userInput.substring(6, dateIndex);
            String time = userInput.substring(dateIndex + 1);
            String info = "Got it. I have added this task:\n";
            Event temp = new Event(task, time);
            String formatDate = temp.getFormattedDate();
            info += "  " + temp.toString() + "\n";
            Storage.addTask(temp.getStorageString("E", formatDate));
            taskLists.add(temp);
            info += "Now you have " + taskLists.size() + " tasks in the list\n";
            Ui.print(info);
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void find(String input) {
        try {
            checkForItem(input.substring(5), "find");
            String keyword = input.substring(5);
            ArrayList<Task> keywordInTasks = new ArrayList<>();
            for (int i = 0; i < this.taskLists.size(); i++) {
                Task current = this.taskLists.get(i);
                if (current.toString().contains(keyword)) {
                    keywordInTasks.add(current);
                }
            }
            if (keywordInTasks.size() == 0) {
                Ui.print("There are no tasks related to this keyword!");
            } else {
                String info = "Here are the matching tasks in your list: \n";
                for (int i = 0; i < keywordInTasks.size(); i++) {
                    info += keywordInTasks.get(i).toString() + "\n";
                }
                Ui.print(info);
            }
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        }
    }

    /**
     * Iterates through the arraylist of tasks and print it out.
     *
     * @return String describing each tasks in the list.
     */
    @Override
    public String toString() {
        String temp = "";
        temp += ("  Here are the tasks in your list:\n");
        for (int i = 0; i < taskLists.size(); i++) {
            temp += ("  " + (i + 1) + ". " + taskLists.get(i).toString() + " \n");
        }
        return temp;
    }


}
