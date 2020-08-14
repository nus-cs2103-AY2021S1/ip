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
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            tasks.append(String.format("%d. %s", i + 1, taskList.get(i)));
            if (i != taskList.size() - 1) {
                tasks.append('\n');
            }
        }
        printMessage(tasks.toString());
    }

    public void start() {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.next();
            if (userInput.equals("bye")) {
                printMessage("Bye! Hope to see you soon!");
                break;
            } else if (userInput.equals("list")) {
                printList();
            } else if (userInput.equals("done")) {
                int taskNumber = scanner.nextInt();
                completeTask(taskNumber);
            } else {
                addTask(new Task(userInput + scanner.nextLine()));
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
