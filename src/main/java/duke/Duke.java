package duke;

import java.util.ArrayList;

public class Duke {

    private Ui ui;
    private Storage storage;
    private InputParser inputParser;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.inputParser = new InputParser();
    }

    public void run() throws DukeException {
        // Print Duke welcome message
        ui.welcomeMessage();

        String input;

        // Initialise TaskList object to store tasks from user
        TaskList userTasks = new TaskList(storage.readFromFile());
        CommandType command = CommandType.HELP;
        // Start chat
        while (command != CommandType.BYE) {
            Task t;
            String[] inputSplit;
            String description;

            // Get input from user
            System.out.println(ui.getUserPrompt());
            input = inputParser.getInput();
            System.out.println();
            if (input.equals("get all events")) {
                ArrayList<Task> events = new TaskFinder().findEvents(userTasks.getTaskList());
                ui.printEvents(events);
                continue;
            }

            command = inputParser.parseInput(input);

            switch (command) {
            case HELP:
                ui.printAvailableCommands();
                continue;
            case TODO:
                description = input.substring(4);
                t = new ToDo(description);
                userTasks.addTask(t);
                storage.saveToFile(userTasks.getTaskList());
                ui.printTaskAddedMessage(t, userTasks.getTaskListSize());
                continue;
            case DEADLINE:
                inputSplit = input.split(" /by ");
                String by = inputSplit[1];
                description = inputSplit[0].substring(8);
                t = new Deadline(description, by);
                userTasks.addTask(t);
                storage.saveToFile(userTasks.getTaskList());
                ui.printTaskAddedMessage(t, userTasks.getTaskListSize());
                continue;
            case EVENT:
                inputSplit = input.split(" /at ");
                String at = inputSplit[1].split(" ")[0];
                String timeRange = inputSplit[1].split(" ")[1];
                description = inputSplit[0].substring(5);
                t = new Event(description, at, timeRange);
                userTasks.addTask(t);
                storage.saveToFile(userTasks.getTaskList());
                ui.printTaskAddedMessage(t, userTasks.getTaskListSize());
                continue;
            case LIST_ALL:
                ui.printAllTasks(userTasks.getTaskList());
                continue;
            case LIST_ALL_DONE:
                ArrayList<Task> tasksDone = new TaskFinder()
                        .findAllDone(userTasks.getTaskList());
                ui.printAllTasksDone(tasksDone);
                continue;
            case LIST_ALL_NOT_DONE:
                ArrayList<Task> tasksNotDone = new TaskFinder()
                        .findAllNotDone(userTasks.getTaskList());
                ui.printAllTasksNotDone(tasksNotDone);
                continue;
            case LIST_TODOS:
                ArrayList<Task> toDos = new TaskFinder()
                        .findToDos(userTasks.getTaskList());
                ui.printToDos(toDos);
                continue;
            case LIST_TODOS_DONE:
                ArrayList<Task> toDosDone = new TaskFinder()
                        .findToDosDone(userTasks.getTaskList());
                ui.printToDosDone(toDosDone);
                continue;
            case LIST_TODOS_NOT_DONE:
                ArrayList<Task> toDosNotDone = new TaskFinder()
                        .findToDosNotDone(userTasks.getTaskList());
                ui.printToDosNotDone(toDosNotDone);
                continue;
            case LIST_DEADLINES:
                ArrayList<Task> deadlines = new TaskFinder()
                        .findDeadlines(userTasks.getTaskList());
                ui.printDeadlines(deadlines);
                continue;
            case LIST_DEADLINES_DONE:
                ArrayList<Task> deadlinesDone = new TaskFinder()
                        .findDeadlinesDone(userTasks.getTaskList());
                ui.printDeadlinesDone(deadlinesDone);
                continue;
            case LIST_DEADLINES_NOT_DONE:
                ArrayList<Task> deadlinesNotDone = new TaskFinder()
                        .findDeadlinesNotDone(userTasks.getTaskList());
                ui.printDeadlinesNotDone(deadlinesNotDone);
                continue;
            case LIST_EVENTS:
                ArrayList<Task> events = new TaskFinder()
                        .findEvents(userTasks.getTaskList());
                ui.printEvents(events);
                continue;
            case LIST_EVENTS_DONE:
                ArrayList<Task> eventsDone = new TaskFinder()
                        .findEventsDone(userTasks.getTaskList());
                ui.printEventsDone(eventsDone);
                continue;
            case LIST_EVENTS_NOT_DONE:
                ArrayList<Task> eventsNotDone = new TaskFinder()
                        .findEventsNotDone(userTasks.getTaskList());
                ui.printEventsNotDone(eventsNotDone);
                continue;
            case DONE:
                // Get the index stated after "done" by parsing the string
                int index = Integer.parseInt(input.substring(5)) - 1;

                // Mark item as done
                try {
                    if (index >= userTasks.getTaskListSize()) {
                        throw new DukeException("", ExceptionType.INDEX_OUT_OF_BOUNDS);
                    } else {
                        userTasks.markTaskAsDone(index);
                        ui.printMarkAsDoneMessage(userTasks.getTask(index));
                    }

                    // Update Tasklist.txt after marking task as done
                    storage.saveToFile(userTasks.getTaskList());

                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                }
                System.out.println();
                continue;
            case DELETE:
                // Get the index stated after "delete" by parsing the string
                index = Integer.parseInt(input.substring(7)) - 1;

                // Delete item
                try {
                    if (index >= userTasks.getTaskListSize()) {
                        throw new DukeException("", ExceptionType.INDEX_OUT_OF_BOUNDS);
                    } else {
                        ui.printTaskDeletedMessage(userTasks.getTask(index));
                        userTasks.deleteTask(index);
                    }

                    // Update Tasklist.txt after removing task
                    storage.saveToFile(userTasks.getTaskList());

                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                }
                System.out.println();
                continue;
            case BYE:
                ui.printByeMessage();
                continue;
            case INVALID_IS_EMPTY:
                try {
                    throw new DukeException("", ExceptionType.EMPTY_INPUT);
                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                    continue;
                }
            case INVALID_COMMAND:
                try {
                    throw new DukeException("", ExceptionType.INVALID_COMMAND);
                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                    continue;
                }
            case INVALID_EMPTY_DESCRIPTION:
                try {
                    throw new DukeException("", ExceptionType.EMPTY_DESCRIPTION);
                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                    continue;
                }
            case INVALID_DEADLINE_NO_BY:
                try {
                    throw new DukeException("", ExceptionType.DEADLINE_NO_BY);
                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                    continue;
                }
            case INVALID_EVENT_NO_START_END:
                try {
                    throw new DukeException("", ExceptionType.EVENT_NO_START_END);
                } catch (DukeException ex) {
                    System.out.print(ui.getServantSpeak());
                    ui.printError(ex);
                    continue;
                }
            }

        }
    }
}