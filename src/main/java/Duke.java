public class Duke {
    private Storage storage;
    private UI ui;

    public Duke(String filePath){
        this.storage = new Storage(filePath);
        this.ui = new UI(storage);
    }

    public void run(){
        ui.welcome();
        ui.run();
        ui.escape();
    }

    public static void main(String[] args) {
        new Duke("Data/duke.txt").run();
    }
}
