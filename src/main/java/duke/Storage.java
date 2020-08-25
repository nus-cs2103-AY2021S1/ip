package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private static String filePath;
    private List<Task> tasks = new ArrayList<>();

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        if (f.exists()) {
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] arrOfStr = line.split(" @ ", 0);
                String symbol = arrOfStr[0];
                String status = arrOfStr[1];
                String description = arrOfStr[2];
                Task newTask;

                if (symbol.equals("[T]")) {
                    newTask = new Todo(description);
                } else if (symbol.equals("[D]")) {
                    String date = arrOfStr[3];
                    String[] dateSplit = date.split(" ", 0);
                    if (dateSplit.length > 3) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hhmm a");
                        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
                        newTask = new Deadline(description, localDateTime);
                    } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                        LocalDate localDate = LocalDate.parse(date, formatter);
                        newTask = new Deadline(description, localDate);
                    }
                } else {
                    String by = arrOfStr[3];
                    newTask = new Event(description, by);
                }

                if (status.equals("[✓]")) {
                    newTask.markAsDone();
                }
                tasks.add(newTask);
            }
        }
        return tasks;
    }

    public static String getFilePath() {
        return filePath;
    }

    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the file as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void updateFile(String filePath, TaskList tasks) throws IOException {
        writeToFile(filePath, "");
        for (Task tsk : tasks.getTasks()) {
            String textToAppend = tsk.getSymbol() + " @ " + tsk.getStatusIcon() + " @ "
                    + tsk.getDescription() + " @ " + tsk.getDate() + "\n";
            appendToFile(filePath, textToAppend);
        }
    }
}
