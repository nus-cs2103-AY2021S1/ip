import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    static List<Task> tasks = new ArrayList<>();

    public static void greet() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________\n");
    }

    public static void farewell() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        String userCommand = sc.nextLine();

        while (!userCommand.equals("bye")) {
            if (userCommand.equals("list")) {
                System.out.println("\t____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    int serialNumber = i + 1;
                    Task task = tasks.get(i);
                    System.out.println("\t" + serialNumber + "." + task);
                }
                System.out.println("\t____________________________________________________________\n");
                userCommand = sc.nextLine();
            }  else if (userCommand.contains("done")) {
                try {
                    // E.g given "done 1", we split to ["done", "1"]
                    String[] userCommandSplit = userCommand.split(" ");
                    // To prevent cases such as "done 1 7"
                    if (userCommandSplit.length != 2) {
                        System.out.println("\t____________________________________________________________");
                        System.out.println("Oops! Something went wrong!");
                        System.out.println("\t____________________________________________________________\n");
                        userCommand = sc.nextLine();
                    } else {
                        // Take serial number e.g 1 "done 1"
                        int serialNumber = Integer.parseInt(userCommandSplit[1]);
                        Task doneTask = tasks.get(serialNumber - 1);
                        doneTask.markAsDone();
                        System.out.println("\t____________________________________________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("\t" + doneTask);
                        System.out.println("\t____________________________________________________________\n");
                        userCommand = sc.nextLine();
                    }
                } catch (Exception e) {
                    System.out.println("\t____________________________________________________________");
                    System.out.println("Oops! Something went wrong!");
                    System.out.println("\t____________________________________________________________\n");
                    userCommand = sc.nextLine();
                }
            } else {
                tasks.add(new Task(userCommand));
                System.out.println("\t____________________________________________________________");
                System.out.println("\t" + "added: " + userCommand);
                System.out.println("\t____________________________________________________________\n");
                userCommand = sc.nextLine();
            }
        }

        farewell();
    }
}
