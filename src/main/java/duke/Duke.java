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
    private boolean isGui = false;
    private boolean hasExit = false;
    private String userInput;

    /**
     * Initialize Duke, UI and load saved file into a TaskList object. If save file is empty, a new
     * TaskList object will be created.
     *
     * @param filePath hard-coded File Path of the saved file
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storeFile = new Storage(filePath);
        try {
            tasks = new TaskList(storeFile.loadFile());
        } catch (DukeException e) {
            ui.showErrorMsg(e);
            tasks = new TaskList();
        }
        ui.printMsg(ui.startupMsg());
    }

    public Duke() {
        this(filePath);
        isGui = true;
    }

    /**
     * Main execution of Duke Chat bot
     */
    public void run() {
        String[] userInputArray;
        boolean validInput;

        while (true) {
            userInput = ui.readInput(isGui);
            if (userInput.equals("bye")) {
                break;
            }
            switch (userInput) {
            case "list":
                if (tasks.isEmpty()) {
                    ui.printMsg(ui.showListEmptyMsg());
                } else {
                    ui.printMsg(ui.showListMsg());
                    ui.printMsg(ui.showTaskList(tasks));
                }
                break;
            case "done":
                if (tasks.isEmpty()) {
                    ui.printMsg(ui.showListEmptyMsg());
                } else {
                    ui.printMsg(ui.showListMsg());
                    ui.printMsg(ui.showTaskList(tasks));
                    ;
                    do {
                        ui.printMsg(ui.showListDoneAskMsg());
                        try {
                            userInput = ui.readInput(isGui);
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
                                    ui.printMsg(ui.showErrorMsg(e));
                                }
                            }
                            if (!doneList.isEmpty()) {
                                ui.printMsg(ui.showListDoneMsg());
                                ui.printMsg(ui.showTaskList(doneList));
                            }
                            validInput = true;
                        } catch (DukeException e) {
                            validInput = false;
                            ui.printMsg(ui.showErrorMsg(e));
                        }
                    } while (!validInput);
                }

                try {
                    storeFile.saveFile(tasks);
                } catch (DukeException e) {
                    ui.printMsg(ui.showErrorMsg(e));
                }

                break;
            case "find":
                if (tasks.isEmpty()) {
                    ui.printMsg(ui.showListEmptyMsg());
                } else {
                    do {
                        try {
                            ui.printMsg(ui.showFindPromptMsg());
                            userInput = ui.readInput(isGui);
                            if (userInput.equals("")) {
                                throw new DukeException("Yo! Enter a keyword.");
                            }
                            TaskList foundTasks;
                            foundTasks = tasks.findTasks(userInput);
                            if (!foundTasks.isEmpty()) {
                                ui.printMsg(ui.showFoundMsg(userInput));
                                ui.printMsg(ui.showTaskList(foundTasks));
                            } else {
                                ui.printMsg(ui.showNotFoundMsg(userInput));
                            }
                            validInput = true;
                        } catch (DukeException e) {
                            validInput = false;
                            ui.printMsg(ui.showErrorMsg(e));
                        }
                    } while (!validInput);
                }
                break;
            case "todo":
                do {
                    ui.printMsg(ui.showTaskAddAskMsg());
                    try {
                        userInput = ui.readInput(isGui);
                        if (userInput.equals("")) {
                            throw new DukeException("Yo! Task details are missing.");
                        }
                        Task toDo = new Todo(userInput);
                        tasks.addTask(toDo);
                        ui.printMsg(ui.showTaskAddedMsg(toDo));
                        validInput = true;
                    } catch (DukeException e) {
                        validInput = false;
                        ui.printMsg(ui.showErrorMsg(e));
                    }
                } while (!validInput);

                try {
                    storeFile.saveFile(tasks);
                } catch (DukeException e) {
                    ui.printMsg(ui.showErrorMsg(e));
                }

                break;
            case "deadline":
                do {
                    ui.printMsg(ui.showTaskAddAskMsg());
                    try {
                        userInput = ui.readInput(isGui);
                        userInputArray = Parser.parseDetails(userInput);
                        Task deadLine = new Deadline(userInputArray[0], userInputArray[1]);
                        tasks.addTask(deadLine);
                        ui.printMsg(ui.showTaskAddedMsg(deadLine));
                        validInput = true;

                    } catch (DukeException e) {
                        ui.printMsg(ui.showErrorMsg(e));
                        validInput = false;
                    }
                } while (!validInput);

                try {
                    storeFile.saveFile(tasks);
                } catch (DukeException e) {
                    ui.printMsg(ui.showErrorMsg(e));
                }

                break;
            case "event":
                do {
                    ui.printMsg(ui.showTaskAddAskMsg());
                    try {
                        userInput = ui.readInput(isGui);
                        userInputArray = Parser.parseDetails(userInput);
                        Task event = new Event(userInputArray[0], userInputArray[1]);
                        tasks.addTask(event);
                        ui.printMsg(ui.showTaskAddedMsg(event));
                        validInput = true;

                    } catch (DukeException e) {
                        ui.printMsg(ui.showErrorMsg(e));
                        validInput = false;
                    }
                } while (!validInput);

                try {
                    storeFile.saveFile(tasks);
                } catch (DukeException e) {
                    ui.printMsg(ui.showErrorMsg(e));
                }

                break;
            case "delete":
                if (tasks.isEmpty()) {
                    ui.printMsg(ui.showListEmptyMsg());
                } else {
                    ui.printMsg(ui.showListMsg());
                    ui.printMsg(ui.showTaskList(tasks));
                    do {
                        ui.printMsg(ui.showTaskDeleteAskMsg());
                        try {
                            userInput = ui.readInput(isGui);
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
                                    ui.printMsg(ui.showErrorMsg(e));
                                }
                            }
                            if (!deletedTasks.isEmpty()) {
                                ui.printMsg(ui.showTaskDeleteMsg());
                                ui.printMsg(ui.showTaskList(deletedTasks));
                            }
                            validInput = true;
                        } catch (DukeException e) {
                            ui.printMsg(ui.showErrorMsg(e));
                            validInput = false;
                        }
                    } while (!validInput);
                }

                try {
                    storeFile.saveFile(tasks);
                } catch (DukeException e) {
                    ui.printMsg(ui.showErrorMsg(e));
                }

                break;
            case "help":
                ui.printMsg(ui.showHelp());
                break;
            default:
                try {
                    throw new DukeException("Unrecognised Command :(, type 'help' for available commands.");
                } catch (DukeException e) {
                    ui.printMsg(ui.showErrorMsg(e));
                }
            }
        }
        ui.printMsg(ui.showByeMsg());
    }

    public void runGui() {
        String[] userInputArray;
        boolean validInput;

        userInput = ui.readInput(isGui);
        switch (userInput) {
        case "bye":
            ui.printMsg(ui.showByeMsg());
            break;
        case "list":
            if (tasks.isEmpty()) {
                ui.printMsg(ui.showListEmptyMsg());
            } else {
                ui.printMsg(ui.showListMsg());
                ui.printMsg(ui.showTaskList(tasks));
            }
            break;
        case "done":
            if (tasks.isEmpty()) {
                ui.printMsg(ui.showListEmptyMsg());
            } else {
                ui.printMsg(ui.showListMsg());
                ui.printMsg(ui.showTaskList(tasks));
                ;
                do {
                    ui.printMsg(ui.showListDoneAskMsg());
                    try {
                        userInput = ui.readInput(isGui);
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
                                ui.printMsg(ui.showErrorMsg(e));
                            }
                        }
                        if (!doneList.isEmpty()) {
                            ui.printMsg(ui.showListDoneMsg());
                            ui.printMsg(ui.showTaskList(doneList));
                        }
                        validInput = true;
                    } catch (DukeException e) {
                        validInput = false;
                        ui.printMsg(ui.showErrorMsg(e));
                    }
                } while (!validInput);
            }

            try {
                storeFile.saveFile(tasks);
            } catch (DukeException e) {
                ui.printMsg(ui.showErrorMsg(e));
            }

            break;
        case "find":
            if (tasks.isEmpty()) {
                ui.printMsg(ui.showListEmptyMsg());
            } else {
                do {
                    try {
                        ui.printMsg(ui.showFindPromptMsg());
                        userInput = ui.readInput(isGui);
                        if (userInput.equals("")) {
                            throw new DukeException("Yo! Enter a keyword.");
                        }
                        TaskList foundTasks;
                        foundTasks = tasks.findTasks(userInput);
                        if (!foundTasks.isEmpty()) {
                            ui.printMsg(ui.showFoundMsg(userInput));
                            ui.printMsg(ui.showTaskList(foundTasks));
                        } else {
                            ui.printMsg(ui.showNotFoundMsg(userInput));
                        }
                        validInput = true;
                    } catch (DukeException e) {
                        validInput = false;
                        ui.printMsg(ui.showErrorMsg(e));
                    }
                } while (!validInput);
            }
            break;
        case "todo":
            do {
                ui.printMsg(ui.showTaskAddAskMsg());
                try {
                    userInput = ui.readInput(isGui);
                    if (userInput.equals("")) {
                        throw new DukeException("Yo! Task details are missing.");
                    }
                    Task toDo = new Todo(userInput);
                    tasks.addTask(toDo);
                    ui.printMsg(ui.showTaskAddedMsg(toDo));
                    validInput = true;
                } catch (DukeException e) {
                    validInput = false;
                    ui.printMsg(ui.showErrorMsg(e));
                }
            } while (!validInput);

            try {
                storeFile.saveFile(tasks);
            } catch (DukeException e) {
                ui.printMsg(ui.showErrorMsg(e));
            }

            break;
        case "deadline":
            do {
                ui.printMsg(ui.showTaskAddAskMsg());
                try {
                    userInput = ui.readInput(isGui);
                    userInputArray = Parser.parseDetails(userInput);
                    Task deadLine = new Deadline(userInputArray[0], userInputArray[1]);
                    tasks.addTask(deadLine);
                    ui.printMsg(ui.showTaskAddedMsg(deadLine));
                    validInput = true;

                } catch (DukeException e) {
                    ui.printMsg(ui.showErrorMsg(e));
                    validInput = false;
                }
            } while (!validInput);

            try {
                storeFile.saveFile(tasks);
            } catch (DukeException e) {
                ui.printMsg(ui.showErrorMsg(e));
            }

            break;
        case "event":
            do {
                ui.printMsg(ui.showTaskAddAskMsg());
                try {
                    userInput = ui.readInput(isGui);
                    userInputArray = Parser.parseDetails(userInput);
                    Task event = new Event(userInputArray[0], userInputArray[1]);
                    tasks.addTask(event);
                    ui.printMsg(ui.showTaskAddedMsg(event));
                    validInput = true;

                } catch (DukeException e) {
                    ui.printMsg(ui.showErrorMsg(e));
                    validInput = false;
                }
            } while (!validInput);

            try {
                storeFile.saveFile(tasks);
            } catch (DukeException e) {
                ui.printMsg(ui.showErrorMsg(e));
            }

            break;
        case "delete":
            if (tasks.isEmpty()) {
                ui.printMsg(ui.showListEmptyMsg());
            } else {
                ui.printMsg(ui.showListMsg());
                ui.printMsg(ui.showTaskList(tasks));
                do {
                    ui.printMsg(ui.showTaskDeleteAskMsg());
                    try {
                        userInput = ui.readInput(isGui);
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
                                ui.printMsg(ui.showErrorMsg(e));
                            }
                        }
                        if (!deletedTasks.isEmpty()) {
                            ui.printMsg(ui.showTaskDeleteMsg());
                            ui.printMsg(ui.showTaskList(deletedTasks));
                        }
                        validInput = true;
                    } catch (DukeException e) {
                        ui.printMsg(ui.showErrorMsg(e));
                        validInput = false;
                    }
                } while (!validInput);
            }

            try {
                storeFile.saveFile(tasks);
            } catch (DukeException e) {
                ui.printMsg(ui.showErrorMsg(e));
            }

            break;
        case "help":
            ui.printMsg(ui.showHelp());
            break;
        default:
            try {
                throw new DukeException("Unrecognised Command :(, type 'help' for available commands.");
            } catch (DukeException e) {
                ui.printMsg(ui.showErrorMsg(e));
            }
        }
    }

    /**
     * Duke is instantiated here.
     */
    public static void main(String[] args) {
        new Duke(filePath).run();
    }

    public String getResponse() {
        runGui();
        return "test";
    }

    public boolean hasExit() {
        return this.hasExit;
    }
}
