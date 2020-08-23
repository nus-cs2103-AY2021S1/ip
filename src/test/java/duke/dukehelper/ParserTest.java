package duke.dukehelper;

import duke.commands.Commands;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    class Params {
        Commands commandType;
        String[] tokens;
        boolean isLoaded;
        int numTasks;
        Params(Commands commandType, String[] tokens, boolean isLoaded, int numTasks) {
            this.commandType = commandType;
            this.tokens = tokens;
            this.isLoaded = isLoaded;
            this.numTasks = numTasks;
        }
    }
    @Test
    public void parseCommand_listOfDeadlines_correctParsing() throws DukeException {
        Params[] listOfDeadlines = new Params[]{
                new Params(Commands.DEADLINE, new String[]{"deadline","someContent","/by","2020-12-12", "1900"},false, 5),
                new Params(Commands.DEADLINE, new String[]{"deadline","someContent","/by","2000-10-09", "2400"},false, 5),
                new Params(Commands.DEADLINE, new String[]{"deadline","someContent","/by","1926-09-02", "0000"},false, 5),
                new Params(Commands.DEADLINE, new String[]{"deadline","someContent","/by","2008-04-11", "1600"},false, 5),
                new Params(Commands.DEADLINE, new String[]{"deadline","someContent","/by","1020-07-07", "0800"},false, 5)
        };
        Parser parse = new Parser();
        for(int i = 0;i < listOfDeadlines.length;i++) {
            Params params = listOfDeadlines[i];
            LocalDate expectedDate = LocalDate.parse(params.tokens[3]);

            Task result = parse.parseCommand(params.commandType, params.tokens, params.isLoaded, params.numTasks);
            assertEquals(expectedDate.toString(), ((Deadline)result).getDeadline().toString());
            assertEquals(params.tokens[1], result.getContent());

        }
    }
}
