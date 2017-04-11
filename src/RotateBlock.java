import java.util.EnumSet;

public class RotateBlock {

	private int[][] block = null;	
	public final int MAX_BLOCKSIZE = 3;
	public enum Angle {ANGLE_90, ANGLE_180, ANGLE_270};
	
	// Used to trim left/top blank lines
	public int offset_row = 0;
	public int offset_col = 0;
		
	public void ShowBlock() {		
		if( block == null ) {
			System.out.println("Block is null");
			return;
		}
		
		System.out.println("# Block ");
		for(int row=0 ; row<MAX_BLOCKSIZE ; ++row) {			
			for(int col=0 ; col<MAX_BLOCKSIZE ; ++col) {
				if( block[row][col] > 0 ) {
					System.out.print(" " + block[row][col]);
				} else {
					System.out.print(" -");
				}
			}
			System.out.println();
		}		
	}
	
	/*
	 * This function is the core logic for rotation
	 * This logic arranges the travel path based on the rotation degree
	 * For example rotation 90 degree,
	 * 1 2 3 
	 * 4 5 6
	 * 7 8 9
	 * 
	 * (Source) Reading order : 7 4 1 8 5 2 9 6 3
	 * (Target) Writing order : 1 2 3 4 5 6 7 8 9 
	 * 
	 * 
	 * extra info
	 * Rotate 90  : Row=>Col, Col(I)=>Row
	 * Rotate 180 : Row(I)=>Row, Col(I)=>Col. 
	 * Rotate 270 : Row(I)=>Col, Col=>Row
	 */
	public boolean RotateBlock(Angle angle) {		
		int[][] newBlock = new int[MAX_BLOCKSIZE][MAX_BLOCKSIZE];
		switch(angle) {
		case ANGLE_90: 
			System.out.println("\n# Rotate 90");
			for(int row=0 ; row<MAX_BLOCKSIZE ; ++row) {
				for(int col=offset_row ; col<MAX_BLOCKSIZE ; ++col) {
					newBlock[row][col-offset_row] = block[MAX_BLOCKSIZE - 1 - col][row];
				}
			}
			break;
		case ANGLE_180: 
			System.out.println("\n# Rotate 180");
			for(int row=offset_row ; row<MAX_BLOCKSIZE ; ++row) {
				for(int col=offset_col ; col<MAX_BLOCKSIZE ; ++col) {
					newBlock[row-offset_row][col-offset_col] = block[MAX_BLOCKSIZE - 1 - row][MAX_BLOCKSIZE - 1 - col];
				}
			}
			break;
		case ANGLE_270: 
			System.out.println("\n# Rotate 270");
			for(int row=offset_col ; row<MAX_BLOCKSIZE ; ++row) {
				for(int col=0 ; col<MAX_BLOCKSIZE ; ++col) {
					newBlock[row-offset_col][col] = block[col][MAX_BLOCKSIZE - 1 - row];
				}
			}
			break;
		default:
				
		}
		
		block = newBlock;
		
		return true;
	}
	
	public void SetNewBlock(int[][] newBlock) {		
		block = newBlock;
		
		offset_row = 0;
		offset_col = 0;
		boolean doneRow = false;
		boolean doneCol = false;		
		for(int i=MAX_BLOCKSIZE-1 ; i>=0 ; --i) {
			if( doneRow == false) {
				if( (block[i][0] == 0) && (block[i][1] == 0) && (block[i][2] == 0) ) {
					++offset_row;
				} else {
					doneRow = true;
				}
			}
			
			if( doneCol == false ) {				
				if( (block[0][i] == 0) && (block[1][i] == 0) && (block[2][i] == 0) ) {
					++offset_col;
				} else {
					doneCol = true;
				}
			}
		}
	}

	public static void main(String[] args) {	
		RotateBlock rotateBlock = new RotateBlock();
		
		int[][] block = {{1, 2, 0}, {3, 4, 0},  {5, 0, 0}};
				
		rotateBlock.SetNewBlock(block);		
		rotateBlock.ShowBlock();
				
		for(RotateBlock.Angle angle : RotateBlock.Angle.values() ) {
			rotateBlock.SetNewBlock(block);
			rotateBlock.RotateBlock(angle);			
			rotateBlock.ShowBlock();
		}		
	}
}
