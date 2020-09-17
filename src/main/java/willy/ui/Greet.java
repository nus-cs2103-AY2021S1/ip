package willy.ui;

/**
 * In charge of the Greetings that the bot can do.
 */
public class Greet {
    private String originalGreeting;
    private String greeting;
    private final String STYLE = "\t_________________________________________________________________\n";
    private final String START_GREETING = "\tHello I'm Willy and I am here for u! \n"
            + "\tTell me what u need to do and I will help u keep track of it :)\n"
            + STYLE;
    private final String EXIT_GREETING = "\t  Byee See you again soon!:)\n";

    public Greet() {
        this.originalGreeting = null;
        this.greeting = null;
    }

    public Greet(String greeting) {
        this.originalGreeting = greeting;
        this.greeting = "\t" + greeting;
    }

    @Override
    public String toString() {
        if (originalGreeting == null) {
            greeting = START_GREETING;
        } else if (originalGreeting.equals("bye")) {
            greeting = EXIT_GREETING;
        }
        String response = "\n" + greeting;
        return response;
    }
}
