import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Contains the ChatBot called by Main class.
 */
public class ChatBot {

    /** Indicates if ChatBot has been ended by User */
    static boolean hasEnded = false;

    /**
     * Main static method for ChatBot to be started in Main class.
     */
    public static String start() {
        try {
            DataStorageInterface.initStorage();
            return RickCustom.SUCCESS_INTRO;
        } catch (DukeException e) {
            return RickCustom.FAILED_INTRO;
        }
    }

    public static String getResponse(String query){
        try{
            if(!hasEnded) {
                String resp = ChatBot.response(query);
                return resp;
            } else {
                return "";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Returns appropriate response to be printed on the screen for the User to read.
     *
     * @param query The String passed in by the User.
     * @return Response to User's command.
     * @throws Exception Encompasses DukeException, FileNotFoundException, IOException
     */
    private static String response(String query) throws Exception {
        String[] splitQuery = Parser.getSplit(query);
        String command = Parser.getCommand(splitQuery);
        String[] commandRemoved = Parser.removeCommandString(splitQuery);
        switch (command) {
            case "bye":
                Bye bye = new Bye();
                bye.endBot();
                return bye.response();
            case "clear":
                Clear clear = new Clear();
                return clear.response();
            case "deadline":
                String title = Parser.getTitle(commandRemoved);
                String preposition = Parser.getPreposition(commandRemoved);
                LocalDate dateDeadline = Parser.getDate(commandRemoved);
                LocalTime timeDeadline = Parser.getTime(commandRemoved);
                Task deadline = DataStorageInterface.addDeadline(
                        title,preposition,dateDeadline,timeDeadline);
                return DataStorageInterface.taskAdded(deadline);
            case "delete":
                Delete delete = new Delete(commandRemoved);
                return delete.deleteTask(splitQuery[1]);
            case "done":
                Done done = new Done(commandRemoved);
                return done.markedAsDone(splitQuery[1]);
            case "event":
                String ttle = Parser.getTitle(commandRemoved);
                String ppstn = Parser.getPreposition(commandRemoved);
                LocalDate dateEvent = Parser.getDate(commandRemoved);
                LocalTime timeEvent = Parser.getTime(commandRemoved);
                Task event = DataStorageInterface.addEvent(ttle,ppstn,dateEvent,timeEvent);
                return DataStorageInterface.taskAdded(event);
            case "find":
                String remainingQuery = Parser.concatenateStrArr(commandRemoved);
                Find find = new Find(remainingQuery);
                return find.response();
            case "help":
                Help help = new Help(splitQuery);
                return help.response();
            case "load":
                Load load = new Load(splitQuery);
                return load.load();
            case "list":
                Ls ls = new Ls();
                return ls.response();
            case "save":
                Save save = new Save();
                save.writeToFile();
                return save.response();
            case "todo":
                String editedQ = Parser.concatenateStrArr(commandRemoved);
                Task toDo = DataStorageInterface.addToDo(editedQ);
                return DataStorageInterface.taskAdded(toDo);
            default:
                throw new UnknownCommandException(command);
        }
    }
}
