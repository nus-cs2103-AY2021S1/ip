import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {
    private File taskList;
    private ArrayList<Task> tasks;

    public Storage(String filePath) {
        this.tasks = new ArrayList<>();
        this.taskList = new File(filePath);
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            if (taskList.exists()) {
                FileReader fr = new FileReader(taskList);
                BufferedReader br = new BufferedReader(fr);
                String input;
                while ((input = br.readLine()) != null) {
                    String[] task = input.split(" \\| ", 4);
                    addToTaskList(task, tasks);
                }
                fr.close();
            } else {
                try {
                    taskList.createNewFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to find file of name 'tasklist");
        } catch (IOException e) {
            throw new DukeException("Error accessing file");
        }
        return tasks;
    }

    private void addToTaskList(String[] s, ArrayList<Task> tasks) {
        boolean isDone = s[2].equals("\u2713");
        switch (s[0]) {
            case "T":
                Todo t = new Todo(s[2], isDone);
                tasks.add(t);
                break;
            case "D":
                LocalDate by = LocalDate.parse(s[3]);
                Deadline dl = new Deadline(s[2], isDone, by);
                tasks.add(dl);
                break;
            case "E":
                LocalDate at = LocalDate.parse(s[3]);
                Event e = new Event(s[2], isDone, at);
                tasks.add(e);
                break;
        }
    }

    public void Save() throws DukeException {
        try {
            FileWriter newList = new FileWriter(taskList);
            for (Task t : tasks) {
                newList.write(t.saveText());
            }
            newList.flush();
            newList.close();
        } catch (Exception e) {
            throw new DukeException("Error saving update to task list");
        }
    }
}
