package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.EmptyCommandException;
import duke.exception.InvalidCommandException;
import duke.exception.MissingArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Parser {

    public static Command parse(String command) throws DukeException {
        ArrayList<String> tokens = new ArrayList<String>(Arrays.asList(command.split(" ")));
        if (tokens.size()==0 || tokens.get(0).equals("")) {
            throw new EmptyCommandException();
        }
        String cmd = tokens.get(0);

        if (cmd.equals("bye")){
            return new ExitCommand();
        } else if (cmd.equals("done") || cmd.equals("delete")) {
            if (tokens.size() < 2) {
                throw new MissingArgumentException("Must provide number after command!");
            }
            int ind;
            try {
                ind = Integer.parseInt(tokens.get(1))-1;
            } catch(Exception ex) {
                throw new InvalidCommandException(tokens.get(1)+" is not a number!");
            }
            if (cmd.equals("done")) {
                return new DoneCommand(ind);
            } else {
                return new DeleteCommand(ind);
            }
        } else if (cmd.equals("list")) {
            return new ListCommand();
        } else if (cmd.equals("todo") || cmd.equals("event") || cmd.equals("deadline")){

            SimpleDateFormat readformatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Task toAdd;

            if (cmd.equals("todo")) {
                if (tokens.size() < 2) {
                    throw new MissingArgumentException("todo cannot be empty!");
                }
                toAdd = new ToDo(stringCombiner(tokens, 1, tokens.size()));
            } else if (cmd.equals("deadline")) {
                int ind = 0;
                boolean found= false;
                while (!found && ind < tokens.size()-1) {
                    ind++;
                    if (tokens.get(ind).equals("/by")) {
                        found = true;
                    }
                }
                if (!found) {
                    throw new MissingArgumentException("Deadline need an /by time!");
                }
                if (ind == 1) {
                    throw new MissingArgumentException("Deadline needs a name!");
                }
                if (ind == tokens.size() - 1) {
                    throw new MissingArgumentException("Deadline needs a deadline time!");
                }
                Date date;
                try {
                    date = readformatter.parse(stringCombiner(tokens,ind+1, tokens.size()));
                } catch (Exception ex) {
                    throw new InvalidCommandException("Provide a proper date and time!");
                }

                toAdd =  new Deadline(stringCombiner(tokens, 1, ind), date);
            } else {
                int ind = 0;
                boolean found= false;
                while (!found && ind < tokens.size()-1) {
                    ind++;
                    if (tokens.get(ind).equals("/at")) {
                        found = true;
                    }
                }
                if (!found) {
                    throw new MissingArgumentException("Event need an /at time!");
                }
                if (ind == 1) {
                    throw new MissingArgumentException("Event needs a name!");
                }
                if (ind == tokens.size() - 1) {
                    throw new MissingArgumentException("Event needs a event time!");
                }
                Date date;
                try {
                    date = readformatter.parse(stringCombiner(tokens,ind+1, tokens.size()));
                } catch (Exception ex) {
                    throw new InvalidCommandException("Provide a proper date and time!");
                }
                toAdd =  new Event(stringCombiner(tokens, 1, ind), date);
            }
            return new AddCommand(toAdd);

        } else {
            throw new InvalidCommandException("I do not recognise this command!");
        }
    }

    public static String stringCombiner(ArrayList<String> tokens, int start, int end) {
        StringBuilder str = new StringBuilder();
        for (int i = start; i < end; i++) {
            str.append(tokens.get(i));
            if (i != end - 1) {
                str.append(" ");
            }
        }
        return str.toString();
    }
}
