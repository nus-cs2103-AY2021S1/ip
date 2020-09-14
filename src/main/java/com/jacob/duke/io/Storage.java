package main.java.com.jacob.duke.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.file.Files;
import java.util.List;

import main.java.com.jacob.duke.DukeException;
import main.java.com.jacob.duke.DukeList;
import main.java.com.jacob.duke.note.Note;
import main.java.com.jacob.duke.task.Deadline;
import main.java.com.jacob.duke.task.Event;
import main.java.com.jacob.duke.task.Task;
import main.java.com.jacob.duke.task.Todo;

public class Storage {
    private StringBuffer stringBufferOfTasks = new StringBuffer();
    private StringBuffer stringBufferOfNotes = new StringBuffer();
    private String filename;

    /**
     * Constructor for the Storage object.
     *
     * @param filename path of the file to be accessed.
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
    }

    //handleTaskCommandsFromFile
    private void convertFileToTask(String[] inputs, DukeList dukeList) throws DukeException {
        List<Task> taskList = dukeList.getTaskList();
        String type = inputs[0];
        int isDone;
        try {
            isDone = Integer.parseInt(inputs[1]);
        } catch (NumberFormatException e) {
            throw new DukeException(e.getMessage());
        }
        String description = inputs[2];
        int count = dukeList.getTaskList().size();

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

    // convert note commands from file and turn into note objects
    private void convertFileToNote(String[] inputs, DukeList dukeList) {
        String question = inputs[1];
        String answer = inputs[2];
        Note note = new Note(question, answer);
        int count = dukeList.getNoteList().size();
        dukeList.getNoteList().add(count, note);
    }

    //handle the file lines at initialization
    private void handleFileCommands(String fileLine, DukeList dukeList) throws DukeException {
        String[] inputs = fileLine.split("~");
        String type = inputs[0];

        //parse whether it is a note or task item
        if (type.equals("N")) {
            convertFileToNote(inputs, dukeList);
            stringBufferOfNotes.append(fileLine).append("\r\n");
        } else {
            convertFileToTask(inputs, dukeList);
            stringBufferOfTasks.append(fileLine).append("\r\n");
        }
    }

    /**
     * Handles the file and creates the required note and task objects based on file information.
     *
     * @return List which containing the task objects in a list representation.
     */
    public DukeList readFile() {
        stringBufferOfTasks = new StringBuffer();
        stringBufferOfNotes = new StringBuffer();
        DukeList dukeList = new DukeList();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new DataInputStream(new FileInputStream(this.filename))));
            String line = reader.readLine();
            while (line != null) {
                //parse the line here and add to duke list
                this.handleFileCommands(line, dukeList);
                line = reader.readLine();
            }
            //handle resource leakage
            reader.close();
        } catch (IOException e) {
            System.out.println("The file " + filename + " could not be found or opened! " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return dukeList;
    }

    /**
     * Writes the stored string buffer to the file
     */
    public void writeToFile() {
        try {
            BufferedWriter bfWriter = new BufferedWriter(new FileWriter(filename));
            bfWriter.write(stringBufferOfTasks.toString()); //writes the edited string buffer to the new file
            bfWriter.write(stringBufferOfNotes.toString()); //adds the edited note buffer to the file
            bfWriter.close(); //closes the file

        } catch (Exception e) { //if an exception occurs
            System.out.println("Error occurred while attempting to write to file: " + e.getMessage());
        }
    }

    /**
     * Replaces a specific line in task buffer.
     *
     * @param lineToEdit text to be replaced.
     * @param replacementText text to be replace with.
     * @throws StringIndexOutOfBoundsException in case the string cannot be found.
     */
    public void replacementInTasks(String lineToEdit, String replacementText) {
        replacement(lineToEdit, replacementText, stringBufferOfTasks);
    }
    /**
     * Replaces a specific line in notes buffer.
     *
     * @param lineToEdit text to be replaced.
     * @param replacementText text to be replace with.
     * @throws StringIndexOutOfBoundsException in case the string cannot be found.
     */
    public void replacementInNotes(String lineToEdit, String replacementText) {
        replacement(lineToEdit, replacementText, stringBufferOfNotes);
    }

    private void replacement(String lineToEdit, String replacementText, StringBuffer sb) throws StringIndexOutOfBoundsException {
        //Find the original text
        int startIndex = sb.indexOf(lineToEdit);
        int endIndex = startIndex + lineToEdit.length();

        //replace text
        sb.replace(startIndex, endIndex, replacementText);

    }

    /**
     * Add a string of text to the end of task buffer representing a task.
     *
     * @param addedText text to be added.
     */
    public void appendTextToTasks(String addedText) {
        appendText(addedText, stringBufferOfTasks);
    }
    /**
     * Add a string of text to the end of notes buffer representing a note.
     *
     * @param addedText text to be added.
     */
    public void appendTextToNotes(String addedText) {
        appendText(addedText, stringBufferOfNotes);
    }
    private void appendText(String addedText, StringBuffer sb) {
        //add a separator for the newline before appending
        String newLine = System.getProperty("line.separator");
        addedText = addedText + newLine;
        sb.append(addedText);
    }
    /**
     * Remove a specific line of note from string buffer.
     *
     * @param lineToEdit line to be removed.
     */
    public void removeTextFromTasks(String lineToEdit) {
        removeText(lineToEdit, stringBufferOfTasks);
    }
    /**
     * Remove a specific line of task from string buffer.
     *
     * @param lineToEdit line to be removed.
     */
    public void removeTextFromNotes(String lineToEdit) {
        removeText(lineToEdit, stringBufferOfNotes);
    }
    private void removeText(String lineToEdit, StringBuffer sb) {
        String newLine = System.getProperty("line.separator");
        lineToEdit = lineToEdit + newLine;
        replacement(lineToEdit, "", sb);
    }

}
