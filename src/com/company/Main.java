import javax.swing.*;

public class Main
{

    public static void main(String[] args)
    {
        JFrame newJF = new JFrame("J 2 F Compiler");
        GUI g = new GUI();
        newJF.setContentPane(g);
        newJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newJF.pack();
        newJF.setVisible(true);

       // LexicalAnalyzer lex = new LexicalAnalyzer();
    }
}