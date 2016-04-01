/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filltheblankspython;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Cursor;

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
    
    public void setBlank(boolean bool){
        blank = bool;
    }
    
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
        
        setDimension();
     }
     
     public void setDimension(){
         Dimension d = new Dimension(label.getPreferredSize().width, label.getPreferredSize().height);       //+5 to solve the border issue
            label.setMaximumSize(d);
            label.setMinimumSize(d);
            label.setSize(d);
     }
     
     public void addMouse(){
         label.addMouseListener(ml);
     }
     
     public void removeMouse(){
         label.removeMouseListener(ml);
     }
     
     public void selected(){
        Main.msgSelected();
        Main.msgChoose();
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
         if(kb == true){
            label.setBorder(BorderFactory.createRaisedSoftBevelBorder()); 
         }
         else {
             label.setBorder(BorderFactory.createLoweredSoftBevelBorder());
         }
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
          try {
            return label.getName();
        }
          catch(java.lang.NullPointerException x){
              return null;
          }
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
      
      
}
