/****************************************************************
    Nome: Felipe Castro de Noronha
    NUSP: 10737032

    Ao preencher esse cabeçalho com o meu nome e o meu número USP,
    declaro que todas as partes originais desse exercício programa (EP)
    foram desenvolvidas e implementadas por mim e que portanto não
    constituem desonestidade acadêmica ou plágio.
    Declaro também que sou responsável por todas as cópias desse
    programa e que não distribui ou facilitei a sua distribuição.
    Estou ciente que os casos de plágio e desonestidade acadêmica
    serão tratados segundo os critérios divulgados na página da
    disciplina.
    Entendo que EPs sem assinatura devem receber nota zero e, ainda
    assim, poderão ser punidos por desonestidade acadêmica.

    Abaixo descreva qualquer ajuda que você recebeu para fazer este
    EP.  Inclua qualquer ajuda recebida por pessoas (inclusive
    monitoras e colegas). Com exceção de material de MAC0323, caso
    você tenha utilizado alguma informação, trecho de código,...
    indique esse fato abaixo para que o seu programa não seja
    considerado plágio ou irregular.

    Descrição de ajuda ou indicação de fonte:

    Se for o caso, descreva a seguir 'bugs' e limitações do seu programa:

****************************************************************/

import java.lang.IllegalArgumentException;
import java.lang.UnsupportedOperationException;

import java.util.NoSuchElementException;
import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Board {

    private int[][] tiles; // Represents the grid
    private int n; // Size of one side of the grid

    private GoalPos[] gp; // Auxiliar goal grid

    private int manhattanN;
    private int hammingN;

    // Create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {

        this.tiles = tiles;
        // n = this.tiles[0].length();
        n = 3; // REMOVE THIS

        // Setting goal values used in manhattan() and hamming()
        // gp[i] will store the goal (row, col) of the tile with number i
        gp = new GoalPos[n*n];
        for (int i = 0; i < n*n; i++)
            gp[i] = new GoalPos();

        int counter = 1; // Number of actual tile
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){

                // Setting blank tile
                if (i == n-1 && j == n-1)
                    counter = 0;

                gp[counter].setPos(i, j);
                counter++;
            }
        }

        // Counting hamming and manhattans distances
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {

                // If i am in a blank tile
                if (this.tiles[i][j] == 0)
                    continue;

                // Hamming
                if (!gp[ this.tiles[i][j] ].equals(i, j))
                    hammingN++;

                // Manhattan
                manhattanN += gp[ this.tiles[i][j] ].manhDist(i, j);
            }
        }
    }

    // Used for comparation to the goal board, used as node
    private class GoalPos {

        private int goalRow;
        private int goalCol;

        public GoalPos() {

        }

        // Set goals positions for this node
        public void setPos(int row, int col) {

            goalRow = row;
            goalCol = col;
        }

        // Receive (row, col) and check if it is equal to (goalRow, goalCol)
        public boolean equals(int row, int col) {

            return (row == goalRow && col == goalCol);
        }

        // Receive (row, col) and return the manhattan distance from goals
        public int manhDist(int row, int col) {

            int ret = Math.abs(row - goalRow);
            ret += Math.abs(col - goalCol);

            return ret;
        }
    }

    // String representation of this boarda
    public String toString() {

        String ret = new String();

        ret = String.valueOf(n);
        ret += "\n";

        // Appending each tile to string
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++)
                ret += " " + String.valueOf(tiles[i][j]) + " ";
            ret += "\n";
        }

        return ret;
    }

    // Tile at (row, col) or 0 if blank
    public int tileAt(int row, int col) {

        // Dealing with parameters out of bounderyes
        if (row < 0 || row >= n || col < 0 || col >= n)
            throw new IllegalArgumentException();

        return tiles[row][col];

    }

    // Board size
    public int size() {
        return n;
    }

    // Number of tiles out of place
    public int hamming() {

        return hammingN;
    }

    // Sum of Manhattan distances between tiles and goal
    public int manhattan() {

        return manhattanN;
    }

    // Is the board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    /*
    // Does this board equal y?
    public boolean equals(Object y) {

    }

    // All neighboor boards
    public Iterable<Board> neighboors() {

    }

    // Is this board solvable?
    public boolean isSolvable()
    */
    // Unit test
    public static void main(String[] args) {

        // javac-algs4 Board.java && java-algs4 Board

        int[][] aux = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};

        Board b = new Board(aux);

        StdOut.println(b.toString());
        StdOut.println(b.hamming());
        StdOut.println(b.manhattan());

    }
}

/*

- Descobrir pq ta dando segfault
- Testar as paradas da goalPos

*/