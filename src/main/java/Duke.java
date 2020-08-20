import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = " __________________________________________________________\n"
                + "|                                                          |\n"
                + "|  ____     _     _____   ____  _   _ ____   ____ _______  |\n"
                + "| |  _ \\   / \\   |  __ \\ / __ \\| \\ | |  _ \\ / __ \\__   __| |\n"
                + "| | |_) | /   \\  | |__) | |  | |  \\| | |_) | |  | | | |    |\n"
                + "| |  _ < / /_\\ \\ |  _  /| |  | | . ` |  _ <| |  | | | |    |\n"
                + "| | |_) / _____ \\| | \\ \\| |__| | |\\  | |_) | |__| | | |    |\n"
                + "| |____/_/     \\_\\_|  \\_\\\\____/|_| \\_|____/ \\____/  |_|    |\n"
                + "|                                                          |\n"
                + "|__________________________________________________________|\n";
        String divider = "____________________________________________________________";

        System.out.println(logo);
        System.out.println(divider);
        System.out.println("Hello, I am BaronBot!");
        System.out.println("What can I do for you?");
        System.out.println(divider);

        Scanner sc = new Scanner(System.in);
        List<Task> taskArr = new ArrayList<>();
        String[] cmdArr = {"help", "add", "list", "done", "bye"};

        String input = sc.nextLine().toLowerCase();

        while (!input.equals("bye")) {
            System.out.println(divider);
            if (input.equals("help")) {
                System.out.println("Here are the commands you can use:");
                for (int i = 0; i < cmdArr.length; i++) {
                    System.out.println((i + 1) + ". " + cmdArr[i]);
                }
            } else if (input.equals("add")) {
                System.out.println("What would you like to add?");
                String inputToAdd = sc.nextLine();
                taskArr.add(new Task(inputToAdd));
                System.out.println("Added: " + inputToAdd);
            } else if (input.equals("list")) {
                if (taskArr.isEmpty()) {
                    System.out.println("There are no tasks on your list");
                    System.out.println("Use the 'add' command to start adding tasks!");
                } else {
                    System.out.println("These are the tasks on your list:");
                    for (int j = 0; j < taskArr.size(); j++) {
                        System.out.println((j + 1)
                                + ". "
                                + "["
                                + taskArr.get(j).getStatusIcon()
                                + "]"
                                + taskArr.get(j).getDescription());
                    }
                }
            } else if (input.equals("done")) {
                System.out.println("Which task do you want to mark as done?");
                int taskNum = sc.nextInt();

                    System.out.println("Good job! This task is now marked as done:");
                    taskArr.get(taskNum - 1).markAsDone();
                    System.out.println("["
                            + taskArr.get(taskNum - 1).getStatusIcon()
                            + "]"
                            + taskArr.get(taskNum - 1).getDescription());
                    sc.nextLine();
            } else {
                System.out.println("Sorry I didn't understand that :(");
                System.out.println("How about entering 'help' instead?");
            }
            System.out.println(divider);
            System.out.println("What else would you like to do?");
            System.out.println(divider);
            input = sc.nextLine().toLowerCase();
        }

        System.out.println(divider);
        System.out.println("Bye! See you around :)");
        System.out.println(divider);
    }
}
