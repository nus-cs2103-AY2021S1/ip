import java.util.List;

public class AddFunction implements Function {
    @Override
    public void execute(String args, Bot bot, List<String> list) {
        list.add(args);
        bot.sayLine("added: " + args);
    }
}
