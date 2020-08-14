import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Duke {

    private final String lineSeparator = "***********************";
    private List<Task> taskList = new ArrayList<>();

    public void greet() {
        printMessage("Hi! I'm Duke. What can I do for you?");
    }

    public void printMessage(String msg) {
        System.out.println(lineSeparator);
        System.out.println(msg);
        System.out.println(lineSeparator);
    }

    public void addTask(Task task) {
        taskList.add(task);
        printMessage(String.format("added: %s", task));
    }

    public void completeTask(int taskNumber) {
        Task completedTask = taskList.get(taskNumber - 1).markCompleted();
        taskList.set(taskNumber - 1, completedTask);
        printMessage(String.format("Nice! I've marked this task as done: \n %s", completedTask.toString()));
    }

    public void printList() {
        StringBuilder tasks = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.size(); i++) {
            tasks.append(String.format("%d. %s", i + 1, taskList.get(i)));
            if (i != taskList.size() - 1) {
                tasks.append('\n');
            }
        }
        printMessage(tasks.toString());
    }

    public boolean handleInput(Scanner scanner) {
        String userInput = scanner.next();
        switch (userInput) {
            case "bye":
                printMessage("Bye! Hope to see you soon!");
                return false;
            case "list":
                printList();
                return true;
            case "done":
                int taskNumber = scanner.nextInt();
                completeTask(taskNumber);
                return true;
            case "deadline":
                String command = scanner.nextLine().trim();
                System.out.println(command.charAt(0));
                String[] parts = command.split(" /by ");
                addTask(new Deadline(parts[0], parts[1]));
                return true;
            default:
                addTask(new Todo(userInput + scanner.nextLine()));
                return true;
        }
    }

    public void start() {
        greet();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            isRunning = handleInput(scanner);
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
