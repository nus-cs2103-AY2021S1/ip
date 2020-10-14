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

    /**
     * Creates a new Storage with the given filepath.
     *
     * @param filePath The filepath of the storage to be created.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Converts all tasks in the list to a string and writes that to the save file.
     *
     * @param taskList The list of tasks.
     */
    public void saveTasks(List<Task> taskList) {
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
     * Converts all notes in the list to a string and writes that to the save file.
     *
     * @param noteList The list of notes.
     */
    public void saveNotes(List<Note> noteList) {
        String toWrite = "";
        for (Note t : noteList) {
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
     *
     * @return List of Tasks that is converted from text.
     */
    public List<Task> readTasks() {
        List<Task> result = new ArrayList<Task>();
        File myObj = new File(filePath);

        try {
            if (!myObj.exists()) {
                createFile(myObj);
            }
            readFileTasks(result, myObj);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while trying to read the task list save file!");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Converts the text in the save file back to Notes.
     *
     * @return List of Notes that is converted from text.
     */
    public List<Note> readNotes() {
        List<Note> result = new ArrayList<Note>();
        File myObj = new File(filePath);

        try {
            if (!myObj.exists()) {
                createFile(myObj);
            }
            readFileNotes(result, myObj);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while trying to read the task list save file!");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Reads the text in the save file and converts it back to a list of Tasks.
     */
    private void readFileTasks(List<Task> result, File myObj) throws FileNotFoundException {
        Scanner myReader;
        myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            result.add(Task.readText(data));
        }
        myReader.close();
    }

    /**
     * Reads the text in the save file and converts it back to a list of Notes.
     */
    private void readFileNotes(List<Note> result, File myObj) throws FileNotFoundException {
        Scanner myReader;
        myReader = new Scanner(myObj);
        StringBuilder text = new StringBuilder();
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            text.append(data);
        }
        Note.readAllText(result, text.toString());
        myReader.close();
    }

    /**
     * Creates a new save file.
     *
     * @param myObj The file to be created.
     */
    private void createFile(File myObj) {
        try {
            myObj.createNewFile();
        } catch (IOException f) {
            System.out.println("An error occurred while creating the task list file! D:");
            f.printStackTrace();
        }
    }

    /**
     * Clears the save file of all text.
     */
    public void clear() {
        saveTasks(new ArrayList<Task>());
    }
}
