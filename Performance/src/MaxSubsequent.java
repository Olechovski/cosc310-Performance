import java.util.Random;

import javax.swing.JOptionPane;

/**
 * @author Eric Olechovski
 *
 */
public class MaxSubsequent {
	public static Random rand = new Random();
	private int seqStart;
	private int seqEnd;
	private int[] seq;;
	
	public static void main(String[] args) {
		int no = 16000;
		
		MaxSubsequent maxSubsequent = new MaxSubsequent();
		
		JOptionPane.showMessageDialog(null, "Geneating " + no + " numbers", "Ready" ,JOptionPane.PLAIN_MESSAGE);
		long startTime = System.currentTimeMillis();
		maxSubsequent.genNumbers(no);
		JOptionPane.showMessageDialog(null, "Geneating " + no + " numbers\n took " + ((System.currentTimeMillis() - startTime)/ 1000.0) + " seconds", "Done" ,JOptionPane.PLAIN_MESSAGE);
		
		JOptionPane.showMessageDialog(null, "Finding max sum in " + no + " numbers", "Ready" ,JOptionPane.PLAIN_MESSAGE);
		startTime = System.currentTimeMillis();
		int maxSum = maxSubsequent.maxSubSequence();
		JOptionPane.showMessageDialog(null, "Max sub sequence is " + maxSum + "\nfound between " + maxSubsequent.getSeqStart() + " and " + maxSubsequent.getSeqEnd()  + "\n took " + ((System.currentTimeMillis() - startTime)/ 1000.0) + " seconds", "Done" ,JOptionPane.PLAIN_MESSAGE);

		System.out.println("done");	
	}
	
	private void genNumbers(int n) {
		seq = new int[n];
		for (int i = 0; i < seq.length; i++) {
			seq[i] = rand.nextInt(100) - 50;
		}
	}
	
	public int maxSubSequence() {
		int maxSum = 0;
		
		for (int i = 0; i < seq.length; i ++) {
			for (int j = i; j < seq.length; j++) {
				int thisSum = 0;
				
				for (int k = i; k <= j; k++) {
					thisSum += seq[k];
				}
				
				if (thisSum > maxSum) {
					maxSum = thisSum;
					seqStart = i;
					seqEnd = j;
				}
			}
		}
		
		return maxSum;
		
	}

	public int getSeqEnd() {
		return seqEnd;
	}

	public int getSeqStart() {
		return seqStart;
	}

}
