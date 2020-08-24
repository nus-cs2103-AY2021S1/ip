import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileParser {
    public static List<Task> loadData(List<Task> list) throws Exception {
        try{
            FileInputStream readData = new FileInputStream("data/dukedata.ser");
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

    public static void writeData(List<Task> list) {
        try{
            FileOutputStream writeData = new FileOutputStream("data/dukedata.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(list);
            writeStream.flush();
            writeStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
