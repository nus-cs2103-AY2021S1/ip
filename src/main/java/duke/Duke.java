package duke;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import duke.ui.Ui;

import duke.dukeException.DukeException;

import duke.parser.Parser;

public class Duke {
    public static String home = System.getProperty("user.home");
    public static String filePath = home + "/Desktop/duke.txt";
    private Storage storeFile;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storeFile = new Storage(filePath);
        try {
            tasks = new TaskList(storeFile.loadFile());
        } catch (DukeException e) {
            ui.showErrorMsg(e);
            tasks = new TaskList();
        }
        ui.startupMsg();
    }

    public void run() {
        String userInput;
        String[] userInputArray;
        boolean validInput = true;

        while (true) {
            userInput = ui.readInput();
            if (userInput.equals("bye")) {
                break;
            }
            switch (userInput) {
            case "list":
                if (tasks.isEmpty()) {
                    ui.showListEmptyMsg();
                } else {
                    ui.showListMsg();
                    ui.showTaskList(tasks);
                }
                break;
            case "done":
                if (tasks.isEmpty()) {
                    ui.showListEmptyMsg();
                } else {
                    ui.showListMsg();
                    ui.showTaskList(tasks);
                    do {
                        ui.showListDoneAskMsg();
                        try {
                            userInput = ui.readInput();
                            int[] tasksArray = Parser.parse(userInput);
                            TaskList doneList = new TaskList();
                            for (int index : tasksArray) {
                                try {
                                    if (index > tasks.size() || index <= 0) {
                                        throw new DukeException("This task #" + index + " does not exist.");
                                    }
                                    Task task = tasks.getTask(index);
                                    task.setDone();
                                    doneList.addTask(task);
                                } catch (DukeException e) {
                                    ui.showErrorMsg(e);
                                }
                            }
                            if (!doneList.isEmpty()) {
                                ui.showListDoneMsg();
                                ui.showTaskList(doneList);
                            }
                            validInput = true;
                        } catch (DukeException e) {
                            ui.showErrorMsg(e);
                        }
                    } while (!validInput);
                }

                try {
                    storeFile.saveFile(tasks);
                } catch (DukeException e) {
                    ui.showErrorMsg(e);
                }

                break;
            case "todo":
                do {
                    ui.showTaskAddAskMsg();
                    try {
                        userInput = ui.readInput();
                        if (userInput.equals("")) {
                            validInput = false;
                            throw new DukeException("Yo! Task details are missing.");
                        }
                        Task toDo = new Todo(userInput);
                        tasks.addTask(toDo);
                        ui.showTaskAddedMsg(toDo);
                        validInput = true;
                    } catch (DukeException e) {
                        ui.showErrorMsg(e);
                    }
                } while (!validInput);

                try {
                    storeFile.saveFile(tasks);
                } catch (DukeException e) {
                    ui.showErrorMsg(e);
                }

                break;
            case "deadline":
                do {
                    ui.showTaskAddAskMsg();
                    try {
                        userInput = ui.readInput();
                        userInputArray = Parser.parseDetails(userInput);
                        Task deadLine = new Deadline(userInputArray[0], userInputArray[1]);
                        tasks.addTask(deadLine);
                        ui.showTaskAddedMsg(deadLine);
                        validInput = true;

                    } catch (DukeException e) {
                        ui.showErrorMsg(e);
                        validInput = false;
                    }
                } while (!validInput);

                try {
                    storeFile.saveFile(tasks);
                } catch (DukeException e) {
                    ui.showErrorMsg(e);
                }

                break;
            case "event":
                do {
                    ui.showTaskAddAskMsg();
                    try {
                        userInput = ui.readInput();
                        userInputArray = Parser.parseDetails(userInput);
                        Task event = new Event(userInputArray[0], userInputArray[1]);
                        tasks.addTask(event);
                        ui.showTaskAddedMsg(event);
                        validInput = true;

                    } catch (DukeException e) {
                        ui.showErrorMsg(e);
                        validInput = false;
                    }
                } while (!validInput);

                try {
                    storeFile.saveFile(tasks);
                } catch (DukeException e) {
                    ui.showErrorMsg(e);
                }

                break;
            case "delete":
                if (tasks.isEmpty()) {
                    ui.showListEmptyMsg();
                } else {
                    ui.showListMsg();
                    ui.showTaskList(tasks);
                    do {
                        ui.showTaskDeleteAskMsg();
                        try {
                            userInput = ui.readInput();
                            TaskList deletedTasks = new TaskList();
                            int[] tasksArray = Parser.parse(userInput);
                            for (int index : tasksArray) {
                                try {
                                    if (index > tasks.size() || index <= 0) {
                                        throw new DukeException("This task #" + index + " does not exist.");
                                    }
                                    Task task = tasks.getTask(index);
                                    tasks.removeTask(index);
                                    deletedTasks.addTask(task);
                                } catch (DukeException e) {
                                    ui.showErrorMsg(e);
                                }
                            }
                            if (!deletedTasks.isEmpty()) {
                                ui.showTaskDeleteMsg();
                                ui.showTaskList(deletedTasks);
                            }
                            validInput = true;
                        } catch (DukeException e) {
                            ui.showErrorMsg(e);
                            validInput = false;
                        }
                    } while (!validInput);
                }

                try {
                    storeFile.saveFile(tasks);
                } catch (DukeException e) {
                    ui.showErrorMsg(e);
                }

                break;
            case "help":
                ui.showHelp();
                break;
            default:
                try {
                    throw new DukeException("Unrecognised Command :(, type 'help' for available commands.");
                } catch (DukeException e) {
                    ui.showErrorMsg(e);
                }
            }
        }
        ui.showByeMsg();
    }

    public static void main(String[] args) {
        new Duke(filePath).run();
    }
}
