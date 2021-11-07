class Vector {
  float x = 0;
  float y = 0;
  float z = 0;

  public Vector(float xval, float yval) {
    x=xval;
    y=yval;
  }

  public Vector(float xval, float yval, float zval) {
    x=xval;
    y=yval;
    z=zval;
  }

  public Vector drfCopy() {
    return new Vector(x,y,z);
  }

  public float mag() {
    return (float) Math.sqrt((float) Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
  }

  public float magSQ() {
    return (float) (Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
  }

  public String asString() {
    if (z==0) {
      return "<"+x+","+y+">";
    } else {
      return "<"+x+","+y+","+z+">";
    }
  }

  public void add(Vector v2) {
    x+=v2.x;
    y+=v2.y;
    z+=v2.z;
  }
  public void sub(Vector v2) {
    x-=v2.x;
    y-=v2.y;
    z-=v2.z;
  }
  public void mult(float v) {
    x*=v;
    y*=v;
    z*=v;
  }
  public void div(float v) {
    x/=v;
    y/=v;
    z/=v;
  }
  public float dot(Vector v) {
    return v.x*x+v.y*y+v.z*z;
  }
  public Vector cross(Vector v) {
    return new Vector(y*v.z-z*v.y, z*v.x-x*v.z, x*v.y-y*v.x);
  }
  public Vector normalize() {
    return new Vector(x/mag(),y/mag(),z/mag());
  }
  public Vector rotate(float ang) {
    return new Vector((float) x*(float)Math.cos((float)ang)-y*(float)Math.sin((float)ang), (float) x*(float)Math.sin((float)ang)+y*(float)Math.cos((float)ang), (float) z);
  }
}