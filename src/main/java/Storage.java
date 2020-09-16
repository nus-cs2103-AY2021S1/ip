import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;
    
    public Storage(String filePath) {
        this.file = new File(filePath);
    }
    
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] task = line.split(" ");
                if (task[0].equals("T")) {
                    Task todo = new Todo(task[4]);
                    if (task[2].equals("✓")) {
                        todo.markDone();
                    }
                    tasks.add(todo);
                } else if (task[0].equals("D")) {
                    Task deadline = new Deadline(task[4], LocalDateTime.parse(task[6]));
                    if (task[2].equals("✓")) {
                        deadline.markDone();
                    }
                    tasks.add(deadline);
                } else if (task[0].equals("E")) {
                    Task event = new Event(task[4], LocalDateTime.parse(task[6]));
                    if (task[2].equals("✓")) {
                        event.markDone();
                    }
                    tasks.add(event);
                } else {
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Folder or file does not exist yet! Please make sure you have data/duke.txt in ip directory. ");
        }
        return tasks;
    }
    
    public void writeFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(tasks.getList());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        
    }
    
}
