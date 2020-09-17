package rock.ui;

import rock.utility.MyString;

/**
 * UI to interact with user
 */
public class Ui {

    /**
     * Constructor.
     */
    public Ui() {

    }

    public static String sayHi() {
        return "Hey, I'm the Rock!\nType any command but \"switch\"!!!!";
    }

    public static String saySwitch() {
        return "I'm the real Rock!";
    }

    public static String sayBye() {
        return "Bye. I am going back to working out!";
    }

    public static void invalidCommand(MyString response) {
        response.addNewLines("Hey you, that is an invalid command!");
    }
}
