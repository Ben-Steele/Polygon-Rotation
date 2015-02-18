import java.awt.*;
import javax.swing.*;

public class Window {
    
    public static void main(String[] args) {
	JFrame frame = new JFrame();
	frame.setTitle("cube");
	frame.setSize(500,500);
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	DrawingPanel dPanel = new DrawingPanel();
	ControlPanel cp = new ControlPanel(dPanel);
	TopControl tp = new TopControl(dPanel);
	BottomControl bt = new BottomControl(dPanel);
	Container c = frame.getContentPane();
	c.setLayout(new BorderLayout());
	c.add(dPanel, BorderLayout.CENTER);
	c.add(tp,BorderLayout.NORTH);
	c.add(cp,BorderLayout.EAST);
	c.add(bt,BorderLayout.SOUTH);
	
	frame.pack();
	frame.setVisible(true);
	frame.setResizable(true);
    }
}
