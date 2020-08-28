import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Load extends Command {

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

    public String load() throws FileNotFoundException, DukeException {
        boolean success = readFile();
        if (success) {
            return "Successfully loaded tasks:\n" + DataStorageInterface.listOfTasks();
        } else {
            return "There was some error. Could not load tasks successfully.";
        }
    }

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
        if (taskType.equals("[T]")) {
            String taskName = concatenateStrArr(splitData, 3, splitData.length);
            Task task = new ToDo(taskName);
            if (isDone) {
                task.markDone();
            }
            return task;
        } else {
            int index = 3;
            while (!splitData[index].startsWith("(")) {
                index++;
            }
            String taskName = concatenateStrArr(splitData,3,index);
            String preposition = getPreposition(splitData[index]);
            index++;
            String dateRep = splitData[index];
            LocalDate date = LocalDate.parse(dateRep);
            index++;
            String timeRep = splitData[index].replace(")","");
            LocalTime time = LocalTime.parse(timeRep);
            if (taskType.equals("[D]")) {
                Task task = new Deadline(taskName,preposition,date, time);
                if (isDone) {
                    task.markDone();
                }
                return task;
            } else if (taskType.equals("[E]")) {
                Task task = new Event(taskName, preposition,date, time);
                if (isDone) {
                    task.markDone();
                }
                return task;
            } else {
                throw new WrongFileFormatException(filePath);
            }
        }
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
}
