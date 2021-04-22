package io.github.underscore11code.compsci.pieces;

import io.github.underscore11code.compsci.*;

import java.util.Map;

public class Pawn extends Piece {
  public Pawn(final Side side) {
    super(side);
  }

  @Override
  public Map<Location, MoveResult> validMoves(final BoardState boardState, final Location location) {
    var moves = ChessGame.locationMoveResultMap();

    int multiplier = side() == Side.WHITE ? 1 : -1;

    Location moveUp = location.relative(0, multiplier);

    if (!boardState.isPieceAt(moveUp)) moves.put(moveUp, MoveResult.MOVE);

    // Pawns can move 2 spaces from their starting position
    if ((location.y() == 1 && side() == Side.WHITE) || (location.y() == 6 && side() == Side.BLACK)) {
      Location moveDouble = location.relative(0, 2 * multiplier);
      if (!boardState.isPieceAt(moveDouble)) moves.put(moveDouble, MoveResult.MOVE);
    }

    if (location.x() != 0) {
      Location captureNegative = location.relative(-1, multiplier);
      if (boardState.isPieceAt(captureNegative)) moves.put(captureNegative, MoveResult.CAPTURE);
    }
    if (location.x() != 7) {
      Location capturePositive = location.relative(1, multiplier);
      if (boardState.isPieceAt(capturePositive)) moves.put(capturePositive, MoveResult.CAPTURE);
    }

    return moves;
  }

  @Override
  public String toString() {
    return "Pawn{} " + super.toString();
  }
}
