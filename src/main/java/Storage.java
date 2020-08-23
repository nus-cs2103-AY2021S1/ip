import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String fileLocation;
    public Storage(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public List<Task> readFile() throws InvalidSaveFileException {
        List<Task> toDoList = new ArrayList<Task>();
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            File file = new File(fileLocation);
            if (file.exists()) {
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    String entry = s.nextLine();
                    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm MMM d yyyy");
                    if (entry.startsWith("[T]")) {
                        ToDos t = new ToDos(entry.substring(7));
                        if (entry.contains("✓")) {
                            t.completeTask();
                        }
                        toDoList.add(t);
                    } else if (entry.startsWith("[D]")) {
                        int index = entry.indexOf("(");
                        String datetime = entry.substring(index + 5, entry.length() - 1);
                        LocalDateTime ldt = LocalDateTime.parse(datetime, dtf2);
                        Deadlines d = new Deadlines(entry.substring(7, index),
                                ldt);
                        if (entry.contains("✓")) {
                            d.completeTask();
                        }
                        toDoList.add(d);
                    } else {
                        int index = entry.indexOf("(");
                        String datetime = entry.substring(index + 5, entry.length() - 1);
                        LocalDateTime ldt = LocalDateTime.parse(datetime, dtf2);
                        Events e = new Events(entry.substring(7, index),
                                ldt);
                        if (entry.contains("✓")) {
                            e.completeTask();
                        }
                        toDoList.add(e);
                    }
                }
            }
        } catch (IOException e) {
            throw new InvalidSaveFileException("Hmmmm....there is something wrong with the previous save file! We " +
                    "will have to write a new one!");
        }
        return toDoList;
    }
    public void saveFile(List<Task> toDoList) throws InvalidSaveFileException {
        File dir = new File("data");
        if(!dir.exists()) {
            dir.mkdir();
        }
        try {
            FileWriter fw = new FileWriter(fileLocation);
            for(int i=0; i<toDoList.size();i++) {
                Task interim = toDoList.get(i);
                String desc = interim.toString();
                fw.write(desc+"\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new InvalidSaveFileException("\t☹ OOPS!!! Duke is experiencing IO errors when writing to save file!");
        }
    }
}
