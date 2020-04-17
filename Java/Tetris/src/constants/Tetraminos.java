package constants;

import java.awt.Point;

public class Tetraminos {
	
	public final static byte[][][] tTetra = {
			{ {1,0}, {0,1}, {1,1}, {2,1} },
			{ {1,0}, {0,1}, {1,1}, {1,2} },
			{ {0,1}, {1,1}, {2,1}, {1,2} },
			{ {1,0}, {1,1}, {2,1}, {1,2} } 
			};
	
	
	public final static byte[][][] lTetra = {
			{ {0,1}, {1,1}, {2,1}, {2,2} },
			{ {1,0}, {1,1}, {1,2}, {0,2} },
			{ {0,1}, {1,1}, {2,1}, {0,0} },
			{ {1,0}, {1,1}, {1,2}, {2,0} } 
			};
	
	public final static byte[][][] iTetra = {
			{ {0,1}, {1,1}, {2,1}, {3,1} },
			{ {1,0}, {1,1}, {1,2}, {1,3} },
			{ {0,1}, {1,1}, {2,1}, {3,1} },
			{ {1,0}, {1,1}, {1,2}, {1,3} } 
			};
	
	public final static byte[][][] oTetra = {
			{ {0,0}, {0,1}, {1,0}, {1,1} },
			{ {0,0}, {0,1}, {1,0}, {1,1} },
			{ {0,0}, {0,1}, {1,0}, {1,1} },
			{ {0,0}, {0,1}, {1,0}, {1,1} } 
			};
	
	public final static byte[][][] jTetra = {
			{ {0,1}, {1,1}, {2,1}, {2,0} },
			{ {1,0}, {1,1}, {1,2}, {2,2} },
			{ {0,1}, {1,1}, {2,1}, {0,2} },
			{ {1,0}, {1,1}, {1,2}, {0,0} } 
			};
	
	public final static byte[][][] sTetra = {
			{ {1,0}, {2,0}, {0,1}, {1,1} },
			{ {0,0}, {0,1}, {1,1}, {1,2} },
			{ {1,0}, {2,0}, {0,1}, {1,1} },
			{ {0,0}, {0,1}, {1,1}, {1,2} } 
			};
	
	public final static byte[][][] zTetra = {
			{ {0,0}, {1,0}, {1,1}, {2,1} },
			{ {1,0}, {0,1}, {1,1}, {0,2} },
			{ {0,0}, {1,0}, {1,1}, {2,1} },
			{ {1,0}, {0,1}, {1,1}, {0,2} } 
			};
	

	
	public static byte arrayTetra[][][][];
	
	static {
		arrayTetra = new byte[7][][][];
		arrayTetra[0]=tTetra;
		arrayTetra[1]=lTetra;
		arrayTetra[2]=iTetra;
		arrayTetra[3]=oTetra;
		arrayTetra[4]=jTetra;
		arrayTetra[5]=sTetra;
		arrayTetra[6]=zTetra;
		
	}

}







