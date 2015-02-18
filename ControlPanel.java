import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
//pretty strait forward
public class ControlPanel extends JPanel implements ActionListener {
    DrawingPanel dPanel;
    JButton b1,b2,b3,b4,b5,b6;
    public ControlPanel(DrawingPanel cp) {
	dPanel = cp;

	setLayout(new GridLayout(10,1,0,0));

	b1 = new JButton("Cube");
	JPanel a = new JPanel();
	b1.addActionListener(this);
	a.setLayout(new BoxLayout(a, BoxLayout.Y_AXIS)); 
	a.add(b1);
	add(a);

	b2 = new JButton("Octahedron");
	JPanel b = new JPanel();
	b2.addActionListener(this);
	b.setLayout(new BoxLayout(b, BoxLayout.Y_AXIS)); 
	b.add(b2);
	add(b);

	b4 = new JButton("Great Rhombicuboctahedron");
	JPanel d = new JPanel();
	b4.addActionListener(this);
	d.setLayout(new BoxLayout(d, BoxLayout.Y_AXIS)); 
	d.add(b4);
	add(d);

	b5 = new JButton("Dodecahedron");
	JPanel e = new JPanel();
	b5.addActionListener(this);
	e.setLayout(new BoxLayout(e, BoxLayout.Y_AXIS)); 
	e.add(b5);
	add(e);

	b6 = new JButton("Rhombicuboctahedron");
	JPanel f = new JPanel();
	b6.addActionListener(this);
	f.setLayout(new BoxLayout(f, BoxLayout.Y_AXIS)); 
	f.add(b6);
	add(f);
	
	b3 = new JButton("Change View");
	JPanel c = new JPanel();
	b3.addActionListener(this);
	c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS)); 
	c.add(b3);
	add(c);
	
    }
    public void actionPerformed(ActionEvent e) {
	if(e.getSource() == b1) {
	    dPanel.loadShape("Cube.txt");
	    dPanel.scale(50);
	}
	else if(e.getSource() == b2) {
	    dPanel.loadShape("Octahedron.txt");
	    dPanel.scale(75);
	}
	else if(e.getSource() == b4) {
	    dPanel.loadShape("GreatRhombicuboctahedron.txt");
	    dPanel.scale(75);
	}
	else if(e.getSource() == b5) {
	    dPanel.loadShape("Dodecahedron.txt");
	    dPanel.scale(75);
	}
	else if(e.getSource() == b6) {
	    dPanel.loadShape("Rhombicuboctahedron.txt");
	    dPanel.scale(75);
	}
	else if(e.getSource() == b3) {
	    dPanel.changeSurface();
	}
    }
}
