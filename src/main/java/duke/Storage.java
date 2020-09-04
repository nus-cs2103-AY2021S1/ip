package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;

class Storage {
    private File savedCopy;
    private List<Task> savedTasks;

    Storage() {
        try {
            this.savedTasks = new ArrayList<>();
            this.savedCopy = new File("./data/save.txt");
            if (!this.savedCopy.exists()) {
                File directory = new File("./data");
                directory.mkdir();
                this.savedCopy.createNewFile();
            } else {
                this.retrieveStorage();
            }
        } catch (IOException e) {
            Ui.printErrorMessage("Something went wrong:  " + e.getMessage());
        }
    }

    private void retrieveStorage() {
        // LIMITATION: CANNOT HAVE COMMA IN DESCRIPTION OF TASKS
        try {
            FileReader fileReader = new FileReader(this.savedCopy);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String eachLine = bufferedReader.readLine();
            while (eachLine != null) {
                String[] simplerData = eachLine.split(",");
                Task toAdd;
                if (simplerData.length == 3) {
                    assert simplerData[0].equals("T");
                    toAdd = this.readTask(simplerData);
                } else {
                     assert simplerData.length == 4;
                    toAdd = this.readDeadlineOrEvent(simplerData);
                }
                this.savedTasks.add(toAdd);
                eachLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            Ui.printErrorMessage("Something went wrong:  " + e.getMessage());
        }
    }

    private Task readTask(String[] details) {
        String description = details[2].strip();
        boolean isDone = details[1].strip().equals("D");
        Task toAdd = new Task(description, isDone);
        return toAdd;
    }

    private Task readDeadlineOrEvent(String[] details) {
        String description = details[2].strip();
        boolean isDone = details[1].strip().equals("D");
        String date = details[3].strip();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate actualDate = LocalDate.parse(date, formatter);
        Task toAdd;
        if (details[0].equals("D")) {
            toAdd = new Deadline(description, actualDate, isDone);
        } else {
            assert details[0].equals("E");
            toAdd = new Event(description, actualDate, isDone);
        }
        return toAdd;
    }

    public List<Task> getSavedTasks() {
        return this.savedTasks;
    }

    public void saveIntoHardDisk() {
        try {
            FileWriter writer = new FileWriter("./data/save.txt", false);
            writer.write("");
            writer.close();
            writer = new FileWriter("./data/save.txt", true);
            for (Task task : this.savedTasks) {
                writer.write(task.getStoreRepresentation() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            Ui.printErrorMessage("Something went wrong:  " + e.getMessage());
        }
    }
}
