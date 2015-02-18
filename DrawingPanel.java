import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DrawingPanel extends JPanel {
    //creates a 3D shape which stores all its faces
    Cube cube;
    //z coordinate that the eye position is at. eye is always on the z axis
    int distance = 300;
    //If surface is false a wire frame of the shape will be drawn
    //if it is true the surfaces facing the eye are drawn and filled with color
    boolean surface = true;
    //vector which is used for an arbitrary rotation
    //this can be set in the bottom control panel
    Vector rotationVector;
    
    public DrawingPanel() {
	setPreferredSize(new Dimension(300,300));
	setBackground(Color.white);
	//initiate rotation vector to be on the y axis
	rotationVector = new Vector(0,1,0);
    }

    public void paintComponent(Graphics G) {
        super.paintComponent(G);
	Graphics2D g2d = (Graphics2D)G;
	//move origin to the center of the canvas
	g2d.translate(getWidth()/2,getHeight()/2);
	//positive y are above the axis
	g2d.scale(1,-1);
	//make sure a 3D shape has been made before trying to draw it
	if(cube != null) {
	    //check if the surface or wire frame should be drawn
	    if(surface == true) {
		//draw surface of the shape
		cube.surface(g2d,distance);
	    }
	    else{
		//normalize the rotation vector
		rotationVector.normalize();
		//scale the rotation vector to 140 times its normal
		rotationVector.scale(140);
		//get the projection of the 3D rotation vector onto the 2D screen
		double[] point = rotationVector.get2D(distance);
		//draw the rotation vector 
		g2d.drawLine(0,0,(int)point[0],(int)point[1]);
		//draw the wire frame of the 3D shape
		cube.wireFrame(g2d,distance);
	    }
	}
    }
    //set the rotation vector to the given x,y,z values
    public void setRotationVector(int x, int y, int z) {
	rotationVector.set(x,y,z);
	repaint();
    }
    //pass degree and rotationVector into the arbitratyRotate method of the Cube class
    public void arbitraryRotate(Double degree) {
	cube.arbitratyRotate(degree, rotationVector);
	repaint();
    }
    //take in a txt file and scan through it to create a 3D shape in the Cube class
    public void loadShape(String fileName) {
	try {
            //Load file
            File inputFile = new File(fileName);
            Scanner scan = new Scanner(inputFile);
	    String line;
            //the first 3 lines don't contain any info I need
            scan.nextLine();
	    scan.nextLine();
	    scan.nextLine();
	    //read in the number of vertices the shape will have
	    int vertices = scan.nextInt();
	    scan.nextLine();
	    //read in the number of faces the shape will have
	    int faceNumber = scan.nextInt();
	    scan.nextLine();
	    scan.nextLine();
	    scan.nextLine();
	    double x,y,z;
	    //create an array of verices to pass into the Cube constructor
	    Vertex[] vList = new Vertex[vertices];
	    //this loop runs once for each vertex
	    for(int i=0;i<vertices;i++) {
		x = scan.nextDouble();
		y = scan.nextDouble();
		z = scan.nextDouble();
		//create a vertex object from the x,y,z scanned in from the file
		Vertex current = new Vertex(x,y,z);
		//add the vertex to the array
		vList[i] = current;
	    }
	    //create a Cube(3D shape) with the list of vertices
	    cube = new Cube(vList);
	    scan.nextLine();
	    scan.nextLine();
	    scan.nextLine();
	    for(int i=0;i<faceNumber;i++) {
		line = scan.nextLine();
		//split this line by spaces to find the number of elements in the line
		String[] split = line.split(" ");
		//create an array of integers to store position of vertices in the vList array
		//this array will store the position for all the vertices of one face
		int[] v = new int[split.length-1];
		//iterate through split leaving out the last element, it is not a vertex
		for(int j=0;j<split.length-1;j++) {
		    //get the integer index of the vertex, indexes into vList
		    int nFace = Integer.parseInt(split[j]);
		    //add the index to the v array
		    v[j] = nFace;
		}
		//create a face for the Cube(3D shape) using the values read in above
		cube.setFace(v);
	    }
	}
	//make sure the file exists
        catch(FileNotFoundException e) {
            System.out.println("ERROR! " + e);      
        }
    }
    //pass the degree and axis to the rotate method in Cube class
    //the axis input is either "x","y","z"
    public void rotate(double degree, String axis) {
        cube.rotate(degree,axis);
	repaint();
    }
    //pass the scaling variable through to Cube class
    public void scale(double a) {
        cube.scale(a);
	repaint();
    }
    //set the viewing distance to d
    public void setDistance(int d) {
	distance = d;
	repaint();
    }
    //this method switches the view between surface and wireFrame
    public void changeSurface() {
	if(surface == true) {
	    surface = false;
	}
	else{
	    surface = true;
	}
	repaint();
    }
}
