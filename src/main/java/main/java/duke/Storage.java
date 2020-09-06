package main.java.duke;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File file;

    private FileWriter appendFileWriter;
    private FileReader fileReader;

    private BufferedWriter bufferedAppendWriter;
    private BufferedReader bufferedReader;

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
            } else {
                Scanner fileScanner = new Scanner(file);
                // while there is still a line of string to read, populate the tasklist
                while(fileScanner.hasNextLine()) {
                    // dissect the line of String to create Task objects.
                    String taskDesc = fileScanner.nextLine();
                    String[] lineComponents = taskDesc.split(" ", 2);
                    Task toAdd;
                    if (taskDesc.contains("[T]")) {
                        toAdd = new Todo(lineComponents[1]);
                    } else if (taskDesc.contains("[D]")) {
                        toAdd = new Deadline(lineComponents[1]);
                    } else if (taskDesc.contains("[E]")) {
                        toAdd = new Event(lineComponents[1]);
                    } else {
                        toAdd = null;
                        System.out.println("Couldn't read saved tasks from System");
                    }
                    if (taskDesc.contains("\u2713")) {
                        // description has a tick
                        toAdd.markDone();
                    }
                    startupTaskList.add(toAdd);
                }
            }
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            // appendFileWriter appends new text whenever we write to the file
            // to maintain the previous entries.
            appendFileWriter = new FileWriter(file, true);
            assert appendFileWriter != null : "appendFileWriter not initialized properly";
            bufferedAppendWriter = new BufferedWriter(appendFileWriter);
            assert appendFileWriter != null : "bufferedAppendWriter not initialized properly";
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Writes a string input to the text file.
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
                assert toWrite != null : "task to be written into .txt storage file is null";
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
