import java.util.ArrayList;

class MathBlock {
  int numOfInputs = -1;
  float input;
  String variable = "";
  OpInterface1 operator1;
  OpInterface2 operator2;
  MathBlock blockA;
  MathBlock blockB;

  public MathBlock(String str) {
    MathBlock thisBlock = mathFrom.string(str);
    this.numOfInputs = thisBlock.numOfInputs;
    this.input = thisBlock.input;
    this.variable = thisBlock.variable;
    this.operator1 = thisBlock.operator1;
    this.operator2 = thisBlock.operator2;
    this.blockA = thisBlock.blockA;
    this.blockB = thisBlock.blockB;
  }

  public MathBlock() {

  }

  public MathBlock drfCopy() {
    MathBlock resultBlock = new MathBlock();
    switch (this.numOfInputs) {
    case 0:
      resultBlock.numOfInputs = 0;
      resultBlock.variable = this.variable;
      resultBlock.input = this.input;
      return resultBlock;
    case 1:
      resultBlock.numOfInputs = 1;
      resultBlock.blockA = this.blockA.drfCopy();
      resultBlock.operator1 = this.operator1;
      return resultBlock;
    case 2:
      resultBlock.numOfInputs = 2;
      resultBlock.blockA = this.blockA.drfCopy();
      resultBlock.blockB = this.blockB.drfCopy();
      resultBlock.operator2 = this.operator2;
      return resultBlock;
    default:
      return resultBlock;
    }
  }

  private String value() {
    if (numOfInputs == 0) {
      if (variable != "") {
        return variable;
      } else {
        return String.valueOf(input);
      }
    } else {
      return "";
    }
  }

  public float result() {
    if (isResolvable()) {
      switch (numOfInputs) {
      case 0:
        return input;
      case 1:
        return operator1.run(blockA.result());
      case 2:
        return operator2.run(blockA.result(), blockB.result());
      default:
        return 0;
      }
    } else {
      throw new java.lang.RuntimeException("Cannot find result of MathBlock containing variable");
    }
  }

  public void reveal() {
    this.reveal(0);
  }

  private void reveal(int tab) {
    System.out.println("| ".repeat(tab) + "BLOCK:");
    System.out.println("| ".repeat(tab) + "|numOfInputs: " + numOfInputs);
    switch (numOfInputs) {
    case 0:
      System.out.println("| ".repeat(tab) + "|value: " + this.value());
      break;
    case 1:
      System.out.println("| ".repeat(tab) + "|operator: " + this.opAsString());
      System.out.println("| ".repeat(tab) + "|arg1: ");
      blockA.reveal(tab + 1);
      break;
    case 2:
      System.out.println("| ".repeat(tab) + "|operator: " + this.opAsString());
      System.out.println("| ".repeat(tab) + "|arg1: ");
      blockA.reveal(tab + 1);
      System.out.println("| ".repeat(tab) + "|arg2: ");
      blockB.reveal(tab + 1);
      break;
    default:
      System.out.println("| ".repeat(tab) + "|:)");
      break;
    }
  }

  public String opAsString() {
    try {
      if (numOfInputs == 0) {
        return ":(";
      } else if (numOfInputs == 1) {
        // System.out.println((int) operator1.run(5));
        switch ((int) (4.0 * operator1.run(9))) {
        case -3:
          return "cos";
        case 1:
          return "sin";
        case -1:
          return "tan";
        case 8:
          return "ln";
        case -4:
          return "sec";
        case -8:
          return "cot";
        case 9:
          return "csc";
        default:
          return ";< U";
        }
      } else {
        switch (2 * ((int) operator2.run(1, 2))) {
        case 6:
          return "+";
        case -2:
          return "-";
        case 4:
          return "*";
        case 0:
          return "/";
        case 2:
          return "^";
        default:
          return ";< B";
        }
      }
    } catch (Exception e) {
      throw new java.lang.RuntimeException("Null Pointer Error: no primary operator");
    }
  }

  public String asString() {
    switch (numOfInputs) {
    case 0:
      return this.value();
    case 1:
      return this.opAsString() + "(" + blockA.asString() + ")";
    case 2:
      return "(" + blockA.asString() + this.opAsString() + blockB.asString() + ")";
    default:
      return "ER";
    }
  }

  public void toConsole() {
    System.out.println(asString());
  }

  public MathBlock derivative() {
    return this.derivative("x");
  }

