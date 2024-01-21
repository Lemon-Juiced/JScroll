package dev.lemonjuice.jscroll.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtil {
    /**
     * Converts a given file to an <code>ArrayList<char></code>
     *
     * @param file The <code>File</code> to convert to an <code>ArrayList<char></code>
     * @return An <code>ArrayList<char></code> containing the content of the given file.
     */
    public static ArrayList<Character> convertFileToCharArrList(File file) throws FileNotFoundException {
        String str = "";
        Scanner scanner = new Scanner(file);

        str = scanner.nextLine();
        while (scanner.hasNextLine()) {
            str = str + '\n' + scanner.nextLine();
        }

        //Adds final '\n' to notate the final line has ended
        //The final ';' denotes end of program
        str = str + "\n;";

        ArrayList<Character> chars = new ArrayList<>();
        for (char c : str.toCharArray()) {
            chars.add(c);
        }

        return chars;
    }

    /**
     * Determines if a file is a valid JScroll file.
     *
     * @param file The given file
     * @return true if the file is a JScroll file, false otherwise
     */
    public static boolean isValidFileType(File file) throws FileNotFoundException {
        String fileName = file.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf('.'));
        return fileExtension.equals(".jscroll");
    }
}
