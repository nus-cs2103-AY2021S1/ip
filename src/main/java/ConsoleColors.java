public enum ConsoleColors {
    RESET("\033[0m"),
    RED("\033[0;31m"),
    GREEN("\033[0;32m"),
    YELLOW("\033[0;33m"),
    BLUE("\033[0;34m");

    public final String color;

    ConsoleColors(String color) {
        this.color = color;
    }

    String getColor() {
        return this.color;
    }


}
