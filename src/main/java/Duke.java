import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    public static String duke = "Duke> ";
    public static String cmd = ">> ";
    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String userInput;
        String[] s;
        boolean validInput = true;
        ArrayList<Task> list = new ArrayList<>();

        startupMsg();

        while (true) {
            System.out.print(cmd);
            userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            }

            int idx = 0;
            switch(userInput) {
                case "list":
                    if (list.isEmpty()) {
                        System.out.println(duke + "Your Task List is empty.");
                    } else {
                        System.out.println(duke + "Here's your Task List:");
                        for (Task t : list) {
                            System.out.println(++idx + ". " + t.toString());
                        }
                    }
                break;
                case "done":
                    if (list.isEmpty()) {
                        System.out.println(duke + "Your Task List is empty. There's nothing to be done.");
                    } else {
                        System.out.println(duke + "Here's your Task List:");
                        for (Task t : list) {
                            System.out.println(++idx + ". " + t.toString());
                        }
                        do {
                            System.out.println("Choose the tasks to be marked as 'Done'");
                            System.out.print(cmd);
                            try {
                                userInput = sc.nextLine();
                                if (userInput.isBlank()) {
                                    validInput = false;
                                    throw new DukeException("Yo! Enter the task number(s) to be marked as 'Done'");
                                }
                                int[] tasksArray = Arrays.stream(userInput.split(" "))
                                        .mapToInt(Integer::parseInt)
                                        .toArray();

                                System.out.println(duke + "Nice! I've marked the following as done:");
                                for (int index : tasksArray) {
                                    Task t = list.get(index - 1);
                                    t.setDone();
                                    System.out.println(t.toString());
                                }
                                validInput = true;
                            } catch (DukeException e) {
                                System.out.println(duke + e.getMessage());
                            }
                        } while (!validInput);
                    }
                break;
                case "todo":
                    do {
                        System.out.println(duke + "Enter task details:");
                        System.out.print(cmd);
                        try {
                            userInput = sc.nextLine();
                            if (userInput.equals("")) {
                                validInput = false;
                                throw new DukeException("Yo! Task details are missing.");
                            }
                            Task toDo = new Todo(userInput);
                            list.add(toDo);
                            System.out.println(duke + "I've added '" + toDo.getTaskName() + "' to your Task List");
                            validInput = true;
                        } catch (DukeException e) {
                            System.out.println(duke + e.getMessage());
                        }
                    } while (!validInput);
                    break;
                case "deadline":
                    do {
                        System.out.println(duke + "Enter task details:");
                        System.out.print(cmd);
                        try {
                            userInput = sc.nextLine();
                            if (!userInput.contains("/by")) {
                                validInput = false;
                                throw new DukeException("Yo! Command Syntax Error. '<Task Details> /by <Deadline>'");
                            }
                            s = userInput.split(" /by ");
                            if (s.length <= 1) {
                                validInput = false;
                                throw new DukeException("Yo! Task details/deadline are missing.");
                            }
                            Task deadLine = new Deadline(s[0], s[1]);
                            list.add(deadLine);
                            System.out.println(duke + "I've added '" + deadLine.getTaskName() + "' to your Task List");
                            validInput = true;

                        } catch (DukeException e) {
                            System.out.println(duke + e.getMessage());
                        }
                    } while (!validInput);
                    break;
                case "event":
                    do {
                        System.out.println(duke + "Enter Event details:");
                        System.out.print(cmd);
                        try {
                            userInput = sc.nextLine();
                            if (!userInput.contains("/at")) {
                                validInput = false;
                                throw new DukeException("Yo! Command Syntax Error. '<Event Details> /at <Event Time>'");
                            }
                            s = userInput.split(" /at ");
                            if (s.length <= 1) {
                                validInput = false;
                                throw new DukeException("Yo! Event details/time are missing.");
                            }
                            Task event = new Event(s[0], s[1]);
                            list.add(event);
                            System.out.println(duke + "I've added '" + event.getTaskName() + "' to your Task List");
                            validInput = true;

                        } catch (DukeException e) {
                            System.out.println(duke + e.getMessage());
                        }
                    } while (!validInput);
                    break;
                case "help":
                    showHelp();
                    break;
                default:
                    try {
                        throw new DukeException("Unrecognised Command :(, type 'help' for available commands");
                    } catch (DukeException e) {
                        System.out.println(duke + e.getMessage());
                    }
            }
        }

        System.out.println(duke + "See you soon!");
    }

    private static void startupMsg() {
        System.out.println(logo);
        System.out.println(duke + "Hi I'm Duke!");
        System.out.println(duke + "What can I do for you?");
    }

    private static void showHelp() {
        String s = "Here's what I can do:\n";
        String msg = "Available Commands: \n" + "'todo' \n" + "'deadline' \n" + "'event' \n" + "'list' \n" +
                "'bye'";
        System.out.println(s + msg);
    }
}
