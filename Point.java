import java.util.ArrayList;
class Point {
  public float x = 0;
  public float y = 0;
  public Point(float xval, float yval) {
    x=xval;
    y=yval;
  }
  public float radius() {
     return (float) Math.sqrt((float) Math.pow(x,2)+Math.pow(y,2));
  }
  public float theta() {
    return (float) Math.atan2(y,x);
  }
  public String asString() {
    return "("+x+","+y+")";
  }
  public boolean isZero() {
    return x==0;
  }
  public boolean isZero(float thresh) {
    return Math.abs(x)<thresh;
  }
  public float curvilinearVal(MathBlock func, String wrt1, String wrt2, float min, float max) {
    if (min>max) {
      throw new java.lang.Error("min is greater than max");
    }
    for (int i = (int)(min*100.0); i<(int)(max*100.0); i++) {
      MathBlock subbed = func.drfCopy().substitute(wrt1,(float)x);
      MathBlock subbed2 = subbed.drfCopy().substitute(wrt2, (float)i/(float)100.0);
      float val = subbed2.result();
      //System.out.println(val);
      if (Math.abs(val-y)<.01) {
        return (float)i/(float)100.0;
      }
    }
    throw new java.lang.Error("no curvilinear value found");
  }
  public Vector asVector() {
    return new Vector(x,y);
  }
}
