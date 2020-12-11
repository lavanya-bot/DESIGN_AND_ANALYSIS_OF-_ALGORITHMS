public class NQueen {
 
    int queens;
    boolean flag;
    Board board;
 
    public NQueen(int queens) {
        this.flag = false;
        this.board = new Board(queens);;
        this.queens = queens;
    }
 
    void solveNqueen(){
 
        int i;
        hasSolution(0,0);
        if(!flag)
            System.out.println("No Solution");
 
    }
 
    boolean hasSolution(int ctr, int colQueen){
        if(colQueen == queens){
            flag = true;
            board.displayBoard();
            return false;
        }
 
        int i,j;
        for(i=ctr; i<queens; i++){
            if(board.isValidPlace(i,colQueen)){
                board.chessBoard[i][colQueen] = 1;
                if(hasSolution(0,colQueen+1))
                    return true;
                board.chessBoard[i][colQueen] = 0;
            }
 
        }
        return false;
    }
}