/**
 * 
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * @author vmangipu
 * 
 */
public class Satisfaction {

	int t;
	ArrayList Items = new ArrayList();
	StringBuffer ItemsToEat = new StringBuffer();
	int itemDesc[];
	int val[];

	/**
	 * Method to check get the amount of time available and to list the items
	 * which can be consumed to get the maximum satisfaction in the time
	 * provided
	 * 
	 */
	public int checkSatisfaction(int t) {

		System.out.println("entered value:" + t);
		this.t = t;

		if (t == 0) {
			System.out.println("Not a valid No");
			throw new RuntimeException();
		} else {
			getItems();
			// sorting Items
			Collections.sort(Items);

			// display Menu

			Iterator iterator = Items.iterator();
			// System.out.println("Menu");
			// while (iterator.hasNext()) {
			// System.out.println(((Item) iterator.next()).toString());
			// }

			// finding the best list if Items
			// return 1000;

			return knapsack(itemDesc, val, t);

			// old return CompareItemsWithTime();
		}

	}

	/**
	 * Method to read the Items form the datastore/ input file provided
	 * 
	 */
	public void getItems() {

		Properties prop = new Properties();
		String propFileName = "config.properties";

		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(propFileName);

		if (inputStream != null) {
			try {
				prop.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				throw new FileNotFoundException("property file '"
						+ propFileName + "' not found in the classpath");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		BufferedReader br = null;
		Item item = null;

		try {

			Object sCurrentLine;
			StringTokenizer tokens = null;

			br = new BufferedReader(
					new FileReader(prop.getProperty("filepath")));
			int index = 0;
			final int lineCount = getLineCount(new FileReader(
					prop.getProperty("filepath")));
			itemDesc = new int[lineCount];
			val = new int[lineCount];
			while ((sCurrentLine = br.readLine()) != null) {
				tokens = new StringTokenizer(sCurrentLine.toString(), " ");
				itemDesc[index] = Integer.parseInt(tokens.nextToken());
				val[index++] = Integer.parseInt(tokens.nextToken());

				// item = new Item(tokens.nextToken(), tokens.nextToken());
				// Items.add(item);

			}

		} catch (Exception e) {
			System.out.println("exception while getting items:" + e.toString());
		}

	}

	/**
	 * Method to compare the Items to get the list of items that can be consumed
	 * in the given time to achieve the maximum satisfaction
	 * 
	 */
	public int CompareItemsWithTime() {
		System.out
				.println("Items To Eat to get Maximum saticfaction with the time of :"
						+ t + " are:");
		Iterator iterator = Items.iterator();
		Item tempItem = null;
		int index = 1;
		int totalSatisfaction = 0;
		int itemsSelected = 0;

		while (iterator.hasNext()) {
			tempItem = (Item) iterator.next();
			if (t >= tempItem.getTime()) {
				// breaking the loop in case 2 items are already selected
				if (itemsSelected == 2) {
					break;
				}

				System.out.println("Item No: " + index + ":"
						+ tempItem.toString());

				ItemsToEat.append("<BR>Item No: " + index + ":"
						+ tempItem.toString());

				totalSatisfaction += tempItem.getSatisfaction();
				t = t - tempItem.getTime();
				itemsSelected++;
			}
			index++;
		}

		System.out.println("Maximum satisfaction recieved is:"
				+ totalSatisfaction);
		return totalSatisfaction;

	}
	
	/**
	 * Method to check get the amount of time available and to list the items
	 * which can be consumed to get the maximum satisfaction in the time
	 * provided by using the knapsack algorithm
	 * 
	 */

	public static int knapsack(int val[], int wt[], int W) {
		int N = wt.length; 
		int[][] V = new int[N + 1][W + 1]; 
		
		for (int col = 0; col <= W; col++) {
			V[0][col] = 0;
		}
		// fill the row with 0 when you do not have any items
		for (int row = 0; row <= N; row++) {
			V[row][0] = 0;
		}
		for (int item = 1; item <= N; item++) {
			// update the values
			for (int time = 1; time <= W; time++) {
				// check the sstisfaction
				if (wt[item - 1] <= time) {
					// Given a satisfaction, check if the value of the current item +
					// value of the item that we could afford with the remaining
					// time is greater than the value without the current item itself
					V[item][time] = Math.max(val[item - 1]
							+ V[item - 1][time - wt[item - 1]],
							V[item - 1][time]);
				} else {
					// If the current item's weight is more than the running
					// weight, just carry forward the value without the current
					// item
					V[item][time] = V[item - 1][time];
				}
			}
		}
	
		return V[N][W];
	}

	public int getLineCount(FileReader fr) {
		int linenumber = 0;
		try {

			LineNumberReader lnr = new LineNumberReader(fr);

			

			while (lnr.readLine() != null) {
				linenumber++;
			}

			lnr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return linenumber;
	}

}
