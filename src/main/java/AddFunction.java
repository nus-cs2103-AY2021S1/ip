import java.util.List;

public class AddFunction implements Function {
    private Task task;

    public AddFunction(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Bot bot, List<Task> list) {
        list.add(task);
        bot.sayLine("Got it, I've added this task:");
        bot.sayLine("  " + task.displayString());
        bot.sayLine("Now you have " + list.size() + " item(s) in your list.");
    }
}
