import java.util.ArrayList;
import java.util.List;

public class Parser {
    // constant SPACE and LINE for format purposes
    public static String SPACE = "     ";
    public static String LINE = "____________________________________________________________\n";
    // add outer frame lines

    /**
     * Returns formatted string, adding SPACE and LINE
     *
     * @param input string to be formatted
     * @return formatted string
     */
    public static String format(String input) {
        return SPACE + LINE +
                SPACE + " " + input + "\n" +
                SPACE + LINE;
    }

    // greeting and exit messages strings
    // list and mark strings
    private final String messageBye = "Bye. Hope to see you again soon!";
    private final String messageList = " Here are the task(s) in your list:";
    private final String messageMarked = "Nice! I've marked this task as done:\n";
    private final String messageAdded = "Got it. I've added this task:\n";
    private final String messageDelete = "Noted. I've removed this task:\n";
    private final String messageMatching = "Here are the matching tasks in your list:\n";

    // currentCommand the whole line
    private String currentCommand;

    Parser(String currentCommand) {
        this.currentCommand = currentCommand;
    }

    /**
     * Returns respond message to be printed
     *
     * @param lst the task list
     * @return respond message by Duke
     */
    public String getRespond(List<Task> lst) {
        StringBuilder messageRespond = new StringBuilder();
        if (currentCommand.equals("bye")) {
            messageRespond = new StringBuilder(format(messageBye));
        } else if (currentCommand.equals("list")) {
            messageRespond.append(SPACE).append(LINE);
            messageRespond.append(SPACE).append(messageList).append("\n");
            int counter = 1;
            for (Task task : lst) {
                messageRespond.append(SPACE).append(" ").append(counter).append(".").append(task.getTypeLetter()).append(task.getStatusIcon()).append(task.getPrintMessage()).append("\n");
                counter++;
            }
            messageRespond.append(SPACE).append(LINE);
        } else {
            String priorCommand = currentCommand.split(" ")[0];
            if (priorCommand.equals("done")) {
                try {
                    String extraCommand = currentCommand.split(" ", 2)[1];
                    int index = Integer.parseInt(extraCommand) - 1;
                    if (index < 0 || index >= lst.size()) {
                        messageRespond.append(format(new InvalidDoneException().toString()));
                    }
                    Task task = lst.get(index);
                    task.setDone();
                    messageRespond.append(format(messageMarked + SPACE + "   "
                            + task.getTypeLetter() + task.getStatusIcon() + task.getPrintMessage()));
                } catch (IndexOutOfBoundsException ex) {
                    messageRespond.append(format(new DoneEmptyBodyException().toString()));
                }
            } else if (priorCommand.equals("delete")) {
                try {
                    String extraCommand = currentCommand.split(" ", 2)[1];
                    int index = Integer.parseInt(extraCommand) - 1;
                    if (index < 0 || index >= lst.size()) {
                        messageRespond.append(new InvalidDoneException().toString());
                    }
                    Task task = lst.get(index);
                    lst.remove(index);
                    String messageNum = "\n      Now you have " + lst.size() + " task(s) in the list.";
                    messageRespond.append(format(messageDelete + SPACE + "   "
                            + task.getTypeLetter() + task.getStatusIcon() + task.getPrintMessage()
                            + messageNum)).append("\n");
                } catch (IndexOutOfBoundsException ex) {
                    messageRespond.append(format(new DeleteEmptyBodyException().toString()));
                }
            } else if (priorCommand.equals("find")) {
                try {
                    String extraCommand = currentCommand.split(" ", 2)[1];
                    List<Task> subList = new ArrayList<>();
                    // check whether extraCommand is a subString of any task message
                    for (Task task : lst) {
                        if (task.getMessage().contains(extraCommand)) {
                            subList.add(task);
                        }
                    }
                    messageRespond.append(SPACE).append(LINE).append("\n");
                    messageRespond.append(SPACE).append(messageMatching).append("\n");

                    int counter = 1;
                    for (Task task : subList) {
                        messageRespond.append(SPACE).append(" ").append(counter).append(".").append(task.getTypeLetter()).append(task.getStatusIcon()).append(task.getPrintMessage()).append("\n");
                        counter++;
                    }
                    messageRespond.append(SPACE).append(LINE).append("\n");
                } catch (IndexOutOfBoundsException ex) {
                    messageRespond.append(format(new FindEmptyBodyException().toString()));
                }
            } else {
                Task task;
                switch (priorCommand) {
                case "deadline":
                    try {
                        String extraCommand = currentCommand.split(" ", 2)[1];
                        task = new Deadline(extraCommand);
                        lst.add(task);
                        messageRespond.append(format(messageAdded + SPACE + "   "
                                + task.getTypeLetter() + task.getStatusIcon() + task.getPrintMessage()
                                + "\n " + SPACE + "Now you have " + lst.size() + " task(s) in the list.")).append("\n");
                        break;
                    } catch (IndexOutOfBoundsException ex) {
                        messageRespond.append(format(new DeadlineEmptyBodyException().toString()));
                    }
                case "event":
                    try {
                        String extraCommand = currentCommand.split(" ", 2)[1];
                        task = new Event(extraCommand);
                        lst.add(task);
                        messageRespond.append(format(messageAdded + SPACE + "   "
                                + task.getTypeLetter() + task.getStatusIcon() + task.getPrintMessage()
                                + "\n " + SPACE + "Now you have " + lst.size() + " task(s) in the list.")).append("\n");
                        break;
                    } catch (IndexOutOfBoundsException ex) {
                        messageRespond.append(format(new EventEmptyBodyException().toString()));
                    }
                case "todo":
                    try {
                        String extraCommand = currentCommand.split(" ", 2)[1];
                        task = new Todo(extraCommand);
                        lst.add(task);
                        messageRespond.append(format(messageAdded + SPACE + "   "
                                + task.getTypeLetter() + task.getStatusIcon() + task.getPrintMessage()
                                + "\n " + SPACE + "Now you have " + lst.size() + " task(s) in the list.")).append("\n");
                        break;
                    } catch (IndexOutOfBoundsException ex) {
                        messageRespond.append(format(new TodoEmptyBodyException().toString()));
                    }
                default:
                    // invalid command: not recognised
                    messageRespond.append(format(new UnknownCommandException().toString()));
                }

            }
        }
        return messageRespond.toString();
    }
}


