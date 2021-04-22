package io.github.underscore11code.compsci;

import java.util.Map;

public abstract class Piece {
  private final Side side;

  public Piece(final Side side) {
    this.side = side;
  }

  public Side side() {
    return this.side;
  }

  public abstract Map<Location, MoveResult> validMoves(final BoardState boardState, final Location location);

  @Override
  public String toString() {
    return "Piece{" +
            "side=" + side +
            '}';
  }
}
