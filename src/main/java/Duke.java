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
        String[] cmdArr = {"help", "add", "list", "done", "delete", "date", "bye"};

        String input = sc.nextLine().toLowerCase();

        while (!input.equals("bye")) {
            System.out.println(divider);
            try {
                if (input.equals("help")) {
                    processHelp(cmdArr);
                } else if (input.equals("add")) {
                    processAdd(taskArr, sc);
                } else if (input.equals("list")) {
                    if (taskArr.isEmpty()) {
                        throw new EmptyListException("There are no tasks on your list");
                    }
                    processList(taskArr);
                } else if (input.equals("done")) {
                    if (taskArr.isEmpty()) {
                        throw new EmptyListException("There are no tasks on your list");
                    }
                    processDone(taskArr, sc);
                } else if (input.equals("delete")) {
                    if (taskArr.isEmpty()) {
                        throw new EmptyListException("There are no tasks on your list");
                    }
                    processDelete(taskArr, sc);
                } else {
                    throw new UnknownCommandException("Sorry I didn't understand that :(");
                }
            } catch (UnknownCommandException e1) {
                System.out.println(e1.getMessage());
                System.out.println("How about entering 'help' instead?");
            } catch (EmptyListException e) {
                System.out.println(e.getMessage());
                System.out.println("Use the 'add' command to start adding tasks!");
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
        try {
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
                    System.out.println("e.g., submit report ,11/10/2019 1700");
                    break;
                case "event":
                    taskType = TaskType.EVENT;
                    System.out.println("Please enter the event followed by the date and time of the event");
                    System.out.println("e.g., team project meeting ,2/10/2019 1400-1600");
                    break;
                default:
                    throw new InvalidTaskTypeException("Oops that wasn't a valid task type!");
            }
            processTaskType(taskArr, taskType, sc);
        } catch (InvalidTaskTypeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void processTaskType(List<Task> taskArr, TaskType taskType, Scanner sc) throws ArrayIndexOutOfBoundsException{
        String inputToAdd = sc.nextLine();
        try {
            if (inputToAdd.isEmpty()) {
                throw new EmptyDescriptionException("Oops, the description can't be empty!");
            }
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
        } catch (EmptyDescriptionException e1){
            System.out.println(e1.getMessage());
        } catch (ArrayIndexOutOfBoundsException e2) {
            System.out.println("Hmmm, looks like the format didn't work");
            System.out.println("Refer to the example for help! :P");
        }
    }

    public static void processList(List<Task> taskArr) {
            System.out.println("These are the tasks on your list:");
            for (int j = 0; j < taskArr.size(); j++) {
                System.out.println((j + 1)
                        + ". "
                        + taskArr.get(j).toString());
            }
    }

    public static void processDone(List<Task> taskArr, Scanner sc) throws IndexOutOfBoundsException {
        System.out.println("Which task do you want to mark as done?");
        int taskNum = sc.nextInt();
        sc.nextLine();
        try {
            if (taskArr.get(taskNum - 1).isDone()) {
                throw new InvalidDoneCommandException("This task is already done!");
            } else {
                taskArr.get(taskNum - 1).markAsDone();
                System.out.println("Good job! This task is now marked as done:");
                System.out.println(taskArr.get(taskNum - 1).toString());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry that task doesn't exist :/");
            System.out.println("Try using 'list' to find out what tasks you have!");
        } catch (InvalidDoneCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("Check out each task's status by using 'list'!");
        }
    }

    public static void processDelete(List<Task> taskArr, Scanner sc) throws IndexOutOfBoundsException {
        System.out.println("Which task do you want to delete?");
        int taskNum = sc.nextInt();
        sc.nextLine();
        try {
            Task task = taskArr.get(taskNum - 1);
            taskArr.remove(taskNum - 1);
            System.out.println("Alright, the following task has been removed:");
            System.out.println(task.toString());
            System.out.println("You now have " + taskArr.size() + " tasks on your list");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry that task doesn't exist :/");
            System.out.println("Try using 'list' to find out what tasks you have!");
        }
    }
}
