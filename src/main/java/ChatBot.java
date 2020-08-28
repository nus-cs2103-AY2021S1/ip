import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.regex.Pattern;

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
            case "clear":
                Clear clear = new Clear();
                return clear.response();
            case "deadline":
                String title = getTitle(cRemoved);
                String preposition = getPreposition(cRemoved);
                LocalDate dateDeadline = getDate(cRemoved);
                LocalTime timeDeadline = getTime(cRemoved);
                Task deadline = DataStorageInterface.addDeadline(title,preposition,dateDeadline,timeDeadline);
                return DataStorageInterface.taskAdded(deadline);
            case "delete":
                Delete delete = new Delete(cRemoved);
                return delete.deleteTask(splitQuery[1]);
            case "done":
                Done done = new Done(cRemoved);
                return done.markedAsDone(splitQuery[1]);
            case "event":
                String ttle = getTitle(cRemoved);
                String ppstn = getPreposition(cRemoved);
                LocalDate dateEvent = getDate(cRemoved);
                LocalTime timeEvent = getTime(cRemoved);
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
                String editedQ = concatenateStrArr(cRemoved);
                Task toDo = DataStorageInterface.addToDo(editedQ);
                return DataStorageInterface.taskAdded(toDo);
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


    //TODO: Place WrongUsageException here instead to accommodate for no dateTime being passed in
    private static LocalDate getDate(String[] splitQuery) throws CustomException{
        int i = 0;
        while(!splitQuery[i].startsWith("/")){
            i++;
        }
        i++;
        String[] splitDate = splitQuery[i].split(Pattern.quote("/"));
        if(splitDate.length!=3){
            throw new CustomException("Date is wrongly formatted!");
        }
        //Format required is DD/MM/YYYY
        LocalDate date = LocalDate.parse(splitDate[2]+"-"+splitDate[1]+"-"+splitDate[0]);
        return date;
    }

    private static LocalTime getTime(String[] splitQuery){
        int i = 0;
        while(!splitQuery[i].startsWith("/")){
            i++;
        }
        i+=2;
        //Format required is HH:MM
        LocalTime time = LocalTime.parse(splitQuery[i]+":00");
        return time;
    }
}
