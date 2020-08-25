import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    Path filepath;

    public Storage(Path filepath) {
        this.filepath = filepath;
    }

    void updateMemory(ArrayList<Task> taskList) throws IOException {
        StringBuilder taskListString = new StringBuilder();
        for (Task task : taskList) {
            for (String attribute : task.attributeList()) {
                taskListString.append(attribute).append("\n");
            }
        }
        Files.writeString(filepath, taskListString, StandardOpenOption.WRITE);

    }

    ArrayList<Task> load() throws BlankTaskException, IOException {
        if (Files.notExists(filepath)) {
            if (Files.notExists(filepath.getParent())) {
                Files.createDirectories(filepath.getParent());
            }
            Files.createFile(filepath);
            return new ArrayList<>();
        } else {
            Scanner data;
            data = new Scanner(filepath);
            ArrayList<Task> taskList = new ArrayList<>();
            while (data.hasNextLine()) {
                Task curr;
                switch (data.nextLine()) {
                case "T":
                    curr = new ToDo(data.nextLine());
                    break;
                case "E":
                    curr = new Event(data.nextLine(), LocalDate.parse(data.nextLine()),
                            LocalTime.parse(data.nextLine()));
                    break;
                default:
                    curr = new Deadline(data.nextLine(), LocalDate.parse(data.nextLine()),
                            LocalTime.parse(data.nextLine()));
                    break;
                }
                if (data.nextBoolean()) {
                    curr.markAsDone();
                }
                taskList.add(curr);
                data.nextLine();
            }
            return taskList;
        }
    }
}
