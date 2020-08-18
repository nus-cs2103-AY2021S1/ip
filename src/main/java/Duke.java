import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                printMessage("Bye. Hope to see you again soon!");
                return ;
            } else if (command.equals("list")) {
                listTasks();
            }
            else {
                addTask(command);
            }

        }
    }

    public static void printMessage(String message) {
        System.out.println("    ______________________________________________________\n" +
                "     " + message + "\n    ______________________________________________________");
    }

    public static void addTask(String task) {
        Task newTask = new Task(task);
        taskList.add(newTask);
        printMessage("added: " + newTask);
    }

    public static void listTasks() {
        String output = "";
        int taskLen = taskList.size();
        for (int i = 0; i < taskLen; i++) {
            output += String.format("%d. %s", i + 1, taskList.get(i));
            if (i != taskLen - 1) {
                output += "\n     ";
            }
        }
        printMessage(output);
    }
}
