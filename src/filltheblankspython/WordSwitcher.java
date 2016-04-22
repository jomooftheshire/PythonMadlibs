/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filltheblankspython;
import java.awt.Point;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 * The WordSwitcher class is used to do the switching of Words in the ArrayLists.
 * @author Joshua Mulcock
 */


public class WordSwitcher {
    private Word word1, word2;
    private ArrayList<Word> kb, code;
    private boolean lock; //puts a lock on to stop word1 pointing constantly being used.
    private int selectCounter;
    private boolean w1exists, w2exists;
    private int moves, maxMoves; //moves is number of switches and maxMoves is possible switches.
    
    /**
     * Constructor for the WordSwitcher class
     */
    public WordSwitcher(){ 
        lock = false;
        selectCounter = 0;
        word1 = new Word(this);
        word2 = new Word(this);
        w1exists = false;
        w2exists = false;
        //setMaxMoves(diff, blanks);
    }
    /**
     * This method sets the maximum amount of word switches the user can make to
     * complete this exercise. It invokes the method setMovesLeft in the Main class
     * to give users visual feedback about how many moves they have at the beginning
     * of the round.
     * @param diff the difficulty of the game
     * @param blanks the amount of blanked out words in the code
     */
    public void setMaxMoves(int diff, int blanks){
        resetMoves();
        int m;
        if(diff == 0){
            m = blanks * 2;
        }
        else if(diff == 1){
            m = blanks + (blanks/2);
        }
        else{
            m = blanks + 1;
        }
        maxMoves = m;
        Main.setMovesLeft(m);
        
    }
    
    /**
     * Resets the moves back to the original moves
    */
    public void resetMoves(){
        moves = 0; //was equal to maxMoves here for some reason
    }
    
    public int getMoves(){
        return moves;
    }
    
    
    /**
     * Simple test to see if all the switches have been used by the player
    */
    public boolean isMaxMoves(){
        if(moves == maxMoves){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Sets the word1 to equal the word passed in.
     * @param word  
     */
    public void setWord1(Word word){
        word1 = word;
        w1exists = true;
        lock = true;
    }
    
    /**
     * Sets the word1 to equal the word passed in.
     * @param word  
     */
    public void setWord2(Word word){
        word2 = word;
        w2exists = true;
        lock = false;
        //System.out.println("Word 2 set: " + word.getWord());
    }
    
    /**
     * A method to decide which word the selected word on the GUI is.
     * @return if Word2 is blank or not. 
     */
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
    
    public void oldChangePlaces(){
        int pos1,pos2;
        Word tempWord;
        boolean fIsKB, sIsKB;   //f = first, s = second
        boolean legitMove = true;     //if the switch is an acutal move.  
        
        if(word2.getWord().equals("@panelKeyboard@")){ //testing if the panel has been clicked
           if(word1.getLabelName().contains("code")){
                pos1 = getPosition(word1, code);
                fIsKB = false;
               kb.add(word1);
               code.remove(word1);
           }
           else {
               legitMove = false;
           }
          
        }   
        else {
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
        
    
            if (fIsKB == true && sIsKB == false){
                if(word2.isBlank()){            //this is used to prevent pieces getting delete by accidenmtl
                    kb.remove(pos1);
                }
                else{
                    kb.set(pos1, word2);
                }
                code.set(pos2, word1);
            }
            
            else if (fIsKB == false && sIsKB == true){
                if(word1.isBlank()){
                    kb.remove(pos2);
                }
                else{
                    kb.set(pos2, word1);
                }
                code.set(pos1, word2);
            }
            
            else if (fIsKB == true && sIsKB == true){
                kb.set(pos1, word2);
                kb.set(pos2, word1);
                legitMove = false;
            }
            
            else {
                code.set(pos1, word2);
                code.set(pos2, word1);
            }     
       
        }
        if (legitMove){
            increaseMoves();
        }
        Main.refresh(code, kb);
    
    }
    
    /**
     * This method is used to increase the variable that holds how many moves the
     * user has made. If the maximum moves have been reached it closes all windows
     * and restarts the program.
     */
    private void increaseMoves(){
        moves++;
        Main.setMovesLeft(maxMoves - moves);
        if(moves == maxMoves){
            JOptionPane.showMessageDialog(null, "Out of Moves!");
            for(Window w : Window.getWindows()){
                w.dispose();
            }
            OpeningScreen.main(null);
            //need to code a close or something
        }
    }
    
    /**
     * A method used for finding which position in the ArrayList a word is.
     * @param word which object in the list that needs to be found
     * @param list which ArrayList the word is in
     * @return the words position in the list. 
     */
    private int getPosition(Word word, ArrayList<Word> list){
       int position = 0;
       boolean finished = false;
       while (finished == false && position < list.size()){
           finished = false;
           String nameArr = list.get(position).getLabelName();
           if(nameArr != null){                 //newline has no label so this prevents index out of bounds.
                if (nameArr.equals(word.getLabelName())){
                    finished = true;     //used to stop IndexOutOfBoundsException
                }
                else {
                    position++;
                }
            }    
            else {
                    position++;
            }
       }
       return position;
    }
    
    /**
     * This method works out when a switch between or within ArrayLists need to be
     * done. If so it launches the method for changing the places and then resets
     * the class back to defaults. The method also works out if the same word has
     * been chosen twice.
     */
    public void needSwitch(){
        if (w1exists == true && w2exists == true){
            if ((word1.equals(word2)) == false){
                oldChangePlaces();
            }
            word1.standardBorder();
            word2.standardBorder();
            word1 = new Word(this);  //empty word except switcher reference and boolean to say its empty
            word2 = new Word(this);
            w1exists = false;
            w2exists = false;
        }
    }
    
    /**
     * Gives the user more moves. Used for when answers are wrong and the user 
     * needs m ore moves to get it right.
     * @param num how many moves to increase by 
     */
    public void addMoves(int num){
        moves += num;
        Main.setMovesLeft(moves);
    }
}
    

