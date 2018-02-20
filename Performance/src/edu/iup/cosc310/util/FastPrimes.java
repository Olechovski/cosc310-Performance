package edu.iup.cosc310.util;

/**
 * @author Eric Olechovski
 * 
 *         Utility class to create an array of primes
 */
public class FastPrimes {

	/**
	 * Get a set of prime numbers.
	 * 
	 * @param no
	 *            the number of primes to create
	 * @return an array containing the requested number of primes
	 */
	public static int[] getPrimes(int no) {
		int[] primes = new int[no];
		int primeInx = 0;
		int i = 2;
		
		// count is the number of primes added into the array
		int count = 0;

		while (primeInx < no) {
			
			boolean prime = true;
			
			if(i > 3){
				// reduces array by half for finding factors
				int cut = i/2;
			// to see if 'i' is divisible by a prime that was already discovered
			for (int j = 1; j < cut; j++) {
				
				if (cut <= count && i % primes[j] == 0) {
					prime = false;
				}
				
			}
			}
			
			
			
			if (prime) {
				primes[primeInx++] = i;
				count++;
			}
			
			if(i > 2){
				i+=2;
			}
			else{
			i++;
			}
			
			
		}

		return primes;
	}

	public static void main(String[] args) {
		new TimeExec(new Runnable() {
			public void run() {
				int[] primes = getPrimes(1000);
//				System.out.print("Primes");
//				for (int i = 0; i < 1000; i++){
//					System.out.printf(" %d,", primes[i]);
//				}
				System.out.printf("Primes %d, %d, %d, %d, %d .... %d, %d, %d, %d, %d%n", primes[0], primes[1],
						primes[2], primes[3], primes[4], primes[995], primes[996], primes[997], primes[998],
						primes[999]);
			}
		}, "Get 1,000 primes", System.out).start();

		new TimeExec(new Runnable() {
			public void run() {
				int[] primes = getPrimes(10000);
				System.out.printf("Primes %d, %d, %d, %d, %d .... %d, %d, %d, %d, %d%n", primes[0], primes[1],
						primes[2], primes[3], primes[4], primes[9995], primes[9996], primes[9997], primes[9998],
						primes[9999]);
			}
		}, "Get 10,000 primes", System.out).start();

		new TimeExec(new Runnable() {
			public void run() {
				int[] primes = getPrimes(100000);
				System.out.printf("Primes %d, %d, %d, %d, %d .... %d, %d, %d, %d, %d%n", primes[0], primes[1],
						primes[2], primes[3], primes[4], primes[99995], primes[99996], primes[99997], primes[99998],
						primes[99999]);
			}
		}, "Get 100,000 primes", System.out).start();

		new TimeExec(new Runnable() {
			public void run() {
				int[] primes = getPrimes(1000000);
				System.out.printf("Primes %d, %d, %d, %d, %d .... %d, %d, %d, %d, %d%n", primes[0], primes[1],
						primes[2], primes[3], primes[4], primes[999995], primes[999996], primes[999997], primes[999998],
						primes[999999]);
			}
		}, "Get 1,000,000 primes", System.out).start();
	}
}