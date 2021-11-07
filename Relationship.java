enum equality {
  Equals, GreaterThan, LessThan, NotEqual, GTOET, LTOET
}
class Relationship {
  MathBlock blockA;
  MathBlock blockB;
  equality eqty;
  public Boolean isTrue() {
    switch(eqty) {
      case Equals:
        return blockA.result() == blockB.result();
      case GreaterThan:
        return blockA.result() > blockB.result();
      case LessThan:
        return blockA.result() < blockB.result();
      case NotEqual:
        return blockA.result() != blockB.result();
      case GTOET:
        return blockA.result() >= blockB.result();
      case LTOET:
        return blockA.result() <= blockB.result();
      default:
        return false;
    }
  }
}