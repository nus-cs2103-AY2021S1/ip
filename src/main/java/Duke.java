import java.util.Scanner;

public class Duke {
    private Scanner scanner;

    private static final int MAXSIZE = 100;
    private Task[] taskList;
    private int taskListSize;

    private String logo, greetingMessage, exitMessage;

    public void initialise() {
        scanner = new Scanner(System.in);
        taskList = new Task[MAXSIZE];
        taskListSize = 0;
        logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        greetingMessage = "Hello! My name is Duke.\n" + "What can I do for you?";
        exitMessage = "Bye. Hope to see you again soon!";
        greet();
        listenForCommands();
    }

    private void greet() {
        System.out.println(logo);
        System.out.println(greetingMessage);
    }

    private void listAllTasks() {
        for (int i = 0; i < taskListSize; i++) {
            int taskNumber = i + 1;
            String entry = String.format("%d. %s", taskNumber, taskList[i]);
            System.out.println(entry);
        }
    }

    private void addToTaskList(String description) {
        taskList[taskListSize] = new Task(description);
        taskListSize++;
        System.out.println("added: " + description);
    }

    private void markTaskAsDone(int taskNumber) {
        int index = taskNumber - 1;
        Task task = taskList[index];
        task.markAsDone();
        System.out.println("Nice! I have marked this task as done:");
        System.out.println(task);
    }

    private void exit() {
        System.out.println(exitMessage);
        System.exit(0);
    }

    private void listenForCommands() {
        String input = scanner.nextLine();
        String[] inputBreakdown = input.split(" ");
        String command = inputBreakdown[0];
        switch (command) {
            case ("list"):
                listAllTasks();
                break;
            case ("done"):
                int taskNumber = Integer.parseInt(inputBreakdown[1]);
                markTaskAsDone(taskNumber);
                break;
            case ("bye"):
                exit();
                break;
            default:
                addToTaskList(input);
        }
        listenForCommands();
    }
}