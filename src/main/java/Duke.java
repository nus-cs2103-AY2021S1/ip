import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
        

public class Duke {

    private static ArrayList<Task> storedTasks = new ArrayList<>();
    private static int globalIndex = 1;

    public static void init() throws DukeException {
        try {
            String workingDir = System.getProperty("user.dir");
            java.nio.file.Path path = java.nio.file.Paths.get(workingDir, "storage", "data.txt");
            if (!java.nio.file.Files.exists(path)) {
                File newDir = new File("storage" + File.separator + "data.txt");
                newDir.getParentFile().mkdirs();
                newDir.createNewFile();
            } else {
                String filePath = "storage" + File.separator + "data.txt";
                FileReader fr = new FileReader(filePath);
                BufferedReader br = new BufferedReader(fr);
                String currentLine;
                while ((currentLine = br.readLine()) != null) {
                    String task[] = currentLine.split("\\|");
                    if (task.length != 0 && !task[0].equals("")) {
                        switch(task[1]) {
                            case "T":
                                ToDo todo = new ToDo(task[3]);
                                if (Integer.parseInt(task[2]) == 1) todo.markAsDone();
                                storedTasks.add(todo);
                                break;
                            case "D":
                                Deadline deadline = new Deadline(task[3]);
                                if (Integer.parseInt(task[2]) == 1) deadline.markAsDone();
                                storedTasks.add(deadline);
                                break;
                            case "E":
                                Event event = new Event(task[3]);
                                if (Integer.parseInt(task[2]) == 1) event.markAsDone();
                                storedTasks.add(event);
                                break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new DukeException("Oops");
        }
    }
    public static void welcome() {
        try {
            init();
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("\n Always at your service, \n" + logo + "\n");
            System.out.println("Your Majesty, I am your loyal Duke.");
            System.out.println("I offer a range of administrative services. Do type 'assist' to see the comprehensive list.");
            System.out.println();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
       
    }

    public static String dashedLineBreak() {
        String dashedLine = "- ";
        return dashedLine.repeat(32);
    }
    
    public static void assist() {
        System.out.println(dashedLineBreak());
        System.out.println("Greetings, Your Majesty. \n");
        System.out.println("Use any of the commands on the left to access my quality services:");
        System.out.println("\ttodo [TASK]: Adds a todo to your scroll");
        System.out.println("\tdeadline [TASK] /by [DATE AND/OR TIME]: Adds a deadline to your scroll");
        System.out.println("\tevent [TASK] /on [DATE AND/OR TIME]: Adds an event to your scroll");
        System.out.println("\tscroll: Displays your scroll - your list of tasks");
        System.out.println("\tconquer [NUMBER]: Marks the particular item on your scroll as DONE");
        System.out.println("\tdelete [NUMBER]: Deletes the particular item from your scroll");
        System.out.println("\tdismiss: This will be my cue to leave.");
        System.out.println();
        System.out.println("Now, how may I serve you?");
        System.out.println(dashedLineBreak() + "\n");
    }
    
    // Handles cases where user enters empty input
    public static void validateScannerInput(String input) throws DukeException {
        if (input.trim().length() == 0) {
            throw new DukeException("Come again, Your Majesty?");
        }
    }
    
    // Handles cases where user does not enter a valid description for tasks
    public static void validateAdd(String[] task) throws DukeException {
        if ((task.length == 1 || task[1].trim().length() == 0) &&
                (task[0].equalsIgnoreCase("todo") ||
                        task[0].equalsIgnoreCase("event") ||
                        task[0].equalsIgnoreCase("deadline"))
        ) {
            throw new DukeException("Do give me more details about this " + task[0] + ", Your Majesty.");
        }
    }
    
    // Handles cases where user does not enter the correct / commands (i.e. /by for deadlines, /on for events)
    public static void validateSlashCommands(String[] task) throws DukeException {
        if(task[0].equalsIgnoreCase("deadline") && task[1].split("/by ", 2).length == 1){
            throw new DukeException("Use /by for deadlines, Your Majesty.");
        } else if (task[0].equalsIgnoreCase("event") &&  task[1].split("/on ", 2).length == 1){
            throw new DukeException("Use /on for events, Your Majesty.");
        }
    }
    
    // Handles cases where user enters a number that does not correspond to an existing task
    public static void validateIndex(int taskNumber) throws DukeException{
        if(taskNumber > storedTasks.size() || taskNumber <= 0) {
            throw new DukeException("Your Majesty, there's no such agenda in my detailed records.");
        }
    }

    // Handles cases where user does not input a valid number after conquer/delete commands
    public static void validateConquerDelete(String[] command) throws DukeException {
        try {
            Integer.parseInt(sanitiseInput(command[1]));
        } catch (Exception err) {
            throw new DukeException("Please enter valid numbers after your command, Your Majesty.");
        } 
    }
    
    // Removes leading whitespaces in a String
    public static String sanitiseInput(String input) {
        return input.stripLeading();
    }

    // Add a new task 
    public static void writeToFile(String type, int done, String content) {
        try {
            String workingDir = System.getProperty("user.dir");
            java.nio.file.Path path = java.nio.file.Paths.get(workingDir, "storage", "data.txt");
            if (!java.nio.file.Files.exists(path)) {
                File newDir = new File("storage" + File.separator + "data.txt");
                newDir.getParentFile().mkdirs();
                newDir.createNewFile();
            } else {
                FileWriter fw = new FileWriter("storage" + File.separator + "data.txt", true);
                fw.write(globalIndex + "|" + type + "|" + done + "|" + content + "\n");
                globalIndex++;
                fw.close();
            }
            
        } catch (Exception err) {
            System.out.println(err);
        }
    }
    
    public static void removeFromFile(int taskIndex) { 
        try {
            int index = 1;
            String tempFilePath = "storage" + File.separator + "temp.txt";
            String oldFilePath = "storage" + File.separator + "data.txt";
            File tempFile = new File(tempFilePath);
            FileReader fr = new FileReader(oldFilePath);
            BufferedReader br = new BufferedReader(fr);
            String currentLine; 
            String[] task;
            FileWriter fw = new FileWriter(tempFile, true);
            
            while((currentLine = br.readLine()) != null) {
                task = currentLine.split("\\|");
                if (Integer.parseInt(task[0]) != taskIndex) {
                    fw.write(index + "|" + task[1] + "|" + task[2] + "|" + task[3] + "\n");
                    index++;
                }
            }
            globalIndex = index;
            fw.close();
            fr.close();
            br.close();
            Files.copy(Paths.get(tempFilePath), Paths.get(oldFilePath), StandardCopyOption.REPLACE_EXISTING);
            Files.delete(Paths.get(tempFilePath));
        } catch (Exception err) {
            System.out.println(err);
        }
    }
    
    public static void overwriteInFile(int taskIndex, String taskType, Task taskToConquer) {
        try {
            int index = 1;
            String tempFilePath = "storage" + File.separator + "temp.txt";
            String oldFilePath = "storage" + File.separator + "data.txt";
            File tempFile = new File(tempFilePath);
            FileReader fr = new FileReader(oldFilePath);
            BufferedReader br = new BufferedReader(fr);
            String currentLine;
            String[] task;
            FileWriter fw = new FileWriter(tempFile, true);

            while((currentLine = br.readLine()) != null) {
                task = currentLine.split("\\|");
                    if (Integer.parseInt(task[0]) != taskIndex) {
                        fw.write(currentLine + "\n");
                    } else {
                        fw.write(taskIndex + "|" + taskType + "|" + 1 + "|" + taskToConquer.getDescription() + "\n");
                    }
            }
            fw.close();
            fr.close();
            br.close();
            Files.copy(Paths.get(tempFilePath), Paths.get(oldFilePath), StandardCopyOption.REPLACE_EXISTING);
            Files.delete(Paths.get(tempFilePath));
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public static void printAllTasks() {
        if (storedTasks.size() == 0) {
            System.out.println("Your scroll is currently empty, Your Majesty.");
        } else {
            System.out.println(dashedLineBreak());
            System.out.println("Your current scroll, Your Majesty:");
            for (Task task : storedTasks) {
                System.out.printf("\t%s.%s", storedTasks.indexOf(task) + 1, task);
                System.out.println();
            }
        }
        System.out.println(dashedLineBreak());
        System.out.println();
    }
    
    
    public static void addTask(String task) {
        try {
            
            validateScannerInput(task);
            
            String splitTask[] = task.split(" ", 2);
            Task newTask = null;
            validateAdd(splitTask);
            validateSlashCommands(splitTask);
            
            switch(splitTask[0].toLowerCase()) {
                case "todo":
                    newTask = new ToDo(splitTask[1]);
                    writeToFile("T", 0, splitTask[1]);
                    break;
                case "deadline":
                    newTask = new Deadline(splitTask[1]);
                    writeToFile("D", 0, splitTask[1]);
                    break;
                case "event":
                    newTask = new Event(splitTask[1]);
                    writeToFile("E", 0, splitTask[1]);
                    break;
                default:
                    throw new DukeException("I'm afraid I do not understand that command, Your Majesty.");
            }
            if (newTask != null) {
                storedTasks.add(newTask);
                System.out.println(dashedLineBreak());
                System.out.println("Your Majesty, I've added the writing:");
                System.out.println("\t" + newTask);
                System.out.printf("You have %s writing(s) on your scroll as of now. \n", storedTasks.size());
                System.out.println(dashedLineBreak());
                System.out.println();
            }
        } catch (DukeException err) {
            System.out.println(err.getMessage());
            System.out.println(dashedLineBreak());
            System.out.println();
        }
    }
    

    public static void conquerTask(String[] command) {
        try {
            validateConquerDelete(command);
            int taskNumber = Integer.parseInt(sanitiseInput(command[1]));
            validateIndex(taskNumber);
            
            storedTasks.get(taskNumber - 1).markAsDone();
            String taskType;
            if (storedTasks.get(taskNumber - 1) instanceof ToDo) {
                taskType = "T";
            } else if (storedTasks.get(taskNumber - 1) instanceof Deadline) {
                taskType = "D";
            } else {
                taskType = "E";
            }
            overwriteInFile(taskNumber, taskType, storedTasks.get(taskNumber - 1));
            System.out.println(dashedLineBreak());
            System.out.println("As you wish, Your Majesty. I have marked this as conquered.");
            System.out.println("\t" + storedTasks.get(taskNumber - 1));
            System.out.println(dashedLineBreak());
        } catch (DukeException err) {
            System.out.println(err.getMessage());
            System.out.println(dashedLineBreak());
        }
        System.out.println();
    }
    
    public static void deleteTask(String[] command) {
        try {
            validateConquerDelete(command);
            int taskNumber = Integer.parseInt(sanitiseInput(command[1]));
            validateIndex(taskNumber);
            
            Task deletedTask = storedTasks.get(taskNumber - 1);
            storedTasks.remove(deletedTask);
            removeFromFile(taskNumber);
            System.out.println(dashedLineBreak());
            System.out.println("As you wish, Your Majesty. I have removed this writing.");
            System.out.println("\t" + deletedTask);
            System.out.printf("You have %s writing(s) on your scroll as of now. \n", storedTasks.size());
            System.out.println(dashedLineBreak());
        } catch (DukeException err) {
            System.out.println(err.getMessage());
            System.out.println(dashedLineBreak());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        welcome();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String[] splitUserInput = userInput.split(" ", 2);
            
            // Handle cases where there are leading whitespaces in user input
            if (splitUserInput[0].isBlank()) { 
                userInput = sanitiseInput(userInput);
                splitUserInput = userInput.split(" ", 2);
            }

            switch(splitUserInput[0].toLowerCase()) {
                case "dismiss":
                    System.out.println(dashedLineBreak());
                    System.out.println("Your wish is my command, Your Majesty. Till I see you again. \n");
                    sc.close();
                    System.exit(0);
                    break;
                case "scroll":
                    printAllTasks();
                    break;
                case "conquer":
                    conquerTask(splitUserInput);
                    break;
                case "delete":
                    deleteTask(splitUserInput);
                    break;
                case "assist":
                    assist();
                    break;
                default:
                    addTask(userInput);
            }
        }
    }
}
