import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    public static String divider = "_________________________________________________________________";
    public static String logo = "             *\n"
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

    public static List<Task> list = new ArrayList<>();

    public static void byeMessage() {
        System.out.println(divider);
        System.out.println("   Banana! King Bob is sad to see you go. Farewell my friend!");
        System.out.println(divider);
    }

    public static void addedMessage(String input) {
        System.out.println(divider);
        System.out.println("   " + "added: " + input);
        System.out.println(divider + "\n");
    }

    public static void doneMessage(Task task) {
        System.out.println(divider);
        System.out.println("   Banana! I've marked this task as done:");
        System.out.println("      " + "[" + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println(divider + "\n");
    }

    public static void addList(String input) {
        list.add(new Task(input));
    }

    public static void showList() {
        System.out.println(divider);
        System.out.println("   Banana! So many tasks?");
        Task task;
        for (int i = 0; i < list.size(); i++) {
            task = list.get(i);
            System.out.println("   " + (i + 1) + ".[" + task.getStatusIcon() + "] " + task.getDescription());
        }
        System.out.println(divider + "\n");
    }

    public static boolean isDoneCommand(String input) {
        if (input.length() < 5) {
            return false;
        } else {
            return input.substring(0, 5).equals("done ");
        }
    }

    public static boolean isValidIndex(String input) {
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

    public static int getIndex(String input) {
        String[] stringArray = input.split(" ");
        return Integer.parseInt(stringArray[1]) - 1;
    }

    public static void errorMessage() {
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
                addedMessage(nextLine);
                addList(nextLine);
            }
        }
        scanner.close();

    }
}
