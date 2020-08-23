public class Duke {

    private Ui ui;
    private HandleFile handleFile;
    private Parser parser;

    public Duke() {
        ListOfItems listOfItems = new ListOfItems();
        handleFile = new HandleFile(listOfItems);
        parser = new Parser(listOfItems, handleFile);
        ui = new Ui(parser);
    }

    public Parser getParser() {
        return this.parser;
    }

    public static void main(String[] args) {
        new Duke();
    }
}

