package duke.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.MocoException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Storage {
    private File taskList;
    private ArrayList<Task> tasks;

    /**
     * Constructor for Storage
     *
     * @param filePath File path for previously saved task list.
     */
    public Storage(String filePath) {
        this.tasks = new ArrayList<>();
        this.taskList = new File(filePath);
    }

    /**
     * Returns arraylist of tasks loaded from given file name.
     * If no file is found, a new one is created with an empty arraylist
     *
     * @return Task ArrayList
     * @throws MocoException If failure to create file or access file.
     */
    public ArrayList<Task> load() throws MocoException {
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
                    throw new MocoException(ex.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            throw new MocoException("Unable to find file of name 'tasklist :(");
        } catch (IOException e) {
            throw new MocoException("Error accessing file :(");
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
            default:
                break;
        }
    }

    protected void Save() throws MocoException {
        try {
            FileWriter newList = new FileWriter(taskList);
            for (Task t : tasks) {
                newList.write(t.saveText());
            }
            newList.flush();
            newList.close();
        } catch (Exception e) {
            throw new MocoException("Error saving update to task list");
        }
    }
}
