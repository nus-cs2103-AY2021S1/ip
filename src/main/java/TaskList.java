import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private InputStream input = null;
    public TaskList() {
        try {
//            FileReader file = new FileReader("C:\\Users\\banik\\OneDrive\\Desktop\\NUS Y2S1\\CS2103\\Duke ChatBot\\src\\main\\java\\Data\\duke.txt");
            FileReader file = new FileReader("Data/duke.txt");
            BufferedReader reader = new BufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null){
                tasks.add(getTask(line));
            }
            file.close();
        } catch (IOException e){
            tasks = new ArrayList<>();
        }
    }

    private Task getTask(String line){
        Task task;
        if (line.charAt(1) == 'T'){
            task = new ToDos(line.substring(6));
        } else if (line.charAt(1) == 'D'){
            int index = line.indexOf("(by:");
            task = new Deadline(line.substring(6, index), line.substring(index + 5));
        } else {
            int index = line.indexOf("(at:");
            task = new Event(line.substring(6, index), line.substring(index + 5));
        }
        if (line.charAt(4) == '✓'){
            task.updateStatus();
        }
        return task;
    }

    public void update(Task task){
        tasks.add(task);
    }

    public Task get(int i){
        return tasks.get(i - 1);
    }

    public void delete(int i){
        tasks.remove(i - 1);
    }

    public void updateStatus(int i){
        tasks.get(i - 1).updateStatus();
    }

    public int getSize(){
        return tasks.size();
    }
    public String toString(){
        StringBuilder line = new StringBuilder();
        for (Task task : tasks) {
            line.append(task.toString());
            line.append('\n');
        }
        return line.toString();
    }

    public static void main(String[] args) throws IOException {
        TaskList task = new TaskList();
    }
}
