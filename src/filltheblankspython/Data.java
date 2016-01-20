/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filltheblankspython;
import java.util.ArrayList;
import java.io.File;

/**
 *
 * @author Joshua Mulcock
 */
public class Data {
    private ArrayList<String> tasks;
    private ArrayList<ArrayList> codeList;
    private ArrayList<ArrayList> keyboardList;
    private ArrayList<File> filesFound;
    private WordSwitcher switcher;
    
    
    public Data(WordSwitcher ws){
        switcher = ws;
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
    
    private ArrayList<File> findFiles(){
          ArrayList<File> textFiles = new ArrayList<>();
          File dir = new File("./Files/");
          for (File file : dir.listFiles()) {
              if (file.getName().endsWith((".txt"))) {
                  textFiles.add(file);
              }
            }
          return textFiles;
    }

    
    private void setInfo(ArrayList<File> filesFound){
        for (File file : filesFound){
            Parser p = new Parser(file, switcher);
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
}
