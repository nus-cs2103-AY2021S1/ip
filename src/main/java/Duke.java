import java.util.Scanner;

public class Duke {
    private static String[] tasks = new String[100];

    private static void printResponse(String response) {
        System.out.println("____________________________________________________________");
        System.out.println(response);
        System.out.println("____________________________________________________________");
    }

    private static void handleUserInputs() {
        Scanner sc = new Scanner(System.in);
        int numberOfTasks = 0;
        while (true) {
            String userInput = sc.nextLine();
            switch(userInput) {
                case "list":
                    String numberedList = "";
                    for (int i = 0; i < numberOfTasks; i++) {
                        if (i != 0) {
                            numberedList += "\n";
                        }
                        numberedList += (i + 1) + ". " + tasks[i];
                    }
                    printResponse(numberedList);
                    break;
                case "bye":
                    printResponse("Bye. Hope to see you again soon!");
                    return;
                default:
                    tasks[numberOfTasks] = userInput;
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
