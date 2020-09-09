package dude.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import dude.task.Deadline;
import dude.task.Event;
import dude.task.Task;
import dude.task.Todo;

/**
 * The class that handles the reading and writing of tasks.
 */
public class Storage {
    private String filePath;
    private Function<String, Boolean> func = x -> x.equals("1") ? true : false;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns an ArrayList of Tasks from the data file.
     *
     * @return ArrayList of Task from the data file.
     * @throws FileNotFoundException file is not found.
     * @throws CorruptedFileException file has unknown formatting.
     */
    public ArrayList<Task> read() throws FileNotFoundException, CorruptedFileException {
        File f = new File(filePath);
        Scanner scanner = new Scanner(f);
        ArrayList<String> lineList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lineList.add(scanner.nextLine());
        }
        try {
            return lineConverter(lineList);
        } catch (CorruptedFileException e) {
            throw new CorruptedFileException("Sorry, the input file is corrupted.");
        }
    }

    /**
     * Saves the Tasklist into the data file.
     *
     * @param textToAdd list of Tasks that the user input.
     * @throws IOException unable to open the file in write.
     */
    public void write(List<Task> textToAdd) throws IOException {
        File f = new File(filePath);
        File dataDir = new File(f.toPath().getParent().toString());
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(taskConverter(textToAdd));
        fileWriter.close();
    }

    /**
     * Converts an Ararylist of Strings into an Arraylist of Task.
     *
     * @param lineList ArrayList of Strings.
     * @return ArrayList of Task.
     * @throws CorruptedFileException Invalid format read from the file.
     */
    private ArrayList<Task> lineConverter(ArrayList<String> lineList) throws CorruptedFileException {
        ArrayList<Task> taskList = new ArrayList<>();
        for (String s : lineList) {
            try {
                String[] temp = s.split(" \\| ");
                if (!temp[1].equals("1") && !temp[1].equals("0")) {
                    throw new CorruptedFileException("Sorry, the input file is corrupted.");
                }
                assert temp.equals("1") || temp.equals("0") : "temp should be equals to 0 or 1";
                if (temp[0].equals("T")) {
                    taskList.add(new Todo(temp[2].trim(), func.apply(temp[1])));
                } else if (temp[0].equals("D")) {
                    taskList.add(new Deadline(temp[2].trim(), func.apply(temp[1]), LocalDate.parse(temp[3])));
                } else if (temp[0].equals("E")) {
                    taskList.add(new Event(temp[2].trim(), func.apply(temp[1]), LocalDate.parse(temp[3])));
                } else {
                    throw new CorruptedFileException("Sorry, the input file is corrupted.");
                }
            } catch (DateTimeException e) {
                throw new CorruptedFileException("Sorry, the input file is corrupted.");
            }
        }
        return taskList;
    }

    /**
     * Converts a TaskList into a String for saving.
     * @param taskList TaskList.
     * @return String representation of TaskList.
     */
    private String taskConverter(List<Task> taskList) {
        StringBuilder uiOutput = new StringBuilder();
        for (Task t : taskList) {
            uiOutput.append(t.toSave()).append("\n");
        }
        return uiOutput.toString();
    }
}
