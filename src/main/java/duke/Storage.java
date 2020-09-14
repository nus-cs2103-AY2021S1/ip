package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.LoadingErrorException;
import duke.exception.StorageIoException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;



public class Storage {
    private ArrayList<Task> list;
    private String filePath;

    /**
     * Creates a Storage object with the given file path.
     *
     * @param filePath The file path where save files are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.list = new ArrayList<>();
    }

    private void createTask(String s) throws DukeException {
        try {
            Task next;
            if (s.startsWith("todo")) {
                assert s.matches("(todo\\s)(.+)");
                next = new ToDo(s.substring(5));
            } else if (s.startsWith("deadline")) {
                String dateTimePattern = "(\\d\\d\\d\\d-\\d\\d-\\d\\d\\s\\d\\d\\d\\d)";
                String pattern = "(deadline\\s)(.+)\\s(/by\\s)" + dateTimePattern;
                assert s.matches(pattern);
                String task = s.replaceAll(pattern, "$2");
                LocalDateTime dateTime = Parser.extractDateTime(s.replaceAll(pattern, "$4"));
                next = new Deadline(task, dateTime);
            } else {
                String dateTimePattern = "(\\d\\d\\d\\d-\\d\\d-\\d\\d\\s\\d\\d\\d\\d)";
                String pattern = "(event\\s)(.+)\\s(/at\\s)(.+)" + dateTimePattern;
                assert s.matches(pattern);
                String task = s.replaceAll(pattern, "$2");
                LocalDateTime dateTime = Parser.extractDateTime(s.replaceAll(pattern, "$4"));
                next = new Event(task, dateTime);
            }
            list.add(next);
        } catch (DukeException e) {
            throw (e);
        }
    }

    /**
     * Returns an arraylist which has been filled with tasks from the saved file.
     *
     * @return Arraylist of tasks.
     * @throws DukeException If saved file does not exist.
     */
    public ArrayList<Task> load() throws DukeException {
        File f = new File(filePath);
        try {
            Scanner listScanner = new Scanner(f);
            int index = 0;
            while (listScanner.hasNext()) {
                String command = listScanner.nextLine();
                if (command.startsWith("done")) {
                    createTask(command.substring(5));
                    list.get(index).setDone();
                } else {
                    createTask(command);
                }
                index++;
            }
            return list;
        } catch (FileNotFoundException e) {
            throw new LoadingErrorException(filePath);
        } catch (DukeException e) {
            throw (e);
        }
    }

    /**
     * Saves the tasks in the TaskList to a text file that can be loaded by the Storage class.
     *
     * @param tasks The TaskList to be saved.
     * @throws DukeException If save file path does not exist.
     */
    public void save(TaskList tasks) throws DukeException {
        File saveFile = new File(filePath);
        File parentDirectory = saveFile.getParentFile();

        if (null != parentDirectory) {
            parentDirectory.mkdirs();
        }

        try {
            FileWriter fw = new FileWriter(saveFile);
            String data = tasks.extractListData();
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            throw new StorageIoException(e.getMessage());
        }
    }
}
