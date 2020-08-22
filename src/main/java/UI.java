import java.util.Scanner;
public class UI {

    protected String readCommand() {
        Scanner sc = new Scanner(System.in);

        String message = "";

        while (sc.hasNextLine()) {
            message = sc.next();
            message += sc.nextLine();
        }

        return message;
    }

    protected void doneTask(Task task) {
        if (task.getStatus()) {
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

}
