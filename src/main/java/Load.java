import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Loads a list of tasks from a persistent file stored on the device.
 */
public class Load extends Command {

    /** Query of User stored for Reference */
    String filePath;

    Load(String[] query) throws WrongUsageException {
        this.name = "load";
        this.usage = "load [FILE_PATH]";
        this.description = "Used to load files stored in the task format";
        if (query.length != 2) {
            throw new WrongUsageException(this.name, this.usage);
        }
        this.filePath = query[1];
    }

    /**
     * Loads file and Returns success or error message accordingly.
     *
     * @return success or error message accordingly.
     * @throws FileNotFoundException If File Cannot be found on device with the given file path.
     * @throws DukeException If Task Data cannot be read properly.
     */
    public String load() throws FileNotFoundException, DukeException {
        boolean success = readFile();
        if (success) {
            return "Successfully loaded tasks, obviously:\n" + DataStorageInterface.printListOfTasks();
        } else {
            return "There was some error. Could not load tasks successfully. " +
                    "Your idiot father must have *BUURRRRRPS* messed up the files somehow";
        }
    }

    /**
     * Reads File Data and adds Task Data into Task List.
     *
     * @return Success boolean.
     * @throws FileNotFoundException If File Cannot be found on device with the given file path.
     * @throws DukeException If Task Data cannot be read properly.
     */
    private boolean readFile() throws FileNotFoundException,DukeException {
        File file = new File(filePath);
        ArrayList<Task> newTasks = new ArrayList<>();
        System.out.println("Loaded file and created the array successfully");
        if (!file.exists()) {
            System.out.println("File does not exist");
            return false;
        } else {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                Task task = parseData(data);
                newTasks.add(task);
            }
            DataStorageInterface.removeAllTasks();
            DataStorageInterface.loadNewTasks(newTasks);
            return true;
        }
    }

    private Task parseData(String data) throws DukeException {
        ///Following the following format
        ///1. [T] [X]  something
        //2. [D] [X]  eat cereal (by:  12/05/2020 14:30)
        //3. [E] [X]  lunch (at:  13/05/2020 13:30)
        String[] splitData = data.split("\\s+");
        String taskType = splitData[1];
        boolean isDone = splitData[2].equals("[DONE]");
        Task task;
        if (taskType.equals("[T]")) {
            String taskName = concatenateStrArr(splitData, 3, splitData.length);
            task = new ToDo(taskName);
        } else {
            int index = 3;
            while (!splitData[index].startsWith("(")) {
                index++;
            }
            String taskName = concatenateStrArr(splitData,3,index);
            String preposition = getPreposition(splitData[index]);
            index++;
            LocalDate date = getDate(splitData[index]);
            index++;
            LocalTime time = getTime(splitData[index]);
            if (taskType.equals("[D]")) {
                task = new Deadline(taskName,preposition, date, time);
            } else if (taskType.equals("[E]")) {
                task = new Event(taskName, preposition, date, time);
            } else {
                throw new WrongFileFormatException(filePath);
            }
        }
        if (isDone) {
            task.markDone();
        }
        return task;
    }

    private String concatenateStrArr(String[] strArr, int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            return strArr[startIndex];
        } else {
            StringBuilder acc = new StringBuilder();
            for (int i = startIndex; i < endIndex; i++) {
                acc.append(" ").append(strArr[i]);
            }
            return acc.toString();
        }
    }

    private String getPreposition(String dirtyPrep) {
        return dirtyPrep.replace("(","").replace(":","");
    }

    private LocalDate getDate(String date) {
        String dateRep = date;
        return LocalDate.parse(dateRep);
    }

    private LocalTime getTime(String time) {
        String timeRep = time.replace(")","");
        return LocalTime.parse(timeRep);
    }

}
