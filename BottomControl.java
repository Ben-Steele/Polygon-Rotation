import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

class BottomControl extends JPanel implements ChangeListener
 {
     DrawingPanel cPanel;
     JSlider xSlider, ySlider, zSlider, dSlider;
     int xState = 0;
     int yState = 0;
     int zState = 0;
     int dState;
     
     public BottomControl(DrawingPanel cp)
     {
	 cPanel=cp;	
	 
	 setLayout(new GridLayout(1,3,30,10));
	 //control rotation about the arbitrary axis
	 dSlider = new JSlider(JSlider.HORIZONTAL,-100,100,0);
	 dSlider.setMajorTickSpacing(25);
	 dSlider.setMinorTickSpacing(5);
	 dSlider.setPaintTicks(true);
	 dSlider.addChangeListener(this);
	 JLabel dlabel = new JLabel("Rotation");
	 JPanel d = new JPanel();
	 d.setLayout(new BoxLayout(d, BoxLayout.Y_AXIS));
	 d.add(dlabel);
	 d.add(dSlider);
	 add(d);
	 //control x position of arbitrary axis
	 xSlider = new JSlider(JSlider.HORIZONTAL,-50,50,0);
	 xSlider.setMajorTickSpacing(25);
	 xSlider.setMinorTickSpacing(5);
	 xSlider.setPaintTicks(true);
	 xSlider.addChangeListener(this);
	 JLabel xlabel = new JLabel("Vector X Position");
	 JPanel x = new JPanel();
	 x.setLayout(new BoxLayout(x, BoxLayout.Y_AXIS));
	 x.add(xlabel);
	 x.add(xSlider);
	 add(x);
	 //control y position of arbitrary axis
	 ySlider = new JSlider(JSlider.HORIZONTAL,-50,50,1);
	 ySlider.setMajorTickSpacing(25);
	 ySlider.setMinorTickSpacing(5);
	 ySlider.setPaintTicks(true);
	 ySlider.addChangeListener(this);
	 JLabel ylabel = new JLabel("Vector Y Position");
	 JPanel y = new JPanel();
	 y.setLayout(new BoxLayout(y, BoxLayout.Y_AXIS));
	 y.add(ylabel);
	 y.add(ySlider);
	 add(y);
	 //control z position of arbitrary axis
	 zSlider = new JSlider(JSlider.HORIZONTAL,-50,50,0);
	 zSlider.setMajorTickSpacing(25);
	 zSlider.setMinorTickSpacing(5);
	 zSlider.setPaintTicks(true);
	 zSlider.addChangeListener(this);
	 JLabel zlabel = new JLabel("Vector Z Position");
	 JPanel z = new JPanel();
	 z.setLayout(new BoxLayout(z, BoxLayout.Y_AXIS));
	 z.add(zlabel);
	 z.add(zSlider);
	 add(z);
     }
     public void stateChanged(ChangeEvent ev) {
	 JSlider s = (JSlider)ev.getSource();
	 if(s == dSlider) {
	     if (s.getValue()<dState) {
		 cPanel.arbitraryRotate(-.05);
	     }
	     else if(s.getValue()>dState) {
		 cPanel.arbitraryRotate(.05);
	     }
	     dState = s.getValue();
	 }
	 if(s == xSlider){
	     cPanel.setRotationVector(s.getValue(),yState,zState);
	     xState = s.getValue();
	 }
	 else if(s == ySlider) {
	     cPanel.setRotationVector(xState,s.getValue(),zState);
	     yState = s.getValue();
	 }
	 else if(s == zSlider) {
	     cPanel.setRotationVector(xState,yState,s.getValue());
	     zState = s.getValue();
	 }
     }
}
