public class Formatter {
    private final int dividerLength;
    private final int leftPadding;

    public Formatter(int dividerLength, int leftPadding) {
        this.dividerLength = dividerLength;
        this.leftPadding = leftPadding;
    }

    private void printLeftPadding() {
        for (int i = 0; i < leftPadding; i++) {
            System.out.print(' ');
        }
    }

    private void printDivider() {
        if (dividerLength != 0) {
            for (int i = 0; i < dividerLength - 1; i++) {
                System.out.print("_");
            }
            System.out.print("_\n");
        }
    }

    public void print(String ericaOutputMessage) {
        printLeftPadding();
        printDivider();
        String[] lines = ericaOutputMessage.split("\\r?\\n");
        for (String line : lines) {
            printLeftPadding();
            System.out.println(line);
        }
        printLeftPadding();
        printDivider();
    }

}
