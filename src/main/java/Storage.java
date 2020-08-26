import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// deals with loading tasks from the file and saving tasks in the file
public class Storage {
    private File file;

    Storage(String filePath) {
        this.file = new File(filePath);
    }


    // read tasks from hard disk and return a TaskList
    public TaskList load() throws IOException {
        TaskList list = new TaskList();
        if (file.createNewFile()) {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String description = task.substring(7);
                // check the 5th char is tick or cross
                boolean isDone = task.charAt(4) == '\u2713';
                Task newTask;

                // check the second character is T/D/E
                if (task.charAt(1) == 'T') {
                    newTask = new Todo(description, isDone);
                } else if (task.charAt(1) == 'D') {
                    newTask = new Deadline(description, isDone);
                } else {
                    newTask = new Event(description, isDone);
                }
                list.addTask(newTask);
            }
            sc.close();
        }
        return list;
    }

    public void writeToFile(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(file);
        List<Task> ls = list.getList();
        for (Task task : ls) {
            fw.write(task + "\n");
        }
        fw.close();
    }
}
