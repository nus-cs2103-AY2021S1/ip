
public class Duke {


    public static void main(String[] args) {
        DukeBot myDukeBot = new DukeBot();
        myDukeBot.greeting();
        myDukeBot.createDirectory("ToDo");
        myDukeBot.populateList();
        myDukeBot.list();

        myDukeBot.listener();

        myDukeBot.updateDirectory();

        System.out.println("End");


    }

}
