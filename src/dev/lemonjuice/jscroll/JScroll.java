package dev.lemonjuice.jscroll;

import dev.lemonjuice.jscroll.util.ErrorUtil;
import dev.lemonjuice.jscroll.util.FileUtil;
import dev.lemonjuice.jscroll.util.TapeUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class JScroll {
    public static void main(String[] args) throws FileNotFoundException {
        //Gets the file passed as an argument
        //File file = new File(args[0]);

        //Pass in a file manually
        File file = new File("src/dev/lemonjuice/test/test.jscroll");

        //Check that file is a ".jscroll" file
        if(!FileUtil.isValidFileType(file)) ErrorUtil.invalidFileError();

        //Get the File as an ArrayList<Character>
        ArrayList<Character> characters = FileUtil.convertFileToCharArrList(file);

        //Simulate the tape
        ArrayList<Integer> tape = new ArrayList<>();
        for (int i = 0; i <= TapeUtil.getTapeCapacity(characters); i++) tape.add(0);
        int tapePointer = 0;
        int pointerMemory = 0;

        //Do the computation
        for (int i = 0; i < characters.size(); i++) {
            switch (characters.get(i)){
                case '<':
                    if(tapePointer - 1 < 0) ErrorUtil.outOfBoundsError(); //This prevents us from using a negative index
                    tapePointer--;
                    break;

                case '>':
                    tapePointer++;
                    break;

                case '+':
                    tape.set(tapePointer, tape.get(tapePointer) + 1);
                    break;

                case '-':
                    tape.set(tapePointer, tape.get(tapePointer) - 1);
                    break;

                case '.':
                    pointerMemory = tape.get(tapePointer);
                    break;

                case '*':
                    tape.set(tapePointer, tape.get(tapePointer) + pointerMemory);
                    break;

                case '/':
                    tape.set(tapePointer, tape.get(tapePointer) - pointerMemory);
                    break;

                case '#':
                    System.out.print(tape.get(tapePointer));
                    break;

                case '@':
                    System.out.print((char) (tape.get(tapePointer) + '0'));
                    break;

                case '^':
                    System.out.print('\n');
                    break;

                case'\'':
                    i++;
                    System.out.print(characters.get(i));
                    i++;
                    break;

                case'\"':
                    i++;
                    while(characters.get(i) != '\"'){
                        System.out.print(characters.get(i));
                        i++;
                    }
                    break;

                case ';':
                    System.exit(0);

                default:
                    ErrorUtil.unexpectedCharacterError(characters.get(i), i);
            }
        }
    }
}
