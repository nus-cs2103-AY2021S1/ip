import main.java.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // this method prints a horizontal line of fixed length
    public static void horiLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
            if (i == length - 1) System.out.println("");
        }
    }


    // Note that all the outputs are formatted with two spaces before.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList();
        // welcome message
        horiLine(60);
        System.out.println("  Hello! I'm IntelliGent!\n  What can I do for you?");
        horiLine(60);
        while (sc.hasNextLine()) {
            String nextInput = sc.nextLine();
            if (nextInput.equals("bye")) {
                horiLine(60);
                System.out.println("  Bye. Hope to see you again soon!");
                horiLine(60);
                sc.close();
                break;
            } else if (nextInput.equals("list")) {
                horiLine(60);
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println("  " + (i+1) + ". " + taskList.get(i));
                }
                horiLine(60);
            } else if (nextInput.contains("done")) {
                horiLine(60);
                String[] commandComponents = nextInput.split(" ");
                int taskIndex = Integer.parseInt(commandComponents[1]) - 1;
                Task toMark = taskList.get(taskIndex);
                toMark.markDone();
                System.out.println("  Nice! I've marked this task as done:");
                System.out.println("    " + toMark.toString());
                horiLine(60);
            } else {
                // adding tasks
                horiLine(60);
                Task taskToAdd = new Task(nextInput);
                taskList.add(taskToAdd);
                System.out.println("  added: " + nextInput);
                horiLine(60);
            }
        }
    }
}
