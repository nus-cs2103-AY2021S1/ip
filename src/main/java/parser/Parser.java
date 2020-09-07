/**
 * Parser class handles all the commands from the King Program
 * and returns a reply from the commands.
 *
 * @see parser.ParserLogic
 */
package parser;

import king.KingException;
import storage.Storage;
import storage.StorageException;
import tasks.TaskList;

public class Parser {

    private final ParserExceptions exceptions = new ParserExceptions();
    private final ParserLogic parserLogic;

    /**
     * Parse the commands from the King Program
     *
     * @param storage  storage to save the commands to.
     * @param taskList taskList to be manipulated from the commands.
     * @return Parser parser to parse commands.
     */
    public Parser(Storage storage, TaskList taskList) {
        parserLogic = new ParserLogic(taskList, storage);
    }

    /**
     * Parse the commands from the King Program.
     * The parser automatically modifies the asset file in the
     * storage and the taskList according to the commands
     * given by the user.
     *
     * @param phrase the command given by the user.
     * @return String the reply from the command given.
     * @throws KingException kingException is thrown when phrase is invalid.
     * @throws StorageException StorageException thrown by Storage when writing to asset file.
     * @see Storage
     */

    public String parse(String phrase) throws KingException, StorageException {

        assert phrase != null : "command cannot be null!";

        String mainCommand = phrase.split(" ")[0].toLowerCase();
        if (mainCommand.equals("bye")) {
            return parserLogic.parseBye();
        } else if (mainCommand.equals("list")) {
            return parserLogic.parseList();
        } else if (phrase.equals("clear list")) {
            return parserLogic.parseClearList();
        } else if (mainCommand.equals("done")) {
            return parserLogic.parseDone(phrase);
        } else if (mainCommand.equals("todo")) {
            return parserLogic.parseToDo(phrase);
        } else if (mainCommand.equals("event")) {
            return parserLogic.parseEvent(phrase);
        } else if (mainCommand.equals("deadline")) {
            return parserLogic.parseDeadline(phrase);
        } else if (mainCommand.equals("delete")) {
            return parserLogic.parseDelete(phrase);
        } else if (mainCommand.equals("find")) {
            return parserLogic.parseFind(phrase);
        } else {
            throw exceptions.badCommandException();
        }
    }
}
