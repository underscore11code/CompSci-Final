package io.github.underscore11code.compsci.pieces;

import io.github.underscore11code.compsci.*;

import java.util.Map;

public class King extends Piece {
  public King(final Side side) {
    super(side);
  }

  @Override
  public Map<Location, MoveResult> validMoves(final BoardState boardState, final Location location) {
    var moves = ChessGame.locationMoveResultMap();

    move(moves, boardState, location.relative(1, 1));
    move(moves, boardState, location.relative(0, 1));
    move(moves, boardState, location.relative(-1, 1));
    move(moves, boardState, location.relative(1, 0));
    move(moves, boardState, location.relative(1, -1));
    move(moves, boardState, location.relative(0, -1));
    move(moves, boardState, location.relative(-1, -1));
    move(moves, boardState, location.relative(-1, 0));

    return moves;
  }

  private void move(Map<Location, MoveResult> moves, BoardState boardState, Location location) {
    if (location == null) return;
    moves.put(location, moveResult(boardState, location));
  }

  private MoveResult moveResult(BoardState boardState, Location location) {
    if (boardState.isPieceAt(location)) {
      if (boardState.pieceAt(location).side() == side()) {
        return MoveResult.INVALID;
      }
      return MoveResult.CAPTURE;
    }
    return MoveResult.MOVE;
  }
}
