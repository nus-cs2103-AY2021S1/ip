import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Handles all of the file operations of "stored.txt" file.
 */
public class HandleFile {
    protected File storedData;
    protected ListOfItems list;

    /**
     * Constructor creates a new HandleFile object and passes to checkFile().
     *
     * @param list list of tasks
     */
    public HandleFile(ListOfItems list) {
        this.storedData = new File("stored.txt");
        this.list = list;
        this.checkFile();
    }

    /**
     * Checks whether if "stored.txt" file exists, which it will pass to readFile().
     * Else, it creates a new "stored.txt" file.
     */
    protected void checkFile() {
        try {
            if (!this.storedData.exists()) {
                this.storedData.createNewFile();
            } else {
                readFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the file using Scanner, and passes each line to ListOfItems's addStored(String input).
     */
    protected void readFile() {
        try {
            Scanner sc = new Scanner(this.storedData);
            while (sc.hasNextLine()) {
                this.list.addStoredList(sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes to "stored.txt", where it will copy each line from ListOfItems's list.
     *
     * @param listOfItems list of tasks
     */
    protected void writeFile(ListOfItems listOfItems) {
        try {
            List<Task> list = listOfItems.list;

            FileWriter fw = new FileWriter(this.storedData);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                assert list.get(i).id > 0 : "item id cannot be <= 0!";
                String taskText = task.toString();
                bw.write(taskText);
                bw.write("\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
