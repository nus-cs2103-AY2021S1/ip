package seedu.duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that stores all the tasks in an arraylist.
 */
public class TaskList {
    private ArrayList<Task> taskLists;

    /**
     * Initializes an instance of Tasklist.
     *
     * @param listOfTasks Arraylist of Tasks to be completed.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.taskLists = listOfTasks;
    }

    /**
     * Checks if a input for a new task is valid.
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
     * Marks the task as done in a list.
     *
     * @param userInput Task to be completed.
     */
    public void completeTask(String userInput) {
        try {
            String[] splitUserInput = userInput.split(" ");
            int index = Integer.parseInt(splitUserInput[1]);
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
        String[] splitUserInput = userInput.split(" ");
        int index = Integer.parseInt(splitUserInput[1]);
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
            Todo tempTodo = new Todo(task);
            Storage.addTask(tempTodo.getStorageString("T"));
            info += "  " + tempTodo.toString() + "\n";
            this.taskLists.add(tempTodo);
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
            Deadline tempDeadline = new Deadline(task, time);
            String formatDate = tempDeadline.getFormattedDate();
            info += "  " + tempDeadline.toString() + "\n";
            taskLists.add(tempDeadline);
            Storage.addTask(tempDeadline.getStorageString("D", formatDate));
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
            Event tempEvent = new Event(task, time);
            String formatDate = tempEvent.getFormattedDate();
            info += "  " + tempEvent.toString() + "\n";
            Storage.addTask(tempEvent.getStorageString("E", formatDate));
            taskLists.add(tempEvent);
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
