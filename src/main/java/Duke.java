package main.java;
import java.util.Scanner;

public class Duke {

    public static void echoInput() {
        Scanner readInput = new Scanner(System.in);
        String currentWord = readInput.next();
        while (!currentWord.equals("bye")) {
            System.out.println(currentWord);
            currentWord = readInput.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static String[] split(String command) {
        String[] array = command.split(" ");
        return array;
    }

    public static String join(String[] stringArray, int index) {
        String result = "";
        for (int i = index; i < stringArray.length; i++) {
            result += stringArray[i] + (i == stringArray.length - 1 ? "" : " ");
        }
        return result;
    }

    public static void run() {
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);
        String currentWord = scanner.nextLine();
        while (!currentWord.equals("bye")) {
            if (currentWord.equals("list")) {
                System.out.println(taskList.toString());
            } else {
                String command = split(currentWord)[0];
                if (command.equals("done")) {
                    int taskNumber = Integer.parseInt(join(split(currentWord), 1));
                    taskList.getTask(taskNumber - 1).markAsDone();
                } else {
                    String description = join(split(currentWord), 0);
                    Task newTask = new Task(description);
                    taskList.addList(newTask);
                }
            }
            currentWord = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        run();
    }
}
