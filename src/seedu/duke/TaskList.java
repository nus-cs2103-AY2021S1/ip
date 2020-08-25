package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import seedu.duke.todo.*;

public class TaskList {
    ArrayList<Task> tasks = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    public void listTasks() throws DukeException {
        if (tasks.size() <= 0) {
            throw new DukeException("You don't have any tasks.");
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int taskNo = i + 1;
            Task task = tasks.get(i);
            System.out.println(taskNo + "." + task);
        }
    }

    public void listTasksOn(LocalDate date) throws DukeException {
        if (tasks.size() <= 0) {
            throw new DukeException("You don't have any tasks.");
        }
        ArrayList<Task> taskList = new ArrayList<>();
        for (Task task: tasks) {
            LocalDate taskDate = task.getDate();
            if (taskDate != null) {
                if (taskDate.isEqual(date)) {
                    taskList.add(task);
                }
            }
        }

        if (taskList.size() > 0) {
            System.out.println("Here are the tasks happening on: " + date.format(formatter));
            for (Task task: taskList) {
                System.out.println(task);
            }
        } else {
            System.out.println("You don't have anything on: " + date.format(formatter) + " :)))");
        }
    }

    public void doneTasks(int taskNo) {
        System.out.println("Nice! I've marked this task as done:");
        Task completedTask = tasks.get(taskNo - 1);
        completedTask.markAsDone();
        System.out.println(" " + " " +
                "[" + completedTask.getStatusIcon() + "] " +
                completedTask.getDescription());
    }

    public void addTask(Task newTask) {
        System.out.println("Got it. I've added this task:");
        tasks.add(newTask);
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void deleteTask(int taskNo) throws DukeException {
        if (tasks.size() <= 0) {
            throw new DukeException("Nothing to delete.");
        } else if (taskNo > tasks.size()) {
            throw new DukeException("Please provide a correct task number.");
        } else {
            System.out.println("Noted. I've removed this task:");
            Task taskToBeDeleted = tasks.get(taskNo - 1);
            tasks.remove(taskNo - 1);
            System.out.println(" " + " " +
                    "[" + taskToBeDeleted.getStatusIcon() + "] " +
                    taskToBeDeleted.getDescription());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }
}
