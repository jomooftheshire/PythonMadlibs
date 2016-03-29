/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filltheblankspython;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joshua Mulcock
 */
public class TerminalFiles {
    
    public TerminalFiles(String file) throws UnsupportedEncodingException, IOException{
        boolean unix = false; //method for finding what OS it is
        if (unix){
            //method for creating bash files and running it
        }
        else{
            createBAT(file);
            runBAT();
            deleteBAT();
        }
    }
    
    /**
     * This method creates the batch file that will be used to run the Python file
     * in the terminal. 
     * @param filePath the path to the python file
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException 
     * @throws  IOException
     */
    private void createBAT(String filePath) throws FileNotFoundException, UnsupportedEncodingException, IOException{
        File folder = new File("./BAT");
        folder.mkdir();
        
        File file = new File("./BAT/pythonRunner.bat");
        file.createNewFile();
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        //String python = pythonFinder(); //this will find the where the python is installed. Potentially should be done at the beginning of the program so it decides to show the button or not.
        String python = "C:\\Python\\Python35-32\\python.exe"; //this is a place holder
        String execute = python + " " + filePath;
        writer.println(execute);
        writer.println("PAUSE");
        writer.println("EXIT");
        writer.close();
    }
    
    /**
     * This method runs the batch file
     */
    private void runBAT(){
        try {
        Runtime.getRuntime().exec("cmd /c start ./BAT/pythonRunner.bat"); 
        } catch (IOException ex) {
            Logger.getLogger(TerminalFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method deletes the BAT folder so any unecessary files are no longer
     * existing.
     */
    private void deleteBAT(){
        File f = new File("./BAT");
        f.delete();
    }
    
}
