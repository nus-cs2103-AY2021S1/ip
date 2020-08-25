import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Duke {
    /** Constants **/
    public static String outputBreaker = ">>> ";
    public static String DATA_PATH = "data/duke.txt";

    public static void main(String[] args) {
        /** Initialize objects required **/
        // Using Scanner for reading inputs
        Scanner scanner = new Scanner(System.in);
        // Initialize List for Duke to store stuff in
        /** Note: limit storage to 100 items **/
        ArrayList<Task> list = new ArrayList<>(100);

        // Text Images
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String lineBreaker = "--.--.--.--.--.--.--.--.--.--.--." +
                "--.--.--.--.--.--.--.--.--.--.--";

        /** Load past data if any. Else create new file */
        try {
            loadFileContents(DATA_PATH, list);
            System.out.println("... Oh! You're back!\nEr, gimme a sec...");
        } catch (FileNotFoundException e) {
            System.out.println("... Who? Never mind.");
            createFile();
        }

        // Greetings
        System.out.println("\nHello, I'm Duke!");
        System.out.println("What can I help you with today?");
        System.out.println("\n" + lineBreaker);
        System.out.println();

        // Read user input
        String userInput = scanner.nextLine();
        // System Loop
        // End only if user input is "bye"
        while (!userInput.equals("bye")) {

            try {
                // Process input
                String[] instructions = userInput.split(" ", 2);

                // React to commands
                if (userInput.equals("list")) {
                    /** Command: "list" **/
                    printList(list);

                } else if (instructions[0].equals("todo")) {
                    /** Command for to do Tasks creation **/
                    // Add check for command details
                    if (hasCmdDetails(instructions)) {
                        // If command has details, add task to list
                        System.out.print(outputBreaker);
                        list.add(new Todo(instructions[1]));
                        System.out.println("added: " + list.get(list.size() - 1));
                        System.out.println("There is now " + list.size() + " tasks in the list!");
                    } else {
                        // Command has no details, throw exception
                        throw new DukeException(outputBreaker +
                                "Whoops! I think you forgot to finish your "
                                + instructions[0] + " command. Sorry but I need it. D:");
                    }

                } else if (instructions[0].equals("deadline")) {
                    /** Command: "deadline <taskName> /by <deadline>" **/
                    if (hasCmdDetails(instructions)) {
                        System.out.print(outputBreaker);
                        // Extract Details from command
                        String[] details = instructions[1].split(" /by ", 2);
                        list.add(new Deadline(details[0], checkDate(details[1])));
                        System.out.println("added: " + list.get(list.size() - 1));
                        System.out.println("There is now " + list.size() + " tasks in the list!");
                    } else {
                        // Command has no details, throw exception
                        throw new DukeException(outputBreaker +
                                "Whoops! I think you forgot to finish your "
                                + instructions[0] + " command. Sorry but I need it. D:");
                    }

                } else if (instructions[0].equals("event")) {
                    /** Command: "deadline <taskName> /by <deadline>" **/
                    if (hasCmdDetails(instructions)) {
                        System.out.print(outputBreaker);
                        // Extract Details from command
                        String[] details = instructions[1].split(" /at ", 2);
                        list.add(new Event(details[0], details[1]));
                        System.out.println("added: " + list.get(list.size() - 1));
                        System.out.println("There is now " + list.size() + " tasks in the list!");
                    } else {
                        // Command has no details, throw exception
                        throw new DukeException(outputBreaker +
                                "Whoops! I think you forgot to finish your "
                                + instructions[0] + " command. Sorry but I need it. D:");
                    }

                } else if (instructions[0].equals("done")) {
                    // Command: "done <task>"
                    // If valid <task>, mark done
                    try {
                        int taskNumber = Integer.parseInt(instructions[1]);

                        // Only add if entry exist at index
                        if (checkList(list, taskNumber)) {
                            list.get(taskNumber - 1).markedDone(true);
                            System.out.print(outputBreaker);
                            System.out.println("Congratulations! I've helped you mark the task as done:");
                            System.out.print("    " + list.get(taskNumber - 1).toString() + "\n");

                        } else {
                            // Index don't exist so throw exception
                            System.out.print(outputBreaker);
                            throw new DukeException("Sorry, I don't think that's a valid index...");
                        }
                    } catch (NumberFormatException e) {
                        //System.out.println("Caught nfe");
                        System.out.print(outputBreaker);
                        throw new DukeException("Sorry, I don't think that's a valid number...");
                    }

                } else if (instructions[0].equals("delete")) {
                    // Command: "delete <taskNumber>"
                    // If valid <taskNumber>, remove from list
                    try {
                        int taskNumber = Integer.parseInt(instructions[1]);

                        // Only delete if entry exist at index
                        if (checkList(list, taskNumber)) {
                            System.out.print(outputBreaker);
                            System.out.println("Noted! I've helped you remove the following task:");
                            System.out.print("    " + list.get(taskNumber - 1).toString() + "\n");
                            list.remove(taskNumber - 1);
                            System.out.println("    Now, there is " + list.size() + " tasks in the list!");

                        } else {
                            // Index don't exist so throw exception
                            System.out.print(outputBreaker);
                            throw new DukeException("Sorry, I don't think that's a valid index...");
                        }
                    } catch (NumberFormatException e) {
                        //System.out.println("Caught nfe");
                        System.out.print(outputBreaker);
                        throw new DukeException("Sorry, I don't think that's a valid number...");
                    }

                } else {
                    // Default
                    // Duke does not recognize the commands
                    System.out.print(outputBreaker);
                    throw new DukeException("Sorry, I don't recognize what you just entered...");
                }

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("\n" + lineBreaker + "\n");
            // Read user input once more
            userInput = scanner.nextLine();
        }

        // Farewell message
        System.out.print(outputBreaker);
        System.out.println("Bye! Hoped I helped!");
        System.out.println("\n" + lineBreaker);

        /** Save current data, if any. */
        try {
            // Try to clear the last save
            clearTheFile();
            // Overwrite it with current
            saveToFile(list);

            System.out.println("\n...save successful...");
        } catch(IOException e) {
            // Print message if something goes wrong
            System.out.println("☹️Sorry, something went wrong and I couldn't save my data... ");
        }
    }

    /** Prints all the contents of the list in order **/
    public static void printList(ArrayList<Task> list) {
        System.out.print("Here is what I have! ^^\n");
        // Handles printing empty list
        if (list.size() == 0) {
            System.out.println("Whoops! I don't have anything of note yet...");
        }
        for (int i = 0; i < list.size(); i++) {
            // Enumerator
            System.out.print((i+1) + ".");
            // Actual Task
            System.out.println(list.get(i));
        }
    }

    /** Checks if index is valid **/
    public static boolean checkList(ArrayList<Task> list, int id) {
        if (id <= 0 || id > list.size()) {
            return false;
        } else {
            return true;
        }
    }

    /** Checks if the date entered is valid **/
    public static LocalDate checkDate(String dateString) throws DukeException {
        try {
            LocalDate df = LocalDate.parse(dateString);
            return df;
        } catch (Exception e) {
            // Check if there is even anything after command
            // Exception thrown if userInput.split() fails
            // Command has no details, throw exception
            throw new DukeException(outputBreaker +
                    "Whoops! I think there is an error in your date for the"
                    + " command. Sorry but please try again.");
        }
    }

    public static boolean hasCmdDetails(String[] cmd) throws DukeException {
        try {
            if (cmd[1].equals("") || cmd[1].trim().length() == 0) {
                /** Make sure command has follow up details **/
                // Check if there is second word
                // Check if second word is just whitespace
                // If so, command has no details
                return false;
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            // Check if there is even anything after command
            // Exception thrown if userInput.split() fails
            // Command has no details, throw exception
            throw new DukeException(outputBreaker +
                    "Whoops! I think you forgot to finish your "
                    + cmd[0] + " command. Sorry but I need it. D:");
        }
    }

    /** Checks if there is data to draw from. **/
    /** Retrieved from https://nus-cs2103-ay2021s1.github.io/website/book/cppToJava/misc/fileAccess/ */
    private static void loadFileContents(String filePath, ArrayList<Task> list) throws FileNotFoundException {
        File data = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(data); // create a Scanner using the File as the source
        while (s.hasNext()) {
            // Read the data line
            String dataLine = s.nextLine();
            // Split data line into components
            String[] taskDetails = dataLine.split(" - ", 0);
            // Depending on first letter, determine what task it is
            if (taskDetails[0].equals("T")) {
                Task t = new Todo(taskDetails[2]);
                if (taskDetails[1].equals("1")) {
                    t.markedDone(true);
                }
                list.add(t);
            } else if (taskDetails[0].equals("D")) {
                Task t = new Deadline(taskDetails[2], taskDetails[3]);
                if (taskDetails[1].equals("1")) {
                    t.markedDone(true);
                }
                list.add(t);
            } else if (taskDetails[0].equals("E")) {
                Task t = new Event(taskDetails[2], taskDetails[3]);
                if (taskDetails[1].equals("1")) {
                    t.markedDone(true);
                }
                list.add(t);
            } else {
                // Basic Error, if invalid task, print error message
                System.out.print("Sorry I could not comprehend this: ");
                System.out.println(dataLine);
            }
        }
    }

    /** Saves Duke's current data **/
    private static void saveToFile(ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            // Try to write into save file
            try {
                writeToFile(DATA_PATH, list.get(i).toSaveData());
            } catch (IOException e) {
                System.out.print("Something happened and this failed to be saved: ");
                System.out.println(list.get(i));
            }
        }
    }

    /** Writes Data to File **/
    /** Retrieved from https://nus-cs2103-ay2021s1.github.io/website/book/cppToJava/misc/fileAccess/ */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAdd);
        fw.close();
    }

    /** Clear the save file of content. */
    /** Retrieved from
     * https://stackoverflow.com/questions/29878237
     *      /java-how-to-clear-a-text-file-without-deleting-it/42282671*/
    public static void clearTheFile() throws IOException {
        FileWriter fwOb = new FileWriter(DATA_PATH, false);
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }

    /** Creation of data to draw from. **/
    private static void createFile() {
        // Make directory if it doesn't already exists
        File dir = new File("data");
        dir.mkdir();
        // create a File for the given file path
        new File("data/duke.txt");
    }
}
