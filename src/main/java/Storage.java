import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    File file;
    String filepath;

    /**
     * Constructor for Storage.
     * @param filepath
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        try {
            this.file = new File(filepath);
            this.file.getParentFile().mkdirs();

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load all the tasks from the files.
     * @return an arraylist of all the tasks
     * @throws FileNotFoundException
     */
    public ArrayList<Task> loadData() throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        ArrayList<Task> list = new ArrayList<>();
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            boolean isDone = data.contains("| 1 |") ? true : false;
            String description = data.split(" | ")[1];
            if (data.startsWith("T")) {
                ToDo toDo = new ToDo(description);
                if (isDone) {
                    toDo.markAsDone();
                }
                list.add(toDo);
            } else if (data.startsWith("D")) {
                String[] arr = description.split(" | ");
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(arr[1], inputFormat);
                dateTime.format(outputFormat);
                Deadline deadline = new Deadline(arr[0], dateTime);
                if (isDone) {
                    deadline.markAsDone();
                }
                list.add(deadline);
            } else if (data.startsWith("E")) {
                String[] arr = description.split(" | ");
                Event event = new Event(arr[0], arr[1]);
                if (isDone) {
                    event.markAsDone();
                }
                list.add(event);
            }
        }
        reader.close();
        return list;
    }

    /**
     * Save new task to the file.
     * @param task
     * @throws IOException
     */
    public void saveTask(Task task) throws IOException {
        BufferedWriter file = new BufferedWriter(new FileWriter(
                filepath, true));
        file.newLine();
        file.write(task.toSave());
        file.close();
    }
}