  public MathBlock derivative(String wrt) {
    MathBlock returnBlock = new MathBlock();
    MathBlock subBlockA = new MathBlock();
    MathBlock subBlockB = new MathBlock();
    MathBlock negative1 = new MathBlock();
    negative1.numOfInputs = 0;
    negative1.input = -1;

    if (numOfInputs == 0) {
      if (wrt.equals(this.variable)) {
        returnBlock.numOfInputs = 0;
        returnBlock.input = 1;
      } else {
        returnBlock.numOfInputs = 0;
        returnBlock.input = 0;
      }
    } else if (numOfInputs == 1) {
      switch (this.opAsString()) {
      case "ln":
        returnBlock.numOfInputs = 2;
        returnBlock.operator2 = (a, b) -> a / b;
        returnBlock.blockA = this.blockA.derivative(wrt);
        returnBlock.blockB = this.blockA;
        break;
      case "sin":
        returnBlock.numOfInputs = 2;
        subBlockA.numOfInputs = 1;
        subBlockA.operator1 = (a) -> (float) Math.cos(a);
        subBlockA.blockA = this.blockA;

        returnBlock.operator2 = (a, b) -> a * b;
        returnBlock.blockA = this.blockA.derivative(wrt);

        returnBlock.blockB = subBlockA;
        break;
      case "cos":
        returnBlock.numOfInputs = 2;
        subBlockA.numOfInputs = 1;
        subBlockA.operator1 = (a) -> (float) Math.sin(a);
        subBlockA.blockA = this.blockA;

        subBlockB.numOfInputs = 2;
        subBlockB.operator2 = (a, b) -> a * b;
        subBlockB.blockA = subBlockA;
        MathBlock neg1 = new MathBlock();
        neg1.numOfInputs = 0;
        neg1.input = -1;
        subBlockB.blockB = neg1;

        returnBlock.operator2 = (a, b) -> a * b;
        returnBlock.blockA = this.blockA.derivative(wrt);
        returnBlock.blockB = subBlockB;
        break;
      case "tan":
        subBlockA.numOfInputs = 1;
        subBlockA.operator1 = (a) -> (float) 1.0 / ((float) Math.cos(a));
        subBlockA.blockA = this.blockA;

        MathBlock sq = new MathBlock();
        sq.numOfInputs = 2;
        sq.operator2 = (a, b) -> a * b;
        sq.blockA = subBlockA;
        sq.blockB = subBlockA;

        returnBlock.numOfInputs = 2;
        returnBlock.operator2 = (a, b) -> a * b;
        returnBlock.blockA = sq;
        returnBlock.blockB = this.blockA.derivative(wrt);
        break;
      case "sec":
        MathBlock tangent = new MathBlock();
        MathBlock secant = new MathBlock();
        MathBlock sectan = new MathBlock();
        tangent.numOfInputs = 1;
        tangent.operator1 = (a) -> (float) Math.tan(a);
        tangent.blockA = blockA;
        secant.numOfInputs = 1;
        secant.operator1 = (a) -> (float) 1.0 / ((float) Math.cos(a));
        secant.blockA = blockA;
        sectan.numOfInputs = 2;
        sectan.operator2 = (a, b) -> a * b;
        sectan.blockA = secant;
        sectan.blockB = tangent;

        returnBlock.numOfInputs = 2;
        returnBlock.operator2 = (a, b) -> a * b;
        returnBlock.blockA = this.blockA.derivative(wrt);
        returnBlock.blockB = sectan;
        break;
      case "csc":
        MathBlock cosecant = new MathBlock();
        MathBlock cotangent = new MathBlock();
        MathBlock csccot = new MathBlock();
        MathBlock neg1mult = new MathBlock();

        cosecant.numOfInputs = 1;
        cosecant.operator1 = (a) -> (float) 1 / ((float) Math.sin(a));
        cosecant.blockA = this.blockA;

        cotangent.numOfInputs = 1;
        cotangent.operator1 = (a) -> ((float) Math.cos(a)) / ((float) Math.sin(a));
        cotangent.blockA = this.blockA;

        csccot.numOfInputs = 2;
        csccot.operator2 = (a, b) -> a * b;
        csccot.blockA = cotangent;
        csccot.blockB = cosecant;

        neg1mult.numOfInputs = 2;
        neg1mult.operator2 = (a, b) -> a * b;
        neg1mult.blockA = negative1;
        neg1mult.blockB = csccot;

        returnBlock.numOfInputs = 2;
        returnBlock.operator2 = (a, b) -> a * b;
        returnBlock.blockA = this.blockA.derivative(wrt);
        returnBlock.blockB = neg1mult;
        break;
      case "cot":
        MathBlock negMult = new MathBlock();
        subBlockA.numOfInputs = 1;
        subBlockA.blockA = this.blockA;
        subBlockA.operator1 = (a) -> (float) 1 / ((float) Math.sin(a));

        subBlockB.numOfInputs = 2;
        subBlockB.blockA = subBlockA;
        subBlockB.blockB = subBlockA;
        subBlockB.operator2 = (a, b) -> a * b;

        negMult.numOfInputs = 2;
        negMult.blockA = subBlockB;
        negMult.blockB = negative1;
        negMult.operator2 = (a, b) -> a * b;

        returnBlock.numOfInputs = 2;
        returnBlock.blockA = negMult;
        returnBlock.blockB = this.blockA.derivative(wrt);
        returnBlock.operator2 = (a, b) -> a * b;
        break;
      default:
        returnBlock.numOfInputs = -1;
        break;
      }
    } else {
      switch (this.opAsString()) {
      case "+":
        returnBlock.numOfInputs = 2;
        returnBlock.operator2 = (a, b) -> a + b;
        returnBlock.blockA = this.blockA.derivative(wrt);
        returnBlock.blockB = this.blockB.derivative(wrt);
        break;
      case "-":
        returnBlock.numOfInputs = 2;
        returnBlock.operator2 = (a, b) -> a - b;
        returnBlock.blockA = this.blockA.derivative(wrt);
        returnBlock.blockB = this.blockB.derivative(wrt);
        break;
      case "*":
        returnBlock.numOfInputs = 2;
        returnBlock.operator2 = (a, b) -> a + b;

        subBlockA.numOfInputs = 2;
        subBlockA.operator2 = (a, b) -> a * b;
        subBlockA.blockA = this.blockA.derivative(wrt);
        subBlockA.blockB = this.blockB;

        subBlockB.numOfInputs = 2;
        subBlockB.operator2 = (a, b) -> a * b;
        subBlockB.blockA = this.blockB.derivative(wrt);
        subBlockB.blockB = this.blockA;

        returnBlock.blockA = subBlockA;
        returnBlock.blockB = subBlockB;
        break;
      case "/":
        MathBlock numerator = new MathBlock();
        numerator.numOfInputs = 2;
        numerator.operator2 = (a, b) -> a - b;
        // MathBlock subBlockA = new MathBlock();
        // MathBlock subBlockB = new MathBlock();

        subBlockA.numOfInputs = 2;
        subBlockA.operator2 = (a, b) -> a * b;
        subBlockA.blockA = this.blockA.derivative(wrt);
        subBlockA.blockB = this.blockB;

        subBlockB.numOfInputs = 2;
        subBlockB.operator2 = (a, b) -> a * b;
        subBlockB.blockA = this.blockB.derivative(wrt);
        subBlockB.blockB = this.blockA;

        numerator.blockA = subBlockA;
        numerator.blockB = subBlockB;

        MathBlock denominator = new MathBlock();
        denominator.numOfInputs = 2;
        denominator.operator2 = (a, b) -> a * b;
        denominator.blockA = this.blockB;
        denominator.blockB = this.blockB;

        returnBlock.numOfInputs = 2;
        returnBlock.operator2 = (a, b) -> a / b;
        returnBlock.blockA = numerator;
        returnBlock.blockB = denominator;
        break;
      case "^":
        MathBlock factor = new MathBlock();
        factor.numOfInputs = 2;
        factor.operator2 = (a, b) -> a * b;

        subBlockA.numOfInputs = 1;
        subBlockA.operator1 = (a) -> (float) Math.log(a);
        subBlockA.blockA = this.blockA;

        factor.blockA = this.blockB;
        factor.blockB = subBlockA;

        returnBlock.numOfInputs = 2;
        returnBlock.operator2 = (a, b) -> a * b;
        returnBlock.blockA = this;
        returnBlock.blockB = factor.derivative(wrt);

        break;
      default:
        returnBlock.numOfInputs = -1;
      }
    }
    return returnBlock;
  }

