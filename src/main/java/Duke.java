import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    private static String divider = "_________________________________________________________________";
    private static String logo = "             *\n"
            + "      o  o  / \\  o  o\n"
            + "      |\\/ \\/   \\/ \\/|\n"
            + "      |             |\n"
            + "      |ooooooooooooo|\n"
            + " __  _  ____  ____    ____      ____    ___   ____  \n"
            + "|  |/ ||    ||    \\  /    |    |    \\  /   \\ |    \\ \n"
            + "|  ' /  |  | |  _  ||   __|    |  o  )|     ||  o  )\n"
            + "|    \\  |  | |  |  ||  |  |    |     ||  O  ||     |\n"
            + "|     | |  | |  |  ||  |_ |    |  O  ||     ||  O  |\n"
            + "|  .  | |  | |  |  ||     |    |     ||     ||     |\n"
            + "|__|\\_||____||__|__||___,_|    |_____| \\___/ |_____|\n";

    private static List<Task> list = new ArrayList<>();


    private static void addList(Task task) {
        list.add(task);
    }

    private static void showList() {
        System.out.println(divider);
        System.out.println("   Banana! So many tasks?");
        Task task;
        for (int i = 0; i < list.size(); i++) {
            task = list.get(i);
            System.out.println("   " + (i + 1) + ". " + task.toString());
        }
        System.out.println(divider + "\n");
    }

    private static int getListSize() {
        return list.size();
    }

    private static boolean isDoneCommand(String input) {
        if (input.length() < 5) {
            return false;
        } else {
            return input.substring(0, 5).equals("done ");
        }
    }

    private static boolean isValidIndex(String input) {
        String[] stringArray = input.split(" ");
        int index;
        try {
            index = Integer.parseInt(stringArray[1]);
        } catch(Exception e) {
            return false;
        }
        int listSize = list.size();
        return index <= listSize && index > 0;
    }

    private static int getIndex(String input) {
        String[] stringArray = input.split(" ");
        return Integer.parseInt(stringArray[1]) - 1;
    }

    private static boolean isTodo(String input) {
        String[] stringArray = input.split(" ");
        return stringArray[0].equals("todo");
    }

    private static String getTodoDescription(String input) {
        return input.substring(5);
    }

    private static boolean isDeadline(String input) {
        String[] stringArray = input.split(" ");
        return stringArray[0].equals("deadline");
    }

    private static String[] getDeadlineStrings(String input) {
        String[] stringArray = input.split(" /by ");
        stringArray[0] = stringArray[0].substring(9);
        return stringArray;
    }

    private static boolean isEvent(String input) {
        String[] stringArray = input.split(" ");
        return stringArray[0].equals("event");
    }

    private static String[] getEventTimeStrings(String input) {
        String[] stringArray = input.split(" /at ");
        stringArray[0] = stringArray[0].substring(6);
        return stringArray;
    }

    private static void byeMessage() {
        System.out.println(divider);
        System.out.println("   Banana! King Bob is sad to see you go. Farewell my friend!");
        System.out.println(divider);
    }

    private static void addedMessage(Task task) {
        System.out.println(divider);
        System.out.println("   Banana! Banana has been added to your list!");
        System.out.println("      " + task.toString());
        System.out.println("   Now you have " + getListSize() + " banana(s) in your list! Nom nom..");
        System.out.println(divider + "\n");
    }

    private static void doneMessage(Task task) {
        System.out.println(divider);
        System.out.println("   Banana! I've marked this task as done:");
        System.out.println("      " + task.toString());
        System.out.println(divider + "\n");
    }

    private static void errorMessage() {
        System.out.println(divider);
        System.out.println("   Apple Pineapple! Something is wrong with your command...");
        System.out.println(divider + "\n");
    }

    public static void main(String[] args) {

        System.out.println("Bello from the Majestic\n" + logo);
        System.out.println("Banana! What can King Bob do for you?\n" + divider + "\n");

        Scanner scanner = new Scanner(System.in);
        String nextLine = "";

        while (scanner.hasNext()) {
            nextLine = scanner.nextLine();

            if (nextLine.equals("bye")) {
                byeMessage();
                break;
            } else if (nextLine.equals("list")) {
                showList();
            } else if (isDoneCommand(nextLine)) {
                if (isValidIndex(nextLine)) {
                    Task task = list.get(getIndex(nextLine));
                    task.markAsDone();
                    doneMessage(task);
                } else {
                    errorMessage();
                }
            } else {
                Task task;
                task = isTodo(nextLine)
                        ? new Todo(getTodoDescription(nextLine))
                        : isDeadline(nextLine)
                        ? new Deadline(getDeadlineStrings(nextLine)[0], getDeadlineStrings(nextLine)[1])
                        : isEvent(nextLine)
                        ? new Event(getEventTimeStrings(nextLine)[0], getEventTimeStrings(nextLine)[1])
                        : new Task(nextLine);
                addList(task);
                addedMessage(task);
            }
        }
        scanner.close();

    }
}
