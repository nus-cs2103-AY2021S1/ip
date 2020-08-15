import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String formatReply(String text) {
        String line = "\t_______________________________________________________________";
        return line + "\n\t\t" + text.replaceAll("\\n", "\n\t\t") + "\n" + line;
    }

    private static String listTasks(ArrayList<String> tasks) {
        String listOfTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks = listOfTasks + (i + 1) + ". " + tasks.get(i)
                    + (i == tasks.size() - 1 ? "" : "\n");
        }
        return listOfTasks;
    }
    public static void main(String[] args) {
        System.out.println(formatReply("Hello! I'm Duke\nWhat can I do for you?"));
        ArrayList<String> taskList = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        while (true) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println(formatReply(listTasks(taskList)));
            } else {
                taskList.add(command);
                System.out.println(formatReply("added: " + command));
            }
        }
        System.out.println(formatReply("Bye. Hope to see you again soon!"));
    }
}
