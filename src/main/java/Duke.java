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
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskListSize; i++) {
            int taskNumber = i + 1;
            String entry = String.format("%d. %s", taskNumber, taskList[i]);
            System.out.println(entry);
        }
    }

    private void addTask(Task task) {
        taskList[taskListSize] = task;
        taskListSize++;
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        String taskWord = (taskListSize > 1 ? "tasks" : "task");
        System.out.println(String.format("Now you have %d %s in the list.", taskListSize, taskWord));
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
        String[] inputBreakdown = input.split(" ", 2);
        String command = inputBreakdown[0];
        switch (command) {
            case ("list"):
                listAllTasks();
                break;
            case ("done"):
                int taskNumber = Integer.parseInt(inputBreakdown[1]);
                try {
                    markTaskAsDone(taskNumber);
                } catch (Exception e) {

                }
                break;
            case ("todo"):
                Todo todo = new Todo(inputBreakdown[1]);
                addTask(todo);
                break;
            case ("deadline"):
                try {
                    String[] remainingInput = inputBreakdown[1].split(" /by ", 2);
                    String description = remainingInput[0];
                    String by = remainingInput[1];
                    Deadline deadline = new Deadline(description, by);
                    addTask(deadline);
                } catch (Exception e) {

                }
                break;
            case ("event"):
                try {
                    String[] remainingInput = inputBreakdown[1].split(" /at ", 2);
                    String description = remainingInput[0];
                    String at = remainingInput[1];
                    Event event = new Event(description, at);
                    addTask(event);
                } catch (Exception e) {

                }
                break;
            case ("bye"):
                exit();
                break;
            default:
                Task task = new Task(input);
                addTask(task);
        }
        listenForCommands();
    }
}