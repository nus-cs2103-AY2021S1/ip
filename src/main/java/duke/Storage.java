package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import duke.tasks.ToDo;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.Ui;


public class Storage {
    private final String DATAFILEDIRECTORY = System.getProperty("user.dir") + (System.getProperty("user.dir").endsWith("text-ui-test") ? "/../data/" : "/data/");
    private File storageFile;

    public Storage(String fileName) {
        this.storageFile = new File(DATAFILEDIRECTORY + fileName);
    }

    public void loadData(TaskList currTaskList) {
        this.checkHistory();
        BufferedReader rb = null;
        try {
            rb = new BufferedReader(new FileReader(this.storageFile));
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found");
        }
        String newLine = "";
        try {
            newLine = rb.readLine();
            while (newLine != null) {
                String[] taskInput = newLine.split("\\|");
                if (taskInput[0].charAt(0) == 'T') {
                    ToDo pastToDo = new ToDo(taskInput[2].trim());
                    if (Integer.parseInt(taskInput[1].trim()) == 1) {
                        pastToDo.markDone();
                    }
                    currTaskList.add(pastToDo);
                    newLine = rb.readLine();
                } else if (newLine.charAt(0) == 'E') {
                    Event pastEvent = new Event(taskInput[2].trim(), LocalDate.parse(taskInput[3].trim()));
                    if (Integer.parseInt(taskInput[1].trim()) == 1) {
                        pastEvent.markDone();
                    }
                    currTaskList.add(pastEvent);
                    newLine = rb.readLine();
                } else if (newLine.charAt(0) == 'D') {
                    Deadline pastDeadline = new Deadline(taskInput[2].trim(), LocalDate.parse(taskInput[3].trim()));
                    if (Integer.parseInt(taskInput[1].trim()) == 1) {
                        pastDeadline.markDone();
                    }
                    currTaskList.add(pastDeadline);
                    newLine = rb.readLine();
                }
            }
            rb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkHistory() {
        try {
            FileReader readFile = new FileReader(DATAFILEDIRECTORY);
        } catch (FileNotFoundException e) {
            File newData = new File(DATAFILEDIRECTORY);
            if(!newData.exists()) {
                newData.mkdirs();
                Ui.addDirectory();
            }
        }

        try{
            if(this.storageFile.createNewFile()) {
                Ui.addDataFile();
            }
        } catch (IOException e) {
            System.out.println("Unable to create file");
        }
    }

    public void addTask(Task newTask) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(this.storageFile,true);
            if (newTask instanceof ToDo) {
                fw.write(((ToDo)newTask).dataStorage() + "\n");
                fw.close();
            } else if (newTask instanceof Deadline) {
                fw.write(((Deadline)newTask).dataStorage() + "\n");
                fw.close();
            } else if (newTask instanceof Event) {
                fw.write(((Event)newTask).dataStorage() + "\n");
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editTask(Task editedTask, int taskIndex, TaskList currentList) {
        File toBeDeleted = new File (DATAFILEDIRECTORY + "dataList1.txt");
        BufferedReader readerBuffer = null;
        try {
            readerBuffer = new BufferedReader(new FileReader(this.storageFile));
            BufferedWriter writerBuffer = new BufferedWriter(new FileWriter(toBeDeleted));
            String readingLine = readerBuffer.readLine();
            String lineToEdit = "";
            String lineToChangeTo = "";

            if (editedTask instanceof Deadline) {
                lineToEdit = ((Deadline) editedTask).dataStorage();
                currentList.get(taskIndex).markDone();
                lineToChangeTo = ((Deadline) editedTask).dataStorage();
            } else if (editedTask instanceof Event) {
                lineToEdit = ((Event) editedTask).dataStorage();
                currentList.get(taskIndex).markDone();
                lineToChangeTo = ((Event) editedTask).dataStorage();
            } else {
                lineToEdit = ((ToDo) editedTask).dataStorage();
                currentList.get(taskIndex).markDone();
                lineToChangeTo = ((ToDo) editedTask).dataStorage();
            }

            while (readingLine != null) {
                if (readingLine.equals(lineToEdit)) {
                    writerBuffer.write(lineToChangeTo + "\n");
                    readingLine = readerBuffer.readLine();
                    continue;
                }
                writerBuffer.write(readingLine + "\n");
                readingLine = readerBuffer.readLine();
            }

            writerBuffer.close();
            readerBuffer.close();

            if (this.storageFile.delete()) {
                // Rename the output file to the input file
                if (!toBeDeleted.renameTo(this.storageFile)) {
                    throw new IOException("Could not rename to update data file");
                }
            } else {
                throw new IOException("Could not delete old data file");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteTask(Task removedTask) {
        try{
            File removed = new File (DATAFILEDIRECTORY + "dataList1.txt");
            BufferedReader reader = new BufferedReader(new FileReader(this.storageFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(removed));
            String currentLine = reader.readLine();
            String lineToRemove = "";

            if (removedTask instanceof Deadline) {
                lineToRemove = ((Deadline) removedTask).dataStorage();
            } else if (removedTask instanceof Event) {
                lineToRemove = ((Event) removedTask).dataStorage();
            } else {
                lineToRemove = ((ToDo) removedTask).dataStorage();
            }

            while (currentLine != null) {
                if (currentLine.equals(lineToRemove)) {
                    currentLine = reader.readLine();
                    continue;
                }
                writer.write(currentLine + "\n");
                currentLine = reader.readLine();
            }

            writer.close();
            reader.close();

            if (this.storageFile.delete()) {
                // Rename the output file to the input file
                if (!removed.renameTo(this.storageFile)) {
                    throw new IOException("Could not rename to update data file");
                }
            } else {
                throw new IOException("Could not delete old data file");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
