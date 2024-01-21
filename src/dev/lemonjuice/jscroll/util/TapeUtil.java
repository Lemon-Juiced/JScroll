package dev.lemonjuice.jscroll.util;

import java.util.ArrayList;

public class TapeUtil {
    /**
     * Get the required capacity of the tape
     *
     * @param chars The instructions being used on this tape
     * @return the required capacity of the tape
     */
    public static int getTapeCapacity(ArrayList<Character> chars){
        int requiredCapacity = 0;

        for (int i = 0; i < chars.size(); i++) {
            if(chars.get(i).equals('>')) requiredCapacity++;
            else if(chars.get(i).equals('<')) requiredCapacity--;
        }

        return requiredCapacity;
    }
}
