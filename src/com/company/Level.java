package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by user on 10/03/2016.
 */
public class Level {
    //variables:
    //index: display wich level its about...
    private int index;
    //score: obvious
    private int score;
    //dictionary: contains the words generated in this game
    String[] dictionary = new String[8];
    //help: a counter to determine if the player needs any help, further explanation in the method "help"
    private int help;

    //variables needed in methods only:
    String testWord;
    ArrayList<Character> organizedCharacters = new ArrayList<>();
    ArrayList<Character> wordLetters = new ArrayList<>();
    Scanner r = new Scanner(System.in);
    String responce;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHelp() {
        return help;
    }

    public void setHelp(int help) {
        this.help = help;
    }

    //constructor: this will be used to determmine each level
    public Level(int index, int score, String[] dictionry, int help) {
        this.index = index;
        this.score = score;
        this.dictionary = dictionry;
        this.help = help;
    }

    public void setDictionary(String[] dictionary) {
        this.dictionary = dictionary;
    }

    public String[] getDictionary() {
        return dictionary;
    }


    //transform a word into a pile of letters
    ArrayList<Character> decompose(String word) {
        for (int i = 0; i < testWord.length(); i++) {
            wordLetters.add(i, word.charAt(i));
        }
        return wordLetters;
    }

    //creates and shows the word in a pattern generated randomly
    ArrayList<Character> generateWord(ArrayList<Character> decomposedWord, int startIndex) {
        /*
            "oussama"
            level 1:
            if startIndex == 0 ====> "o-s-a-a"
            if startIndex == 1 ====> "-u-s-m-"
            level 2:
            if startIndex == 0 ====> "-u--am-"
            if startIndex == 1 ====> "o----ma"
         */
        if (index == 1) {
            if (startIndex == 0) {
                for (int i = 0; i < decomposedWord.size(); i++) {
                    if (i % 2 == 0) {
                        organizedCharacters.add(i, decomposedWord.get(i));
                    } else {
                        organizedCharacters.add(i, '-');
                    }
                }
            }

            if (startIndex == 1) {
                for (int i = 0; i < decomposedWord.size(); i++) {
                    if (i % 2 != 0) {
                        organizedCharacters.add(i, decomposedWord.get(i));
                    } else {
                        organizedCharacters.add(i, '-');
                    }
                }
            }
        }
        //for level two:
        if (index == 2) {
            if (startIndex == 0) {
                for (int i = 0; i < decomposedWord.size(); i++) {
                    if (i==1 || i==4 || i==5 || i==8 || i==9) {
                        organizedCharacters.add(i, decomposedWord.get(i));
                    } else {
                        organizedCharacters.add(i, '-');
                    }
                }
            }

            if (startIndex == 1) {
                for (int i = 0; i < decomposedWord.size(); i++) {
                    if (i==0 || i==5 || i==6 || i==7|| i==11) {
                        organizedCharacters.add(i, decomposedWord.get(i));
                    } else {
                        organizedCharacters.add(i, '-');
                    }
                }
            }
        }
        return organizedCharacters;

    }

    public boolean guessingCharachter(String c) {
        boolean valeur = true;
        //correct answer:
        if (wordLetters.contains(c) && organizedCharacters.contains(c) == false) {
            valeur = true;
            int g = wordLetters.indexOf(c);
            organizedCharacters.set(g, c.charAt(0));
            System.out.println("that s correct!\nthe new word is now :");
            for (int i = 0; i < organizedCharacters.size(); i++) {
                System.out.print(organizedCharacters.get(i));
            }
            score += 5;
            System.out.println("\nyour new score is " + score);
        }
        //wrong answer:
        else if (wordLetters.contains(c) == false || organizedCharacters.contains(c)) {
            System.out.println("answer uncorrect, please try again...");
            valeur = false;
            score -= 1;
            System.out.println("\nyour new score is " + score);
            help++;
        }
        return valeur;
    }

    //help method 1:
    public boolean help1() {
        boolean helpval = true;
        //each time the user gets 3 errors, this will appear
        if (help >= 3) {
            System.out.println("*******seems like you re having some troubles*******");
            System.out.println("do you need help ? (y/n)");
            responce = r.next();


            //if the user wants help:
            if (responce.equals("y") || responce.equals("yes")) {
                System.out.println("okay, show the entier word(for 50 points) ? (y/n)");
                responce = r.next();
                if (responce.equals("y") || responce.equals("yes")) {
                    //helping
                    if (score >= 50) {
                        System.out.print("that word was: ");
                        for (int i = 0; i < wordLetters.size(); i++) {
                            System.out.print(wordLetters.get(i));
                        }
                        help = 0;
                        helpval = false;
                        score -= 50;
                        System.out.println(" your new score is " + score);
                        score -= 100;
                    } else System.out.println("*******sorry,you don't have enough points*******");
                }
                //end of helping...
            }
            //if that bitch thinks he s smart enough:
            else {
                System.out.println("very well..good luck");
                help = 0;
            }
        }
        return helpval;
    }

}
