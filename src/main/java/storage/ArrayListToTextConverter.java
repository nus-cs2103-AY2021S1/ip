package storage;

import tasks.Task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class ArrayListToTextConverter {

    public static void convertArrayListToText(List<Task> tasks) {
        try {
            FileOutputStream writeData = new FileOutputStream("./taskdata.txt");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(tasks);
            writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
