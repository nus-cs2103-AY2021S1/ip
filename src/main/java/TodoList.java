import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {
    private static ArrayList<Task> todoList = new ArrayList<>();
    private static boolean terminate = false;
    private static String filePath;
    private static String lineSeparator = System.getProperty("line.separator");
    private static String taskDetailsSeparator = " | ";

    public static void initialise() {
        // Set the file path for the text file
        TodoList.filePath = System.getProperty("user.dir") + File.separator
                + "data" + File.separator + "data.txt";
        try {
            // Try to load data from text file
            TodoList.loadFromTextFile();
        } catch (FileNotFoundException e) {
            // If text file does not exist yet, create the text file
            TodoList.createFile();
        }
        // Take in user input
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        while (!TodoList.terminate) {
            String command = sc.nextLine();  // Read user input
            TodoList.handleCommand(command); // Output user input
        }
        sc.close();
    }

    private static void createFile() {
        String currentPath = System.getProperty("user.dir");
        String targetPath = currentPath + File.separator + "data";
        File dataFolder = new File(targetPath);
        try {
            // Make directory if it doesn't exist yet
            dataFolder.mkdir();
            File dataFile = new File(targetPath, "data.txt");
            // Make file if it doesn't exist yet
            dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleCommand(String command) {
        String[] splitCommand = command.split(" ", 2);
        try {
            try {
                Command enumCommand = Command.stringToEnum(splitCommand[0]);
                switch (enumCommand) {
                    case LIST: {
                        for (int i = 0; i < todoList.size(); i++) {
                            int index = i + 1;
                            System.out.println(index + ". " + todoList.get(i).toString());
                        }
                        // break is necessary to prevent fall-through
                        break;
                    }
                    case BYE: {
                        TodoList.terminate = true;
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    }
                    case DONE: {
                        int index = Integer.parseInt(splitCommand[1]);
                        Task targetTask = todoList.get(index - 1);
                        String message = targetTask.completeTask();
                        try {
                            System.out.println("here");
                            TodoList.modifyLineInTextFile(index, "done");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(message);
                        break;
                    }
                    case DEADLINE: {
                        String[] splitDeadline = splitCommand[1].split(" /by ");
                        System.out.println("Got it. I've added this task:");
                        TodoList.addDeadline("0", splitDeadline[0], splitDeadline[1]);
                        TodoList.writeToFile("D", "0", splitDeadline[0], splitDeadline[1]);
                        int size = todoList.size();
                        System.out.println("Now you have " + size + " tasks in the list.");
                        break;
                    }
                    case EVENT: {
                        String[] splitEvent = splitCommand[1].split(" /at ");
                        System.out.println("Got it. I've added this task:");
                        TodoList.addEvent("0", splitEvent[0], splitEvent[1]);
                        TodoList.writeToFile("E", "0", splitEvent[0], splitEvent[1]);
                        int size = todoList.size();
                        System.out.println("Now you have " + size + " tasks in the list.");
                        break;
                    }
                    case TODO: {
                        if (splitCommand.length <= 1) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty");
                        } else {
                            System.out.println("Got it. I've added this task:");
                            TodoList.addTodo("0", splitCommand[1]);
                            TodoList.writeToFile("T", "0", splitCommand[1], "");
                            int size = todoList.size();
                            System.out.println("Now you have " + size + " tasks in the list.");
                        }
                        break;
                    }
                    case DELETE: {
                        int index = Integer.parseInt(splitCommand[1]);
                        Task targetTask = todoList.get(index - 1);
                        todoList.remove(index - 1);
                        try {
                            TodoList.modifyLineInTextFile(index, "delete");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(targetTask.toString());
                        int size = todoList.size();
                        System.out.println("Now you have " + size + " tasks in the list.");
                        break;
                    }
                    case INVALID: {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (NumberFormatException error) {
                // When "done" is not followed by a valid number
                throw new DukeException("OOPS!!! Please enter a valid index!");
            } catch (ArrayIndexOutOfBoundsException error) {
                // When "done" is not followed by any number
                throw new DukeException("OOPS!!! Please enter a valid index!");
            } catch (IndexOutOfBoundsException error) {
                // When "done is followed by a number that is out of range
                throw new DukeException("OOPS!!! That index is out of range!");
            }
        } catch (DukeException error) {
            System.out.println(error.getMessage());
        }
    }

    private static void addTask(String isCompleted, String task) {
        Task newTask = new Task(isCompleted, task);
        todoList.add(newTask);
        System.out.println("added: " + task);
    }

    private static void addTodo(String isCompleted, String task) {
        Todo newTodo = new Todo(isCompleted, task);
        todoList.add(newTodo);
    }

    private static void addDeadline(String isCompleted, String task, String deadline) {
        Deadline newDeadline = new Deadline(isCompleted, task, deadline);
        todoList.add(newDeadline);
    }

    private static void addEvent(String isCompleted, String task, String eventDate) {
        Event newEvent = new Event(isCompleted, task, eventDate);
        todoList.add(newEvent);
    }

    private static void writeToFile(String taskType, String isCompleted, String taskDetails, String date) {
        try {
            FileWriter fw = new FileWriter(TodoList.filePath, true);
            if (date.equals("")) {
                // For To-do tasks with no date
                fw.write(taskType + taskDetailsSeparator + isCompleted
                        + taskDetailsSeparator + taskDetails + TodoList.lineSeparator);
            } else {
                // For Event and Deadline tasks
                fw.write(taskType + taskDetailsSeparator + isCompleted
                        + taskDetailsSeparator + taskDetails + taskDetailsSeparator
                        + date + TodoList.lineSeparator);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void modifyLineInTextFile(int lineNumber, String type) throws IOException {
        String tempFilePath = System.getProperty("user.dir") + File.separator
                + "data" + File.separator + "dataTemp.txt";
        File inputFile = new File(TodoList.filePath);
        File tempFile = new File(tempFilePath);

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        int lineToRemove = lineNumber;
        String currentLine;
        int count = 0;

        while ((currentLine = reader.readLine()) != null) {
            count++;
            if (count == lineToRemove) {
                if (type.equals("done")) {
                    // Modify current line and write to text file if is a "done" command
                    String lineToWrite[] = currentLine.split("\\|", 3);
                    writer.write(lineToWrite[0] + "| " + "1"
                            + " |" + lineToWrite[2] + TodoList.lineSeparator);
                }
                continue;
            }
            // Write all other lines
            writer.write(currentLine + TodoList.lineSeparator);
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    private static void loadFromTextFile() throws FileNotFoundException {
            File file = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(file); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String currentLine = s.nextLine();
                String details[] = currentLine.split("\\|");
                switch(details[0].trim()) {
                    // Trims away whitespaces at the start and end of string
                    case "T":
                        TodoList.addTodo(details[1].trim(), details[2].trim());
                        break;
                    case "D" :
                        TodoList.addDeadline(details[1].trim(), details[2].trim(), details[3].trim());
                        break;
                    case "E":
                        TodoList.addEvent(details[1].trim(), details[2].trim(), details[3].trim());
                }
            }
            s.close();
    }
}