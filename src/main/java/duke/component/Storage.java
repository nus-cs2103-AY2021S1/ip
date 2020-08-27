package duke.component;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;
    private List<String> listOfTaskStrings;

    /**
     * constructor for Storage instance.
     * @param filePath specifies where to search for the txt file to take in tasks from storage.
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
        try {

            if (Files.exists(this.filePath)) {
                this.listOfTaskStrings = Files.readAllLines(this.filePath);
            } else {
                System.out.println("File not found at that location, new file of tasks has been created");
                // create folder if needed
                Path folderPath = filePath.getParent();
                Files.createDirectories(folderPath);
                Files.createFile(this.filePath);
                this.listOfTaskStrings = new ArrayList<>();
            }

        } catch (IOException e) {
            System.out.println("Error when loading with file: Saveload class");
            this.listOfTaskStrings = new ArrayList<>();
        }
    }

    /**
     * retrieves the tasks from storage and returns a list of it formatted as Tasks.
     * @return list of tasks from storage.
     * @throws DukeException exception thrown when exception caught while running.
     */
    public ArrayList<Task> getListOfTasks() throws DukeException {
        ArrayList<Task> listOfTasks = new ArrayList<>();

        try {
            for (String stringTask : this.listOfTaskStrings) {
                char typeOfTask = stringTask.charAt(0);
                char doneChar = stringTask.charAt(4);
                boolean isDone = Task.checkIfDone(doneChar);
                Task tempTask;

                switch (typeOfTask) {
                case 'T':
                    tempTask = new Todo(stringTask.substring(8));
                    if (isDone) {
                        tempTask.markDone();
                    }
                    listOfTasks.add(tempTask);
                    break;
                case 'E':
                    // Splitting string
                    String substringE = stringTask.substring(8);
                    String[] strArrE = substringE.split("\\|");
                    String descriptionE = strArrE[0];
                    String dateE = convertBackDateTime(strArrE[1]);
                    tempTask = new Event(descriptionE, dateE);

                    if (isDone) {
                        tempTask.markDone();
                    }
                    listOfTasks.add(tempTask);
                    break;

                case 'D':
                    String substringD = stringTask.substring(8);
                    String[] strArrD = substringD.split("\\|");
                    String descriptionD = strArrD[0];
                    String dateD = convertBackDateTime(strArrD[1]);
                    tempTask = new Deadline(descriptionD, dateD);

                    if (isDone) {
                        tempTask.markDone();
                    }
                    listOfTasks.add(tempTask);
                    break;

                default:
                    throw new DukeException("duke.task.Task was not recognised: " + stringTask);

                }
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
//            System.out.println(e.getMe
        } catch (NullPointerException | StringIndexOutOfBoundsException n) {
            throw new DukeException("No data found in file");
        }
        return listOfTasks;
    }

    /**
     * converts datetime formatted string back to resemble input date and time.
     * @param dateTime string of formatted datetime.
     * @return string of date time resembling input.
     */
    private String convertBackDateTime(String dateTime) {
        String dateUnconverted = dateTime.substring(1, 12);
        String timeConverted = dateTime.substring(13, 18);

        // converting date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate date = LocalDate.parse(dateUnconverted, dtf);

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String dateConverted = date.format(dtf2);


        return " " + dateConverted + " " + timeConverted;
    }

    /**
     * updates storage with updated information.
     */
    public void saveFile() {
        if (!listOfTaskStrings.isEmpty()) {
            try {
                FileWriter fileWriter = new FileWriter(String.valueOf(filePath));
                for (String task : listOfTaskStrings) {
                    fileWriter.write(task + "\n");
                }
                fileWriter.close();

            } catch (IOException e) {
                System.out.println("There was an error when writing the tasks to file: " + e.getMessage());
            }
        }
    }

    /**
     * adds task to list and updates storage.
     * @param task task to add.
     */
    public void addTask(Task task) {
        String taskString = task.stringToSave();
        this.listOfTaskStrings.add(taskString);
        this.saveFile();
    }

    /**
     * deletes task from list and updates storage.
     * @param index index of task to delete.
     */
    public void deleteTask(int index) {
        this.listOfTaskStrings.remove(index);
        this.saveFile();
    }

    /**
     * modifies task and updates storage.
     * @param task task to modify.
     * @param index index of task to modify.
     */
    public void modifyTask(Task task, int index) {
        String taskString = task.stringToSave();
        this.listOfTaskStrings.set(index, taskString);
        this.saveFile();
    }

}
