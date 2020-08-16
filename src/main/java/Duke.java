import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    static List<Task> taskList = new ArrayList<>();

    static String wrapMessage(String message) {
        String line = "----------------------------------------------";
        return line + "\n " + message + "\n" + line;
    }

    static void greet() {
        String greeting = "Hello! I'm Duke\n What can I do for you?";
        System.out.println(wrapMessage(greeting));
    }

    static void exit() {
        String byeMessage = "Bye. Hope to see you again soon!";
        System.out.println(wrapMessage(byeMessage));
    }

    static void addTask(String desc) {
        taskList.add(new Task(desc));
        System.out.println(wrapMessage("added: " + desc));
    }

    static String formatTask(int num) {
        String lineBreak = num != taskList.size() - 1 ? "\n " : "";
        return (num + 1) + "." + taskList.get(num) + lineBreak;
    }

    static void list() {
        String list = "Here are the tasks in your list: \n  ";
        for (int i = 0; i < taskList.size(); i++) {
            list += formatTask(i);
        }
        System.out.println(wrapMessage(list));
    }

    static void completeTask(int num) {
        Task task = taskList.get(num - 1);
        task.completeTask();
        String message = "Nice! I've marked this task as done: \n  " + task;
        System.out.println(wrapMessage(message));
    }

    static void readInput() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String [] commands = input.split(" ");
            switch (commands[0]) {
                case "list":
                    list();
                    break;
                case "bye":
                    exit();
                    break;
                case "done":
                    completeTask(Integer.valueOf(commands[1]));
                    break;
                default:
                    addTask(input);
            }
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        greet();
        readInput();
    }
}

