package duke.misc;

import duke.exception.InvalidArgumentException;

import java.util.List;

public class Checker {
    public static <S> void checkListIndex(int index, List<S> list, String errorMessage) throws InvalidArgumentException {
        if (index <= 0 || index > list.size()) {
            throw new InvalidArgumentException(errorMessage);
        }
    }

    public static void checkTime(String datetimeString) throws InvalidArgumentException {
        if (datetimeString.equals(Const.NO_TIME)) {
            throw new InvalidArgumentException("Time cannot be empty");
        }
    }
}
