import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    // Constants
    public static final String LOGO =
            " ____        _\n"
                    + " |  _ \\ _   _| | _____\n"
                    + " | | | | | | | |/ / _ \\\n"
                    + " | |_| | |_| |   <  __/\n"
                    + " |____/ \\__,_|_|\\_\\___|\n";
    public static final String TOP_LINE = " ____________________________________________________________\n ";
    public static final String BOTTOM_LINE = "\n ____________________________________________________________";

    // Static variables
    public static List<Task> tasks = new ArrayList<>();
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
                    tasksList = " 1. " + tasks.get(i);
                } else {
                    tasksList = tasksList + "\n " + (i + 1) + ". " + tasks.get(i);
                }
            }

            System.out.println(formatMessage("Task(s) in your list:\n" + tasksList));
        }
    }

    // Checks if user's input date is of the correct format (yyyy-mm-dd HH:MM)
    public static boolean isValidFormat(String inputDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(inputDate, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
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
                            } else if (tasks.get(taskNumber - 1).isDone) {
                                throw new DukeException("You have already marked this task as done!");
                            } else {
                                tasks.set(taskNumber - 1, tasks.get(taskNumber - 1).markAsDone());
                            }

                        } else {
                            throw new DukeException("Please enter a valid task number.");
                        }
                    }

                } else if (firstWord.equals("delete")) {
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
                                numTasks--;
                                System.out.println(formatMessage("Noted. I've removed this task:\n    " +
                                        tasks.get(taskNumber - 1) +
                                        "\n Now you have " + numTasks + " task(s) in the list."));
                                tasks.remove(taskNumber - 1);
                            }

                        } else {
                            throw new DukeException("Please enter a valid task number.");
                        }
                    }

                } else if (firstWord.equals("todo")) {
                    if (inputDataWords.length < 2) {
                        throw new DukeException("The description of a " + firstWord + " cannot be empty.");
                    } else {
                        tasks.add(numTasks, new ToDo(inputData.split("todo ")[1]));
                        System.out.println(formatMessage("Got it. I've added this task:\n    " +
                                tasks.get(numTasks) +
                                "\n Now you have " + (numTasks + 1) + " task(s) in the list."));
                        numTasks++;
                    }

                } else if (firstWord.equals("deadline")) {
                    if (inputDataWords.length < 2) {
                        throw new DukeException("The description of a " + firstWord + " cannot be empty.");
                    } else if (inputData.split("/by ").length < 2 || inputDataWords[1].equals("/by")) {
                        throw new DukeException("The deadline of this task is not provided.\n" +
                                "   Please re-enter the desired deadline task\n" +
                                "   (e.g. deadline xxx /by yyyy-mm-dd HH:MM)");
                    } else {
                        String byString = inputData.split("/by ")[1];
                        if (isValidFormat(byString)) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDateTime byLocalDate = LocalDateTime.parse(byString, formatter);
                            tasks.add(numTasks, new Deadline(inputData.split("deadline ")[1].split("/by ")[0],
                                    byLocalDate));

                            System.out.println(formatMessage("Got it. I've added this task:\n    " +
                                    tasks.get(numTasks) +
                                    "\n Now you have " + (numTasks + 1) + " task(s) in the list."));
                            numTasks++;
                        } else {
                            throw new DukeException("   Please enter a valid deadline task\n" +
                                    "   (e.g. deadline xxx /by yyyy-mm-dd HH:mm)");
                        }

                    }

                } else if (firstWord.equals("event")) {
                    if (inputData.split(" ").length < 2 || inputDataWords[1].equals("/at")) {
                        throw new DukeException("The description of a " + firstWord + " cannot be empty.");
                    } else if (inputData.split("/at ").length < 2) {
                        throw new DukeException("The duration of this task cannot be empty.\n" +
                                "   Please re-enter the desired event task\n" +
                                "   (e.g. event xxx /at yyyy-mm-dd HH:mm)");
                    } else {
                        String atString = inputData.split("/at ")[1];
                        if (isValidFormat(atString)) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDateTime atLocalDate = LocalDateTime.parse(atString, formatter);
                            tasks.add(numTasks, new Event(inputData.split("event ")[1].split("/at ")[0],
                                    atLocalDate));
                            System.out.println(formatMessage("Got it. I've added this task:\n    " +
                                    tasks.get(numTasks) +
                                    "\n Now you have " + (numTasks + 1) + " task(s) in the list."));
                            numTasks++;
                        } else {
                            throw new DukeException("   Please enter a valid event task\n" +
                                    "   (e.g. event xxx /at yyyy-mm-dd HH:mm)");
                        }
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