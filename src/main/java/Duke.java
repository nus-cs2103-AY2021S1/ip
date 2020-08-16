import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    // list storage
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    // list of commands
    private static String exitCmd = "bye";
    private static String doneCmd = "done";
    private static String todoCmd = Task.Command.TODO.getCmd();
    private static String deadlineCmd = Task.Command.DEADLINE.getCmd();
    private static String eventCmd = Task.Command.EVENT.getCmd();
    private static String deadlineTimeSpecifier = Task.Command.DEADLINE.getTimeSpecifier();
    private static String eventTimeSpecifier = Task.Command.EVENT.getTimeSpecifier();

    // list of messages
    private static String exitMsg = "Bye human. See you again soon!";
    private static String doneMsg = "Good job bud! I've marked this task as done:";
    private static String showTasksMsg = "Here are the tasks in your lists:";
    private static String addSuccessfulMsg = "Got it. I've added this task:";
    private static String horizontalLine = "________________________________________";
    private static String cmdReq = "Your command: ";
    private static String lazyHumanBash = "You have nothing in your list. Why are you so lazy human?";

    private static String taskReport() {
        return "You have " + taskCount + " tasks in your list";
    }

    // logo and greeting
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static String greeting = "Hello human, I'm Duke the supporting chatbot \n"
            + "What can I do for you?";

    // main
    public static void main(String[] args) {
        System.out.println(logo + "\n" + horizontalLine);
        System.out.println(greeting + "\n" + horizontalLine);
        System.out.println(cmdReq);

        // getting command
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] inputSplitted = input.split(" ", 2);
        while (!input.equals(exitCmd)) {
            System.out.println(horizontalLine);
            if (input.equals("list")) {
                if (taskCount == 0) {
                    System.out.println(lazyHumanBash);
                } else {
                    System.out.println(showTasksMsg);
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i].toString());
                    }
                }
            } else if (inputSplitted[0].equals(doneCmd)) {
                int idx = Integer.parseInt(inputSplitted[1]) - 1;
                tasks[idx] = tasks[idx].markAsDone();
                System.out.println(doneMsg);
                System.out.println(tasks[idx]);
            } else if (inputSplitted[0].equals(todoCmd)){
                Task newTask = new ToDo(inputSplitted[1]);
                taskCount++;
                tasks[taskCount - 1] = newTask;
                System.out.println(addSuccessfulMsg);
                System.out.println(tasks[taskCount - 1]);
                System.out.println(taskReport());
            } else if (inputSplitted[0].equals(deadlineCmd)) {
                String content = inputSplitted[1];
                int timeIdx = content.indexOf(" /by");
                String description = content.substring(0, timeIdx);
                String time = content.substring(timeIdx + 5, content.length());
                Task newTask = new Deadline(description, time);
                taskCount++;
                tasks[taskCount - 1] = newTask;
                System.out.println(addSuccessfulMsg);
                System.out.println(tasks[taskCount - 1]);
                System.out.println(taskReport());
            } else if (inputSplitted[0].equals(eventCmd)) {
                String content = inputSplitted[1];
                int timeIdx = content.indexOf(" /at");
                String description = content.substring(0, timeIdx);
                String time = content.substring(timeIdx + 5, content.length());
                Task newTask = new Event(description, time);
                taskCount++;
                tasks[taskCount - 1] = newTask;
                System.out.println(addSuccessfulMsg);
                System.out.println(tasks[taskCount - 1]);
                System.out.println(taskReport());
            }
            System.out.println(horizontalLine);
            System.out.println(cmdReq);
            input = sc.nextLine();
            inputSplitted = input.split(" ", 2);
        }

        // exit
        System.out.println(horizontalLine + "\n" +  exitMsg + "\n" + horizontalLine);
    }
}
