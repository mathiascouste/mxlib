package test.reflexivity;

import javax.swing.JFrame;
import javax.swing.JPanel;

import eu.couste.common.reflexivity.GetterExplorer;


public class GetterExplorerTest {

    public static void main(String[] args) {
        GetterExplorer ge = new GetterExplorer();
        ge.init(new JFrame(), new JPanel(), new Object());
        ge.addRow(new JFrame(), new JPanel());
        ge.addRow(new JFrame(), new JPanel());
        ge.addRow(new JFrame(), new JPanel());
        System.out.println(ge.getTable());
    }
}
