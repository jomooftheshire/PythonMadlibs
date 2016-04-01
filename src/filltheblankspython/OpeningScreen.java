/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filltheblankspython;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Joshua Mulcock
 */
public class OpeningScreen extends javax.swing.JDialog {

    private boolean pythree, windows;
    private String pyLocation;
    
    /**
     * Creates new form OpeningScreen.
     * It finds out if the OS is windows, then if it is get the location of python.
     * If it is not; it prevents any of the Python script running methods from being
     * accessed by the user.
     */
    public OpeningScreen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        windows = getOS();
        if(windows){
            pyLocation = getPythonLocation();
        }
        else{
            btnPython.setVisible(false);
            JOptionPane.showMessageDialog(null, "Operating System detected as non-Windows. Running of Python"
                    + " scripts has been disabled");
            pythree = false;
            pyLocation = null;
        }
    }
    
    /**
     * This method is used to find out what Operating System is the program is
     * being run on. Currently will only recognise if windows or not; however preference
     * would be for later versions to work out any OS.
     * @return if the OS is windows or not
     */
    private boolean getOS(){
        boolean windows;
        String os = System.getProperty("os.name");
        os = os.toLowerCase();
        if(os.contains("windows")){
            windows = true;
        }
        else {
            windows = false;
        }
        return windows;
    }
    
    /**
     * This method is allows the user to choose the python executable to be able to
     * run python scripts from the program. It then runs the method for checking the
     * python version. NOTE: this currently only works with windows OS.
     * @return the location of the python executable
     */
    private String getPythonLocation(){
        String location; 
        int result = JOptionPane.showConfirmDialog(null, "Choose location of python.exe? "
                 + "(enables ability to run python scripts) \n CHOOSE PYTHON 3 ONLY! " 
                            , "Run Python?", JOptionPane.YES_NO_OPTION);
        if(result == 0){
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("C:/"));
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            FileNameExtensionFilter filter  = new FileNameExtensionFilter(".exe", "exe");
            fc.setFileFilter(filter);
            fc.showOpenDialog(null);
            location = fc.getSelectedFile().getAbsolutePath();
            isPythonThree(location, windows);
        }
        else{
            pythree = false;
            location = null;
            btnPython.setVisible(false);
        }
        
        return location;
    }
    
    /**
     * This method is used to determine if the version of Python that the user is
     * attempting to use. It does this by running the version checker in the cmd
     * and looking at the first digit it comes across, within the returned result.
     * NOTE: this currently only works with windows.
     * @param location the location of the python executable
     * @param windows whether the OS is windows or not
     */
    private void isPythonThree(String location, boolean windows){
        if(windows){
            int version = 0;
            try {
                Process p = Runtime.getRuntime().exec("cmd /C " + location + " -V");
                BufferedReader in = new BufferedReader(
                                new InputStreamReader(p.getInputStream()));
                String line = in.readLine();
                                
                int i = 0;
                boolean numFound = false;
                char[] sepLine = line.toCharArray();
                do {
                    if(Character.isDigit(sepLine[i])){
                        numFound = true;
                        
                        version = sepLine[i];
                    }
                    else{
                        i++;
                    }
                } while(numFound == false && i < line.length());
                
                version = Character.getNumericValue(sepLine[i]);
                System.out.println(version);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            if(version == 3){
                pythree = true;
            }
            else {
                JOptionPane.showMessageDialog(null, "Python Selected is not Version 3!"
                        + "Running Python scripts will be disabled!");
                pythree = false;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        //This is a button group that deals with the difficulty options
        Difficulty = new javax.swing.ButtonGroup();
        panelScreen = new javax.swing.JPanel();
        lblText = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        radEasy = new javax.swing.JRadioButton();
        radMedium = new javax.swing.JRadioButton();
        radHard = new javax.swing.JRadioButton();
        btnHelp = new javax.swing.JButton();
        btnPython = new javax.swing.JButton();

        Difficulty.add(radEasy);
        Difficulty.add(radMedium);
        Difficulty.add(radHard);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FIll The Blanks ~ Python Edition");
        setBackground(new java.awt.Color(204, 204, 255));

        panelScreen.setBackground(new java.awt.Color(204, 204, 255));

        lblText.setFont(new java.awt.Font("Tw Cen MT", 2, 24)); // NOI18N
        lblText.setText("Fill The Blanks ~ Python Edition");

        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        radEasy.setText("easy");

        radMedium.setText("medium");

        radHard.setText("hard");

        btnHelp.setText("Help");
        btnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpActionPerformed(evt);
            }
        });

        btnPython.setText("Find Python 3");
        btnPython.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPythonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelScreenLayout = new javax.swing.GroupLayout(panelScreen);
        panelScreen.setLayout(panelScreenLayout);
        panelScreenLayout.setHorizontalGroup(
            panelScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelScreenLayout.createSequentialGroup()
                .addGroup(panelScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelScreenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblText, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelScreenLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(panelScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radMedium)
                                    .addComponent(radEasy)
                                    .addComponent(radHard)
                                    .addComponent(btnPython))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelScreenLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnStart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(btnHelp)
                .addContainerGap())
        );
        panelScreenLayout.setVerticalGroup(
            panelScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelScreenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblText, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radEasy)
                .addGap(18, 18, 18)
                .addComponent(radMedium)
                .addGap(18, 18, 18)
                .addGroup(panelScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelScreenLayout.createSequentialGroup()
                        .addComponent(radHard)
                        .addGap(0, 50, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelScreenLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHelp)
                            .addComponent(btnStart)
                            .addComponent(btnPython))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        if(radEasy.isSelected()){
            start(0);
        }
        else if (radMedium.isSelected()){
            start(1);
        }
        else if (radHard.isSelected()){
            start(2);
        }
        else {
            JOptionPane.showMessageDialog(null, "Difficulty not selected!");
        }
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpActionPerformed
        JOptionPane.showMessageDialog(null, "<html><p><i>Fill The Blanks ~ Python Edition</i> is a educational program for learning python 3 syntax, aimed"
                + " at new users to the language</p> <p></p> <p>Developed by Joshua Mulcock</p></html>");
    }//GEN-LAST:event_btnHelpActionPerformed

    private void btnPythonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPythonActionPerformed
        pyLocation = getPythonLocation();
    }//GEN-LAST:event_btnPythonActionPerformed

    /**
     * THsi method is run to make to load up Main
     * @param gameDiff the difficulty selected 
     */
    public void start(int gameDiff){
        //Main game = new Main(new javax.swing.JFrame(), true, gameDiff, windows, pythree, pyLocation);
            Main game = new Main();
            game.main(null, gameDiff, windows, pythree, pyLocation);
            dispose();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(OpeningScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OpeningScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OpeningScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OpeningScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                OpeningScreen dialog = new OpeningScreen(new javax.swing.JFrame(), true);
                
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Difficulty;
    private javax.swing.JButton btnHelp;
    private javax.swing.JButton btnPython;
    private javax.swing.JButton btnStart;
    private javax.swing.JLabel lblText;
    private javax.swing.JPanel panelScreen;
    private javax.swing.JRadioButton radEasy;
    private javax.swing.JRadioButton radHard;
    private javax.swing.JRadioButton radMedium;
    // End of variables declaration//GEN-END:variables
}
