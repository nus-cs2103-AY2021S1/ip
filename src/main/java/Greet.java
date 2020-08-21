package main.java;

public class Greet {
    String originalGreeting;
    String greeting;
    String style = "\t______________________________________________________________\n";
    String startGreeting = "\tHelloo I'm Willy and I am here for u! \n" +
            "\tTell me wat u like to do and I will help u keep track of it ~\n";
    String exitGreeting = "\tBye! See you again soon hoho\n";

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
