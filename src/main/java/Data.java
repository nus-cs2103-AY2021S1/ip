import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Data {
    final Path path;

    Data(String path) throws IOException {
        //System.out.println("Path being located");
        this.path = Paths.get("src/main/data/duke.txt").toAbsolutePath();
        if (Files.notExists(this.path)) {
            new File(String.valueOf(path)).createNewFile();
        }
    }

    public List<Task> loadData() throws FileNotFoundException {
        List<Task> todoList = new ArrayList<>();
        Scanner scanner = new Scanner(path.toFile());
        for (int i = 1; scanner.hasNextLine(); i++) {
            Task currTask = null;
            String[] curr = scanner.nextLine().split("---");
            String task = curr[0];
            boolean isDone = Integer.parseInt(curr[1]) == 1;
            String activity = curr[2];
            switch (task) {
                case "T":
                    currTask = new Todo(activity, i, isDone);
                    break;
                case "D": {
                    String description = activity + " " + curr[3];
                    currTask = new Deadline(description, i, isDone);
                    break;
                }
                case "E": {
                    String description = activity + " " + curr[3];
                    currTask = new Event(description, i, isDone);
                    break;
                }
            }
            todoList.add(currTask);
        }
        return todoList;
    }

    public void save(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(this.path.toString());
        for (Task task : tasks) {
            String line = "";
            //int index = task.index;
            int done = task.isDone ? 1 : 0;
            String description = task.description;
            switch (task.type) {
                case TODO:
                    line = String.format("T---%d---%s", done, description);
                    break;
                case DEADLINE:
                case EVENT:
                    int idx = description.indexOf('/');
                    String activity = description.substring(0, idx - 1);
                    String timing = description.substring(idx);
                    line = String.format("%s---%d---%s---%s",
                            task.type == TaskType.DEADLINE ? "D" : "E", done, activity, timing);
                    break;
            }
            writer.write(line + "\n");
        }
        writer.close();
    }

}
