import java.util.Scanner;

public class ChatBot {

    static boolean ended = false;

    public static void start(){
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
        } catch(DukeException e){
            System.out.println("ChatBot initialisation failed.");
        }
    }

    private static String response(String query) throws Exception{
        String[] splitQuery = query.split("\\s+");
        String command = splitQuery[0].toLowerCase();
        String[] cRemoved = removeCommandString(splitQuery);
        switch (command){
            case "bye":
                Bye bye = new Bye();
                bye.endBot();
                return bye.response();
            case "help":
                Help help = new Help(splitQuery);
                return help.response();
            case "done":
                Done done = new Done(cRemoved);
                return done.markedAsDone(splitQuery[1]);
            case "clear":
                Clear clear = new Clear();
                return clear.response();
            case "list":
                Ls ls = new Ls();
                return ls.response();
            case "todo":
                String editedQ = concatenateStrArr(cRemoved);
                Task toDo = DataStorageInterface.addToDo(editedQ);
                return DataStorageInterface.taskAdded(toDo);
            case "deadline":
                String title = getTitle(cRemoved);
                String preposition = getPreposition(cRemoved);
                String dateTime = getDateTime(cRemoved);
                Task deadline = DataStorageInterface.addDeadline(title,preposition,dateTime);
                return DataStorageInterface.taskAdded(deadline);
            case "event":
                String ttle = getTitle(cRemoved);
                String ppstn = getPreposition(cRemoved);
                String dT = getDateTime(cRemoved);
                Task event = DataStorageInterface.addEvent(ttle,ppstn,dT);
                return DataStorageInterface.taskAdded(event);
            default:
                throw new UnknownCommandException(command);
        }
    }

    /***
     * All methods below are to do with string preprocessing before being passed
     * to the relevant commands.
     *
     */

    private static String[] removeCommandString(String[] splitQuery){
        splitQuery[0] = "";
        return splitQuery;
    }

    private static String concatenateStrArr(String[] strArr){
        StringBuilder acc = new StringBuilder();
        for (String s: strArr) {
            if(!s.equals("")) {
                acc.append(" ").append(s);
            }
        }
        return acc.toString();
    }

    private static String getTitle(String[] splitQuery){
        StringBuilder accTaskTitle = new StringBuilder();
        int i = 1;
        while(!splitQuery[i].startsWith("/")){
            accTaskTitle.append(" ").append(splitQuery[i]);
            i++;
        }
        return accTaskTitle.toString();
    }

    private static String getPreposition(String[] splitQuery){
        for (String s:splitQuery) {
            if(s.startsWith("/")){
                return s.substring(1);
            }
        }
        return "";
    }

    private static String getDateTime(String[] splitQuery){
        StringBuilder accTaskDateTime = new StringBuilder();
        int i = 0;
        while(!splitQuery[i].startsWith("/")){
            i++;
        }
        i++;
        while(i < splitQuery.length){
            accTaskDateTime.append(" ").append(splitQuery[i]);
            i++;
        }
        return accTaskDateTime.toString();
    }
}
