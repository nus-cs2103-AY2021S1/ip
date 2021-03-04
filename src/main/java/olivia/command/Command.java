package olivia.command;

import java.util.List;
import java.util.function.BiFunction;

import olivia.resource.Wrapper;

/**
 * Command interface that is implemented by all other Commands. Handles commands by taking
 * in a Wrapper, which wraps the Storage, TaskList and Ui classes, and a List of Strings,
 * which represents the further arguments of the command, if applicable. Returns a String
 * representing the message that should be displayed to the user.
 */

public interface Command extends BiFunction<Wrapper, List<String>, String> {

    String apply(Wrapper wrapper, List<String> input);

}
