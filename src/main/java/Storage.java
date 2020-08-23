import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String pathname;

    public Storage(String pathname) {
        this.pathname = pathname;
    }

    public void load(TaskList taskList) throws IOException {
        String[] directories = this.pathname.split("/");
        try {
            for (int i = 0; i < directories.length - 1; i++) {
                File directory = new File(directories[i]);
                if (!directory.exists()) {
                    directory.mkdir();
                }
            }
            File f = new File(this.pathname);
            f.createNewFile();
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String[] data;
                data = s.nextLine().split(" \\| ");
                switch (data[0]) {
                    case "T":
                        taskList.addTask(taskList.createTask(TaskType.TypeOfTask.TODO, data[2], null, data[1].equals("1") ? true : false));
                        break;
                    case "D":
                        taskList.addTask(taskList.createTask(TaskType.TypeOfTask.DEADLINE, data[2], LocalDateTime.parse(data[3]), data[1].equals("1") ? true : false));
                        break;
                    case "E":
                        taskList.addTask(taskList.createTask(TaskType.TypeOfTask.EVENT, data[2], LocalDateTime.parse(data[3]), data[1].equals("1") ? true : false));
                        break;
                }
            }
        } catch (IOException e) {
            throw e;
        }
    }

    public void save(TaskList taskList) throws IOException {
        String taskString = "";
        ArrayList<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String typeOfTask = task.getClass().getName();
            String timing = "";

            switch (typeOfTask) {
                case "Todo":
                    taskString = taskString + "T";
                    break;
                case "Deadline":
                    taskString = taskString + "D";
                    timing = timing + ((Deadline) task).getTiming();
                    break;
                case "Event":
                    taskString = taskString + "E";
                    timing = timing + ((Event) task).getTiming();
                    break;
            }

            taskString = taskString + " | " + (task.getDone() ? "1" : "0") + " | " + task.getTaskDescription()
                    + (typeOfTask.equals("Deadline") || typeOfTask.equals("Event")
                    ? " | " + timing
                    : "");

            taskString = taskString + "\n";
        }

        try {
            FileWriter fw = new FileWriter(this.pathname);
            fw.write(taskString);
            fw.close();
        } catch (IOException e) {
            throw e;
        }
    }
}
