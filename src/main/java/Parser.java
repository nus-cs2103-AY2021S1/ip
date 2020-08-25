import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.time.format.DateTimeParseException;

public class Parser {
    private TaskList taskList;
    private boolean terminate = false;
    private String filePath;
    private String lineSeparator = System.getProperty("line.separator");
    private String taskDetailsSeparator = " | ";
    Ui ui;

    public Parser() {
        this.ui = new Ui();
        this.taskList = new TaskList();
    }

//    public void initialise() {
//        // Set the file path for the text file
//        this.filePath = System.getProperty("user.dir") + File.separator
//                + "data" + File.separator + "data.txt";
//        try {
//            // Try to load data from text file
//            this.loadFromTextFile();
//        } catch (FileNotFoundException e) {
//            // If text file does not exist yet, create the text file
//            Parser.createFile();
//        }
//        // Take in user input
//        Scanner sc = new Scanner(System.in);  // Create a Scanner object
//        while (!this.terminate) {
//            String command = sc.nextLine();  // Read user input
//            this.handleCommand(command); // Output user input
//        }
//        sc.close();
//    }

//    private static void createFile() {
//        String currentPath = System.getProperty("user.dir");
//        String targetPath = currentPath + File.separator + "data";
//        File dataFolder = new File(targetPath);
//        try {
//            // Make directory if it doesn't exist yet
//            dataFolder.mkdir();
//            File dataFile = new File(targetPath, "data.txt");
//            // Make file if it doesn't exist yet
//            dataFile.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public String[] parse(String command) {
        String[] splitCommand = command.split(" ", 2);
        return splitCommand;
    }

//    private void addTask(String isCompleted, String task) {
//        Task newTask = new Task(isCompleted, task);
//        this.todoList.add(newTask);
//        System.out.println("added: " + task);
//    }
//
//    private void addTodo(String isCompleted, String task) {
//        Todo newTodo = new Todo(isCompleted, task);
//        this.todoList.add(newTodo);
//    }
//
//    private void addDeadline(String isCompleted, String task, String deadline) {
//        Deadline newDeadline = new Deadline(isCompleted, task, deadline);
//        this.todoList.add(newDeadline);
//    }
//
//    private void addEvent(String isCompleted, String task, String eventDate) {
//        Event newEvent = new Event(isCompleted, task, eventDate);
//        this.todoList.add(newEvent);
//    }

//    private void writeToFile(String taskType, String isCompleted, String taskDetails, String date) {
//        try {
//            FileWriter fw = new FileWriter(this.filePath, true);
//            if (date.equals("")) {
//                // For To-do tasks with no date
//                fw.write(taskType + taskDetailsSeparator + isCompleted
//                        + taskDetailsSeparator + taskDetails + this.lineSeparator);
//            } else {
//                // For Event and Deadline tasks
//                fw.write(taskType + taskDetailsSeparator + isCompleted
//                        + taskDetailsSeparator + taskDetails + taskDetailsSeparator
//                        + date + this.lineSeparator);
//            }
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private void modifyLineInTextFile(int lineNumber, String type) throws IOException {
//        String tempFilePath = System.getProperty("user.dir") + File.separator
//                + "data" + File.separator + "dataTemp.txt";
//        File inputFile = new File(this.filePath);
//        File tempFile = new File(tempFilePath);
//
//        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
//        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
//
//        int lineToRemove = lineNumber;
//        String currentLine;
//        int count = 0;
//
//        while ((currentLine = reader.readLine()) != null) {
//            count++;
//            if (count == lineToRemove) {
//                if (type.equals("done")) {
//                    // Modify current line and write to text file if is a "done" command
//                    String lineToWrite[] = currentLine.split("\\|", 3);
//                    writer.write(lineToWrite[0] + "| " + "1"
//                            + " |" + lineToWrite[2] + this.lineSeparator);
//                }
//                continue;
//            }
//            // Write all other lines
//            writer.write(currentLine + this.lineSeparator);
//        }
//        writer.close();
//        reader.close();
//        inputFile.delete();
//        tempFile.renameTo(inputFile);
//    }
//
//    private void loadFromTextFile() throws FileNotFoundException {
//            File file = new File(this.filePath); // create a File for the given file path
//            Scanner s = new Scanner(file); // create a Scanner using the File as the source
//            while (s.hasNext()) {
//                String currentLine = s.nextLine();
//                String details[] = currentLine.split("\\|");
//                switch(details[0].trim()) {
//                    // Trims away whitespaces at the start and end of string
//                    case "T":
//                        this.addTodo(details[1].trim(), details[2].trim());
//                        break;
//                    case "D" :
//                        this.addDeadline(details[1].trim(), details[2].trim(), details[3].trim());
//                        break;
//                    case "E":
//                        this.addEvent(details[1].trim(), details[2].trim(), details[3].trim());
//                }
//            }
//            s.close();
//    }
}