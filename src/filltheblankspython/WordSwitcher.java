/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filltheblankspython;
import java.awt.Point;
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
    private boolean w1exists = false, w2exists = false;
 
    
    public WordSwitcher(){ 
        lock = false;
        selectCounter = 0;
        word1 = new Word(this);
        word2 = new Word(this);
    }
    
    public void setWord1(Word word){
        word1 = word;
        //System.out.println("Word1: " + word.getWord() + word.isKeyboard());
        w1exists = true;
        lock = true;
    }
    
    public void setWord2(Word word){
        word2 = word;
        //System.out.println("Word2: " + word.getWord() + word.isKeyboard());
        w2exists = true;
        lock = false;
    }
    
    public boolean isWord2(){
        //if lock is true, it means that Word2 if free, if false Word1 is free
        //prevents words being overriden.
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
        Point one, two;
        
//        System.out.println();
//        System.out.println("Word11abel: "+ word1.getLabelName());
//        System.out.println("Word21abel: "+ word2.getLabelName());
//        
        
        if(word1.getLabelName().contains("key")){
            pos1 = getPosition(word1, kb);
            fIsKB = true;
        }
        
        else {
            pos1 = getPosition(word1, code);
            fIsKB = false;
        }
        
        if(word2.getLabelName().contains("key")){
            pos2 = getPosition(word2, kb);
            sIsKB = true;
        }
        else {
            pos2 = getPosition(word2, code);
            sIsKB = false;
        }
        
        System.out.println("Words found:");
        
        two = word2.getLabel().getLocation();
        one = word1.getLabel().getLocation();
        word1.setLabelPoint(two);
        word2.setLabelPoint(one);
        
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
            for(Word w : kb){
                System.out.println(w.getWord());
            }
            kb.set(pos1, word2);
            kb.set(pos2, word1);
            for(Word w : kb){
                System.out.println(w.getWord());
            }
        }
        else {
            code.set(pos1, word2);
            code.set(pos2, word1);
        }
        
        System.out.println("Switch Complete");
        
        //Main.refresh();
    }
    
    
    private int getPosition(Word word, ArrayList<Word> list){
//       System.out.println("--------------------------------------------------------------------------------------------");
//       System.out.println("Element 0 of List: " + list.get(0).getLabelName() + "|| Word Label: " + word.getLabelName());
//       System.out.println("List Size:" + list.size());
//       for(Word w: list){
//           System.out.println(w.getLabelName());
//       }
//       if(word.getLabelName().contains("code")){
//            System.out.println(list.get(3).getLabelName());
//       }
       int position = 0;
       boolean found = false, finished = false;
       while (finished == false && position < list.size()){
          
           if (word.getLabelName().equals(list.get(position).getLabelName())){
               found = true;
               finished = true;     //used to stop IndexOutOfBoundsException
               System.out.println("Word Found");
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
//        System.out.println("Word1 exists:" + word1.isEmpty());
//        System.out.println("Word2 exists:" + word2.isEmpty());
        
        //if ( //(word1.isEmpty()  == true || word2.isEmpty() == true){
            
        //}
        //else {
        if (w1exists == true && w2exists == true){
            System.out.println("Switch Started");
            changePlaces();
            word1.standardBorder();
            word2.standardBorder();
            word1 = new Word(this);  //empty word except switcher reference and boolean to say its empty
            word2 = new Word(this);
            w1exists = false;
            w2exists = false;
        }
    }
}
    

