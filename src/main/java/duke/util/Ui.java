package duke.util;

public class Ui {
    private String line = "----------------------------------------------------------------";

    public void greet() {
        String greeting = "Hello! I'm duke.Duke \nWhat can I do for you?";
        printResponse(greeting);
    }
    
    public void showLoadingError() {
        printResponse("Error loading file");
    }
    
    public void showError(Exception e) {
        printResponse(e.getMessage());
    }

    // Wrapper method for printing with horizontal line borders and 1 tab indent
    public void printResponse(String string) {
        String formatted = String.format("%s\n%s\n%s", line, string, line)
                .replaceAll("(?m)^", "\t");
        System.out.println(formatted);
    }
    
    private void showLine() {
        System.out.println(line);
    }
}
