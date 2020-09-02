public class Test {

    public static void main(String[] args) {
        Storage storage = new Storage();
        TaskList tasks = new TaskList();
        UI ui = new UI();
        Parser newParser = new Parser();

        storage.createDirectory("FileSaver1");
        storage.updateList(tasks);
        //newParser.run(tasks, ui);
        storage.updateDirectory(tasks);
        tasks.showList();
        System.out.println("Thank you for using! See you next time!");
    }
}
