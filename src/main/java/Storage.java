import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
/**
 * Provides a handle to hard disk storage
 */
public class Storage {
    protected FileWriter writeHandle;
    protected Scanner readHandle;
    protected File fileHandle;
    protected ArrayList<Task> store;

    /**
     * Constructor
     */
    public Storage() {
        try {
            // Initializes storage folder & file
            File storageFolder = new File("./data");
            storageFolder.mkdir(); // creates folder if it doesn't exist

            File storageFile = new File("./data/duke.txt");
            storageFile.createNewFile(); // creates file if it doesn't exist

            // Initializes handle for writing to file
            this.writeHandle = new FileWriter("./data/duke.txt");
            this.readHandle = new Scanner(storageFile);


            // Initialize our store
            while (readHandle.hasNext()) {
                // Get the current store files
                // Try to open the file and parse the contents
                String data = readHandle.nextLine();
                ArrayList<Task> tasks = Parser.parseFile(data);
                // Load contents into store
                this.store = tasks;
            }
        } catch (Exception e) {
            System.out.println("Failed to initialize storage");
            System.exit(1);
        }
    }

    /**
     * Sync all tasks
     */
    public boolean syncTasks() {
        try {
            // Delete the current file
            fileHandle.delete();

            // Create a new file
            File storageFile = new File("./data/duke.txt");
            storageFile.createNewFile(); // creates file if it doesn't exist

            // Initializes handle for writing to file
            this.writeHandle = new FileWriter("./data/duke.txt");
            this.readHandle = new Scanner(storageFile);

            // Format the store output as a string
            String data = "";
            for ( Task task : store ) {
                data += task.toString();
            }
            // Write the store to file
            writeHandle.write(data);
            return true;
        } catch (Exception e) {
            System.out.println("Sync failed");
        }
        return false;
    }

    public void addTask(Task task) {
        String message = String.format("Added: %s", task.toString());
        System.out.println(message);
        store.add(task);
    }

    public void deleteTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= store.size()) {
            throw new MissingTaskException();
        }
        Task task = store.remove(taskIndex);
        System.out.println("Task deleted:");
        System.out.println(task);
    }
    public void completeTask(Integer taskIndex) {
        // If the task doesn't exist (It's index is missing)
        if (taskIndex < 0 || taskIndex >= store.size()) {
            throw new MissingTaskException();
        }

        // Set the task to done
        Task task = store.get(taskIndex);
        task.done();
        System.out.println("Task marked as complete:");
        System.out.println(task);
    }

    public void list() {
        for (int i = 0; i < store.size(); i++) {
            String listText = String.format("%d. %s", i + 1, store.get(i));
            System.out.println(listText);
        }
    }
}
