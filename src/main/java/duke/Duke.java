package main.java.duke;

import main.java.duke.tasks.Task;
import main.java.duke.tasks.Deadline;
import main.java.duke.tasks.Todo;
import main.java.duke.tasks.Event;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                String arr[] = fullCommand.split(" ", 2);
                String command = arr[0];
                if (command.equals("list")) {
                    ui.printInBorder(tasks.listAllTasks());
                } else if (command.equals("done") || command.equals("delete")) {
                    if (arr.length < 2) {
                        throw new DukeException("Please specify a task number!");
                    }
                    int taskNum = Integer.parseInt(arr[1]);
                    if (command.equals("done")) {
                        Task task = tasks.doneTask(taskNum);
                        storage.editTaskList(task.saveToString(), taskNum, false);
                        ui.printInBorder("Nice I've digested the following:\n"
                                + task.toString() + "\n" + "Now I'm hungry again! FEED ME MORE :3");
                    } else {
                        Task task = tasks.removeTask(taskNum);
                        storage.editTaskList("", taskNum, true);
                        ui.printInBorder("Nooo you can't take away what you've already given me...\n"
                                + "Okay fine. It's in my stomach tho... ASDFGUUVHHH!!\n"
                                + "The following has been removed:\n"
                                + task.toString() + "\n" + "Now I'm feeling sick :( there's " + tasks.getTaskCount()
                                + " thing(s) in my belly now...HUNGRY!");
                    }
                } else if (command.equals("uwu")) {
                    ui.printInBorder("owo");
                } else if (command.equals("owo")) {
                    ui.printInBorder("uwu");
                } else if (command.equals("exit")) {
                    ui.printInBorder("bb cya again!");
                    isExit = true;
                } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    if (arr.length < 2) {
                        throw new DukeException("The description of a task cannot be empty!");
                    }
                    Task newTask;
                    if (!command.equals("todo")) {
                        if (command.equals("deadline")) {
                            if (!arr[1].contains(" /by ")) {
                                throw new DukeException("The date has to be specified!");
                            }
                            String temp[] = arr[1].split(" /by ", 2);
                            newTask = new Deadline(temp[0], temp[1].replace('/', '-'));
                        } else {
                            if (!arr[1].contains(" /at ")) {
                                throw new DukeException("The date has to be specified!");
                            }
                            String temp[] = arr[1].split(" /at ", 2);
                            newTask = new Event(temp[0], temp[1].replace('/', '-'));
                        }
                    } else {
                        newTask = new Todo(arr[1]);
                    }
                    tasks.addTask(newTask);
                    storage.saveTaskList(newTask.saveToString());
                    ui.printInBorder("*Gobble gobble* the following has been eated OwO:\n"
                            + newTask.toString() + "\n"
                            + "I now have " + tasks.getTaskCount() + " thing(s) in my belly");
                } else {
                    throw new DukeException("I don't understand what you're saying HMM...");
                }
            } catch (DukeException e) {
                ui.printInBorder(e.toString());
            }
        }
    }


    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }


}
