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
     * Exit pattern of the exit command for Duke.
     */
    public static final String EXITPATTERN = ("^(b|B)(y|Y)(e|E)$");

    /**
     * List pattern of the list command for Duke.
     */
    public static final String LISTPATTERN = ("^(l|L)(i|I)?(s|S)(t|T)?$");

    /**
     * Done pattern of the done command for Duke.
     */
    public static final String DONEPATTERN = ("^(d|D)(o|O)(n|N)(e|E)$");

    /**
     * TODOPattern for Duke command.
     */
    public static final String TODOPATTERN = ("^(t|T)(o|O)(d|D)(o|O)$");

    /**
     * Deadline pattern of the deadline command for Duke.
     */
    public static final String DEADLINEPATTERN = ("^(d|D)(e|E)(a|A)(d|D)(l|L)(i|I)(n|N)(e|E)$");

    /**
     * Event pattern of the event command for Duke.
     */
    public static final String EVENTPATTERN = ("^(e|E)(v|V)(e|E)(n|N)(t|T)$");

    /**
     * Delete pattern of the delete command for Duke.
     */
    public static final String DELETEPATTERN = ("^(d|D)(e|E)(l|L)(e|E)(t|T)(e|E)$");
    /**
     * Standard date format.
     */
    public static final String DF_LOCAL_TIME = "yyyy-mm-dd (hh:mm)?";

    public static final String FINDPATTERN = ("^(f|F)(i|I)(n|N)(d|D)$");

    public static final String HELPPATTERN = ("^(h|H)(e|E)(l|L)(p|P)$");

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
