package duke.exception;

import java.util.Random;

public class InvalidCommandException extends DukeException {
    @Override
    public String toString() {
        Random rnd = new Random();
        int responseNumber = rnd.nextInt(3);
        switch(responseNumber) {
        case 0:
            return "speak louder patrick! i cant hear you!";
        case 1:
            return "could you repeat that again patrick?";
        default:
            return "i don't really know what you are saying patrick";
        }
    }
}
