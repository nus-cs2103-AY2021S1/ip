package duke.storage;

import duke.DukeAction;
import duke.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    protected String filepath;
    protected String dirpath;

    public Storage(String filepath) {
        this.filepath = filepath;
        this.dirpath = filepath.substring(0, 6);
    }

    /**
     * Returns the list of tasks stored in the hard disk.
     *
     * @return List list of Task stored in hard disk.
     * @throws DukeException If could not load task information from hard disk.
     */
    public List<Task> loadData() throws DukeException {
        //read Duke's data file and load tasks into tasks list
        List<Task> tasks = new ArrayList<>();
        try {
            File dir = new File(this.dirpath);
            File taskFile = new File(this.filepath);
            if(dir.mkdir()) {
                System.out.println("Welcome. Dino has created a new directory " +
                        "to store your data.");
                if(taskFile.createNewFile()) {
                    System.out.println("Dino has successfully created a new file to store your task list.");
                } else {
                    System.out.println("Dino could not create a new file to store your task list.");
                }
            } else if(taskFile.createNewFile()) {
                System.out.println("Welcome. Dino has successfully created " +
                        "a new file to store your task list.");
            } else {
                Scanner scan = new Scanner(taskFile);
                while (scan.hasNextLine()) {
                    String taskString = scan.nextLine();
                    if(taskString.equals("")) {
                    } else {
                        String[] taskSplit = taskString.split("@");
                        String taskType = taskSplit[0];
                        String taskDone = taskSplit[1];
                        String taskDesc = taskSplit[2];

                        switch (taskType) {

                        case "T":
                            Todo todo = new Todo(taskDesc);
                            if(taskDone.equals("1")) {
                                todo.markAsDone();
                            }
                            tasks.add(todo);
                            break;

                        case "D":
                            String taskDeadline = taskSplit[3] + " " + taskSplit[4];
                            Deadline deadline = Deadline.createDeadline(taskDesc, taskDeadline);
                            if(taskDone.equals("1")) {
                                deadline.markAsDone();
                            }
                            tasks.add(deadline);
                            break;

                        case "E":
                            String taskDateTime = taskSplit[3] + " " + taskSplit[4];
                            Event event = Event.createEvent(taskDesc, taskDateTime);
                            if(taskDone.equals("1")) {
                                event.markAsDone();
                            }
                            tasks.add(event);
                            break;
                        }
                    }
                }
                System.out.println("Welcome back. Dino has successfully loaded your task data.");
            }
        } catch(IOException e) {
            throw new DukeException("Dino could not load your task data.");
        }
        System.out.println("____________________________________________________________");
        return tasks;
    }

    /**
     * Writes action done to task into hard disk.
     * If action is ADD, Dino adds task to hard disk.
     * If action is DELETE, Dino deletes task from hard disk.
     * If action is MARK_DONE, Dino marks the specific task in hard disk as done.
     *
     * @param task Task to be acted upon.
     * @param action DukeAction action done to task.
     * @throws DukeException If Dino could not write to hard disk.
     */
    public void writeToFile(Task task, DukeAction action) throws DukeException {
        try {
            switch(action) {
            case ADD:
                FileWriter fw = new FileWriter(this.filepath, true);
                fw.write(task.storedTaskString() + "\n");
                fw.close();
                System.out.println("Success!");
                break;

            case DELETE:
                File inputFile = new File(this.filepath);
                File tempFile = new File("./data/myTempFile.txt");
                BufferedReader reader = new BufferedReader(new FileReader(this.filepath));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String lineToRemove = task.storedTaskString();
                String currentLine;
                while((currentLine = reader.readLine()) != null) {
                    if (!currentLine.equals(lineToRemove)) {
                        writer.write(currentLine);
                        writer.newLine();
                    }
                }
                writer.close();
                reader.close();
                System.gc();

                if(inputFile.delete()) {
                    if(tempFile.renameTo(inputFile)) {
                        System.out.println("Success!");
                    } else {
                        throw new DukeException("Dino could not write task data to hard disk.");
                    }
                } else {
                    throw new DukeException("Dino could not write task data to hard disk.");
                }
                break;

            case MARK_DONE:
                File taskFile = new File(this.filepath);
                File temFile = new File("./data/myTemFile.txt");
                BufferedReader br = new BufferedReader(new FileReader(this.filepath));
                BufferedWriter bw = new BufferedWriter(new FileWriter(temFile));

                String lineToMarkDone = task.storedTaskString();
                String currentL;
                while((currentL = br.readLine()) != null) {
                    if (!currentL.equals(lineToMarkDone)) {
                        bw.write(currentL);
                    } else {
                        String taskType = currentL.substring(0,2);
                        String taskDesc = currentL.substring(3);
                        bw.write(taskType + "1" + taskDesc);
                    }
                    bw.newLine();
                }
                bw.close();
                br.close();
                System.gc();

                if(taskFile.delete()) {
                    if(temFile.renameTo(taskFile)) {
                        System.out.println("Success!");
                    } else {
                        throw new DukeException("Dino could not write task data to hard disk.");
                    }
                } else {
                    throw new DukeException("Dino could not write task data to hard disk.");
                }
                break;
            }

        } catch(IOException e) {
            throw new DukeException("Dino could not write task data to hard disk.");
        }
    }
}
