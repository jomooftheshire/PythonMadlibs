 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filltheblankspython;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.View;



/**
 * This is the 
 * @author Joshua Mulcock
 */
public class Main extends javax.swing.JDialog {
private Data data; //edit this static
private ArrayList<Word> code;
private static ArrayList<Word> screenCode;
private ArrayList<Word> keyboard;
private static WordSwitcher switcher;
private int taskNum, taskSize;
private static ArrayList<Word> screenKB;
private static int spaceX, spaceY; 
private static int difficulty;
private static int rndKB;   
private int blanks;
private static boolean windows; //this is stating if a windows machine or not.
private static boolean pythree;
private static String pyLocation;
private static boolean gaps;

    /**
     * Main holds the UI for the application and does all of the work behind this
     * form as well.
     */

    public Main(){}
    
    /**
     * Constructor for Main. It runs the method to set up the form.
     * It also sets creates the WordSwitcher, the Data object for
     * finding getting all of the data for the system. It stores the information 
     * for the gaps between the JLabels. It also sets the information for the Python
     * running capabilities and if there is none then the button used for running the
     * Python is hidden. The difficulty is stored and the method for setting up the
     * components on the screen is run.
     * @param diff difficulty
     * @param wnd if the OS is Windows
     * @param pythonThree if there is Python 3
     * @param pyLoc location of Python Executable.
     */
    public Main(java.awt.Frame parent, boolean modal, int diff, boolean wnd, boolean pythonThree, String pyLoc) { 
        super(parent, modal);
        initComponents();
        pythree = pythonThree;
        if (pythree == false){
            btnPython.setVisible(false);
        }
        windows = wnd;
        pyLocation = pyLoc;
        spaceX = lblSpace.getWidth();    //spacing to be used to place next Jlabel once the JLbael is the size of the text in code.
        spaceY = lblSpace.getHeight() + 10;
        switcher = new WordSwitcher();
        data = new Data(switcher, diff);
        
//        panelKeyboard.addMouseListener(new MouseAdapter() {    
//               @Override
//               public void mousePressed(MouseEvent e) {
//                    if(switcher.isWord2()){
//                        switcher.setWord2(new Word("@panelKeyboard@", false, switcher, true, null));
//                    }
//               }
//
//               });
        
        taskSize = data.getTasksSize();
        taskNum = 0;
        levelBuilder(diff);
        difficulty = diff;
        JOptionPane.showMessageDialog(null, "Stuck? Look at the message box in the bottom lef"
                + "t corner!");
    }
    
    /**
     * Sets the help message in the top help JLabel.
     * @param choice which message should be displayed.
     */
    public static void helpBlock1(int choice){
        switch (choice) {
            case 1:
                lblHelp1.setText("SELECT BLOCK");
                lblHelp1.setBackground(Color.red);
                break;
            case 2:
                lblHelp1.setText("BLOCK SELECTED");
                lblHelp1.setBackground(Color.red);
                break;
            case 3:
                lblHelp2.setText("SELECT GAP");
                lblHelp2.setBackground(Color.red);
                break;
            case 4:
                lblHelp2.setText("GAP SELECTED");
                lblHelp2.setBackground(Color.green);
                break;
            default:
                break;
        }
    }
    
    /**
     * Sets the help message in the bottom help JLabel.
     * @param choice which message should be displayed.
     */
    public static void helpBlock2(int choice){
        switch (choice) {
            case 1:
                lblHelp2.setText("SELECT BLOCK");
                lblHelp2.setBackground(Color.red);
                break;
            case 2:
                lblHelp2.setText("BLOCK SELECTED");
                lblHelp2.setBackground(Color.green);
                break;
            case 3:
                lblHelp2.setText("SELECT GAP");
                lblHelp2.setBackground(Color.red);
                break;
            case 4:
                lblHelp2.setText("BLOCK SELECTED");
                lblHelp2.setBackground(Color.green);
                break;
            default:
                break;
        }
    }
    
    
    /**
     * Sets the size of the random code segments and then runs the method for
     * cresting a new task.
     * @param diff difficult
     */    
    private void levelBuilder(int diff){
    //This will be used to construct the game depnding on the level.
        switch (diff) {
            case 0:
                rndKB = 0;
                break;
            case 1:
                rndKB = 10;
                break;
            default:
                rndKB = 15;
                break;
        }
        newTask(taskNum, diff);
    }
    
