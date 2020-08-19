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
                        System.out.println(duke + "Your To-Do List is empty.");
                    } else {
                        System.out.println(duke + "Here's your To-Do List:");
                        for (Task t : list) {
                            System.out.println(++idx + ". [" + t.getStatusIcon() + "] " + t.getTaskName());
                        }
                    }
                break;
                case "done":
                    System.out.println(duke + "Here's your To-Do List:");
                    for (Task t : list) {
                        System.out.println(++idx + ". [" + t.getStatusIcon() + "] " + t.getTaskName());
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
                        System.out.println("[" + t.getStatusIcon() + "] " + t.getTaskName());
                    }
                break;
                default:
                    Task newT = new Task(userInput);
                    list.add(newT);
                    System.out.println(duke + "I added '" + newT.getTaskName() + "' to your To-Do List");
            }
        }

        System.out.println(duke + "See you soon!");
    }

    private static void startupMsg() {
        System.out.println(logo);
        System.out.println(duke + "Hi I'm Duke!");
        System.out.println(duke + "What can I do for you?");
    }
}
