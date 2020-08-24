package tasks;

import DukeException.DukeException;
import storage.Storage;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class TaskList {

    protected ArrayList<Task> list;
    protected Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
        this.list = storage.load();
    }

    public Storage getStorage() {
        return this.storage;
    }

    protected void addToList(Task task) {
        list.add(task);
    }

    protected int getNumList() {
        return this.list.size();
    }

    public boolean containsWord(String text, String keyword) {
        /*String regexFormat = "(?i).*?\\b%s\\b.*?";
        String regex = String.format(regexFormat, Pattern.quote(keyword));
        return text.matches(regex);*/
        return text.contains(keyword);
    }

    public boolean findInList(String keyword) {
        boolean isMatch = false;
        int i = 1;
        for (Task task : list) {
            if (containsWord(task.getTaskName(), keyword)) {
                isMatch = true;
                System.out.println(i + ". " + task);
                i++;
            }
        }
        return isMatch;
    }

    public void setDoneList(String command) throws DukeException {
        String[] doneCommand = command.split("\\W+");
        try {
            int i = parseInt(command.split(" ")[1]);
            Task doneTask = list.get(i - 1);
            doneTask.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            ;
        } catch (NumberFormatException e) {
            ;
        }

    }

    public void deleteList(String command) throws DukeException{
        String[] deleteCommand = command.split("\\W+");
        try {
            int i = parseInt(command.split(" ")[1]);
            Task deleteTask = list.get(i - 1);
            list.remove(i - 1);
        } catch (IndexOutOfBoundsException e) {
            ;
        } catch (NumberFormatException e) {
            ;
        }

    }

    protected static String processTasks(Task task) {
        String result = "";
        int isDone = task.getIsDone().equals("[\u2713] ") ? 1 : 0;
        if (task instanceof Todo) {
            result = "T | " + isDone + " | " + task.getTaskName();
        } else if (task instanceof Deadline) {
            result = "D | " + isDone + " | " + task.getTaskName() + " | " + ((Deadline) task).getByDate();
        } else if (task instanceof Event) {
            result = "E | " + isDone + " | " + task.getTaskName() + " | " + ((Event) task).getAtDate();
        }
        return result;
    }

    public void addToFile(Task task) {
        String taskString = processTasks(task);
        storage.saveData(taskString);
        addToList(task);
        System.out.println("New task added!");
        System.out.println(task);
        System.out.println("You now have " + getNumList() + " tasks.");
    }

    /*public void readList() {
        if (storage.getNumOfTasks() == 0) {
            System.out.println("Looks like you don't have any tasks! Go on and add some!");
        } else {
            System.out.println("Here's all your tasks to complete:");
            storage.readFile();
            System.out.println("Time to get to work! :D");
        }
    }*/

    public void readList() {
        if (list.isEmpty()) {
            System.out.println("Looks like you don't have any tasks! Go on and add some!");
        } else {
            System.out.println("Here's all your tasks to complete:");
            int i = 1;
            for (Task ele : list) {
                System.out.println(i + ". " + ele);
                i++;
            }
            System.out.println("Time to get to work! :D");
        }
    }

}
