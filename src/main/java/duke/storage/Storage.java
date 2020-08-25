package duke.storage;

import duke.Duke;
import duke.exception.DukeException;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents an object that deals with loading tasks from the file and saving
 * tasks in the file.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a <code>Task</code> based on the input format from the txt file.
     * e.g. input = "D | 0 | do this | 2020-01-01" will return
     * <code>new Task("deadline do this /by 2020-01-01")</code>
     *
     * @param input
     * @return Task derived from the input format in the txt file.
     */
    public static Task textToTask(String input) {
        String des = "";
        Character first = input.charAt(0);
        Character num = input.charAt(4);
        if (first == 'T') {
            des = "todo ";
            des += input.substring(8);

        } else {
            String temp = input.substring(8);
            String taskDescription = temp.substring(0, temp.indexOf(" |"));
            String date = temp.substring(temp.indexOf("|") + 2);
            if (first == 'D') {
                des = "deadline " + taskDescription + " /by " + date;
            } else if (first == 'E') {
                des = "event " + taskDescription + " /at " + date;
            }
        }

        Task t = new Task(des);
        if (num == '0') {
            return t;
        } else {
            t.markAsDone();
            return t;
        }
    }

    /**
     * Adds each line of the file specified to the <code>tasksStrings</code>
     * array list as a different item.
     *
     * @param tasksStrings String from txt file is added to this ArrayList.
     * @throws FileNotFoundException If the file specified cannot be found.
     */
    public void fileContentToArrayList(ArrayList<String> tasksStrings) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String input = s.nextLine();
            tasksStrings.add(input);
        }
    }

    /**
     * Returns an ArrayList of <code>Task</code> objects based on the String in
     * the txt file.
     *
     * @return ArrayList of <code>Task</code> objects.
     * @throws DukeException If any of the <code>Task</code> objects is not valid.
     */
    public ArrayList<Task> load() throws DukeException {
        // converts text to tasks, duke exception to check if is valid task
        ArrayList<Task> tasksArray = new ArrayList<>();
        ArrayList<String> tasksStrings = new ArrayList<>();

        try {
            fileContentToArrayList(tasksStrings);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        for (int i = 0; i < tasksStrings.size(); i++) {
            String currString = tasksStrings.get(i);
            Task t = textToTask(currString);
            t.validate();
            if (t.isTodo()) {
                tasksArray.add(t.convertToTodo());
            } else if (t.isDeadline()) {
                tasksArray.add(t.convertToDeadline());
            } else {
                tasksArray.add(t.convertToEvent());
            }
        }
        return tasksArray;
    }

    /**
     * Writes <code>String textToAdd</code> to the file specified in
     * <code>String filePath</code>.
     *
     * @param filePath Path of the file to write to.
     * @param textToAdd Text to be written to the file specified in <code>filePath</code>.
     * @throws IOException
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Saves the text from <code>String textToAdd</code> to the file specified
     * in <code>String filePath</code>.
     *
     * @param filePath Path of the file to write to.
     * @param textToAdd Text to be written to the file specified in <code>filePath</code>.
     */
    public static void save(String filePath, String textToAdd) {
        // writes fileString to .txt file
        try {
            writeToFile(Duke.FILENAME, textToAdd);
        } catch (IOException e) {
            System.out.println("Oops, something went wrong: " + e.getMessage());
        }
    }

}

