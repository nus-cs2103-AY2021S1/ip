public class Duke {
    public static void main(String[] args) {

        GreetingsFarewell greetingsFarewell = new GreetingsFarewell();
        Bot bot = new Bot();
        Storage storage = new Storage("./data/duke.txt");
        greetingsFarewell.greeting();
        bot.serve();
        greetingsFarewell.farewell();

    }
}
