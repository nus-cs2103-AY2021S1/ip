import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String formatReply(String text) {
        String line = "\t_______________________________________________________________";
        return line + "\n\t\t" + text.replaceAll("\\n", "\n\t\t") + "\n" + line;
    }

    public static void main(String[] args) {
        System.out.println(formatReply("Hello! I'm Duke\nWhat can I do for you?"));
        ArrayList<String> taskList = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        while (true) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                break;
            } else {
                taskList.add(command);
                System.out.println(formatReply("added: " + command));
            }
        }
        System.out.println(formatReply("Bye. Hope to see you again soon!"));
    }
}
