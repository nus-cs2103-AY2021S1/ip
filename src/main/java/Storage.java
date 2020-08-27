import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {

    /** Variable to store file path for I/O. */
    private String filePath;

    /**
     * Constructor used to create file storage.
     *
     * @param filePath Filepath of input and output file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<ArrayList<String>> load() throws DukeException {
        File f = new File(this.filePath);
        f.getParentFile().mkdirs();
        try {
            if (!f.exists()) {
                f.createNewFile();
                return new ArrayList<>();
            } else {
                return readTasksFromFile();
            }
        } catch (IOException e) {
            throw new DukeException("     A file error has occurred!");
        }
    }

    private ArrayList<ArrayList<String>> readTasksFromFile() throws DukeException {
        // File structure
        // D | isDoneInt | description | by
        // E | isDoneInt | description | at
        // T | isDoneInt | description

        // TODO: Can store Deadline, Event, Todo string instead, and can use same Enums
        File f = new File(this.filePath);
        ArrayList<ArrayList<String>> formattedOutput = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] lineParts = line.split(" \\| ");
                ArrayList<String> linePartArray = new ArrayList<>(Arrays.asList(lineParts));
                formattedOutput.add(linePartArray);
            }
            return formattedOutput;
        } catch (java.io.FileNotFoundException e) {
            throw new DukeException("     A file error has occurred!");
        }
    }

    public void save(ArrayList<Task> tasks) throws DukeException {
        StringBuilder output = new StringBuilder();
        for (Task task : tasks) {
            output.append(task.toStringSimple()).append(System.lineSeparator());
        }
        appendToFile(output.toString());
    }

    private void appendToFile(String textToAppend) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath); // create a FileWriter in append mode
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("     A file error has occurred!");
        }
    }
}


