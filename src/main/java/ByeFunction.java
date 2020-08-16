import java.util.List;

public class ByeFunction implements Function {
    @Override
    public void execute(Bot bot, List<Task> list) {
        bot.sayLine("Bye! Hope to see you again soon!");
        bot.stop();
    }
}
