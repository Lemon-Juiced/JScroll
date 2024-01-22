package dev.lemonjuice.jscroll;

import dev.lemonjuice.jscroll.record.ProgramState;
import dev.lemonjuice.jscroll.util.ErrorUtil;
import dev.lemonjuice.jscroll.util.FileUtil;
import dev.lemonjuice.jscroll.util.TapeUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static dev.lemonjuice.jscroll.util.ErrorUtil.loopNestingError;
import static dev.lemonjuice.jscroll.util.ErrorUtil.zeroLoopError;

public class JScroll {
    public static void main(String[] args) throws FileNotFoundException {
        //Gets the file passed as an argument
        //File file = new File(args[0]);

        //Pass in a file manually
        File file = new File("src/dev/lemonjuice/test/fibonacci.jscroll");

        //Check that file is a ".jscroll" file
        if(!FileUtil.isValidFileType(file)) ErrorUtil.invalidFileError();

        //Get the File as an ArrayList<Character>
        ArrayList<Character> characters = FileUtil.convertFileToCharArrList(file);

        //Simulate the tape
        ArrayList<Integer> tape = new ArrayList<>();
        for (int i = 0; i <= TapeUtil.getTapeCapacity(characters); i++) tape.add(0);
        int tapePointer = 0;
        int pointerMemory = 0;
        int loopCount = 0; // This is being set just in case we enter a loop

        //Do the computation
        ProgramState programState = new ProgramState(tape, tapePointer, pointerMemory, 0);
        programState = computeLoop(programState, characters);
    }

    public static ProgramState computeLoop(ProgramState programState, ArrayList<Character> characters){
        //Simulate the tape
        ArrayList<Integer> tape = programState.tape();
        int tapePointer = programState.tapePointer();
        int pointerMemory = programState.pointerMemory();
        int loopCount = programState.loopCount();

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

                // Loop Cases
                case '(':
                    // In this case we know the next characters will be a "%," or a number followed by ',' with possible whitespace
                    boolean isDerivedFromPointer = false; //Set to false by default
                    String potentialInteger = ""; //Stores the potential integer being built
                    i++;
                    while(characters.get(i) != ','){
                        if(characters.get(i).equals('%')) isDerivedFromPointer = true;
                        else potentialInteger += characters.get(i);
                        i++;
                    }

                    // Now setting the number of iterations
                    int numberOfIterations = 0;
                    if(isDerivedFromPointer) numberOfIterations = pointerMemory;
                    else numberOfIterations = Integer.parseInt(potentialInteger);
                    if(numberOfIterations == 0) zeroLoopError();

                    // Get the rest of the loop (we have to be careful with nested loops though)
                    String loopText="";
                    int nestLevel = 0; //Set this to 0 by default
                    boolean runCondition = true; //Controls the following loop
                    i++;
                    while(runCondition){
                        if(characters.get(i).equals('(')) nestLevel++;
                        if(characters.get(i).equals(')')) {
                            nestLevel--;
                            if(nestLevel == 0) runCondition = false;
                        }
                        loopText += characters.get(i);
                        i++;
                    }

                    //Testing purposes
                    System.out.println("Number of Iterations: " + numberOfIterations);
                    System.out.println("Loop Text: " + loopText);

                    //Convert the loopText to the characters needed
                    /*
                    ArrayList<Character> loopCharacters = new ArrayList<>();
                    for (int j = 0; j < loopText.length(); j++) {
                        loopCharacters.add(loopText.charAt(j));
                    }

                    //Loop the code block
                    ProgramState newProgramState = new ProgramState(tape, tapePointer, pointerMemory, 0);
                    for (int j = 0; j < numberOfIterations; j++) {
                        newProgramState = computeLoop(newProgramState, loopCharacters);
                    }
                    */

                    break;
                case ')':
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

                case '&':
                    for (Integer integer : tape) System.out.print(integer);
                    break;

                case '$':
                    for (Integer integer : tape) System.out.print(integer + " ");
                    break;

                // Whitespace Cases
                case '\n':
                case ' ':
                    break;

                case ';':
                    System.exit(0);

                default:
                    ErrorUtil.unexpectedCharacterError(characters.get(i), i);
            }
        }

        // We probably won't reach here, but just in case
        ProgramState newProgramState = new ProgramState(tape, tapePointer, pointerMemory, loopCount);
        return newProgramState;
    }
}
