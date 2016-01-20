/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filltheblankspython;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Joshua Mulcock
 */

//neen to set 
public class WordSwitcher {
    private Word word1, word2;
    private ArrayList<Word> kb, code;
    private boolean lock; //puts a lock on to stop word1 pointing constantly being used.
    private int selectCounter;
    
    public WordSwitcher(){ 
        lock = false;
        selectCounter = 0;
        word1 = new Word(this);
        word2 = new Word(this);
    }
    
    public void setWord1(Word word){
        word1 = word;
        lock = true;
    }
    
    public void setWord2(Word word){
        word2 = word;
        lock = false;
    }
    
    public boolean isWord2(){
        return lock;
    }
    
    public void setCodeList(ArrayList<Word> list){
        code = list;
    }
    
    public void setKeyboardList(ArrayList<Word> list){
        kb = list;
    }
    
    public ArrayList<Word> getCode(){
        return code;
    }
    
    public ArrayList<Word> getKeyboard(){
        return kb;
    }
    
    public boolean whichWord(){
        return lock;
    }
    
    public void changePlaces(){
        int pos1,pos2;
        Word tempWord;
        boolean fIsKB, sIsKB;   //f = first, s = second

        if(word1.getLabelName().contains("keyboard")){
            pos1 = getPosition(word1, kb);
            fIsKB = true;
        }
        
        else {
            pos1 = getPosition(word1, code);
            fIsKB = false;
        }
        
        if(word2.getLabelName().contains("keyboard")){
            pos2 = getPosition(word2, kb);
            sIsKB = true;
        }
        else {
            pos2 = getPosition(word2, code);
            sIsKB = false;
        }
        
        tempWord = new Word(this);
        tempWord.become(word1);
        word1.become(word2);
        word2.become(tempWord);
        
        if (fIsKB == true && sIsKB == false){
            kb.set(pos1, word2);
            code.set(pos2, word1);
        }
        else if (fIsKB == false && sIsKB == true){
            kb.set(pos2, word1);
            code.set(pos1, word2);
        }
        else if (fIsKB == true && sIsKB == true){
            kb.set(pos1, word2);
            kb.set(pos2, word1);
        }
        else {
            code.set(pos1, word2);
            code.set(pos2, word1);
        }
        
        //Main.refresh();
    }
    
    
    private int getPosition(Word word, ArrayList<Word> list){
       int position = 0;
       boolean found = false, finished = false;
       while (finished == false && position < list.size()){
          
           if (word == list.get(position)){
               found = true;
               finished = true;     //used to stop IndexOutOfBoundsException
           }
           else {
               position++;
           }
          
       }
       if(found == false){
           System.out.println("Word not found");
       }
       return position;
    }
    
    public void needSwitch(){
        
        if (word1.isEmpty()  == true || word2.isEmpty() == true){
            if (word2.isEmpty()){
                System.out.println("word1: " + word1.getWord());
            }
            else {
                System.out.println("word2: " + word2.getWord());
            }
        }
        else {
            changePlaces();
            word1.standardBorder();
            word2.standardBorder();
            word1 = new Word(this);  //empty word except switcher reference and boolean to say its empty
            word2 = new Word(this);
        }
    }
}
    

