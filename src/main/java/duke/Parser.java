package duke;

import duke.exceptions.*;

public class Parser {
    // constant SPACE and LINE for format purposes
    private static final String SPACE = "     ";
    private static final String INDENT = "      ";
    private static final String MORE_INDENT = "        ";

    /**
     * Returns formatted string, adding SPACE and LINE.
     *
     * @param input string to be formatted
     * @return formatted string
     */
    public static String format(String input) {
        return INDENT + input + "\n";
    }

    // command split indicators
    private final int PRIOR = 0;
    private final int EXTRA = 1;

    // current command: the whole line
    private String command;

    /**
     * Constructor of Parser.
     * @param command current command
     */
    Parser(String command) {
        this.command = command;
    }

    /**
     * Returns the bye message called by user input "bye".
     *
     * @return formatted bye message
     */
    private String handleBye() {
        String messageBye = "Bye. Hope to see you again soon!";
        return format(messageBye);
    }

    /**
     * Returns the list message called by user input "list".
     *
     * @param lst the task list to be handled
     * @return formatted list message
     */
    private String handleList(TaskList lst) {
        String messageList = " Here are the task(s) in your list:";
        return formatList(taskPrint(lst), messageList);
    }

    /**
     * Returns a raw list without any formatting.
     *
     * @param lst the task list to be printed
     * @return a raw list string
     */
    private String taskPrint(TaskList lst) {
        StringBuilder listContent = new StringBuilder();
        int counter = 1;
        for (Task task : lst.getLst()) {
            listContent.append(INDENT).append(counter).append(".")
                    .append(task.print()).append("\n");
            counter++;
        }
        return listContent.toString();
    }

    /**
     * Returns a formatted list message.
     *
     * @param content the raw list
     * @param message the opening statement for print a list
     * @return a formatted string of "content"
     */
    private String formatList(String content, String message) {
        return SPACE + message + "\n" + content;
    }

    /**
     * Returns a response message of complicated commands.
     *
     * @param lst the task list
     * @param command the command to be processed
     * @return a formatted string as user input response
     */
    private String handleComplicatedCommands(TaskList lst, String command) {
        if (!isValid(command)) {
            return format(new UnknownCommandException().toString());
        }
        String type = command.split(" ")[PRIOR];
        String response;
        switch (type) {
        case "done":
            response = handleDone(lst, command);
            break;
        case "delete":
            response = handleDelete(lst, command);
            break;
        case "find":
            response = handleFind(lst, command);
            break;
        case "todo":
            response = handleAdd(lst, command, TaskType.T);
            break;
        case "deadline":
            response = handleAdd(lst, command, TaskType.D);
            break;
        case "event":
            response = handleAdd(lst, command, TaskType.E);
            break;
        default:
            return format(new UnknownCommandException().toString());
        }

        return response;
    }

