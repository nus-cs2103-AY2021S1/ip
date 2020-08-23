package main.java;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;
    private Ui ui;
    
    public TaskList() {
        this.tasks = new ArrayList<>();
        ui = new Ui();
    }
    
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        ui = new Ui();
    }
    
    public List<Task> getTasksList() {
        return this.tasks;
    }
    
    public void showTasks() {
        ui.printLineBackground();
        System.out.println("        Here are the tasks in your list:");
        for(int i=0; i<tasks.size(); i++) {
            System.out.println("            " + (i+1) + "." + tasks.get(i));
        }
        ui.printLineBackground();
    }
    
    public void setDone(int taskPosition) {
        tasks.get(taskPosition - 1).markAsDone();
        ui.printLineBackground();
        System.out.println("        Nice! I've marked this task as done:");
        System.out.println("            " + tasks.get(taskPosition - 1));
        ui.printLineBackground();
    }
    
    public void addToDo(String description) {
        Task toDo = new Todo(description);
        tasks.add(toDo);
        ui.printLineBackground();
        System.out.println("        Got it. I've added this task:");
        System.out.println("            " + toDo);
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        ui.printLineBackground();
    }
    
    public void addDeadline(String description, String by) {
        Task deadline = new Deadline(description, by);
        tasks.add(deadline);
        ui.printLineBackground();
        System.out.println("        Got it. I've added this task:");
        System.out.println("            " + deadline);
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        ui.printLineBackground();
    }
    
    public void addEvent(String description, String at) {
        Task event = new Event(description, at);
        tasks.add(event);
        ui.printLineBackground();
        System.out.println("        Got it. I've added this task:");
        System.out.println("            " + event);
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        ui.printLineBackground();
    }
    
    public void deleteTask(int taskPosition) {
        ui.printLineBackground();
        System.out.println("        Noted. I've removed this task:");
        System.out.println("            " + tasks.remove(taskPosition - 1));
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        ui.printLineBackground();
    }
    
    public void filterTask(String[] inputArray) {
        QueryOnTasks queryOnTasks = new QueryOnTasks();
        if (inputArray[1].equals("date")) {
            List<Task> queryList = queryOnTasks.filterByDate(tasks,inputArray[2]);
            ui.printLineBackground();
            System.out.println("        Here are the tasks in your query list:");
            for(int i=0; i<queryList.size(); i++) {
                System.out.println("            " + (i+1) + "." + queryList.get(i));
            }
            ui.printLineBackground();
            
        } else if (inputArray[1].equals("month")) {
            List<Task> queryList = queryOnTasks.filterByMonth(tasks,inputArray[2]);
            ui.printLineBackground();
            System.out.println("        Here are the tasks in your query list:");
            for(int i=0; i<queryList.size(); i++) {
                System.out.println("            " + (i+1) + "." + queryList.get(i));
            }
            ui.printLineBackground();
            
        } else if (inputArray[1].equals("year")) {
            List<Task> queryList = queryOnTasks.filterByYear(tasks,inputArray[2]);
            ui.printLineBackground();
            System.out.println("        Here are the tasks in your query list:");
            for(int i=0; i<queryList.size(); i++) {
                System.out.println("            " + (i+1) + "." + queryList.get(i));
            }
            ui.printLineBackground();
        }
    }
}
