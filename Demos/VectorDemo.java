class VectorDemo {
  public static void run() {
    System.out.println("VECTOR DEMO");
    System.out.println("this demo will show various features of the VectorFunc and Vector class");
    System.out.println("");
    VectorFunc vFunc = new VectorFunc(new MathBlock("t^2+7*t"), new MathBlock("sin(cos(t))"), new MathBlock("t^sin(t)+1"));
    System.out.println("We begin with the vector function: "+vFunc.asString());
    System.out.println("We can use the .derivative() method to find the derivative: "+vFunc.derivative("t").asString());
    System.out.println("We can use the .length() function to find the length of the function between t=1 and t=10: "+vFunc.length("t",1,10)+" units");
    Vector newVec = vFunc.resultAt("t",10);
    System.out.println("Speaking of t=10, we can pull the vector value of that function at t=10 too: "+vFunc.resultAt("t",10).asString());
    System.out.println("Then, we can take that vector and find its magnitude: "+newVec.mag());
    System.out.println("We can also find its dot product with another vector, let's say <1,15,17>: "+newVec.dot(new Vector(1, 15,17)));

  }
}