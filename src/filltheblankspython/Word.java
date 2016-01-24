/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filltheblankspython;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Joshua Mulcock
 */
public class Word {
    private boolean blank;
    private String word;
    private JLabel label;
    private int xCoord, yCoord;
    private WordSwitcher switcher;
    private boolean empty;
    private boolean kb;
    //Mouse adapter for deleting purposes. May not be necessary
    private MouseAdapter ml = new MouseAdapter() {    
               @Override
               public void mousePressed(MouseEvent e) {
                   
                        label.setBorder(BorderFactory.createDashedBorder(Color.BLUE));
                        selected();
                        
                   }
               };
    
   
    public Word(String w, boolean b, WordSwitcher s){
        word = w;
        blank = b;
        switcher = s;
        empty = false;
    }
    
    public Word(WordSwitcher s){
        switcher = s;
        empty = true;
    };
    
    public boolean isEmpty(){
        return empty;
    }
    
    public void become(Word old){ //cloning method
        word = old.getWord();
        blank = old.isBlank();
        label = old.getLabel();
    }
    
    public String getWord(){
        return word;
    }
    
    public boolean isBlank(){
        return blank;
    }
    
    public void setBlank(boolean bool){
        blank = bool;
    }
    
     public void newLabel (int x, int y, boolean keyboard, String name){
        kb = keyboard;
        label = new JLabel();
        label.setName(name);
        label.setLocation(x, y);
        label.setFocusable(true);
        label.setSize(50, 30);
        label.setVisible(true);
        label.setText(getWord());       
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        if (keyboard == false && isBlank() == false){
            label.setFocusable(false);
        }
        
        else if (isBlank()){
            label.setText("_____");
            standardBorder();
        }
        
        else {
            standardBorder();
        }
            
        if(label.isFocusable()){
               addMouse();
            }
     }
     
     public void addMouse(){
         label.addMouseListener(ml);
     }
     
     public void removeMouse(){
         label.removeMouseListener(ml);
     }
     
     public void selected(){
         
         if (switcher.isWord2()){
             switcher.setWord2(this);
             switcher.needSwitch();
         }
         else {
             switcher.setWord1(this);
             switcher.needSwitch();
         }
     }
     
     public void standardBorder(){
         label.setBorder(BorderFactory.createEtchedBorder()); 
     }
     
      public void setLabelName(String name){
        label.setName(name);
      }
      
      
      public void setWord(String w){
          word = w;
      }
      
      public JLabel getLabel(){
          return label;
      }
      
      public void setCoords(int x, int y){
          xCoord = x;
          yCoord = y;
      }
      
      public int getX(){
          return xCoord;
      }
      
      public int getY(){
          return yCoord;
      }
      
      public String getLabelName(){
          return label.getName();
      }
      
      public void printName(){
          System.out.println(word);
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
      
      
}
