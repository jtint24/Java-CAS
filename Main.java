import java.util.ArrayList;
interface OpInterface2 {
  float run(float a, float b);
}
interface OpInterface1 {
  float run(float a);
}

class Main {

  /*public static void arrayPrint(String[] arr) {
    System.out.print("[");
    for (int i = 0; i < arr.length; i++) {
      if (arr[i]=="") {break;}
      System.out.print("\""+arr[i]+"\", ");
    }
    System.out.println("]");
  }*/
  
  public static ArrayList<Float> getXofPoints(ArrayList<Point> points) {
    ArrayList<Float> returnValues = new ArrayList<Float>();
    for(int i = 0; i<points.size(); i++) {
      returnValues.add(points.get(i).x);
    }
    return returnValues;
  }
  public static void arrayPrint(ArrayList<Float> arr) {
    System.out.print("[");
    for (int i = 0; i < arr.size(); i++) {
      //if (arr[i]=="") {break;}
      System.out.print("\""+arr.get(i)+"\", ");
    }
    System.out.println("]");
  }

  public static void main(String[] args) {
    VectorDemo.run();
  }
}