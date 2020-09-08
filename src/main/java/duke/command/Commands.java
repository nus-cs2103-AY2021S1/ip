package duke.command;

import duke.resource.Wrapper;

import java.util.function.BiFunction;

public interface Commands extends BiFunction<Wrapper, String, String> {

    public String apply(Wrapper wrapper, String input);

}