    /**
     * Returns boolean value indicating whether the command is valid.
     * The command is valid if it consists of COMMAND_TYPE + " " + EXTRA_MESSAGE
     *
     * @param command the command to be processed
     * @return a boolean value indicating whether the command is valid
     */
    private boolean isValid(String command) {
        try {
            //noinspection unused
            String type = command.split(" ")[PRIOR];
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
        return true;
    }

    /**
     * Returns a response string for "done" command.
     *
     * @param lst the task list
     * @param command the command to be processed
     * @return a response string for "done" command
     */
    private String handleDone(TaskList lst, String command) {
        String extraCommand;
        try {
            extraCommand = command.split(" ", 2)[EXTRA];
        } catch (IndexOutOfBoundsException ex) {
            return format(new DoneEmptyBodyException().toString());
        }

        int index = Integer.parseInt(extraCommand) - 1;
        Task task;

        try {
            task = lst.get(index);
        } catch (IndexOutOfBoundsException ex) {
            return format(new InvalidDoneException().toString());
        }

        assert index > 0;
        assert index < lst.size();

        if (!task.getStatus()) {
            task.setDone();
            TaskList.incrementNumberOfDoneTasks();
        }
        String messageMarked = "Nice! I've marked this task as done:\n";
        return format(messageMarked + MORE_INDENT + task.print());
    }

    /**
     * Returns a response string for "delete" command.
     *
     * @param lst the task list
     * @param command the command to be processed
     * @return a response string for "delete" command
     */
    private String handleDelete(TaskList lst, String command) {
        String extraCommand;
        try {
            extraCommand = command.split(" ", 2)[EXTRA];
        } catch (IndexOutOfBoundsException ex) {
            return format(new DeleteEmptyBodyException().toString());
        }

        int index = Integer.parseInt(extraCommand) - 1;
        Task task;

        try {
            task = lst.get(index);
        } catch (IndexOutOfBoundsException ex) {
            return format(new InvalidDeletionException().toString());
        }

        assert index > 0;
        assert index < lst.size();

        lst.delete(index);

        String messageDelete = "Noted. I've removed this task:\n";
        return format(messageDelete
                + MORE_INDENT + task.print() + getListCountMessage(lst));

    }

    /**
     * Returns a string listing all tasks in the task list.
     *
     * @param lst the task list
     * @return a string listing all tasks in lst
     */
    private String getListCountMessage(TaskList lst) {
        return "\n" + INDENT + "Now you have " + lst.size()
                + " task(s) in the list.";
    }

    /**
     * Returns a response string
     * listing all tasks containing the searching string.
     *
     * @param lst the task list
     * @param command the command to be processed
     * @return a response string for "find" command
     */
    private String handleFind(TaskList lst, String command) {
        String keyword;
        try {
            keyword = command.split(" ", 2)[EXTRA];
        } catch (IndexOutOfBoundsException ex) {
            return format(new FindEmptyBodyException().toString());
        }

        TaskList subList = new TaskList();

        // check whether extraCommand is a subString of any task message
        for (Task task : lst.getLst()) {
            if (task.getMessage().contains(keyword)) {
                subList.add(task);
            }
        }

        String messageMatching = "Here are the matching task(s) in your list:";
        return formatList(taskPrint(subList), messageMatching);
    }

    /**
     * Returns a response string for a new task command.
     *
     * @param lst the task list
     * @param command the command to be processed
     * @param taskType task type, T or D or E
     * @return a response string for a new task command
     */
    private String handleAdd(TaskList lst, String command, TaskType taskType) {
        String description;
        try {
            description = command.split(" ", 2)[EXTRA];
        } catch (IndexOutOfBoundsException ex) {
            switch (taskType) {
            case T:
                return format(new TodoEmptyBodyException().toString());
            case D:
                return format(new DeadlineEmptyBodyException().toString());
            case E:
                return format(new EventEmptyBodyException().toString());
            default:
            }
            return format(new UnknownCommandException().toString());
        }

        lst.addOfType(description, taskType);
        // a message to be printed when invoking a todo, event, or deadline
        String messageAdded = "Got it. I've added this task:\n";
        return format(messageAdded + MORE_INDENT
                + getMostRecentTask(lst).print() + getListCountMessage(lst));
    }

    /**
     * Returns the most recent task in the task list.
     *
     * @param lst the task list
     * @return the most recent task
     */
    private Task getMostRecentTask(TaskList lst) {
        return lst.get(lst.size() - 1);
    }

    /**
     * Returns list of commands available.
     *
     * @return a help message of list of commands available
     */
    private String handleHelp() {
        return Ui.listOfCommands();
    }

    /**
     * Returns respond message to be printed generated from user input.
     *
     * @param lst the task list
     * @return respond message by Duke
     */
    public String getRespond(TaskList lst) {
        String messageRespond;
        switch (command) {
        case "bye":
            messageRespond = handleBye();
            break;
        case "list":
            messageRespond = handleList(lst);
            break;
        case "help":
            messageRespond = handleHelp();
            break;
        default:
            messageRespond = handleComplicatedCommands(lst, command);
            break;
        }
        return messageRespond;
    }
}


