package duke.storage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * A class to save tasks into the file and to load tasks from the file.
 */
public class Storage {

    /**
     * The file location where Duke's list of tasks if stored.
     */
    private String directoryPath;
    private String filepath;

    /**
     * Constructs a new Storage object.
     * @param directoryPath where the task data is stored.
     */
    public Storage(String directoryPath) {
        this.directoryPath = directoryPath;
        this.filepath = directoryPath + "duke.txt";
    }


    /**
     * Loads all the tasks saved in the file.
     * @param taskList the TaskList where all the tasks should be loaded into.
     */
    public void loadList(TaskList taskList) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }

        Path path = Paths.get(filepath);
        if (Files.exists(path)) {
            File file = new File(filepath);
            try {
                Scanner sc = new Scanner(file);

                while (sc.hasNextLine()) {
                    Task temp;
                    String line = sc.nextLine();
                    String[] nextTaskArr = line.split("\\|");
                    int nextTaskLength = nextTaskArr.length;
                    String nextTaskType = nextTaskArr[0].strip();

                    if (nextTaskType.equals("T")) {
                        if (nextTaskLength != 3) {
                            throw new DukeException("Your data might be corrupted~");
                        }
                        temp = new Todo(nextTaskArr[2].strip());
                        if (nextTaskArr[1].strip().equals("1")) {
                            temp.setDone();
                        }
                        taskList.add(temp);
                    } else if (nextTaskType.equals("D")) {
                        if (nextTaskLength != 4) {
                            throw new DukeException("Your data might be corrupted~");
                        }
                        temp = new Deadline(nextTaskArr[2].strip(), nextTaskArr[3].strip());
                        if (nextTaskArr[1].strip().equals("1")) {
                            temp.setDone();
                        }
                        taskList.add(temp);
                    } else if (nextTaskType.equals("E")) {
                        if (nextTaskLength != 4) {
                            throw new DukeException("Your data might be corrupted~");
                        }
                        temp = new Event(nextTaskArr[2].strip(), nextTaskArr[3].strip());
                        if (nextTaskArr[1].strip().equals("1")) {
                            temp.setDone();
                        }
                        taskList.add(temp);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            try {
                Files.write(path, new ArrayList<String>(), StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
    /**
     * Saves all the tasks into the file.
     * @param tasks the TaskList whose tasks should be stored into the file.
     */
    public void storelist(TaskList tasks) {
        Path path = Paths.get(filepath);
        List<String> savedTasks = new ArrayList<>();
        String seperator = " | ";
        for (int i = 0; i < tasks.getSize(); i++) {
            Task temp = tasks.get(i);
            String doneStatus = "0";
            if (temp.checkDone()) {
                doneStatus = "1";
            }

            if (temp instanceof Todo) {
                savedTasks.add("T" + seperator + doneStatus + seperator
                        + temp.getTaskName());
            } else if (temp instanceof Deadline) {
                Deadline tempDeadline = (Deadline) temp;
                savedTasks.add("D" + seperator + doneStatus + seperator
                        + tempDeadline.getTaskName() + seperator + tempDeadline.getDeadlineDate());
            } else if (temp instanceof Event) {
                Event tempEvent = (Event) temp;
                savedTasks.add("E" + seperator + doneStatus + seperator
                        + tempEvent.getTaskName() + seperator + tempEvent.getEventDate());
            }
        }
        try {
            Files.write(path, savedTasks, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Archives all the tasks into a specified file.
     * @param tasks the TaskList whose tasks should be stored.
     * @param archive the file name where the tasks should be archived.
     */
    public void archive(TaskList tasks, String archive) {
        String archivePath = directoryPath + archive;
        Path path = Paths.get(archivePath);
        List<String> savedTasks = new ArrayList<>();
        String seperator = " | ";
        for (int i = 0; i < tasks.getSize(); i++) {
            Task temp = tasks.get(i);
            String doneStatus = "0";
            if (temp.checkDone()) {
                doneStatus = "1";
            }

            if (temp instanceof Todo) {
                savedTasks.add("T" + seperator + doneStatus + seperator
                        + temp.getTaskName());
            } else if (temp instanceof Deadline) {
                Deadline tempDeadline = (Deadline) temp;
                savedTasks.add("D" + seperator + doneStatus + seperator
                        + tempDeadline.getTaskName() + seperator + tempDeadline.getDeadlineDate());
            } else if (temp instanceof Event) {
                Event tempEvent = (Event) temp;
                savedTasks.add("E" + seperator + doneStatus + seperator
                        + tempEvent.getTaskName() + seperator + tempEvent.getEventDate());
            }
        }

        try {
            Files.write(path, savedTasks, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
