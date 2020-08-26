import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TaskSaver {
    private Path savePath;

    TaskSaver (Path savePath) {
        this.savePath = savePath;
    }

    public void save(List<Task> taskList) {
        File saveFile = savePath.toFile();
        try {
            if (!saveFile.exists()) {
                saveFile.mkdirs();
                saveFile.createNewFile();
            }
            FileWriter writer = new FileWriter(saveFile);
            for (Task task : taskList) {
                writer.append(task.toSaveFormat() + "\n");
            }
            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't save to file :(");
        }
    }

    public List<Task> load() {
        List<Task> taskList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(savePath)) {
            stream.forEach((s -> {
                String[] saveParts = s.split(" \\| ");
                switch (saveParts[0]) {
                    case "T":
                        taskList.add(new Todo(saveParts[2], saveParts[1].equals("1")));
                        break;
                    case "D":
                        taskList.add(new Deadline(saveParts[2], saveParts[3], saveParts[1].equals("1")));
                        break;
                    case "E":
                        taskList.add(new Deadline(saveParts[2], saveParts[3], saveParts[1].equals("1")));
                        break;
                }
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;

    }

}
