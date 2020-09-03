package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.IOException;

public class Storage {

    private String pathName;
    private String fileName;

    public Storage(String pathName, String fileName) {
        this.pathName = pathName;
        this.fileName = fileName;
    }

    /**
     * Returns a TaskList that stores tasks retrieved from the data/duke.txt if the file exits
     *
     * @return a TaskList with tasks stored in the txt file.
     */
    public TaskList load() {
        TaskList tasks = new TaskList( new ArrayList<Task>() );
        try {
            File file = new File("data/duke.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String previousTask = sc.nextLine();
                String[] words = previousTask.split(" @ ", 0);
                if (words[0].equals("D")) {
                    Deadline previousDeadline = new Deadline(words[2], words[3]);
                    if (words[1].equals("1")) {
                        previousDeadline.setIsDone();
                    }
                    tasks.add(previousDeadline);
                } else if (words[0].equals("T")) {
                    ToDo previousToDo = new ToDo(words[2]);
                    if (words[1].equals("1")) {
                        previousToDo.setIsDone();
                    }
                    tasks.add(previousToDo);
                } else if (words[0].equals("E")) {
                    Event previousEvent = new Event(words[2], words[3]);
                    if (words[1].equals("1")) {
                        previousEvent.setIsDone();
                    }
                    tasks.add(previousEvent);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Creates a duke.txt under data directory if the file does not exist at the first place.
     * If the file exist, start loading. (Adding previous stored task in duke.txt into an ArrayList<tasks>)
     *
     * @throws IOException exception occurs when the file is not found
     */
    public void createFile() throws IOException {
        File file = new File(this.pathName);
        File dukeSave = new File(this.pathName + this.fileName);
        if (!file.exists()) {
            file.mkdirs();
            dukeSave.createNewFile();
        } else {
            if (dukeSave.exists()) {
                this.load();
            } else {
                dukeSave.createNewFile();
            }
        }

    }

    /**
     * Stores newly added tasks and previous tasks from the TaskList in the duke.txt file
     *
     * @param tasks TaskList with newly added tasks and previous tasks
     * @throws IOException exception occurs when the file is not found
     */
    public void storeFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        for (int k = 0; k < tasks.size(); k++) {
            Task currentTask = tasks.get(k);
            String isDone = currentTask.isDone ? " 1 @ " : " 0 @ ";
            if (currentTask instanceof ToDo) {
                fw.write("T @" + isDone + currentTask.description + System.lineSeparator());
            } else if (currentTask instanceof Deadline) {
                String date = " @ " + ((Deadline) currentTask).by;
                fw.write("D @" + isDone + currentTask.description + date + System.lineSeparator());
            } else if (currentTask instanceof Event) {
                String date = " @ " + ((Event) currentTask).at;
                fw.write("D @" + isDone + currentTask.description + date + System.lineSeparator());
            }
        }
        fw.close();
    }


}
