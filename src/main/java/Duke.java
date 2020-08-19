public class Duke {
    public static void main(String[] args) {

        GreetingsFarewell greetingsFarewell = new GreetingsFarewell();
        Bot bot = new Bot();
        greetingsFarewell.greeting();
        bot.serve();
        greetingsFarewell.farewell();


    }
}
