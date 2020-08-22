import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class TaskList {
    private ArrayList<Task> lst = new ArrayList<>();
    private File file; // format: e.g. deadline, 1, description/byDATETIME\n


    public TaskList() throws DukeException {
        try {
            this.file = new File("./data/duke.txt"); // main/data/duke.txt
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) { // e.g. deadline, 1, description/by date
                String[] arr = sc.nextLine().split(", ");
                if (arr.length == 3) {
                    this.addTask(arr[0], arr[2], arr[1].equals("1"), true);
                } else {
                    throw new DukeException("invalid format in file");
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Sorry, " + e.getMessage());
        }
    }

    public void updateTextFile() throws DukeException {
        try {
            String text = "";
            FileWriter fw = new FileWriter(this.file);
            for (Task t : lst) {
                text += t.textFormat() + "\n";
            }
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Sorry, " + e.getMessage());
        }
    }

    public int size() {
        return this.lst.size();
    }

    public Task addTask(String type, String input) throws DukeException {
        try {
            return this.addTask(type, input, false, false);
        } catch (DukeException e) {
            throw e;
        }
    }

    private Task addTask(String type, String input, boolean done, boolean fileRead) throws DukeException {
        Task task;
        if (type.equals("todo")) {
            task = new Todo(input);
        } else if (type.equals("deadline")) {
            String[] arr = input.split("/by");
            try {
                task = new Deadline(arr[0].trim(), arr[1].trim());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! I'm sorry, when is the deadline? (/by...)");
            }
        } else if (type.equals("event")) {
            String[] arr = input.split("/at");
            try {
                task = new Event(arr[0].trim(), arr[1].trim());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! I'm sorry, when is the event? (/at...)");
            }
        } else {
            throw new DukeException("OOPS!!! I'm sorry, I don't know what that means :<");
        }
        if (input.isEmpty()) {
            throw new DukeException("OOPS!!! I'm sorry, the description cannot be empty :<");
        }
        task = done ? task.markAsDone() : task;
        this.lst.add(task);
        if (!fileRead) { // if not called by constructor
            this.updateTextFile();
        }
        return task;
    }

    public Task markAsDone(int i) throws DukeException {
        try {
            Task task = this.lst.get(i).markAsDone();
            this.updateTextFile();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! I'm sorry, the task number is out of range :<");
        }
    }

    public Task deleteTask(int i) throws DukeException {
        try {
            Task task = this.lst.remove(i);
            this.updateTextFile();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! I'm sorry, the task number is out of range :<");
        }
    }

    @Override
    public String toString() {
        String listString = "";
        for (int i = 0; i < this.size(); i++) {
            int taskNum = i + 1;
            listString += taskNum + ".";
            listString += this.lst.get(i).toString();
            if (i < this.size() - 1) {
                listString += '\n';
            }
        }
        return listString;
    }
}
