class mathFrom {
  public static boolean isNumeric(String str) {
    if (str == null) {return false;}
    try {
      float f = Float.parseFloat(str);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }

  public static String removeParens(String str) {
    if (str.charAt(0) != '(' || str.charAt(str.length()-1) != ')') {
      return str; 
    }
    int parenCount = 0;
    int minPC = str.length();
    for (int i = 0; i < str.length(); i++) {
      if (minPC > parenCount & i != 0) {
        minPC = parenCount;
      }
      if (str.charAt(i) == '(') {parenCount++;}
      if (str.charAt(i) == ')') {parenCount--;}
    }
    if (minPC != 0) {
      str = str.substring(1, str.length()-1);
      return removeParens(str);
    } else {
      return str;
    }
  }

  public static MathBlock string(String input) {
    boolean diagnostic = false;
    if (diagnostic) {System.out.println("converting: "+input);}
    
    input = removeParens(input);
    String supportedOps = "-+/*^";
    
    if (diagnostic) {System.out.println("parens: "+input);}

    if (isNumeric(input) || input.length() == 1) {
      if (diagnostic) {System.out.println("is a number/variable! Result: "+input);}
      MathBlock returnBlock = new MathBlock();
      returnBlock.numOfInputs = 0;
      if (isNumeric(input)) {
        returnBlock.input = Float.parseFloat(input);
      }else{
          returnBlock.variable = input;
      }
      return returnBlock;
    } else {
      if (diagnostic) {System.out.println("is a unary/binary op...");}

      MathBlock returnBlock = new MathBlock();
      returnBlock.numOfInputs = 2;

      int splitPoint = 100;
      int[] operatorPrecedence = new int[100];
      String[] parseByOp = new String[100];
      for (int i = 0; i < parseByOp.length; i++) {
        parseByOp[i]="";
        operatorPrecedence[i] = 100;
      }
      int i = 0;
      int parenCount = 0;
      for (int ii = 0; ii < input.length(); ii++) {
        if (input.charAt(ii) == '(') {parenCount++;}
        if (input.charAt(ii) == ')') {parenCount--;}
        if (parenCount == 0 & supportedOps.contains(Character.toString(input.charAt(ii))) & ii!=0) {
          if (!supportedOps.contains(Character.toString(input.charAt(ii-1))) & !supportedOps.contains(Character.toString(input.charAt(ii+1)))) {
            i++;
            parseByOp[i] = ""+input.charAt(ii);
            operatorPrecedence[i]=supportedOps.indexOf(parseByOp[i]);
            if (min(operatorPrecedence)==supportedOps.indexOf(parseByOp[i])) {
            splitPoint = i;
            }
            i++;
          }
        } else {
          parseByOp[i] = parseByOp[i]+input.charAt(ii); 
        }
      } 

      if (parseByOp[1] == "") {
        if (diagnostic) {System.out.println("Okay, it's unary");};
        returnBlock.numOfInputs = 1;
        String blockString = "";
        if (input.substring(0,2).equals("ln")) {
          blockString = input.substring(3, input.length()-1);
        } else {
          blockString = input.substring(4, input.length()-1);
        }
        returnBlock.operator1 = op1From(input.substring(0,3));
        returnBlock.blockA = mathFrom.string(blockString);
        return returnBlock;
      }

      if (diagnostic) {System.out.println("Binary! I split this as: ");};
      if (diagnostic) {arrayPrint(parseByOp);}
      //split separated array into two halves
      String partOne = "";
      String partTwo = "";
      for (i=0;i<input.length();i++) {
        if (i<splitPoint) {
          partOne+=parseByOp[i];
        } else if (i==splitPoint) {

        } else {
          partTwo+=parseByOp[i];
        }
      }
      if (diagnostic) {
        System.out.println("partOne: "+partOne);
        System.out.println("partTwo: "+partTwo);
        System.out.println("Operator: "+parseByOp[splitPoint]);
      }

      returnBlock.blockA = mathFrom.string(partOne);
      returnBlock.blockB = mathFrom.string(partTwo);
      returnBlock.operator2 = op2from(parseByOp[splitPoint]);
      return returnBlock;
    }
  }

  public static OpInterface1 op1From(String str) {
    switch(str) {
      case "ln(":
        return (a) -> (float) Math.log(a);
      case "sin":
        return (a) -> (float) Math.sin(a);
      case "cos":
        return (a) -> (float) Math.cos(a);
      case "tan":
        return (a) -> (float) Math.tan(a);
      case "sec":
        return (a) -> (float) 1/((float) Math.cos(a));
      case "csc":
        return (a) -> (float) 1/((float) Math.sin(a));
      case "cot":
        return (a) -> (float) 1/((float) Math.tan(a));
      default:
        return (a) -> -1;
    }
  }

  public static OpInterface2 op2from(String str) {
    switch(str) {
      case "+":
        return (a,b) -> a+b;
      case "-":
        return (a,b) -> a-b;
      case "*":
        return (a,b) -> a*b;
      case "/":
        return (a,b) -> a/b;
      case "^":
        return (a,b) -> (float)Math.pow(a,b);
      default:
        return (a,b) -> (a+b)/0;
    }
  }

  public static int min(int[] arr) {
    int minint = arr[0];
    for (int i = 0; i<arr.length; i++) {
      if (minint > arr[i]) {minint = arr[i];}
    }
    return minint;
  } 
  public static void arrayPrint(String[] arr) {
    System.out.print("[");
    for (int i = 0; i < arr.length; i++) {
      if (arr[i]=="") {break;}
      System.out.print("\""+arr[i]+"\", ");
    }
    System.out.println("]");
  }
};