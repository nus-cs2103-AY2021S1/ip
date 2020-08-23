package main.java;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> todo;
    final static String UNDERSCORE = "____________________________________________________________ \n";

    public TaskList(){
        todo = new ArrayList<>();
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

    public void addTask (String line) throws DukeException {
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

    private void addEvent (String line) throws DukeInvalidDayException, DukeInvalidTaskException {
        String[] splits = line.split("event |/at");
        if (splits.length > 2){
            Event task = new Event(splits[1], splits[2]);
            todo.add(task);
            System.out.println(UNDERSCORE + "Got it. I've added this to task: \n" + task + "\n"
                    + "Now you have " + todo.size() + " tasks in the list \n" + UNDERSCORE
            );
        } else if (splits.length > 1){
            throw new DukeInvalidDayException();
        } else {
            throw new DukeInvalidTaskException();
        }
    }

    private void addDeadline (String line) throws DukeInvalidDateException, DukeInvalidTaskException {
        String[] splits = line.split("deadline |/by ");
        if (splits.length > 2) {
            Deadline task = new Deadline(splits[1], splits[2]);
            todo.add(task);
            System.out.println(UNDERSCORE + "Got it. I've added this to task: \n" + task + "\n"
                    + "Now you have " + todo.size() + " tasks in the list \n" + UNDERSCORE
            );
        } else if (splits.length > 1) {
            throw new DukeInvalidDateException();
        } else {
            throw new DukeInvalidTaskException();
        }
    }

    private void addToDo (String line) throws DukeInvalidTaskException {
        String[] splits = line.split("todo");
        if(splits.length > 1) {
            Todo task = new Todo(splits[1]);
            todo.add(task);
            System.out.println(UNDERSCORE + "Got it. I've added this to task: \n" + task + "\n"
                    + "Now you have " + todo.size() + " tasks in the list \n" + UNDERSCORE
            );
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
