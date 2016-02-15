/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filltheblankspython;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Joshua Mulcock
 */
public class Data {
    private ArrayList<String> tasks;
    private ArrayList<ArrayList> codeList;
    private ArrayList<ArrayList> keyboardList;
    private ArrayList<File> filesFound;
    private ArrayList<String> funcList;
    private WordSwitcher switcher;
    private HashMap dictionary;
    static Parser parser; //testing
    
    
    public Data(WordSwitcher ws){
        switcher = ws;
        dictionary = new HashMap<String, String>();
        funcList = new ArrayList<>();
        setDictionary();
        filesFound = new ArrayList<>();
        filesFound = findFiles();
        tasks = new ArrayList<>();
        codeList = new ArrayList<>();
        keyboardList = new ArrayList<>();
        setInfo(filesFound);
    }
    
    public String getTask(int num){
        String task = tasks.get(num);
        return task;
    }
    
    public ArrayList getCode(int num){
        ArrayList<Word> code = new ArrayList<>(codeList.get(num));
        return code;
    }
    
    public ArrayList getKeyboard(int num) {
        ArrayList<Word> keyboard = new ArrayList<>(keyboardList.get(num));
        return keyboard;
    }
    
    public int getTasksSize(){
        //to be used to tell the program how many functions there are
        return tasks.size();
    }
    
    public HashMap getDictionary(){
        return dictionary;
    }
    
    public ArrayList<String> getFunctionList(){
        return funcList;
    }
    
    private void setDictionary(){
        //Sets the dictionary that is used for the tool tip over words.
        File dir = new File("./Files/dictionary/dictionary.txt");
        try (BufferedReader br = Files.newBufferedReader(dir.toPath())) {
            String s = null;
            while ((s = br.readLine()) != null){
                String[] def = s.split("\\s::\\s"); 
                dictionary.put(def[0], def[1]);
                funcList.add(def[0]);
            }
        } catch (IOException x){
            System.err.format("IOException: %s%n", x);
        }
    }
    
    private ArrayList<File> findFiles(){
          ArrayList<File> textFiles = new ArrayList<>();
          File dir = new File("./Files/");
          for (File file : dir.listFiles()) {
              if (file.getName().endsWith((".txt"))) {
                  textFiles.add(file);
              }
            }
          if(textFiles.size() == 0){
              JOptionPane.showMessageDialog(null, "No files found!");
          }
          return textFiles;
    }

    
    private void setInfo(ArrayList<File> filesFound){
        for (File file : filesFound){
            Parser p = new Parser(file, switcher, dictionary);
            parser = p; //delete this. Testing only.
            tasks.add(p.getTask());
            codeList.add(p.getCodeList());
            keyboardList.add(p.getKeyboardList());
        }
    }
 
    
    //uaed for testing purposes
//    public void printInfo(int l){
//        ArrayList<Word> c = getCode(l);
//        ArrayList<Word> k = getKeyboard(l);
//        System.out.println("CODE:");
//        for(Word cw : c){
//            System.out.println(cw.getWord());
//        }
//        System.out.println("KEYBOARD:");
//        for(Word kw : k){
//            System.out.println(kw.getWord());
//        }
//    }  
    
    static Parser getParser(){
        return parser;
    }
}
