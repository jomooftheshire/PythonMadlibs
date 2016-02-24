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
    
    
    private void inputData(File f){
        try (BufferedReader br = Files.newBufferedReader(f.toPath())) {
            String s = null;
            while ((s = br.readLine()) != null){
                if(s.contains("Task:")){
                    task = s;
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
    
    private String[] StringChopper(String string){
        //working
        String[] words = string.split("\\s");
        return words;
    }
    
    /*
    @param difficulty the difficulty represents how many keybparts need to be hidden
    @int place is a holder for a random position
    The setKeybaord class could be implemented to select random words to be used as the keyboard.
    This removes the necessity of having to prechoose what words are implemented
    and can lead to the same files to being used over and over again. Currently 
    not implemented.
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
            } while(w.getWord().equals("@newline@") || w.isBlank() == true || w.getWord().equals("\t")); //Pevents the newline being an option or one that has already being chosen
            Word kb =  w.clone();
            kb.setKeyboard(true);
            keyboard.add(kb);
            w.setBlank(true);
        }
    }
    
    private int getBlanks(int diff){
        Random rnd = new Random();
        int rand = rnd.nextInt(3) + 1;
        int missing;
        if(diff == 0){
            //missing = rnd.nextInt(3) + 3; //(max - min + 1) + min   3-5
            missing = rand;
        }
        else if(diff == 1){
            missing = (code.size() / 2) - rand; 
            
        }
        else {
            missing = (code.size() / 2) + rand;
        }
        System.out.println("M:R:S;  "+missing + ":" + rand + ":"+code.size());
        return missing;
    }
    
    private void setWordList(String[] words){
        for(String word : words){
            Word w = new Word(word, false, switcher, false, dictionary);
            if(word.startsWith("!") && word.endsWith("!")){
//                w.setBlank(true);
                w.setWord(word.replaceAll("!", ""));
//                
//                Word kb = new Word(w.getWord(), false, switcher, true, dictionary);  //new instance of word
//                keyboard.add(kb);
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
