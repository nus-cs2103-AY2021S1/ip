import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private ArrayList<Task> txtData;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.txtData = new ArrayList<>();
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            File dir = new File(filePath);
            if (dir.exists()) {
                Scanner data = new Scanner(dir);

                while (data.hasNextLine()) {
                    String curr = data.nextLine();
                    String[] info = curr.split(", ", 4);
                    if (info[0].equals("T")) {
                        ToDo tobeAdded = new ToDo(info[2]);
                        if (info[1].equals("1")) {
                            tobeAdded.markAsDone();
                        }
                        txtData.add(tobeAdded);
                    } else if (info[0].equals("D")) {
                        Deadline tobeAdded = new Deadline(info[2], Duke.dateParser(info[3]));
                        if (info[1].equals("1")) {
                            tobeAdded.markAsDone();
                        }
                        txtData.add(tobeAdded);
                    } else if (info[0].equals("E")) {
                        String[] t = info[3].split(" ", 2);
                        Event tobeAdded = new Event(info[2], Duke.dateParser(t[0]), t[1]);
                        if (info[1].equals("1")) {
                            tobeAdded.markAsDone();
                        }
                        txtData.add(tobeAdded);
                    }
                }
            }
            return txtData;
        } catch (FileNotFoundException ex1) {
            throw new DukeException("Saved Data not found");
        } catch (DateTimeParseException ex2) {
            throw new DukeException("Date/Time incorrect format");
        }
    }

    public void overwriteData(ArrayList<Task> data) throws IOException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            String newData = "";
            for (Task k : data) {
                String textToAdd = k.storageForm();
                System.out.println(textToAdd);
                newData += textToAdd + System.lineSeparator();
            }
            fw.write(newData);
            fw.close();
        } catch (IOException ex1) {
            System.out.println("Something went wrong... " + ex1.getMessage());
        }
    }
}

