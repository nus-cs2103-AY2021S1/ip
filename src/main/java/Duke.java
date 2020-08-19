import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static String messageFormatter(String msg) {
//      Add indentation for new lines
        msg = msg.replace("\n", "\n    ");

        return ("    ____________________________________________________________\n"
                + "    " + msg + "\n"
                + "    ____________________________________________________________\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int taskIndex = 0;

        System.out.printf(messageFormatter(
                "Hello! I'm Duke\n" +
                "What can I do for you?"));

        while (scanner.hasNext()) {
            String msg = scanner.nextLine();
            if (msg.equals("bye")) {
                System.out.printf(messageFormatter("Bye. Hope to see you again soon!"));
                break;
            } else if (msg.equals("list")) {
                String listMessage = "";
                for (int i = 0; i < taskIndex; i++) {
                    listMessage += (i+1) + ". " + taskList[i].toString();
                    // If is not last object, add a new line at the end of the item
                    if (i != taskIndex - 1) {
                        listMessage += "\n";
                    }
                }
                System.out.println(messageFormatter(listMessage));

//            Check if message is in the format of "done %d"
            } else {
                Task newTask = new Task(msg, taskIndex);
                taskList[taskIndex] = newTask;
                taskIndex++;
                System.out.printf(messageFormatter("added: " + msg));
            }
        }
    }
}
