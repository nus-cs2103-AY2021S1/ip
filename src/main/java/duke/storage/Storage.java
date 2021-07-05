package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Encapsulates a save-and-load system that allows task information to be saved to the hard drive
 * via a text file when the program ends, as well as allow the information to be loaded to
 * the UI system when the program begins.
 */
public class Storage {

    private static final String DONE = "Y";

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a file and its parent directory in reference to the project root
     * folder. If the file exists, nothing will change.
     */
    public void createFile() {
        try {
            File saveFile = new File(filePath);
            saveFile.getParentFile().mkdirs();
            saveFile.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    /**
     * Loads the contents of the text file onto an ArrayList to create a task list.
     * @return A task list.
     * @throws FileNotFoundException If file cannot be found.
     * @throws DukeException If date format is incorrect.
     */
    public ArrayList<Task> load() throws FileNotFoundException, DukeException {
        ArrayList<Task> lib = new ArrayList<>();
        File file = new File(filePath);
        Scanner reader = new Scanner(file);

        while (reader.hasNext()) {
            String next = reader.nextLine();

            String[] parsedNext = next.split(">");
            if (parsedNext[0].trim().equals("D")) {

                String[] time = parsedNext[3].trim().split(" ");
                Deadline deadline = new Deadline(parsedNext[2].trim(),
                        time[1], parsedNext[1].trim().equals(DONE));
                lib.add(deadline);
            }
            if (parsedNext[0].trim().equals("E")) {

                String[] time = parsedNext[3].trim().split(" ");
                Event event = new Event(parsedNext[2].trim(),
                        time[1], parsedNext[1].trim().equals(DONE));
                lib.add(event);
            }
            if (parsedNext[0].trim().equals("T")) {

                ToDo toDo = new ToDo(parsedNext[2].trim(),
                        parsedNext[1].trim().equals(DONE)
                );
                lib.add(toDo);
            }

        }
        return lib;
    }

    /**
     * Writes the task list to a text file.
     * @param filePath The path that the text file resides.
     * @param textToAdd The text to add to the file.
     * @throws IOException If FileWriter has issues with the given path.
     */
    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(textToAdd);
        writer.close();
    }

    /**
     * Appends the text provided to a text file.
     * @param filePath The path where the text file is.
     * @param textToAppend The text to append to the file.
     * @throws IOException If FileWriter has issues with the given path.
     */
    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);
        writer.write(textToAppend);
        writer.close();
    }

    /**
     * Save the data from the updated task list to the text file and hence the hard
     * drive.
     * @param lib The updated task list.
     * @throws IOException If issues occur when writing the text to the file path.
     */
    public void save(ArrayList<Task> lib) throws IOException {
        for (int i = 0; i < lib.size(); i++) {
            String curr = lib.get(i).saveData();

            if (i == 0) {
                writeToFile("data/duke.txt", curr + "\n");
            } else if (i == (lib.size() - 1)) {
                appendToFile("data/duke.txt", curr);
            } else {
                appendToFile("data/duke.txt", curr + "\n");
            }
        }
    }

}
