import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    static String rtfPath = "data/duke.rtf";
    static String newLine = System.lineSeparator();


    public Storage(TaskList tasksLs) throws FileNotFoundException {
        readFromFile(rtfPath, tasksLs);
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        File f = new File(filePath);
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void readFromFile(String filePath, TaskList taskLs) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String eachTask = s.nextLine();
            if (eachTask.startsWith("[T]")) {
                String[] description = eachTask.split("%");
                Todo todoTask = new Todo(description[2]);
                taskLs.add(todoTask);
            } else if (eachTask.startsWith("[D]")) {
                String[] description = eachTask.split("%");
                Deadline deadlineTask = new Deadline(description[2], description[3]);
                taskLs.add(deadlineTask);
            } else if (eachTask.startsWith("[E]")) {
                String[] description = eachTask.split("%");
                Event eventTask = new Event(description[2], description[3]);
                taskLs.add(eventTask);
            } else {}
        }
    }

    public static String genList(ArrayList<Task> itemsLs) {
        String s = "";
        int i = 1;
        for (Task n : itemsLs) {
            if (n instanceof Todo) {
                s = s + "[T]" + "%" +  n.isDone + "%" + n.description + newLine;
            } else if (n instanceof Deadline) {
                s = s + "[D]" + "%" + n.isDone + "%" + n.description + "%" + ((Deadline) n).by + newLine;
            } else if (n instanceof  Event) {
                s = s + "[E]" + "%" + n.isDone + "%" + n.description + "%" + ((Event) n).at + newLine;
            } else {}
        }

        return s;
    }

}
