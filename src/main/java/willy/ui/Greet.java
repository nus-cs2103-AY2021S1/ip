package willy.ui;

/**
 * In charge of the Greetings that the bot can do.
 */
public class Greet {
    private String originalGreeting;
    private String greeting;
    private final String style = "\t_________________________________________________________________\n";
    private final String startGreeting = "\tHello I'm Willy and I am here for u! \n"
            + "\tTell me what u need to do and I will help u keep track of it :)\n"
            + style;
    private final String exitGreeting = "\t  Byee See you again soon!:)\n";

    /**
     * Constructs empty Greetings
     */
    public Greet() {
        this.originalGreeting = null;
        this.greeting = null;
    }

    /**
     * Constructs bot Greetings according to input given
     * @param greeting Greetings bot gives to user
     */
    public Greet(String greeting) {
        this.originalGreeting = greeting;
        this.greeting = "\t" + greeting;
    }

    @Override
    public String toString() {
        if (originalGreeting == null) {
            greeting = startGreeting;
        } else if (originalGreeting.equals("bye")) {
            greeting = exitGreeting;
        }
        String response = "\n" + greeting;
        return response;
    }
}
