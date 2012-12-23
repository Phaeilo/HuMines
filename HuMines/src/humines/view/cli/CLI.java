package humines.view.cli;

import humines.model.EField;
import humines.model.IMineGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLI {

    public static void main(String[] args) throws IOException {
        // help, quit, new w h m, print, dig x y
        CLI cli = new CLI();
        cli.run();
    }

    private boolean cliActive;
    private IMineGame game;

    private void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        this.cliActive = true;
        this.game = null;
        while (this.cliActive) {
            System.out.print("> ");

            String command = br.readLine();
            command = command.trim();
            command = command.toLowerCase();

            if (command.length() == 0) {
                continue;
            }

            switch (command.charAt(0)) {
                case 'h':
                    this.showHelp();
                    break;
                case 'q':
                    this.quit();
                    break;
                case 'p':
                    this.printField();
                    break;
                case 'n':
                    this.newGame(command);
                    break;
                case 'd':
                    this.dig(command);
                    break;
                default:
                    this.unknownCommand();
            }
        }
    }

    private void unknownCommand() {
        System.out.println("Unknown command, type \"help\" for help.");
    }

    private void dig(String command) {
        if (this.game == null) {
            System.out.println("Error! No game was started.");
            return;
        }

        if (this.game.isGameOver()) {
            System.out.println("Error! Game over.");
            return;
        }

        String[] args = command.split("\\s+");
        if (args.length != 3) {
            System.out.println("Error! Wrong number of arguments.");
            return;
        }

        int x = 0;
        int y = 0;
        try {
            x = Integer.parseInt(args[1]);
            y = Integer.parseInt(args[2]);
        } catch (NumberFormatException nfe) {
            System.out.println("Error! Arguments must be numeric.");
            return;
        }

        if (x < 0 || y < 0 || x >= this.game.getWidth() || y >= this.game.getHeight()) {
            System.out.println("Error! Coordinates out of bounds.");
            return;
        }

        this.game.uncover(x, y);

        if (this.game.isGameOver()) {
            if (this.game.isGameWon()) {
                System.out.println("Game over: You won!");
            } else {
                System.out.println("Game over: You lost!");
            }
            System.out.printf("%d seconds elapsed.%n", this.game.getSecondsElapsed());
        }
    }

    private void newGame(String command) {
        String[] args = command.split("\\s+");
        if (args.length != 4) {
            System.out.println("Error! Wrong number of arguments.");
            return;
        }

        int w = 0;
        int h = 0;
        int m = 0;
        try {
            w = Integer.parseInt(args[1]);
            h = Integer.parseInt(args[2]);
            m = Integer.parseInt(args[3]);
        } catch (NumberFormatException nfe) {
            System.out.println("Error! Arguments must be numeric.");
            return;
        }

        this.game = null;
        // TODO use game implementation
    }

    private void quit() {
        this.cliActive = false;
        System.out.println("Bye!");
    }

    private void printField() {
        if (this.game == null) {
            System.out.println("Error! No game was started.");
            return;
        }

        StringBuilder result = new StringBuilder();
        EField[] minefield = this.game.getField();
        int w = this.game.getWidth();
        int h = this.game.getHeight();

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int i = y * w + x;
                char c;
                switch (minefield[i]) {
                    case Covered:
                        c = '.';
                        break;
                    case CoveredMine:
                        c = '*';
                        break;
                    case Uncovered:
                        c = ' ';
                        break;
                    case Uncovered1:
                        c = '1';
                        break;
                    case Uncovered2:
                        c = '2';
                        break;
                    case Uncovered3:
                        c = '3';
                        break;
                    case Uncovered4:
                        c = '4';
                        break;
                    case Uncovered5:
                        c = '5';
                        break;
                    case Uncovered6:
                        c = '6';
                        break;
                    case Uncovered7:
                        c = '7';
                        break;
                    case Uncovered8:
                        c = '8';
                        break;
                    case UncoveredMine:
                        c = '#';
                        break;
                    default:
                        c = '?';
                }
                result.append(c);
            }
            result.append("\n");
        }

        System.out.print(result);
    }

    private void showHelp() {
        System.out.println("Available commands:");
        System.out.println("help, quit, new <width> <heigt> <mines>, print, dig <x> <y>");
    }

}
