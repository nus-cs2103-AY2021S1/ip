package Task;

import Exceptions.*;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final Task addedOrDeletedTask;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.addedOrDeletedTask = null;
    }

    public TaskList(ArrayList<Task> tasks) throws DukeException {

        this.tasks = tasks;
        this.addedOrDeletedTask = null;
    }

    public TaskList(ArrayList<Task> tasks , Task addedOrDeletedTask) {
        this.tasks = tasks;
        this.addedOrDeletedTask = addedOrDeletedTask;
    }

    public ArrayList<Task> getTaskList(){
        return this.tasks;
    }

    public int taskSize(){
        return this.tasks.size();
    }

    public Task assignTask(String type, String name) throws NoTaskException, InvalidCommandException, NoDateException {
        try {
            if (type.equals("todo")) {
                try {
                    return new Todo(name.substring(5), false);
                } catch (IndexOutOfBoundsException e) {
                    throw new NoTaskException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else {
                int indexOfCommand = name.indexOf("/");
                String deadline = name.substring(indexOfCommand + 4);
                if (type.equals("deadline")) {
                    try {
                        String currname = name.substring(9);
                        if (indexOfCommand > -1 ) {
                            return new Deadline(name.substring(9, indexOfCommand - 1), false, deadline);
                        } else {
                            throw new NoDateException("☹ OOPS!!! Please specify the deadline!");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        throw new NoTaskException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                } else {
                    try {
                        String currname = name.substring(6);
                        if (indexOfCommand > -1 ) {
                            return new Event(name.substring(6, indexOfCommand - 1), false, deadline);
                        } else {
                            throw new NoDateException("☹ OOPS!!! Please specify when the event is going to be held!");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        throw new NoTaskException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                }
            }
        } catch (NullPointerException e) {
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public Task getAddedOrDeletedTask(){
        return this.addedOrDeletedTask;
    }


    public static int decideIndexDelete(String word) throws MissingSpecifiedDeleteError {
        int index = 0;
        try {
            index = Integer.parseInt(word.substring(7));
        } catch (IndexOutOfBoundsException e) {
            throw new MissingSpecifiedDeleteError("☹ OOPS!!! Please specify which task you want to delete.");
        }
        return index;
    }

    public static Task deletedTask(int index, ArrayList<Task> tasks) throws WrongDeleteIndexError {
        Task curr = null;
        try {
            curr = tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new WrongDeleteIndexError("☹ OOPS!!! You only have " + tasks.size() + " tasks in your list. " +
                    "Please select a valid task to be deleted.");
        }
        return curr;
    }

    private static String line = "________________________________________________";
    public TaskList delete(String task) {
        int num = 0;
        ArrayList<Task> tempTasks = this.tasks;
        Task removed = null;
        try {
            num = decideIndexDelete(task);
            try {
                removed = deletedTask(num,tasks);
                tempTasks.remove(num - 1);
            } catch (WrongDeleteIndexError e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
            }
        } catch (MissingSpecifiedDeleteError e) {
            System.out.println(line);
            System.out.println(e.getMessage());
            System.out.println(line);
        }
        return new TaskList(tempTasks, removed);

    }

    public TaskList add(String task) throws NoTaskException, InvalidCommandException, NoDateException {
        String firstWord = task.toLowerCase().contains("todo") ? "todo"
                : task.toLowerCase().contains("deadline") ? "deadline"
                : task.toLowerCase().contains("event") ? "event"
                : null;

        Task curr = null;
        ArrayList<Task> tempTasks = this.tasks;
        try {
            curr = assignTask(firstWord, task);
            tempTasks.add(curr);
        } catch (NoTaskException e) {
            throw new NoTaskException(e.getMessage());
        } catch (InvalidCommandException e) {
            throw new InvalidCommandException(e.getMessage());
        } catch (NoDateException e){
            throw new NoDateException(e.getMessage());
        }
        return new TaskList(tempTasks, curr);
    }

    public TaskList done(String done){
        int num = Integer.parseInt(done.substring(5));
        ArrayList<Task> tempTask = this.tasks;
        Task curr = tempTask.get(num - 1).setToTrue();
        tempTask.set(num - 1, curr);
        curr = tasks.get(num - 1);

        return new TaskList(tempTask , curr);

    }

}
