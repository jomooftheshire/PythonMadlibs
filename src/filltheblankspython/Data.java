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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * The Data class is the class that holds the entirety of the data for the tasks, 
 * as well as setting in the motion of the data retrieval, and also imports the 
 * dictionary to be used for function descriptions and also filling the spare
 * keyboard places
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
    //static Parser parser; //testing
    private int difficulty;
    
    /**
     * The constructor for the Data class
     * @param ws    The WordSwitcher, passed on into the Parser to be added into new Words 
     * @param diff  The difficulty chosen.
     */
    public Data(WordSwitcher ws, int diff){
        switcher = ws;
        dictionary = new HashMap<String, String>();
        funcList = new ArrayList<>();
        setDictionary();
        filesFound = new ArrayList<>();
        filesFound = findFiles();
        tasks = new ArrayList<>();
        codeList = new ArrayList<>();
        keyboardList = new ArrayList<>();
        difficulty = diff;
        setInfo(filesFound);
    }
    
    /**
     * Returns the string containing the task description
     * @param num which element in the list is to be called
     * @return the task description
     */
    public String getTask(int num){
        String task = tasks.get(num);
        return task;
    }
    
    /**
     * This method gets the code for this task.
     * @param num which element in the list is to be called
     * @return the list of code 
     */
    public ArrayList getCode(int num){
        ArrayList<Word> code = new ArrayList<>(codeList.get(num));
        return code;
    }
    
    
    /**
     * This method gets the keyboard for this task.
     * @param num which element in the list is to be called
     * @return the list of code 
     */
    public ArrayList getKeyboard(int num) {
        ArrayList<Word> keyboard = new ArrayList<>(keyboardList.get(num));
        return keyboard;
    }
    
    /**
     * This method returns how many tasks there are in total.
     * @return the number of tasks available
     */
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
    
    /**
     * Get the information from the dictionary file, splits it into the two 
     * entities and adds it to a HashMap
     */
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
            JOptionPane.showMessageDialog(null, "Dictionary not found!");
        }
    }
    
    
    /**
     * This method looks for all the files that hold the task data. It looks in
     * a specified location for the files. The list is shuffled so that the tasks
     * are in a different order on each run.
     * @return the list of found files 
     */
    private ArrayList<File> findFiles(){
          ArrayList<File> textFiles = new ArrayList<>();
          File dir = new File("./Files/");
          for (File file : dir.listFiles()) {
              if (file.getName().endsWith((".py"))) { //.txt
                  textFiles.add(file);
              }
            }
          if(textFiles.size() == 0){
              JOptionPane.showMessageDialog(null, "No files found!");
          }
          Collections.shuffle(textFiles);
          return textFiles;
    }

    
    /**
     * This method creates a new Parser to get the information for each task
     * @param filesFound the list of files found
     */
    private void setInfo(ArrayList<File> filesFound){
        for (File file : filesFound){
            Parser p = new Parser(file, switcher, dictionary, difficulty);
            tasks.add(p.getTask());
            codeList.add(p.getCodeList());
            keyboardList.add(p.getKeyboardList());
        }
    }
    
    /**
     * This method files the file location of the python file the code
     * is from and returns the information
     * @param index which point in the list the file is in
     * @return location of the python file
     */
    public String getFilesLocation(int index) throws IOException{
        File f = filesFound.get(index);
        String location = f.getCanonicalPath();
        return location;
    }
}
