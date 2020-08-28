import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class ChatBot {

    static boolean ended = false;

    public static void start() {
        try {
            DataStorageInterface.initStorage();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Hello I'm Duke!\nWhat can I do for you?");
            while (!ChatBot.ended) {
                try {
                    String query = scanner.nextLine();
                    String resp = ChatBot.response(query);
                    System.out.println(resp);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (DukeException e) {
            System.out.println("ChatBot initialisation failed.");
        }
    }

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
