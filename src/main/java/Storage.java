import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    private enum TaskType {
        T {
            Todo create(String[] line) {
                return new Todo(line[2], Boolean.parseBoolean(line[1]));
            }

            void check(String[] line) throws DukeException {
                if (line.length != 3)
                    throw new DukeException("The file format is wrong :((.");
            }
        },
        D {
            Deadline create(String[] line) {
                try {
                    return new Deadline(line[2], LocalDate.parse(line[3]),
                            Boolean.parseBoolean(line[1]));
                } catch (Exception e) {
                    throw e;
                }
            }
        },
        E {
            Event create(String[] line) {
                try {
                    return new Event(line[2], LocalDate.parse(line[3]),
                            Boolean.parseBoolean(line[1]));
                } catch (Exception e) {
                    throw e;
                }
            }
        };

        abstract Task create(String[] line);

        void check(String[] line) throws DukeException {
            if (line.length != 4)
                throw new DukeException("The file format is wrong :((.");
        }
    };

    public Storage(String filePath) throws DukeException {
        this.file = new File("./data/dukeFile.txt");
        file.getParentFile().mkdirs();

        try {
            file.createNewFile();
        } catch (Exception e) {
            throw new DukeException("Can't create file .__.");
        }
    }

    public ArrayList<Task> readFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner fileIn = null;
        try {
            fileIn = new Scanner(file);

            while (fileIn.hasNextLine()) {
                String[] line = fileIn.nextLine().split(" \\| ");

                TaskType taskType;
                try {
                    taskType = TaskType.valueOf(line[0]);
                    taskType.check(line);
                    tasks.add(taskType.create(line));
                } catch (Exception e) {
                    throw new DukeException("The file format is wrong :((.");
                }
            }

            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("The file is not found TwT");
        } catch (Exception e) {
            throw e;
        } finally {
            if (fileIn != null)
                fileIn.close();
        }
    }

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
