package duke;

import duke.exception.DukeStorageException;
import duke.task.Task;
import duke.util.SerializeUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A storage class to handle the loading and save of the tasks.
 */
public class Storage{

    private final String path;
    private final String fileName = "saved.duke";

    /**
     * Public constructor of storage
     *
     * It requires a path of the saving directory to
     * save/load tasks.
     *
     * @param path Path of saving directory
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Load the tasks from local disk.
     *
     * Using the path provided, try to load the tasks and
     * deserialize it to TaskList object, if it does not exist, a new arraylist
     * will be created.
     * @return A list of tasks
     * @throws DukeStorageException If fails to find or deserialize the list
     */
    @SuppressWarnings("unchecked")
    public List<Task> load() throws DukeStorageException {
        List<Task> list = new ArrayList<>();

        try{
            File file = new File(this.path + this.fileName);
            if(file.exists()) {
                FileInputStream fis = new FileInputStream(file);


                list = (ArrayList<Task>) SerializeUtil.deserialize(fis.readAllBytes());
            }
        } catch(Exception e) {
            throw new DukeStorageException("Failed to load saved list.");
        }
        return list;
    }

    /**
     * Saves the current list of tasks on local disk.
     *
     * The list of tasks will be saved in the directory according to
     * the path given.
     * @param tasks TaskList to be stored
     * @throws DukeStorageException If fails to create file or serialize the tasklist object
     */
    public void save(TaskList tasks) throws DukeStorageException{
        try{
            File f = new File(path);
            if(!f.exists()){
                f.mkdirs();
            }

            File file = new File(this.path + this.fileName);
            file.delete();
            file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);
            byte[] data = SerializeUtil.serialize(tasks.getList());
            fos.write(data);
        } catch (IOException e) {
            throw new DukeStorageException("Failed to save changes.");
        }

    }
}
