import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> ls) {
        this.taskList = ls;
    }

    public void checkForItem(String input, String task) throws DukeException {
        if (input.isBlank()) {
            throw new DukeException("The description of a " + task + " cannot be empty!");
        }
    }

    public void completeTask(String userInput) {
        try {
            int index = userInput.charAt(5) - '0';
            if (index < 1 || index > taskList.size()) {
                Ui.print("Index out of range! Try Again.\n");
            } else {
                if (taskList.get(index - 1).getIsDone()) {
                    Ui.print("This task has already been completed!");
                } else {
                    taskList.get(index - 1).markAsDone();
                    Storage.completeTask(index - 1, taskList.size());
                    String info = "  Nice! I have marked this task as done:\n";
                    info += taskList.get(index - 1).toString() + "\n";
                    Ui.print(info);
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            Ui.print("Index out of range! Try again.\n");
        }
    }

    public void deleteTask(String userInput) {
        int index = userInput.charAt(7) - '0';
        if (index < 1 || index > taskList.size()) {
            Ui.print("Index out of range! Try Again.\n");
        } else {
            String info = "Noted. I have removed this task:\n";
            info += "  " + taskList.get(index - 1).toString() + "\n";
            taskList.remove(index - 1);
            Storage.deleteTask(index - 1, taskList.size());
            info += "Now you have " + taskList.size() + " tasks in the list" + "\n";
            Ui.print(info);
        }
    }

    public void addToDo(String userInput) {
        try {
            this.checkForItem(userInput.substring(4), "todo");
            String task = userInput.substring(5);
            String info = ("Got it. I have added this task:\n");
            Todo temp = new Todo(task);
            Storage.addTask(temp.getStorageString("T"));
            info += "  " + temp.toString() + "\n";
            this.taskList.add(temp);
            info += "Now you have " + taskList.size() + " tasks in the list\n";
            Ui.print(info);
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addDeadline(String userInput) {
        try {
            checkForItem(userInput.substring(8), "deadline");
            int dateIndex = userInput.indexOf("/");
            String task = userInput.substring(9, dateIndex);
            String time = userInput.substring(dateIndex + 1);
            String info = "Got it. I have added this task:\n";
            Deadline temp = new Deadline(task, time);
            String formatDate = temp.getFormattedDate();
            info += "  " + temp.toString() + "\n";
            taskList.add(temp);
            Storage.addTask(temp.getStorageString("D", formatDate));
            info += "Now you have " + taskList.size() + " tasks in the list\n";
            Ui.print(info);
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addEvent(String userInput) {
        try {
            checkForItem(userInput.substring(5), "event");
            int dateIndex = userInput.indexOf("/");
            String task = userInput.substring(6, dateIndex);
            String time = userInput.substring(dateIndex + 1);
            String info = "Got it. I have added this task:\n";
            Event temp = new Event(task, time);
            String formatDate = temp.getFormattedDate();
            info += "  " + temp.toString() + "\n";
            Storage.addTask(temp.getStorageString("E", formatDate));
            taskList.add(temp);
            info += "Now you have " + taskList.size() + " tasks in the list\n";
            Ui.print(info);
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        String temp = "";
        temp += ("  Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            temp += ("  " + (i + 1) + ". " + taskList.get(i).toString() + " \n");
        }
        return temp;
    }


}
