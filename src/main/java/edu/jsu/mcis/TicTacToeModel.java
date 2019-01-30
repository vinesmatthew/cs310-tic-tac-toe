package edu.jsu.mcis;

public class TicTacToeModel {
    
    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* Size of game board */
    
    /* ENUM TYPE DEFINITIONS */
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("TIE"), 
        NONE("NONE");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create board (width x width) as a 2D Mark array */
        
        board = new Mark[width][width];

        /* Initialize board by filling every square with empty marks */
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                
                board[i][j] = Mark.EMPTY;
            }
        }
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */
        
        if (isValidSquare(row, col)) {
            if (!isSquareMarked(row, col)) {
                if (xTurn) {
                    board[row][col] = Mark.X;
                } else {
                    board[row][col] = Mark.O;
                }
                xTurn = !xTurn;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return TRUE if the specified location is within the bounds of the board */
        return row < width && col < width && row >= 0 && col >= 0;
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return TRUE if the square at specified location is marked */
        return !board[row][col].equals(Mark.EMPTY);
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return the mark from the square at the specified location */
        
        return board[row][col];
    }
	
    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */
        
        if (isMarkWin(Mark.X)) {
            return Result.X;
        } else if (isMarkWin(Mark.O)) {
            return Result.O;
        } else if (isTie()) {
            return Result.TIE;
        } else {
            return Result.NONE;
        }
    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
        
        int rowCounter;
        int colCounter;
        int rightDiagCounter = 0;
        int leftDiagCounter = 0;
        
        for (int i = 0; i < width; i++) {
            rowCounter = 0;
            colCounter = 0;
            for (int j = 0; j < width; j++) {
                if (board[i][j].equals(mark)) {
                    rowCounter++;               
                    if (i == j) {
                        rightDiagCounter++;
                    }
                    
                    if ((i + j) == (width - 1)) {
                        leftDiagCounter++;
                    }
                }
                
                if (board[j][i].equals(mark)) {
                    colCounter++;
                }
            }
            
            if (rowCounter == width || colCounter == width || rightDiagCounter == width || leftDiagCounter == width) {
                return true;
            }
        }
        
        return false;
    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */
        
        boolean emptySpaces = false;
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (!isSquareMarked(i, j))
                    emptySpaces = true;
            }
        }
        
        return !emptySpaces;
    }

    public boolean isGameover() {
        
        /* Return TRUE if the game is over */
        
        return (Result.NONE != getResult());
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
    @Override
    public String toString() {
        
        StringBuilder output = new StringBuilder("  ");
        
        /* Output the board contents as a string (see examples) */
        
        for (int i = 0; i < width; i++) {
            output.append(i);
        }
        
        output.append("\n\n");
                
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (j == 0) {
                    output.append(i).append(" ").append(board[i][j]);
                } else if (j > 0) {
                    output.append(board[i][j]);
                } 
                
                if (j == width - 1) {
                    output.append("\n");
                }
            }
        }
                
        return output.toString();
        
    }
    
}
