/**
 * 
 */

import java.io.BufferedReader;
import java.io.File;
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
	long totalSatisfaction=0;

	/**
	 * Method to check get the amount of time available and to list the items
	 * which can be consumed to get the maximum satisfaction in the time
	 * provided
	 * 
	 */
	public long checkSatisfaction(int t) {

		//System.out.println("entered value:" + t);
		this.t = t;
		
		ItemsToEat.append("<table border=1><th>Item No</th><th>Time to consume</th><th>Satisfaction</th></tr>");

		if (t == 0) {
			//System.out.println("Not a valid No");
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
			solve(val,itemDesc,t,val.length-1);
			
			return totalSatisfaction;

			// old return CompareItemsWithTime();
		}

	}

	/**
	 * Method to read the Items form the datastore/ input file provided
	 * 
	 */
	public void getItems() {

		/*Properties prop = new Properties();
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
		}*/

		BufferedReader br = null;
		Item item = null;

		try {

			Object sCurrentLine;
			StringTokenizer tokens = null;

			//br = new BufferedReader(
				//	new FileReader(prop.getProperty("filepath")));
			br = new BufferedReader(
				new FileReader(new File("c:/data.txt")));
			
			int index = 0;
			final int lineCount = getLineCount(new FileReader(
					new File("c:/data.txt")));
			itemDesc = new int[lineCount];
			val = new int[lineCount];
			while ((sCurrentLine = br.readLine()) != null) {
				tokens = new StringTokenizer(sCurrentLine.toString(), " ");
				

				 item = new Item(tokens.nextToken(), tokens.nextToken());
				 Items.add(item);
				 
				 itemDesc[index] = (int) item.getSatisfaction();
					val[index++] = item.getTime();

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
		//System.out
			//	.println("Items To Eat to get Maximum saticfaction with the time of :"
				//		+ t + " are:");
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

			//	System.out.println("Item No: " + index + ":"
				//		+ tempItem.toString());

				ItemsToEat.append("<BR>Item No: " + index + ":"
						+ tempItem.toString());

				totalSatisfaction += tempItem.getSatisfaction();
				t = t - tempItem.getTime();
				itemsSelected++;
			}
			index++;
		}

		//System.out.println("Maximum satisfaction recieved is:"
		//		+ totalSatisfaction);
		return totalSatisfaction;

	}
	
	/**
	 * Method to check get the amount of time available and to list the items
	 * which can be consumed to get the maximum satisfaction in the time
	 * provided by using the knapsack algorithm
	 * 
	 */


	

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
	
	
	
	
	public void solve(int[] wt, int[] val, int W, int N)
    {
        int NEGATIVE_INFINITY = Integer.MIN_VALUE;
        int[][] m = new int[N + 1][W + 1];
        int[][] sol = new int[N + 1][W + 1];
 
        for (int i = 1; i <= N; i++)
        {
            for (int j = 0; j <= W; j++)
            {
                int m1 = m[i - 1][j];
                int m2 = NEGATIVE_INFINITY; 
                if (j >= wt[i])
                    m2 = m[i - 1][j - wt[i]] + val[i];
                /** select max of m1, m2 **/
                m[i][j] = Math.max(m1, m2);
                sol[i][j] = m2 > m1 ? 1 : 0;
            }
        }        
        /** make list of what all items to finally select **/
        int[] selected = new int[N + 1];
        for (int n = N, w = W; n > 0; n--)
        {
            if (sol[n][w] != 0)
            {
                selected[n] = 1;
                w = w - wt[n];
            }
            else
                selected[n] = 0;
        }
        /** Print finally selected items **/
              for (int i = 1; i < N + 1; i++)
            if (selected[i] == 1){
              //  System.out.print(i +" ");
                totalSatisfaction+=val[i];
                ItemsToEat.append("<tr><th>"+i+"</th><th>"+wt[i] + "</th><th>"+val[i]+"</th></tr>");
            }
        
        ItemsToEat.append("</tr></table>");
    }

}
