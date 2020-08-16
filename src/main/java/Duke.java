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
        if (command.equals("list")) {
            // Handle list command
            handleDisplay();
            return this;
        } else if (command.matches("^done [0-9]?$")) {
            // Handle done command
            int index = Integer.parseInt(command.split(" ")[1]);
            return handleDone(index);
        } else if (command.matches("^todo .*")) {
            // Handle todo command
            String param = command.split(" ", 2)[1];
            return handleToDo(param);
        } else if (command.matches("^deadline ..* /by ..*")) {
            // Handle deadline command
            String[] params = command.split(" ", 2)[1].split(" /by ");
            return handleDeadline(params[0], params[1]);
        } else if (command.matches("^event ..* /at ..*")) {
            // Handle event command
            String[] params = command.split(" ", 2)[1].split(" /at ");
            return handleEvent(params[0], params[1]);
        }

        // All no match
        System.out.println(TextFormatter.getFormattedText(
                "Sorry I don't know what you are talking about..."));
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

    private IDuke handleDone(int index) {
        IDuke newDuke = doneTask(index);
        System.out.println(TextFormatter.getFormattedText(
                "Naisu! I've marked this task done!\n" + newDuke.getTask(index)));
        return newDuke;
    }

    private IDuke handleToDo(String description) {
        ITask task = ToDo.getToDo(description);
        IDuke newDuke = storeTask(task);
        System.out.println(TextFormatter.getFormattedText(
                "Got it. I've added this task:\n\t" + task.toString()
                        + "\nNow you have " +  newDuke.getNumTask() + " task(s) in the list."));
        return newDuke;
    }

    private IDuke handleDeadline(String description, String time) {
        ITask task = Deadline.getDeadline(description, time);
        IDuke newDuke = storeTask(task);
        System.out.println(TextFormatter.getFormattedText(
                "Got it. I've added this task:\n\t" + task.toString()
                        + "\nNow you have " +  newDuke.getNumTask() + " task(s) in the list."));
        return newDuke;
    }

    private IDuke handleEvent(String description, String time) {
        ITask task = Event.getEvent(description, time);
        IDuke newDuke = storeTask(task);
        System.out.println(TextFormatter.getFormattedText(
                "Got it. I've added this task:\n\t" + task.toString()
                        + "\nNow you have " +  newDuke.getNumTask() + " task(s) in the list."));
        return newDuke;
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException
     */
    @Override
    public IDuke doneTask(int id) {
        if (id - 1 > list.size() || id < 0) {
            throw new IllegalArgumentException("Task id out of bound!");
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
            } else {
                bot = bot.handleCommand(s);
            }
        }

        // Exit bot
        bot.bye();
    }
}
