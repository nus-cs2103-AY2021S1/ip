package main.java.com.jacob.duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import main.java.com.jacob.duke.task.Deadline;
import main.java.com.jacob.duke.task.Event;
import main.java.com.jacob.duke.task.Task;
import main.java.com.jacob.duke.task.Todo;





public class Storage {
    private StringBuffer stringBufferOfData = new StringBuffer();
    private String filename;

    /**
     * Constructor for the Storage object
     * @param filename path of the file to be accessed
     */
    public Storage(String filename) {
        this.filename = filename;
        accessTaskListInFileSystem(getCurrentDirectory());
    }

    //get current directory
    private String getCurrentDirectory() {
        return System.getProperty("user.dir");
    }

    //Access the current list, creates the folder and files if they do not exist
    private void accessTaskListInFileSystem(String current) {
        String[] fileParents = filename.split("/");
        String parent = fileParents[0];
        java.nio.file.Path directoryPath = java.nio.file.Paths.get(current, parent);
        boolean directoryExists = java.nio.file.Files.exists(directoryPath);


        java.nio.file.Path filePath = java.nio.file.Paths.get(current, filename);
        boolean fileExists = java.nio.file.Files.exists(filePath);
        try {
            if (!directoryExists) {
                Files.createDirectories(directoryPath);
            }
            if (!fileExists) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //System.out.println("Printing filepath after accessing it: " + filePath.toString());
    }


    //handle the file lines at initialization
    private void handleFileCommands(String inputCommand, List<Task> taskList, int count) {

        //parse input command (in format :  type, done, description, datetime) into task
        String[] inputs = inputCommand.split(",");
        String type = inputs[0];
        int isDone = Integer.parseInt(inputs[1]);
        String description = inputs[2];

        switch (type) {
        case "T":
            Task theTodo = new Todo(description);
            if (isDone == 1) {
                theTodo.setDone();
            }
            taskList.add(count, theTodo);
            break;
        case "E":
            String dateTime = inputs[3];
            Task theEvent = new Event(description, dateTime);
            if (isDone == 1) {
                theEvent.setDone();
            }
            taskList.add(count, theEvent);
            break;
        case "D":
            dateTime = inputs[3];
            Task theDeadline = new Deadline(description, dateTime);
            if (isDone == 1) {
                theDeadline.setDone();
            }
            taskList.add(count, theDeadline);
            break;
        default:
            break;
        }
    }

    /**
     * Handles the file and creates the taskList objects based on file information
     * @return List which containing the task objects in a list representation
     */
    public List<Task> readFile() {
        List<Task> taskList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new DataInputStream(new FileInputStream(this.filename))));
            int count = 0;
            for (String line; (line = reader.readLine()) != null; ) {
                //System.out.println(line);

                //parse the line here and add to taskList
                this.handleFileCommands(line, taskList, count++);

                //add to the buffer
                stringBufferOfData.append(line).append("\r\n");
            }
            System.out.println(taskList);

            //handle resource leakage
            reader.close();
        } catch (IOException e) {
            System.out.println("The file " + filename + " could not be found or opened! " + e.getMessage());
        }
        return taskList;
    }

    /**
     * Writes the stored string buffer to the file
     */
    public void writeToFile() {
        try {
            BufferedWriter bfWriter = new BufferedWriter(new FileWriter(filename));
            bfWriter.write(stringBufferOfData.toString()); //writes the edited string buffer to the new file
            bfWriter.close(); //closes the file

        } catch (Exception e) { //if an exception occurs
            System.out.println("Error occurred while attempting to write to file: " + e.getMessage());
        }
    }

    /**
     * Replaces a specific line of text in the string buffer read
     * @param lineToEdit text to be replaced
     * @param replacementText text to be replace with
     * @throws StringIndexOutOfBoundsException in case the string cannot be found
     */
    public void replacement(String lineToEdit, String replacementText) throws StringIndexOutOfBoundsException {
        //System.out.println(sb);//used for debugging to check that my string buffer has correct contents and spacing

        //Find the original text
        int startIndex = stringBufferOfData.indexOf(lineToEdit);
        int endIndex = startIndex + lineToEdit.length();

        //replace text
        stringBufferOfData.replace(startIndex, endIndex, replacementText);

    }

    /**
     * Add a string of text to the end
     * @param replacementText text to be added
     */
    public void appendText(String replacementText) {
        //add a separator for the newline before appending
        String newLine = System.getProperty("line.separator");
        replacementText = replacementText + newLine;
        stringBufferOfData.append(replacementText);
    }

    /**
     * Remove a specific line of text from string buffer
     * @param lineToEdit line to be removed
     */
    public void removeText(String lineToEdit) {
        String newLine = System.getProperty("line.separator");
        lineToEdit = lineToEdit + newLine;
        replacement(lineToEdit, "");
    }

    //test driver code below
    /* public static void main(String[] args) {
        Storage storage = new Storage("data/duke.txt");
        Scanner sc = new Scanner(System.in);

        storage.accessTaskListInFileSystem(storage.getCurrentDirectory());
        System.out.println(storage.getCurrentDirectory());

        //System.out.println("Please enter your files name and path i.e C:\\test.txt: ");//prompt for file name
        //String filename = sc.nextLine();//read in the file name
        storage.readFile();

        System.out.println(storage.stringBufferOfData);

        //prompt for a line in file to edit
        System.out.println("Please enter the contents of a line you would like to edit: ");
        String lineToEdit = sc.nextLine();

        //prompt for a line in file to replace
        System.out.println("Please enter the the replacement text: ");
        String replacementText = sc.nextLine();

        try {
            storage.replacement(lineToEdit, replacementText);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        //add text
        System.out.println("Please enter the the append text: "); //prompt for a line in file to replace
        String appendText = sc.nextLine();
        storage.appendText(appendText);

        //add text
        System.out.println("Please enter the text to be removed: "); //prompt for a line in file to replace
        String removeText = sc.nextLine();
        storage.removeText(removeText);

        //complete writing to file
        storage.writeToFile();
    }
     */
}
