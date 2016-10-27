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

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Satisfaction statisfaction = new Satisfaction();
		try {
			int time = Integer.parseInt(new BufferedReader(
					new InputStreamReader(System.in)).readLine());

			new Satisfaction().checkSatisfaction(time);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		

	}

	/**
	 * Method to check get the amount of time available and to list the items
	 * which can be consumed to get the maximum satisfaction in the time
	 * provided
	 * 
	 */
	public void checkSatisfaction(int t) {
		
		

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
			System.out.println("Menu");
			while (iterator.hasNext()) {
				System.out.println(((Item) iterator.next()).toString());
			}

			// finding the best list if Items

			CompareItemsWithTime();
		}

	}

	/**
	 * Method to read the Items form the datastore/ input file provided
	 * 
	 */
	public void getItems() {
		
		
		Properties prop = new Properties();
		String propFileName = "config.properties";

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			try {
				prop.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
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

			br = new BufferedReader(new FileReader(prop.getProperty("filepath")));

			while ((sCurrentLine = br.readLine()) != null) {
				tokens = new StringTokenizer(sCurrentLine.toString(), " ");
				item = new Item(tokens.nextToken(), tokens.nextToken());
				Items.add(item);
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
	public void CompareItemsWithTime() {
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
				totalSatisfaction += tempItem.getSatisfaction();
				t = t - tempItem.getTime();
				itemsSelected++;
			}
			index++;
		}
 if(totalSatisfaction==0){
	 throw new RuntimeException();
 }
		System.out.println("Maximum satisfaction recieved is:"
				+ totalSatisfaction);

	}

}
