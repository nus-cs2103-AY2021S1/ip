package dev.jingyen.duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import dev.jingyen.duke.model.Task;
import dev.jingyen.duke.parser.InvalidInputException;
import dev.jingyen.duke.parser.TaskParser;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Deserializes a list of tasks stored at a given path into a list of Tasks.
     *
     * @return a list of Tasks that were stored at the path at <code>filePath</code>.
     * @throws IOException if a problem was encountered while trying to access the file at <code>filePath</code>
     */
    // TODO: 26/8/20 Add more relevant error for parsing
    public List<Task> load() throws IOException, InvalidInputException, DateTimeParseException {
        List<Task> tasks = new ArrayList<>();
        try (var br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = TaskParser.parse(line);
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Checks if the file at <code>filePath</code> exists.
     *
     * @return true if the file exists, otherwise false
     */
    public boolean hasSavedTasks() {
        return new File(filePath).exists();
    }

    /**
     * Saves a list of tasks into a file.
     *
     * @param storables the list of tasks to serialize and save
     * @throws IOException if a problem was encountered while trying to access the file at <code>filePath</code>
     */
    public void saveTasks(List<? extends Storable> storables) throws IOException {
        assert storables != null;
        File saveFile = new File(filePath);
        if (!saveFile.exists()) {
            saveFile.getParentFile().mkdirs();
            saveFile.createNewFile();
        }

        // Use PrintWriter wrapping BufferedWriter in FileWriter
        try (var out = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            for (Storable storable : storables) {
                out.println(storable.toSaveString());
            }
        }
    }

}
