package edu.iup.cosc310.util;

/**
 * @author Eric Olechovski
 *
 * A utlity class to calculate the perfect power of an integer
 */
public class FastPerfectPower {
    public static void main(String[] args) {
		new TimeExec(new Runnable() {
			public void run() {
			       System.out.println("Pth of 17 is " + perfectPower(17));
			}
		}, "Get Pth of 17", System.out).start();
		
		new TimeExec(new Runnable() {
			public void run() {
			       System.out.println("Pth of 625 is " + perfectPower(625));
			}
		}, "Get Pth of 625", System.out).start();
		
		new TimeExec(new Runnable() {
			public void run() {
			       System.out.println("Pth of 1024 is " + perfectPower(1024));
			}
		}, "Get Pth of 1024", System.out).start();
		
		new TimeExec(new Runnable() {
			public void run() {
			       System.out.println("Pth of 10000 is " + perfectPower(10000));
			}
		}, "Get Pth of 10000", System.out).start();
		
		new TimeExec(new Runnable() {
			public void run() {
			       System.out.println("Pth of 1073741824 is " + perfectPower(1073741824));
			}
		}, "Get Pth of 1073741824", System.out).start();	
    }

    /**
     * Get the perfect power for a number.
     * @param x number for which to calculate the perfect power.
     */
    public static int perfectPower(int x) {

        int largestP = 1;
        int sqrt = (int) Math.sqrt(x);

        for (int p = sqrt; p > 0; p--) {
            for (int b = 1; b < x; b++) {
            	
            	int power = (int) Math.pow(b,p);
        
            	if( power > x){
            		break;
            	}
            	else if (power == x) {
                    return largestP = p;
                }
            
           }           
        }
        
        return largestP;
        
        
        
        
        
    }

}
