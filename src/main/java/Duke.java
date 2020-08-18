import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int index = 0;

    public static void init() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Always at your service, \n" + logo);
        System.out.println("Your Majesty, I am your loyal Duke.");
        System.out.println("How may I serve you?");
        System.out.println();
    }

    public static String dashedLineBreak() {
        String dashedLine = "- ";
        return dashedLine.repeat(24);
    }

    public static void printAllTasks() {
        if (index == 0) {
            System.out.println("Your scroll is currently empty, milady.");
        } else {
            System.out.println(dashedLineBreak());
            System.out.println("Your current scroll, milady:");
            for (int i = 0; i < index; i++) {
                System.out.printf("%s.%s", i + 1, tasks[i]);
                System.out.println();
            }
            System.out.println(dashedLineBreak());
        }
        System.out.println();
    }

    public static void addTask(String task) {
        String splitTask[] = task.split(" ", 2);
        Task newTask = null;
        switch(splitTask[0].toLowerCase()) {
            case "todo":
                newTask = new ToDo(splitTask[1]);
                tasks[index] = newTask;
                index++;
                break;
            case "deadline":
                newTask = new Deadline(splitTask[1]);
                tasks[index] = newTask;
                index++;
                break;
            case "event":
                newTask = new Event(splitTask[1]);
                tasks[index] = newTask;
                index++;
                break;
            default:
                System.out.println("I'm afraid I do not understand that command, milady.");
                System.out.println(dashedLineBreak());
                System.out.println();
                break;
        }

        if (newTask != null) {
            System.out.println(dashedLineBreak());
            System.out.println("Milady, I've added the writing:");
            System.out.println("\t" + newTask);
            System.out.printf("You have %s writing(s) on your scroll as of now. \n", index);
            System.out.println(dashedLineBreak());
            System.out.println();
        }
    }

    public static void doneTask(int taskNumber) {
        if (taskNumber - 1 < 0 || taskNumber - 1 > 99 || tasks[taskNumber - 1] == null) {
            System.out.println("Milady, there's no such agenda in my detailed records.");
        } else {
            tasks[taskNumber - 1].markAsDone();
            System.out.println(dashedLineBreak());
            System.out.println("As you wish, milady. I have marked this as conquered.");
            System.out.println(tasks[taskNumber - 1]);
            System.out.println(dashedLineBreak());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        init();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String[] splitUserInput = userInput.split(" ");
            switch(splitUserInput[0].toLowerCase()) {
                case "dismiss":
                    System.out.println("Your wish is my command, milady. Till I see you again.");
                    sc.close();
                    System.exit(0);
                    break;
                case "scroll":
                    printAllTasks();
                    break;
                case "conquer":
                    int taskNumber = Integer.parseInt(splitUserInput[1]);
                    doneTask(taskNumber);
                    break;
                default:
                    addTask(userInput);
            }
        }
    }
}
