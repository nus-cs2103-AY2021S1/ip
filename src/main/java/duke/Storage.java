package duke;

import duke.task.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Storage {
    private final TaskList taskList;
    private final static Ui ui = new Ui();

    Storage(TaskList taskList) {
        this.taskList = taskList;
    }

    public static Storage createStorage(String filePath) throws DukeException {
        String errMessage = "Woof woof... I can't seem to create a file to store your tasks...\n"
                + "Your tasks would be forgotten at this rate...";
        String[] pathNames = filePath.split("/");
        String dirName = pathNames[0];
        String fileName = pathNames[1];

        try {
            String home = System.getProperty("user.dir");
            Path currDir = Paths.get(home).getParent();
//            Path currDir = Paths.get(home);
            Path targetPath = Paths.get(currDir.toString(), filePath);
            File directory = new File(Paths.get(currDir.toString(), dirName).toString());
            boolean isDirCreated;
            boolean isFileCreated;

            if (!java.nio.file.Files.exists(targetPath)) {

                if (directory.exists()) {
                    isDirCreated = true;
                    File file = new File(Paths.get(currDir.toString(),dirName, fileName).toString());
                    isFileCreated = file.createNewFile();
                } else {
                    File dir = new File(Paths.get(currDir.toString(), dirName).toString());
                    isDirCreated = dir.mkdir();
                    File file = new File(Paths.get(currDir.toString(), dirName, fileName).toString());
                    isFileCreated = file.createNewFile();
                }

                if (isDirCreated && isFileCreated) {
                    ui.fileCreationSuccess();
                    return new Storage(new TaskList(new File(targetPath.toString())));
                } else {
                    throw new DukeException(errMessage);
                }
            } else {
                ui.welcome();
                return new Storage(new TaskList(new File(targetPath.toString())));
            }
        } catch (InvalidPathException | DukeException | IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void load() throws DukeException{
        taskList.loadTasks();
    }

    public void save(Task t) throws DukeException {
        try {
            int total = taskList.addTask(t);
            ui.addSuccess(t.toString(), total);
        } catch (IOException e) {
            throw new DukeException(ui.accessFileFailure());
        }
    }

    public void delete(String message) throws DukeException {
        try {
            int ind = Integer.parseInt(message.substring(6).trim()) - 1;
            Task t = taskList.deleteTask(ind);
            ui.deleteSuccess(t.toString(), total());
        } catch (IOException e) {
            throw new DukeException(ui.accessFileFailure());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ui.noSuchTask());
        } catch (NumberFormatException e) {
            throw new DukeException(ui.wrongDeleteInput());
        }
    }

    public void markDone(int ind) throws DukeException {
        try {
            taskList.markDone(ind);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ui.noSuchTask());
        } catch (IOException e) {
            throw new DukeException(ui.accessFileFailure());
        }
    }

    public void printAll() {
        if (total() == 0) {
            ui.noTask();
        } else {
            ui.listHeader();
            List<Task> list = taskList.displayAll();
            list.forEach((task) -> {
                int ind = list.indexOf(task) + 1;
                ui.listBody(ind, task.toString());
            });
            ui.line();
        }
    }

    public void checkDate(String s) throws DukeException{
        try {
            String[] inputDate = s.trim().split("/");
            String formatDate = inputDate[0] + "-" + inputDate[1] + "-" + inputDate[2];
            LocalDate date = LocalDate.parse(formatDate);
            List<Task> sameDates = taskList.checkDate(date);

            if (sameDates.isEmpty()) {
                ui.noSameDate();
            } else {
                ui.sameDateHeader(date);
                for (Task t : sameDates) {
                    ui.listBody(sameDates.indexOf(t) + 1, t.toString());
                }
                ui.line();
            }
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ui.inputCorrectCheckDateFormat());
        }
    }

    public int total() {
        return taskList.total();
    }
}
