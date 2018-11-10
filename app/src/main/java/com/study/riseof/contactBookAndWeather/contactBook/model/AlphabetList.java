package com.study.riseof.contactBookAndWeather.contactBook.model;

import java.util.ArrayList;
import java.util.List;

public final class AlphabetList {
  /*  private final int LETTERS_NUMBER=26;
    private final int LETTER_CODE_A=65;
    private final int LETTER_CODE_Z=90; */

    public static final List<Character> alphabet;

    static {
        alphabet = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            alphabet.add(c);
        }
    }

    private AlphabetList() {
    }

  /*  private void createAlphabet(){
        alphabet = new ArrayList<Character>(LETTERS_NUMBER);

        for (int i=LETTER_CODE_A;i<=LETTER_CODE_Z;i++){
            alphabet.add((char)i);
        }
    }*/
}
