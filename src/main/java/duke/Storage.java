import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    String path;
    String content = "";

    public Storage(String path) {
        this.path = path;
    }

    public List<Task> load() throws DukeException {

        String fileLine = "";
        List<Task> taskList = new ArrayList<>();
        File file = new File("data/duke.txt");
        if (!file.exists()) {
            try {
                File dir = new File("data");
                dir.mkdir();
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("File cannot be created");
            }
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                while ((fileLine = reader.readLine()) != null) {
                    content += fileLine + "\n";
                    String[] str = fileLine.split(" \\| ");
                    switch (str[0]) {
                        case "T":
                            taskList.add(new Todo(str[2], Integer.parseInt(str[1])));
                            break;
                        case "D":
                            taskList.add(new Deadline(str[2], Integer.parseInt(str[1]), str[3]));
                            break;
                        case "E":
                            taskList.add(new Event(str[2], Integer.parseInt(str[1]), str[3]));
                            break;
                    }
                }
                content = content.trim();
                reader.close();
            } catch (FileNotFoundException e) {
                throw new DukeException("File not Found");
            } catch (IOException e) {
                throw new DukeException("Error reading file: " + path);
            }
        }
        return taskList;
    }

    public void save(Task task) {
        if(content.isEmpty()) {
            content = task.saveText(task.getStatus());
        } else {
            content = content + "\n" + task.saveText(task.getStatus());
        }
    }

    public void delete(Task task) {
        content = content.replace(task.saveText(task.getStatus()), "");
    }

    public void replace(Task task) {
        content = content.replace(task.saveText(0), task.saveText(1));
    }

    public void update() throws DukeException {
        File file = new File("data/duke.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error writing file: " + path);
        }

    }

}
