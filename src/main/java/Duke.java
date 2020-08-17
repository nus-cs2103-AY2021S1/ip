import java.util.Scanner;

public class Duke {
    private static String[] tasks = new String[100];
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

    public static void printTasks() {
        if (index == 0) {
            System.out.println("You have no outstanding tasks, milady.");
        } else {
            System.out.println(dashedLineBreak());
            for (int i = 0; i < index; i++) {
                System.out.printf("%s. %s", i + 1, tasks[i]);
                System.out.println();
            }
            System.out.println(dashedLineBreak());
        }
        System.out.println();
    }

    public static void addTask(String task) {
        tasks[index] = task;
        index++;
    }

    public static void main(String[] args) {
        init();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("list")) {
                printTasks();
            } else if (userInput.equalsIgnoreCase("bye")){
                System.out.println("Your wish is my command, milady. Till I see you again.");
                sc.close();
                System.exit(0);
            } else {
                addTask(userInput);
                System.out.println(dashedLineBreak());
                System.out.println("added: " + userInput);
                System.out.println(dashedLineBreak());
                System.out.println();
            }
        }
    }
}
