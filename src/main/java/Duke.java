import duke.AdditionalInfo;
import duke.Command;
import duke.Deadline;
import duke.Event;
import duke.Parser;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.ToDo;
import duke.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        String filePath = "./data";
        String fileName = "data.txt";
        this.ui = new Ui();
        this.storage = new Storage(filePath + "/" + fileName);
        try {
            File dir = new File(filePath);
            File file = new File(filePath, fileName);
            if (dir.exists() && file.exists()) {
                this.taskList = new TaskList(storage.load());
            } else if (dir.exists()) {
                // case where only folder exist
                storage.createFile();
                this.taskList = new TaskList();
            } else {
                // case where folder does not exist
                dir.mkdir();
                storage.createFile();
                this.taskList = new TaskList();
            }
        } catch (FileNotFoundException e) {
//            MainWindow.printException(e);
            // TODO: Show exception
            System.exit(1);
        } catch (IOException e) {
//            MainWindow.printException(e);
            System.exit(1);
        }
    }

    private String printTasks(TaskList tasklist, boolean isFind) {
        String output = String.format("Here are the %stasks in your list:", isFind ? "matching " : "");
        for (int i = 0; i < tasklist.size(); i++) {
            Task currentTask = tasklist.get(i);
            String num = Integer.toString(i + 1);
            output += "\n" + num + "." + currentTask;
        }
        return output;
    }

    public String execute(Command command) {
        String output = "";
        int commandType = command.getCommandType();
        boolean print = true;
        if (commandType == Command.LIST) {
            output += printTasks(this.taskList, false);
        } else if (commandType == Command.DONE || commandType == Command.DELETE) {
            int taskIndex = command.getAdditionalInfo().getTaskIndex();
            Task selectedTask = taskList.get(taskIndex);
            if (commandType == Command.DONE) {
                selectedTask.markAsDone();
                output += "Nice! I've marked this task as done:\n  " + selectedTask;
            } else {
                taskList.remove(taskIndex);
                output += "Noted. I've removed this task:\n  " + selectedTask;
            }
            output += "\nNow you have " + taskList.size() + " tasks in the list.";
        } else if (commandType == Command.EXIT) {
            storage.save(taskList);
            print = false;
        } else if (commandType == Command.INVALID) {
            output = "Sorry, I don't understand that command..";
        } else if (commandType == Command.FIND) {
            String keyword = command.getAdditionalInfo().getDescription();
            TaskList tempTaskList = new TaskList();
            for (int i = 0; i < this.taskList.size(); i++) {
                Task tempTask = taskList.get(i);
                String taskDescription = tempTask.getDescription();
                // contains will return true for "bookstore" when searching for "book"
                // contains is case - sensitive "Book" and "book" is different
                if (taskDescription.contains(keyword)) {
                    tempTaskList.add(tempTask);
                }
            }
            output += printTasks(tempTaskList, true);
        } else {
            Task newTask;
            AdditionalInfo info = command.getAdditionalInfo();
            if (commandType == Command.CREATE_TODO) {
                newTask = new ToDo(info.getDescription());
            } else if (commandType == Command.CREATE_DEADLINE) {
                newTask = new Deadline(info.getDescription(), info.getDate(), info.getTime());
            } else {
                newTask = new Event(info.getDescription(), info.getTime());
            }
            taskList.add(newTask);
            output += "Got it. I've added this task:\n  " + newTask;
            output += "\nNow you have " + taskList.size() + " tasks in the list.";
        }

        return print ? output : "";
    }

    String getResponse(String input) {
        Command command;
        command = Parser.parse(input);
        if (command.getCommandType() == Command.EXIT) {
            System.exit(0);
        } else {
            return this.execute(command);
        }
        return "";
    }

//    private void run() {
//        runLoopUntilExit();
//        exit();
//    }

//    private void runLoopUntilExit() {
//        Command command;
//        do {
//            String input = ui.getUserInput();
//            command = Parser.parse(input);
//            this.execute(command);
//        } while (command.getCommandType() != Command.EXIT);
//    }

//    private void exit() {
//        ui.showGoodbyeMessage();
//        System.exit(0);
//    }

    public static void main(String[] args) {
        new Duke();
    }
}
