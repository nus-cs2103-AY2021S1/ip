import java.io.*;

public class Storage {

    private static String filePath = "./src/main/data/duke.txt";

    /**
     * Attempts to load an existing save file.
     */
    public static TaskList loadFromMem() throws DukeException {
        File saveFile = new File(filePath);
        if (saveFile.exists()) {
            return convertToTaskList();
        } else {
            throw new DukeNoSaveFileFoundException();
        }
    }

    /**
     * Creates a new file that contains the saved tasks.
     */
    public static void createNewSave(String data) throws DukeException {
        new File("./src/main/data").mkdirs();
        try {
            new File(filePath).createNewFile();
            File newFile = new File(filePath);
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
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            br.lines().forEach(s -> {
                try {
                    convertToTask(s,taskList);
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

        if (line.startsWith("\u2713", 4)) {
            isDone = true;
        } else if (line.startsWith("\u2718", 4)) {
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
