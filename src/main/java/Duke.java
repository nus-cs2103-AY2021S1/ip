import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

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
                                overwriteFile();
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
                                overwriteFile();
                            }

                        } else {
                            throw new DukeException("Please enter a valid task number.");
                        }
                    }

                } else if (firstWord.equals("todo")) {
                    if (inputDataWords.length < 2) {
                        throw new DukeException("The description of a " + firstWord + " cannot be empty.");
                    } else {
                        ToDo task = new ToDo(inputData.split("todo ")[1]);
                        tasks.add(numTasks, task);
                        appendToFile("\nT | 0 | " + task.description);
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
                                "   (e.g. deadline xxx /by zzz)");
                    } else {
                        Deadline task = new Deadline(inputData.split("deadline ")[1].split(" /by ")[0],
                                inputData.split("/by ")[1]);
                        tasks.add(numTasks, task);
                        appendToFile("\nD | 0 | " + task.description + " | " + task.by);
                        System.out.println(formatMessage("Got it. I've added this task:\n    " +
                                tasks.get(numTasks) +
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
                        Event task = new Event(inputData.split("event ")[1].split(" /at ")[0],
                                inputData.split("/at ")[1]);
                        tasks.add(numTasks, task);
                        appendToFile("\nE | 0 | " + task.description + " | " + task.at);
                        System.out.println(formatMessage("Got it. I've added this task:\n    " +
                                        tasks.get(numTasks) +
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

            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Scans through file and makes use of the information to initialise tasks arraylist.
    // If file does not exist, it creates a duke.txt file in the correct directory.
    private static void loadFileContents() throws IOException {
        File f = new File("data/duke.txt"); // create a File for the given file path

        if (f.exists()) {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source

            // go through file contents and initialise the tasks arraylist
            while (s.hasNext()) {
                String currString = s.nextLine();
                String[] currStringArray = currString.split(" \\| ");
                boolean isDone = currStringArray[1].equals("1");
                if (currStringArray[0].equals("T")) {
                    tasks.add(numTasks, new ToDo(currStringArray[2], isDone));
                    numTasks++;
                } else if (currStringArray[0].equals("D")) {
                    tasks.add(numTasks, new Deadline(currStringArray[2],
                           currStringArray[3], isDone));
                    numTasks++;
                } else {
                    tasks.add(numTasks, new Event(currStringArray[2],
                            currStringArray[3], isDone));
                    numTasks++;
                }
            }
        } else {
            File directory = new File("data");
            directory.mkdir(); // creates the directory if it does not exist
            File file = new File("data/duke.txt");
            file.createNewFile();
        }

        printTasks();
    }

    private static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt", true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    // Rewrite actual duke.txt file based on tasks arraylist
    // Loop through tasks array and format each task
    public static void overwriteFile() throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        String tasksList = "";
        for (int i = 0; i < numTasks; i++) {
            if (i == 0) {
                tasksList = tasks.get(i).toTxtFileFormat();
            } else {
                tasksList = tasksList + "\n" + tasks.get(i).toTxtFileFormat();
            }
        }
        fw.write(tasksList);
        fw.close();
    }

    public static void main(String[] args) {
        System.out.println(formatMessage(LOGO +
                " Hello! I'm Duke\n" +
                " What can I do for you today?"));

        try {
            loadFileContents();
            getInput();
        } catch (IOException e) {
            System.out.println("File could not be created.");
        }
    }
}
