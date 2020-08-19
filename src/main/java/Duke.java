import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static void printDivider() {
        System.out.println("          ____________________________________________________________");
    }
    private static void printOutput(String output) {
        System.out.println("          " + output);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printDivider();
        printOutput("Hi! I'm Duke");
        printOutput("What can I do for you?");
        printDivider();

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            if(input.equals("list")) {
                int index = 1;
                printDivider();
                printOutput("Here are the tasks in your list:");
                for(index = 0; index < taskList.size(); index++) {
                    printOutput((index + 1) + ": " + taskList.get(index));
                }
                printDivider();
            } else {
                String[] inputs = input.split("\\s+");
                if(inputs[0].equals("done")) {
                    int index = Integer.valueOf(inputs[1]) - 1;
                    taskList.set(index, taskList.get(index).completeTask());
                    printDivider();
                    printOutput("Nice! I've marked this task as done:");
                    printOutput(taskList.get(index).toString());
                    printDivider();

                } else {
                    Task task = new Task(input);
                    taskList.add(task);
                    printDivider();
                    printOutput("added: " + input);
                    printDivider();
                }
            }
            input = sc.nextLine();
        }

        printDivider();
        printOutput("Bye. See you again next time!" );
        printDivider();
    }
}
