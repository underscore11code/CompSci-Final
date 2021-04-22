package io.github.underscore11code.compsci.pieces;

import io.github.underscore11code.compsci.*;

import java.util.Map;

public class Queen extends Piece {
  public Queen(final Side side) {
    super(side);
  }

  @Override
  public Map<Location, MoveResult> validMoves(final BoardState boardState, final Location location) {
    var moves = ChessGame.locationMoveResultMap();

    PieceUtils.validAxis(boardState, location, moves);
    PieceUtils.diagonalValidAxis(boardState, location, moves);

    return moves;
  }
}
