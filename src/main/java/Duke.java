import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private UI ui;

    public Duke() {
        this.tasks = new TaskList();
        this.storage = new Storage();
        this.ui = new UI();
    }

    public static void run() {
        try {
            File file = Storage.getFile();
            TaskList.generateList(file);
            UI.readFile(file);
            UI.startUpMessage();
            UI.processInput();
        } catch (Exception e) {
            UI.showError(e);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /*
    public static void processInput(Scanner scanner, List<Task> saved) {
        List<Task> storedTasks = saved;

        if (saved != null) {
            System.out.println("Hi! I'm Duke" + "\n" + "What can I do for you?");
            String currInput = scanner.nextLine();
            while (isNotTerminateCommand(currInput)) {
                try {
                    if (isListCommand(currInput)) {
                        //List out all tasks' description
                        System.out.println(readList(storedTasks));
                    } else if (isDoneCommand(currInput)) {
                        verifyDoneCommand(currInput, storedTasks.size());
                        String[] parts = currInput.split(" ");
                        int index = Integer.parseInt(parts[1]) - 1;

                        storedTasks.set(index, storedTasks.get(index).markDone());

                        System.out.println("Nice! I've marked this task as done:" + "\n" + "  " + storedTasks.get(index).toString());
                    } else if (isDeleteCommand(currInput)) {
                        verifyDeleteCommand(currInput, storedTasks.size());

                        String[] parts = currInput.split(" ");
                        int index = Integer.parseInt(parts[1]) - 1;

                        System.out.println("Noted. I've removed this task:" + "\n" + "  " + storedTasks.get(index)
                                + "\n" + "Now you have " + (storedTasks.size() - 1) + " tasks in the list.");
                        storedTasks.remove(index);
                        //Save the task list
                    } else {
                        //handle task commands
                        addTasks(currInput, storedTasks);
                        System.out.println("Got it. I've added this task:" + "\n" + "  " + storedTasks.get(storedTasks.size() - 1)
                                + "\n" + "Now you have " + storedTasks.size() + " tasks in the list.");
                        //Save the task list
                    }

                    currInput = scanner.nextLine();

                } catch (InvalidCommandException e) {
                    System.out.println(e + "\n" + "Please enter a valid command");
                    currInput = scanner.nextLine();
                    //Should continue to run the program as it is
                } catch (InvalidInputException e) {
                    System.out.println(e + "\n" + "Please enter a valid input");
                    currInput = scanner.nextLine();
                    //Should continue to run the program as it is
                }

            }
            //Saves whatever changes made to the tasks
            saveTasks(storedTasks);

            System.out.println("Bye. Hope to see you again soon!");
            scanner.close();
        } else {
            scanner.close();
        }
    }

     public static boolean isDeleteCommand(String input) {
         String[] parts = input.split(" ");
         return parts[0].equals("delete") && parts.length == 2;
     }

    public static void verifyDeleteCommand(String input, int numOfTasks) throws InvalidCommandException {
        try {
            String[] parts = input.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;
            boolean result = index > -1 && index < numOfTasks;
            if (!result) {
                throw new InvalidCommandException("Number is invalid!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(("Please input number after the delete command!"));
        }
    }

    public static boolean isDoneCommand(String input) {
        String[] parts = input.split(" ");
        return parts[0].equals("done") && parts.length == 2;
    }

    public static void verifyDoneCommand(String input, int numOfTasks) throws InvalidCommandException {
        try {
            String[] parts = input.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;
            boolean result = index > -1 && index < numOfTasks;
            if (result) {

            } else {
                throw new InvalidCommandException("Number is invalid!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(("Please input number after the done command!"));
        }
    }

    public static boolean isListCommand(String input) {
        return input.equals("list");
    }

    public static boolean isNotTerminateCommand(String input) {
        return !input.equals("bye");
    }

    public static void addTasks(String input, List<Task> tasks) throws InvalidInputException, InvalidCommandException {
        String[] parts = input.split(" ", 2);

        try {
            if (input.startsWith("todo")) {
                //Verify the todo command
                verifyTodo(input);
                tasks.add(new Task(parts[1]));
            } else if (input.startsWith("deadline")) {
                //Verify the deadline command
                verifyDeadline(input);
                String[] split = parts[1].split("/by");

                //Should be of format yyyy-mm-dd x:x
                LocalDate date = getDate(split[1]);
                LocalTime time = getTime(split[1]);
                tasks.add(new Deadlines(split[0], date, time));
            } else if (input.startsWith("event")) {
                //Verify the event command
                verifyEvent(input);
                String[] split = parts[1].split("/at");
                LocalDate date = getDate(split[1]);
                LocalTime time = getTime(split[1]);
                tasks.add(new Events(split[0], date, time));
            } else {
                throw new InvalidInputException("Sorry, I don't know what that means :(");
            }
        } catch (InvalidCommandException e) {
            throw e;
        } catch (InvalidInputException e) {
            throw e;
        }
    }

    public static void verifyTodo(String input) throws InvalidCommandException{
        String[] parts = input.split(" ");

        if (parts.length <= 1) {
            throw new InvalidCommandException("Sorry, the description of a todo cannot be empty :(");
        }
    }

    public static void verifyDeadline(String input) throws InvalidCommandException {
        if (input.contains(" /by ")) {
            if (input.charAt(8) == ' ') {
                String[] parts = input.split(" ", 2);
                if (parts[1].startsWith("/by")) {
                    throw new InvalidCommandException("Sorry, missing deadline description :(");
                } else {
                    String[] split = parts[1].split("/by");
                    if (split.length <= 1 || split[1].isBlank()) {
                        throw new InvalidCommandException("Sorry, missing deadline time :(");
                    } else if (split[0].isBlank()) {
                        throw new InvalidCommandException("Sorry, missing deadline description :(");
                    }
                }
            } else {
                throw new InvalidCommandException("Sorry please leave a space after the deadline command!");
            }
        } else {
            throw new InvalidCommandException("Sorry, missing /by keyword, make sure to leave a space before " +
                    "and after the /by keyword!");
        }
    }

    public static void verifyEvent(String input) throws InvalidCommandException {
        if (input.contains(" /at ")) {
            if (input.charAt(5) == ' ') {
                String[] parts = input.split(" ", 2);
                if (parts[1].startsWith("/at")) {
                    throw new InvalidCommandException("Sorry, missing event description :(");
                } else {
                    String[] split = parts[1].split("/at");
                    if (split.length <= 1 || split[1].isBlank()) {
                        throw new InvalidCommandException("Sorry, missing event time :(");
                    } else if (split[0].isBlank()) {
                        throw new InvalidCommandException("Sorry, missing event description :(");
                    }
                }
            } else {
                throw new InvalidCommandException("Sorry please leave a space after the event command!");
            }
        } else {
            throw new InvalidCommandException("Sorry, missing /at keyword, make sure to leave a space before " +
                    "and after the /at keyword!");
        }
    }

    public static List<Task> getSavedTasks() {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "ip", "src", "main", "java", "Data");
        boolean directoryExists = Files.exists(path);

        //checks for the directory
        if (directoryExists) {

            List<Task> tasks= new ArrayList<Task>();

            try {
                File file1 = new File(home + "/ip/src/main/java/Data/Duke.txt");
                File file2 = new File(home + "/ip/src/main/java/Data/Duke2.txt");
                File toBeRead;

                //Checks which file is to be read
                if (file1.exists()) {
                    toBeRead = file1;
                } else if (file2.exists()) {
                    toBeRead = file2;
                } else {
                    //if no file create a new empty file
                    Path newFile = Files.createFile(Path.of(home + "/ip/src/main/java/Data/Duke.txt"));
                    toBeRead = new File(String.valueOf(newFile));
                }
                Scanner savedTasks = new Scanner(toBeRead);

                //Prints out the tasks
                if (savedTasks.hasNext()) {
                    //If there's saved tasks, print and add to list
                    while (savedTasks.hasNext()) {
                        String nextLine = savedTasks.nextLine();
                        Task task = convertToTask(nextLine);
                        tasks.add(task);
                        System.out.println(nextLine);
                    }
                    System.out.println("That's the end of your current tasks!" + "\n");
                } else {
                    //No saved tasks
                    System.out.println("You currently have no tasks");
                }

            } catch (FileNotFoundException e) {
                System.out.println("Error, file not found!");
                //Throw exception?
            } catch (IOException e) {
                e.printStackTrace();
                //Throw exception?
            }
            return tasks;
        } else {
            System.out.println("Sorry the directory does not exists");
            //Directory exception
            return null;
        }

    }

    //Saves the tasks into a new file after termination("bye" command) and deletes the old file
    public static void saveTasks(List<Task> list) {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "ip", "src", "main", "java", "Data");
        boolean directoryExists = Files.exists(path);

        if (directoryExists) {
            File file1 = new File(home + "/ip/src/main/java/Data/Duke.txt");

            try {
                if (file1.exists()) {
                    //creates a new empty file
                    Path newFile = Files.createFile(Path.of(home + "/ip/src/main/java/Data/Duke2.txt"));
                    FileWriter fw = new FileWriter(home + "/ip/src/main/java/Data/Duke2.txt");
                    BufferedWriter bw = new BufferedWriter(fw);

                    //deletes the old file
                    Files.delete(Paths.get(home + "/ip/src/main/java/Data/Duke.txt"));

                    for (int i = 0; i < list.size(); i++) {
                        Task task = list.get(i);
                        bw.write((i + 1) + "." + task.toString());
                        //Writes task on a new line
                        bw.newLine();
                    }

                    bw.close();
                } else {
                    //creates a new empty file
                    Path newFile = Files.createFile(Path.of(home + "/ip/src/main/java/Data/Duke.txt"));
                    FileWriter fw = new FileWriter(home + "/ip/src/main/java/Data/Duke.txt");
                    BufferedWriter bw = new BufferedWriter(fw);

                    //deletes the old file
                    Files.delete(Paths.get(home + "/ip/src/main/java/Data/Duke2.txt"));

                    for (int i = 0; i < list.size(); i++) {
                        Task task = list.get(i);
                        bw.write((i + 1) + "." + task.toString());
                        //Writes the task on a new line
                        bw.newLine();
                    }

                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                //Throw exception?
            }
        } else {
            System.out.println("Sorry directory changed!");
            //Directory exception
        }

    }

    public static String readList(List<Task> list) {
        String listOfTasks = "Here are the tasks in your list:";

        for (int i = 1; i <= list.size(); i++) {
            Task currTask = list.get(i - 1);
                listOfTasks += "\n" + i + "." + currTask.toString();
        }

        if (list.size() == 0) {
            return "Currently no tasks in you list";
        }
        return listOfTasks;
    }

    //Converts each line in file to task
    public static Task convertToTask(String line) {
        if (line.startsWith("[T]", 2)) {
            //Is a todo task
            String[] parts = line.split(" ", 2);
            if (line.contains("[✘]")) {
                return new Task(parts[1]);
            } else {
                return new Task(parts[1]).markDone();
            }
        } else if (line.startsWith("[E]", 2)) {
            DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("d MMM yyyy");
            DateTimeFormatter myTimeFormat = DateTimeFormatter.ofPattern("h:mm a");
            //Is a event task
            String[] parts = line.split(" ", 2);
            String[] split = parts[1].split("\\(at:");
            String desc = split[0];
            String timeInfo = split[1].split("\\)")[0];
            if (line.contains("[✘]")) {
                String[] dateTime = timeInfo.trim().split(", ");
                String date = dateTime[1];
                String time = dateTime[2];
                return new Events(desc, LocalDate.parse(date, myDateFormat), LocalTime.parse(time, myTimeFormat));
            } else {
                String[] dateTime = timeInfo.trim().split(", ");
                String date = dateTime[1];
                String time = dateTime[2];
                return new Events(desc, LocalDate.parse(date, myDateFormat), LocalTime.parse(time, myTimeFormat));
            }
        } else {
            DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("d MMM yyyy");
            DateTimeFormatter myTimeFormat = DateTimeFormatter.ofPattern("h:mm a");
            //Is a deadline task
            String[] parts = line.split(" ", 2);
            String[] split = parts[1].split("\\(by:");
            String desc = split[0];
            String timeInfo = split[1].split("\\)")[0];
            if (line.contains("[✘]")) {
                String[] dateTime = timeInfo.trim().split(", ");
                String date = dateTime[1];
                String time = dateTime[2];
                return new Deadlines(desc, LocalDate.parse(date, myDateFormat), LocalTime.parse(time, myTimeFormat));
            } else {
                String[] dateTime = timeInfo.trim().split(", ");
                String date = dateTime[1];
                String time = dateTime[2];
                return new Deadlines(desc, LocalDate.parse(date, myDateFormat), LocalTime.parse(time, myTimeFormat)).markDone();
            }
        }
    }

    public static LocalDate getDate(String string) throws InvalidCommandException{
        //Currently only accepts date in yyyy-mm-dd format
        //Removing the whitespace before and after the string
        try {
            String timeDate = string.trim();
            String[] parts = timeDate.split(" ", 2);
            String date = parts[0].trim();
            System.out.println(LocalDate.parse(date));
            return LocalDate.parse(date);
        } catch (DateTimeParseException e ) {
            throw new InvalidCommandException("Please give your date in yyyy-mm-dd format!");
        }
    }

    public static LocalTime getTime(String string) throws InvalidCommandException {
        //Currently only accepts time in x:x format
        //Removing the whitespace before and after the string
        try {
            String timeDate = string.trim();
            String[] parts = timeDate.split(" ", 2);
            String date = parts[1].trim();
            System.out.println(LocalTime.parse(date));
            return LocalTime.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Please give your time in hh:mm format!");
        }
    }*/
}

