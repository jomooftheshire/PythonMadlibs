/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filltheblankspython;

import static filltheblankspython.Main.helpBlock1;
import static filltheblankspython.Main.helpBlock2;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.Cursor;

/**
 * The Word class holds all of the information for each code segment that appears
 * on the UI. It is from here that the JLabels are also made.
 * @author Joshua Mulcock
 */
public class Word {
    private boolean blank;
    private String word;
    private JLabel label;
    private int xCoord, yCoord;
    private WordSwitcher switcher;
    private final boolean empty;
    private boolean kb;
    private boolean icon;
    private HashMap<String, String> dictionary;
    //Mouse adapter for deleting purposes. May not be necessary
    private MouseAdapter ml = new MouseAdapter() {    
               @Override
               public void mousePressed(MouseEvent e) {
                        
                        label.setBorder(BorderFactory.createDashedBorder(Color.BLUE));
                        selected();
                   }
               
                public void mouseEntered(MouseEvent e){
                    label.setCursor(new Cursor(Cursor.HAND_CURSOR));
               }
               };
    
   
    /**
     * This is the main constructor for Word.
     * 
     * @param w The code element in String format
     * @param b If the Word is to blank
     * @param s The WordSwitcher that is being used
     * @param keyboard If the Word is in the Keyboard ArrayList
     * @param dic The dictionary for describing what the words do.
     */
    public Word(String w, boolean b, WordSwitcher s, boolean keyboard, HashMap<String, String> dic){
        
        if(w.equals("")){
            word = "@tab@";
        }
        else{
            word = w;
        }
        blank = b;
        switcher = s;
        empty = false;
        kb = keyboard;
        dictionary = dic;
        
    }
    
    /**
     * The constructor used for making empty Words, used for place holding 
     * @param s The WordSwticher being used
     */
    public Word(WordSwitcher s){
        switcher = s;
        empty = true;
        kb = false;
    };
    
    public boolean isEmpty(){
        return empty;
    }
    
    public String getWord(){
        return word;
    }
    
    public boolean isBlank(){
        return blank;
    }
    
    /**
     * Sets if the Word is to be blank or not.
     * @param bool 
     */
    public void setBlank(boolean bool){
        blank = bool;
    }
    
    /**
     * The method creates the JLabel and give it particular properties, depending 
     * on the Word properties which it is associated with.
     * @param x X coordinate position of the JLabel
     * @param y Y coordinate position of the JLabel
     * @param name the name assigned to the JLabel 
     */
     public void newLabel (int x, int y, String name){
        label = new JLabel();
        label.setOpaque(true);
        label.setName(name);
        label.setLocation(x, y);
        label.setFocusable(true);
        //label.setSize(50, 30);
        label.setVisible(true);
        label.setText(getWord());
        label.setFont(new Font("courier new", Font.PLAIN, 30));
        label.setBackground(Main.getCodeBackground());
        if (isBlank()){
            label.setText("____");
        }
        
        else if(getWord().equals("@tab@")){
            label.setText("    ");
        }
        else {
            if(dictionary.containsKey(getWord())){
                label.setToolTipText(dictionary.get(getWord()));
            }
        }
        
        if (kb == false && isBlank() == false){
            label.setFocusable(false);
        }
        
        else {
            standardBorder();
        }
            
        if(label.isFocusable()){
               addMouse();
        }
        
        setSize();
     }
     
     /**
      * Used for setting the size for the JLabel. 
      */
     public void setSize(){
         Dimension d = new Dimension(label.getPreferredSize().width, label.getPreferredSize().height);       //+5 to solve the border issue
            label.setMaximumSize(d);
            label.setMinimumSize(d);
            label.setSize(d);
     }
     
     /**
      * Adds a MouseListerner to the JLabel
      */
     public void addMouse(){
         label.addMouseListener(ml);
     }
     
     /**
      * Removes the MouseListener.
      */
     public void removeMouse(){
         label.removeMouseListener(ml);
     }
     
     /**
      * This method is run when the JLabel is selected. It changes the help messages
      * in the Main form depending on is the Word is a blank or not. It then runs
      * a method in WordSwitcher to assign itself as one of the selected words and 
      * then checks if the system needs to switch the Words.
      * 
      */
     public void selected(){
            if(isBlank()){
                  helpBlock2(4);
            }
            else{
                if(Main.isGaps()){
                  helpBlock1(2);
                }
                else {
                    helpBlock2(2);
                }
            }  
          
         if (switcher.isWord2()){
             switcher.setWord2(this);
             switcher.needSwitch();
         }
         else {
                          
             switcher.setWord1(this);
             switcher.needSwitch();
         }
     }
     
     /**
      * Sets the Border around the JLabel.
      */
     public void standardBorder(){
         if(kb == true){
            label.setBorder(BorderFactory.createRaisedSoftBevelBorder()); 
         }
         else {
             label.setBorder(BorderFactory.createLoweredSoftBevelBorder());
         }
     }
     
     /**
      * Sets the JLabel name
      * @param name JLabel name
      */
      public void setLabelName(String name){
            label.setName(name);
      }
      
      
      public JLabel getLabel(){
          return label;
      }
      
      /**
       * Gets the name of the JLabel
       * @return JLabel Name 
       */
      public String getLabelName(){
          try {
            return label.getName();
        }
          catch(java.lang.NullPointerException x){
              return null;
          }
      }
           
      
      public boolean isKeyboard(){
          return kb; 
      }
      
      
      public void setLabelPoint(Point p){
          label.setLocation(p);
      }
      
      public void removeLabel(){
          label = null;
      }
      
      /**
       * This method is used to clone the this Word. It creates a new Word object 
       * using this Words properties.
       * @return new Word object 
       */
      public Word clone(){
          Word w = new Word(word, blank, switcher, kb, dictionary);
          w.setLabel(getLabel());
          //w.become(this);
          return w;
      }
      
      public void setIcon(boolean b){
          icon = b;
      }
      
      public void setLabel(JLabel lbl){
          label = lbl;
      }
      
      public boolean hasIcon(){
          return icon;
      }
      
      public boolean getKB(){
          return kb;
      }
      
      
      public void setKeyboard(boolean bool){
          kb = true;
      }
      
      public void setWord(String w){
          word = w;
      }
      
}
