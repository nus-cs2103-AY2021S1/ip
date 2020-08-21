package ip.src.main.java;

import java.util.ArrayList;

public class Tasks {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private Layout layout = new Layout();
    
    public enum Type {
        TODO,
        DEADLINE,
        EVENT
    };
    
    public enum Action {
        DONE, 
        DELETE
    }

    public void addTask(Type type, String [] arr) {
        Task task;
        try {
            String date = getInfo(arr)[0];
            String description = getInfo(arr)[1];
            if (description.equals("") || arr.length == 1) {
                throw new DukeException("The description of a " + type + " cannot be empty");
            }
    
            switch(type) {
            case DEADLINE:
                if (date.equals("")) {
                    throw new DukeException("Please specify a due date using /by");
                } else {
                    task = new Deadline(description, date);
                }
                break;
            case EVENT:
                if (date.equals("")) {
                    throw new DukeException("Please specify an event date using /at");
                } else {
                    task = new Event(description, date);   
                }
                break;
            default: //case: todo
                task = new Todo(description);
                break;
            }
    
            tasks.add(task);
            layout.printAddedMessage(task.toString(), tasks.size());
        } catch (DukeException e) {
            layout.print(e.getMessage());
        }
    }

    public void showTasks() {
        layout.printTaskList(tasks);
    }

    public void modifyTask(Action type, String i) {
        try {
            int index = Integer.parseInt(i);
            Task task = tasks.get(index - 1);
            switch (type) {
                case DONE:
                    task.markDone();
                    layout.printMarkedDone(task);
                    break;
                case DELETE:
                    tasks.remove(index - 1);
                    layout.printDeleted(task, tasks.size());
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            DukeException d = new DukeException("Task " + i + " cannot be found");
            layout.print(d.getMessage());
        } catch (NumberFormatException e) {
            DukeException d = new DukeException(i + " is not an integer");
            layout.print(d.getMessage());
        }

    }

    public String [] getInfo(String [] arr) {
        boolean reached = false;
        String date = "";
        String description = "";
        for (int i = 1; i < arr.length; i ++) {
            if (reached) {
                if (i != arr.length - 1) {
                    date += arr[i] + " ";
                } else {
                    date += arr[i];
                }
            } else if (arr[i].equals("/by") || arr[i].equals("/at")) {
                reached = true;
            } else {
                description += arr[i] + " ";
            }
        }
        return new String[]{date, description};
    }

}
