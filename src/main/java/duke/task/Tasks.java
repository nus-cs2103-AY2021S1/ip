package duke.task;

import duke.exception.ReadFailedException;

import java.time.LocalDate;

import java.util.ArrayList;

public class Tasks {
    private ArrayList<Task> tasks;

    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    
    public Task getTask(int taskIndex) throws IndexOutOfBoundsException {
        return this.tasks.get(taskIndex);
    }
    
    public int getSize() {
        return this.tasks.size();
    }
    
    public String getData() {
        String data = "";
        for (Task task: tasks) {
            data += task.getData() + "\n";
        }
        return data;
    }

    public void addTask(String[] stringArr) throws ReadFailedException {
        Task task = Task.createTask(stringArr);
        this.tasks.add(task);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }
    
    public void removeTask(int taskIndex) throws IndexOutOfBoundsException {
        this.tasks.remove(taskIndex);
    }
    
    public ArrayList<Task> findByDate(LocalDate date) {
        ArrayList<Task> taskList = new ArrayList<>();
        for (Task task: this.tasks) {
            if (task.hasDate()) {
                boolean isEqual = task.type == TaskType.DEADLINE
                        ? ((Deadline) task).isDateEqual(date)
                        : ((Event) task).isDateEqual(date);
                if (isEqual) taskList.add(task);
            }
        }
        return taskList;
    }
}
