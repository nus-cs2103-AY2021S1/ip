
public class Duke {


    public static void main(String[] args) {
        Ui myDukeBot = new Ui();
        Storage myStorage = new Storage();
        Parser myParser = new Parser();
        TaskList myTaskList = new TaskList();

        myDukeBot.greeting();
        myStorage.createDirectory("ToDo");
        myStorage.populateList(myTaskList);
        myTaskList.list();

        myParser.listener(myTaskList,myDukeBot);

        myStorage.updateDirectory(myTaskList);
        System.out.println("End");

    }

}
