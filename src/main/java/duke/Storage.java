package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {

    private Path dukeFile;

    public Storage(String filePath) {
        createFile(filePath);
    }

    private void createFile(String filePath) {
        try {

            if (Files.notExists(Paths.get(filePath))) {
                Files.createDirectory(Paths.get(filePath));
            }

            if (Files.notExists(Paths.get(filePath + "/duke.txt"))) {
                dukeFile = Files.createFile(Paths.get(filePath + "/duke.txt"));
            } else {
                dukeFile = Paths.get(filePath + "/duke.txt");
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void saveTasks(ArrayList<Task> taskList) {

        StringBuilder taskString = new StringBuilder();

        for (Task task: taskList) {
            taskString.append(task.saveFormat());
            taskString.append("\n");
        }

        try {
            Files.writeString(dukeFile, taskString);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public ArrayList<Task> load() throws DukeException {
        try {

            ArrayList<Task> taskList = new ArrayList<>();

            for (String task : Files.readAllLines(dukeFile)) {
                taskList.add(readTask(task));
            }

            return taskList;

        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new DukeException("Loading Error");
        }
    }

    private Task readTask(String task) {
        switch (task.charAt(0)) {
            case 'T':
                ToDo toDo = new ToDo(task.substring(3));

                if (task.charAt(1) == '1') {
                    toDo = toDo.markDone();
                }

                return toDo;
                // Fallthrough

            case 'D':
                Deadline deadline = new Deadline(task.substring(3, task.length() - 19),
                        LocalDateTime.parse(task.substring(task.length() - 19)));

                if (task.charAt(1) == '1') {
                    deadline = deadline.markDone();
                }

                return deadline;
                // Fallthrough

            case 'E':

                String taskText = task.substring(3, task.length() - 43);
                String startDateString = task.substring(task.length() - 42, task.length() - 23);
                String endDateString = task.substring(task.length() - 19);

                Event event = new Event(taskText,
                        LocalDateTime.parse(startDateString),
                        endDateString.equals("XXXXXXXXXXXXXXXXXXX")
                                ? null
                                : LocalDateTime.parse(endDateString));

                if (task.charAt(1) == '1') {
                    event = event.markDone();
                }

                return event;
                // Fallthrough

            default:
                return null;
                // Fallthrough
        }
    }
}