package Duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> dukeList = new ArrayList<>();
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputSplit = input.split("/");

            Task task;
            switch (inputSplit[0]) {
                case "T":
                    // two inputs
                    task = new ToDo(inputSplit[2]);
                    break;
                case "D":
                    //three inputs
                    task = new Deadline(inputSplit[2], inputSplit[3]);
                    break;
                case "E":
                    //three inputs
                    task = new Event(inputSplit[2], inputSplit[3]);
                    break;
                default:
                    continue;
            }

            if (Boolean.parseBoolean(inputSplit[1])) {
                task.setDone();
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
     * @throws IOException
     */
    public void markDoneData(int order) throws IOException {
        String newData = "";
        Scanner reader = new Scanner(f);

        for (int i = 0; reader.hasNextLine(); i++) {
            if (i == order - 1) {
                String[] oldData = reader.nextLine().split("/");
                oldData[1] = "True";
                String parsedData = String.join("/", oldData);
                newData = newData + parsedData + "\n";
            } else {
                newData = newData + reader.nextLine() + "\n";
            }
        }

        writeData(newData, false);
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
}