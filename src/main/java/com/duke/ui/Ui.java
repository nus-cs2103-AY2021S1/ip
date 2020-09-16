package com.duke.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.duke.exceptions.DukeException;
import com.duke.parser.Parser;
import com.duke.storage.Storage;
import com.duke.tasklist.TaskList;
import com.duke.tasks.Deadlines;
import com.duke.tasks.Events;
import com.duke.tasks.RecurringDeadlines;
import com.duke.tasks.RecurringEvents;
import com.duke.tasks.RecurringToDos;
import com.duke.tasks.Task;
import com.duke.tasks.ToDos;

/**
 * Deals with responding to the user.
 * Calls methods of other classes to save/pull entries into/from session-based storage.
 */
public class Ui {
    private final String ERROR_MESSAGE = "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(";
    private final String DUKE_SECTION = "\t____________________________________________________________";

    private TaskList taskList;
    private Scanner scanner = new Scanner(System.in);

    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    public Ui() {
        this.taskList = null;
    }

    private void generateCliReply(String reply) {
        System.out.println(DUKE_SECTION);
        System.out.println(reply);
        System.out.println(DUKE_SECTION);
    }

    private String replyEmptyList() {
        return "The tasklist is currently empty.";
    }

    /**
     * Prints all the instructions available for DukeBot.
     */
    private String handleHelpCommand() {
        return "The list of commands are of below:\n"
                + "list - List all available entries in the tasklist.\n"
                + "Delete Task: delete <TASK INDEX>\n"
                + "Mark Task as Done: done <TASK INDEX>\n"
                + "Find Task: find <WORD IN TASK DESCRIPTION>\n"
                + "\n"
                + "Add Tasks: \n"
                + "Deadline: deadline <TASK> /by <DATE IN D/MM/YYYY> <24H TIME FORMAT>\n"
                + "Event: event <TASK> /at <DATE IN D/MM/YYYY> <24H TIME FORMAT>\n"
                + "ToDo : todo <TASK>\n"
                + "\n"
                + "Add Recurring Tasks:\n"
                + "Recurring Deadline: recurring deadline <TASK> /by <D/MM/YYYY> <TIME> /<PERIOD>\n"
                + "Recurring Event: recurring event <TASK> /at <D/MM/YYYY> <TIME> /<PERIOD>\n"
                + "Recurring ToDo: recurring todo <TASK>\n"
                + "\n"
                + "Exit: bye";
    }

    /**
     * Prints entries stored in Duke.
     */
    private String handleListCommand() {
        assert this.taskList != null;

        if (this.taskList.size() == 0) {
            String reply = this.replyEmptyList();
            generateCliReply(reply);
            return reply;
        }

        String reply = generateTaskList();
        generateCliReply(reply);
        return reply;
    }

    private String generateTaskList() {
        String reply = "\tHere are the tasks in your list:";
        int counter = 1;
        for (int i = 0; i < this.taskList.size(); i++) {
            reply += "\n";
            reply += "\t" + counter + "." + this.taskList.getItem(i).toString();
            counter++;
        }
        return reply;
    }

    /**
     * Prints entries that match input keyword.
     *
     * @param input input keyword to match.
     */
    private String handleFindCommand(String input) {
        if (input.isBlank()) {
            return this.replyNoEmptyString(input);
        }

        assert input != null;
        assert !input.isBlank();

        List<Task> matchingTaskList = generateMatchingTaskList(input);
        if (matchingTaskList.size() == 0) {
            return this.replyNoTaskFound(input);
        } else {
            return this.replyTasksFound(matchingTaskList);
        }
    }

