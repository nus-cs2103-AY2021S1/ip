package duke;

import java.io.IOException;
import java.util.*;


public class Duke {

    private Storage saveData;
    private TaskList list;
    private Ui ui;

    public Duke(String filePath) {
        this.saveData = new Storage(filePath);
        list = new TaskList(saveData.loadSavedData());
    }

    public void run() {
        ui.sayHi();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String str = sc.nextLine();
            String arr[] = str.split(" ");
            String keyWord = arr[0];
            if (keyWord.equals("bye")) {
                break;
            } else if (keyWord.equals("list")) {
                showList();
            } else if(keyWord.equals("todo")) {
                addToDo(str);
            } else if(keyWord.equals("deadline")){
                addDeadline(str);
            } else if (keyWord.equals("event")){
                addEvent(str);
            } else if(keyWord.equals("done")) {
                addCompleteTask(str);
            } else if(keyWord.equals("delete")) {
                addDeleteTask(str);
            } else {
                try {
                    throw new DukeException("☹ OOPS!!! wait..... I don't understand your order my sir.");
                } catch (DukeException e) {
                    ui.saySomthing(e.getMessage());
                }
            }
        }
        ui.byeMessage();
    }
    public void addDeleteTask(String str) {
        try {
            if (str.trim().length() == 6) {
                throw new DukeException("☹ OOPS!!! Check delete formatting, include which task to delete.");
            } else if (Character.getNumericValue(str.charAt(7)) > list.size() || Character.getNumericValue(str.charAt(7)) == 0) {
                throw new DukeException("☹ OOPS!!! Task not in the list");
            }
            int index = Character.getNumericValue(str.charAt(7));
            deleteTask(index);
            this.saveData.deleteTask(index);
        } catch (DukeException | IOException e) {
            ui.saySomthing(e.getMessage());
        }
    }

    public void deleteTask(int index) {
        Task toRemove = list.get(index-1);
        list.remove(index-1);
        ui.saySomthing("Noted. I've removed this task:\n" + toRemove.toString() + "\n" + String.format("Now you have %d tasks in the list.", list.size()));
    }
    public void addDeadline(String str) {
        try {
            if (str.trim().length() == 8) {
                throw new DukeException("☹ OOPS!!! Check deadline formatting, include description and /by.");
            } else if (!str.contains("/by")) {
                throw new DukeException("☹ OOPS!!! Check deadline formatting, include /by.");
            }
            String holder[] = str.split("deadline")[1].split("/by ");
            String description = holder[0].trim();
            String by = holder[1].trim();
            Deadline task = new Deadline(description, by);
            addToList(task);
            String save = "D>0>"+description+">"+by;
            this.saveData.addTask(save);
        } catch (DukeException | IOException e) {
            ui.saySomthing(e.getMessage());
        }
    }

    public void addToDo(String str) {
        try {
            if (str.trim().length() == 4) {
                throw new DukeException("☹ OOPS!!! Check todo formatting, include description");
            }
            Todo task = new Todo(str.substring(5));
            addToList(task);
            String save = "T>0>"+str.substring(5);
            this.saveData.addTask(save);
        } catch (DukeException | IOException e) {
            ui.saySomthing(e.getMessage());
        }
    }

    public void addEvent(String str) {
        try {
            if (str.trim().length() == 5) {
                throw new DukeException("☹ OOPS!!! Check event formatting, include description and /at.");
            } else if (!str.contains("/at")) {
                throw new DukeException("☹ OOPS!!! Check event formatting, include /at.");
            }
            String holder[] = str.split("event")[1].split("/at ");
            String description = holder[0].trim();
            String at = holder[1].trim();
            Event task = new Event(description, at);
            addToList(task);

            String save = "E>0>"+description+">"+at;
            this.saveData.addTask(save);
        } catch (DukeException | IOException e) {
            ui.saySomthing(e.getMessage());
        }
    }

    public void addCompleteTask(String str) {
        try {
            if (str.trim().length() == 4) {
                throw new DukeException("☹ OOPS!!! Check done formatting, include which task to complete.");
            } else if (Character.getNumericValue(str.charAt(5)) > list.size() || Character.getNumericValue(str.charAt(5)) == 0) {
                throw new DukeException("☹ OOPS!!! Task not in the list");
            }
            int index = Character.getNumericValue(str.charAt(5));
            completeTask(index);
            this.saveData.completeTask(index);
        } catch (DukeException | IOException e) {
            ui.saySomthing(e.getMessage());
        }
    }

    public void completeTask(int index) {
        list.get(index-1).markAsDone();
        ui.saySomthing("Nice! I've marked this task as done:\n" + list.get(index-1).toString());
    }

    public void addToList(Task task) {
        list.add(task);
        ui.saySomthing("Got it. I've added this task:\n" + task.toString() + "\n" + String.format("Now you have %d tasks in the list.", list.size()));
    }

    public void showList() {
        String print = "";
        print += "Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size()-1) {
                print += String.format("%d. ", i+1) + list.get(i);
            } else {
                print += String.format("%d. ", i + 1) + list.get(i) + "\n";
            }
        }
        ui.saySomthing(print);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
