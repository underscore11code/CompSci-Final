package io.github.underscore11code.compsci;

import io.github.underscore11code.compsci.pieces.*;

import java.util.HashMap;
import java.util.Map;

public class BoardState {
  private final Map<Location, Piece> pieces;

  private BoardState(final Map<Location, Piece> pieces) {
    this.pieces = pieces;
  }

  public Piece pieceAt(final Location location) {
    return pieces.get(location);
  }

  public boolean isPieceAt(final Location location) {
    return this.pieceAt(location) != null;
  }

  public boolean movePiece(final Location starting, final Location ending) {
    if (!isPieceAt(starting)) return false;
    final Piece piece = pieceAt(starting);
    final MoveResult moveResult = piece.validMoves(this, starting).get(ending);
    if (!moveResult.valid()) return false;

    if (moveResult != MoveResult.CAPTURE && isPieceAt(ending)) return false;

    pieces.remove(starting);
    pieces.put(ending, piece);
    return true;
  }

  public static BoardState create() {
    Map<Location, Piece> pieces = new HashMap<>();

    for (int i = 0; i < 8; i++) {
      pieces.put(Location.of(i, 1), new Pawn(Side.WHITE));
      pieces.put(Location.of(i, 6), new Pawn(Side.BLACK));
    }

    pieces.put(Location.of(0, 0), new Rook(Side.WHITE));
    pieces.put(Location.of(7, 0), new Rook(Side.WHITE));
    pieces.put(Location.of(0, 7), new Rook(Side.BLACK));
    pieces.put(Location.of(7, 7), new Rook(Side.BLACK));

    pieces.put(Location.of(1, 0), new Horse(Side.WHITE));
    pieces.put(Location.of(6, 0), new Horse(Side.WHITE));
    pieces.put(Location.of(1, 7), new Horse(Side.BLACK));
    pieces.put(Location.of(6, 7), new Horse(Side.BLACK));

    pieces.put(Location.of(2, 0), new Bishop(Side.WHITE));
    pieces.put(Location.of(5, 0), new Bishop(Side.WHITE));
    pieces.put(Location.of(2, 7), new Bishop(Side.BLACK));
    pieces.put(Location.of(5, 7), new Bishop(Side.BLACK));

    pieces.put(Location.of(3, 0), new Queen(Side.WHITE));
    pieces.put(Location.of(3, 7), new Queen(Side.BLACK));

    pieces.put(Location.of(4, 0), new King(Side.WHITE));
    pieces.put(Location.of(4, 7), new King(Side.BLACK));

    return new BoardState(pieces);
  }

  @Override
  public String toString() {
    return "BoardState{" +
            "pieces=" + pieces +
            '}';
  }
}
