package main.java.duke.core;

import java.time.LocalDate;
import java.util.ArrayList;
import main.java.duke.command.*;
import main.java.duke.task.*;
import main.java.duke.handle.*;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int n) {
        return tasks.remove(n);
    }

    public void markAsCompleted(int n) {
        tasks.get(n).markAsCompleted();
    }

    public boolean has(int n) {
        return n >= 0 && n < tasks.size();
    }

    public TaskList findTaskAt(LocalDate localDate) {
        ArrayList<Task> list = new ArrayList<>();
        for(int i = 0; i < tasks.size(); i = i + 1) {
            if(tasks.get(i).isAt(localDate)) {
                list.add(tasks.get(i));
            }
        }
        return new TaskList(list);
    }

    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i < tasks.size(); i = i + 1) {
            result = result + String.valueOf(i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return result;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int count) {
        return tasks.get(count);
    }
}
