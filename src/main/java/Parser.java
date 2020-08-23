public class Parser {

    HandleFile handleFile;
    ListOfItems listOfItems;

    Parser(ListOfItems list, HandleFile handleFile) {
        this.handleFile = handleFile;
        this.listOfItems = list;
    }

    void run(String input) {
        try {
            if (input.equals("list")) {
                listOfItems.getList();
            } else if (input.contains("done")) {
                listOfItems.doneItem(input);
                handleFile.writeFile(listOfItems);
            } else if (input.contains("delete")) {
                listOfItems.deleteItem(input);
                handleFile.writeFile(listOfItems);
            } else if (input.contains("items due by")) { // check items due on a specific date
                listOfItems.checkBy(input);
            } else if (input.contains("items due before")) { // check items due before a specific date + time
                listOfItems.checkBefore(input);
            } else if (input.contains("find")) {
                listOfItems.find(input);
            } else {
                listOfItems.addItem(input);
                handleFile.writeFile(listOfItems);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}

