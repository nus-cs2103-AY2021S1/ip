import java.util.List;

public class ByeFunction implements Function {
    @Override
    public void execute(String args, Bot bot, List<String> list) {
        bot.sayLine("Bye! Hope to see you again soon!");
        bot.stop();
    }
}
