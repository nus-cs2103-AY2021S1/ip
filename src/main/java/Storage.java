import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Storage {
    protected String path;
    protected boolean isFileChanged = false;

    public Storage(String path) {
        this.path = path;
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            ArrayList<Task> outputTaskArray = new ArrayList<>();
            FileReader fileToRead = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileToRead);
            String inputData = bufferedReader.readLine();

            while (inputData != null) {
                String taskDate;
                String taskTime;
                String[] dateTimeArray;
                String[] userInputArray = inputData.split("\\|");
                int boolDone = Integer.parseInt(userInputArray[1]);
                String descriptions = userInputArray[2];

                switch (userInputArray[0]) {
                case "T":
                    Todo newTodo = new Todo(descriptions, boolDone);
                    outputTaskArray.add(newTodo);
                    break;

                case "D":
                    dateTimeArray = userInputArray[3].split(" ");
                    taskDate = dateTimeArray[0];
                    taskTime = dateTimeArray[1];
                    Deadline newDeadline = new Deadline(boolDone, descriptions, taskDate, taskTime);
                    outputTaskArray.add(newDeadline);
                    break;

                case "E":
                    dateTimeArray = userInputArray[3].split(" ");
                    taskDate = dateTimeArray[0];
                    taskTime = dateTimeArray[1];
                    Event newEvent = new Event(boolDone, descriptions, taskDate, taskTime);
                    outputTaskArray.add(newEvent);
                    break;

                default:
                    break;
                }
                inputData = bufferedReader.readLine();
            }
            bufferedReader.close();
            return outputTaskArray;
        } catch (Exception error) {
            throw new DukeException("File cannot be loaded from the specified file path. Please try again!");
        }

    }

    public void saveToDisk(TaskList arrayOfTasks) throws DukeException {
        try {
            String outputLine = "";
            FileWriter writer = new FileWriter(path);
            int sizeOfArray = arrayOfTasks.taskArraySize();
            int index = 0;
            int isDone;

            while (index < sizeOfArray) {
                Task task = arrayOfTasks.get(index);
    
                if (task.isDone) {
                    isDone = 1;
                } else {
                    isDone = 0;
                }
            
                if ((task.getClass().equals(Todo.class))) {
                    outputLine = "T" + "|" + isDone + "|" + task.description;
                } else if ((task.getClass().equals(Deadline.class))) {
                    Deadline deadlineTask = (Deadline) task;
                    outputLine = "D" + "|" + isDone + "|" + deadlineTask.description + "|"
                            + deadlineTask.deadlineDate.date + " " + deadlineTask.deadlineTime.timing;
                } else if ((task.getClass().equals(Event.class))) {
                    Event eventTask = (Event) task;
                    outputLine = "E" + "|" + isDone + "|" + eventTask.description + "|"
                            + eventTask.eventDate.date + " " + eventTask.eventTime.timing;
                } else {
                    // Do nothing
                }
                String separator = System.lineSeparator();
                writer.write(outputLine + separator);
                index++;
            }
            writer.close();
        } catch (Exception error) {
            throw new DukeException("Error writing to specified file path. Please try again!");
        }
    }

    public void changeFile() {
        isFileChanged = true;
    }

    public boolean isStorageChanged() {
        return isFileChanged;
    }
}