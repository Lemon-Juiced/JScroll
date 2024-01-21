package dev.lemonjuice.jscroll.record;

import java.util.ArrayList;

/**
 * Stores the state of the program for use in iteration.
 */
public record ProgramState(ArrayList<Integer> tape, int tapePointer, int pointerMemory, int loopCount) {}
