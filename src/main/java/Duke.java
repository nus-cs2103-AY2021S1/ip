import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {

    public static void main(String[] args) {
        String logo = "   ___      _      _ __   _              \n" +
                "  /   \\    | |    | '_ \\ | |_     __ _   \n" +
                "  | - |    | |    | .__/ | ' \\   / _` |  \n" +
                "  |_|_|   _|_|_   |_|__  |_||_|  \\__,_|  \n" +
                "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n" +
                "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' ";
        System.out.println("Hello from\n" + logo);

        String greeting = "    ____________________________________________________________\n" +
                "     Hello, Alpha here... welcome to my help centre... again :/\n" +
                "     Would you like to explain what you want?\n" +
                "    ____________________________________________________________";
        System.out.println(greeting);

        List<Task> taskList = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            respondToInput(input, taskList);
            System.out.println("    ____________________________________________________________");
            input = sc.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Try not to come again please... let me live.");
        System.out.println("    ____________________________________________________________");
    }

    private static void respondToInput(String input, List<Task> taskList) {
        if (input.equals("list")) {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.print("    " + (i+1) + ". ");
                System.out.println(taskList.get(i));
            }
        }
        else if (input.contains("done")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0 || index > taskList.size() - 1) {
                System.out.println("    Error. Entry does not exist. Please try again.");
            }
            else {
                taskList.get(index).setDone();
                System.out.println("    Finally... about time you finished that. Marked this task as done: ");
                System.out.println("      " + taskList.get(index));
            }
        }
        else {
            taskList.add(new Task(input));
            System.out.println("    added: " + input);
        }
    }
}
