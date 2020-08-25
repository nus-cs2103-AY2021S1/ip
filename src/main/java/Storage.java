import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Storage {
    private static final String TODO_FORMAT = "T";
    private static final String EVENT_FORMAT = "E";
    private static final String DEADLINE_FORMAT = "D";
    private static final String SEPARATOR = "/";

    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public List<Task> load() throws DukeException {
        try {
            if (this.file.exists()) {
                ArrayList<Task> taskList = new ArrayList<>();
                Scanner scn = new Scanner(this.file);
                while (scn.hasNextLine()) {
                    String[] data = scn.nextLine().split(SEPARATOR);
                    switch (data[0]) {
                    case Storage.TODO_FORMAT:
                        taskList.add(new Todo(data[2], convertFormatToIsDone(data[1])));
                        break;
                    case Storage.EVENT_FORMAT:
                        taskList.add(new Event(data[2], convertFormatToIsDone(data[1]), data[3]));
                        break;
                    case Storage.DEADLINE_FORMAT:
                        taskList.add(new Deadline(data[2], convertFormatToIsDone(data[1]), data[3]));
                        break;
                    }
                }
                scn.close();
                return taskList;
            } else {
                this.file.getParentFile().mkdirs();
                this.file.createNewFile();
                return new ArrayList<>();
            }
        } catch (IOException e) {
            throw new DukeException("Sorry, there is an error loading the data");
        }
    }

    public void saveTaskList(TaskList tasks) throws DukeException {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(this.file);
            for (Task task : tasks.getListOfTasks()) {
                if (task instanceof Todo) {
                    fileWriter.write(String.join(Storage.SEPARATOR,
                            Storage.TODO_FORMAT, convertIsDoneToFormat(task.getIsDone()), task.getDescription()));
                } else if (task instanceof Event) {
                    fileWriter.write(String.join(Storage.SEPARATOR,
                            Storage.EVENT_FORMAT, convertIsDoneToFormat(task.getIsDone()), task.getDescription(), ((Event) task).getAt()));
                } else if (task instanceof Deadline) {
                    fileWriter.write(String.join(Storage.SEPARATOR,
                            Storage.DEADLINE_FORMAT, convertIsDoneToFormat(task.getIsDone()), task.getDescription(), ((Deadline) task).getBy()));
                } else {
                    throw new DukeException("Sorry, there is an error saving the task list here.");
                }
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Sorry, there is an error saving the task list here.");
        }
    }

    private boolean convertFormatToIsDone(String taskDoneFormat) {
        return taskDoneFormat.equals("1");
    }

    private String convertIsDoneToFormat(boolean isDone) {
        return isDone ? "1" : "0";
    }
}
