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
    private boolean w1exists, w2exists;
 
    
    public WordSwitcher(){ 
        lock = false;
        selectCounter = 0;
        word1 = new Word(this);
        word2 = new Word(this);
        w1exists = false;
        w2exists = false;
    }
    
    public void setWord1(Word word){
        word1 = word;
        w1exists = true;
        lock = true;
        System.out.println("Word 1 set: " + word.getWord());
    }
    
    public void setWord2(Word word){
        word2 = word;
        w2exists = true;
        lock = false;
        System.out.println("Word 2 set: " + word.getWord());
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
        
        
        tempWord = new Word(this);
        tempWord = word1.clone();
        word1 = word2.clone();
        word2 = tempWord.clone();
        
        //test();
        
        two = word2.getLabel().getLocation();
        one = word1.getLabel().getLocation();
        word1.setLabelPoint(two);
        word2.setLabelPoint(one);
        
        //test();
        
//this is not flipping it in the arrayLists    
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
//       
        
        
        Main.refresh(code, kb);
    }
    
    
//    void test(){
//        ArrayList<Word> tempC = new ArrayList<>(code);
//        ArrayList<Word> tempKB = new ArrayList<>(kb);
//        
//        System.out.println("Code:\t Old||New");
//        for(int i=0;i<code.size();i++){
//           Word wN = code.get(i);
//           Word wO = tempC.get(i);
//            System.out.println(wO.getLabelName()+":"+wO.getWord()+"\t||\t"+wN.getLabelName()+":"+wN.getWord());
//                }
//        System.out.println();
//         System.out.println("KB:\t Old||New");
//        for(int i=0;i<kb.size();i++){
//           Word wN = kb.get(i);
//           Word wO = tempKB.get(i);
//            System.out.println(wO.getLabelName()+":"+wO.getWord()+"\t||\t"+wN.getLabelName()+":"+wN.getWord());
//                }
//        System.out.println("--------------------------------------------------------------");
//    }
//    
//    void print(){
//        System.out.println("code:");
//        for(Word w : code){
//            System.out.println(w.getLabel().getText());
//        }
//        System.out.println("Keyboard:");
//        for(Word kb : kb){
//            System.out.println(kb.getLabel().getText());
//        }
//        System.out.println("--------------------------------------------------");
//    }
    
    private int getPosition(Word word, ArrayList<Word> list){
       int position = 0;
       boolean finished = false;
       while (finished == false && position < list.size()){
           String nameArr = list.get(position).getLabelName();
           if(nameArr != null){                 //newline has no label so this prevents index out of bounds.
                if (word.getLabelName().equals(nameArr)){
                    finished = true;     //used to stop IndexOutOfBoundsException
                }
            }    
            else {
                    position++;
            }
       }
       return position;
    }
    
    public void needSwitch(){
        if (w1exists == true && w2exists == true){
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
    

