import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        String sectionBreak = "------------------------------------------";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("\nHello buddy! I am");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        System.out.println(sectionBreak);

        Scanner sc=new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printTaskList();
            } else if (input.startsWith("done ")) {
                MarkTask(input.substring(5));
            } else {
                taskList.add(new Task(input));
                System.out.printf("added: %s\n", input);
            }

            System.out.println(sectionBreak);
        }

        System.out.println("Bye. Hope to see you soon!");
        System.out.println(sectionBreak);
    }

    private static void printTaskList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, taskList.get(i));
        }
    }

    private static void MarkTask(String indexString) {
        try {
            int index = Integer.parseInt(indexString);
            Task t = taskList.get(index - 1);
            t.markAsDone();
            System.out.println("Cool, I've marked this task as done\n" + t);
        } catch (NumberFormatException e) {
            System.out.printf("Oops, \"%s\" is not a valid integer\n", indexString);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Oops, Task #%s doesn't exist\n", indexString);
        }
    }
}
