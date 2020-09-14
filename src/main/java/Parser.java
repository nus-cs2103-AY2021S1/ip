public class Parser {
    // constant SPACE and LINE for format purposes
    public static String SPACE = "     ";
    public static String INDENT = "      ";
    public static String MORE_INDENT = "        ";
    public static String LINE = "_____________________________________________\n";

    /**
     * Returns formatted string, adding SPACE and LINE
     *
     * @param input string to be formatted
     * @return formatted string
     */
    public static String format(String input) {
        return SPACE + LINE + INDENT + input + "\n" + SPACE + LINE;
    }

    // a message to be printed when invoking a toodoo, event, or deadline
    private final String messageAdded = "Got it. I've added this task:\n";

    // command split indicators
    private final int PRIOR = 0;
    private final int EXTRA = 1;

    // current command: the whole line
    private String command;

    /**
     * Constructor of Parser
     *
     * @param command current command
     */
    Parser(String command) {
        this.command = command;
    }

    /**
     * Returns the bye message called by user input "bye"
     *
     * @return formatted bye message
     */
    private String handleBye() {
        String messageBye = "Bye. Hope to see you again soon!";
        return format(messageBye);
    }

    /**
     * Returns the list message called by user input "list"
     *
     * @return formatted list message
     */
    private String handleList(TaskList lst) {
        String messageList = " Here are the task(s) in your list:";
        return formatList(taskPrint(lst), messageList);
    }

    /**
     * Returns a raw list without any formatting
     *
     * @return a raw list string
     */
    private String taskPrint(TaskList lst) {
        StringBuilder listContent = new StringBuilder();
        int counter = 1;
        for (Task task : lst.getLst()) {
            listContent.append(INDENT).append(counter).append(".").append(task.print()).append("\n");
            counter++;
        }
        return listContent.toString();
    }

    /**
     * Returns a formatted list message
     *
     * @param content the raw list
     * @return a formatted string of "content"
     */
    private String formatList(String content, String message) {
        return SPACE + LINE + SPACE + message + "\n" + content + SPACE + LINE;
    }

    /**
     * Returns a response message of complicated commands
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
            response = handleTodo(lst, command);
            break;
        case "deadline":
            response = handleDeadline(lst, command);
            break;
        case "event":
            response = handleEvent(lst, command);
            break;
        default:
            return format(new UnknownCommandException().toString());
        }

        return response;
    }

    /**
     * Returns boolean value indicating whether the command is valid
     * The command is valid if it consists of COMMAND_TYPE + " " + EXTRA_MESSAGE
     *
     * @param command the command to be processed
     * @return a boolean value indicating whether the command is valid
     */
    private boolean isValid(String command) {
        try {
            String type = command.split(" ")[PRIOR];
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
        return true;
    }

    /**
     * Returns a response string for "done" command
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

        int index;
        try {
            index = Integer.parseInt(extraCommand) - 1;
        } catch (IndexOutOfBoundsException ex) {
            return format(new InvalidDoneException().toString());
        }

        assert index > 0;
        assert index < lst.size();

        Task task = lst.get(index);
        task.setDone();
        TaskList.numberOfDoneTasks++;
        String messageMarked = "Nice! I've marked this task as done:\n";
        return format(messageMarked + MORE_INDENT + task.print());
    }

    /**
     * Returns a response string for "delete" command
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

        int index;
        try {
            index = Integer.parseInt(extraCommand) - 1;
        } catch (IndexOutOfBoundsException ex) {
            return format(new InvalidDeletionException().toString());
        }

        assert index > 0;
        assert index < lst.size();

        Task task = lst.get(index);
        lst.delete(index);
        TaskList.numberOfDoneTasks++;

        String messageDelete = "Noted. I've removed this task:\n";
        return format(messageDelete + MORE_INDENT + task.print() + getListCountMessage(lst));

    }

    /**
     * Returns a string listing all tasks in the task list
     *
     * @param lst the task list
     * @return a string listing all tasks in lst
     */
    private String getListCountMessage(TaskList lst) {
        return "\n" + INDENT + "Now you have " + lst.size() + " task(s) in the list.\n";
    }

    /**
     * Returns a response string listing all tasks containing the searching string
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

        String messageMatching = "Here are the matching tasks in your list:\n";
        return formatList(taskPrint(subList), messageMatching);
    }

    /**
     * Returns a response string for "toodoo" command
     *
     * @param lst the task list
     * @param command the command to be processed
     * @return a response string for "toodoo" command
     */
    public String handleTodo(TaskList lst, String command) {
        String description;
        try {
            description = command.split(" ", 2)[EXTRA];
        } catch (IndexOutOfBoundsException ex) {
            return format(new TodoEmptyBodyException().toString());
        }

        Task task = new Todo(description);
        lst.add(task);

        return format(messageAdded + task.print() + getListCountMessage(lst));
    }

    /**
     * Returns a response string for "event" command
     *
     * @param lst the task list
     * @param command the command to be processed
     * @return a response string for "event" command
     */
    public String handleEvent(TaskList lst, String command) {
        String description;
        try {
            description = command.split(" ", 2)[EXTRA];
        } catch (IndexOutOfBoundsException ex) {
            return format(new TodoEmptyBodyException().toString());
        }

        Task task = new Event(description);
        lst.add(task);

        return format(messageAdded + task.print() + getListCountMessage(lst));
    }

    /**
     * Returns a response string for "deadline" command
     *
     * @param lst the task list
     * @param command the command to be processed
     * @return a response string for "deadline" command
     */
    public String handleDeadline(TaskList lst, String command) {
        String description;
        try {
            description = command.split(" ", 2)[EXTRA];
        } catch (IndexOutOfBoundsException ex) {
            return format(new TodoEmptyBodyException().toString());
        }

        Task task = new Deadline(description);
        lst.add(task);

        return format(messageAdded + task.print() + getListCountMessage(lst));
    }

    /**
     * Returns respond message to be printed generated from user input
     *
     * @param lst the task list
     * @return respond message by Duke
     */
    public String getRespond(TaskList lst) {
        String messageRespond;
        if (command.equals("bye")) {
            messageRespond = handleBye();
        } else if (command.equals("list")) {
            messageRespond = handleList(lst);
        } else {
            messageRespond = handleComplicatedCommands(lst, command);
        }
        return messageRespond;
    }
}


