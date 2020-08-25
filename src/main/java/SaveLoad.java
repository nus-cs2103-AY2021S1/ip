import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SaveLoad {
    private final Path filePath;
    private List<String> listOfTaskStrings;

    public SaveLoad(Path filePath) {
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
                    String dateE = strArrE[1];
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
                    String dateD = strArrD[1];
                    tempTask = new Deadline(descriptionD, dateD);

                    if (isDone) {
                        tempTask.markDone();
                    }
                    listOfTasks.add(tempTask);
                    break;

                    default:
                        throw new DukeException("Task was not recognised: " + stringTask);

                }
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
//            System.out.println(e.getMe
        } catch (NullPointerException n) {
            throw new DukeException("No data found in file");
        }
        return listOfTasks;
    }


    // Overwrites file at that location with updated information
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

    // saved task to list of tasks and overwrites file on HARD DRIVE
    public void addTask(Task task) {
        String taskString = task.stringToSave();
        this.listOfTaskStrings.add(taskString);
        this.saveFile();
    }

    // deletes task to list of tasks and overwrites file on HARD DRIVE
    public void deleteTask(int index){
        this.listOfTaskStrings.remove(index);
        this.saveFile();
    }

    // changes task in list of tasks and overwrites file on HARD DRIVE
    public void modifyTask(Task task, int index) {
        String taskString = task.stringToSave();
        this.listOfTaskStrings.set(index, taskString);
        this.saveFile();
    }

}
