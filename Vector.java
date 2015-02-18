import java.util.Arrays;

public class Vector {
    //the vector is stored as an array of 3 doubles
    double[] vector = new double[3];
    //initiate the vector with the given coordinates
    public Vector(double x, double y, double z) {
	vector[0] = x;
	vector[1] = y;
	vector[2] = z; 
    }
    //compute the dot product of this vector and the input
    public double dot(Vector in) {
	//multiply corresponding components then add the products
	return vector[0]*in.getX() + vector[1]*in.getY() + vector[2]*in.getZ();
    }
    //compute the cross product of this vector and the input
    public Vector cross(Vector in) {
	//x,y,z of a 3D dot product
	double x = vector[1]*in.getZ()-vector[2]*in.getY();
	double y = -(vector[0]*in.getZ()-vector[2]*in.getX());
	double z = vector[0]*in.getY()-vector[1]*in.getX();
	return new Vector(x,y,z);
    }
    //set Vector coordinates
    public void set(double x, double y, double z) {
	vector[0] = x;
	vector[1] = y;
	vector[2] = z; 
    }
    
    public double getX() {
	return vector[0];
    }

    public double getY() {
	return vector[1];
    }

    public double getZ() {
	return vector[2];
    }
    
    public String toString() {
	return "" + vector[0] + " " + vector[1] + " " + vector[2];
    }
    //scale the vector
    public void scale(double s) {
	//multiply each component by the scalar
	vector[0] = s*vector[0];
	vector[1] = s*vector[1];
	vector[2] = s*vector[2];
    }
    //normalize the vector
    public void normalize(){
	//magnitude = sqrt(x^2 + y^2 + z^2)
	double mag = Math.sqrt(Math.pow(vector[0],2) + Math.pow(vector[1],2) + Math.pow(vector[2],2));
	//coordinate = coordinate/magnitude
	vector[0] = vector[0]/mag;
	vector[1] = vector[1]/mag;
	vector[2] = vector[2]/mag;
    }
    //returns an array of the coordinates (x,y) representing a 3D vector in 2D
    //depends on the input distance
    public double[] get2D(int distance) {
	//x = x*distance/(distance-z)
	double x = vector[0]*distance/(distance-vector[2]);
	//y = y*distance/(distance-z)
	double y = vector[1]*distance/(distance-vector[2]);
	double[] returnable = {x,y};
	return returnable;
    }
}
