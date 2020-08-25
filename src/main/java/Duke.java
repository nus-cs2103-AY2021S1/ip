
public class Duke {


    public static void main(String[] args) {
        Ui myDukeBot = new Ui();
        Storage myStorage = new Storage();
        Parser myParser = new Parser();

        myDukeBot.greeting();
        myStorage.createDirectory("ToDo");
        myStorage.populateList(myDukeBot);
        myDukeBot.list();

        myParser.listener(myDukeBot);

        myStorage.updateDirectory(myDukeBot);
        System.out.println("End");

    }

}
