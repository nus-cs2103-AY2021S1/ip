import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles storing and retrieving of task list.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Converts all tasks in the list to a string and writes that to the save file.
     * @param taskList The list of tasks.
     */
    public void save(List<Task> taskList) {
        String toWrite = "";
        for (Task t : taskList) {
            toWrite += t.saveText();
            toWrite += "\n";
        }
        try {
            File tasks = new File(filePath);
            tasks.createNewFile();
            FileWriter myWriter = new FileWriter(tasks);
            myWriter.write(toWrite);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the task list! D:");
            e.printStackTrace();
        }
    }

    /**
     * Converts the text in the save file back to Tasks.
     * @return List of Tasks that is converted from text.
     */
    public List<Task> read() {
        List<Task> result = new ArrayList<Task>();
        File myObj = new File(filePath);
        Scanner myReader;
        try {
            if (!myObj.exists()) {
                try {
                    myObj.createNewFile();
                } catch (IOException f) {
                    System.out.println("An error occurred while creating the task list file! D:");
                    f.printStackTrace();
                }
            }
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                result.add(Task.readText(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while trying to read the tasklist save file!");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Clears the save file of all text.
     */
    public void clear() {
        save(new ArrayList<Task>());
    }
}
