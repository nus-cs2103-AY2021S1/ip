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
                    System.out.println(duke + "Here's your Task List:");
                    for (Task t : list) {
                        System.out.println(++idx + ". " + t.toString());
                    }
                    System.out.println("Choose the tasks to be marked as 'Done'");
                    System.out.print(cmd);
                    int[] tasksArray = Arrays.stream(sc.nextLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

                    System.out.println(duke + "Nice! I've marked the following as done:");
                    for (int index : tasksArray) {
                        Task t = list.get(index - 1);
                        t.setDone();
                        System.out.println(t.toString());
                    }
                break;
                case "todo":
                    System.out.println(duke + "Enter task details: ");
                    System.out.print(cmd);
                    Task toDo = new Todo(sc.nextLine());
                    list.add(toDo);
                    System.out.println(duke + "I've added '" + toDo.getTaskName() + "' to your Task List");
                    break;
                case "deadline":
                    System.out.println(duke + "Enter task details: ");
                    System.out.print(cmd);
                    s = sc.nextLine().split(" /by ");
                    Task deadLine = new Deadline(s[0], s[1]);
                    list.add(deadLine);
                    System.out.println(duke + "I've added '" + deadLine.getTaskName() + "' to your Task List");
                    break;
                case "event":
                    System.out.println(duke + "Enter task details: ");
                    System.out.print(cmd);
                    s = sc.nextLine().split(" /at ");
                    Task event = new Event(s[0], s[1]);
                    list.add(event);
                    System.out.println(duke + "I've added '" + event.getTaskName() + "' to your Task List");
                    break;
                case "help":
                    showHelp();
                    break;
                default:
                    System.out.println(duke + "Unrecognised Command :(, type 'help' for available commands");
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
        String msg = "Available Commands: \n" + "'todo' \n" + "'deadline' \n" + "'event' \n" + "'list' \n" +
                "'bye'";
        System.out.println(msg);
    }
}
