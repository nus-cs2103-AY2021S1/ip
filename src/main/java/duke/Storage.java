package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private static final String SPLITTER = "|";
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads task information stored in the file.
     *
     * @return List of tasks after loading.
     * @throws DukeException When file formatting is wrong.
     */
    public List<Task> load() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        try {
            if (file.exists()) {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String[] taskInformation = sc.nextLine().split("\\" + SPLITTER);
                    if (taskInformation[0].equals(TaskType.TODO.getInitial())) {
                        assert taskInformation.length == 3; //change to erORORRO
                        taskList.add(new ToDo(taskInformation[2], isDone(taskInformation[1])));
                    } else if (taskInformation[0].equals(TaskType.DEADLINE.getInitial())) {
                        assert taskInformation.length == 4;
                        taskList.add(new Deadline(taskInformation[2], isDone(taskInformation[1]), taskInformation[3]));
                    } else if (taskInformation[0].equals(TaskType.EVENT.getInitial())) {
                        assert taskInformation.length == 4;
                        taskList.add(new Event(taskInformation[2], isDone(taskInformation[1]), taskInformation[3]));
                    } else {
                        throw new DukeException("File formatting is wrong...");
                    }
                }
                sc.close();
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public String getDoneString(Task task) {
        return task.isDone() ? "1" : "0";
    }

    public boolean isDone(String storedTaskStatus) {
        return storedTaskStatus.equals("1");
    }

    /**
     * Updates the tasks stored in file from the task list.
     *
     * @param taskList Task list to be updated in file.
     * @throws DukeException When there is an input or output error.
     */
    public void updateTasks(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : taskList.getStoredTasks()) {
                if (task instanceof ToDo) {
                    fileWriter.write(String.join(SPLITTER, TaskType.TODO.getInitial(), getDoneString(task),
                            task.getDescription()));
                } else if (task instanceof Deadline) {
                    fileWriter.write(String.join(SPLITTER, TaskType.DEADLINE.getInitial(), getDoneString(task),
                            task.getDescription(), ((Deadline) task).getBy()));
                } else if (task instanceof Event) {
                    fileWriter.write(String.join(SPLITTER, TaskType.EVENT.getInitial(), getDoneString(task),
                            task.getDescription(), ((Event) task).getAt()));
                } else {
                    assert (false) : "Tasks in taskList should have been in the correct format!";
                }
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("I can't seem to handle the input/output properly...");
        }

    }
}
