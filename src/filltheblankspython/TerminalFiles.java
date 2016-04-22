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
 * TerminalFiles deals with the running of the Python scripts in the Operating 
 * Systems Command Line. Currently only works with Windows OS.
 * @author Joshua Mulcock
 */
public class TerminalFiles {
    
    /**
     * The constructor for terminal files. Decides whether the methods should be
     * run or not
     * @param file the location of the Python Files currently being used
     * @param windows whether the OS is Windows or not
     * @param python The location of the Python executable.
     * @throws UnsupportedEncodingException
     * @throws IOException 
     */
    
    public TerminalFiles(String file, boolean windows, String python) throws UnsupportedEncodingException, IOException{
        
        String dir = System.getProperty("user.dir");
        if (windows){
            createBAT(file, dir, python);
            runBAT(dir);
            deleteBAT();
        }
        else{
            //method for creating bash files and running it
        }
    }
    
    /**
     * This method creates the batch file that will be used to run the Python file
     * in the terminal. 
     * @param filePath the path to the python file
     * @param dir the current working directory
     * @param python the location of the python executable
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException 
     * @throws  IOException
     */
    private void createBAT(String filePath, String dir, String python) throws FileNotFoundException, UnsupportedEncodingException, IOException{
        File folder = new File("./BAT");
        folder.mkdir();
        System.out.println(filePath);
        File file = new File("./BAT/pythonRunner.bat");
        file.createNewFile();
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        //String python = pythonFinder(); //this will find the where the python is installed. Potentially should be done at the beginning of the program so it decides to show the button or not.
        //String python = "C:\\Python\\Python35-32\\python.exe"; //this is a place holder
        String execute = python + " " + filePath;
        writer.println(execute);
        writer.println("PAUSE");
        writer.println("EXIT");
        writer.close();
    }
    
    /**
     * This method runs the batch file
     * @param dir The location of the current working directory.
     */
    private void runBAT(String dir){
        try {
        Runtime.getRuntime().exec("cmd /c start " + dir + "/BAT/pythonRunner.bat"); 
        } catch (IOException ex) {
            Logger.getLogger(TerminalFiles.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Runtime Failed");
        }
    }
    
    /**
     * This method deletes the BAT folder so any unecessary files are no longer
     * existing.
     */
    private void deleteBAT(){
        File f = new File("/BAT");
        f.delete();
    }
    
}
