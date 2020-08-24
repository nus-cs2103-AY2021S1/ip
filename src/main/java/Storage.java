import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadData() throws Exception {
        List<Task> list = new ArrayList<>();
        try{
            FileInputStream readData = new FileInputStream(filePath);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            ArrayList<Task> loadedList = (ArrayList<Task>) readStream.readObject();
            readStream.close();
            list = loadedList;
        }catch (Exception ex) {
            if (ex instanceof FileNotFoundException) {
                throw new FileNotFoundException("☹ OOPS!!! I can't find your file!");
            } else {
                throw new Exception("☹ OOPS!!! There was an error in loading file.");
            }
        }
        return list;
    }

    public void writeData(List<Task> list) {
        try{
            FileOutputStream writeData = new FileOutputStream(filePath);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(list);
            writeStream.flush();
            writeStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
