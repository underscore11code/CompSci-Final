package io.github.underscore11code.compsci.pieces;

import io.github.underscore11code.compsci.*;

import java.util.Map;

public class Bishop extends Piece {
  public Bishop(final Side side) {
    super(side);
  }

  @Override
  public Map<Location, MoveResult> validMoves(final BoardState boardState, final Location location) {
    var moves = ChessGame.locationMoveResultMap();

    PieceUtils.diagonalValidAxis(boardState, location, moves);

    return moves;
  }
}
