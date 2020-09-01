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

    public void addData(Task task) throws IOException {
        writeData(task.getParsedData() + "\n", true);
    }

    public void markDoneData(int order, String parsedData) throws IOException {
        String newData = "";
        Scanner reader = new Scanner(f);

        for (int i = 0; reader.hasNextLine(); i++) {
            if (i == order - 1) {
                newData = newData + parsedData + "\n";
                reader.nextLine();
            } else {
                newData = newData + reader.nextLine() + "\n";
            }
        }

        writeData(newData, false);
    }

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

    public void writeData(String text, Boolean appendMode) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(this.dir + "/" + this.fileName, appendMode));
        bw.write(text);
        bw.close();
    }
}