/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filltheblankspython;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/**
 * The Parser class is used to the get the data for the task from the given text
 * file. It sets out which words are going to be blanked out and adds the necessary
 * tags used for formatting when creating the JLabels later on.
 * 
 * 
 * @author Joshua Mulcock
 */
public class Parser {
    
    private ArrayList<Word> keyboard;
    private ArrayList<Word> code;
    private String task;
    private WordSwitcher switcher;
    private HashMap<String,String> dictionary;
    private int difficulty;
    
    /**
     * The constructor for Parser Class
     * @param f     the file to get the data from
     * @param ws    the WordSwitcher which switches the word. Needed to add to 
     *              the created word.
     * @param dic   the dictionary of functions
     * @param diff  the difficulty of the round.
     */
    public Parser(File f, WordSwitcher ws, HashMap<String, String> dic, int diff){
        File file = f;
        switcher = ws;
        keyboard = new ArrayList<Word>();
        code = new ArrayList<Word>();    
        dictionary = dic;
        inputData(file);
        difficulty = diff;
        setKeyboard();
    }
    
    /**
     * The method extrapolates the data from the text files. It words out which
     * is the task rather than code adds the @newline@ placeholder at the end of
     * each line.
     * @param f     The file to be processed
     */
    private void inputData(File f){
        try (BufferedReader br = Files.newBufferedReader(f.toPath())) {
            String s = null;
            int cnt = 0;
            ArrayList<String> bob = new ArrayList<>();
            while ((s = br.readLine()) != null){
                bob.add(s);
                cnt++;
                if(s.contains("Task:")){
                    task = s.replace("#Task", "Task");
                }
                else if(s.length()==0){
                    System.out.println("EMPTY LINE");   //THis is catching and ignoring empty lines
                }
                else {
                    String[] words = StringChopper(s);
                    setWordList(words);
                    Word eol = new Word("@newline@", false, switcher, false, dictionary);           //shows there needs to be a new line, new WordSwitcher just a place holder.
                    code.add(eol);
                }
                
            }
            
        } catch (IOException x){
            System.err.format("IOException: %s%n", x);
        }
       
    }
    
    
    /**
     * The method splits a string of code into a string that contain each element 
     * from the code.
     * @param string    the line of code taken from the text file.
     * @return          String array of each of the words from the line of code
     */
    private String[] StringChopper(String string){
        //working
        String[] words = string.split("\\s");
        return words;
    }
    
    /**
     * This method sets which words are going to blanked out. It then creates
     * adds these words to the keyboard ArrayList. It uses the method getBlanks 
     * to work out how many blanks are needed. It ignore any previously used words
     * or the newline or tab holders.
     */
    private void setKeyboard(){
        int place, blanks;      //blanks is number of blanks
        blanks = getBlanks(difficulty);
        Random rnd = new Random();
        Word w = new Word(switcher);
        for(int i=0; i < blanks; i++){
            do {
                place = rnd.nextInt(code.size());
                w = code.get(place);
            } while(w.getWord().equals("@newline@") || w.isBlank() == true || w.getWord().equals("@tab@")); //Pevents the newline being an option or one that has already being chosen
            Word kb =  w.clone();
            kb.setKeyboard(true);
            keyboard.add(kb);
            w.setBlank(true);
        }
    }
    
    
    /**
     * This method works out how many blanks there should be in the code.
     * @param diff  the difficulty chosen at the setup of the program
     * @return      returns the how many blanks there should be in the code
     */
    private int getBlanks(int diff){
        Random rnd = new Random();
        int missing;
        int size = getActualCodeSize();
        if(diff == 0){
            missing = rnd.nextInt(3) + 2;
        }
        else if(diff == 1){
            missing = (size / 3); 
        }
        else {
            missing = (size / 2);
        }
        
        return missing;
    }
    
    /**
     * This method is used to go through the ArrayList code to take a count of 
     * how many elements it contains that can be used for blanking out. It is used
     * so that the amount of words missing is not excessive.
     * @return how many Words actually contain code elements. 
     */
    private int getActualCodeSize(){
        String s;
        int size = 0;
        for(Word w : code){
            s = w.getWord();
            if(s.equals("@tab@") || s.equals("@newline@")){
                //does nothing
            }
            else {
                size++;
            }
        }
        return size;
    }
    
    /**
     * This method creates a new Word for each 'segment' of code. It then adds 
     * the Word to code ArrayList. The method also recognises where a tabs to be 
     * and sets a tag to be used later. The method also erases any '!' around 
     * code segments, which is left over of some data files that were designed
     * for the original method for choosing the blanks.
     * @param words an array of each word from the line of code
     */
    private void setWordList(String[] words){
        for(String word : words){
            Word w = new Word(word, false, switcher, false, dictionary);
            if(word.startsWith("!") && word.endsWith("!")){
                w.setWord(word.replaceAll("!", ""));
            }
            if(word.equals("    ")){
                
                w.setWord("@tab@");
            }
            code.add(w);
        }
    }
 
    
    public ArrayList<Word> getKeyboardList(){
        return keyboard;
    }
    
    public ArrayList<Word> getCodeList(){
       return code;
    }
    
    public String getTask(){
        return task;
    }
}
