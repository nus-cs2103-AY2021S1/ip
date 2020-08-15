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
            if (message.equals("Done") || message.equals("done")) {
                int taskIndex;
                try {
                    taskIndex = Integer.parseInt(sc.next());
                }
                catch (NumberFormatException e) {
                    taskIndex = 0;
                }
                if (taskIndex <= 0 || taskIndex > newBot.taskList.size()) {
                    System.out.println("\nNo such Task\n");
                } else {
                    newBot.markDone(newBot.taskList.get(taskIndex - 1));
                }
            } else {
                message += sc.nextLine();
                if (message.equals("bye")) {
                    System.out.println("\nBye! Have a nice day!\n");
                    break;
                } else if (message.equals("list")) {
                    if (newBot.taskList.isEmpty()) {
                        System.out.println("\nThere are currently no messages stored!\n");
                    } else {
                        newBot.displayMessages();
                    }
                } else {
                    newBot.addMessages(message);
                }
            }
        }
    }

    // Method to add new messages to the bot
    private void addMessages(String message) {
        Task newTask = new Task(message);
        this.taskList.add(newTask);
        System.out.println("\nadded: " + message + "\n");
    }

    // Method to display all messages
    private void displayMessages() {
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
            System.out.println(" " + completedTask + "\n");
            int indexOfTask = this.taskList.indexOf(completedTask);
            this.taskList.get(indexOfTask).markAsDone();
        } else {
            System.out.println("\nThis task has already been completed!\n");
        }
    }

    public static void main(String[] args) {
        startBot();
    }
}