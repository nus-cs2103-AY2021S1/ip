import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filepath;
    private List<Task> tasks;

    private enum TaskSymbols {
        SYMBOL_T, SYMBOL_E, SYMBOL_D
    }

    public Storage(String filepath) {
        this.filepath = filepath;
        this.tasks = new ArrayList<>();
    }

    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filepath);
            for (Task t : tasks.getTasks()) {
                fw.write(t.format() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(">> Oh no! Your tasks could not be saved!");
        }
    }

    public List<Task> load() throws DukeException {
        String directoryErrorMsg = ">> Something went wrong creating the data directory!";
        try {
            String[] directories = filepath.split("/");
            StringBuilder path = new StringBuilder();
            for (int i = 0; i < directories.length - 1; i++) {
                path.append(directories[i]).append("/");
                File directory = new File(path.toString());
                if (!directory.exists()) {
                    boolean success = directory.mkdir();
                    if (!success) {
                        throw new DukeException(directoryErrorMsg);
                    }
                }
            }
            File file = new File(filepath);
            if (file.exists()) {
                Scanner fr = new Scanner(file);
                while (fr.hasNextLine()) {
                    String ln = fr.nextLine();
                    String[] taskInfo = ln.split(Task.ESCAPED_SAVE_DELIMITER);
                    TaskSymbols type = TaskSymbols.valueOf("SYMBOL_" + taskInfo[0]);
                    switch(type) {
                        case SYMBOL_T:
                            tasks.add(new Todo(taskInfo[1], Boolean.parseBoolean(taskInfo[2])));
                            break;
                        case SYMBOL_E:
                            tasks.add(new Event(taskInfo[1], Boolean.parseBoolean(taskInfo[2]),
                                    LocalDate.parse(taskInfo[3])));
                            break;
                        case SYMBOL_D:
                            tasks.add(new Deadline(taskInfo[1], Boolean.parseBoolean(taskInfo[2]),
                                    LocalDate.parse(taskInfo[3])));
                            break;
                    }
                }
            } else {
                boolean success = file.createNewFile();
                if (!success) {
                    throw new DukeException(directoryErrorMsg);
                }
            }
            return this.tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException(">> Oh no! I can't find your file!");
        } catch (IOException e) {
            throw new DukeException(">> Oh no! The file couldn't be created for some reason!");
        } catch (IllegalArgumentException e) {
            throw new DukeException(">> Oh no! File format seems to be incorrect!");
        }
    }
}
