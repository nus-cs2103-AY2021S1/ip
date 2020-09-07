package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.Duke;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents an object that deals with loading tasks from the file and saving
 * tasks in the file.
 */
public class Storage {
    private String filePath;

    static final int INDEX_OF_TASK_TYPE = 0;
    static final int INDEX_OF_DONENESS = 4;
    static final int INDEX_OF_TASK_DESCRIPTION = 8;

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
        char first = input.charAt(INDEX_OF_TASK_TYPE);
        char num = input.charAt(INDEX_OF_DONENESS);
        if (first == 'T') {
            des = "todo ";
            des += input.substring(INDEX_OF_TASK_DESCRIPTION);
        } else {
            String temp = input.substring(INDEX_OF_TASK_DESCRIPTION);
            String taskDescription = temp.substring(0, temp.indexOf(" |"));
            String date = temp.substring(temp.indexOf("|") + 2);
            if (first == 'D') {
                des = "deadline " + taskDescription + " /by " + date;
            } else {
                des = "event " + taskDescription + " /at " + date;
            }
        }

        Task t = new Task(des);
        if (num != '0') {
            t.markAsDone();
        }
        return t;
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
            } else if (t.isEvent()){
                tasksArray.add(t.convertToEvent());
            }
            assert false;
        }
        return tasksArray;
    }

    /**
     * Writes <code>String textToAdd</code> to the file specified in
     * <code>String filePath</code>.
     *
     * @param textToAdd Text to be written to the file specified in <code>filePath</code>.
     * @throws IOException
     */
    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(Duke.FILENAME);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Saves the text from <code>String textToAdd</code> to the file specified
     * in <code>String filePath</code>.
     *
     * @param textToAdd Text to be written to the file specified in <code>filePath</code>.
     */

    public static void save(String textToAdd) {

        try {
            writeToFile(textToAdd);
        } catch (IOException e) {
            System.out.println("Oops, something went wrong: " + e.getMessage());
        }
    }
}

