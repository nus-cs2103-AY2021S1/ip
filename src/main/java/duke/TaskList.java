package main.java.duke;

import main.java.duke.task.Deadline;
import main.java.duke.task.Event;
import main.java.duke.task.Task;
import main.java.duke.task.ToDo;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<Task> tasks;

    public TaskList(){
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task addToDo(String name) {
        Task newTask = new ToDo(name);
        tasks.add(newTask);
        return newTask;
    }

    public String find(String keyword) {
        String result = "";
        for (Task t : tasks) {
            if (t.getName().contains(keyword)){
                if (result != "") {
                    result += "\n   " + t.toString();
                } else {
                    result = "  " + t.toString();
                }
            }
        }
        return result;
    }

    public Task addDeadLine(String name, String time) throws DukeException {
        Task newTask = new Deadline(name,time);
        tasks.add(newTask);
        return newTask;
    }

    public Task addEvent(String name, String time) throws DukeException{
        Task newTask = new Event(name, time);
        tasks.add(newTask);
        return newTask;
    }

    public int getTotalTask(){
        return this.tasks.size();
    }

    public void doTask(int index) throws DukeException {
        this.tasks.get(index-1).setDone();
    }

    public String toSaveFormat() {
        String result = "";
        for(Task t: tasks) {
            result += t.toSaveFormat() + "\n";
        }
        return result;
    }

    public Task deleteTask(int index) throws DukeException {
        if (index-1 < 1 || index > this.tasks.size()) {
            throw new DukeException("Task #" + index + "does not exist.\n" +
                    "To check for lists of Tasks, type \"list\"");
        }
        return this.tasks.remove(index-1);
    }

    public String getTaskStatus(int index) {
        return tasks.get(index-1).toString();
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 1; i <= tasks.size(); i++) {
            output += i + "." + tasks.get(i-1).toString();
            if (i != tasks.size()) {
                output += "\n";
            }
        }
        return output;
    }
}
