/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filltheblankspython;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
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
    static Word temp; //delete
    
    public Parser(File f, WordSwitcher ws, HashMap<String, String> dic){
        File file = f;
        switcher = ws;
        keyboard = new ArrayList<Word>();
        code = new ArrayList<Word>();    
        dictionary = dic;
        inputData(file);
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
    
    private void setWordList(String[] words){
        for(String word : words){
            Word w = new Word(word, false, switcher, false, dictionary);
            if(word.startsWith("!") && word.endsWith("!")){
                w.setBlank(true);
                w.setWord(word.replaceAll("!", ""));
                
                Word kb = new Word(w.getWord(), false, switcher, true, dictionary);  //new instance of word
                //System.out.println(w.getWord() + ":" + w);
                //kb.become(w);
                //kb.setBlank(false);
                //kb.setKB(true);     //used to state the word is keybaord.
                keyboard.add(kb);
            }
            code.add(w);
        }
        setTempWord(keyboard.get(0));
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
    
    public void setTempWord(Word w){
        temp = w;
    }
    
    static Word getTempWord(){
        return temp;
    }
}
