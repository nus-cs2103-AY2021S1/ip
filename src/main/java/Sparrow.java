import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;

public class Sparrow {
    public static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (handle(command)) {
            command = sc.nextLine();
        }
        sc.close();
    }

    public static boolean handle(String command) {
        String[] commandArr = command.trim().split("\\s+");
        switch (commandArr[0]) {
            case "bye":
                reply("Bye. Hope t' see ye again soon!");
                return false;
            case "list":
                displayList();
                break;
            case "done":
                try {
                    markAsDone(commandArr[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please enter a task number after \"done\".");
                } finally {
                    break;
                }
            default:
                add(command);
                break;
        }
        return true;
    }

    public static void greet() {
        String welcome = "  _  _ _   ___ _                    \n" +
                " | || (_) |_ _( )_ __               \n" +
                " | __ | |  | ||/| '  \\              \n" +
                " |_||_|_| |___| |_|_|_|             \n" +
                " / __|_ __  __ _ _ _ _ _ _____ __ __\n" +
                " \\__ \\ '_ \\/ _` | '_| '_/ _ \\ V  V /\n" +
                " |___/ .__/\\__,_|_| |_| \\___/\\_/\\_/ \n" +
                "     |_|                            ";
        System.out.println(welcome);
        reply("How can I help ye?");
    }

    public static void reply(String message) {
        System.out.println("    ________________________________________");
        Scanner sc = new Scanner(message);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println("      " + line);
        }
        System.out.println("    ________________________________________");
        sc.close();
    }

    public static void add(String data) {
        taskList.add(new Task(data));
        reply("added: " + data);
    }

    public static void displayList() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.size(); i++) {
            String temp = String.format("%d. %s\n", i+1, taskList.get(i));
            sb.append(temp);
        }
        reply(sb.toString());
    }

    public static void markAsDone(String taskNum) {
        try {
            int taskNumber = Integer.parseInt(taskNum);
            System.out.println(taskNumber);
            if (taskNumber <= taskList.size()) {
                taskList.get(taskNumber - 1).markAsDone();
                reply("Jolly riddance! I've marked this task as done:\n" + taskList.get(taskNumber - 1));
            } else {
                System.out.println("Please enter a valid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task number.");
        }

    }

}
