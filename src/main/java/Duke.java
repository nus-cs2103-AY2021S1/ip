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
            if (input.equals("list")) {
                printDivider();
                printOutput("Here are the tasks in your list:");
                for(int index = 0; index < taskList.size(); index++) {
                    printOutput((index + 1) + ": " + taskList.get(index));
                }
                printDivider();
            } else {
                String[] inputs = input.split("\\s+", 2);
                if (inputs[0].equals("done")) {
                    int index = Integer.parseInt(inputs[1]) - 1;
                    taskList.set(index, taskList.get(index).completeTask());
                    printDivider();
                    printOutput("Nice! I've marked this task as done:");
                    printOutput(taskList.get(index).toString());
                    printDivider();

                } else {
                    if (inputs[0].equals("todo")){
                        taskList.add(new Todo(inputs[1]));
                    } else if (inputs[0].equals("deadline")) {
                        String[] desc = inputs[1].split(" /by ", 2);
                        taskList.add(new Deadline(desc[0], desc[1]));
                    } else {
                        String[] desc = inputs[1].split(" /at ", 2);
                        taskList.add(new Event(desc[0], desc[1]));
                    }
                    printDivider();
                    printOutput("Got it. I've added this task: ");
                    printOutput(taskList.get(taskList.size() -1).toString());
                    printOutput("Now you have " + taskList.size() + " tasks in the list.");
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
