import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Storage {
    private final String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public ArrayList<Task> readFile() throws PathNoFoundException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            String cwd = System.getProperty("user.dir");
            if (Files.exists(Paths.get(cwd + filePath))) {
                List<String> allLines = Files.readAllLines(Paths.get(cwd + filePath));
                for (String line : allLines) {
                    String[] info = line.split(" \\| ");
                    String type = info[0];
                    int complete = Integer.parseInt(info[1]);
                    String title = info[2];
                    switch(type) {
                        case "T":
                            Todo newTodo = new Todo(title, complete);
                            tasks.add(newTodo);
                            break;
                        case "D":
                            String deadline = info[3];
                            LocalDate deadlineInLocalDate = LocalDate.parse(deadline);
                            Deadline newDeadline = new Deadline(title, complete, deadlineInLocalDate);
                            tasks.add(newDeadline);
                            break;
                        case "E":
                            String time = info[3];
                            LocalDate timeInLocalDate = LocalDate.parse(time);
                            Event newEvent = new Event(title, complete, timeInLocalDate);
                            tasks.add(newEvent);
                            break;
                    }
                }
            }

            return tasks;
        } catch (IOException ex) {
            throw new PathNoFoundException("No database found... A new database will be initialized!");
        }
    }

    public void saveFile(TaskList tasklist){
        try {
            String cwd = System.getProperty("user.dir");
            FileWriter fw = new FileWriter(cwd + filePath);
            for (Task task : tasklist.getTasks()) {
                String data = task.data();
                fw.write(data + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }


}
