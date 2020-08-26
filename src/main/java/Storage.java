import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;
    private final ArrayList<Task> taskList = new ArrayList<>();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws IOException, DateException {

        File dataFile = new File(this.filePath);
        if (!dataFile.exists()) {
            return taskList;
        }
        FileReader reader = new FileReader(this.filePath);
        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();
        while (line != null) {
            loadTask(line);
            line = br.readLine();
        }
        reader.close();
        return this.taskList;
    }

    public void saveData(List<Task> taskList) throws IOException {

        File dataFile = new File(this.filePath);
        String DIR_PATH = dataFile.getParent();
        File directory = new File(DIR_PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }
        dataFile.createNewFile();
        FileWriter writer = new FileWriter(this.filePath);
        String data = "";
        for (Task task : taskList) {
            data += String.format("%s\n", formatTask(task));
        }
        writer.write(data);
        writer.close();

    }

    private String formatTask(Task task) {
        String name = task.getName();
        int isComplete = task.getStatus() ? 1 : 0;
        String type;
        if (task.getType() == TaskType.DEADLINE) {
            type = "D";
        } else if (task.getType() == TaskType.TODO) {
            type = "T";
        } else {
            type = "E";
        }
        if (type.equals("T")) {
            return String.format("%s|%d|%s", type, isComplete, name);
        } else {
            return String.format("%s|%d|%s|%s", type, isComplete,
                    name, DateFormat.formatDate(task.getDate()));
        }
    }

    private void loadTask(String line) throws DateException  {
        String[] taskComponent = line.split("\\|");
        boolean isComplete = !taskComponent[1].equals("0");
        String name = taskComponent[2];
        if (taskComponent[0].equals("T")) {
            taskList.add(new Todo(name, isComplete));
        } else if (taskComponent[0].equals("D")) {
            String by = taskComponent[3];
            taskList.add(new Deadline(name, isComplete, DateFormat.parseDate(by)));
        } else {
            String at = taskComponent[3];
            taskList.add(new Event(name, isComplete, DateFormat.parseDate(at)));
        }
    }
}
