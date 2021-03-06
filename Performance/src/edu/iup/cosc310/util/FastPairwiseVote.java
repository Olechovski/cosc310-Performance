package edu.iup.cosc310.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Eric Olechovski

 *         
 */

public class FastPairwiseVote {
	/**
	 * Get the placement order of a candidate in a rank order list of candidates
	 *
	 * @param votersVotes
	 *            an array of rank order candidates. First has the highest rank.
	 *  @return (1) if candidate1 has a higher rank than candidate2
	 *  	    (-1) if candidate2 has a higher rank than candidate1
	 */
	public static int getPlacement(int candidate,  int[] votersVotes) {
		return votersVotes[candidate];
	}


	/**
	 * Get the candidate winner from a set of rank ordered ballots
	 *
	 * @param votes
	 *            - a two dimensional array, first dimension is the voter, second
	 *            dimension is the rank ordered ballot of candidates for the given
	 *            voter
	 */
	public static int getPairwiseWinner(int[][] votes) {
		int noVoters = votes.length;

		if (noVoters == 0) {
			return -1;
		}

		int noCandidates = votes[0].length;

		if (noCandidates == 0) {
			return -1;
		}


		
		// Consider a candidate compared with themselves to be a winner
		int noTimesCandidate1Wins = 1;
		// the last candidate to have tied with candidate1 // intended to speed up program for loop below
		int equalVoteIndex = noCandidates;
		int candidate1 = 0;
		
		// Compare every pair of candidates
		for (int candidate2 = 0; candidate2 < noCandidates; candidate2++) {

			// Consider a candidate compared with themselves to be a winner
			if (candidate1 == candidate2) {
				continue;
			}

			// Determine count the ballots for each candidate
			int candidate1votes = 0;
			int candidate2votes = 0;

			for (int voter = 0; voter < noVoters; voter++) {
				int placement1 = getPlacement(candidate1, votes[voter]);
				int placement2 = getPlacement(candidate2, votes[voter]);
				
				if (placement1 < placement2) {
					candidate1votes++;
				} else{
					candidate2votes++;
				}
				// if a candidate has more than half the votes then they win by default
				if (candidate1votes > noVoters/2 || candidate2votes > noVoters/2){
					break;
				}
			}

			// determine if candidate1 is the winner if so increment the number of wins
			if (candidate1votes > candidate2votes) {
				noTimesCandidate1Wins++;
			}
			// update the potential winning candidate to candidate1
			else if (candidate1votes < candidate2votes){
				candidate1 = candidate2;
				noTimesCandidate1Wins = 1;
			}
			
			else{
				equalVoteIndex = candidate2;
			}
			// Determine if candidate 1 wins all
			if (noTimesCandidate1Wins == noCandidates) {
				return candidate1;
			}


		}
		
		
		// Compare every candidate to the potential winner "candidate1"
		// Reason: some candidates were not compared with the updated candidate1
		for (int candidate2 = 0; candidate2 < equalVoteIndex; candidate2++) {
			
			// Consider a candidate compared with themselves to be a winner
			// if there was one candidate and one voter this if would take care of that.
			if (candidate1 == candidate2) {
				continue;
			}

			// Determine count the ballots for each candidate
			int candidate1votes = 0;
			int candidate2votes = 0;

			for (int voter = 0; voter < noVoters; voter++) {
				int placement1 = getPlacement(candidate1, votes[voter]);
				int placement2 = getPlacement(candidate2, votes[voter]);
				if (placement1 < placement2) {
					candidate1votes++;
				} else{
					candidate2votes++;
				}

				// if a candidate has more than half the votes then they win by default
				if (candidate1votes > noVoters/2 || candidate2votes > noVoters/2){
					break;
				}
			}

			// Determines if there will be a tie
			if (candidate1votes <= candidate2votes) {
				return -1;
				}
			
		}

		return candidate1;
	}

	
	
	static int electionNo = 0;

	/**
	 * Main - reads several test elections using the text file votes.txt. Each
	 * election begins with two number, the number of voters and the number of
	 * candidates, all followed by the ballots of each voter.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		int noVoters;
		int noCandidates;

		Scanner in = new Scanner(new FileInputStream("votes.txt"));

		// read ballots for each election
		for (;;) {
			noVoters = in.nextInt();
			noCandidates = in.nextInt();

			if (noVoters == 0 && noCandidates == 0) {
				break;
			}

			final int[][] votes = new int[noVoters][noCandidates];

			// Read the ballots
			for (int i = 0; i < noVoters; i++) {
				for (int j = 0; j < noCandidates; j++) {
					votes[i][in.nextInt()] = j;
				}
			}

			new TimeExec(new Runnable() {
				public void run() {
					int winner = getPairwiseWinner(votes);
					if (winner >= 0) {
						System.out.printf("Winner of election %d is candidate %d\n", electionNo, winner);
					} else {
						System.out.printf("No winner for election %d\n", electionNo);
					}
				}
			}, "Election " + ++electionNo, System.out).start();
		}
		
		in.close();
	}
}