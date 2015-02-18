import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Random;

public class Face {
    //each face stores the indices of its vertices
    //these index the vertices in the vList of the Cube class
    int[] vertices;
    //color of the face
    Color c;
    //initiate a face with a random color and the given vertices
    public Face(int[] v){
	Random r = new Random();
	vertices = v;
	c = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
    }
    public Color getColor() {
	return c;
    }

    public int getIndex(int i) {
	return vertices[i];
    }
    //return the size of the list of indices
    public int size() {
	return vertices.length;
    }
}
