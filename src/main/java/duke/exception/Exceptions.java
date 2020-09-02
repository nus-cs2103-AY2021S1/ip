package duke.exception;

import duke.tools.FormatString;

/**
 * This is an enum class that contains
 * all String for the exception error message.
 */
public enum Exceptions {
    NUMBEREXCESSEXCEPTION {
        @Override
        public String toString() {
            return "    "
                    + "OOPS!!! It seems like you don't have this option\n"
                    + "    Enter list to see options";
        }
    },

    CLASSCASTEXCEPTION {
        @Override
        public String toString() {
            return "    The content is not of data type of String";
        }
    },

    NUMBERFORMATEXCEPTION {
        @Override
        public String toString() {
            return "    OOPS!!! The format of your index number is incorrect";
        }
    },

    INPUTFORMATEXCEPTION {
        @Override
        public String toString() {
            return "    OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    },

    EMPTYTASKEXCEPTION {
        @Override
        public String toString() {
            return "    OOPS!!! The description of the task cannot be empty";
        }
    },

    FILEEXCEPTION {
        @SuppressWarnings("checkstyle:SingleSpaceSeparator")
        @Override
        public String toString() {
            return FormatString.FOURSPACE.toString()
                    + "OOPS!!! The file exists but is a directory rather than a regular file,\n"
                    + "    "
                    + "or does not exist but cannot be created,\n"
                    + "    "
                    + "or cannot be opened for any other reason";
        }
    },

    READLINEEXCEPTION {
        @Override
        public String toString() {
            return "    OOPS!!! There is a problem reading";
        }
    },

    TIMEFORMATEXCEPTION {
        @Override
        public String toString() {
            return "    OOPS!!! Your time format seems incorrect, please follow yyyy-mm-dd";
        }
    },

    FINDDETAILMISSINGEXCEPTION {
        @Override
        public String toString() {
            return "    OOPS!!! So what would you like to find?\n"
                    + "    Try to add something behind 'find'.";
        }
    }
}
