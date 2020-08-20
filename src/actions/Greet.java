package actions;

public class Greet {
    String greetings;
    String style = "___________________________________\n";

    public Greet() {
        this.greetings = null;
    }

    public Greet(String greetings) {
        this.greetings = greetings;
    }

    public boolean equal(Object ref) {
        if (this == ref) {
            return true;
        }
        if (this instanceof Object) {
            if ((Object)(this.greetings) == ref) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (greetings == null) {
            greetings =  "Hello! I'm Duke \n" +
                    "What can I do for you?\n";
        }
        if (greetings.equals("bye")) {
            greetings = "Bye! Hope to see you again soon:)\n";
        }
        return style + greetings + "\n" + style;
    }
}
