import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
//the class is called cube but can draw any convex 3D shape
public class Cube {
    //store the list of vertices for the 3D shape
    Vertex[] vList;
    //store a list of all the faces of the shape which will reference the vertex list
    List<Face> faces = new ArrayList<Face>();
    //the transform for the shape is updated whenever the shape is moved
    //the transform is only applied right before drawing
    //the vertexes always stay the same
    Matrix3D transform;
    //create a cube with the given list of vertices
    public Cube(Vertex[] vListIn) {
     	vList = vListIn;
	transform = new Matrix3D();
    }
    //create a face for the shape
    //the vertices input is an array of indices for the vertices in vList
    public void setFace(int[] vertices) {
	Face tempFace = new Face(vertices);
	faces.add(tempFace);
    }
    //apply a scale to the transform of the shape
    public void scale(double a){
	transform.scale(a);
    }
    //rotate counter clockwise looking down specified axis
    //axis is a string, either "x", "y", or "z"
    public void rotate(double degree, String axis){
	transform.rotate(degree, axis);
    }
    //rotate around the given vector
    public void arbitratyRotate(double degree, Vector v) {
	//first normalize the input vector
	v.normalize();
	//assign variables to the components of the vector
	double a = v.getX();
	double b = v.getY();
	double c = -v.getZ();
	//d = sqrt(b^2+c^2)
	double d = Math.sqrt(Math.pow(b,2)+Math.pow(c,2));
	//create a temporary matrix which will be used to concatenate to the main transform
	Matrix3D tran = new Matrix3D();
	//if d is 0 I want to avoid dividing by 0 so I make it a separate case
	if (d == 0) {
	    //rotate about the y axis to get on the z axis
	    //this 2D array represents the rotation
	    double[][] matrix = {{d,0,-a},{0,1,0},{a,0,d}};
	    //set the values of tran to the 2D array above
	    tran.setValues(matrix);
	    //apply tran to the main transform
	    transform.concat(tran);
	    //rotate around the z axis by the input degree
	    matrix = new double[][] {{Math.cos(degree),-Math.sin(degree),0},{Math.sin(degree),Math.cos(degree),0},{0,0,1}};
	    tran.setValues(matrix);
	    transform.concat(tran);
	    //rotate about the y axis to get back to the original orientation
	    matrix = new double[][] {{d,0,a},{0,1,0},{-a,0,d}};
	    tran.setValues(matrix);
	    transform.concat(tran);
	}
	else{
	    //rotate about the x axis the get on the xz plane
	    double[][] matrix = {{1,0,0},{0,c/d,-b/d},{0,b/d,c/d}};
	    tran.setValues(matrix);
	    transform.concat(tran);
	    //rotate about the y axis to get on the z axis
	    matrix = new double[][] {{d,0,-a},{0,1,0},{a,0,d}};
	    tran.setValues(matrix);
	    transform.concat(tran);
	    //rotate around the z axis by the input degree
	    matrix = new double[][] {{Math.cos(degree),-Math.sin(degree),0},{Math.sin(degree),Math.cos(degree),0},{0,0,1}};
	    tran.setValues(matrix);
	    transform.concat(tran);
	    //rotate about the y axis to reverse the above y axis rotation
	    matrix = new double[][] {{d,0,a},{0,1,0},{-a,0,d}};
	    tran.setValues(matrix);
	    transform.concat(tran);
	    //rotate about the x axis to treverse the above x axis rotation
	    matrix = new double[][] {{1,0,0},{0,c/d,b/d},{0,-b/d,c/d}};
	    tran.setValues(matrix);
	    transform.concat(tran);
	}
    }

    //project shapes onto screen which is "distance" away from the eye
    public void surface(Graphics2D g, int distance) {
	//create an array for the transformed vertices
	Vertex[] tVertices = new Vertex[vList.length];
	//for each vertex in vList, apply the main transformation and save it in tVertices
	for(int i=0;i<vList.length;i++) {
	     tVertices[i]= transform.mult(vList[i]);
	}
	for(Face f : faces) {
	    //check if the face is visible from the viewing angle
	    if(isVisible(f,tVertices,distance) == true){
		//convert the world coordinates to screen coordinates and draw the face
		perspective(g,f,tVertices,distance, true);
	    }
	}
    }

    public boolean isVisible(Face f, Vertex[] v, int distance) {
	//vertexes are given in clockwise order
	//create a vector which is the second vertex minus the first vertex
	Vector v1 = v[f.getIndex(1)].sub(v[f.getIndex(0)]);
	//create a vector which is the last vertex minus the first vertex
	Vector v2 = v[f.getIndex(f.size()-1)].sub(v[f.getIndex(0)]);
	//create a vector from the eye to the first vertex
	Vector eye = v[f.getIndex(0)].sub(new Vertex(0,0,distance));
	//The cross product of v1 and v2 is an outward facing normal
	//the dot product of the eye vector and this normal will be
	//less than zero if the face is visible
	if (eye.dot(v1.cross(v2)) < 0) {
	    return true;
	}
	return false;
    }
    //draws all the edges of the shape as lines
    public void wireFrame(Graphics2D g, int distance) {
	//create an array for the transformed vertices
	Vertex[] tVertices = new Vertex[vList.length];
	//for each vertex in vList, apply the main transformation and save it in tVertices
	for(int i=0;i<vList.length;i++) {
	     tVertices[i]= transform.mult(vList[i]);
	}
	for(Face f : faces) {
	    //convert the world coordinates to screen coordinates and draw the face
	    perspective(g,f,tVertices,distance, false);
	}
    }
    //translate each face from world coordinates to screen coordinates
    //using graphics from the drawing panel, draw the face
    public void perspective(Graphics2D g, Face f, Vertex[] tVertices, int distance, boolean surface) {
	//create a path2D
	Path2D.Double path = new Path2D.Double();
	//flag to determin if the vertex is the first vertex
	boolean flag = true;
	double x,y;
	//iterate through each vertex in the face
	for(int i=0;i<f.size();i++) {
	    //world x to screen x
	    //screen x = (world x)* distance / (distance-(world z))
	    x = tVertices[f.getIndex(i)].getX()*distance/(distance-tVertices[f.getIndex(i)].getZ());
	    //screen y = (world y)* distance / (distance-(world z))
	    y = tVertices[f.getIndex(i)].getY()*distance/(distance-tVertices[f.getIndex(i)].getZ());
	    //if flag is true, move path to the screen point without drawing a line
	    if (flag == true) {
		path.moveTo(x,y);
		flag = false;
	    }
	    //if flag is false, draw a line to the screen point
	    else{
		path.lineTo(x,y);
	    }
	}
	//same x,y conversion as above
	x = tVertices[f.getIndex(0)].getX()*distance/(distance-tVertices[f.getIndex(0)].getZ());
	y = tVertices[f.getIndex(0)].getY()*distance/(distance-tVertices[f.getIndex(0)].getZ());
	//draw a line back to the first vertex to close the shape
	path.lineTo(x,y);
	//if surface is true, fill each face with its color
	if (surface == true) {
	    g.setPaint(f.getColor());
	    g.fill(path);
	}
	//if surface is false, only draw the path outline
	else {
	    g.setPaint(Color.black);
	    g.draw(path);
	}
    }
}
