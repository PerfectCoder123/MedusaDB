package MedussaDatabase.MedusaEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class QueryRetrieval {
	/*
	 * Do all the checking beforehand no error handling is going to be done while
	 * handling methods its your own risk take of each possible risks
	 */

	// This method has all proper path of column it will just apply joins and return
	// data in matrix form
	
	private Object[][] select(List<String> columName) {
		int row = PropertyBuilder.buildProperty(columName.get(0)).size();
		Object[][] table = new String[row][columName.size()];
		for (int i = 0 ; i < columName.size(); i++) {
			// loading column property file and adding to array;
			Properties columnProperty = PropertyBuilder.buildProperty(columName.get(i));
			for (int j = 0; j < columnProperty.size(); j++){ 
				table[j][i] = columnProperty.getProperty(j + "");
			}
            
		}
		return table;
	}

	
}

