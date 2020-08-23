package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Storage for Tasks. A <code>Storage</code> represents a system that manages the storage and retrieval of
 * <code>Task</code> from a file.
 */
public class Storage {
    private final TaskList taskList;
    private final static Ui ui = new Ui();
    private final File file;

    Storage(TaskList taskList, File file) {
        this.taskList = taskList;
        this.file = file;
    }

    /**
     * Returns a <code>Storage</code> system containing a <code>TaskList</code>.
     * Creates a new file at the given directory if the file does not exists. Else, retrieves all tasks from the
     * given file.
     *
     * @param filePath FilePath representing the location of the file where all <code>Task</code> are saved
     * @return <code>Storage</code> object with all previous <code>Task</code> saved, if applicable
     * @throws DukeException if file cannot be created/ retrieved
     */
    public static Storage createStorage(String filePath) throws DukeException {
        String errMessage = "Woof woof... I can't seem to create a file to store your tasks...\n"
                + "Your tasks would be forgotten at this rate...";
        String[] pathNames = filePath.split("/");
        String dirName = pathNames[0];
        String fileName = pathNames[1];

        try {
            String home = System.getProperty("user.dir");
//            Path currDir = Paths.get(home).getParent();
            Path currDir = Paths.get(home);
            Path targetPath = Paths.get(currDir.toString(), filePath);
            File directory = new File(Paths.get(currDir.toString(), dirName).toString());
            boolean isDirCreated;
            boolean isFileCreated;

            if (!java.nio.file.Files.exists(targetPath)) {

                if (directory.exists()) {
                    isDirCreated = true;
                    File file = new File(Paths.get(currDir.toString(), dirName, fileName).toString());
                    isFileCreated = file.createNewFile();
                } else {
                    File dir = new File(Paths.get(currDir.toString(), dirName).toString());
                    isDirCreated = dir.mkdir();
                    File file = new File(Paths.get(currDir.toString(), dirName, fileName).toString());
                    isFileCreated = file.createNewFile();
                }

                if (isDirCreated && isFileCreated) {
                    ui.fileCreationSuccess();
                    return new Storage(new TaskList(), new File(targetPath.toString()));
                } else {
                    throw new DukeException(errMessage);
                }
            } else {
                ui.welcome();
                return new Storage(new TaskList(), new File(targetPath.toString()));
            }
        } catch (InvalidPathException | DukeException | IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Loads all <code>Task</code> saved in file into the <code>TaskList</code>, if applicable.
     *
     * @throws DukeException if <code>Task</code> cannot be retrieved due <code>FileNotFound</code> exception
     */
    public void load() throws DukeException{
        try {
            Scanner s = new Scanner(file);

            while (s.hasNextLine()) {
                taskList.checkTask(s.nextLine());
            }

        } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Saves the given <code>Task</code> to file.
     *
     * @param task Task to be added to file
     * @throws DukeException if <code>Task</code> fails to save
     */
    public void save(Task task) throws DukeException {
        try {
            taskList.addTask(task);
            int total = total();
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(task.toString());
            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.close();
            ui.addSuccess(task.toString(), total);
        } catch (IOException e) {
            throw new DukeException(ui.accessFileFailure());
        }
    }

    /**
     * Deletes the given <code>Task</code> from file.
     *
     * @param command Command containing index of <code>Task</code> to be deleted from file
     * @throws DukeException if <code>Task</code> fails to delete due to invalid index or
     * <code>FileNotFound</code> exception
     */
    public void delete(String command) throws DukeException {
        try {
            int ind = Integer.parseInt(command.substring(6).trim()) - 1;
            Task t = taskList.deleteTask(ind);
            updateFile();
            ui.deleteSuccess(t.toString(), total());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ui.noSuchTask());
        } catch (NumberFormatException e) {
            throw new DukeException(ui.wrongDeleteInput());
        }
    }

    /**
     * Marks a given <code>Task</code> as done and update this <code>Task</code> in the file.
     *
     * @param ind Index of <code>Task</code> to be updated
     * @throws DukeException if <Task>Task</Task> fails to update due to invalid index or
     * <code>FileNotFound</code> exception
     */
    public void markDone(int ind) throws DukeException {
        try {
            taskList.markDone(ind);
            updateFile();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ui.noSuchTask());
        }
    }

    /**
     * Updates all <code>Task</code> in the file.
     *
     * @throws DukeException if system fails to access file
     */
    public void updateFile() throws DukeException {
        try {
            List<Task> listOfTask = taskList.getList();
            FileWriter fileWriter = new FileWriter(file);

            for (Task task : listOfTask) {
                fileWriter.write(task.toString());
                fileWriter.write(System.getProperty("line.separator"));
            }

            fileWriter.close();

        } catch (IOException e) {
            throw new DukeException(ui.accessFileFailure());
        }
    }

    /**
     * Prints all the <code>Task</code> in a given File.
     */
    public void printAll() {
        if (total() == 0) {
            ui.noTask();
        } else {
            ui.listHeader();
            List<Task> list = taskList.getList();
            list.forEach((task) -> {
                int ind = list.indexOf(task) + 1;
                ui.listBody(ind, task.toString());
            });
            ui.line();
        }
    }

    /**
     * Iterates through all <code>Task</code> in the file and prints all <code>Task</code>
     * on the specified date. Date input must be in the form of YYYY/MM/DD.
     *
     * @param date Date required
     * @throws DukeException if date input is of the wrong format
     */
    public void checkDate(String date) throws DukeException{
        try {
            String[] inputDate = date.trim().split("/");
            String formatDate = inputDate[0] + "-" + inputDate[1] + "-" + inputDate[2];
            LocalDate dateFormat = LocalDate.parse(formatDate);
            List<Task> sameDates = taskList.checkDate(dateFormat);

            if (sameDates.isEmpty()) {
                ui.noSameDate();
            } else {
                ui.sameDateHeader(dateFormat);
                for (Task t : sameDates) {
                    ui.listBody(sameDates.indexOf(t) + 1, t.toString());
                }
                ui.line();
            }
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ui.inputCorrectCheckDateFormat());
        }
    }

    /**
     * Returns the total number of <code>Task</code> in a file.
     *
     * @return total number of <code>Task</code> in a file
     */
    public int total() {
        return taskList.total();
    }

    public void findRelevantTask(String s) throws DukeException{
        try {
            String searchName = s.substring(s.indexOf("find") + 5).trim();
            List<Task> results = taskList.searchTask(searchName.toLowerCase());

            if (results.isEmpty()) {
                ui.noRelevantTask();
            } else {
                ui.relevantTaskHeader();
                for (Task t : results) {
                    ui.listBody(results.indexOf(t) + 1, t.toString());
                }
                ui.line();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ui.searchFail());
        }
    }
}
