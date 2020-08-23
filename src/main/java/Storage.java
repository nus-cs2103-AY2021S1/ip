import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Storage {
    private final String filePath = "/data.txt";

    public Storage(){
    }

    public ArrayList<Task> readFile() throws PathNoFoundException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            String cwd = System.getProperty("user.dir");
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
            return tasks;
        } catch (IOException ex) {
            throw new PathNoFoundException("not database yet");
        }
    }

    public void saveFile(ArrayList<Task> tasks){
        try {
            String cwd = System.getProperty("user.dir");
            FileWriter fw = new FileWriter(cwd + filePath);
            for (Task task : tasks) {
                String data = task.data();
                fw.write(data + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }


}
