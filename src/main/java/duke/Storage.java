package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String FILE_PATH;

    Storage(String path) {
        FILE_PATH = path;
        try {
            File f = new File(FILE_PATH);
            if (!f.exists()) {
                if (f.getParentFile().mkdirs() && f.createNewFile()) {
                    System.out.println("\tStorage space for you tasks has been initialized successfully.");
                }
            }
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong when loading your tasks from storage.");
            e.printStackTrace();
        }
    }

    List<Task> processStorage() throws IOException {
        File f = new File(FILE_PATH);
        Scanner sc = new Scanner(f);
        List<Task> list = new ArrayList<>();
        while (sc.hasNext()) {
            String data = sc.nextLine();
            String[] dataArray = data.split(" \\| ");
            switch (dataArray[1]) {
            case "T":
                list.add(new ToDo(dataArray[3], dataArray[2]));
                break;
            case "E":
                list.add(new Event(dataArray[3], LocalDate.parse(dataArray[4]), dataArray[2]));
                break;
            case "D":
                list.add(new Deadline(dataArray[3], LocalDate.parse(dataArray[4]), dataArray[2]));
                break;
            default:
                throw new IOException("Invalid data");
            }
        }
        return list;
    }

    public void writeData(List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task t: list) {
            String toWrite = "";
            toWrite += (t.encode() + "\n");
            fw.write(toWrite);
        }
        fw.close();
    }
}
