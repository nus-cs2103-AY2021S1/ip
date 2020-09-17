package duke.utils;

import java.util.Arrays;
import java.util.List;

import duke.dateformats.DateFormat;
import duke.dateformats.DayOnlyFormat;
import duke.dateformats.StandardDateFormat;
import duke.dateformats.StandardTimeFormat;
import duke.storage.Storage;


public class Constants {
    /**
     * Greeting for Duke.
     */
    public static final String GREETING =
            "Hello/您好/こんにちは I'm Duke\n"
            + "What can I do for you?";

    /**
     * Divider for Duke output.
     */
    public static final String DIVIDER = "__________________________________________________";

    /**
     * Standard date format.
     */
    public static final String DF_LOCAL_TIME = "yyyy-mm-dd (hh:mm)?";


    /**
     * Default console width.
     */
    public static final int CONSOLEWIDTH = 50;

    public static final String EXITRESPONSE = "EXIT";

    public static final Storage DEFAULTSTORAGE = new Storage("data/tasksTable.csv");

    public static final List<DateFormat> DATE_TIME_FORMAT_LIST =
            Arrays.asList(new DayOnlyFormat(), new StandardDateFormat(), new StandardTimeFormat());

    public static final String PERIODERROR = "perioderror";

}
