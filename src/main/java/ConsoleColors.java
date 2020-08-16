/**
 * Enum of the console colors used in the project.
 */
public enum ConsoleColors {
    RESET("\033[0m"),
    RED("\033[0;31m"),
    GREEN("\033[0;32m"),
    YELLOW("\033[0;33m"),
    BLUE("\033[0;34m"),
    BLUE_BOLD("\033[1;94m"),
    GREEN_BACKGROUND("\033[0;102m"),
    YELLOW_BACKGROUND("\033[0;103m"),
    CYAN_BACKGROUND("\033[0;106m");

    public final String color;

    /**
     * Instantiates the enum with a color code.
     * @param color the color code associated with that color
     */
    ConsoleColors(String color) {
        this.color = color;
    }

    /**
     * Get the color code of a particular color.
     * @return the code code of a particular color
     */
    String getColor() {
        return this.color;
    }


}
