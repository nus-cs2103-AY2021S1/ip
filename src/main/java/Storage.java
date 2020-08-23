import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void createFile() {
        try {
            File saveFile = new File(filePath);
            saveFile.getParentFile().mkdirs();
            saveFile.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Task> load() throws FileNotFoundException, DukeException {
        ArrayList<Task> lib = new ArrayList<>();
        File file = new File(filePath);
        Scanner reader = new Scanner(file);

        while (reader.hasNext()) {
            String next = reader.nextLine();

            String[] modNext = next.split(">");
            if (modNext[0].trim().equals("D")) {

                String[] time = modNext[3].trim().split(" ");
                Deadline deadline = new Deadline(modNext[2].trim(),
                        time[1], modNext[1].trim().equals("✓"));
                lib.add(deadline);
            }
            if (modNext[0].trim().equals("E")) {

                String[] time = modNext[3].trim().split(" ");
                Event event = new Event(modNext[2].trim(),
                        time[1], modNext[1].trim().equals("✓"));
                lib.add(event);
            }
            if (modNext[0].trim().equals("T")) {

                ToDo toDo = new ToDo(modNext[2].trim(),
                        modNext[1].trim().equals("✓")
                );
                lib.add(toDo);
            }

        }
        return lib;
    }

    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(textToAdd);
        writer.close();
    }

    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);
        writer.write(textToAppend);
        writer.close();
    }

    public void save(ArrayList<Task> lib) throws IOException {
        for (int i = 0; i < lib.size(); i++) {
            String curr = lib.get(i).saveData();

            if (i == 0) {
                writeToFile("data/duke.txt", curr + "\n");
            } else if (i == (lib.size() - 1)) {
                appendToFile("data/duke.txt", curr);
            } else {
                appendToFile("data/duke.txt", curr + "\n");
            }
        }
    }

}
