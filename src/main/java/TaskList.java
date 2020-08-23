package main.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
public class TaskList {
    private ArrayList<Task> todo;
    final static String UNDERSCORE = "____________________________________________________________ \n";
    final static String FILE_PATH =  "./src/main/java/data/duke.txt";

    public TaskList(File data) throws FileNotFoundException, DukeException {
        Scanner reader = new Scanner(data);
        todo = new ArrayList<>();
        while (reader.hasNext()) {
            String currentTask = reader.nextLine();
            String[] splits = currentTask.split(" / ");
            boolean done = Integer.parseInt(splits[1]) == 1 ? true : false;
            String type = splits[0];
            String task = splits[2];
            switch (type) {
                case ("T"):
                    Task todoTask = new Todo(task);
                    if (done) {
                        todoTask.checkOff();
                    }
                    todo.add(todoTask);
                    break;
                case ("D"):
                    String date = splits[3];
                    Deadline deadlineTask = new Deadline(task, date);
                    if (done) {
                        deadlineTask.checkOff();
                    }
                    todo.add(deadlineTask);
                    break;
                case ("E"):
                    String day = splits[3];
                    Event eventTask = new Event(task, day);
                    if (done) {
                        eventTask.checkOff();
                    }
                    todo.add(eventTask);
                    break;
            }
        }
        System.out.println("your current tasks are: ");
        list();
    }

    public void delete (String line) throws DukeException {
        String[] splits = line.split("delete ");
        if(splits.length > 1) {
            int taskNumber =  Integer.parseInt(splits[1]);
            if (taskNumber - 1 >= todo.size() || taskNumber - 1 < 0 || todo.size() == 0){
                throw new DukeException("Invalid task number");
            } else {
                int size = todo.size() - 1;
                System.out.println(UNDERSCORE + "Noted. I've removed this task: \n"
                        + todo.get(taskNumber - 1) + "\n" + "Now you have " + size + " task in the list \n"
                        + UNDERSCORE
                );
                todo.remove(taskNumber - 1);
            }
        } else {
            throw new DukeException("Please key in the task number to be marked done");
        }
    }

    public void addTask (String line) throws DukeException, IOException {
        if (line.indexOf("todo") == 0) {
            addToDo(line);
        } else if (line.indexOf("deadline") == 0) {
            addDeadline(line);
        } else if (line.indexOf("event") == 0) {
            addEvent(line);
        } else {
            throw new DukeException("I'm sorry i don't know what you mean :(");
        }

    }

    private void addEvent (String line) throws DukeInvalidDayException, DukeInvalidTaskException, IOException {
        String[] splits = line.split("event |/at");
        if (splits.length > 2){
            Event task = new Event(splits[1], splits[2]);
            todo.add(task);
            System.out.println(UNDERSCORE + "Got it. I've added this to task: \n" + task + "\n"
                    + "Now you have " + todo.size() + " tasks in the list \n" + UNDERSCORE
            );
            FileWriter fw = new FileWriter(FILE_PATH, true);
            String textToAppend = "\nE / 0 / " + splits[1] + " / " + splits[2];
            fw.write(textToAppend);
            fw.close();
        } else if (splits.length > 1){
            throw new DukeInvalidDayException();
        } else {
            throw new DukeInvalidTaskException();
        }
    }

    private void addDeadline (String line) throws DukeInvalidDateException, DukeInvalidTaskException, IOException {
        String[] splits = line.split("deadline |/by ");
        if (splits.length > 2) {
            Deadline task = new Deadline(splits[1], splits[2]);
            todo.add(task);
            System.out.println(UNDERSCORE + "Got it. I've added this to task: \n" + task + "\n"
                    + "Now you have " + todo.size() + " tasks in the list \n" + UNDERSCORE
            );
            FileWriter fw = new FileWriter(FILE_PATH, true);
            String textToAppend = "\nD / 0 / " + splits[1] + " / " + splits[2];
            fw.write(textToAppend);
            fw.close();
        } else if (splits.length > 1) {
            throw new DukeInvalidDateException();
        } else {
            throw new DukeInvalidTaskException();
        }
    }

    private void addToDo (String line) throws DukeInvalidTaskException, IOException {
        String[] splits = line.split("todo ");
        if(splits.length > 1) {
            Todo task = new Todo(splits[1]);
            todo.add(task);
            System.out.println(UNDERSCORE + "Got it. I've added this to task: \n" + task + "\n"
                    + "Now you have " + todo.size() + " tasks in the list \n" + UNDERSCORE
            );
            FileWriter fw = new FileWriter(FILE_PATH, true);
            String textToAppend = "\nT / 0 / " + splits[1];
            fw.write(textToAppend);
            fw.close();
        } else {
            throw new DukeInvalidTaskException();
        }
    }

    public void checkOff (String line) throws DukeException {
        String[] splits = line.split("done ");
        if(splits.length > 1){
            int taskNumber = Integer.parseInt(splits[1]);
            if(taskNumber - 1 >= todo.size() ){
                throw new DukeException("Invalid Task Number");
            }
            todo.get(taskNumber - 1).checkOff();
            System.out.println(UNDERSCORE + "Nice! I've marked this task as done: \n" +
                    todo.get(taskNumber - 1) + "\n" + UNDERSCORE
            );
        } else {
            throw new DukeException("Please key in the task number to be marked done");
        }
    }

    public void list() {
        System.out.println(UNDERSCORE);
        if(todo.size() == 0){
            System.out.println("you do not have any tasks yet");
        } else {
            for (int i = 0; i < todo.size(); i++) {
                int number = i + 1;
                System.out.println(" " + number + "." + todo.get(i));
            }
        }
        System.out.println(UNDERSCORE);
    }
}
