
public class Duke {


    public static void main(String[] args) {
        Ui myDukeBot = new Ui();
        myDukeBot.greeting();
        myDukeBot.createDirectory("ToDo");
        myDukeBot.populateList();
        myDukeBot.list();

        myDukeBot.listener();

        myDukeBot.updateDirectory();

        System.out.println("End");


    }

}
