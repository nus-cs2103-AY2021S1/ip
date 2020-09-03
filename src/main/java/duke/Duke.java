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
    }

    public Duke() {
        this(filePath);
        isGui = true;
    }

    /**
     * Main execution of Duke Chat bot for CLI
     */
    public void run() {
        ui.printMsg(ui.startupMsg());
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

    /**
     * Main execution of Duke Chat bot for GUI
     */
    public void runGui() {
        String[] GuiInputArray;
        String[] userInputArray;

        userInput = ui.readInput(isGui);
        GuiInputArray = Parser.parseCommand(userInput);
        switch (GuiInputArray[0]) {
        case "bye":
            ui.setGuiOutput(ui.showByeMsg());
            hasExit = true;
            break;
        case "list":
            if (tasks.isEmpty()) {
                ui.setGuiOutput(ui.showListEmptyMsg());
            } else {
                ui.setGuiOutput(ui.showListMsg() + "\n" + ui.showTaskList(tasks));
            }
            break;
        case "done":
            if (tasks.isEmpty()) {
                ui.setGuiOutput(ui.showListEmptyMsg());
            } else {
                try {
                    if (GuiInputArray.length <= 1) {
                        throw new DukeException("Yo! Enter the task number(s).");
                    }
                    userInput = GuiInputArray[1];
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
                            ui.setGuiOutput(ui.showErrorMsg(e));
                        }
                    }
                    if (!doneList.isEmpty()) {
                        ui.setGuiOutput(ui.showListDoneMsg() + "\n" + ui.showTaskList(doneList));
                    }
                } catch (DukeException e) {
                    ui.setGuiOutput(ui.showErrorMsg(e));
                }
            }

            try {
                storeFile.saveFile(tasks);
            } catch (DukeException e) {
                ui.setGuiOutput(ui.showErrorMsg(e));
            }

            break;
        case "find":
            if (tasks.isEmpty()) {
                ui.setGuiOutput(ui.showListEmptyMsg());
            } else {
                try {
                    if (GuiInputArray.length <= 1) {
                        throw new DukeException("Yo! Enter a keyword.");
                    }
                    userInput = GuiInputArray[1];
                    if (userInput.equals("")) {
                        throw new DukeException("Yo! Enter a keyword.");
                    }
                    TaskList foundTasks;
                    foundTasks = tasks.findTasks(userInput);
                    if (!foundTasks.isEmpty()) {
                        ui.setGuiOutput(ui.showFoundMsg(userInput) + "\n" + ui.showTaskList(foundTasks));
                    } else {
                        ui.setGuiOutput(ui.showNotFoundMsg(userInput));
                    }
                } catch (DukeException e) {
                    ui.setGuiOutput(ui.showErrorMsg(e));
                }
            }
            break;
        case "todo":
            try {
                if (GuiInputArray.length <= 1) {
                    throw new DukeException("Yo! Task details are missing.");
                }
                userInput = GuiInputArray[1];
                if (userInput.equals("")) {
                    throw new DukeException("Yo! Task details are missing.");
                }
                Task toDo = new Todo(userInput);
                tasks.addTask(toDo);
                ui.setGuiOutput(ui.showTaskAddedMsg(toDo));
            } catch (DukeException e) {
                ui.setGuiOutput(ui.showErrorMsg(e));
            }

            try {
                storeFile.saveFile(tasks);
            } catch (DukeException e) {
                ui.setGuiOutput(ui.showErrorMsg(e));
            }

            break;
        case "deadline":
            try {
                if (GuiInputArray.length <= 1) {
                    throw new DukeException("Yo! Command Syntax Error. '<Details> /by or /at <dd/MM/yy [HH:MM]>'");
                }
                userInput = GuiInputArray[1];
                userInputArray = Parser.parseDetails(userInput);
                Task deadLine = new Deadline(userInputArray[0], userInputArray[1]);
                tasks.addTask(deadLine);
                ui.setGuiOutput(ui.showTaskAddedMsg(deadLine));
            } catch (DukeException e) {
                ui.setGuiOutput(ui.showErrorMsg(e));
            }

            try {
                storeFile.saveFile(tasks);
            } catch (DukeException e) {
                ui.setGuiOutput(ui.showErrorMsg(e));
            }

            break;
        case "event":
            try {
                if (GuiInputArray.length <= 1) {
                    throw new DukeException("Yo! Command Syntax Error. '<Details> /by or /at <dd/MM/yy [HH:MM]>'");
                }
                userInput = GuiInputArray[1];
                userInputArray = Parser.parseDetails(userInput);
                Task event = new Event(userInputArray[0], userInputArray[1]);
                tasks.addTask(event);
                ui.setGuiOutput(ui.showTaskAddedMsg(event));
            } catch (DukeException e) {
                ui.setGuiOutput(ui.showErrorMsg(e));
            }

            try {
                storeFile.saveFile(tasks);
            } catch (DukeException e) {
                ui.setGuiOutput(ui.showErrorMsg(e));
            }

            break;
        case "delete":
            if (tasks.isEmpty()) {
                ui.setGuiOutput(ui.showListEmptyMsg());
            } else {
                try {
                    if (GuiInputArray.length <= 1) {
                        throw new DukeException("Yo! Enter the task numbers(s).");
                    }
                    userInput = GuiInputArray[1];
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
                            ui.setGuiOutput(ui.showErrorMsg(e));
                        }
                    }
                    if (!deletedTasks.isEmpty()) {
                        ui.setGuiOutput(ui.showTaskDeleteMsg() + "\n" + ui.showTaskList(deletedTasks));
                    }
                } catch (DukeException e) {
                    ui.setGuiOutput(ui.showErrorMsg(e));
                }
            }

            try {
                storeFile.saveFile(tasks);
            } catch (DukeException e) {
                ui.setGuiOutput(ui.showErrorMsg(e));
            }

            break;
        case "help":
            ui.setGuiOutput(ui.showHelp());
            break;
        default:
            try {
                throw new DukeException("Unrecognised Command :(, type 'help' for available commands.");
            } catch (DukeException e) {
                ui.setGuiOutput(ui.showErrorMsg(e));
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
        return ui.getGuiOutput();
    }

    public boolean hasExit() {
        return this.hasExit;
    }
}
