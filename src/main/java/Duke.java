package main.java;
import java.util.Scanner;

public class Duke {
    private String[] taskList = new String[100];
    private int totalTask = 0;

    public static void echoInput() {
        Scanner readInput = new Scanner(System.in);
        String currentWord = readInput.next();
        while (!currentWord.equals("bye")) {
            System.out.println(currentWord);
            currentWord = readInput.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    // method to add task and print the corresponding response
    public void addTask(String task, int position) {
        taskList[position] = task;
        System.out.println("added: " + task);
    }

    // method to print out the entire list
    public void listing() {
        for (int i = 0; i < totalTask; i++) {
            int taskNumber = i + 1;
            String task = taskList[i];
            System.out.println(taskNumber + ". " + task);
        }
    }

    public static void run() {
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);
        String currentWord = scanner.nextLine();
        while (!currentWord.equals("bye")) {
            // listing
            if (currentWord.equals("list")) {
                duke.listing();
            } else {
                duke.addTask(currentWord, duke.totalTask); // add task to tasklist
                duke.totalTask += 1;
            }
            currentWord = scanner.nextLine(); // update next word
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
