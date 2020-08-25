import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;

public class Storage {

    private String filename;

    /**
     * Class constructor specifying the filename in the form of a String.
     * @param filename the path of the file.
     */
    public Storage (String filename) {
        this.filename = filename;
    }

    /**
     * Loads the file if it exists. If not, create the file.
     * @return ArrayList with the previous saved Tasks
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ArrayList<Task> load() throws IOException, ClassNotFoundException{
        ArrayList<Task> store = new ArrayList<Task>();
        try {
            Path relativePath = Paths.get(filename);
            Path absolutePath = relativePath.toAbsolutePath();
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("" + absolutePath));
            ArrayList<Task> array = (ArrayList<Task>) in.readObject();
            in.close();
            for (Task task : array) {
                store.add(task);
            }
        } catch (EOFException e) {

        } catch (IOException e) {
            File yourFile = new File("store.ser");
            yourFile.createNewFile();
            FileOutputStream oFile = new FileOutputStream(yourFile, false);
        }
        return store;
    }

    /**
     * Stores the current task list into a File.
     * @param task the current ArrayList of Tasks
     * @throws IOException
     */
    public static void store(ArrayList<Task> task) throws IOException{
        Path relativePath = Paths.get("store.ser");
        Path absolutePath = relativePath.toAbsolutePath();
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("" + absolutePath)
        );
        out.writeObject(task);
        out.flush();
        out.close();
    }
}
