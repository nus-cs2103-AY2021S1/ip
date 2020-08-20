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
                processHelp(cmdArr);
            } else if (input.equals("add")) {
                processAdd(taskArr, sc);
            } else if (input.equals("list")) {
                processList(taskArr);
            } else if (input.equals("done")) {
                processDone(taskArr, sc);
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
    public static void processHelp(String[] cmdArr) {
        System.out.println("Here are the commands you can use:");
        for (int i = 0; i < cmdArr.length; i++) {
            System.out.println((i + 1) + ". " + cmdArr[i]);
        }
    }

    public static void processAdd(List<Task> taskArr, Scanner sc) {
        System.out.println("What kind of task is it?");
        System.out.print(" - Todo\n"
                + " - Deadline\n"
                + " - Event\n");
        String type = sc.nextLine().toLowerCase();
        TaskType taskType = TaskType.TODO;
        switch (type.toLowerCase()) {
            case "todo":
                taskType = TaskType.TODO;
                System.out.println("Please enter the task");
                break;
            case "deadline":
                taskType = TaskType.DEADLINE;
                System.out.println("Please enter the task followed by the date and time of the deadline");
                System.out.println("e.g., submit report ,11/10/2019 5pm");
                break;
            case "event":
                taskType = TaskType.EVENT;
                System.out.println("Please enter the event followed by the date and time of the event");
                System.out.println("e.g., team project meeting ,2/10/2019 2-4pm");
                break;
            default:
        }
        processTaskType(taskArr, taskType, sc);
    }

    public static void processTaskType(List<Task> taskArr, TaskType taskType, Scanner sc) {
        String inputToAdd = sc.nextLine();
        switch (taskType) {
            case TODO:
                taskArr.add(new Todo(inputToAdd, taskType));
                break;
            case DEADLINE:
                String[] deadlineSplit = inputToAdd.split(",");
                taskArr.add(new Deadline(deadlineSplit[0], deadlineSplit[1], taskType));
                break;
            case EVENT:
                String[] eventSplit = inputToAdd.split(",");
                taskArr.add(new Event(eventSplit[0], eventSplit[1], taskType));
                break;
            default:
        }
        System.out.println("Alright, I've added this task:");
        System.out.println(taskArr.get(taskArr.size() - 1).toString());
        System.out.println("You now have " + taskArr.size() + " tasks on your list");
    }

    public static void processList(List<Task> taskArr) {
        if (taskArr.isEmpty()) {
            System.out.println("There are no tasks on your list");
            System.out.println("Use the 'add' command to start adding tasks!");
        } else {
            System.out.println("These are the tasks on your list:");
            for (int j = 0; j < taskArr.size(); j++) {
                System.out.println((j + 1)
                        + ". "
                        + taskArr.get(j).toString());
            }
        }
    }

    public static void processDone(List<Task> taskArr, Scanner sc) {
        System.out.println("Which task do you want to mark as done?");
        int taskNum = sc.nextInt();
        System.out.println("Good job! This task is now marked as done:");
        taskArr.get(taskNum - 1).markAsDone();
        System.out.println(taskArr.get(taskNum - 1).toString());
        sc.nextLine();
    }
}
