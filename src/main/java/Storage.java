import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final File taskFile;

    public Storage(String filePath) {
        this.taskFile = new File(filePath);
    }

    public List<Task> load() throws DukeException {
        if (!this.taskFile.exists()) {
            File dir = this.taskFile.getParentFile();
            if (dir != null && !dir.exists()) {
                dir.mkdirs();
            }

            try {
                this.taskFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace(); // todo
            }
        }

        Scanner sc = null;
        try {
            sc = new Scanner(this.taskFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // todo
        }

        if (sc != null) {
            List<Task> list = new ArrayList<>();
            while (sc.hasNext()) {
                String storedTask = sc.nextLine();
                Task task = parseFromStorage(storedTask);
                list.add(task);
            }
            return list;
        } else {
            return new ArrayList<Task>(); //todo
        }
    }

    public void update(Task task) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.taskFile, true);
            fileWriter.write("\n" + parseToStorage(task));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(List<Task> list) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.taskFile);
            String fileContents = parseToStorage(list.get(0));

            for (int i = 1; i < list.size(); i++) {
                fileContents += "\n" + parseToStorage(list.get(i));
            }

            fileWriter.write(fileContents);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace(); // todo
        }
    }

    public String parseToStorage(Task task) throws DukeException {
        String taskType = "";
        String status = task.isDone() ? "1" : "0";
        String taskDescription = "";

        if (task instanceof Todo) {
            taskType = "T";
            taskDescription = task.getTaskName();
        } else if (task instanceof Deadline) {
            taskType = "D"; // + date
            taskDescription = task.getTaskName() +
                " | " +
                ((Deadline) task).getDateTime().format(
                        DateTimeFormatter.ofPattern("d MMM yyyy, h.m a"));
        } else if (task instanceof Event) {
            taskType = "E";
            taskDescription = task.getTaskName() +
                    " | " +
                    ((Event) task).getDateTime().format(
                            DateTimeFormatter.ofPattern("d MMM yyyy, h.m a"));
        } else {
            throw new DukeException("Cannot recognise type");
        }

        return taskType +
                " | " +
                status +
                " | " +
                taskDescription;
    }

    public Task parseFromStorage(String storedTask) throws DukeException {
        String[] taskElements = storedTask.split(" \\| ", 4);

        try {
            Task task = null;
            if (storedTask.charAt(0) == 'T') {
                task = new Todo(taskElements[2]);
            } else if (storedTask.charAt(0) == 'D') {
                String taskName = taskElements[2];
                LocalDateTime dateTime = LocalDateTime.parse(taskElements[3],
                        DateTimeFormatter.ofPattern("d MMM yyyy, h.m a"));
                task = new Deadline(taskName, dateTime);
            } else if (storedTask.charAt(0) == 'E') {
                String taskName = taskElements[2];
                LocalDateTime dateTime = LocalDateTime.parse(taskElements[3],
                        DateTimeFormatter.ofPattern("d MMM yyyy, h.m a"));
                task = new Event(taskName, dateTime);
            }

            if (taskElements[1].equals("1")) {
                task.markDone();
            }

            if (task != null) {
                return task;
            } else {
                throw new DukeException("Cannot read tasks from file.");
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new DukeException("Cannot read tasks from file.");
        }
    }
}
