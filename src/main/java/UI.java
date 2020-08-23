import java.util.ArrayList;
import java.util.Scanner;
public class UI {

    protected String readCommand() {
        Scanner sc = new Scanner(System.in);

        String message = sc.nextLine();

        return message;
    }

    protected void greetUser() {
        String welcome = "Hello I am Duke!\nHow can I help you?\n";
        String instructions = "Leave a single blankspace after each command to " +
                "trigger Duke's response!\n";
        String listOfCommand = "List of Commands = " +
                "event, todo, deadline, delete, done, find, list\n";
        System.out.println(welcome);
        System.out.println(instructions);
        System.out.println(listOfCommand);
    }

    protected void doneTask(Task task) {
        if (!task.getStatus()) {
            System.out.println("\nNice! I have completed this task!");
            System.out.println(" " + task + "\n");
        } else {
            System.out.println("\nThis task has already been completed!\n");
        }
    }

    protected void addTask(TaskList tasks, Task newTask) {
        System.out.println("\nGot it. This task is now added.");
        System.out.println(" " + newTask);
        int tasksLeft = tasks.checkTasksLeft();
        System.out.println("You have " + tasksLeft + " tasks left in your list!\n");
    }

    protected void deleteTask(TaskList tasks, Task deletedTask) {
        System.out.println("\nGot it. Deleting task.....");
        System.out.println(" " + deletedTask);
        System.out.println("You have " + tasks.getTaskList().size()  + " tasks left in your list!\n");
    }

    protected void displayTasks(TaskList taskList) {
        int index = 1;
        System.out.println("\n");
        System.out.println("Here are the tasks in your tasklist:");
        for (Task task: taskList.getTaskList()) {
            System.out.println(index + "." + task);
            index++;
        }
        System.out.println("\n");
    }

    protected void findTask(ArrayList<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            System.out.println("\nNo matching tasks found!\n");
        } else {
            System.out.println("\nHere are the tasks in your list!\n");
            int index = 1;
            for (Task task : foundTasks) {
                System.out.println(index + "." + task);
                index++;
            }
            System.out.println("\n");
        }
    }
}
