/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.teste;
import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author Paulo.Tenorio
 */
public class Test extends JComponent{
    public Test()
    {
        super();
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.drawLine(10, 10, 100, 10);
        g.drawLine(10, 10, 10, 100);
    }
}
