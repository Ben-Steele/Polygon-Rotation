import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

class TopControl extends JPanel implements ChangeListener
 {
     DrawingPanel cPanel;
     JSlider xSlider, ySlider, zSlider, dSlider;
     int xState, yState, zState;
     
     public TopControl(DrawingPanel cp)
     {
	 cPanel=cp;	
	 
	 setLayout(new GridLayout(1,3,30,10));
	 //controls the distance the eye is from the origin on the z axis
	 dSlider = new JSlider(JSlider.HORIZONTAL,0,600,300);
	 dSlider.setMajorTickSpacing(25);
	 dSlider.setMinorTickSpacing(5);
	 dSlider.setPaintTicks(true);
	 dSlider.addChangeListener(this);
	 JLabel dlabel = new JLabel("Distance");
	 JPanel d = new JPanel();
	 d.setLayout(new BoxLayout(d, BoxLayout.Y_AXIS));
	 d.add(dlabel);
	 d.add(dSlider);
	 add(d);
	 
	 xSlider = new JSlider(JSlider.HORIZONTAL,-100,100,0);
	 xSlider.setMajorTickSpacing(25);
	 xSlider.setMinorTickSpacing(5);
	 xSlider.setPaintTicks(true);
	 xSlider.addChangeListener(this);
	 JLabel xlabel = new JLabel("X-axis");
	 JPanel x = new JPanel();
	 x.setLayout(new BoxLayout(x, BoxLayout.Y_AXIS));
	 x.add(xlabel);
	 x.add(xSlider);
	 add(x);

	 ySlider = new JSlider(JSlider.HORIZONTAL,0,200,100);
	 ySlider.setMajorTickSpacing(25);
	 ySlider.setMinorTickSpacing(5);
	 ySlider.setPaintTicks(true);
	 ySlider.addChangeListener(this);
	 JLabel ylabel = new JLabel("Y-axis");
	 JPanel y = new JPanel();
	 y.setLayout(new BoxLayout(y, BoxLayout.Y_AXIS));
	 y.add(ylabel);
	 y.add(ySlider);
	 add(y);

	 zSlider = new JSlider(JSlider.HORIZONTAL,-100,100,0);
	 zSlider.setMajorTickSpacing(25);
	 zSlider.setMinorTickSpacing(5);
	 zSlider.setPaintTicks(true);
	 zSlider.addChangeListener(this);
	 JLabel zlabel = new JLabel("Z-axis");
	 JPanel z = new JPanel();
	 z.setLayout(new BoxLayout(z, BoxLayout.Y_AXIS));
	 z.add(zlabel);
	 z.add(zSlider);
	 add(z);
     }
     public void stateChanged(ChangeEvent ev) {
	 JSlider s = (JSlider)ev.getSource();
	 if(s == dSlider) {
	     cPanel.setDistance(s.getValue());
	 }
	 if(s == xSlider){
	     if (s.getValue()<xState) {
		 cPanel.rotate(-.05,"x");
	     }
	     else if(s.getValue()>xState) {
		 cPanel.rotate(.05,"x");
	     }
	     xState = s.getValue();
	 }
	 else if(s == ySlider) {
	     if (s.getValue()<yState) {
		 cPanel.rotate(-.05,"y");
	     }
	     else if(s.getValue()>yState) {
		 cPanel.rotate(.05,"y");
	     }
	     yState = s.getValue();
	 }
	 else if(s == zSlider) {
	     if (s.getValue()<zState) {
		 cPanel.rotate(-.05,"z");
	     }
	     else if(s.getValue()>zState) {
		 cPanel.rotate(.05,"z");
	     }
	     zState = s.getValue();
	 }
     }
}
