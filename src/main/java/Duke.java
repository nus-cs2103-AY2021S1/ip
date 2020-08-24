import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static List<Task> load(String filePath) throws DukeException {
        File file = new File(filePath);
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] dataArray = data.split(" \\| ");
                String letter = dataArray[0];
                int bit = Integer.parseInt(dataArray[1]);
                Task task;
                if (letter.equals("T")) {
                    task = new Todo(dataArray[2]);
                } else if (letter.equals("D")) {
                    task = new Deadline(dataArray[2], dataArray[3]);
                } else {
                    task = new Event(dataArray[2], dataArray[3]);
                }
                if (bit == 1) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
            sc.close();
            return tasks;
        } catch (FileNotFoundException e) {
            String errorMessage = "No history found, "
                    + "starting up with no saved records...";
            throw new DukeException(errorMessage);
        }
    }

    public static void main(String[] args) {
        try {
            List<Task> tasks = Duke.load("data/duke.txt");
            TaskManager manager = new TaskManager(tasks);
            manager.initialise();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            TaskManager manager = new TaskManager();
            manager.initialise();
        }
    }
}
