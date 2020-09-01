import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected ArrayList<Task> arr;
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.arr = new ArrayList<Task>();
    }

    public ArrayList<Task> load() throws IOException {
        File data = new File(filePath);

        // Check if duke.txt exists, if not create file and retrieve data
        if (!data.exists()) {
            data.createNewFile();
        } else {
            Scanner read = new Scanner(data);
            while (read.hasNext()) {
                String currLine = read.nextLine();
                Scanner cL = new Scanner(currLine);
                String type = cL.next();
                cL.next();
                String isDone = cL.next();
                cL.next();
                if (type.equals("T")) {
                    Todo toAdd = new Todo(cL.nextLine().substring(1));
                    if (isDone.equals("1")) {
                        toAdd.markAsDone();
                    }
                    arr.add(toAdd);
                } else if (type.equals("D")) {
                    String desc = cL.next();
                    String add = cL.next();
                    while (!add.equals("|")) {
                        desc = desc + " " + add;
                        add = cL.next();
                    }
                    String dtString = (cL.nextLine()).substring(1);
                    Scanner dT = new Scanner(dtString);
                    LocalDate date = LocalDate.parse(dT.next());
                    Deadline curr;
                    if (dT.hasNext()) {
                        String duration = dT.next();
                        curr = new Deadline(desc, date, duration);
                    } else {
                        curr = new Deadline(desc, date);
                    }
                    arr.add(curr);
                } else if (type.equals("E")) {
                    String desc = cL.next();
                    String add = cL.next();
                    while (!add.equals("|")) {
                        desc = desc + " " + add;
                        add = cL.next();
                    }
                    String dtString = (cL.nextLine()).substring(1);
                    Scanner dT = new Scanner(dtString);
                    LocalDate date = LocalDate.parse(dT.next());
                    Event curr;
                    if (dT.hasNext()) {
                        String duration = dT.next();
                        curr = new Event(desc, date, duration);
                    } else {
                        curr = new Event(desc, date);
                    }
                    arr.add(curr);
                }
            }
        }
        return arr;
    }

    /**
     * Function to write data to txt file. Processes the instances and then converts to String to be written.
     * @param array
     * @throws IOException
     */
    public void saveData(ArrayList<Task> array) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task curr : array) {
            String toWrite = null;
            if (curr instanceof Todo) {
                String state = curr.isDone ? " | 1 | " : " | 0 | ";
                toWrite = "T" + state + curr.description;
            } else if (curr instanceof Deadline) {
                String state = curr.isDone ? " | 1 | " : " | 0 | ";
                toWrite = "D" + state + curr.description + " | " + curr.date;
                if (curr.duration != null) {
                    toWrite = toWrite + " " + curr.duration;
                }
            } else if (curr instanceof Event) {
                String state = curr.isDone ? " | 1 | " : " | 0 | ";
                toWrite = "E" + state + curr.description + " | " + curr.date;
                if (curr.duration != null) {
                    toWrite = toWrite + " " + curr.duration;
                }
            }
            assert toWrite != null;
            fw.write(toWrite);
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
