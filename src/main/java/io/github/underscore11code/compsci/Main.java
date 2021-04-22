package io.github.underscore11code.compsci;

import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.ansi;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    final Scanner scanner = new Scanner(System.in);
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      System.out.println(ansi().eraseScreen().cursor(0, 0).reset());
      scanner.close();
    }));
    while (true) {
      System.out.println(ansi().eraseScreen().cursor(0, 0).reset());
      System.out.println(ansi().fgBrightGreen().a("Welcome to Chess!").reset());
      System.out.println("Pieces are represented by the first letter of their name (Knights are \"H\" since King takes \"K\").");
      System.out.println("To move, you will be prompted first for the location of which piece you wish to move, then where to move it.");
      System.out.println("Please note that \"castling\" is not possible. Sorry advanced players.");
      System.out.println();

      System.out.println(ansi().a(Ansi.Attribute.ITALIC).fgRgb(150, 150, 150).a("Press enter to start...").reset());
      scanner.nextLine();
      new ChessGame(scanner).run();
    }
  }
}
