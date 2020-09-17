package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String filePath;
    private File file;

    /**
     * Creates a Storage object with data from save file.
     * @param filepath File path of the save file.
     */
    public Storage(String filepath) {
        this.filePath = filepath;
        this.file = new File(filepath);
    }

    /**
     * Writes text to save file.
     * @param textToAdd Text to write into save file.
     * @throws IOException If there is an error accessing the file.
     */
    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public String getFilePath() {
        return this.filePath;
    }

    public File getFile() {
        return this.file;
    }

    /**
     * Populates a TaskList with data from a save file.
     * @param list TaskList to be populated.
     * @throws IOException If there is an error accessing the file.
     * @throws IndexOutOfBoundsException If there is an error parsing the file.
     */
    public void populateList(TaskList list) throws IOException, IndexOutOfBoundsException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;

        if (!file.exists()) {
            Ui.displayMessage("Creating new data file: duke.txt");
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();

                assert file.exists();
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
                            list.list.add(new Todo(desc).taskDone());
                        } else {
                            list.list.add(new Todo(desc));
                        }
                    } else if (arr[0].equals("D")) {
                        String desc = arr[2];
                        String by = arr[3];
                        if (arr[1].equals("1")) {
                            list.list.add(new Deadline(desc, by).taskDone());
                        } else {
                            list.list.add(new Deadline(desc, by));
                        }
                    } else if (arr[0].equals("E")) {
                        String desc = arr[2];
                        String at = arr[3];
                        if (arr[1].equals("1")) {
                            list.list.add(new Event(desc, at).taskDone());
                        } else {
                            list.list.add(new Event(desc, at));
                        }
                    }
                }
                assert file.exists();
            } catch (IndexOutOfBoundsException e) {
                Ui.displayMessage("Sorry your duke.txt file is corrupt!");
            } catch (Exception e) {
                Ui.displayMessage(e.getMessage());
            }
        }


    }

}
