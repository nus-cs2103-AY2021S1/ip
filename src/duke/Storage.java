package duke;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Storage {
    String filePath;
    File f;

    public Storage(String filepath) {
        this.filePath = filepath;
        this.f = new File(filepath);
    }

    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void populateList(TaskList list) throws IOException, IndexOutOfBoundsException {
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line;

        if (!f.exists()) {
            Ui.displayMessage("Creating new data file: duke.txt");
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
            } catch (IOException e) {
                Ui.displayMessage(e.getMessage());
            }
        } else {
            Ui.displayMessage("duke.txt file found, loading data");
            try {
                while ((line = br.readLine()) != null) {
                    String[] arr = line.split("@", 4);
                    if (arr[0].equals("T")) {
                        String desc = arr[2];
                        if (arr[1].equals("1")) {
                            list.addToList(new Todo(desc).taskDone());
                        } else {
                            list.addToList(new Todo(desc));
                        }
                    } else if (arr[0].equals("D")) {
                        String desc = arr[2];
                        String by = arr[3];
                        if (arr[1].equals("1")) {
                            list.addToList(new Deadline(desc, by).taskDone());
                        } else {
                            list.addToList(new Deadline(desc, by));
                        }
                    } else if (arr[0].equals("E")) {
                        String desc = arr[2];
                        String at = arr[3];
                        if (arr[1].equals("1")) {
                            list.addToList(new Event(desc, at).taskDone());
                        } else {
                            list.addToList(new Event(desc, at));
                        }
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                Ui.displayMessage("Sorry your duke.txt file is corrupt!");
            } catch (Exception e) {
                Ui.displayMessage(e.getMessage());
            }
        }


    }

}
