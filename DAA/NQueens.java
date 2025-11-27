import java.util.ArrayList; 
import java.util.List; 
 
public class NQueens { 
 
    public List<List<String>> solveNQueens(int n) { 
        List<List<String>> solutions = new ArrayList<>(); 
        int[] board = new int[n];  
        solveNQueensUtil(0, n, board, solutions); 
        return solutions; 
    } 
 
    private void solveNQueensUtil(int row, int n, int[] board, List<List<String>> solutions) { 
        if (row == n) { 
            solutions.add(createBoard(board, n)); 
            return; 
        } 
 
        for (int col = 0; col < n; col++) { 
            if (isSafe(row, col, board)) { 
                board[row] = col;  
                solveNQueensUtil(row + 1, n, board, solutions); 
            } 
        } 
    } 
 
    private boolean isSafe(int row, int col, int[] board) { 
        for (int prevRow = 0; prevRow < row; prevRow++) { 
            int prevCol = board[prevRow]; 
             
            if (prevCol == col) { 
                return false; 
            } 
 
            if (Math.abs(row - prevRow) == Math.abs(col - prevCol)) { 
                return false; 
            } 
        } 
        return true; 
    } 
 
    private List<String> createBoard(int[] board, int n) { 
        List<String> solution = new ArrayList<>(); 
        for (int i = 0; i < n; i++) { 
            StringBuilder row = new StringBuilder(); 
            int queenCol = board[i];  
             
            for (int j = 0; j < n; j++) { 
                if (j == queenCol) { 
                    row.append('Q'); 
                } 
                else { 
                    row.append('.'); 
                } 
            } 
            solution.add(row.toString()); 
        } 
        return solution; 
    } 
 
    public static void main(String[] args) { 
        NQueens solver = new NQueens(); 
        int n = 4; 
         
        System.out.println("Solving N-Queens for N = " + n); 
        List<List<String>> solutions = solver.solveNQueens(n); 
         
        System.out.println("Total solutions found: " + solutions.size()); 
         
        int count = 1; 
        for (List<String> solution : solutions) { 
            System.out.println("\nSolution " + count + ":"); 
            for (String row : solution) { 
                System.out.println(row); 
            } 
            count++; 
        } 
    } 
}