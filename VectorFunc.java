class VectorFunc {
  MathBlock xBlock;
  MathBlock yBlock;
  MathBlock zBlock;
  public VectorFunc(MathBlock xb, MathBlock yb, MathBlock zb) {
    xBlock = xb;
    yBlock = yb;
    zBlock = zb;
  }
  public VectorFunc(MathBlock xb, MathBlock yb) {
    xBlock = xb;
    yBlock = yb;
    zBlock = new MathBlock("0");
  }
  public VectorFunc drfCopy() {
    return new VectorFunc(xBlock.drfCopy(), yBlock.drfCopy(), zBlock.drfCopy());
  }
  public String asString() {
    if (zBlock.isResolvable()) {
      if (zBlock.result() == 0) {
        return("<"+xBlock.asString()+", "+yBlock.asString()+">");
      } else {
        return("<"+xBlock.asString()+", "+yBlock.asString()+", "+zBlock.asString()+">");
      }
    } else {
      return("<"+xBlock.asString()+", "+yBlock.asString()+", "+zBlock.asString()+">");
    }
  }
  public VectorFunc substitute(String wrt, float value) {
    return new VectorFunc(xBlock.substitute(wrt, value), yBlock.substitute(wrt, value), zBlock.substitute(wrt, value));
  }
  public Vector resultAt(String wrt, float value) {
    return new Vector(xBlock.resultAt(wrt, value), yBlock.resultAt(wrt, value), zBlock.resultAt(wrt, value));
  }
  public VectorFunc derivative(String wrt) {
    return new VectorFunc(xBlock.drfCopy().derivative(wrt), yBlock.drfCopy().derivative(wrt), zBlock.drfCopy().derivative(wrt));
  }
  public float length(String wrt, float min, float max) {
    float len = 0;
    MathBlock xd = xBlock.drfCopy().derivative(wrt);
    MathBlock yd = yBlock.drfCopy().derivative(wrt);
    MathBlock zd = zBlock.drfCopy().derivative(wrt);
    //xd.toConsole();
    for (int i = (int)(min*(float)100.0); i<(int)(max*(float)100.0); i++) {
      float ii = (float)i/(float)100;
      //System.out.println(ii);
      len+=Math.sqrt(Math.pow(xd.resultAt(wrt,ii),2)+Math.pow(yd.resultAt(wrt,ii),2)+Math.pow(zd.resultAt(wrt,ii),2));
    }
    return len*(float).01;
  }
}