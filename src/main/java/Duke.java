import java.util.Scanner;

public class Duke {

    static String[] tasks = new String[100];
    static int numTasks = 0;

    // loop through tasks array and return all tasks as a string
    public static String printTasks() {
        String tasksList = "";
        for (int i = 0; i < numTasks; i++) {
            if (i == 0) {
                tasksList = "1. " + tasks[i];
            } else {
                tasksList = tasksList + "\n " + (i + 1) + ". " + tasks[i];
            }
        }

        return tasksList;
    }

    public static void getInput() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String inputData = sc.nextLine();

            if (inputData.equals("list")) {
                System.out.println(" ____________________________________________________________\n " +
                        printTasks() +
                        "\n ____________________________________________________________");
            } else if (!inputData.equals("bye")) {
                // add inputData to next index in tasks array and print inputData
                tasks[numTasks] = inputData;
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
