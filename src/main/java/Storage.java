import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;

/**
 * deals with loading tasks from the file and saving tasks in the file
 */
public final class Storage {
    private static final String DB_FILE_NAME = "duke.txt";
    private static Path dbPath = null;

    private TaskList toDoLst;

    public Storage() throws FileNotFoundException, IOException {
        try {
            checkDbExists();
        } catch (FileNotFoundException e) {
            throw e;
        }

        toDoLst = new TaskList();

        try {
            BufferedReader br = new BufferedReader(new FileReader(dbPath.toFile()));
            String line = br.readLine();

            while (line != null) {
                String[] lineData = line.split("\\|");

                Task task = new Task(lineData[0], lineData[2], lineData.length == 3 ? null : LocalDate.parse(lineData[3]), Boolean.valueOf(lineData[1]));

                addToDoItem(task);

                line = br.readLine();
            }
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Checks whether the text file (for storage) exists
     *
     * @throws FileNotFoundException
     */
    public static void checkDbExists() throws FileNotFoundException {
        Path curDirPath = Paths.get("");

        dbPath = curDirPath.resolve(DB_FILE_NAME);

        if (!Files.exists(dbPath)) {
            File file = new File(DB_FILE_NAME);
            try {
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
            }
            catch (IOException ex){
                System.out.println(ex);
            }
            dbPath = curDirPath.resolve(DB_FILE_NAME);
        }
    }

    /**
     * Gets to-do list.
     *
     * @returns List of tasks (to-do list).
     */
    public List<Task> getToDoLst() {
        return toDoLst.getToDoLst();
    }

    /**
     * Get size of to-do list.
     *
     * @returns size of to-do list.
     */
    public int getToDoLstSize() {
        return toDoLst.getToDoLstSize();
    }

    /**
     * Get description of size of list.
     *
     * @returns Description of size of list.
     */
    public String getTotalItemsDescription() {
        return String.format("Now you have %d %s in the list.", toDoLst.getToDoLstSize(), toDoLst.getToDoLstSize() > 1 ? "tasks" : "task");
    }

    /**
     * Add to-do item
     *
     * @param task to be added
     * @return boolean whether there are duplicates
     */
    public boolean addToDoItem(Task task) {
        boolean hasDuplicates = false;

        if (toDoLst.contains(task)) {
            hasDuplicates = true;
        }

        toDoLst.addToDoItem(task);

        return hasDuplicates;
    }

    /**
     * Remove to-do item
     *
     * @param i index of task tobe removed in the to-do list
     *
     * @return removed task
     */
    public Task removeToDoItem(int i) {
        return toDoLst.removeToDoItem(i);
    }

    public List<Task> searchToDoItems(String searchTerm) {
        return toDoLst.searchToDoItems(searchTerm);
    }

    public void save() throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(dbPath.toFile()));

            for (Task task: toDoLst.getToDoLst()) {
                writer.append(task.toDbString());
                writer.newLine();
            }

            writer.flush();
        } catch (IOException e) {
            throw e;
        }
    }
}
