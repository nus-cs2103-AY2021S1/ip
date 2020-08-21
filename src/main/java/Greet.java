package main.java;

public class Greet {
    String originalGreeting;
    String greeting;
    String style = "\t______________________________________________________________\n";
    String startGreeting = "\tHello! I'm Duke \n" +
            "\tWhat can I do for you?\n";
    String exitGreeting = "\tBye! Hope to see you again soon:)\n";

    public Greet() {
        this.originalGreeting = null;
        this.greeting = null;
    }

    public Greet(String greeting) {
        this.originalGreeting = greeting;
        this.greeting = "\t" + greeting;
    }

    public String getStartGreeting() {
        return startGreeting;
    }

    public String getGreeting() {
        return this.originalGreeting;
    }

    public String getExitGreeting() {
        return exitGreeting;
    }

    @Override
    public String toString() {
        if (originalGreeting == null) {
            greeting =  startGreeting;
        } else if (originalGreeting.equals("bye")) {
            greeting = exitGreeting;
        }
        String response = style + greeting + "\n" + style;
        return response;
    }
}
