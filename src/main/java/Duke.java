import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class Duke {
    // all valid commands
    private enum Command {
        BYE,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DONE,
        DELETE
    }
    
    // dir where tasks are saved
    private static final String FILE = "data/serina.txt";
    private static final String FILE_PATH = "data";

    // validate command format
    private static void validateCommandDesc(String desc, Command type) throws DukeException {
        // trim whitespaces
        String result = desc.trim();
        if (result.isEmpty()) {
            throw new DukeException("Command description cannot be empty.");
        } 
        if (type == Command.DEADLINE) {
            if (!result.contains("/by") || result.split("/by").length <= 1 || result.split("/by")[0].isEmpty()) {
                throw new DukeException("Be sure to include a task description and date in the correct format.");
            }
        }
        if (type == Command.EVENT) {
            if (!result.contains("/at") || result.split("/at").length <= 1 || result.split("/at")[0].isEmpty()) {
                throw new DukeException("Be sure to include a task description and date in the correct format.");
            }
        }
    }

    // validate command format
    private static void validateCommandDesc(int taskNum, Command type, List<Task> lst) throws DukeException {
        if (taskNum > lst.size()) {
            throw new DukeException("You have no such task. Please check your task number.");
        }
    }
    
    // helper function to check if task is done
    private static boolean isTaskDone(String number) {
        int num = Integer.parseInt(number);
        return num == 1;
    }
    
    // helper function to display tasks
    private static void printTasks(List<Task> tasks) {
        System.out.println("----- Here are your current tasks: ");
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + tasks.get(i));
        }
    }
    
    // read file and add tasks to list
    private static void readFile(String file, List<Task> lst) throws FileNotFoundException {
        File f = new File(file);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String inputLine = s.nextLine();
            String[] splitInput = inputLine.split(" \\| ");
            Command command = Command.valueOf(splitInput[0]);
            switch (command){ 
                case TODO:
                    lst.add(new ToDoTask(splitInput[2], isTaskDone(splitInput[1])));
                    break;
                case DEADLINE:
                    lst.add(new DeadlineTask(splitInput[2], isTaskDone(splitInput[1]), splitInput[3]));
                    break;
                case EVENT:
                    lst.add(new EventTask(splitInput[2], isTaskDone(splitInput[1]), splitInput[3]));
                    break;
            }
        }
    }
    
    // create new file if no existing file, otherwise add tasks to existing file
    private static void writeToFile(String task) throws IOException {
        File f = new File(FILE_PATH);
        File file = new File(FILE);
        if (!f.isDirectory()) {
            f.mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        } 
        FileWriter fw = new FileWriter(file, true);
        fw.write(task+"\n");
        fw.close();
    }

    // modify file (marking task as done or deleting task)
    private static void modifyFile(Command command, int taskNum) throws IOException {
        Path path = Paths.get(FILE);
        List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
        switch (command) {
            case DELETE:
                fileContent.remove(taskNum);
                break;
            case DONE:
                String task = fileContent.get(taskNum);
                String modifiedTask = task.replaceFirst("0" , "1");
                fileContent.set(taskNum, modifiedTask);
                break;
        }
        
        Files.write(path, fileContent, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        // initialize scanner and tasks list
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        
        // display welcome message
        String welcomeMessage = "----- Serina here, let me know if you need any assistance";
        System.out.println(welcomeMessage);
        
        // read the tasks file if and process the tasks, printing them if there is a file 
        try {
           readFile(FILE, tasks);
           printTasks(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("----- You have no tasks saved as of yet. Feel free to add tasks and I will track them for you");
        }
        
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String[] splitInput = userInput.split(" ", 2);

            try {
                Command command = Command.valueOf(splitInput[0].toUpperCase());
                String value;
                value = splitInput.length > 1 ? splitInput[1] : "";

                // exit the application
                if (command == Command.BYE) {
                    System.out.print("----- Goodbye, call me when you need me.");
                    break;
                }

                switch (command) {
                    // list task
                    case LIST:
                        printTasks(tasks);
                        break;
                    // mark task as done and print it
                    case DONE:
                        validateCommandDesc(Integer.parseInt(value), Command.DONE, tasks);
                        int taskNum = Integer.parseInt(value) - 1;
                        modifyFile(Command.DONE, taskNum);
                        System.out.println(tasks.get(taskNum).markAsDone());
                        break;
                    // add toDoTask
                    case TODO:
                        Duke.validateCommandDesc(value, Command.TODO);
                        tasks.add(new ToDoTask(value, false));
                        writeToFile("TODO | 0 | " + value);
                        System.out.println("----- Received, added the following task:\n" + tasks.get(tasks.size() - 1));
                        System.out.println("You now have " + tasks.size() + " pending tasks.");
                        break;
                    // add deadlineTask
                    // add eventTask
                    case DEADLINE:
                    case EVENT:
                        String[] splitValue;
                        if (command == Command.DEADLINE) {
                            Duke.validateCommandDesc(value, Command.DEADLINE);
                            splitValue = value.split("/by ");
                            writeToFile("DEADLINE | 0 | " + splitValue[0] + "| " + splitValue[1]);
                            tasks.add(new DeadlineTask(splitValue[0], false, splitValue[1]));
                        } else {
                            Duke.validateCommandDesc(value, Command.EVENT);
                            splitValue = value.split("/at ");
                            writeToFile("EVENT | 0 | " + splitValue[0] + "| " + splitValue[1]);
                            tasks.add(new EventTask(splitValue[0], false, splitValue[1]));
                        }
                        System.out.println("----- Received, added the following task:\n" + tasks.get(tasks.size() - 1));
                        System.out.println("You now have " + tasks.size() + " pending tasks.");
                        break;
                    // delete task
                    case DELETE:
                        validateCommandDesc(Integer.parseInt(value), Command.DELETE, tasks);
                        int taskIndex = Integer.parseInt(value) - 1;
                        Task removedTask = tasks.get(taskIndex);
                        tasks.remove(taskIndex);
                        modifyFile(Command.DELETE, taskIndex);
                        System.out.println("----- Received, deleted the following task:\n" + removedTask);
                        System.out.println("You now have " + tasks.size() + " pending tasks.");
                }
            }
            // catch error when command is invalid
            catch (IllegalArgumentException ex) {
                System.out.println("----- I can't help you with that request, try something else.");
            }
            // catch invalid command description errors
            catch (DukeException ex) {
                String result = "----- You entered a valid command but I can't carry it out. " + ex.getMessage();
                System.out.println(result);
            }
            catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }
}
