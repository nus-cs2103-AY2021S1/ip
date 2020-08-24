import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Tasks implements java.io.Serializable {

    public static final File storage_file = Paths.get("tasks.ser").toFile();
    private ArrayList<Task> tasks;

    public Tasks() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        UI.print("added: " + task.toString() + numTasks());
        store();
    }

    public String numTasks() {
        int size = tasks.size();
        return "You now have " + size + " task" + (size > 1 ? "s" : "") + " in the list.\n";
    }

    public void print_tasks() {
        System.out.print(UI.LINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + "." + tasks.get(i));
        }
        System.out.print(UI.LINE);
    }

    public Task get(int i) throws DukeException {
        if (i < 0 || i >= tasks.size()) {
            throw new DukeException("invalid task number");
        }
        return tasks.get(i);
    }

    public void remove(int i) throws DukeException {
        if (i < 0 || i >= tasks.size()) {
            throw new DukeException("invalid task number");
        }
        tasks.remove(i);
        store();
    }

    public void setDone(int i, boolean value) throws DukeException {
        if (i < 0 || i >= tasks.size()) {
            throw new DukeException("invalid task number");
        }
        tasks.get(i).done = value;
        store();
    }

    public void store() {
        try {
            FileOutputStream fileOut = new FileOutputStream(storage_file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Tasks read() {
        try {
            Tasks t = null;
            FileInputStream fileIn = new FileInputStream(storage_file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            t = (Tasks) in.readObject();
            in.close();
            fileIn.close();
            return t;
        } catch (FileNotFoundException i) {
            try{
                storage_file.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return new Tasks();
        } catch (IOException i) {
            i.printStackTrace();
            return new Tasks();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return new Tasks();
        }
    }
}