import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private File file;
    private static final String SPLITTER = "|";

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public List<Task> load() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        try {
            if (file.exists()) {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String[] taskInformation = sc.nextLine().split("\\" + SPLITTER);
                    if (taskInformation[0].equals(TaskType.TODO.getInitial())) {
                        taskList.add(new ToDo(taskInformation[2], isDone(taskInformation[1])));
                    } else if (taskInformation[0].equals(TaskType.DEADLINE.getInitial())) {
                        taskList.add(new Deadline(taskInformation[2], isDone(taskInformation[1]), taskInformation[3]));
                    } else if (taskInformation[0].equals(TaskType.EVENT.getInitial())) {
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
            return taskList;
        } catch (IOException e) {
            throw new DukeException("I can't seem to load the file...");
        }
    }

    public String getDoneString(Task task) {
        return task.isDone() ? "1" : "0";
    }

    public boolean isDone(String storedTaskStatus) {
        return storedTaskStatus.equals("1");
    }

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
                    throw new DukeException("I can't seem to update the file...");
                }
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("I can't seem to handle the input/output properly...");
        }

    }
}
