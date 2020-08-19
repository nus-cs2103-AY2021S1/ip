import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Task> taskList = new ArrayList<>();
    final static String line = "    ____________________________________________________________\n";

    public static void main(String[] args) {
        greeting();
        String[] inputArr = getInputArr();
        String command = inputArr[0];

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                listCommand();
            } else if (command.equals("done")) {
                doneCommand(inputArr);
            } else if (command.equals("deadline")) {
                addDeadline(inputArr);
            }
            inputArr = getInputArr();
            command = inputArr[0];
        }
        goodbye();
    }

    public static String[] getInputArr() {
        String input = scanner.nextLine();
        return input.split("\\s+");
    }

    public static void greeting() {
        String message = line
                + "      Eh what's up\n"
                + "      What do you want?\n"
                + line;
        System.out.println(message);
    }

    public static void goodbye() {
        String message = line
                + "     Alright I'll see you around!\n"
                + line;
        System.out.println(message);
    }

    public static void listCommand() {
        String message;
        message = line;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            message = message
                    + "      "
                    + (i + 1)
                    + ". "
                    + task
                    + "\n";
        }
        message = message + line;
        System.out.println(message);
    }

    public static void addDeadline(String[] inputArr) {
        String desc = "";
        String by = "";

        int i = 1;
        while (!inputArr[i].contains("/")) {
            desc = desc + inputArr[i] + " ";
            i++;
        }
        desc = desc.substring(0, desc.length() - 1);
        i++;
        while (i < inputArr.length) {
            by = by + inputArr[i] + " ";
            i++;
        }
        by = by.substring(0, by.length() - 1);

        Deadline deadline = new Deadline(desc, by);
        taskList.add(new Deadline(desc, by));

        String message = line
                + "      added: "
                + deadline
                + "\n"
                + line;
        System.out.println(message);
    }

    public static void doneCommand(String[] inputArr) {
        String lastChar = inputArr[inputArr.length - 1];
        int i = Integer.parseInt(lastChar);
        i -= 1;
        Task task = taskList.get(i);
        task.markAsDone();
        String message = line
                + "      Nice! I've marked this task as done:\n"
                + "      "
                + task
                + "\n"
                + line;
        System.out.println(message);
    }
}