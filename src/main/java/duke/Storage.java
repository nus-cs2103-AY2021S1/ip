package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with the reading from and writing to a file.
 */
public class Storage {
    private File file;

    private enum TaskType {
        T {
            @Override
            Todo create(String[] line) {
                return new Todo(line[2], Boolean.parseBoolean(line[1]));
            }

            @Override
            void check(String[] line) throws DukeException {
                if (line.length != 3) {
                    throw new DukeException("The file format is wrong :((.");
                }
            }
        },
        D {
            @Override
            Deadline create(String[] line) {
                return new Deadline(line[2], LocalDate.parse(line[3]),
                        Boolean.parseBoolean(line[1]));
            }
        },
        E {
            @Override
            Event create(String[] line) {
                return new Event(line[2], LocalDate.parse(line[3]),
                        Boolean.parseBoolean(line[1]));
            }
        };

        abstract Task create(String[] line);

        void check(String[] line) throws DukeException {
            if (line.length != 4) {
                throw new DukeException("The file format is wrong :((.");
            }
        }
    }

    /**
     * Storage constructor.
     *
     * @param filePath path of the the storage file.
     */
    public Storage(String filePath) throws DukeException {
        file = new File("./data/dukeFile.txt");
        file.getParentFile().mkdirs();

        try {
            file.createNewFile();
        } catch (Exception e) {
            throw new DukeException("Can't create file .__.");
        }
    }

    /**
     * Reads data from the file and converts it to a list of tasks.
     *
     * @return a list of tasks.
     * @throws DukeException can't read or convert the file to a list of tasks.
     */
    public ArrayList<Task> readFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner fileIn = new Scanner(file)) {

            while (fileIn.hasNextLine()) {
                String[] line = fileIn.nextLine().split(" \\| ");
                TaskType taskType;
                taskType = TaskType.valueOf(line[0]);
                taskType.check(line);
                tasks.add(taskType.create(line));
                assert tasks.size() != 0;
            }

            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("The file is not found TwT");
        } catch (Exception e) {
            throw new DukeException("The file format is wrong :((.");
        }
    }

    /**
     * Writes a list of tasks to the file.
     *
     * @param tasks the list of tasks to be written to the file.
     * @throws DukeException failed to write the list to the file.
     */
    public void writeToFile(TaskList tasks) throws DukeException {
        try {
            FileWriter dukeFileWriter = new FileWriter(file);
            dukeFileWriter.write(tasks.toString());
            dukeFileWriter.close();
        } catch (Exception e) {
            throw new DukeException("Can't write to file :\"");
        }
    }
}
