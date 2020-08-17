import java.util.Scanner;

public class Duke {

    static Task[] tasks = new Task[100];
    static int numTasks = 0;

    // loop through tasks array and print tasks in order
    public static void printTasks() {
        if (numTasks == 0) {
            System.out.println(" ____________________________________________________________\n " +
                    "There are currently no tasks in your list.\n" +
                    " ____________________________________________________________");
        } else {
            String tasksList = "";
            for (int i = 0; i < numTasks; i++) {
                if (i == 0) {
                    tasksList = " 1. " + tasks[i];
                } else {
                    tasksList = tasksList + "\n " + (i + 1) + ". " + tasks[i];
                }
            }

            System.out.println(" ____________________________________________________________\n " +
                    "Task(s) in your list:\n" +
                    tasksList +
                    "\n ____________________________________________________________");
        }
    }

    public static void getInput() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String inputData = sc.nextLine();
            String firstWord = inputData.split(" ")[0];

            if (inputData.equals("list")) {
                printTasks();

            } else if (firstWord.equals("done")) {
                int taskNumber = Integer.parseInt(inputData.split(" ")[1]);

                if (taskNumber > numTasks || taskNumber <= 0) {
                    System.out.println("Invalid task number. Please try again.");
                } else if (inputData.split(" ").length > 2) {
                    System.out.println("Invalid command. Please try again.");
                } else {
                    tasks[taskNumber - 1] = tasks[taskNumber - 1].markAsDone();
                }

            } else if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")) {

                if (firstWord.equals("todo")) {
                    // to-do task
                    tasks[numTasks] = new ToDo(inputData.split("todo ")[1]);
                } else if (firstWord.equals("deadline")) {
                    // deadline task
                    tasks[numTasks] = new Deadline(inputData.split("/by ")[0], inputData.split("/by ")[1]);
                } else {
                    // event task
                    tasks[numTasks] = new Event(inputData.split("/at ")[0], inputData.split("/at ")[1]);
                }

                System.out.println(" ____________________________________________________________\n " +
                        "Got it. I've added this task:\n    " +
                        tasks[numTasks] +
                        "\n Now you have " + (numTasks + 1) + " task(s) in the list." +
                        "\n ____________________________________________________________");
                numTasks++;

            } else if (inputData.equals("bye")) {
                String ending = " ____________________________________________________________\n " +
                        " Goodbye! See you again!\n" +
                        " ____________________________________________________________";

                System.out.println(ending);

                sc.close();
                break;
            } else {
                // invalid commands
                System.out.println("Invalid command. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + " |  _ \\ _   _| | _____ \n"
                + " | | | | | | | |/ / _ \\\n"
                + " | |_| | |_| |   <  __/\n"
                + " |____/ \\__,_|_|\\_\\___|\n";

        System.out.println(" ____________________________________________________________\n " +
                logo +
                " Hello! I'm Duke\n" +
                " What can I do for you today?\n" +
                " ____________________________________________________________");

        getInput();

    }
}
