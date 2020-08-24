package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Storage {

    private Path filepath;

    private static final SimpleDateFormat dateTimeConverterFormat = new SimpleDateFormat("MMM dd yyyy hh:mma");
    private static final SimpleDateFormat dateConverterFormat = new SimpleDateFormat("MMM dd yyyy");

    public Storage(String filepath) {
        this.filepath = Paths.get(System.getProperty("user.dir") + filepath);
    }

    public ArrayList<Task> readFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        //From https://www.sghill.net/how-do-i-make-cross-platform-file-paths-in-java.html
        if (!Files.isRegularFile(filepath)) {
            createFile();
        } else {
            File f = new File(filepath.toString());
            try {
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String string = s.nextLine();
                    String[] arr = string.split(" \\| ");

                    boolean isDone = arr[1].equals("1");
                    boolean isTime;
                    Date date;
                    switch (arr[0]) {
                    case "T":
                        tasks.add(new ToDo(arr[2], isDone));
                        break;
                    case "D":
                        isTime = arr[4].equals("1");
                        date = (isTime) ? dateTimeConverterFormat.parse(arr[3]) : dateConverterFormat.parse(arr[3]);
                        tasks.add(new Deadline(arr[2], date, isTime, isDone));
                        break;
                    case "E":
                        isTime = arr[4].equals("1");
                        date = (isTime) ? dateTimeConverterFormat.parse(arr[3]) : dateConverterFormat.parse(arr[3]);
                        tasks.add(new Event(arr[2], date, isTime, isDone));
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Unable to find file :(");
                createFile();
            } catch (ParseException e) {
                System.out.println("Unable to parse date :(");
            }
        }
        return tasks;
    }

    public void createFile() {
        if (!Files.isDirectory(filepath.getParent())) {
            try {
                Files.createDirectory(filepath.getParent());
            } catch (IOException e) {
                System.out.println("Failed to create directory");
            }
        }

        try {
            Files.createFile(filepath);
        } catch (IOException e) {
            System.out.println("Failed to create file");
        }
    }

    public void writeToFile(ArrayList<Task> arrayList) {
        try {
            FileWriter fw = new FileWriter(filepath.toString());
            for (Task task : arrayList) {
                fw.write(task.toStoredTextString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }
    }
}
