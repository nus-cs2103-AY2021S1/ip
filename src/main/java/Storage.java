import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filepath;


    public Storage(String filepath) {
        this.filepath = filepath;
    }

    protected ArrayList<Task> loadData() throws IOException {
            ArrayList<Task> taskList = new ArrayList<>();
            Files.createDirectories(Paths.get("/Users/nigelng/Desktop/Y2S1/CS2103T/IndivProj/Data"));
            File dukeData = new File(this.filepath);
            if (dukeData.exists()) {
                Scanner dukeScanner = new Scanner(dukeData);
                while (dukeScanner.hasNext()) {
                    String txtLine = dukeScanner.nextLine();
                    String[] txtLineArr = txtLine.split("//");
                    Task task;
                    if (txtLineArr[0].equals("T")) {
                        task = new Todo(txtLineArr[1]);
                    } else if (txtLineArr[0].equals("E")) {
                        task = new Event(txtLineArr[2], txtLineArr[3]);
                    } else {
                        task = new Deadline(txtLineArr[2], txtLineArr[3]);
                    }
                    taskList.add(task);
                }
                return taskList;
            } else {
                File newDukeData = new File(this.filepath);
                newDukeData.createNewFile();
                return taskList;

        }
    }

}
