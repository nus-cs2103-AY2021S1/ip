import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    static List<String> taskList = new ArrayList<>();

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

    static void addTask(String task) {
        taskList.add(task);
        System.out.println(wrapMessage("added: " + task));
    }

    static String formatTask(int num) {
        String lineBreak = num != taskList.size() - 1 ? "\n " : "";
        return (num + 1) + ". " + taskList.get(num) + lineBreak;
    }

    static void list() {
        String list = "";
        for (int i = 0; i < taskList.size(); i++) {
            list += formatTask(i);
        }
        System.out.println(wrapMessage(list));
    }

    static void readInput() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            switch (input) {
                case "list":
                    list();
                    break;
                case "bye":
                    exit();
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

