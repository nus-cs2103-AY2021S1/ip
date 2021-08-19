/**
 * List refers to a task list that is used in the Duke program.
 * An List object contains the tasks and several methods that work on the task.
 *
 * @author Dominic Siew Zhen Yu
 */

import java.util.ArrayList;

public class List {
    private ArrayList<Task> taskList;

    /**
     * The constructor for the List class that instantiates a List object.
     */

    public List() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * the addTask() places generic tasks into the list (which was used in level 1-3).
     *
     * @param userInput
     */

    public void addTask(String userInput) {
        taskList.add(new Task(userInput));
    }

    /**
     * the printList() method prints out the String representation of the tasks
     * in the List object.
     */

    public void printList() {
        int numb = 1;
        System.out.println("Here are the tasks in your list:");
        for(Task task: taskList) {
            System.out.println(numb + ". " + task.printName());
            numb++;
        }
    }

    /**
     * the updateTaskStatus() method marks a task to be as completed.
     *
     * @param userInput the rank of the task in a list
     */

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

    /**
     * the addTodo() method adds todos task into the list object
     *
     * @param userInput the name of the todo task
     */

    public void addTodo(String userInput) {
        Task task = new Todos(userInput);
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.printName());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
    /**
     * the addEvent() method adds Events task into the list object.
     *
     * @param name the name of the event
     * @param timeline the period that the event is taking place
     */
    public void addEvent(String name, String timeline) {
        Task task = new Event(name, timeline);
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.printName());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
    /**
     * the addDeadline() method adds Events task into the list object.
     *
     * @param name the name of the deadline
     * @param deadline the deadline of the deadline task
     */
    public void addDeadline(String name, String deadline) {
        Task task = new Deadlines(name, deadline);
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.printName());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
    /**
     * the removeTask() method removes the task from the list object.
     *
     * @param userInput the rank of the task in a list
     */
    public void removeTask(int userInput) {
        Task task = taskList.get(userInput);
        System.out.println("Noted. I've removed the task:");
        System.out.println(task.printName());
        taskList.remove(userInput);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
