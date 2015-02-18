import java.util.Arrays;
//simple class to store each vertex
public class Vertex {
    //each vertex is stored as 3 component array
    double[] point = new double[3];
    //initiate a vertex with the given coordinates
    public Vertex(double x, double y, double z){
	point[0] = x;
	point[1] = y;
	point[2] = z;
    }
    
    public double[] getPoint() {
	return point;
    }

    public double getX() {
	return point[0];
    }

    public double getY() {
	return point[1];
    }

    public double getZ() {
	return point[2];
    }
    //subtract the given vertex from this vertex
    public Vector sub(Vertex in) {
	//subtract each component of the given vertex from this vertex
	double x = point[0]-in.getX();
	double y = point[1]-in.getY();
	double z = point[2]-in.getZ();
	return new Vector(x,y,z);
    }

    public String toString() {
	return ("x: " + point[0] + " y: " + point[1] + " z: " + point[2]);
    }
    
}
