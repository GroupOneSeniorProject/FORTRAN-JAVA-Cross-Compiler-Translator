


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
//import org.jdesktop.layout.GroupLayout;
//import org.jdesktop.layout.LayoutStyle;


public class GUI extends javax.swing.JPanel {

    /**
     * Creates new form GUI
     */
    int count = 0;
    private void jFileChooser1ActionPerformed(ActionEvent e) {
         count++;
         System.out.print(count);
        // TODO add your code here
        int result = jFileChooser1.showSaveDialog(this);//  JFileChooser.APPROVE_OPTION;
        //System.out.print(returnVal);
        if( result == jFileChooser1.APPROVE_OPTION ){
           File inputFile = jFileChooser1.getSelectedFile();
          LexicalAnalyzer lex = new LexicalAnalyzer(inputFile);

        // copy the content in Java Linked list to jTextArea1 of GUI
       for (int i = 0 ; i < LexicalAnalyzer.Java.size(); i++) {
            jTextArea1.append("  "+ LexicalAnalyzer.Java.get(i) + "\n");
        }
      }
    }

    /*
    private void jButton1ActionPerformed(ActionEvent e) {
        // TODO add your code here

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {

            //String content = "This is the content to write into file\n";
            File dest = new File("C:\\Users\\Nelsosn Tsaku\\Desktop\\javaOut.txt");
            fw = new FileWriter(dest);
            bw = new BufferedWriter(fw);
            for (int i = 0 ; i < LexicalAnalyzer.Java.size(); i++) {
                bw.write(LexicalAnalyzer.Java.get(i) + "\n");
            }
            JOptionPane.showMessageDialog(null,"Output saved at Directory "+ dest.getPath());
            LexicalAnalyzer.Java.clear();

        } catch (IOException ex) {

            ex.printStackTrace();

        } finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

    }
    */

    public GUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - NELSON ZANGE TSAKU
    private void initComponents() {
        jLabel1 = new JLabel();
        jFileChooser1 = new JFileChooser();
        jLabel2 = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jLabel3 = new JLabel();
        jButton1 = new JButton();

        //======== this ========
        setBackground(new Color(0, 153, 255));

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


        //---- jLabel1 ----
        jLabel1.setBackground(Color.white);
        jLabel1.setFont(new Font("Times New Roman", Font.BOLD, 18));
        jLabel1.setText("Fortran To JAVA Cross-Compiler");

        //---- jFileChooser1 ----
        jFileChooser1.addActionListener(e -> {
			jFileChooser1ActionPerformed(e);
			jFileChooser1ActionPerformed(e);
		});

        //---- jLabel2 ----
        jLabel2.setText("Browse you source file below");

        //======== jScrollPane1 ========
        {

            //---- jTextArea1 ----
            jTextArea1.setColumns(20);
            jTextArea1.setRows(5);
            jScrollPane1.setViewportView(jTextArea1);
        }

        //---- jLabel3 ----
        jLabel3.setText("JAVA output");

        //---- jButton1 ----
        jButton1.setText("SAVE");
        //jButton1.addActionListener(e -> jButton1ActionPerformed(e));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 37, Short.MAX_VALUE)
                    .addComponent(jFileChooser1, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE)
                    .addGap(43, 43, 43))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addGap(194, 194, 194)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(224, 224, 224)
                            .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(485, 485, 485)
                            .addComponent(jButton1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 511, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jFileChooser1, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jButton1)
                    .addGap(55, 55, 55))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - NELSON ZANGE TSAKU
    private JLabel jLabel1;
    private JFileChooser jFileChooser1;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private JLabel jLabel3;
    private JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
