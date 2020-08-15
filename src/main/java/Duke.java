import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];

    private static void printResponse(String response) {
        System.out.println("____________________________________________________________");
        System.out.println(response);
        System.out.println("____________________________________________________________");
    }

    private static void handleUserInputs() {
        Scanner sc = new Scanner(System.in);
        int numberOfTasks = 0;
        while (true) {
            String userInput = sc.nextLine().trim();
            if (userInput.equals("list")) {
                String numberedList = "Here are the tasks in your list:";
                for (int i = 0; i < numberOfTasks; i++) {
                    numberedList += "\n" + (i + 1) + "." + tasks[i];
                }
                printResponse(numberedList);
            } else if (userInput.startsWith("done")) {
                String[] splitInput = userInput.split(" ", 2);
                int taskNumber = Integer.parseInt(splitInput[1]);
                tasks[taskNumber - 1].markAsDone();
                printResponse("Nice! I've marked this task as done:\n" + tasks[taskNumber - 1]);
            } else if (userInput.equals("bye")) {
                printResponse("Bye. Hope to see you again soon!");
                return;
            } else {
                tasks[numberOfTasks] = new Task(userInput);
                numberOfTasks++;
                printResponse("added: " + userInput);
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printResponse("Hello! I'm Duke\nWhat can I do for you?");
        handleUserInputs();
    }
}