  public boolean isResolvable() {
    switch (numOfInputs) {
    case 0:
      return variable.isEmpty();
    case 2:
      return blockA.isResolvable() & blockB.isResolvable();
    case 1:
      return blockA.isResolvable();
    default:
      return false;
    }
  }

  private MathBlock simplifiedBasic() {
    MathBlock resultBlock = new MathBlock();

    // resolve inputs
    if (this.isResolvable()) {
      resultBlock.numOfInputs = 0;
      resultBlock.input = this.result();
    }
    // resolve identity ops
    if (numOfInputs == 2) {
      if (this.opAsString().equals("*") & (blockA.value().equals("0.0") | blockB.value().equals("0.0"))) {
        resultBlock.numOfInputs = 0;
        resultBlock.input = 0;
      }
      if (this.opAsString().equals("+") & blockA.value().equals("0.0")) {
        resultBlock = blockB;
      }
      if (this.opAsString().equals("+") & blockB.value().equals("0.0")) {
        resultBlock = blockA;
      }
      if (this.opAsString().equals("*") & blockA.value().equals("1.0")) {
        resultBlock = blockB;
      }
      if (this.opAsString().equals("*") & blockB.value().equals("1.0")) {
        resultBlock = blockA;
      }
      if (this.opAsString().equals("/") & blockB.value().equals("1.0")) {
        resultBlock = blockA;
      }
      if (this.opAsString().equals("^") & blockB.value().equals("0.0")) {
        resultBlock.numOfInputs = 0;
        resultBlock.input = 1;
      }
      if (this.opAsString().equals("^") & blockB.value().equals("1.0")) {
        resultBlock = blockA;
      }

    }
    if (resultBlock.numOfInputs == -1) {
      resultBlock = this;
      if (numOfInputs > 0) {
        resultBlock.blockA = this.blockA.simplifiedBasic();
      }
      if (numOfInputs > 1) {
        resultBlock.blockB = this.blockB.simplifiedBasic();
      }
    }
    return resultBlock;
  }

