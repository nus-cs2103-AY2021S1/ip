package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.CommandType;
import duke.InputParser;
import duke.Task;
import duke.TaskFinder;
import duke.DukeException;
import duke.ExceptionType;

import java.util.ArrayList;

/**
 * This class lists the appropriate list of tasks
 * as desired by the user of Duke chatbot.
 */
public class ListCommand extends Command{
    protected Ui ui;

    public ListCommand() {
        super();
        ui = new Ui();
    }

    /**
     * Executes the parsing of user input and
     * returns the required list of tasks.
     *
     * @param userTasks list of user tasks.
     * @param input input from user.
     * @return response after execution of command.
     */
    public String execute(TaskList userTasks, String input) {
        CommandType command = new InputParser().parseList(input);
        switch (command) {
        case LIST_ALL:
            response = ui.allTasksToString(userTasks.getTaskList());
            break;
        case LIST_ALL_DONE:
            ArrayList<Task> tasksDone = new TaskFinder()
                    .findAllDone(userTasks.getTaskList());
            response = ui.allTasksDoneToString(tasksDone);
            break;
        case LIST_ALL_NOT_DONE:
            ArrayList<Task> tasksNotDone = new TaskFinder()
                    .findAllNotDone(userTasks.getTaskList());
            response = ui.allTasksNotDoneToString(tasksNotDone);
            break;
        case LIST_TODOS:
            ArrayList<Task> toDos = new TaskFinder()
                    .findToDos(userTasks.getTaskList());
            response = ui.toDosToString(toDos);
            break;
        case LIST_TODOS_DONE:
            ArrayList<Task> toDosDone = new TaskFinder()
                    .findToDosDone(userTasks.getTaskList());
            response = ui.toDosDoneToString(toDosDone);
            break;
        case LIST_TODOS_NOT_DONE:
            ArrayList<Task> toDosNotDone = new TaskFinder()
                    .findToDosNotDone(userTasks.getTaskList());
            response = ui.toDosNotDoneToString(toDosNotDone);
            break;
        case LIST_DEADLINES:
            ArrayList<Task> deadlines = new TaskFinder()
                    .findDeadlines(userTasks.getTaskList());
            response = ui.deadlinesToString(deadlines);
            break;
        case LIST_DEADLINES_DONE:
            ArrayList<Task> deadlinesDone = new TaskFinder()
                    .findDeadlinesDone(userTasks.getTaskList());
            response = ui.deadlinesDoneToString(deadlinesDone);
            break;
        case LIST_DEADLINES_NOT_DONE:
            ArrayList<Task> deadlinesNotDone = new TaskFinder()
                    .findDeadlinesNotDone(userTasks.getTaskList());
            response = ui.deadlinesNotDoneToString(deadlinesNotDone);
            break;
        case LIST_EVENTS:
            ArrayList<Task> events = new TaskFinder()
                    .findEvents(userTasks.getTaskList());
            response = ui.eventsToString(events);
            break;
        case LIST_EVENTS_DONE:
            ArrayList<Task> eventsDone = new TaskFinder()
                    .findEventsDone(userTasks.getTaskList());
            response = ui.eventsDoneToString(eventsDone);
            break;
        case LIST_EVENTS_NOT_DONE:
            ArrayList<Task> eventsNotDone = new TaskFinder()
                    .findEventsNotDone(userTasks.getTaskList());
            response = ui.eventsNotDoneToString(eventsNotDone);
            break;
        case LIST_BY_KEYWORD:
            String keyword = input.split("find ")[1];
            ArrayList<Task> filteredTasks = new TaskFinder()
                    .findByKeyword(userTasks.getTaskList(), keyword);
            response = ui.filteredTasksByKeywordToString(filteredTasks, keyword);
            break;
        default:
            response = ui.errorMessage(
                    new DukeException("", ExceptionType.INVALID_COMMAND)
            );
        }
        return response;
    }
}