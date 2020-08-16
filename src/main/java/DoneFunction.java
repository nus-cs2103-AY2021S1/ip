import java.util.List;

public class DoneFunction implements Function {
    private int index;

    public DoneFunction(int index) {
       this.index = index;
    }

    @Override
    public void execute(Bot bot, List<Task> list) {
        Task t = list.get(index);
        if (t.isDone()) {
            bot.sayLine("You've already completed this task:");
        } else {
            t.markAsDone();
            bot.sayLine("Nice! I've marked this task as done:");
        }
        bot.sayLine("  " + t.displayString());
    }
}
