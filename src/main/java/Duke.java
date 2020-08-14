import jdk.jfr.Event;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // create scanner object
        String welcomeMessage = "―――― Serina here, what would you like to do?";
        System.out.println(welcomeMessage); // print welcome message
        int index = 0;
        Task[] tasks = new Task[100];

        while (true) {
            String userInput = sc.nextLine();
            String[] splitInput = userInput.split(" ", 2); // split command by the spaces
            String command = splitInput[0];
            String value;
            value = splitInput.length > 1 ? splitInput[1] : "";

            switch (command) {
                case "bye":  // terminate on "bye"
                    System.out.println("――――  Goodbye, call me when you need me.");
                    break;
                case "list":  // list task
                    System.out.println("―――― Your Tasks: ");
                    for (int i = 0; i < tasks.length; i++) {
                        if (tasks[i] == null) {
                            break;
                        } else {
                            int num = i + 1;
                            System.out.println(num + ". " + tasks[i]);
                        }
                    }
                    break;
                case "done":  // mark task as done and print it
                    int taskNum = Integer.parseInt(value) - 1;
                    System.out.println(tasks[taskNum].markAsDone());
                    break;
                case "todo": // add toDoTask
                    tasks[index] = new ToDoTask(value);
                    System.out.println("―――― Received, added the following task:\n" + tasks[index].toString());
                    index += 1; // increment index
                    System.out.println("You now have " + index + " pending tasks.");
                    break;
                case "deadline": // add deadlineTask
                case "event": // add eventTask
                    String[] splitValue;
                    if (command.equals("deadline")) {
                        splitValue = value.split("/by ");
                        tasks[index] = new DeadlineTask(splitValue[0], splitValue[1]);
                    } else {
                        splitValue = value.split("/at ");
                        tasks[index] = new EventTask(splitValue[0], splitValue[1]);
                    }
                    System.out.println("―――― Received, added the following task:\n" + tasks[index].toString());
                    index += 1; // increment index
                    System.out.println("You now have " + index + " pending tasks.");
                    break;
            }
        }
    }
}
