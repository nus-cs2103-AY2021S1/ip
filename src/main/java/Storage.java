import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {

    String path;

    public Storage(Path filePath) {
        this.path = filePath.toString();
    }
    
    public void loadData(List<Task> todoList) throws IOException {
        File dataFolder = new File(Paths.get("data").toString());
        File data = new File(path);
        dataFolder.mkdirs();
        if (!data.createNewFile()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(path));
                String line;
                while ((line = br.readLine()) != null) {

                    String[] taskData = line.split("\\|");
                    String taskType = taskData[1];
                    Task task = null;

                    switch (taskType) {
                        case "T":
                            task = new ToDo(taskData[0], Boolean.parseBoolean(taskData[2]), taskData[3]);
                            break;
                        case "D":
                            task = new Deadline(taskData[0], Boolean.parseBoolean(taskData[2]), taskData[3], taskData[4]);
                            break;
                        case "E":
                            task = new Event(taskData[0], Boolean.parseBoolean(taskData[2]), taskData[3], taskData[4]);
                            break;
                    }
                    todoList.add(task);
                }
            } catch (IOException e) {
                System.out.println("duke.txt not found");
            }
        }
    }

    private void appendData(String data)  {
        try {
            FileWriter writer = new FileWriter(path, true);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            System.out.println("duke.txt not found");
        }
    }
    
    private void overwriteData(String data) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            System.out.println("duke.txt not found");
        }
    }
    
    public void saveTodo(ToDo task) {
        String line = task.getUniqueID() + "|" + task.getTaskType() + "|" + task.getDone() + "|"
                + task.getDescription() + "\n";
        appendData(line);
    }

    public void saveDeadline(Deadline task)  {
        String line = task.getUniqueID() + "|" + task.getTaskType() + "|" + task.getDone() + "|"
                + task.getDescription() + "|" + task.getTime() + "\n";
        appendData(line);
    }

    public void saveEvent(Event task) {
        String line = task.getUniqueID() + "|" + task.getTaskType() + "|" + task.getDone() + "|"
                + task.getDescription() + "|" + task.getTime() + "\n";
        appendData(line);
    }

    public void doneTask(Task task) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            StringBuilder newData = new StringBuilder();
            String line;
            
            while ((line = br.readLine()) != null) {
                if (line.contains(task.uniqueId)) {
                    line = line.replaceFirst("false", "true");
                }
                newData.append(line).append("\n");
            }
            br.close();
            overwriteData(newData.toString());
        } catch (Exception e) {
            System.out.println("duke.txt not found");
        }
        
    }

    public void deleteTask(Task task) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            StringBuilder newData = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                if (!line.contains(task.uniqueId)) {
                    newData.append(line).append("\n");
                }
            }
            br.close();
            overwriteData(newData.toString());
        } catch (Exception e) {
            System.out.println("duke.txt not found");
        }
    }
}