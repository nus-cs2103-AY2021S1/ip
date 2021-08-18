/**
 * List refers to a task list that is used in the Duke program.
 */

import java.util.ArrayList;

public class List {
    private ArrayList<Task> taskList;

    public List() {
        this.taskList = new ArrayList<Task>();
    }

    public void addTask(String userInput) {
        taskList.add(new Task(userInput));
    }

    public void printList() {
        int numb = 1;
        System.out.println("Here are the tasks in your list:");
        for(Task task: taskList) {
            System.out.println(numb + ". " + task.printName());
            numb++;
        }
    }

    public void updateTaskStatus(int userInput) {
        if (userInput > taskList.size()) {
            System.out.println("please enter a number that's between 1 and " + taskList.size());
            return ;
        }
        Task task = taskList.get(userInput - 1);
        task.toggleComplete();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.printName());
    }

    public void addTodo(String userInput) {
        Task task = new Todos(userInput);
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.printName());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void addEvent(String name, String timeline) {
        Task task = new Event(name, timeline);
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.printName());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void addDeadline(String name, String deadline) {
        Task task = new Deadlines(name, deadline);
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.printName());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void removeTask(int userInput) {
        Task task = taskList.get(userInput);
        System.out.println("Noted. I've removed the task:");
        System.out.println(task.printName());
        taskList.remove(userInput);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
