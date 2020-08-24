import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load(Duke Duke) throws IOException {
        // read file
        try {
            File file = new File(filePath);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String user_input = reader.nextLine();
                Duke.user_input_handler(user_input, true);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // create folder if doesn't exist
            File path = new File("./data/");
            if (!path.isDirectory()){
                path.mkdir();
            }
            // create file if doesn't exist
            File file = new File("./data/duke.txt");
            file.createNewFile();
            // Load again
            load(Duke);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DukeException e) {
            e.printStackTrace();
        }
        ArrayList<Task> taskList = Duke.tasks.taskList;
        return taskList;
    }

    static void save(ArrayList<Task> taskList) throws IOException {
        File file = new File("./data/duke.txt");
        FileWriter fileWriter = new FileWriter(file, false);
        int index = 1;
        for (Task task: taskList) {
            // convert task into instruction(user input)
            String taskInst = "";
            if (task instanceof Todo) {
                Todo todo = (Todo) task;
                taskInst = String.format("todo %s\n", todo.description);
            } else if (task instanceof Event) {
                Event event = (Event) task;
                taskInst = String.format("event %s /at %s\n", event.description, event.eventTime);
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                taskInst = String.format("deadline %s /by %s\n", task.description, deadline.deadline);
            }
            //write instruction to text file
            fileWriter.write(taskInst);
            // add done instruction if task is done
            if (task.isDone) {
                fileWriter.write(String.format("done %s\n", index));
            }
            index += 1;
        }
        fileWriter.close();
    }

}
