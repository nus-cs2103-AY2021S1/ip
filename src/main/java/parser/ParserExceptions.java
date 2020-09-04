/**
 * ParserExceptions contains all the king exceptions thrown
 * by parser during execution of the King program.
 *
 * @see parser.Parser
 * @see king.KingException
 */

package parser;

import king.KingException;

class ParserExceptions {

    public KingException itemNotFoundException(String item, Throwable error) {
        return new KingException("Item " + item + " is not found!", error);
    }

    public KingException doneNotFollowedByNumberException() {
        return new KingException("Done must be followed by item number", new Throwable("number field empty"));
    }

    public KingException invalidNumberException(String item, Throwable error) {
        return new KingException(item + " is not a valid item number!", error);
    }

    public KingException badDoneSyntaxException() {
        return new KingException("Please follow the syntax: done <item no.>", new Throwable("bad done syntax"));
    }

    public KingException emptyTodoException() {
        return new KingException("Todo cannot be empty!", new Throwable("empty field"));
    }

    public KingException badEventSyntaxException() {
        return new KingException("Follow the syntax event: <description> /at <time>", new Throwable(
                "bad event syntax"
        ));
    }

    public KingException badDeadlineSyntaxException() {
        return new KingException("Follow the syntax: deadline <description> /by <date> <time>", new Throwable(
                "bad deadline syntax"
        ));
    }

    public KingException badDeleteSyntaxException() {
        return new KingException("Please follow the syntax: delete <item no.>", new Throwable("bad delete syntax"));
    }

    public KingException deleteNotFollowedByNumberException() {
        return new KingException("delete must be followed by item number", new Throwable("number field empty"));
    }

    public KingException badCommandException() {
        return new KingException("Not a valid command!", new Throwable("bad command"));
    }

    public KingException badLocalDateTimeException(Throwable error) {
        return new KingException("Date and Time must be formatted as /by <date> <time>. E.g. 2/1/2020 1400",
                error);
    }
}
