import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *  Deals with saving and loading tasks from input path.
 */
public class Storage {
    protected String path;
    protected boolean isFileChanged = false;

    /**
     * Instantiates Storage object.
     * @param path Filepath to load and read input file.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Parses strings from input text file.
     * @return ArrayList that contains the parsed strings from input text file.
     * @throws DukeException if there is an issue.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            ArrayList<Task> outputTaskArray = new ArrayList<Task>();
            FileReader fileToRead = new FileReader(path);
            assert fileToRead != null : "File to be read should not be empty!";
            BufferedReader bufferedReader = new BufferedReader(fileToRead);
            String inputData = bufferedReader.readLine();

            while (inputData != null) {
                String taskType = getTaskType(inputData);

                switch (taskType) {
                case "T":
                    Todo newTodo = parseToDo(inputData);
                    outputTaskArray.add(newTodo);
                    break;

                case "D":
                    Deadline newDeadline = parseDeadline(inputData);
                    outputTaskArray.add(newDeadline);
                    break;

                case "E":
                    Event newEvent = parseEvent(inputData);
                    outputTaskArray.add(newEvent);
                    break;

                default:
                    break;
                }
                inputData = bufferedReader.readLine();
            }
            bufferedReader.close();
            return outputTaskArray;
        } catch (IOException error) {
            throw new DukeException("File cannot be loaded from the specified file path. Please try again!");
        }
    }

    /**
     * Saves parsed strings to output text file.
     * @param arrayOfTasks Array of tasks that contains all tasks read from input text file.
     * @throws DukeException if there is an issue.
     */
    public void saveToDisk(TaskList arrayOfTasks) throws DukeException {
        try {
            final int isDone = 1;
            final int notDone = 0;
            String outputLine = "";
            FileWriter writer = new FileWriter(path);
            int sizeOfArray = arrayOfTasks.taskArraySize();
            int index = 0;
            int check;

            while (index < sizeOfArray) {
                Task task = arrayOfTasks.get(index);

                if (task.isDone) {
                    check = isDone;
                } else {
                    check = notDone;
                }

                if ((task.getClass().equals(Todo.class))) {
                    outputLine = "T" + "|" + check + "|" + task.description;
                } else if ((task.getClass().equals(Deadline.class))) {
                    Deadline deadlineTask = (Deadline) task;
                    outputLine = "D" + "|" + check + "|" + deadlineTask.description + "|"
                            + deadlineTask.deadlineDate.date + " " + deadlineTask.deadlineTime.timing;
                } else if ((task.getClass().equals(Event.class))) {
                    Event eventTask = (Event) task;
                    outputLine = "E" + "|" + check + "|" + eventTask.description + "|"
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
            throw new DukeException("Error writing to specified file path. Please try again.");
        }
    }

    /**
     * Asserts that there have been changes made to the file.
     */
    public void changeFile() {
        isFileChanged = true;
    }

    /**
     * Checks if there are any changes made to the storage files.
     * @return True if files have been changed, False if no changes have been made.
     */
    public boolean isStorageChanged() {
        return isFileChanged;
    }

    /**
     * Parses deadline command.
     * @param inputData String input by user on command line.
     * @return Deadline object.
     */
    private Deadline parseDeadline(String inputData) throws DukeException {
        String[] userInputArray = inputData.split("\\|");
        String[] dateTimeTokens = userInputArray[3].split(" ");
        String taskDate = dateTimeTokens[0];
        String taskTime = dateTimeTokens[1];
        String taskDesc = userInputArray[2];
        int boolDone = Integer.valueOf(userInputArray[1]);
        return new Deadline(boolDone, taskDesc, taskDate, taskTime);
    }

    /**
     * Parses Todo command.
     * @param inputData String input by user on command line.
     * @return Todo object.
     */
    private Todo parseToDo(String inputData) {
        String[] userInputArray = inputData.split("\\|");
        int boolDone = Integer.valueOf(userInputArray[1]);
        String taskDesc = userInputArray[2];
        return new Todo(taskDesc, boolDone);

    }

    /**
     * Parses Event command.
     * @param inputData String input by user on command line.
     * @return Event object.
     */
    private Event parseEvent(String inputData) throws DukeException {
        String[] userInputArray = inputData.split("\\|");
        String[] dateTimeTokens = userInputArray[3].split(" ");
        String taskDate = dateTimeTokens[0];
        String taskTime = dateTimeTokens[1];
        String taskDesc = userInputArray[2];
        int boolDone = Integer.valueOf(userInputArray[1]);
        return new Event(boolDone, taskDesc, taskDate, taskTime);
    }

    /**
     * Obtains type of task/command.
     * @param inputData String input by user on command line.
     * @return First alphabet of task in string.
     */
    private String getTaskType(String inputData) {
        String[] userInputArray = inputData.split("\\|");
        return userInputArray[0];
    }
}
