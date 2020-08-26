public class Duke {
    public static void main(String[] args) {

        Bot bot = new Bot();
        Storage storage = new Storage("./data/duke.txt");
        TaskList taskList = new TaskList(storage.load());
        taskList.list.add(new ToDo("b"));
        storage.save(taskList.list);
        bot.serve();

    }
}
