import java.util.Scanner;

public class Duke {

    static Task[] tasks = new Task[100];
    static int numTasks = 0;

    // loop through tasks array and print tasks in order
    public static void printTasks() {
        String tasksList = "";
        for (int i = 0; i < numTasks; i++) {
            Task curr = tasks[i];
            if (i == 0) {
                tasksList = " 1. " + "[" + curr.getStatusIcon() + "] " + curr.description;
            } else {
                tasksList = tasksList + "\n " + (i + 1) + ". " + "[" + curr.getStatusIcon() + "] " + curr.description;
            }
        }

        System.out.println(" ____________________________________________________________\n " +
                "Here are the tasks in your list:\n" +
                tasksList +
                "\n ____________________________________________________________");
    }

    public static void getInput() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String inputData = sc.nextLine();

            if (inputData.equals("list")) {
                printTasks();

            } else if (inputData.split(" ")[0].equals("done")) {
                int taskNumber = Integer.parseInt(inputData.split(" ")[1]);

                if (taskNumber > numTasks || taskNumber < 0) {
                    System.out.println("Invalid task number. Please try again.");
                } else {
                    tasks[taskNumber - 1] = tasks[taskNumber - 1].markAsDone();
                }

            } else if (!inputData.equals("bye")) {
                // add inputData to next index in tasks array and print inputData
                tasks[numTasks] = new Task(inputData);
                numTasks++;
                System.out.println(" ____________________________________________________________\n " +
                        "added: " + inputData +
                        "\n ____________________________________________________________");
            } else {
                String ending = " ____________________________________________________________\n " +
                        " Goodbye! See you again!\n" +
                        " ____________________________________________________________";

                System.out.println(ending);

                sc.close();
                break;
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
