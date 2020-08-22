package main.java;

public class Greet {
    String originalGreeting;
    String greeting;
    String style = "\t________________________________________________________________\n";
    String startGreeting = "\tHello I'm Willy and I am here for u! \n" +
            "\tTell me what u need to do and I will help u keep track of it :)\n";
    String exitGreeting = "\tByee See you again soon!\n";

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
