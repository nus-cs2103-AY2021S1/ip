import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Clara! :D How may I help you? :)");

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        while (sc.hasNext()) {
            String command = sc.nextLine();
            String[] splitCommand = command.split(" ");
            String keyword = splitCommand[0];

            switch(keyword) {
                case "bye":
                    System.out.println("\tBye! Have a great day and hope to see you soon! :D");
                    break;
                case "list":
                    if (tasks.size() > 0) {
                        System.out.println("These are the tasks in your list. Jiayous! :)");
                    } else {
                        System.out.println("You have no task in your list. :D");
                    }

                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(String.format("\t%d. %s", i+1, tasks.get(i).toString()));
                    }
                    break;
                case "done":
                    int index = Integer.parseInt(splitCommand[1])-1;
                    Task updatedTask = new Task(tasks.get(index).description, true);
                    tasks.set(index, updatedTask);
                    System.out.println("Nice! I've marked this task as done: \n\t" + updatedTask);
                    break;
                default:
                    tasks.add(new Task(command, false));
                    System.out.println("\tadded: " + command);
            }

            if (command.equals("bye")) {
                break;
            }
        }


    }
}
