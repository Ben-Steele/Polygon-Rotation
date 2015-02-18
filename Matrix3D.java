
public class Matrix3D {
    //each matrix is stored as a 2D array,  it is initiated as the identity matrix
    double[][] matrix = {{1,0,0},{0,1,0},{0,0,1}};;

    public Matrix3D() {
    }

    public void concat(Matrix3D temp) {
	//create a 2D array for the values of the sum to be stored in
	double[][] sum = new double[3][3];
	//iterate through the the 2D arrays of both matrices being added
	for(int i=0;i<3;i++) {
	    for(int j=0;j<3;j++) {
		//sum at position [i][j] = (column i of this matrix) dot product (row j of temp matrix)
		sum[i][j] = (temp.getPos(0,j))*(matrix[i][0]) + (temp.getPos(1,j))*(matrix[i][1]) + (temp.getPos(2,j))*(matrix[i][2]);
	    }
	}
	//set this matrix equal to sum
	matrix = sum;
    }
    //set the values of the matrix to the given 2D array
    public void setValues(double[][] temp) {
	matrix = temp;
    }
    //rotate counter clockwise theta degrees along the given axis
    //axis is given as the String "x","y", or "z"
    public void rotate(double theta, String  axis) {
	//create a temporary matrix to store the rotation matrix
	Matrix3D temp = new Matrix3D();
	//compute sin(theta) and cos(theta) once
	double s = Math.sin(theta);
	double c = Math.cos(theta);
	//rotation matrix for the x axis
	if(axis == "x") {
	    double[][] at = {{1,0,0},{0,c,-s},{0,s,c}};
	    temp.setValues(at);
	}
	//rotation matrix for the y axis
	else if(axis == "y") {
	    double[][] at = {{c,0,s},{0,1,0},{-s,0,c}};
	    temp.setValues(at);
	}
	//rotation matrix for the z axis
	else if(axis == "z") {
	    double[][] at = {{c,-s,0},{s,c,0},{0,0,1}};
	    temp.setValues(at);
	}
	//apply the rotation matrix to the current matrix
	this.concat(temp);
    }
    //scale the matrix by a constant
    public void scale(double a) {
	//create a temporary matrix to store the scale matrix
	Matrix3D temp = new Matrix3D();
	//this 2D array will scale a matrix by a
	double[][] at = {{a,0,0},{0,a,0},{0,0,a}};
	temp.setValues(at);
	this.concat(temp);
    }
    //get the value at position [a][b] in this matrix
    public double getPos(int a,int b) {
	return matrix[a][b];
    }
    //get the entire 2D array for this matrix
    public double[][] getMat() {
	return matrix;
    }
    //multiply a vertex by this matrix
    public Vertex mult(Vertex point) {
	//array to store the transformed vertex
	double[] temp = new double[3];
	//iterate through each coordinate of the vertex
	for(int i=0;i<3;i++) {
	    //coordinate = (coordinate) dot (corresponding row of this matrix)
	    temp[i] = point.getX()*matrix[0][i]+point.getY()*matrix[1][i]+point.getZ()*matrix[2][i];
	}
	//make a new vertex with the transformed coordinates
	Vertex tempV = new Vertex(temp[0],temp[1],temp[2]);
	return tempV;
    }
}