  public MathBlock simplified() {
    MathBlock returnBlock = this.drfCopy();
    while (true) {
      if (returnBlock.asString().equals(returnBlock.simplifiedBasic().asString())) {
        break;
      } else {
        returnBlock = returnBlock.simplifiedBasic();
      }
    }
    return returnBlock;
  }

  public MathBlock simplify() {
    MathBlock returnBlock = this;
    while (true) {
      if (returnBlock.asString().equals(returnBlock.simplifiedBasic().asString())) {
        break;
      } else {
        returnBlock = returnBlock.simplifiedBasic();
      }
    }
    return returnBlock;
  }

  public MathBlock substitute(String var, float number) {
    MathBlock resultBlock = this;
    switch (resultBlock.numOfInputs) {
    case 0:
      if (resultBlock.value().equals(var)) {
        resultBlock.variable = "";
        resultBlock.input = number;
      }
      return resultBlock;
    case 1:
      resultBlock.blockA = this.blockA.substitute(var, number);
      return resultBlock;
    case 2:
      resultBlock.blockA = this.blockA.substitute(var, number);
      resultBlock.blockB = this.blockB.substitute(var, number);
      return resultBlock;
    default:
      return resultBlock;
    }
  }

  public float resultAt(String wrt, float value) {
    MathBlock copyBlock = this.drfCopy();
    return copyBlock.substitute(wrt, value).result();
  }

  public ArrayList<Point> calcApproxZeros(String wrt, float min, float max) {
    float oldValue = this.resultAt(wrt, min);
  
    ArrayList<Point> returnValues = new ArrayList<Point>();
    for (int i = (int) min; i < max; i++) {
      float valueAtI = (float) this.resultAt(wrt, i);
      System.out.println(valueAtI);
      if ((Math.signum(valueAtI) != Math.signum(oldValue) || Math.signum(valueAtI) == 0)
          && !(Math.signum(oldValue) == 0 && Math.signum(valueAtI) != 0)) {
        if (Math.signum(valueAtI) == 0) {
          returnValues.add(new Point(i, 0));
        } else {
          for (int j = -10000; j < 0; j++) {
            valueAtI = (float) this.resultAt(wrt, ((float) j) / ((float) 10000.0) + (float) i);
            System.out.println(">" + valueAtI);
            if (Math.signum(valueAtI) != Math.signum(oldValue)) {
              returnValues.add(new Point(((float) j) / ((float) 10000.0) + (float) i, 0));
            }
            oldValue = valueAtI;
          }
        }
      }
      oldValue = valueAtI;
    }

    return returnValues;
  }

  public float maximum(String wrt, float min, float max) {
    if (min>max) {
      throw new java.lang.Error("min is greater than max");
    }
    float maxVal = resultAt(wrt, min);
    for (int i = (int)min*100; i<max*100; i++) {
      maxVal = Math.max(resultAt(wrt,(float)((float)i)/(float)100.0), maxVal);
    }
    return maxVal;
  }

  public float minimum(String wrt, float min, float max) {
    if (min>max) {
      throw new java.lang.Error("min is greater than max");
    }
    float minVal = resultAt(wrt, min);
    for (int i = (int)min*100; i<max*100; i++) {
      minVal = Math.min(resultAt(wrt,(float)((float)i)/(float)100.0), minVal);
    }
    return minVal;
  }

  public float integralApprox(String wrt, float min, float max) {
    float area = 0;
    for (int i = (int)(min*(float)100.0); i<(int)(max*(float)100.0); i++) {
      area+=(float)(.01)*resultAt(wrt, (float)i/(float)100.0);
    }
    return area;
  }
};