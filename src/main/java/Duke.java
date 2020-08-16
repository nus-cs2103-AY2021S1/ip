import java.util.ArrayList;
import java.util.Scanner;

/**
 * Immutable Duke chatbot class to encapsulate the behavior of the chatbot.
 * Task id starts from 1.
 */
public class Duke implements IDuke{
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
    public Duke storeTask(ITask task) {
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

    @Override
    public ITask getTask(int id) {
        return list.get(id - 1);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public IDuke doneTask(int id) {
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
            } else if (s.equals("list")) {
                bot.displayTasks();
            } else if (s.matches("^done [0-9]?$")) {
                int index = Integer.parseInt(s.split(" ")[1]);
                bot = bot.doneTask(index);
                System.out.println(TextFormatter.getFormattedText(
                        "Naisu! I've marked this task done!\n" + bot.getTask(index)));
            } else {
                bot = bot.storeTask(Task.getTask(s));
                System.out.println(TextFormatter.getFormattedText("added: " + s));
            }
        }

        // Exit bot
        bot.bye();
    }
}
