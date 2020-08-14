import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(
                "____________________________________________________________\nHello from\n" +
                        logo + "\n What can I do for you?" +
                        "\n____________________________________________________________\n");

        Scanner scanner = new Scanner(System.in);
        DukeList list = new DukeList();

        boolean running = true;
        while (running) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                running = false;
                System.out.println(
                        "____________________________________________________________\n" +
                                "Bye! Hope to see you again soon!" +
                                "\n____________________________________________________________\n");
                scanner.close();
            } else {
                System.out.println(
                        "____________________________________________________________\n" +
                                Duke.processCommand(command, list) +
                                "\n____________________________________________________________\n");
            }
        }

    }

    private static String processCommand(String command, DukeList list) {
        String[] stringArray = command.split(" ");
        List<String> stringList = new ArrayList<>(Arrays.asList(stringArray));
        String com = stringList.remove(0);

        if (com.equals("hello")) {
            return "Hi! I'm Duke! Pleasure to meet you :)";
        }
        if (com.equals("list")) {
            return list.toString();
        } else if (com.equals("done")) {
            if (!stringList.isEmpty()) {
                return list.markDone(Integer.parseInt(stringList.get(0)) - 1);
            } else {
                return "Please choose a task to mark as done, with \"done <task number>\"";
            }
        } else if (com.equals("todo")) {
            if (!stringList.isEmpty()) {
                return list.addItem(new Todo(String.join(" ", stringList)));
            } else {
                return "Please write a task to be done, with \"todo <task>\"";
            }
        } else if (com.equals("deadline")) {
            if (!stringList.isEmpty()) {
                String[] taskAndDate = Duke.getTaskAndDate(stringList);
                return list.addItem(new Deadline(taskAndDate[0], taskAndDate[1]));
            } else {
                return "Please write a deadline, with \"deadline <task> /by <date>\"";
            }
        } else if (com.equals("event")) {
            if (!stringList.isEmpty()) {
                String[] taskAndDate = Duke.getTaskAndDate(stringList);
                return list.addItem(new Event(taskAndDate[0], taskAndDate[1]));
            } else {
                return "Please write an event, with \"event <task> /at <date>\"";
            }
        }
        return "Sorry, I did not understand: " + command;
    }

    private static String[] getTaskAndDate(List<String> description) {
        String[] result = new String[2];
        int dateIndex = -1;
        for (int i = 0; i < description.size(); i++) {
            if (description.get(i).charAt(0) == '/') {
                dateIndex = i;
            }
        }
        if (dateIndex == -1) {
            result[0] = String.join(" ", description.subList(0, description.size()));
            result[1] = "No date set";
        } else {
            result[0] = String.join(" ", description.subList(0, dateIndex));
            result[1] = String.join(" ", description.subList(dateIndex + 1, description.size()));
        }

        return result;
    }
}
