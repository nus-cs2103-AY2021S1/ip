package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private ArrayList<Task> list;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.list = new ArrayList<>();
    }

    private void createTask(String s) throws DukeException {
        try {
            Task next;
            if (s.startsWith("todo")) {
                next = new ToDo(s.substring(5));
            } else if (s.startsWith("deadline")) {
                String datePattern = "(\\d\\d\\d\\d-\\d\\d-\\d\\d)\\s";
                String timePattern = "(\\d\\d)(\\d\\d)";
                String pattern = "(deadline\\s)(.+)\\s(/by\\s)"+ datePattern + timePattern;
                String task = s.replaceAll(pattern, "$2");
                LocalDateTime dateTime = Parser.extractDateTime(s, pattern);
                next = new Deadline(task, dateTime);
            } else {
                String datePattern = "(\\d\\d\\d\\d-\\d\\d-\\d\\d)\\s";
                String timePattern = "(\\d\\d)(\\d\\d)";
                String pattern = "(event\\s)(.+)\\s(/at\\s)(.+)"+ datePattern + timePattern;
                String task = s.replaceAll(pattern, "$2");
                LocalDateTime dateTime = Parser.extractDateTime(s, pattern);
                next = new Event(task, dateTime);
            }
            list.add(next);
        } catch (DukeException e) {
            throw(e);
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
            throw DukeException.loadingError(filePath);
        } catch (DukeException e) {
            throw(e);
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
        File parent_directory = saveFile.getParentFile();

        if (null != parent_directory) {
            parent_directory.mkdirs();
        }

        try {
            FileWriter fw = new FileWriter(saveFile);
            String data = tasks.extractListData();
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            throw DukeException.storageIOException(e.getMessage());
        }
    }
}
