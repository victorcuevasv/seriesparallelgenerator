
package org.dataone.daks.seriespar;

import java.util.Random;
import java.util.Stack;


public class SeriesParallelGenerator {

	
	private static double WEAKEN = 0.1;
	private static int STMTIDX;
	
	
	public static void main(String args[]) {
		SeriesParallelGenerator generator = new SeriesParallelGenerator();
		Random rand = new Random();
		System.out.println(generator.asm(2, 3, 0.5, rand));
	}
	
	
	private String asm(int minStatements, int maxStatements, double nonTermProb, Random rand) {
		STMTIDX = 1;
		int dice = randInt(rand, 1, 2);
		String retVal = null;
		//Top construct is either seq (1) or par (2)
		if( dice == 1 )
			retVal = seq(minStatements, maxStatements, nonTermProb-WEAKEN, rand);
		else if( dice == 2 )
			retVal = par(minStatements, maxStatements, nonTermProb-WEAKEN, rand);
		else
			System.out.println("Error random number outside range.");
		return retVal;
	}
	
	
	private String seq(int minStatements, int maxStatements, double nonTermProb, Random rand) {
		int dice = randInt(rand, minStatements, maxStatements);
		StringBuilder builder = new StringBuilder();
		//Dice tells the number of statements to generate
		for(int i = 1; i <= dice; i++) {
			double randVal = Math.random();
			//Generate a nested par ... endpar
			if( randVal < nonTermProb )
				builder.append(par(minStatements, maxStatements, nonTermProb-WEAKEN, rand));
			//Generate a simple statement
			else
				builder.append(" a" + STMTIDX + " ");
		}
		return " seq " + builder.toString() + " endseq ";
	}
	
	
	private String par(int minStatements, int maxStatements, double nonTermProb, Random rand) {
		int dice = randInt(rand, minStatements, maxStatements);
		StringBuilder builder = new StringBuilder();
		//Dice tells the number of statements to generate
		for(int i = 1; i <= dice; i++) {
			double randVal = Math.random();
			//Generate a nested par ... endpar
			if( randVal < nonTermProb )
				builder.append(seq(minStatements, maxStatements, nonTermProb-WEAKEN, rand));
			//Generate a simple statement
			else
				builder.append(" a" + STMTIDX + " ");
		}
		return " par " + builder.toString() + " endpar ";
	}
	
	
	/*
	private String genGraph(int steps, int minStatements, int maxStatements) {
		Random rand = new Random();
		StringBuilder builder = new StringBuilder();
		Stack stack = new Stack();
		int indent = 0;
		int actId = 0;
		int dice = -1;
		boolean inseq = false;
		dice = randInt(rand, 1,2);
		//Top construct is either seq (1) or par (2)
		if( dice == 1 ) {
			inseq = true;
			builder.append(genIndent(indent) + "seq");
			stack.push("endseq");
		}
		//Proceed the generation for the rest of the steps
		for(int i = 0; i < steps-1; i++) {
			dice = randInt(rand, 1,2);
		}
		return builder.toString();
	}
	*/
	
	
	public static int randInt(Random rand, int min, int max) {
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	/*
	private String genIndent(int indent) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < indent; i++)
			builder.append("\t");
		return builder.toString();
	}
	*/
	
	
}


