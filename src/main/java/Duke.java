
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Duke {

    public static List<Task> toDoList = new ArrayList<>();
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String fileLocation;
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke(String fileLocation) {
        this.fileLocation = fileLocation;
        this.ui = new Ui();
        this.storage = new Storage(fileLocation);
        try {
            this.taskList = new TaskList(this.storage.readFile());
        } catch (InvalidSaveFileException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }
//    }
//    public void listTask() {
//        this.ui.showLine();
//        if(toDoList.size() == 0) {
//            System.out.println("\tList is empty! Start adding some tasks");
//        } else {
//            System.out.println("\t Here are the tasks in your list:");
//            for (int i = 1; i <= toDoList.size(); i++) {
//                Task current = toDoList.get(i - 1);
//                System.out.println("\t" + i + "." + current.toString());
//            }
//        }
//        this.ui.showLine();
//    }
//
//    public void completeTask(String newLine) throws InvalidInputException {
//        if(newLine.length() <= 5) {
//            throw new InvalidInputException("\t☹ OOPS!!! Please specify which task you want to complete!");
//        }
//        int completed = Integer.parseInt(newLine.substring(5));
//        try {
//            Task current = toDoList.get(completed - 1);
//            current.completeTask();
//            this.ui.showLine();
//            System.out.println("\tNice! I've marked this task as done:");
//            System.out.println("\t\t" + current.toString());
//            System.out.println();
//        } catch (IndexOutOfBoundsException e) {
//            throw new InvalidInputException("\tIndex out of bounds! Please try again.");
//        }
//    }
//
//    private void deadlineTask(String newLine) throws InvalidInputException, InvalidDateTimeFormatException {
//        if(newLine.length() <= 9) {
//            throw new InvalidInputException("\t☹ OOPS!!! The description of a deadline cannot be empty.");
//        }
//        String[] splitWord = newLine.split("/");
//        String deadline = splitWord[1].substring(3);
//        Deadlines task;
//        try {
//            task = new Deadlines(splitWord[0].substring(9), LocalDateTime.parse(deadline,dtf));
//        } catch (DateTimeParseException e) {
//            throw new InvalidDateTimeFormatException("\tDeadline input must follow a certain format: yyyy-mm-dd HH:mm " +
//                    "e.g. 2020-08-23 16:45");
//        }
//            toDoList.add(task);
//            this.ui.showLine();
//            System.out.println("\tGot it. I've added this task:");
//            System.out.println("\t" + task.toString());
//            System.out.println("\tNow you have " + toDoList.size() + " tasks in the list.");
//            this.ui.showLine();
//
//    }
//    private void eventTask(String newLine) throws InvalidInputException, InvalidDateTimeFormatException {
//        if(newLine.length() <= 6) {
//            throw new InvalidInputException("\t☹ OOPS!!! The description of an event cannot be empty.");
//        }
//        String[] splitWord = newLine.split("/");
//        String timing = splitWord[1].substring(3);
//        Events task;
//        try {
//            task = new Events(splitWord[0].substring(6),LocalDateTime.parse(timing,dtf) );
//        } catch(DateTimeParseException e) {
//            throw new InvalidDateTimeFormatException("\tEvent timing input must follow a certain format: yyyy-mm-dd HH:mm " +
//                    "e.g. 2020-08-23 16:45");
//        }
//        toDoList.add(task);
//        this.ui.showLine();
//        System.out.println("\tGot it. I've added this task:");
//        System.out.println("\t"+task.toString());
//        System.out.println("\tNow you have " + toDoList.size() + " tasks in the list.");
//        this.ui.showLine();
//    }
//
//    private void todoTask(String newLine) throws InvalidInputException {
//        if(newLine.length() <= 5) {
//            throw new InvalidInputException("\t☹ OOPS!!! The description of a todo cannot be empty.");
//        }
//        ToDos task = new ToDos(newLine.substring(5));
//        toDoList.add(task);
//        this.ui.showLine();
//        System.out.println("\tGot it. I've added this task:");
//        System.out.println("\t"+task.toString());
//        System.out.println("\tNow you have " + toDoList.size() + " tasks in the list.");
//        this.ui.showLine();
//    }
//
//    public void deleteTask(String newLine) throws InvalidInputException{
//        if(newLine.length() <= 7) {
//            throw new InvalidInputException("\t☹ OOPS!!! The description of a delete operation cannot be empty / invalid index.");
//        }
//        int index = Integer.parseInt(newLine.substring(7));
//        if( index >= toDoList.size() || index<0) {
//            throw new InvalidInputException("\t☹ OOPS!!! The description of a delete operation cannot be empty / invalid index.");
//        }
//        Task task = toDoList.get(index-1);
//        toDoList.remove(index-1);
//        this.ui.showLine();
//        System.out.println("\tNoted. I've removed this task:");
//        System.out.println("\t"+task.toString());
//        System.out.println("\tNow you have " + toDoList.size() + " tasks in the list.");
//        this.ui.showLine();
//    }


    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
