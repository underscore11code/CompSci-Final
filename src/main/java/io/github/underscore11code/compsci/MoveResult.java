package io.github.underscore11code.compsci;

public enum MoveResult {
  INVALID(false),
  MOVE(true),
  CAPTURE(true);
  private final boolean valid;

  MoveResult(final boolean valid) {
    this.valid = valid;
  }

  public boolean valid() {
    return this.valid;
  }
}
