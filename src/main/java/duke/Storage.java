package main.java.duke;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File file = null;

    private Scanner fileScanner = null;

    private FileWriter appendFileWriter = null;
    private FileReader fileReader = null;

    private BufferedWriter bufferedAppendWriter = null;
    private BufferedReader bufferedReader = null;

    private ArrayList<Task> startupTaskList = new ArrayList<>();

    /**
     * Creates an instance of Storage and initializes File, Scanner, FileWriter, FileReader,
     * BufferedWriter and BufferedReader, some of which are used to do appending operations on the tasklist.txt file
     * @param filepath
     */
    public Storage(String filepath) {
        // what about the IOException
        file = new File(filepath);
        try {
            if (!file.exists()) {
                file.createNewFile();
                fileScanner = new Scanner(file);
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
            } else {
                fileScanner = new Scanner(file);
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                // while there is still a line of string to read, populate the tasklist
                while(fileScanner.hasNextLine()) {
                    // dissect the line of String to create Task objects.
                    String taskDesc = fileScanner.nextLine();
                    String[] lineComponents = taskDesc.split(" ", 2);
                    Task toAdd = null;
                    if (taskDesc.contains("[T]")) {
                        toAdd = new Todo(lineComponents[1]);
                    } else if (taskDesc.contains("[D]")) {
                        toAdd = new Deadline(lineComponents[1]);
                    } else if (taskDesc.contains("[E]")) {
                        toAdd = new Event(lineComponents[1]);
                    } else {
                        System.out.println("Couldn't read saved tasks from System");
                    }
                    if (taskDesc.contains("\u2713")) {
                        // description has a tick
                        toAdd.markDone();
                    }
                    startupTaskList.add(toAdd);
                }
            }
            // appendFileWriter appends new text whenever we write to the file
            appendFileWriter = new FileWriter(file, true);
            bufferedAppendWriter = new BufferedWriter(appendFileWriter);
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Write a string input to the text file.
     * @param input
     */
    public void writeToFile(String input) {
        try {
            bufferedAppendWriter.write(input + System.lineSeparator());
            bufferedAppendWriter.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Overrides the existing text file using the overriding bufferedWriter.
     * @param taskList
     */
    public void changeFileContents(TaskList taskList) {
        try {
            // the FileWriter and BufferedWriters are created on demand here
            // as they tend to change over time.
            FileWriter overrideFileWriter = new FileWriter(file);
            BufferedWriter bufferedOverrideWriter = new BufferedWriter(overrideFileWriter);;
            for (int i = 0; i < taskList.size(); i++) {
                Task toWrite = taskList.getTask(i);
                bufferedOverrideWriter.write(toWrite.toString() + System.lineSeparator());
            }
            bufferedOverrideWriter.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Returns the task list saved previously in the text file.
     * @return
     */
    public ArrayList<Task> getStartupTaskList() {
        return startupTaskList;
    }

}
