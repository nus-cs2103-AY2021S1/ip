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
            } else if (command.startsWith("done")) {
                int index = Integer.valueOf(command.split(" ")[1]);
                completeTask(index);
            } else if (command.startsWith("todo")){
                String name = command.split(" ", 2)[1];
                addTodo(name);
            } else if (command.startsWith("deadline")) {
                String details = command.split(" ", 2)[1];
                String[] detailArr = details.split(" /by ", 2);
                addDeadline(detailArr[0], detailArr[1]);
            } else if (command.startsWith("event")) {
                String details = command.split(" ", 2)[1];
                String[] detailArr = details.split(" /at ", 2);
                addEvent(detailArr[0], detailArr[1]);
            } else {
                printMessage("Invalid command");
            }

        }
    }

    public static void printMessage(String message) {
        System.out.println("    ______________________________________________________\n" +
                "     " + message + "\n    ______________________________________________________");
    }

    public static void addTodo(String name) {
        Todo todo = new Todo(name);
        addTask(todo);
    }

    public static void addDeadline(String name, String by) {
        Deadline deadline = new Deadline(name, by);
        addTask(deadline);
    }

    public static void addEvent(String name, String at) {
        Event event = new Event(name, at);
        addTask(event);
    }

    public static void addTask(Task task) {
        taskList.add(task);
        int taskLen = taskList.size();
        String message = String.format("Got it. I have added this task:\n       %s\n     Now you have %d %s in the list.",
                task, taskLen, taskLen > 1 ? "tasks" : "task");
        printMessage(message);
    }

    public static void listTasks() {
        String output = "Here are the tasks in your list:\n";
        int taskLen = taskList.size();
        for (int i = 0; i < taskLen; i++) {
            output += String.format("     %d. %s", i + 1, taskList.get(i));
            if (i != taskLen - 1) {
                output += "\n";
            }
        }
        printMessage(output);
    }

    public static void completeTask(int index) {
        taskList.get(index - 1).markAsDone();
        String message = String.format("Nice! I've marked this task as done:\n       %s",
                taskList.get(index - 1));
        printMessage(message);
    }
}
