package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n"
                + "~ \n Hello I'm the Terminator \n What can I do for you? \n~");
    }

    public void showLoadingError() {
        System.out.println("~ \n ERROR DETECTED! UNABLE TO LOAD PROGRAM. \n SYSTEM SHUTTING DOWN");
    }

    public void showTaskList(ArrayList<Task> taskList) {
        System.out.println("~ \n Here are targets in your kill list: ");
        if (!taskList.isEmpty()) {
            for (int i = 0; i < taskList.size(); i++) {
                int count = i + 1;
                System.out.println(String.format("   %d. ", count) + taskList.get(i).toString());
            }
        }
        System.out.println("\n~ ");
    }

    public String readCommand() {
        String input = sc.nextLine().trim();
        return input;
    }

}
