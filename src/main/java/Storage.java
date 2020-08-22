import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class Storage {
    private List<Task> list;
    private static String FILE_PATH = "src/main/data/input.txt";

    public Storage() {
        this.list = new ArrayList<>();
    }

    public Storage(List<Task> taskArr) {
        this.list = taskArr;
    }

    public void addItem(Task item) {
        try {
            Storage.appendToFile(item);
            this.list.add(item);
        } catch (IOException e) {
            System.out.println("Sorry! The task failed to save. Please try again.");
        }
    }

    public int size() {
        return this.list.size();
    }

    public void setDone(int index) {
        Task task = this.list.get(index);
        task.setDone(true);
    }

    public Task getItem(int index) {
        return this.list.get(index);
    }

    public List getList() {
        return this.list;
    }

    public Task remove(int index) {
        return this.list.remove(index);
    };

    public static void printFileContents() {
        try {
            File f = new File(FILE_PATH); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static List<Task> parseFileContents() {
        try{
            File f = new File(FILE_PATH);
            Scanner s = new Scanner(f);
            List<Task> taskArr = new ArrayList<>();
            while (s.hasNext()) {
                String taskString = s.nextLine();
                String[] taskStringArr = taskString.split(" - ");
                Task task = Parser.parseTask(taskStringArr);
                taskArr.add(task);
            }
            return taskArr;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
            return null;
        }
    }

    private static String parseTaskToString(Task task) {
        return task.parseToSaveFormat();
    }

    public static void appendToFile(Task task) throws IOException {
        String textToAppend = parseTaskToString(task);
        FileWriter fw = new FileWriter(FILE_PATH, true); // create a FileWriter in append mode
        fw.write("\n" + textToAppend);
        fw.close();
    }

}
