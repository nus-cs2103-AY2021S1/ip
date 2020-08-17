import java.util.ArrayList;
import java.util.Scanner;

/**
 * Immutable Duke chatbot class to encapsulate the behavior of the chatbot.
 * Task id starts from 1.
 */
public class Duke implements IDuke {
    /** List for storing Tasks */
    private final ArrayList<ITask> list;

    private Duke(ArrayList<ITask> list) {
        this.list = new ArrayList<>(list);
    }

    /**
     * Returns a new Duke chatbot object.
     *
     * @return New Duke chatbot object.
     */
    public static Duke getDuke() {
        return new Duke(new ArrayList<>());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void greet() {
        System.out.println(TextFormatter.LOGO);
        System.out.println(TextFormatter
                .getFormattedText("Hi I'm Cat Bot.\nWhat can I do for you?"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bye() {
        System.out.println(TextFormatter
                .getFormattedText("Bye! Hope to see you again!"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDuke storeTask(ITask task) {
        ArrayList<ITask> newList = new ArrayList<>(this.list);
        newList.add(task);
        return new Duke(newList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayTasks() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            sb.append(" ").append(i + 1).append(". ")
                    .append(list.get(i).toString()).append("\n");
        }
        System.out.println(TextFormatter.getFormattedText(sb.toString()));
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException
     */
    @Override
    public ITask getTask(int id) {
        if (id - 1 > list.size() || id < 0) {
            throw new IllegalArgumentException("Task id out of bound!");
        }
        return list.get(id - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumTask() {
        return list.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDuke handleCommand(String command) {
        try {
            if (command.equals("list")) {
                // Handle list command
                handleDisplay();
                return this;
            } else if (command.matches("^done -?[0-9]+$")) {
                // Handle done command
                int index = Integer.parseInt(command.split(" ")[1]);
                return handleDone(index);
            } else if (command.matches("^todo.*")) {
                // Handle todo command
                if (!command.matches("^todo .*")) {
                    throw new DukeIllegalArgumentException(
                            "Wrong todo command! Format: todo <taskName>");
                }
                String param = command.split(" ", 2)[1];
                return handleToDo(param);
            } else if (command.matches("^deadline.*")) {
                // Handle deadline command
                if (!command.matches("^deadline .* /by .*")) {
                    throw new DukeIllegalArgumentException(
                            "Wrong deadline command!\nFormat: deadline <taskName> /by <time>");
                }
                String[] params = command.split(" ", 2)[1].split(" /by ");
                return handleDeadline(params[0], params[1]);
            } else if (command.matches("^event.*")) {
                // Handle event command
                if (!command.matches("^event .* /at .*")) {
                    throw new DukeIllegalArgumentException(
                            "Wrong event command!\nFormat: event <taskName> /at <time>");
                }
                String[] params = command.split(" ", 2)[1].split(" /at ");
                return handleEvent(params[0], params[1]);
            } else {
                throw new DukeUnrecognizedArgumentException("Unrecognizable command!");
            }
        } catch (DukeIllegalArgumentException e) {
            System.out.println(TextFormatter.getFormattedText(
                    "Meow?!! " + e.getMessage()));
        } catch (DukeUnrecognizedArgumentException e) {
            System.out.println(TextFormatter.getFormattedText(
                    "Meow? Sorry I don't know what you are talking about..."));
        } catch (Exception e) {
            System.out.println(TextFormatter.getFormattedText(
                    "Meow!!! Something terrible happened!" + e));
        }

        return this;
    }

    private void handleDisplay() {
        if (list.size() == 0) {
            System.out.println(TextFormatter.getFormattedText(
                    "Oops! Looks like there's no task in the list!"));
        } else {
            displayTasks();
        }
    }

    private IDuke handleDone(int index) throws DukeIllegalArgumentException {
        if (index < 1 || index > getNumTask()) {
            throw new DukeIllegalArgumentException("Task index out of bound!");
        }
        IDuke newDuke = doneTask(index);
        System.out.println(TextFormatter.getFormattedText(
                "Naisu! I've marked this task done!\n" + newDuke.getTask(index)));
        return newDuke;
    }

    private IDuke handleToDo(String description) throws DukeIllegalArgumentException {
        if (description.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The description of todo cannot be empty!");
        }
        ITask task = ToDo.getToDo(description);
        IDuke newDuke = storeTask(task);
        System.out.println(TextFormatter.getFormattedText(
                "Got it. I've added this task:\n\t" + task.toString()
                        + "\nNow you have "
                        +  newDuke.getNumTask() + " task(s) in the list."));
        return newDuke;
    }

    private IDuke handleDeadline(String description, String time)
            throws DukeIllegalArgumentException{
        if (description.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The description of deadline cannot be empty!");
        }
        if (time.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The time of deadline cannot be empty!");
        }
        ITask task = Deadline.getDeadline(description, time);
        IDuke newDuke = storeTask(task);
        System.out.println(TextFormatter.getFormattedText(
                "Got it. I've added this task:\n\t" + task.toString()
                        + "\nNow you have " +  newDuke.getNumTask()
                        + " task(s) in the list."));
        return newDuke;
    }

    private IDuke handleEvent(String description, String time)
            throws DukeIllegalArgumentException {
        if (description.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The description of event cannot be empty!");
        }
        if (time.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The time of event cannot be empty!");
        }
        ITask task = Event.getEvent(description, time);
        IDuke newDuke = storeTask(task);
        System.out.println(TextFormatter.getFormattedText(
                "Got it. I've added this task:\n\t" + task.toString()
                        + "\nNow you have " +  newDuke.getNumTask()
                        + " task(s) in the list."));
        return newDuke;
    }

    /**
     * {@inheritDoc}
     * @throws DukeIllegalArgumentException
     */
    @Override
    public IDuke doneTask(int id) {
        if (id - 1 > list.size() || id < 0) {
            throw new DukeIllegalArgumentException(
                    "Cannot done task! Task id out of bound!");
        } else if (list.get(id - 1).isDone()) {
            throw new DukeIllegalArgumentException(
                    "Cannot done task! Task is already done!");
        }
        ArrayList<ITask> newList = new ArrayList<>(this.list);
        newList.set(id - 1, newList.get(id - 1).markComplete());
        return new Duke(newList);
    }

    public static void main(String[] args) {
        IDuke bot = getDuke();
        Scanner sc = new Scanner(System.in);

        // Greet user
        bot.greet();

        // Handle commands until user types bye
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                break;
            } else if (!s.matches("\\s*")) {
                // Ignore white spaces or empty lines
                bot = bot.handleCommand(s);
            }
        }

        // Exit bot
        bot.bye();
    }
}
