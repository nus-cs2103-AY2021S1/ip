import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        showGreeting();
        String[] inputArr = getInputArr();
        String command = inputArr[0];
        while (!command.equals("bye")) {
            switch (command) {
            case "list":
                showList();
                break;
            case "done":
                done(inputArr);
                break;
            case "deadline":
                addTask("deadline", inputArr);
                break;
            case "event":
                addTask("event", inputArr);
                break;
            }
            inputArr = getInputArr();
            command = inputArr[0];
        }
        showGoodbye();
    }

    public static String[] getInputArr() {
        String input = scanner.nextLine();
        return input.split("\\s+");
    }

    public static void showGreeting() {
        String message = "      Eh what's up\n"
                + "      What do you want?";
        System.out.println(wrapMessage(message));
    }

    public static void showGoodbye() {
        String message = "     Alright I'll see you around!";
        System.out.println(wrapMessage(message));
    }

    public static void showList() {
        String message = "    Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            message += "    "
                    + (i + 1)
                    + ". "
                    + task
                    +"\n";
        }
        System.out.println(wrapMessage(message));
    }

    public static void addTask(String type, String[] inputArr) {
        String desc = "";
        String dateTime = "";
        Task task;

        int i = 1;
        System.out.println("inputArr.length = " + inputArr.length);
        while (!inputArr[i].contains("/") || i < inputArr.length - 1) {
            desc = desc + inputArr[i] + " ";
            i++;
            System.out.println("i = " + i);
        }
        desc = desc.substring(0, desc.length() - 1);

        if (type.equals("deadline") || type.equals("event")) {
            i++;
            while (i < inputArr.length) {
                dateTime = dateTime + inputArr[i] + " ";
                i++;
            }
            dateTime = dateTime.substring(0, dateTime.length() - 1);
        }

        if (type.equals("deadline")) {
            task = new Deadline(desc, dateTime);
        } else if (type.equals("event")) {
            task = new Event(desc, dateTime);
        }

        taskList.add(task);

        String message = "    Got it. I've added this task:\n"
                + "      "
                + task
                + "\n"
                + "    Now you have "
                + taskList.size()
                + " tasks in the list.";
        System.out.println(wrapMessage(message));
    }

    public static void done(String[] inputArr) {
        String lastChar = inputArr[inputArr.length - 1];
        int i = Integer.parseInt(lastChar);
        i -= 1;
        Task task = taskList.get(i);
        task.markAsDone();
        String message = "      Nice! I've marked this task as done:\n"
                + "      "
                + task;
        System.out.println(wrapMessage(message));
    }

    public static String wrapMessage(String message) {
        if (message.endsWith("\n")) {
            message = message.substring(0, message.length() - 1);
        } // If the message ends with a newline, remove the newline

        String line = "    ____________________________________________________________\n";
        return line
                + message
                + "\n"
                + line;
    }
}