import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private static final String FILEPATH = "./src/main/data/duke.txt";

    /**
     * Attempts to load an existing save file.
     */
    public static TaskList loadFromMem() throws DukeException {
        File saveFile = new File(FILEPATH);
        if (saveFile.exists()) {
            return convertToTaskList();
        } else {
            throw new DukeNoSaveFileFoundException();
        }
    }

    /**
     * Creates a new file that contains the saved tasks.
     * @param data The data to be read and converted to a save file, obtained from calling toString() on a taskList.
     */
    public static void createNewSave(String data) throws DukeException {
        new File("./src/main/data").mkdirs();
        try {
            File newFile = new File(FILEPATH);
            writeToFile(data, newFile);
        } catch (IOException e) {
            throw new DukeException();
        }
    }

    private static void writeToFile(String data, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.write(data);
        fileWriter.close();
    }

    private static TaskList convertToTaskList() throws DukeException {
        TaskList taskList = new TaskList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILEPATH));
            br.lines().forEach(s -> {
                try {
                    convertToTask(s, taskList);
                } catch (DukeException e) {
                    Ui.printWithLines(e.toString());
                }
            });
        } catch (FileNotFoundException e) {
            throw new DukeException();
        }
        return taskList;
    }

    private static void convertToTask(String line, TaskList taskList) throws DukeException {
        boolean isDone;

        if (line.startsWith("O", 4)) {
            isDone = true;
        } else if (line.startsWith("X", 4)) {
            isDone = false;
        } else {
            throw new DukeException();
        }

        switch (line.charAt(1)) {
        case 'T':
            taskList.addTask(new Todo(line.substring(7)), false);
            break;
        case 'D':
            Deadline.newDeadline(line.substring(7), taskList, isDone, false);
            break;
        case 'E':
            Event.newEvent(line.substring(7), taskList, isDone, false);
            break;
        default:
            throw new DukeException();
        }
    }

}
