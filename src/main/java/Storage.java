import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;

public class Storage {
    private final Path path;
    private static final String DESTINATION = "./duke.txt";

    public Storage() {
        path = Paths.get(DESTINATION);
        File file = new File(DESTINATION);

        if (Files.notExists(this.path)) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Task> readData() throws IOException {
        Scanner sc = new Scanner(path.toFile());
        List<Task> list = new ArrayList<>();

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] details = line.split(":");
            String type = details[0];
            boolean isCompleted = details[1].equals("true");
            String taskString = details[2];
            Task task;

            if (type.equals("T")) {

                task = new Todo(taskString, isCompleted);

            } else if (type.equals("D")) {

                task = new Deadline(taskString, details[3], isCompleted);

            } else {

                task = new Event(taskString, details[3], isCompleted);

            }

            list.add(task);
        }

        return list;
    }

    public void addData(List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(DESTINATION);

        for (Task task : list) {

            String toAdd = "";
            String completed = String.valueOf(task.isCompleted);

            if (task instanceof Todo) {

                toAdd = "T:" + completed + ":" + task.task;

            } else if (task instanceof Deadline) {

                toAdd = "D:" + completed + ":" + task.task + ":" + ((Deadline) task).date;

            } else if (task instanceof Event) {

                toAdd = "E:" + completed + ":" + task.task + ":" + ((Event) task).date;

            } else {
                // nothing
            }
            
            fw.write(toAdd + "\n");
        }

        fw.close();
    }

    public void clear() throws IOException {
        FileWriter fw = new FileWriter(DESTINATION);
        fw.close();
    }

}
