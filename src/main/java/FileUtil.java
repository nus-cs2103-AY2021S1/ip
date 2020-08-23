import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public final class FileUtil {
    private static final String DB_DIR_NAME = "data";
    private static final String DB_FILE_NAME = "duke.txt";
    private static Path dbPath = null;

    private List<Task> toDoLst;

    public FileUtil() throws FileNotFoundException, IOException {
        try {
            checkDbExists();
        } catch (FileNotFoundException e) {
            throw e;
        }

        toDoLst = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(dbPath.toFile()));
            String line = br.readLine();

            while (line != null) {
                String[] lineData = line.split("\\|");
                Task task = new Task(lineData[0], lineData[2], Boolean.valueOf(lineData[1]));

                addToDoItem(task);

                line = br.readLine();
            }
        } catch (IOException e) {
            throw e;
        }
    }

    public static void checkDbExists() throws FileNotFoundException {
        Path curDirPath = Paths.get("");
        Path dbDirPath = curDirPath.resolve(DB_DIR_NAME);

        if (!Files.exists(dbDirPath)) {
            throw new FileNotFoundException(String.format("Data folder does not exist! Please create a directory named %s", DB_DIR_NAME));
        }

        dbPath = dbDirPath.resolve(DB_FILE_NAME);

        if (!Files.exists(dbPath)) {
            throw new FileNotFoundException(String.format("Db does not exist! Please create a text file named %s", DB_FILE_NAME));
        }
    }

    public List<Task> getToDoLst() {
        return toDoLst;
    }

    public int getToDoLstSize() {
        return toDoLst.size();
    }

    public String getTotalItemsDescription() {
        return String.format("Now you have %d %s in the list.", getToDoLstSize(), getToDoLstSize() > 1 ? "tasks" : "task");
    }

    public void addToDoItem(Task task) {
        toDoLst.add(task);
    }

    public Task removeToDoItem(int i) {
        Task deletedTask = toDoLst.remove(i);

        return deletedTask;
    }

    public void save() throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(dbPath.toFile()));

            for (Task task: toDoLst) {
                writer.append(task.toDbString());
                writer.newLine();
            }

            writer.flush();
        } catch (IOException e) {
            throw e;
        }
    }
}