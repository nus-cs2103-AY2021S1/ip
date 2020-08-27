package main.java;

import main.java.Task.Deadline;
import main.java.Task.Event;
import main.java.Task.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

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
            System.out.println("Something went wrong:  " + e.getMessage());
        }
    }

    private void retrieveStorage() {
// LIMITATION CANNOT HAVE COMMA IN DESCRIPTION OF TASKS
        try {
            FileReader fileReader = new FileReader(this.savedCopy);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String eachLine = bufferedReader.readLine();
            while (eachLine != null) {
                String[] simplerData = eachLine.split(",");
                Task toAdd;
                if (simplerData.length == 3) {
                    String description = simplerData[2].strip();
                    boolean isDone = !simplerData[1].strip().equals("N");
                    toAdd = new Task(description, isDone);
                    this.savedTasks.add(toAdd);
                } else {
//                    assert simplerData.length == 4;
                    if (simplerData[0].equals("D")) {
                        String description = simplerData[2].strip();
                        boolean isDone = !simplerData[1].strip().equals("N");
                        String date = simplerData[3].strip();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
                        LocalDate actualDate = LocalDate.parse(date, formatter);
                        toAdd = new Deadline(description, actualDate, isDone);
                        this.savedTasks.add(toAdd);

                    } else if (simplerData[0].equals("E")) {
                        String description = simplerData[2].strip();
                        boolean isDone = !simplerData[1].strip().equals("N");
                        String date = simplerData[3].strip();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
                        LocalDate actualDate = LocalDate.parse(date, formatter);
                        toAdd = new Event(description, actualDate, isDone);
                        this.savedTasks.add(toAdd);
                    }
                }
                eachLine = bufferedReader.readLine();
            }

        } catch (IOException e) {
            System.out.println("Something went wrong:  " + e.getMessage());
        }
    }

    public List<Task> getSavedTasks() {
        return this.savedTasks;
    }

    public void saveIntoHarddisk() {
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
            System.out.println("Something went wrong:  " + e.getMessage());
        }
    }


}
