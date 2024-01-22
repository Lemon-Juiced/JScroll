package dev.lemonjuice.jscroll.util;

public class ErrorUtil {
    /**
     * Handles an error for when the file is of an invalid type.
     */
    public static void invalidFileError(){
        System.err.println("Error: The file is not a valid type.");
        endProgramWithError();
    }

    /**
     * Handles an error for when a loop is nested incorrectly, or does not end.
     */
    public static void loopNestingError(){
        System.err.println("Error: There is a loop nested incorrectly or it does not end.");
        endProgramWithError();
    }

    /**
     * Handles "not a number" errors
     *
     * @param nanText The text that is not a number
     */
    public static void nanError(String nanText){
        System.err.println("Error: " + nanText + " is NaN.");
        endProgramWithError();
    }

    /**
     * Handles an error for when the pointer goes "out of bounds"
     */
    public static void outOfBoundsError(){
        System.err.println("Error: The pointer has left the bounds of the tape.");
        endProgramWithError();
    }

    /**
     * Handles an error for when an "unexpected" <code>char</code> is used.
     *
     * @param unexpectedCharacter The unexpected <code>char</code>
     * @param location The <code>int</code> index of the character
     */
    public static void unexpectedCharacterError(char unexpectedCharacter, int location){
        System.err.println("Error: Unexpected character \'" + unexpectedCharacter + "\' found at: " + location);
        endProgramWithError();
    }

    /**
     * Handles an error, where a loop is set to run zero times.
     */
    public static void zeroLoopError(){
        System.err.println("Error: A loop is set to run zero times");
        endProgramWithError();
    }


    /**
     * Ends a program when an error is thrown if desired.
     */
    public static void endProgramWithError(){
        System.exit(1);
    }
}
