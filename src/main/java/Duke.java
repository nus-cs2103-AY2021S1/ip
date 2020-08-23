import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Over-arching class containing the main information and methods of the Duke bot.
 */

public class Duke {
    private Scanner sc;
    private File data;
    private TaskList tasks;
    private boolean quit;

    Duke() {
        this.sc = new Scanner(System.in);
        this.tasks = new TaskList();
        this.quit = false;
        try {
            String dir = System.getProperty("user.dir");
            this.data = Paths.get("data.txt").toFile();
            readData();
        } catch (FileNotFoundException e){
            System.out.println("No data found, creating new .txt file");
            this.data = new File("data.text");
        }
    }

    /**
     * Allows the system to begin taking in user input and edits the stored data accordingly
     */

    void takeInputs() {
        String input;
        while (!quit) {
            input = sc.nextLine();
            try {
                if (input.equals("exit")) { // quit
                    quit = true;
                    System.out.println("Goodbye!");
                } else if (input.equals("list")) { // list
                    System.out.println(tasks);
                } else if (input.startsWith("done")) { // mark something as done
                    doneTask(Integer.parseInt(input.substring(input.length() -1)));
                    writeToFile("data.txt", tasks.writeString());
                } else if (input.startsWith("delete")) { // delete a task
                    deleteTask(Integer.parseInt(input.substring(input.length() -1)));
                    writeToFile("data.txt", tasks.writeString());
                } else { // add something
                    String[] info = extractInfo(input);
                    if (info[0].equals("todo")) {
                        tasks.addItem(new Todo(info[1], false));
                        System.out.println("Added new task: " + info[1]);
                        printListSize();
                        writeToFile("data.txt", tasks.writeString());
                    } else if (info[0].equals("deadline")) {
                        tasks.addItem(new Deadline(info[1], stringToTime(info[2]), false));
                        System.out.println("Added new task: " + info[1]);
                        printListSize();
                        writeToFile("data.txt", tasks.writeString());
                    } else {
                        tasks.addItem(new Event(info[1], stringToTime(info[2]), false));
                        System.out.println("Added new task: " + info[1]);
                        printListSize();
                        writeToFile("data.txt", tasks.writeString());
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Marks an input task as done
     * @param idx Number of the task which the user wishes to mark as done
     * @throws TaskNotFoundException If input task number is not found in the list
     * @throws InvalidTaskNumberException If user enters a non-integer input
     */

    public void doneTask(int idx) throws TaskNotFoundException, InvalidTaskNumberException {
        try {
            tasks.getTasks().get(idx - 1).setDone(true);
            System.out.println("Congrats, you've finished this task!");
            System.out.println(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException("Sorry, I couldn't find that task.");
        } catch (NumberFormatException ex) {
            throw new InvalidTaskNumberException("Please enter a valid number!");
        }
    }

    /**
     * Deletes an input task from the list
     * @param idx Number of the task indicated to be deleted
     * @throws TaskNotFoundException If input task number is not found in the list
     * @throws InvalidTaskNumberException If user enters a non-integer input
     */

    public void deleteTask(int idx) throws TaskNotFoundException, InvalidTaskNumberException {
        try {
            System.out.println("The task " + tasks.getTasks().get(idx - 1) + " has been removed.");
            tasks.getTasks().remove(idx - 1);
            printListSize();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException("Sorry, I couldn't find that task.");
        } catch (NumberFormatException ex) {
            throw new InvalidTaskNumberException("Please enter a valid number!");
        }
    }

    /**
     * Breaks down and stores user input in a string array for easy access
     * @param str String to be broken down
     * @return A string array split according to the information categories
     * @throws InvalidTaskException If task description is empty
     * @throws UnknownCmdException If an unknown command is entered
     * @throws InvalidTimeException If an invalid time is entered for a Deadline or Event task
     */

    public String[] extractInfo(String str) throws InvalidTaskException, UnknownCmdException,InvalidTimeException {
        String[] store = new String[3];
        // Handling the classification of event type
        if (str.startsWith("todo")) {
            if (str.equals("todo") || str.strip().equals("todo"))  {
                throw new InvalidTaskException("Your task cannot be empty!");
            } else if (!str.startsWith("todo ")) {
                throw new UnknownCmdException("Unknown command entered!");
            } else {
                store[0] = "todo";
            }
        } else if (str.startsWith("deadline")) {
            if (str.equals("deadline") || str.strip().equals("deadline")) {
                throw new InvalidTaskException("Your task cannot be empty!");
            } else if (!str.startsWith("deadline ")) {
                throw new UnknownCmdException("Unknown command entered!");
            } else {
                store[0] = "deadline";
            }
        } else if (str.startsWith("event")) {
            if (str.equals("event") || str.strip().equals("event")) {
                throw new InvalidTaskException("Your task cannot be empty!");
            } else if (!str.startsWith("event ")) {
                throw new UnknownCmdException("Unknown command entered!");
            } else {
                store[0] = "event";
            }
        } else {
            throw new UnknownCmdException("Unknown command entered!");
        }

        // handling the content of the event
        int splitPrefix = str.indexOf(" ");
        String content = str.substring(splitPrefix).strip();
        if (content.length() <= 0) {
            throw new InvalidTaskException("Your task cannot be empty!");
        }
        if (store[0].equals("todo")) {
            store[1] = content;
            store[2] = "";
        } else {
            int splitTime = store[0].equals("deadline") ? content.indexOf("/by") : content.indexOf("/at");
            if (splitTime < 0) {
                throw new InvalidTimeException("Please use /by (deadlines) or /at (events)! to indicate the date or time!");
            }
            String name = content.substring(0, splitTime).strip();
            String time = content.substring(splitTime + 3).strip();
            if (name.length() <= 0) {
                throw new InvalidTaskException("Your task cannot be empty!");
            }
            if (time.length() <= 0) {
                throw new InvalidTimeException("Please specify a date or time for this task!");
            }
            store[1] = name;
            store[2] = time;
        }
        return store;
    }

    /**
     * Reads data from a pre-existing data.txt file
     * @throws FileNotFoundException Required file is not found
     */
    public void readData() throws FileNotFoundException {
        Scanner reader = new Scanner(data);
        while (reader.hasNextLine()) {
            String[] line = reader.nextLine().split(" # ");
            String type = line[0];
            boolean done = line[1].equals("1");
            String name = line[2];
            if (type.equals("T")) {
                tasks.addItem(new Todo(name, done));
            } else {
                String time = line[3];
                LocalDateTime ldt = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                if (type.equals("D")) {
                    tasks.addItem(new Deadline(name, ldt, done));
                } else {
                    tasks.addItem(new Event(name, ldt, done));
                }
            }
        }
    }

    /**
     * Writes data into a file
     * @param path Path to the file
     * @param text Text to be written into the file
     * @throws InvalidFileException File was not found at the end of the input path
     */
    private void writeToFile(String path, String text) throws InvalidFileException {
        try {
            FileWriter fw = new FileWriter(path);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            throw new InvalidFileException("File was not found");
        }
    }

    /**
     * Converts a string into a LocalDateTime
     * @param time The string to be parsed
     * @return A LocalDateTime object
     * @throws BadDtFormatException If an invalid string format is entered
     */
    public LocalDateTime stringToTime(String time) throws BadDtFormatException {
        try {
            return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd/MM/yyyy H:m"));
        } catch (DateTimeParseException e) {
            throw new BadDtFormatException("Please enter the date and time in the following format: dd/mm/yyyy hh:mm "
                    + "(24 hour clock)");
        }
    }

    /**
     * Prints the current size of the tasklist
     */
    public void printListSize() {
        System.out.println("You now have " + tasks.size() + (tasks.size() == 1
                ? " task in your list."
                : " tasks in your list."));
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        duke.takeInputs();
    }
}
