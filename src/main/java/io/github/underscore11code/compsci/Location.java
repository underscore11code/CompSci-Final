package io.github.underscore11code.compsci;

public class Location {
  private int x;
  private int y;

  private Location(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  public static Location of(final int x, final int y) {
    if (x >= 8 || x < 0 || y >= 8 || y < 0) throw new IllegalArgumentException("Coordinate out of 0-7 bounds: " + x + " " + y);
    return new Location(x, y);
  }

  public static Location of(final char x, final int y) {
    int xInt = xFromChar(x);
    return of(xInt, y);
  }

  public static Location of(String rawIn) {
    if (rawIn.length() != 2) return null;
    char rawX = rawIn.charAt(0);
    char rawY = rawIn.charAt(1);

    final int y;
    try {
      y = Integer.parseInt(String.valueOf(rawY));
    } catch (NumberFormatException e) {
      return null;
    }

    try {
      return of(rawX, y);
    } catch (Exception e) {
      return null;
    }
  }

  public int x() {
    return this.x;
  }

  public void x(final int x) {
    this.x = x;
  }

  public void xChar(final char x) {
    this.x = xFromChar(x);
  }

  public char xChar() {
    return charFromX(this.x);
  }

  public int y() {
    return this.y;
  }

  public void y(final int y) {
    this.y = y;
  }

  /**
   * Creates a new location with coordinates relative to this.
   * @param x relative X coordinate
   * @param y relative y coordinate
   * @return a new location, relative to this
   */
  public Location relative(final int x, final int y) {
    try {
      return of(this.x + x, this.y + y);
    } catch (Exception e) {
      return null;
    }
  }

  public boolean color() {
    return (x + y) % 2 == 0;
  }

  public static int xFromChar(char c) {
    c = Character.toLowerCase(c);
    switch (c) {
      case 'a': return 0;
      case 'b': return 1;
      case 'c': return 2;
      case 'd': return 3;
      case 'e': return 4;
      case 'f': return 5;
      case 'g': return 6;
      case 'h': return 7;
      default: throw new IllegalArgumentException("Not a char within a-h: " + c);
    }
  }

  public static char charFromX(int x) {
    switch (x) {
      case 0: return 'a';
      case 1: return 'b';
      case 2: return 'c';
      case 3: return 'd';
      case 4: return 'e';
      case 5: return 'f';
      case 6: return 'g';
      case 7: return 'h';
      default: throw new IllegalArgumentException("Not a number within 0-7: " + x);
    }
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final Location location = (Location) o;

    if (x != location.x) return false;
    return y == location.y;
  }

  @Override
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
  }

  @Override
  public String toString() {
    return "Location{" +
            "x=" + x +
            ", y=" + y +
            '}';
  }
}
