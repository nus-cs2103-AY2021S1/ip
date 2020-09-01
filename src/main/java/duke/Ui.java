package duke;

import java.util.ArrayList;

import task.Task;

/**
 * A Ui object deals with interactions with the user.
 *
 * @author amelia
 * @version 1.0
 * @since 2020-08-26
 */
public class Ui {

    public String addTask(Task newTask, TaskList currList) {
        currList.add(newTask);
        String outputMsg = "Got it. I've added this task:\n"
                + newTask.toString()
                + "\nYou have " + currList.getNumOfTasks() + " tasks in the list.";
        return outputMsg;
    }

    public String deleteTask(int taskNumber, Task currTask, TaskList currList) {
        currList.remove(taskNumber - 1);
        String output = "Noted. I've removed this task:\n"
                + currTask.toString()
                + "\nNow you have " + currList.getNumOfTasks() + " tasks in the list.";
        return output;
    }

    public String completeTask(Task currTask) {
        currTask.markAsComplete();
        return "Nice! I've marked this task as done:\n" + currTask.toString();
    }

    public String findTask(ArrayList<Task> searchResult) {
        TaskList result = new TaskList(searchResult);
        return result.displayTasks();
    }
}
