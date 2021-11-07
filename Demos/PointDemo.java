class PointDemo {
  public static void run() {
    Point myPoint = new Point(5,10);
    System.out.println("POINT DEMO");
    System.out.println("This demo shows a few features of the Point class: the .asString method to make a String representation of the point, the ability to convert the point into polar coordinates, and the ability to find the point's value in an arbitrary coordinate system by using the .curvilinearVal() method");
    System.out.println(myPoint.asString());
    System.out.println("radius: "+myPoint.radius());
    System.out.println("theta: "+myPoint.theta());
    MathBlock myMathBlock = new MathBlock("x^2+a");
    System.out.println("curvilinear value of a for x^2+a: "+myPoint.curvilinearVal(myMathBlock,"x","a",-100,100));
  }

}