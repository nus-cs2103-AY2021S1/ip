// DatedTask.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

import java.util.List;
import java.util.Arrays;

public class DatedTask {

	public static List<String> parse(String kind, String input, String dateSpec, String usage) throws InvalidInputException {

        var slash = input.indexOf('/');

        if (slash == 0 || input.isEmpty()) {
            throw new InvalidInputException("task description cannot be empty", usage);

        } else if (slash == -1) {
            throw new InvalidInputException(String.format("%s requires a date", kind), usage);
        }

        var item = input.substring(0, slash).strip();
        var when = input.substring(slash + 1).strip();

        assert !item.isEmpty();

        if (!when.startsWith(dateSpec + " ")) {
            throw new InvalidInputException("incorrect date specification", usage);
        }

        when = when.substring(3).strip();
        return Arrays.asList(item, when);
	}
}
