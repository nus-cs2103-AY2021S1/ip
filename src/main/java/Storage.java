import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class Storage {

    TaskList tasks;
    File file;

    public Storage(File file, TaskList tasks) {
        this.file = file;
        this.tasks = tasks;
    }

    public Storage() {
        this.file = new File("data.txt");
    }

    /**
     * Reads data from a pre-existing .txt file
     * @throws FileNotFoundException If required file is not found
     */
    public void readData() throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String[] line = reader.nextLine().split(" # ");
            String type = line[0];
            boolean done = line[1].equals("1");
            String name = line[2];
            if (type.equals("T")) {
                tasks.addItem(new Todo(name, done));
            } else {
                String time = line[3];
                LocalDateTime ldt = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                if (type.equals("D")) {
                    tasks.addItem(new Deadline(name, ldt, done));
                } else {
                    tasks.addItem(new Event(name, ldt, done));
                }
            }
        }
    }

    /**
     * Writes data into a file
     * @param path Path to the file
     * @param text Text to be written into the file
     * @throws InvalidFileException File was not found at the end of the input path
     */
    public void writeToFile(String path, String text) throws InvalidFileException {
        try {
            FileWriter fw = new FileWriter(path);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            throw new InvalidFileException("File was not found");
        }
    }
}
