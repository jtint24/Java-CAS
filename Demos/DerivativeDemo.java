class DerivativeDemo {
  public static void run() {
    System.out.println("DERIVATIVE DEMO");
    System.out.println("The .derivative() function is capable of finding the exact functional derivative of any MathBlock, which is capable of storing virtually any elementary function. The .simplified() function is also used to make elementary simplifications here. Lastly, the value of the derivative at x=0 is calculated using the .resultAt() function");
    System.out.println("");
    demoDer(new MathBlock("5*x"));
    demoDer(new MathBlock("sin(5+x*9)"));
    demoDer(new MathBlock("ln(x+2)^cos(9*x)+(x+1)^sin(x)"));
  }
  public static void demoDer(MathBlock mb) {
    System.out.println("Function: "+mb.asString());
    System.out.println("Derivative: "+mb.derivative().asString());
    System.out.println("Simplified derivative: "+mb.derivative().simplified().asString());
    System.out.println("");
    System.out.println("Derivative value at 0: "+mb.derivative().simplified().resultAt("x", 0));
    System.out.println("");
  }
}