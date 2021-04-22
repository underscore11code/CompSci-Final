package io.github.underscore11code.compsci.pieces;

import io.github.underscore11code.compsci.BoardState;
import io.github.underscore11code.compsci.Location;
import io.github.underscore11code.compsci.MoveResult;

import java.util.Map;

public final class PieceUtils {
  private PieceUtils() {}

  // TODO HANDLE NOT CAPTURING OWN PIECES

  public static void validAxis(BoardState boardState, Location location, Map<Location, MoveResult> moves) {
    for (int x = location.x(); x < 8; x++) {
      Location current = Location.of(x, location.y());
      final MoveResult moveResult = forSquare(location, current, boardState);
      if (moveResult == MoveResult.INVALID) continue;
      moves.put(current, moveResult);
      if (moveResult == MoveResult.CAPTURE) break;
    }

    for (int x = location.x(); x > -1; x--) {
      Location current = Location.of(x, location.y());
      final MoveResult moveResult = forSquare(location, current, boardState);
      if (moveResult == MoveResult.INVALID) continue;
      moves.put(current, moveResult);
      if (moveResult == MoveResult.CAPTURE) break;
    }

    for (int y = location.y(); y < 8; y++) {
      Location current = Location.of(location.x(), y);
      final MoveResult moveResult = forSquare(location, current, boardState);
      if (moveResult == MoveResult.INVALID) continue;
      moves.put(current, moveResult);
      if (moveResult == MoveResult.CAPTURE) break;
    }

    for (int y = location.y(); y > -1; y--) {
      Location current = Location.of(location.x(), y);
      final MoveResult moveResult = forSquare(location, current, boardState);
      if (moveResult == MoveResult.INVALID) continue;
      moves.put(current, moveResult);
      if (moveResult == MoveResult.CAPTURE) break;
    }
  }

  public static void diagonalValidAxis(BoardState boardState, Location location, Map<Location, MoveResult> moves) {
    int i = -1;
    while (true) {
      i++;
      Location current = location.relative(i, i);
      if (current == null) break;
      final MoveResult moveResult = forSquare(location, current, boardState);
      if (moveResult == MoveResult.INVALID) continue;
      moves.put(current, moveResult);
      if (moveResult == MoveResult.CAPTURE) break;
    }

    i = -1;
    while (true) {
      i++;
      Location current = location.relative(-i, i);
      if (current == null) break;
      final MoveResult moveResult = forSquare(location, current, boardState);
      if (moveResult == MoveResult.INVALID) continue;
      moves.put(current, moveResult);
      if (moveResult == MoveResult.CAPTURE) break;
    }

    i = -1;
    while (true) {
      i++;
      Location current = location.relative(i, -i);
      if (current == null) break;
      final MoveResult moveResult = forSquare(location, current, boardState);
      if (moveResult == MoveResult.INVALID) continue;
      moves.put(current, moveResult);
      if (moveResult == MoveResult.CAPTURE) break;
    }

    i = -1;
    while (true) {
      i++;
      Location current = location.relative(-i, -i);
      if (current == null) break;
      final MoveResult moveResult = forSquare(location, current, boardState);
      if (moveResult == MoveResult.INVALID) continue;
      moves.put(current, moveResult);
      if (moveResult == MoveResult.CAPTURE) break;
    }
  }

  private static MoveResult forSquare(Location startingLoc, Location currentLoc, BoardState boardState) {
    if (startingLoc.equals(currentLoc)) return MoveResult.INVALID;
    if (!boardState.isPieceAt(currentLoc)) {
      return MoveResult.MOVE;
    }
    return MoveResult.CAPTURE;
  }
}
