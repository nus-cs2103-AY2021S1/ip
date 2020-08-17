import java.util.Scanner;

public class Duke {

    // Constant variables
    public static final String LOGO =
            " ____        _\n"
            + " |  _ \\ _   _| | _____\n"
            + " | | | | | | | |/ / _ \\\n"
            + " | |_| | |_| |   <  __/\n"
            + " |____/ \\__,_|_|\\_\\___|\n";
    public static final String TOP_LINE = " ____________________________________________________________\n ";
    public static final String BOTTOM_LINE = "\n ____________________________________________________________";

    // Static variables
    public static Task[] tasks = new Task[100];
    public static int numTasks = 0;

    // Add top and bottom lines to provided message
    public static String formatMessage(String msg) {
        return TOP_LINE + msg + BOTTOM_LINE;
    }

    // Check if given string can be parsed into an integer
    public static boolean isInteger(String secondWord) {
        try {
            Integer.parseInt(secondWord);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    // Loop through tasks array and print tasks in order
    public static void printTasks() {
        if (numTasks == 0) {
            System.out.println(formatMessage("There are currently no tasks in your list."));
        } else {
            String tasksList = "";
            for (int i = 0; i < numTasks; i++) {
                if (i == 0) {
                    tasksList = " 1. " + tasks[i];
                } else {
                    tasksList = tasksList + "\n " + (i + 1) + ". " + tasks[i];
                }
            }

            System.out.println(formatMessage("Task(s) in your list:\n" + tasksList));
        }
    }

    // Receives commands from user input and executes them accordingly
    public static void getInput() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            try {
                String inputData = sc.nextLine();
                String[] inputDataWords = inputData.split(" ");
                String firstWord = inputDataWords[0];

                if (inputData.equals("list")) {
                    printTasks();

                } else if (firstWord.equals("done")) {
                    if (inputDataWords.length != 2) {
                        throw new DukeException("Invalid command provided. Please try again.");
                    } else {
                        // check if second word is an integer
                        if (isInteger(inputDataWords[1])) {
                            int taskNumber = Integer.parseInt(inputDataWords[1]);

                            // then check if the taskNumber provided is in range
                            if (taskNumber > numTasks || taskNumber <= 0) {
                                throw new DukeException("Please enter a valid task number.");
                            } else {
                                tasks[taskNumber - 1] = tasks[taskNumber - 1].markAsDone();
                            }

                        } else {
                            throw new DukeException("Please enter a valid task number.");
                        }
                    }

                } else if (firstWord.equals("todo")) {
                    if (inputDataWords.length < 2) {
                        throw new DukeException("The description of a " + firstWord + " cannot be empty.");
                    } else {
                        tasks[numTasks] = new ToDo(inputData.split("todo ")[1]);
                        System.out.println(formatMessage("Got it. I've added this task:\n    " +
                                tasks[numTasks] +
                                "\n Now you have " + (numTasks + 1) + " task(s) in the list."));
                        numTasks++;
                    }

                } else if (firstWord.equals("deadline")) {
                    if (inputDataWords.length < 2) {
                        throw new DukeException("The description of a " + firstWord + " cannot be empty.");
                    } else if (inputData.split("/by ").length < 2 || inputDataWords[1].equals("/by")) {
                        throw new DukeException("The deadline of this task is not provided.\n" +
                                "   Please re-enter the desired deadline task\n" +
                                "   (e.g. deadline xxx /by zzz)");
                    } else {
                        tasks[numTasks] = new Deadline(inputData.split("deadline ")[1].split("/by ")[0],
                                inputData.split("/by ")[1]);

                        System.out.println(formatMessage("Got it. I've added this task:\n    " +
                                tasks[numTasks] +
                                "\n Now you have " + (numTasks + 1) + " task(s) in the list."));
                        numTasks++;
                    }

                } else if (firstWord.equals("event")) {
                    if (inputData.split(" ").length < 2 || inputDataWords[1].equals("/at")) {
                        throw new DukeException("The description of a " + firstWord + " cannot be empty.");
                    } else if (inputData.split("/at ").length < 2) {
                        throw new DukeException("The duration of this task cannot be empty.\n" +
                                "   Please re-enter the desired event task\n" +
                                "   (e.g. event xxx /at zzz)");
                    } else {
                        tasks[numTasks] = new Event(inputData.split("event ")[1].split("/at ")[0],
                                inputData.split("/at ")[1]);
                        System.out.println(formatMessage("Got it. I've added this task:\n    " +
                                        tasks[numTasks] +
                                        "\n Now you have " + (numTasks + 1) + " task(s) in the list."));
                        numTasks++;
                    }

                } else if (inputData.equals("bye")) {
                    System.out.println(formatMessage(" Goodbye! See you again!"));
                    sc.close();
                    break;

                } else {
                    // invalid commands
                    throw new DukeException("Invalid command provided. Please try again.");
                }

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(formatMessage(LOGO +
                " Hello! I'm Duke\n" +
                " What can I do for you today?"));

        getInput();
    }
}
