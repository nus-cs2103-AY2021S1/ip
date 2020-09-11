package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles storing Tasks locally and reading from locally stored Tasks.
 */

public class Storage {
    /** Where the local save file is stored */
    private String filepath;
    /** List of tasks to be saved */
    private List<Task> tasks;

    /** Short form of duke.Task types. */
    private enum TaskSymbols {
        SYMBOL_T, SYMBOL_E, SYMBOL_D
    }

    /**
     * Constructor for duke.Storage.
     * @param filepath Contains path of local storage file.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        this.tasks = new ArrayList<>();
    }

    /**
     * Saves the input list of tasks into a local store.
     * @param tasks List of tasks to be saved.
     */
    public String save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filepath);
            for (Task t : tasks.getTasks()) {
                fw.write(t.format() + "\n");
            }
            fw.close();
            return ">> Tasks saved!";
        } catch (IOException e) {
            return ">> Oh no! Your tasks could not be saved!";
        }
    }

    /**
     * Loads a list of tasks from a local store.
     * @return List of tasks from the local store.
     * @throws DukeException When file is not found.
     */
    public List<Task> load() throws DukeException {
        String directoryErrorMsg = ">> Something went wrong creating the data directory!";
        try {
            String[] directories = filepath.split("/");
            StringBuilder path = new StringBuilder();

            // Creates directories if they don't exist yet.
            for (int i = 0; i < directories.length - 1; i++) {
                path.append(directories[i]).append("/");
                File directory = new File(path.toString());
                if (!directory.exists()) {
                    boolean success = directory.mkdir();
                    if (!success) {
                        throw new DukeException(directoryErrorMsg);
                    }
                }
            }

            File file = new File(filepath);

            if (!file.exists()) {
                boolean success = file.createNewFile();
                if (!success) {
                    throw new DukeException(directoryErrorMsg);
                }
            }

            Scanner fr = new Scanner(file);
            while (fr.hasNextLine()) {
                String ln = fr.nextLine();
                String[] taskInfo = ln.split(Task.getEscapedSaveDelimiter());
                TaskSymbols type = TaskSymbols.valueOf("SYMBOL_" + taskInfo[0]);
                switch(type) {
                case SYMBOL_T:
                    assert taskInfo.length > 2 : "File format error!";
                    if (taskInfo.length == 3) { // no tags
                        tasks.add(new Todo(taskInfo[1], Boolean.parseBoolean(taskInfo[2])));
                    } else { // tags
                        tasks.add(new Todo(taskInfo[1], Boolean.parseBoolean(taskInfo[2]),
                                taskInfo[3].split(",")));
                    }
                    break;
                case SYMBOL_E:
                    assert taskInfo.length > 3 : "File format error!";
                    if (taskInfo.length == 4) { // no tags
                        tasks.add(new Event(taskInfo[1], Boolean.parseBoolean(taskInfo[2]),
                                LocalDate.parse(taskInfo[3])));
                    } else { // tags
                        tasks.add(new Event(taskInfo[1], Boolean.parseBoolean(taskInfo[2]),
                                LocalDate.parse(taskInfo[4]), taskInfo[3].split(",")));
                    }
                    break;
                case SYMBOL_D:
                    assert taskInfo.length > 3 : "File format error!";
                    if (taskInfo.length == 4) { // no tags
                        tasks.add(new Deadline(taskInfo[1], Boolean.parseBoolean(taskInfo[2]),
                                LocalDate.parse(taskInfo[3])));
                    } else { // tags
                        tasks.add(new Deadline(taskInfo[1], Boolean.parseBoolean(taskInfo[2]),
                                LocalDate.parse(taskInfo[4]), taskInfo[3].split(",")));
                    }
                    break;
                default:
                    break;
                }
            }

            return this.tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException(">> Oh no! I can't find your file!");
        } catch (IOException e) {
            throw new DukeException(">> Oh no! The file couldn't be created for some reason!");
        } catch (IllegalArgumentException e) {
            throw new DukeException(">> Oh no! File format seems to be incorrect!");
        }
    }
}
