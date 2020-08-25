package duke.tool;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadData() throws IOException {
        DateTimeFormatter validFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<Task> orderList = new ArrayList<>();

        try {
            File dataStorage = new File(filePath);
            Scanner s = new Scanner(dataStorage);
            while (s.hasNext()) {
                String curr = s.nextLine();
                String[] currTask = curr.split("|");
                Boolean isDone = currTask[1] == "1";
                if (currTask[0] == "T") {
                    orderList.add(new Todo(currTask[2], isDone));
                } else if (currTask[0] == "D") {
                    orderList.add(new Deadline(currTask[2],
                            LocalDateTime.parse(currTask[3], validFormat), isDone));
                } else if (currTask[0] == "E") {
                    orderList.add(new Event(currTask[2],
                            LocalDateTime.parse(currTask[3], validFormat), isDone));
                }
            }
        } catch (FileNotFoundException e) {
            if (new File("data").mkdir()) {
                System.out.println("folder data does not exist yet.");
            } else if (new File(filePath).createNewFile()) {
                System.out.println("File duke.txt does not exist yet.");
            }
        }

        return orderList;

    }

    public void writeData(ArrayList<Task> orderlist) {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            for (Task task : orderlist) {
                fw.write(task.fileFormattedString() + "\n");
            }
            fw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