    private List<Task> generateMatchingTaskList(String input) {
        int len = this.taskList.size();
        ArrayList<Task> matchingTaskList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            Task task = this.taskList.getItem(i);
            String taskDescription = task.getTask();
            if (taskDescription.contains(input)) {
                matchingTaskList.add(task);
            }
        }
        return matchingTaskList;
    }

    private String replyNoEmptyString(String input) {
        String reply = "\tI'm sorry, please specify what you want to find!";
        generateCliReply(reply);
        return reply;
    }

    private String replyNoTaskFound(String input) {
        String reply = "\tI'm sorry, there are no tasks found with keyword " + input + ".";
        generateCliReply(reply);
        return reply;
    }

    /**
     * Lists entries that match input keyword.
     *
     * @param taskList list of tasks that match input keyword.
     */
    private String replyTasksFound(List<Task> taskList) {
        assert taskList != null;

        String reply = "\tHere are the matching tasks in your list:";
        for (Task task : taskList) {
            reply += "\n\t";
            reply += task.toString();
        }
        generateCliReply(reply);
        return reply;
    }

    private String handleByeCommand() {
        try {
            Storage.saveListToFile(this.taskList);
            String reply = replyBye();
            generateCliReply(reply);
            return reply;
        } catch (DukeException dukeException) {
            String reply = dukeException.getMessage();
            generateCliReply(reply);
            return reply;
        } finally {
            exit();
        }
    }

    private String replyBye() {
        return "\tBye. Hope to see you again soon!";
    }

    private String handleDoneCommand(int index) {
        try {
            this.taskList.setDone(index);
            String reply = replyDone(index);
            generateCliReply(reply);
            return reply;
        } catch (IndexOutOfBoundsException exception) {
            String reply = "\t\u2639 OOPS!!! I'm sorry, this task does not exist in your list!";
            generateCliReply(reply);
            return reply;
        }
    }

    private String replyDone(int index) {
        String reply = "\tNice! I've marked this task as done:\n";
        reply += "\t" + this.taskList.getItem(index).toString();
        return reply;
    }

    /**
     * Removes specified entry from Duke and prints reply.
     *
     * @param index index of entry to be removed.
     */
    private String handleRemoveCommand(int index) {
        try {
            Task task = this.taskList.remove(index);
            String reply = replyRemove(task);
            generateCliReply(reply);
            return reply;
        } catch (IndexOutOfBoundsException e) {
            String errorMessage = "\t\u2639 OOPS!!! I'm sorry, this task does not exist in your list!";
            generateCliReply(errorMessage);
            return errorMessage;
        }
    }

    /**
     * Adds Recurring task to Duke and give reply.
     * Example command is 'recurring todo Do Laundry /weekly'
     * Example command is 'recurring deadline Pay Bills /by 9/07/2019 1800 /monthly'
     * Example command is 'recurring event Wedding Anniversary /at 30/10/2020 1800 /yearly'
     *
     * @param input The input string for recurring task instruction.
     */
    private String handleRecurringTaskCommand(String input) {
        try {
            String instruction = input.split(" ", 2)[1];
            String taskType = instruction.split(" ", 2)[0];
            String task = instruction.split(" ", 2)[1].split(" /")[0];
            String intervalString = "";
            if (!taskType.equals("todo")) {
                task += " /" + instruction.split(" ", 2)[1].split(" /")[1];
                intervalString = instruction.split(" ", 2)[1].split(" /")[2];
            }
            return this.addRecurringTask(taskType, task, intervalString);
        } catch (DukeException dukeException) {
            String errorMessage = "\t\t" + dukeException.getMessage();
            generateCliReply(errorMessage);
            return errorMessage;
        }
    }

    /**
     * Adds Recurring Task entry into Duke and replies with response.
     *
     * @param taskType type of Task: event, todo, deadline.
     * @param task Description of task.
     * @param interval time interval. Examples include: weekly, monthly, yearly.
     */
    private String addRecurringTask(String taskType, String task, String interval) throws DukeException {
        assert this.taskList != null;

        String date = "";
        switch (taskType) {
        case ("todo"):
            this.taskList.addItem(new RecurringToDos(task, interval));
            break;
        case ("event"):
            String[] taskAndDateArr = Parser.splitTaskAndDate(task);
            task = taskAndDateArr[0];
            date = taskAndDateArr[1];
            taskList.addItem(new RecurringEvents(task, date, interval));
            break;
        case ("deadline"):
            taskAndDateArr = Parser.splitTaskAndDate(task);
            task = taskAndDateArr[0];
            date = taskAndDateArr[1];
            taskList.addItem(new RecurringDeadlines(task, date, interval));
            break;
        default:
            throw new DukeException(ERROR_MESSAGE);
        }
        String reply = generateAddListReply();
        generateCliReply(reply);
        return reply;
    }

    /**
     * Adds task to Duke and give reply.
     *
     * @param taskType type of task.
     * @param task details of the task.
     */
    private String handleTaskCommand(String taskType, String task) {
        try {
            switch (taskType) {
            case ("todo"):
            case ("deadline"):
            case ("event"):
                return this.addTask(taskType, task);
            default:
                throw new DukeException(ERROR_MESSAGE);
            }
        } catch (DukeException dukeException) {
            String errorMessage = "\t\t" + dukeException.getMessage();
            generateCliReply(errorMessage);
            return errorMessage;
        }
    }

    /**
     * Adds entry into Duke and replies with response.
     */
    private String addTask(String taskType, String task) throws DukeException {
        assert this.taskList != null;

        String date = "";
        switch (taskType) {
        case ("todo"):
            this.taskList.addItem(new ToDos(task));
            break;
        case ("event"):
            String[] taskAndDateArr = Parser.splitTaskAndDate(task);
            task = taskAndDateArr[0];
            date = taskAndDateArr[1];
            taskList.addItem(new Events(task, date));
            break;
        case ("deadline"):
            taskAndDateArr = Parser.splitTaskAndDate(task);
            task = taskAndDateArr[0];
            date = taskAndDateArr[1];
            taskList.addItem(new Deadlines(task, date));
            break;
        default:
            throw new DukeException(ERROR_MESSAGE);
        }

        String reply = generateAddListReply();
        generateCliReply(reply);
        return reply;
    }

    private String generateAddListReply() {
        String reply = "\tGot it. I've added this task:\n";
        reply += "\t\t" + this.taskList.getList().get(this.taskList.size() - 1).toString() + "\n";
        reply += "\tNow you have " + this.taskList.size() + " tasks in the list.";
        return reply;
    }


    private String replyRemove(Task task) {
        String reply = "\tNoted. I've removed this task:\n";
        reply += "\t\t" + task.toString() + "\n";
        reply += "\tNow you have " + this.taskList.size() + " tasks in the list.";
        return reply;
    }

    /**
     * Prints welcome message for Duke.
     */
    public String showWelcome() {
        String reply = "";
        reply += "Hello! I'm DukeBot\n";
        reply += "What can I do for you?";
        generateCliReply(reply);
        return reply;
    }

    /**
     * Initializes Duke with welcome message.
     */
    public String initialize() {
        return showWelcome();
    }

    /**
     * Tells user that persistent file failed to load.
     */
    public void showLoadingError() {
        System.out.println("File failed to load. Initializing new File...");
        initialize();
    }

    private static void exit() {
        System.exit(0);
    }

    /**
     * Listens from commands from user.
     * Possible commands include: "done 1", "delete 2", "bye", "list".
     * Possible event command: "event halloween party /at 2/12/2019 1800".
     * Possible deadline command: "deadline add comments /by 2/12/2019 1800".
     * Possible todo command: "todo read book".
     */
    public void listen() {
        String input = scanner.nextLine();
        handleCommand(input);
        this.listen();
    }

    /**
     * Listens from commands from user.
     * Possible commands include: "done 1", "delete 2", "bye", "list".
     * Possible event command: "event halloween party /at 2/12/2019 1800".
     * Possible deadline command: "deadline add comments /by 2/12/2019 1800".
     * Possible todo command: "todo read book".
     */
    public String listen(String input) {
        String res = handleCommand(input);
        return res;
    }

    private String handleCommand(String input) {
        try {
            if (Parser.isHelp(input)) {
                return handleHelpCommand();
            } else if (Parser.isDone(input)) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                return handleDoneCommand(index);
            } else if (Parser.isDelete(input)) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                return handleRemoveCommand(index);
            } else if (input.equals("bye")) {
                return handleByeCommand();
            } else if (input.equals("list")) {
                return handleListCommand();
            } else if (Parser.isFind(input)) {
                String item = input.split(" ", 2)[1];
                return handleFindCommand(item);
            } else if (Parser.isAddTask(input)) {
                //pull type of task and the task
                String taskType = Parser.getTaskType(input);
                String task = Parser.getTask(input);
                return this.handleTaskCommand(taskType, task);
            } else if (Parser.isRecurringTask(input)) {
                return handleRecurringTaskCommand(input);
            } else {
                throw new DukeException(ERROR_MESSAGE);
            }
        } catch (DukeException dukeException) {
            String errorMessage = "\t\t" + dukeException.getMessage();
            generateCliReply(errorMessage);
            return errorMessage;
        }
    }
}
