Welcome to the Math Things library! This project contains several classes and methods that aids in creating CAS projects.

CLASSES:
  MathBlock- used to store mathematical expressions
  Point - used to store points
  Relationship - used to store equations and inequalities
  mathFrom - generates MathBlocks (integrated into MathBlock)
  Vector - used to store either 2d or 3d vectors
  VectorFunc - used to store vector/parametric functions in either 2d or 3d spaces

METHODS:

  MATHBLOCK METHODS:
    
    MathBlock(String str) (constructor):
      generates a MathBlock from a string representing a mathematical expression. MathBlock the following operators: +, -, *, /, sin(), cos(), tan(), sec(), cot(), csc(), cot(), and ln(). 
      Examples of MathBlock strings: 
        "5*x"
        "sin(2^x)*ln(x)"
        "sin(cos(tan(sec(cot(csc(10))))))" (I mean, if you really wanted to)

    MathBlock() (constructor):
      generates an empty MathBlock

    MathBlock drfCopy():
      returns a copy of the MathBlock, dereferenced from the original MathBlock. Similar to clone() methods.

    float result():
      returns a float that represents the value of a MathBlock. Will return error if the MathBlock contains a variable.

    void reveal():
      puts a printout to the console that shows the structure of the MathBlock.

    String opAsString():
      returns a String representing the primary operator of a MathBlock. Throws an error for MathBlocks without a primary operator. 

    String asString():
      returns a String that represents the MathBlock. Differs from value() in that it shows the expression, not the result. Differs from reveal() in that it is not verbose.

    void toConsole():
      prints asString() on the console.

    MathBlock derivative(String wrt):
      returns a MathBlock that represents the derivative of the MathBlock in question. Wrt represents the variable to differentiate the MathBlock by.

    MathBlock derivative():
      returns the derivative of the MathBlock with respect to x.

    boolean isResolvable():
      returns true if and only if the MathBlock does not contain any variables (i.e., if result() can be used).

    MathBlock simplified():
      returns a MathBlock that is a simplified, equivalent version of the original MathBlock. DOES NOT MUTATE THE ORIGINAL MATHBLOCK.
    
    MathBlock simplify():
      returns a MathBlock that is a simplified, equivalent version of the original MathBlock. Also mutates the original MathBlock to that simplified value.

    MathBlock substitute(String var, float number):
      replaces all instances of variable var with the number, number.

    float resultAt(String wrt, float value):
      finds the result of a function at a certain value for a certain variable, essentially finding a point on the function at a certain x-value. MathBlock must be resolvable other than the wrt variable.

    ArrayList<Point> calcApproxZeros(String wrt, float min, float max):
      returns an ArrayList of point classes, representing the approximate zeros of the function on the variable wrt on the interval between min and max. MathBlock must be resolvable other than the wrt variable.

    float maximum(float min, float max):
      finds the approximate maximum value of the function on the interval from min to max. If the min>max, an error is thrown. MathBlock must be resolvable other than the wrt variable.

    float minimum(float min, float max):
      finds the approximate minimum value of the function on the interval from min to max.  If the min>max, an error is thrown. MathBlock must be resolvable other than the wrt variable.

    float integralApprox(String wrt, float min, float max):
      Approximates the integral of the function along the variable wrt on the interval greater than min and less than max. MathBlock must be resolvable other than the wrt variable.

  POINT METHODS:

    Point(float xval, float yval) (constructor):
      constructs a Point class at a certain x and y value
    
    float radius():
      calculates the r value of the polar representation of a point

    float theta():
      calculates the theta value of the polar representation of a point

    boolean isZero():
      returns true if and only if the point is a zero.

    boolean isZero(float thresh):
      returns true if the point is within a certain threshold of being a zero.

    String asString():
      returns a String representation of the point.

    float curvilinearVal(MathBlock func, String wrt1, String wrt2, float min, float max):
      calculates the value of the point on an arbitrary curvilinear system, where wrt1 is the x-equivalent and wrt2 is the y-equivalent. The curvilinear value must be between min and max, otherwise an error will be thrown. If the min>max, an error is thrown.

    VECTOR METHODS:

      Vector(float xval, float yval) (constructor):
        generates a 2 dimensional vector.

      Vector(float xval, float yval, float zval) (constructor):
        generates a 3 dimensional vector.
      
      Vector drfCopy():
        returns a copy of the Vector, dereferenced from the original Vector. Similar to clone() methods.

      float mag():
        returns the magnitude of the vector.

      float magSQ():
        returns the square the magnitude of the vector.

      String asString():
        returns a string that represents the vector.

      void add(Vector v):
        adds a second vector to the original.

      void sub(Vector v):
        subtracts a second vector from the original.

      void mult(float s):
        multiplies the vector by a scalar.

      void div(float s):
        divides the vector by a scalar.

      float dot(Vector v):
        returns the dot product of the original vector and another.

      Vector cross(Vector v):
        returns the cross product of the original vector and another.

      Vector normalize(Vector v):
        returns a normalized vector in the same direction with a magnitude of 1.

      Vector rotate(float ang):
        returns a rotated copy of the original vector. The vector is rotated in the XY plane.

    VECTORFUNC METHODS:

      VectorFunc(MathBlock xb, MathBlock yb, MathBlock zb):
        generates a 3D vector function.

      VectorFunc(MathBlock xb, MathBlock yb):
        generates a 2D vector function.

      VectorFunc drfCopy():
        returns a copy of the VectorFunc, dereferenced from the original VectorFunc. Similar to clone() methods.

      String asString():
        returns a string representing the VectorFunc.

      Vector resultAt(String wrt, float value):
        returns the vector result at a specified value of the function.

      VectorFunc derivative(String wrt):
        returns the derivative of the vector function with respect to the variable wrt.

      float length(String wrt, float min, float max):
        returns the length of the vector function with respect to a certain variable along the interval greater than min and less than max. 

    RELATIONSHIP METHODS:

      boolean isTrue():
        Determines if the relationship describes is true or not.