package Threaded;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

//The logic of the for some key aspects of the game is in here.
public class HangmanLogicAlgorithm {
    private final String file;
    private String Hidden;
    private final ArrayList<String> BrokenUp = new ArrayList<>();
    private final ArrayList<String> allWords = new ArrayList<>();


    public HangmanLogicAlgorithm(String file){

        this.file = file;

        System.out.println("Getting data from file: ");
        BufferedReader buffed = loadFile();


        try{
        while (buffed.readLine() != null) {allWords.add(buffed.readLine());}
        }catch(Exception e){
            System.out.println("Insert sad noises");
        }




    }
    /**
     * Populates the BufferedReader with the data from the file
     * */
    private BufferedReader loadFile(){
        BufferedReader bee = null;
        try {
            bee =  new BufferedReader( new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load file: " + file);
            e.printStackTrace();
        }

        return bee;
    }

    public String getWord() {
        String word = safelyGetWord();
        BrokenUp.clear();

        breakUp();

        return word;
    }

    private String safelyGetWord(){
        Random r = new Random();
        int wordNumber = r.nextInt(allWords.size()-1);

        this.Hidden = allWords.get(wordNumber);


        return allWords.get(wordNumber);
    }
//Skips x lines of codes inside the BufferedReader

    private void breakUp(){

        for(int i = 0; i<Hidden.length(); i++){
            BrokenUp.add(String.valueOf(Hidden.charAt(i)));
        }

    }

    /**
     *
     * <p>Adds to all indexes of the array where the character of the user is located</p>
     * <p>The char should be checked that it exist inside the word first for efficiency</p>
     * @param s the character that the user has inputted
     * @param data The int array that has a length the same as the word the user tries to guess.
     * @return an integer array that has been populated at the indexes the inputted char is found in the word.
     * */
    public int[] checkAllIndexes(int[] data, String s){

        for(int i = 0; i< BrokenUp.size(); i++){
            if(s.equalsIgnoreCase(BrokenUp.get(i))){
                data[i]++;
            }
            if(data[i]>1){
                System.out.println("Something as gone wrong. This program will safely close ");
                System.exit(0);
            }

        }


        return data;
    }

    /**
     * Checks if the win condition has been reached.
     * @return Boolean
     * */
    public boolean isWon(int[] a){
        int counter = 0;

        for(int i: a){
            if(i > 0){
                counter++;
            }
        }

        return counter == Hidden.length();
    }

    /**
     *
     * Uses the data from the int array in order to know which letter has been found and which has not, and builds a string
     * of the word with "*" replacing the letters that still haven't been found.
     *
     * @param data The int array that has a length the same as the word the user tries to guess.
     * @return A string containing the formatted word
     * */
    public String hiddenBuilder(int[] data){
            StringBuilder br = new StringBuilder();
        for(int i = 0; i<data.length; i++){
            if(data[i] == 1){
                br.append(BrokenUp.get(i));
            }else{
                br.append("*");
            }
        }
        return br.toString();

    }


}
