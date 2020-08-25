import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public List<Task> load() throws DukeException {
        try {
            if (file.exists()) {
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    System.out.println(s.nextLine()); //to change
                }
                s.close();
            } else {
                file.mkdirs();
                file.createNewFile();
            }
            return new ArrayList<>();
        } catch (IOException e){
            throw new DukeException("I can't seem to load the file...");
        }
    }

    public void updateTasks(List<Task> stored_task) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(file);
            String textToAdd = "";
            for (Task task : stored_task){
                textToAdd += task.getStoredString() + "\n";
            }
            fileWriter.write(textToAdd);
            fileWriter.close();
        } catch (IOException e){
            throw new DukeException("I can't seem to update the file...");
        }

    }
}
