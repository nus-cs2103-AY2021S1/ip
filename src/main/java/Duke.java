import main.java.*;

import java.io.*;
import java.lang.reflect.Array;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // this method prints a horizontal line of fixed length
//    public static void horiLine(int length) {
//        for (int i = 0; i < length; i++) {
//            System.out.print("-");
//            if (i == length - 1) System.out.println("");
//        }
//    }

//    public static void invalidInput(String errMsg) {
//        DukeException exception = new DukeException(errMsg);
//        System.out.println(exception.getMessage());
//    }

//    public static File retrieveTasklistFile() throws IOException {
//        String workingDir = System.getProperty("user.dir");
//        File file = new File(workingDir + File.separator
//                + "tasklist.txt");
//        return file;
//    }

    Storage storage;
    Ui ui;
    TaskList taskList;

    /**
     * Creates an instance of Duke
     * @param filepath the directory of Duke application storage
     */
    public Duke(String filepath) {
        storage = new Storage(filepath);
        // task
        taskList = new TaskList(storage.getStartupTaskList());
        ui = new Ui(taskList, storage);
    }

    /**
     * Interacts with TaskList, Ui, Storage and Parser to facilitate the
     * duke application
     */
    public void run() {
        ui.handleUserInput();
    }


    // Note that all the outputs are formatted with two spaces before.
    public static void main(String[] args) {

        String workingDir = System.getProperty("user.dir");
        Duke application = new Duke(workingDir + File.separator
                + "tasklist.txt");
        application.run();

//        Scanner sc = new Scanner(System.in);
//        // scanner used to scan the file.
//        Scanner fileScanner = null;
//        ArrayList<Task> taskList = new ArrayList();
//        File file = null;
//        BufferedWriter bufferedWriter = null;
//        BufferedReader bufferedReader = null;
//        FileWriter appendFileWriter = null;
//        FileReader fileReader = null;
//
//        // set of code that reads System file on start of Duke program
//        // FileReader and BufferedReaders are instantiated here, used across the main method
//        // FileWriter and BufferedWriters are appending writers, used for operations other than done and delete
//        // overriding writers are used for done and delete commands.
//        try {
//            // the .txt file containing the tasklist
//            file = retrieveTasklistFile();
//
//            // if the file does not exist, create a new file at that directory.
//            if (!file.exists()) {
//                file.createNewFile();
//                fileScanner = new Scanner(file);
//                fileReader = new FileReader(file);
//                bufferedReader = new BufferedReader(fileReader);
//            } else {
//                fileScanner = new Scanner(file);
//                fileReader = new FileReader(file);
//                bufferedReader = new BufferedReader(fileReader);
//                // while there is still a line of string to read, populate the tasklist
//                while(fileScanner.hasNextLine()) {
//                    // dissect the line of String to create Task objects.
//                    String taskDesc = fileScanner.nextLine();
//                    String[] lineComponents = taskDesc.split(" ", 2);
//                    Task toAdd = null;
//                    if (taskDesc.contains("[T]")) {
//                        toAdd = new Todo(lineComponents[1]);
//                    } else if (taskDesc.contains("[D]")) {
//                        toAdd = new Deadline(lineComponents[1]);
//                    } else if (taskDesc.contains("[E]")) {
//                        toAdd = new Event(lineComponents[1]);
//                    } else {
//                        System.out.println("Couldn't read saved tasks from System");
//                    }
//                    if (taskDesc.contains("\u2713")) {
//                        // description has a tick
//                        toAdd.markDone();
//                    }
//                    taskList.add(toAdd);
//                }
//            }
//            // this fileWriter is used to appending operations
//            appendFileWriter = new FileWriter(file, true);
//            bufferedWriter = new BufferedWriter(appendFileWriter);
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//
//        // welcome message
//        horiLine(60);
//        System.out.println("  Hello! I'm IntelliGent!\n  What can I do for you?");
//        horiLine(60);
//        while (sc.hasNextLine()) {
//            String nextInput = sc.nextLine();
//            String[] commandComponents = nextInput.split(" ", 2);
//            String taskType = commandComponents[0];
//            if (nextInput.equals("bye")) {
//                horiLine(60);
//                System.out.println("  Bye. Hope to see you again soon!");
//                horiLine(60);
//                sc.close();
//                fileScanner.close();
//                break;
//            } else if (nextInput.equals("list")) {
//                horiLine(60);
//                for (int i = 0; i < taskList.size(); i++) {
//                    System.out.println("  " + (i+1) + "." + taskList.get(i));
//                }
//                horiLine(60);
//            } else if (commandComponents[0].equals("done")) {
//                horiLine(60);
//                FileWriter overrideFileWriter = null;
//                BufferedWriter overrideBuffWriter = null;
//                try {
//                    overrideFileWriter = new FileWriter(file);
//                    overrideBuffWriter = new BufferedWriter(overrideFileWriter);
//                    int taskIndex = Integer.parseInt(commandComponents[1]) - 1;
//                    Task toMark = taskList.get(taskIndex);
//                    toMark.markDone();
//                    for (int i = 0; i < taskList.size(); i++) {
//                        Task toWrite = taskList.get(i);
//                        overrideBuffWriter.write(toWrite.toString() + System.lineSeparator());
//                    }
//                    overrideBuffWriter.flush();
//                    System.out.println("  Nice! I've marked this task as done:");
//                    System.out.println("    " + toMark.toString());
//                } catch (IOException ioe) {
//                    ioe.printStackTrace();
//                }
//                horiLine(60);
//            } else if (commandComponents[0].equals("delete")) {
//                horiLine(60);
//
//                FileWriter overrideFileWriter = null;
//                BufferedWriter overrideBuffWriter = null;
//                try {
//                    overrideFileWriter = new FileWriter(file);
//                    overrideBuffWriter = new BufferedWriter(overrideFileWriter);
//                    int taskIndex = Integer.parseInt(commandComponents[1]) - 1;
//                    Task toDisplay = taskList.get(taskIndex);
//                    taskList.remove(taskIndex);
//                    for (int i = 0; i < taskList.size(); i++) {
//                        Task toWrite = taskList.get(i);
//                        overrideBuffWriter.write(toWrite.toString() + System.lineSeparator());
//                    }
//                    overrideBuffWriter.flush();
//                    System.out.println("  Noted. I've removed this task:");
//                    System.out.println("    " + toDisplay.toString());
//                    System.out.println("  Now you have " + taskList.size() + " tasks in the list.");
//                } catch (IOException ioe) {
//                    ioe.printStackTrace();
//                }
//                horiLine(60);
//            } else {
//                // 1. split the input based on the first word(task type)
//                // 2. if there is a valid string after the first word, work as intended
//                // 3. else display corresponding error.
//                // adding tasks
//                horiLine(60);
//                Task taskToAdd;
//                if (taskType.equals("event")) {
//                    // Event task
//                    if (commandComponents.length == 1) {
//                        // description is empty
//                        invalidInput("  \u2639 OOPS!!! The description of a todo cannot be empty.");
//                    } else {
//                        try {
//                            taskToAdd = new Event(commandComponents[1]);
//                            bufferedWriter.write(taskToAdd.toString() + System.lineSeparator());
//                            bufferedWriter.flush();
//                            System.out.println("  Got it. I've added this task:\n"
//                                    + "    " + taskToAdd.toString() + "\n  Now you have "
//                                    + (taskList.size()+1) + " tasks in the list.");
//                            taskList.add(taskToAdd);
//                        } catch (ArrayIndexOutOfBoundsException e) {
//                            invalidInput("  \u2639 OOPS!!! An event task must be input with a forward slash and the deadline");
//                        } catch (IOException ioe) {
//                            ioe.printStackTrace();
//                        } catch (DateTimeParseException dtpe) {
//                            System.out.println("Sorry, please key in a valid date and time format");
//                        }
//                    }
//                } else if (taskType.equals("deadline")) {
//                    // Deadline Task
//                    if (commandComponents.length == 1) {
//                        // description is empty
//                        invalidInput("  \u2639 OOPS!!! The description of a todo cannot be empty.");
//                    } else {
//                        try {
//                            // the exception is thrown when we call the toString method.
//                            taskToAdd = new Deadline(commandComponents[1]);
//                            bufferedWriter.write(taskToAdd.toString() + System.lineSeparator());
//                            bufferedWriter.flush();
//                            System.out.println("  Got it. I've added this task:\n"
//                                    + "    " + taskToAdd.toString() + "\n  Now you have "
//                                    + (taskList.size()+1) + " tasks in the list.");
//                            taskList.add(taskToAdd);
//                        } catch (ArrayIndexOutOfBoundsException e) {
//                            invalidInput("  \u2639 OOPS!!! A deadline task must be input with a forward slash and the deadline");
//                        } catch (IOException ioe) {
//                            ioe.printStackTrace();
//                        } catch (DateTimeParseException dtpe) {
//                            System.out.println("Sorry, please key in a valid date and time format");
//                        }
//                    }
//                } else if (taskType.equals("todo")){
//                    // Todo task
//                    if (commandComponents.length == 1) {
//                        // description is empty
//                        invalidInput("  \u2639 OOPS!!! The description of a todo cannot be empty.");
//                    } else {
//                        try {
//                            taskToAdd = new Todo(commandComponents[1]);
//                            bufferedWriter.write(taskToAdd.toString() + System.lineSeparator());
//                            bufferedWriter.flush();
//                            System.out.println("  Got it. I've added this task:\n"
//                                    + "    " + taskToAdd.toString() + "\n  Now you have "
//                                    + (taskList.size()+1) + " tasks in the list.");
//                            taskList.add(taskToAdd);
//                        } catch (IOException ioe) {
//                            ioe.printStackTrace();
//                        }
//                    }
//                } else {
//                    // invalid input, detect it and create a DukeException,
//                    // display the error message.
//                    invalidInput("  \u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
//                }
//                // include done to the test cases
//                horiLine(60);
//            }
//        }
    }
}