    /**
     * Sets the label to show the user which task they are at
     * @param taskNum current task number
     * @param taskSize Number of tasks 
     */
    private void setLblNum(int taskNum, int taskSize){
        int size = taskSize;
        int num = taskNum + 1;
        lblNum.setText("Level: "+num +"/"+ size);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        msgScroll = new javax.swing.JScrollPane();
        msgBoard = new javax.swing.JTextArea();
        panelTask = new java.awt.Panel();
        lblTask = new javax.swing.JLabel();
        panelFunction = new javax.swing.JPanel();
        btnRemove = new javax.swing.JButton();
        btnCheck = new javax.swing.JButton();
        lblNum = new javax.swing.JLabel();
        lblSpace = new javax.swing.JLabel();
        lblMoves = new javax.swing.JLabel();
        btnPython = new javax.swing.JButton();
        lblHelp1 = new javax.swing.JLabel();
        lblHelp2 = new javax.swing.JLabel();
        scrollKB = new javax.swing.JScrollPane();
        panelKeyboard = new javax.swing.JPanel();
        scrollCode = new javax.swing.JScrollPane();
        panelCode = new javax.swing.JPanel();

        msgBoard.setColumns(20);
        msgBoard.setFont(new java.awt.Font("Tw Cen MT", 1, 25)); // NOI18N
        msgBoard.setRows(5);
        msgScroll.setViewportView(msgBoard);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fill The Blanks ~ Python Edition");
        setMaximumSize(new java.awt.Dimension(1315, 815));
        setMinimumSize(new java.awt.Dimension(1315, 815));
        setPreferredSize(new java.awt.Dimension(1315, 815));
        setResizable(false);

        panelTask.setBackground(new java.awt.Color(204, 204, 255));
        panelTask.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelTask.setMaximumSize(new java.awt.Dimension(460, 400));
        panelTask.setMinimumSize(new java.awt.Dimension(460, 400));
        panelTask.setPreferredSize(new java.awt.Dimension(460, 400));

        lblTask.setFont(new java.awt.Font("Tw Cen MT", 2, 24)); // NOI18N
        lblTask.setText("Task:");
        lblTask.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblTask.setMaximumSize(new java.awt.Dimension(50, 30));
        lblTask.setMinimumSize(new java.awt.Dimension(50, 30));
        lblTask.setPreferredSize(new java.awt.Dimension(50, 30));
        lblTask.setSize(panelTask.getWidth(), panelTask.getHeight());
        lblTask.setMaximumSize(lblTask.getSize());

        javax.swing.GroupLayout panelTaskLayout = new javax.swing.GroupLayout(panelTask);
        panelTask.setLayout(panelTaskLayout);
        panelTaskLayout.setHorizontalGroup(
            panelTaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTaskLayout.createSequentialGroup()
                .addComponent(lblTask, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelTaskLayout.setVerticalGroup(
            panelTaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTaskLayout.createSequentialGroup()
                .addComponent(lblTask, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(189, 189, 189))
        );

        panelFunction.setBackground(new java.awt.Color(204, 204, 255));
        panelFunction.setMaximumSize(new java.awt.Dimension(460, 330));
        panelFunction.setMinimumSize(new java.awt.Dimension(460, 330));
        panelFunction.setName(""); // NOI18N
        panelFunction.setPreferredSize(new java.awt.Dimension(460, 330));

        btnRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/alphaTick.png"))); // NOI18N
        btnCheck.setName("btnCheck"); // NOI18N
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });

        lblNum.setFont(new java.awt.Font("Tw Cen MT", 0, 30)); // NOI18N
        lblNum.setText("? / ?");

        lblSpace.setFont(new java.awt.Font("Monospaced", 0, 30)); // NOI18N
        lblSpace.setText(" ");

        lblMoves.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        lblMoves.setText("Moves");

        btnPython.setIcon(new javax.swing.ImageIcon(getClass().getResource("/filltheblankspython/Images/pylogoSmall.png"))); // NOI18N
        btnPython.setToolTipText("Run Program in Python");
        btnPython.setMaximumSize(new java.awt.Dimension(83, 59));
        btnPython.setMinimumSize(new java.awt.Dimension(83, 59));
        btnPython.setPreferredSize(new java.awt.Dimension(83, 59));
        btnPython.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPythonActionPerformed(evt);
            }
        });

        lblHelp1.setFont(new java.awt.Font("Tw Cen MT", 1, 30)); // NOI18N
        lblHelp1.setText("Select Block");
        lblHelp1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblHelp1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblHelp2.setFont(new java.awt.Font("Tw Cen MT", 1, 30)); // NOI18N
        lblHelp2.setText("Select Block");
        lblHelp2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblHelp2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelFunctionLayout = new javax.swing.GroupLayout(panelFunction);
        panelFunction.setLayout(panelFunctionLayout);
        panelFunctionLayout.setHorizontalGroup(
            panelFunctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFunctionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFunctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMoves, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHelp1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHelp2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelFunctionLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnCheck)
                        .addGap(18, 18, 18)
                        .addComponent(btnPython, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemove))
                    .addGroup(panelFunctionLayout.createSequentialGroup()
                        .addComponent(lblNum)
                        .addGap(264, 264, 264)
                        .addComponent(lblSpace)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFunctionLayout.setVerticalGroup(
            panelFunctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFunctionLayout.createSequentialGroup()
                .addGroup(panelFunctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFunctionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSpace))
                    .addComponent(lblNum))
                .addGap(18, 18, 18)
                .addComponent(lblMoves)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(lblHelp1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHelp2)
                .addGap(67, 67, 67)
                .addGroup(panelFunctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCheck)
                    .addComponent(btnPython, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        btnRemove.setToolTipText("Refresh Screen");
        btnCheck.setToolTipText("Check Answers");

        scrollKB.setMaximumSize(new java.awt.Dimension(906, 330));
        scrollKB.setMinimumSize(new java.awt.Dimension(906, 330));
        scrollKB.setPreferredSize(new java.awt.Dimension(906, 330));

        panelKeyboard.setBackground(new java.awt.Color(204, 204, 255));
        panelKeyboard.setAutoscrolls(true);
        panelKeyboard.setMaximumSize(new java.awt.Dimension(906, 330));
        panelKeyboard.setMinimumSize(new java.awt.Dimension(906, 330));
        panelKeyboard.setPreferredSize(new java.awt.Dimension(906, 330));

        javax.swing.GroupLayout panelKeyboardLayout = new javax.swing.GroupLayout(panelKeyboard);
        panelKeyboard.setLayout(panelKeyboardLayout);
        panelKeyboardLayout.setHorizontalGroup(
            panelKeyboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 906, Short.MAX_VALUE)
        );
        panelKeyboardLayout.setVerticalGroup(
            panelKeyboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 339, Short.MAX_VALUE)
        );

        scrollKB.setViewportView(panelKeyboard);

        scrollCode.setMaximumSize(new java.awt.Dimension(906, 401));
        scrollCode.setMinimumSize(new java.awt.Dimension(906, 401));
        scrollCode.setPreferredSize(new java.awt.Dimension(906, 401));

        panelCode.setBackground(new java.awt.Color(51, 200, 238));
        panelCode.setAutoscrolls(true);
        panelCode.setMaximumSize(new java.awt.Dimension(906, 401));
        panelCode.setMinimumSize(new java.awt.Dimension(906, 401));

        javax.swing.GroupLayout panelCodeLayout = new javax.swing.GroupLayout(panelCode);
        panelCode.setLayout(panelCodeLayout);
        panelCodeLayout.setHorizontalGroup(
            panelCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 916, Short.MAX_VALUE)
        );
        panelCodeLayout.setVerticalGroup(
            panelCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );

        scrollCode.setViewportView(panelCode);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTask, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelFunction, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollKB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelTask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelFunction, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollKB, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * This method is run when the button for checking the answers if run. It first sees
     * if all of the blanks have been filled. It then checks
     * the original Word ArrayList against the one holding the player's order 
     * Words. Then finds which ones are right or wrong and then puts a tick or cross
     * in the JLabel to represent that. If answers are correct its runs the method
     * for creating a new task.
     * @param evt 
     */
    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
        boolean finished = true;
        boolean correct = true;
        Word word1;
        Word word2;
        for(Word word : screenCode){
            if (word.isBlank() == true){
                finished = false;
            }
        } 
        
        if (finished == false) {
            JOptionPane.showMessageDialog(null, "Not all boxes filled!");
            correct = false;
        }
        
        else {
            for (int i = 0; i < code.size(); i++){
                word1 = screenCode.get(i);
                word2 = code.get(i);
                
                if (!(word1.getWord().equals(word2.getWord()))){ //reversers the output so it checks if false;
                    correct = false;
                    word1.getLabel().setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/alphaWrong.png")));
                    word1.setIcon(true);
                    word1.setSize();
                }
                
                else if ((word1.getWord().equals(word2.getWord())) && word1.isBlank() == false){
                    if(word1.getLabel().isFocusable()){             
                        word1.getLabel().setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/alphaRight.png")));
                        word1.setIcon(true);
                        word1.setSize();
                    }
                }
                
            }
            //shows whats wrong or correct then hides the images again.
            
        
        if (correct == true){
                    System.out.println("Correct");
                    if(pythree){
                        int result = JOptionPane.showConfirmDialog(null, "<html><p><b>Correct</b></p> "
                                + "<p></p> <p>Run Code in Python?</p></html>", "Correct", JOptionPane.YES_NO_OPTION);
                    //JOptionPane.showMessageDialog(null, "Correct");
                    
                        if(result == 0){
                            btnPythonActionPerformed(null);
                        }
                    }
                    
                    else {
                        JOptionPane.showMessageDialog(null, "Correct!");
                    }
                    
                    removeComponents(panelCode);
                    removeComponents(panelKeyboard);
                    taskNum +=1;
       
                    
                    if (taskNum == data.getTasksSize()){
                        JOptionPane.showMessageDialog(null, "Difficulty Complete! \nTry a harder difficulty", "Complete", JOptionPane.INFORMATION_MESSAGE);
                        close();
                        OpeningScreen.main(null);
                    }
                    else {
                    newTask(taskNum, difficulty);     //goes to next task
                    }
        }
        
        else {
            JOptionPane.showMessageDialog(null, "Incorrect");
            switcher.addMoves(blanks); // this is used to to give the player amount needed to move every object. Needs to be changed!
            }
        
        try{
              TimeUnit.SECONDS.sleep(2);
              for(Word w : screenCode){
                if(w.hasIcon()){
                   w.getLabel().setIcon(null);
                   w.setIcon(false);
                   w.getLabel().revalidate();
                   w.setSize();
                }
            }
            } catch (InterruptedException ex){
                System.out.println("Sleep failed");
            }
        
            newCode(screenCode);                //redraws the code
        
        }
    }//GEN-LAST:event_btnCheckActionPerformed

    /**
     * Returns the task to default
     * @param evt 
     */
    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        removeComponents(panelCode);
        removeComponents(panelKeyboard);
        newTask(taskNum, difficulty);
    }//GEN-LAST:event_btnRemoveActionPerformed

    /**
     * Creates a new TerminalFiles object to run the Python file.
     * @param evt 
     */
    private void btnPythonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPythonActionPerformed
        String path;
    try {
        path = data.getFilesLocation(taskNum);
        new TerminalFiles(path, windows, pyLocation);
    } catch (IOException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Failed to get Python file location");
        JOptionPane.showMessageDialog(null, "Python Failed to Run!");
    }
        
    }//GEN-LAST:event_btnPythonActionPerformed

    /**
     * Method used for removing the components off a JPanel
     * @param p JPanel which components need removing
     */
    private static void removeComponents(JPanel p){
        Component[] panel = p.getComponents();
        for(Component comp : panel){
            p.remove(comp);
        }
       p.revalidate();
       p.repaint();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[], int diff, boolean wnd, boolean pythree, String pyLoc) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main dialog = new Main(new javax.swing.JFrame(), true, diff, wnd, pythree, pyLoc);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    /**
     * Creates the new tasks by getting the keyboard and code ArrayLists from the
     * data Object. It also creates the ArrayLists that will hold the users order.
     * It tells the wordSwither to set the moves. It sets the initial text for the help.
     * It also runs the setup method.
     * @param level
     * @param diff 
     */
    private void newTask(int level, int diff) {
        setLblNum(taskNum, taskSize);
        keyboard = data.getKeyboard(level); //shallow copy
        screenKB = new ArrayList<Word>(keyboard);
        switcher.setMaxMoves(diff , keyboard.size()); //this gets the blank
        switcher.resetMoves();
        int n = rndKB - keyboard.size() / 2;
        screenKB.addAll(getRandomKeyboard(n));
        code = data.getCode(level);
        screenCode = new ArrayList<Word>(code); //contains the information that will change.
        blanks = code.size();
        setTask(data.getTask(level));
        setup(screenCode, screenKB);
        helpBlock1(1);
        if(isGaps()){
            helpBlock2(3);
        }
        else{
            helpBlock2(1);
        }
    }
    
    /**
     * Sets the description of the task in the label of the UI
     * @param task the task description
     */
    private void setTask(String task){
        lblTask.setText("<html><p>"+task+"</p></html>");    //html used for automatic wrapping
    }
    
    /**
     * Runs the methods for cresting the JLabels for the code and the keyboard
     * @param c ArrayList of Word for the code.
     * @param kb ArrayList of Word for the keyboard. 
     */
    private static void setup(ArrayList<Word> c, ArrayList<Word> kb){
        newCode(c);
        newKeyboard(kb);  
    }
  
    
    /**
     * Used by the WordSwitcher for updating the UI when a switch has been made.
     * @param c ArrayList of Word for the code.
     * @param kb ArrayList of Word for the keyboard.  
     */
    public static void refresh(ArrayList<Word> c, ArrayList<Word> kb){
        //This take is used to be able to update the UI when switch has takn place.
        helpBlock1(1);
        if(isGaps()){
            helpBlock2(3);
        }
        else{
            helpBlock2(1);
        }
        screenKB = new ArrayList<>(switcher.getKeyboard());
        screenCode = new ArrayList<>(switcher.getCode());
        removeComponents(panelCode);
        removeComponents(panelKeyboard);
        setup(c, kb);
        
        
    }
    
    /**
     * Creates the JLabels and places them in the JPanel. The placement of JLabels
     * is meant to represent how the code looks in an IDE. It then does the calculation
     * for showing how deep the scrollbars can go. 
     * @param c ArrayList of Word for the code.
     *
     */
    private static void newCode(ArrayList<Word> c){
        switcher.setCodeList(c);
        gaps = false;
        int xCoord = 20;
        int yCoord = 0;
        int num = 0;
        int lines = 1;
        int topX = 0, topY = 0; // stored for the dimension
        for(Word word : c) {
            if (word.getWord().equals("@newline@")){
                lines++;
                if(xCoord + 50 > topX){
                    topX = xCoord + 50; //+50 is currently an arbitary number 
                }
                yCoord+=spaceY;
                xCoord = 20;
                word.newLabel(0, 0, null);            
                word.getLabel().setVisible(false);
            }
            
            else {
                String name = "code" + num;
                num++;  
                boolean kb = false;         //determines if keyboard to have those special options in the new label. Moved to constructor of word.
                word.newLabel(xCoord, yCoord, name);
                word.setLabelName(name);
                panelCode.add(word.getLabel());
                xCoord += word.getLabel().getWidth() + spaceX; // this makes sure there is a space inbetween 
            }
            if(word.isBlank()){
                System.out.println("BLANKS");
                gaps = true;
            }
        }
        
        topX += 10;
        //topY = yCoord + spaceY;
        topY = spaceY * (lines + 1);
        Dimension d = new Dimension(topX, topY);
        Dimension n = scrollCode.getPreferredSize();
        if((d.height > n.height - 15) || (d.width > n.width - 15)){
            //d.height += 15;
            panelCode.setPreferredSize(d);
        }
        else{
            panelCode.setPreferredSize(n);
        }

       
    }
    
    
    /**
     * Create and places the JLabels for the Word objects in the Keyboard. It sets
     * out the distances between each JLabel as a standard size. It then sets how
     * far the scrollbars can go.
     * @param k the keyboard ArrayList
     */
    private static void newKeyboard(ArrayList<Word> k){
        switcher.setKeyboardList(k);
        int xCoord = 20;
        int yCoord = 0;
        int num=0;
        int topX = 0, topY = 0;
        int gapX = (scrollKB.getWidth() - 60) / 5;
        int gapY = spaceY * 2;
        for (Word word : k){
            String name = "key"+num;
            word.newLabel(xCoord, yCoord, name);
            word.setLabelName(name);
            panelKeyboard.add(word.getLabel());
                       
            xCoord+=gapX + word.getLabel().getWidth(); //equal width apart for number of objects but including random notatin 
            if (xCoord + 20 > topX){
                topX = xCoord + 20;
            }
            num++;
            if(xCoord > panelKeyboard.getWidth() - 30){
                yCoord += gapY;
                topY = yCoord;
                xCoord = 20;
            }
        }
        
        topX = panelKeyboard.getWidth() + 50;
        
        topY = yCoord + spaceY;
        Dimension d = new Dimension(topX, topY);
        Dimension n = scrollCode.getPreferredSize();
        if((d.height > n.height) && (d.width > n.width)){
            panelKeyboard.setPreferredSize(d);
        }
        else{
            panelKeyboard.setPreferredSize(n);
        }        
  
    }

    /**
     * Method used for generating the random Keyboard Word objects. It selects a
     * random word position from the dictionary and uses that to create the object
     * @param n number of elements in the random Keybaord
     * @return the ArrayList of the random Keyboard Words.
     */
    private ArrayList<Word> getRandomKeyboard(int n){
        ArrayList<Word> list = new ArrayList<>();
        ArrayList<String> funcList = data.getFunctionList();
        Random rnd = new Random();
        int size = funcList.size();
        String s;
        for (int i = 0; i < n; i++){
            do {
                s = funcList.get(rnd.nextInt(size));
            } while(s.equals("@newline@"));
            
            Word w = new Word(s, false, switcher, true, data.getDictionary());
            list.add(w);
        }
        return list;
    }   
    
    /**
     * Sets the moves left on the UI
     * @param moves 
     */
    public static void setMovesLeft (int moves){
        lblMoves.setText("Moves Left:" + moves);
        lblMoves.setSize(lblMoves.getPreferredSize());
    }
    
    
    public static Color getCodeBackground(){
        return panelCode.getBackground();
    }
    
    /**
     * A method for getting closing the Main form
     */
    public void close(){
        dispose();
    }
    
    /**
     * 
     * @return is there are blanks in the code 
     */
    public static boolean isGaps(){
        return gaps;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheck;
    private javax.swing.JButton btnPython;
    private javax.swing.JButton btnRemove;
    private static javax.swing.JLabel lblHelp1;
    private static javax.swing.JLabel lblHelp2;
    private static javax.swing.JLabel lblMoves;
    private javax.swing.JLabel lblNum;
    private javax.swing.JLabel lblSpace;
    private javax.swing.JLabel lblTask;
    private static javax.swing.JTextArea msgBoard;
    private javax.swing.JScrollPane msgScroll;
    private static javax.swing.JPanel panelCode;
    private javax.swing.JPanel panelFunction;
    private static javax.swing.JPanel panelKeyboard;
    private java.awt.Panel panelTask;
    private static javax.swing.JScrollPane scrollCode;
    private static javax.swing.JScrollPane scrollKB;
    // End of variables declaration//GEN-END:variables
}
