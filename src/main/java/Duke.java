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
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm your loyal Duke.");
        System.out.println("How may I serve you, my Queen?");
        System.out.println();
    }

    public static String dashedLineBreak() {
        String dashedLine = "- ";
        return dashedLine.repeat(24);
    }

    public static void printAllTasks() {
        if (index == 0) {
            System.out.println("You have no outstanding tasks, milady.");
        } else {
            System.out.println(dashedLineBreak());
            System.out.println("Your current list, milady:");
            for (int i = 0; i < index; i++) {
                System.out.printf("%s.%s", i + 1, tasks[i]);
                System.out.println();
            }
            System.out.println(dashedLineBreak());
        }
        System.out.println();
    }

    public static void addTask(String task) {
        Task newTask = new Task(task);
        tasks[index] = newTask;
        index++;
        System.out.println(dashedLineBreak());
        System.out.println("added: " + task);
        System.out.println(dashedLineBreak());
        System.out.println();
    }

    public static void doneTask(int taskNumber) {
        if (taskNumber - 1 < 0 || taskNumber - 1 > 99 || tasks[taskNumber - 1] == null) {
            System.out.println("Milady, there's no such task in my detailed records.");
        } else {
            tasks[taskNumber - 1].markAsDone();
            System.out.println(dashedLineBreak());
            System.out.println("As you wish, milady. I have marked this as done.");
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
                case "bye":
                    System.out.println("Your wish is my command, milady. Till I see you again.");
                    sc.close();
                    System.exit(0);
                    break;
                case "list":
                    printAllTasks();
                    break;
                case "done":
                    int taskNumber = Integer.parseInt(splitUserInput[1]);
                    doneTask(taskNumber);
                    break;
                default:
                    addTask(userInput);
            }
        }
    }
}
