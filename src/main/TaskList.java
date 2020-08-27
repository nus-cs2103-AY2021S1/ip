import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.IOException;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTasks(){
        return taskList;
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
        String addTaskMessage = "____________________________________________________\n" +
                "Got it. I've added this task:\n" +
                newTask.toString() + "\n" +
                "Now you have " + taskList.size() + (taskList.size() > 1 ? " tasks " : " task ") + "in the list.\n" +
                "____________________________________________________\n";
        new Ui(addTaskMessage).printMessage();
    }

    public void deleteTask(int index) {
        Task task = taskList.remove(index);
        String deleteTaskMessage = "____________________________________________________\n" +
                "Noted. I've removed this task:\n" +
                task.toString() + "\n" +
                "____________________________________________________\n";
        new Ui(deleteTaskMessage).printMessage();
    }

    public void markDone(int i) {
        Task task = taskList.get(i);
        task.makeDone();
        String markDoneMessage = "____________________________________________________\n" +
                "Nice! I've marked this task as done:\n" +
                "[\u2713] " + task.getDescription() + "\n" +
                "____________________________________________________\n";
        new Ui(markDoneMessage).printMessage();
    }
}
