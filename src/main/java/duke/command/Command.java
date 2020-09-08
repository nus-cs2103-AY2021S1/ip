package duke.command;

import duke.resource.Wrapper;

import java.util.function.BiFunction;
import java.util.List;

public interface Command extends BiFunction<Wrapper, List<String>, String> {

    public String apply(Wrapper wrapper, List<String> input);

}
