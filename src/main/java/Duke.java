import javax.management.remote.JMXServerErrorException;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    private ArrayList<Task> taskList;

    // Constructor for the bot
    public Duke(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    // Method to intiate the bot
    private static void startBot() {
        String welcome = "Hello I am Duke!\nHow can I help you?\n";
        System.out.println(welcome);

        Duke newBot = new Duke(new ArrayList<Task>());

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String message = sc.next();

            if (message.equals("done")) {
                int taskIndex;
                try {
                    taskIndex = Integer.parseInt(sc.next());
                } catch (NumberFormatException e) {
                    taskIndex = 0;
                }
                if (taskIndex <= 0 || taskIndex > newBot.taskList.size()) {
                    System.out.println("\nNo such Task\n");
                } else {
                    newBot.markDone(newBot.taskList.get(taskIndex - 1));
                }
            } else if (message.equals("todo")) {
                message += sc.nextLine();
                Todo newTodo = new Todo(message);
                newBot.addTask(newTodo);
            } else if (message.equals("event")) {
                message += sc.nextLine();
                String at = newBot.getDateForTask(message);
                String description = newBot.getDescriptionForTask(message);
                Event newEvent = new Event(description, at);
                newBot.addTask(newEvent);
            } else if (message.equals("deadline")) {
                message += sc.nextLine();
                String by = newBot.getDateForTask(message);
                String description = newBot.getDescriptionForTask(message);
                Deadline newDeadline = new Deadline(description, by);
                newBot.addTask(newDeadline);
            } else if (message.equals("bye")) {
                System.out.println("\nBye! Have a nice day!\n");
                break;
            } else if (message.equals("list")) {
                if (newBot.taskList.isEmpty()) {
                    System.out.println("\nThere are currently no messages stored!\n");
                } else {
                    newBot.displayTasks();
                }
            } else {
                System.out.println("Unrecognised Command");
            }
        }
    }

    // Method to display all messages
    private void displayTasks() {
        int index = 1;
        System.out.println("\n");
        System.out.println("Here are the tasks in your tasklist:");
        for (Task task: this.taskList) {
            System.out.println(index + "." + task);
            index++;
        }
        System.out.println("\n");
    }

    // Method to mark task as done
    private void markDone(Task completedTask) {
        if (!completedTask.getStatus()) {
            System.out.println("\nNice! I have completed this task!");
            completedTask.markAsDone();
            System.out.println(" " + completedTask + "\n");
            int indexOfTask = this.taskList.indexOf(completedTask);
            this.taskList.get(indexOfTask).markAsDone();
        } else {
            System.out.println("\nThis task has already been completed!\n");
        }
    }

    // Method to add an impending task to the bot's task list
    private void addTask(Task newTask) {
        this.taskList.add(newTask);
        System.out.println("\nGot it. This task is now added.");
        System.out.println(" " + newTask);
        int tasksLeft = checkTasksLeft();
        System.out.println("You have " + tasksLeft + " tasks left in your list!\n");
    }

    // Method to obtain the duedate or deadline for an event or deadline task
    private String getDateForTask(String task) {
        return task.split("/")[1];
    }

    // Method to obtain the description of a event or deadline task
    private String getDescriptionForTask(String task) {
        return task.split("/")[0];
    }

    // Method to check how many tasks are not completed
    private int checkTasksLeft() {
        int index = 0;
        for (Task task: this.taskList) {
            if (!task.getStatus()) {
                index++;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        startBot();
    }
}