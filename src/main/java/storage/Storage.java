package storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class Storage {

    // Read file
    private File f;
    private String dir;
    private String fileName;

    /**
     * Constructor creates the data folder and txt if not yet created.
     *
     * @param dest     the destination folder for the data file
     * @param filename the name of the file
     * @throws IOException   thrown when there's an error creating the data file.
     * @throws DukeException thrown when error in creating folder and data file.
     */
    public Storage(String dest, String filename) throws IOException, DukeException {
        this.dir = dest;
        this.fileName = filename;
        File dir = new File(dest);
        this.f = new File(this.dir, filename);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                throw new DukeException("Cannot create directory");
            }
        }
        if (!f.exists()) {
            if (!this.f.createNewFile()) {
                throw new DukeException("Cannot create data text file");
            }
        }
    }


    /**
     * Load reads the data file and returns a list of tasks stored inside.
     *
     * @return list of tasks stored in the data file as an ArrayList
     * @throws IOException thrown when scanner is unable to read the file
     */
    public ArrayList<Task> load() throws IOException, DukeException {
        ArrayList<Task> dukeList = new ArrayList<>();
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputSplit = input.split("/");

            assert inputSplit.length >= 2;
            Task task;
            switch (inputSplit[0]) {
            case "T":
                // two inputs
                task = new ToDo(inputSplit[3]);
                break;
            case "D":
                //three inputs
                task = new Deadline(inputSplit[3], inputSplit[4]);
                break;
            case "E":
                //three inputs
                task = new Event(inputSplit[3], inputSplit[4]);
                break;
            default:
                continue;
            }

            if (Boolean.parseBoolean(inputSplit[1])) {
                task.setDone();
            }

            String tagName = inputSplit[2];
            if (!tagName.equals("")) {
                task.setTag(tagName);
            }

            dukeList.add(task);

        }
        return dukeList;
    }

    /**
     * Adds the new task into the data file.
     *
     * @param task New task to be written into the data file.
     * @throws IOException thrown when unable to find data file.
     */
    public void addData(Task task) throws IOException {
        writeData(task.getParsedData() + "\n", true);
    }

    /**
     * Marks task as done in the data file
     *
     * @param order which order task to overwrite as done.
     * @throws IOException thrown when file cannot be found.
     */
    public void markDoneData(int order) throws IOException {
        editData(order, "true", 1);
    }

    /**
     * Adds the new task into the data file.
     *
     * @param order which order task to be deleted from the data file.
     * @throws IOException thrown when unable to find data file.
     */
    public void deleteData(int order) throws IOException {
        //New text
        Scanner reader = new Scanner(f);
        String newData = "";
        for (int i = 0; reader.hasNextLine(); i++) {
            if (i != order - 1) {
                newData = newData + reader.nextLine() + "\n";
            } else {
                reader.nextLine();
            }

        }

        writeData(newData, false);

    }

    /**
     * Writes the data as string into the data file.
     *
     * @param text     String to write or overwrite.
     * @param isAppend true to append text, false to overwrite entire file.
     * @throws IOException thrown when file cannot be found.
     */
    private void writeData(String text, Boolean isAppend) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(this.dir + "/" + this.fileName, isAppend));
        bw.write(text);
        bw.close();
    }

    /**
     * Sets Tagname of Task.
     *
     * @param order   which order task to setTag.
     * @param tagName name of tag.
     * @throws IOException thrown when file cannot be found.
     */
    public void setTag(int order, String tagName) throws IOException {
        editData(order, tagName, 2);
    }

    /**
     * editData of Task.
     *
     * @param order which order task to setTag.
     * @throws IOException thrown when file cannot be found.
     */
    public void editData(int order, String data, int type) throws IOException {
        String newData = "";
        Scanner reader = new Scanner(f);

        for (int i = 0; reader.hasNextLine(); i++) {
            if (i == order - 1) {
                String[] oldData = reader.nextLine().split("/");
                oldData[type] = data;
                String parsedData = String.join("/", oldData);
                newData = newData + parsedData + "\n";
            } else {
                newData = newData + reader.nextLine() + "\n";
            }
        }

        writeData(newData, false);
    }
}
