import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public final String file;


    public Storage(String file) {
        this.file = file;
    }

    public void saveTasks(TaskList taskList, Ui ui) {
        File savedFile = new File(".//SAVED-TASKS.txt");
        boolean exists = savedFile.exists();
        try {
            if (exists) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(".//SAVED-TASKS.txt"));
                StringBuilder tasks = new StringBuilder();
                for (int i = 1; i <= taskList.size(); i++) {
                    Task task = taskList.getTask(i);
                    tasks.append(task.toSaveString());
                    tasks.append("\n");
                }
                writer.write(tasks.toString());
                writer.close();
            } else {
                boolean created = savedFile.createNewFile();
                ui.printHasCreated(created);
                saveTasks(taskList, ui);
                System.out.println("File does not exist!");
            }
        } catch (IOException e) {
            ui.printIOError(e);
        }

    }

    public void load(TaskList taskList, Ui ui) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(this.file));
            String line = br.readLine();
            while (line != null) {
                Task task = loadTasks(line);
                taskList.addTask(task);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            ui.printIOError(e);
        }
    }
    public Task loadTasks(String line) {
        String[] tasks = line.split(" \\| ");
        String task = tasks[0];
        Boolean isDone = tasks[1].equals("1");
        String desc = tasks[2];
        String date = null;
        if (tasks.length > 3) {
            date = tasks[3];
        }
        Task newTask = null;
        if (task.equals("T")) {
            newTask = new ToDos(desc, isDone);
        } else if (task.equals("E")) {
            newTask = new Events(desc, date, isDone);
        } else if (task.equals("D")) {
            newTask = new Deadline(desc, date, isDone);
        }
        return newTask;
    }
}
