import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    /**
     * Creates a Storage object.
     * @param filePath Pathname of the file that stores tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads file from hard disk when Duke bot is started.
     * @return List of tasks.
     * @throws IOException Input error.
     */
    public ArrayList<Task> loadFile() throws IOException {
        File file = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        if(file.exists()) {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while((line = br.readLine()) != null) {
                String[] strArr = line.split(" , ");
                switch(strArr[0]) {
                    case "T":
                        taskList.add(new Todo(strArr[1], strArr[2]));
                        break;
                    case "D":
                        taskList.add(new Deadline(strArr[1], strArr[2], LocalDate.parse(strArr[3])));
                        break;
                    case "E":
                        taskList.add(new Event(strArr[1], strArr[2], LocalDate.parse(strArr[3])));
                        break;
                }
            }
        } else {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return taskList;
    }

    /**
     * Save the tasks in the hard disk automatically whenever the task list changes.
     * @param tasks List of tasks.
     * @throws IOException Input error.
     */
    public void writeFile(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for(Task task : tasks) {
            writer.write(task.saveString() + "\n");
        }
        writer.close();
    }
    
}
