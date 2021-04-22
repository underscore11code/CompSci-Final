package io.github.underscore11code.compsci;

import io.github.underscore11code.compsci.pieces.King;
import org.fusesource.jansi.Ansi;

import java.util.*;

import static org.fusesource.jansi.Ansi.ansi;

public class ChessGame {
  private final BoardState boardState = BoardState.create();
  private final List<String> out = new ArrayList<>();
  private final Scanner scanner;
  private Side turn = Side.WHITE;
  private Location selectedLocation;
  private String activePrompt = "";

  public ChessGame(final Scanner scanner) {
    this.scanner = scanner;
  }

  public void run() {
    while (true) {
      out(
              selectedLocation == null ?
              "Please enter the location of the piece you wish to move, or \"quit\" to quit." :
              "Please enter the location of where you wish to move this piece, \"cancel\" to choose another piece, or \"quit\" to quit."
      );
      renderScreen();
      String command = scanner.nextLine();
      out("> " + command);
      System.out.println(ansi().reset());
      if (command.equalsIgnoreCase("quit")) {
        return;
      }

      if (command.equalsIgnoreCase("cancel")) {
        selectedLocation = null;
        activePrompt = "";
        continue;
      }

      Location tmpLocation = Location.of(command);

      if (tmpLocation == null) {
        out("Invalid location entered. Locations are [Letter][Number] i.e. a1");
        continue;
      }
      if (selectedLocation == null) {
        if (boardState.isPieceAt(tmpLocation) && boardState.pieceAt(tmpLocation).side() == turn) {
          selectedLocation = tmpLocation;
          activePrompt = "Move [" + tmpLocation.xChar() + tmpLocation.y() + "]";
        } else {
          out("Invalid location choice.");
        }
      } else {
        Piece piece = boardState.pieceAt(selectedLocation);
        if (piece.validMoves(boardState, selectedLocation).get(tmpLocation).valid()) {
          if (boardState.isPieceAt(tmpLocation) && boardState.pieceAt(tmpLocation) instanceof King) {
            System.out.println(ansi().eraseScreen().cursor(0, 0).a(Ansi.Attribute.BLINK_FAST).a(turn + " has won!").reset());
            System.out.println(ansi().a(Ansi.Attribute.ITALIC).fgRgb(150, 150, 150).a("Press enter to continue...").reset());
            scanner.nextLine();
            return;
          }

          boardState.movePiece(selectedLocation, tmpLocation);
          turn = turn == Side.WHITE ? Side.BLACK : Side.WHITE;
          selectedLocation = null;
          activePrompt = "";
        }
      }

      while (out.size() > 10) {
        out.remove(0);
      }
    }
  }

  public static List<Location> allLocations() {
    List<Location> locations = new ArrayList<>();
    for (int x = 0; x < 8; x++) {
      for (int y = 0; y < 8; y++) {
        locations.add(Location.of(x, y));
      }
    }

    return locations;
  }

  public static Map<Location, MoveResult> locationMoveResultMap() {
    Map<Location, MoveResult> out = new HashMap<>();
    for (final Location location : allLocations()) {
      out.put(location, MoveResult.INVALID);
    }
    return out;
  }

  public void out(final String out) {
    this.out.add(out);
  }

  public void out(final Object out) {
    this.out.add(out.toString());
  }

  public void renderScreen() {
    System.out.print(ansi().eraseScreen());
    for (Location location : allLocations()) {
        final Piece piece = this.boardState.pieceAt(location);
        System.out.print(ansi().cursor(location.y() + 1, location.x() * 3 + 2) + (renderSquare(location, piece)) + ansi().reset());
    }

    for (int i = 0; i < 8; i++) {
      System.out.println(ansi().cursor(i + 1, 1).a(i));
      System.out.println(ansi().cursor(9, i * 3 + 3).a(Location.charFromX(i)));
    }

    System.out.println(ansi().cursor(11, 0).bg(Ansi.Color.WHITE).fgBlack().a("Console").reset());
    for (final String s : out) {
      System.out.println(s);
    }
    System.out.print(
            ansi().fgBlack().bg(Ansi.Color.WHITE).a(turn + (!activePrompt.equalsIgnoreCase("") ? " " + activePrompt : ""))
                    .reset().a("> ")
                    .reset().a(Ansi.Attribute.ITALIC));
  }

  public String renderSquare(final Location loc, final Piece piece) {
    Ansi.Consumer bg;
    if (loc.color()) {
      if (isPossibleMove(loc)) {
        bg = ansi -> ansi.bgRgb(100, 150, 100);
      } else {
        bg = ansi -> ansi.bgRgb(100, 100, 100);
      }
    } else {
      if (isPossibleMove(loc)) {
        bg = ansi -> ansi.bgRgb(150, 200, 150);
      } else {
        bg = ansi -> ansi.bgRgb(150, 150, 150);
      }
    }

    return ansi()
            .apply(bg)
            .apply(piece != null && piece.side() == Side.WHITE ? ansi -> ansi.fg(Ansi.Color.WHITE) : Ansi::fgBlack)
            .apply(loc.equals(selectedLocation) ? Ansi::bgGreen : ansi -> {})
            .a(" " + (piece == null ? " " : piece.getClass().getSimpleName().charAt(0)) + " ")
            .toString();
  }

  public boolean isPossibleMove(final Location loc) {
    if (selectedLocation == null) return false;
    if (!boardState.isPieceAt(selectedLocation)) return false;
    var moves = boardState.pieceAt(selectedLocation).validMoves(boardState, selectedLocation);
    if (!moves.containsKey(loc)) return false;
    return moves.get(loc).valid();
  }
}
