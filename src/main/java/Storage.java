import java.io.*;

public class Storage {

    private static String filePath = "./src/main/data/duke.txt";

    public static TaskList loadFromMem() throws DukeException {
        File saveFile = new File(filePath);
        if (saveFile.exists()) {
            return convertToTaskList();
        } else {
            throw new DukeNoSaveFileFoundException();
        }
    }

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

    public static void writeToFile(String data, File file) throws IOException {
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
        boolean done;

        if (line.startsWith("\u2713", 4)) {
            done = true;
        } else if (line.startsWith("\u2718", 4)) {
            done = false;
        } else {
            throw new DukeException();
        }

        switch (line.charAt(1)) {
            case 'T':
                taskList.addTask(new Todo(line.substring(7)), false);
                break;
            case 'D':
                Deadline.newDeadline(line.substring(7), taskList, done, false);
                break;
            case 'E':
                Event.newEvent(line.substring(7), taskList, done, false);
                break;
            default:
                throw new DukeException();
        }
    }

}
