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

public class Storage{

    private final String path;
    private final String fileName = "saved.duke";

    public Storage(String path) {
        this.path = path;
    }

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
