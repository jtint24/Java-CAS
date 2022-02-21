# Java-CAS

Welcome to my Java Computer Algebra System! This began as a High School senior project. It has the ability to parse mathematical expressions into callable functions, perform exact derivative calculation, create vectors and vector functions, and apply precise functional analysis.

# Table of Contents

1. [ Classes ](Classes)
2. [Methods](Methods)  
    1. [MathBlock Methods](MathBlock-Methods)
    2. [Point Methods](Point-Methods)
    3. [Vector Methods](Vector-Methods)
    4. [VectorFunc Methods](VectorFunc-Methods)
    5. [Relationship Methods](Relationship-Methods)

# Classes:

  - MathBlock- used to store mathematical expressions
  - Point - used to store points
  - Relationship - used to store equations and inequalities
  - mathFrom - generates MathBlocks (integrated into MathBlock)
  - Vector - used to store either 2d or 3d vectors
  - VectorFunc - used to store vector/parametric functions in either 2d or 3d spaces

# Methods:

## MathBlock Methods:
    
### `MathBlock(String str) (constructor)`

Generates a MathBlock from a string representing a mathematical expression. MathBlock the following operators: +, -, \*, /, sin(), cos(), tan(), sec(), cot(), csc(), cot(), and ln().  
Examples of MathBlock strings:  
  `"5*x"`  
  `"sin(2^x)*ln(x)"`  
  `"sin(cos(tan(sec(cot(csc(10))))))" (I mean, if you really wanted to)`  

###   `MathBlock() (constructor)`

Generates an empty MathBlock

###    `MathBlock drfCopy()`

Returns a copy of the MathBlock, dereferenced from the original MathBlock. Similar to clone() methods.

###    `float result()`

Returns a float that represents the value of a MathBlock. Will return error if the MathBlock contains a variable.

###    `void reveal()`

Puts a printout to the console that shows the structure of the MathBlock.

###   `String opAsString()`

Returns a String representing the primary operator of a MathBlock. Throws an error for MathBlocks without a primary operator. 

###   `String asString()`

Returns a String that represents the MathBlock. Differs from value() in that it shows the expression, not the result. Differs from reveal() in that it is not verbose.

###   `void toConsole()`

Prints asString() on the console.

###    `MathBlock derivative(String wrt)`

Returns a MathBlock that represents the derivative of the MathBlock in question. Wrt represents the variable to differentiate the MathBlock by.

###    `MathBlock derivative()`

Returns the derivative of the MathBlock with respect to x.

###    `boolean isResolvable()`

Returns true if and only if the MathBlock does not contain any variables (i.e., if result() can be used).

###    `MathBlock simplified()`

Returns a MathBlock that is a simplified, equivalent version of the original MathBlock. DOES NOT MUTATE THE ORIGINAL MATHBLOCK.
    
###    `MathBlock simplify()`

Returns a MathBlock that is a simplified, equivalent version of the original MathBlock. Also mutates the original MathBlock to that simplified value.

###    `MathBlock substitute(String var, float number)`

Replaces all instances of variable var with the number, number.

###    `float resultAt(String wrt, float value)`

Finds the result of a function at a certain value for a certain variable, essentially finding a point on the function at a certain x-value. MathBlock must be resolvable other than the wrt variable.

###    `ArrayList<Point> calcApproxZeros(String wrt, float min, float max)`

Returns an ArrayList of point classes, representing the approximate zeros of the function on the variable wrt on the interval between min and max. MathBlock must be resolvable other than the wrt variable.

###    `float maximum(float min, float max)`

Finds the approximate maximum value of the function on the interval from min to max. If the min>max, an error is thrown. MathBlock must be resolvable other than the wrt variable.

###    `float minimum(float min, float max)`

Finds the approximate minimum value of the function on the interval from min to max.  If the min>max, an error is thrown. MathBlock must be resolvable other than the wrt variable.

###    `float integralApprox(String wrt, float min, float max)`

Approximates the integral of the function along the variable wrt on the interval greater than min and less than max. MathBlock must be resolvable other than the wrt variable.

##     Point Methods

###    `Point(float xval, float yval) (constructor)`

Constructs a Point class at a certain x and y value
    
###    `float radius()`

Calculates the r value of the polar representation of a point

###    `float theta()`

Calculates the theta value of the polar representation of a point

###    `boolean isZero()`

Returns true if and only if the point is a zero.

###    `boolean isZero(float thresh)`

Returns true if the point is within a certain threshold of being a zero.

###    `String asString()`

Returns a String representation of the point.

###    `float curvilinearVal(MathBlock func, String wrt1, String wrt2, float min, float max)`

Calculates the value of the point on an arbitrary curvilinear system, where wrt1 is the x-equivalent and wrt2 is the y-equivalent. The curvilinear value must be between min and max, otherwise an error will be thrown. If the min>max, an error is thrown.

## Vector Methods:

###    `Vector(float xval, float yval) (constructor)`

Generates a 2 dimensional vector.

###    `Vector(float xval, float yval, float zval) (constructor)`

Generates a 3 dimensional vector.
      
###    `Vector drfCopy()`

Returns a copy of the Vector, dereferenced from the original Vector. Similar to clone() methods.

###    `float mag()`

Returns the magnitude of the vector.

###    `float magSQ()`

Returns the square the magnitude of the vector.

###    `String asString()`

Returns a string that represents the vector.

###    `void add(Vector v)`

Adds a second vector to the original.

###    `void sub(Vector v)`

Subtracts a second vector from the original.

###    `void mult(float s)`

Multiplies the vector by a scalar.

###    `void div(float s)`

Divides the vector by a scalar.

###    `float dot(Vector v)`

Returns the dot product of the original vector and another.

###    `Vector cross(Vector v)`

Returns the cross product of the original vector and another.

###    `Vector normalize(Vector v)`

Returns a normalized vector in the same direction with a magnitude of 1.

###    `Vector rotate(float ang)`

Returns a rotated copy of the original vector. The vector is rotated in the XY plane.

## VectorFunc Methods:

###    `VectorFunc(MathBlock xb, MathBlock yb, MathBlock zb)`

Generates a 3D vector function.

###    `VectorFunc(MathBlock xb, MathBlock yb)`

Generates a 2D vector function.

###    `VectorFunc drfCopy()`

Returns a copy of the VectorFunc, dereferenced from the original VectorFunc. Similar to clone() methods.

###    `String asString()`

Returns a string representing the VectorFunc.

###    `Vector resultAt(String wrt, float value)`

Returns the vector result at a specified value of the function.

###    `VectorFunc derivative(String wrt)`

Returns the derivative of the vector function with respect to the variable wrt.

###    `float length(String wrt, float min, float max)`

Returns the length of the vector function with respect to a certain variable along the interval greater than min and less than max. 

## Relationship Methods:

###    `boolean isTrue()`

Determines if the relationship describes is true or not.
