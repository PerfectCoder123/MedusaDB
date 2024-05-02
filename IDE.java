import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;

public class IDE {
	Executer executer;
	ArrayList<String> queries;
	String textArea_data = null;

	public IDE(String textArea_data) {
		this.textArea_data = textArea_data;
		queries = new ArrayList<>();
		tokenGenerator();
	}

	public void tokenGenerator() {
		try {
			StringTokenizer tokens = new StringTokenizer(textArea_data);
			while (tokens.hasMoreElements()) {
				queries.add(tokens.nextToken());
			}
			executer = new Executer(queries);
		} catch (Exception e) {

		}

	}
}

class Executer {
	Keyword keyword;
	ArrayList<String> queries;
	Properties configProperties;

	public Executer(ArrayList<String> queries) {

		try {
			this.queries = queries;
			keyword = new Keyword();
			configProperties = new Properties();
			configProperties.load(new FileInputStream(Keyword.config));
			interperate();
		} catch (IOException e) {
			NewJFrame.jLabel1.setText("<html><body>Terminal Output<br>Error 404 : Missing config file</body></html>");
			e.printStackTrace();
		}

	}

	public void interperate() throws FileNotFoundException, IOException {
		Iterator<String> iterator = queries.iterator();
		while (iterator.hasNext()) {

			switch (keyword.getMethodCode(iterator.next())) {
				case 1:
					String str = iterator.next();
					ArrayList<String> words = new ArrayList<>();
					while (!is_terminator(str)) {
						if (str.equalsIgnoreCase("fields"));
						else
							words.add(str);
						str = iterator.next();
					}
					str = str.substring(0, str.length() - 1);
					words.add(str);
					Object info[] = words.toArray();
					create(info);
					break;
				case 2:
					words = new ArrayList<>();
					str = iterator.next();
					while (!str.equalsIgnoreCase("from")) {
						words.add(str);
						str = iterator.next();
					}
					info = words.toArray();
					select(info, iterator.next());
					break;
				case 4:
					ArrayList<String> data = new ArrayList<>();
					iterator.next();
					str = iterator.next();
					iterator.next();
					String column = iterator.next();
					iterator.next();
					String value = iterator.next();
					delete(str, column, value);
					break;
				case 5:
					use(iterator.next());
					break;
				case 6:
					data = new ArrayList<>();
					str = iterator.next();
					String temp_table = null;
					if (str.equalsIgnoreCase("into"))
						temp_table = iterator.next();
					str = iterator.next();
					while (!str.equalsIgnoreCase("}")) {
						if (str.equalsIgnoreCase("{") || str.equalsIgnoreCase("values"));	
						else
							data.add(str);
						str = iterator.next();
					}
					insert(temp_table, data.toArray());
					break;
				case 7:
					desc(iterator.next());
					break;
				case 8:
					drop(iterator.next());
					break;
				case 9:
					show(iterator.next());
					break;

			}
		}
	}

	public static void deleteDirectory(File file) 
    { 
    
        for (File subfile : file.listFiles()) { 
  
            if (subfile.isDirectory()) { 
                deleteDirectory(subfile); 
            } 
  
            subfile.delete(); 
        } 
    } 
	
	private void desc(String table_name) {
		if(Keyword.current_db == null) NewJFrame.jLabel1.setText("<html><body>Terminal Output<br>Error 400 : No database selected</body></html>");
		else{
			if(is_terminator(table_name)) table_name = table_name.substring(0,table_name.length()-1);
			Properties p = new Properties();
			try {
				p.load(new FileInputStream(configProperties.getProperty(Keyword.current_db)));
				if(p.getProperty(table_name)==null) NewJFrame.jLabel1.setText("<html><body>Terminal Output<br>Error 404 : No such table found</body></html>");
				else{
					Properties p1 = new Properties();
					p1.load(new FileInputStream(p.getProperty(table_name)));
					Enumeration<Object> e = p1.keys();
					StringBuffer txt = new StringBuffer("<html><body>Terminal Output<br>");
					while(e.hasMoreElements()){
						txt.append((String)e.nextElement()+"<br>");
					}
					txt.append("</body></html>");
					NewJFrame.jLabel1.setText(txt.toString());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	private void show(String type) {
		String key[] = null;
		int i = 0;
		if (is_terminator(type))
			type = type.substring(0, type.length() - 1);
		Properties p = new Properties();
		if (type.equalsIgnoreCase("database")) {
			StringBuffer buf = new StringBuffer("<html><body>Terminal Output<br>");
			key = new String[configProperties.size()];
			Enumeration<Object> e = configProperties.keys();
			while (e.hasMoreElements()) {
				key[i] = (String) e.nextElement();
				buf.append(key[i]);
				buf.append("<br>");
				i++;
			}
			buf.append("</body></html>");
			NewJFrame.jLabel1.setText(buf.toString());
		} else if (type.equalsIgnoreCase("tables")) {
			StringBuffer buf = new StringBuffer("<html><body>Terminal Output<br>");
			if (Keyword.current_db != null) {
				try {
					p.load(new FileInputStream(configProperties.getProperty(Keyword.current_db)));
					key = new String[p.size()];
					Enumeration<Object> e = p.keys();
					while (e.hasMoreElements()) {
						key[i] = (String) e.nextElement();
						buf.append(key[i]);
						buf.append("<br>");
						i++;
					}
					buf.append("</body></html>");
					NewJFrame.jLabel1.setText(buf.toString());

				} catch (IOException e) {

					e.printStackTrace();
				}
			} else {
				NewJFrame.jLabel1.setText("<html><body>Terminal Output<br>Error 404 : No database selected</body></html>");
			}

		} else {
			NewJFrame.jLabel1.setText("<html><body>Terminal Output<br>Error 400 :unknown statement</body></html>");

		}

	}

	public static void deleteDirectory(String directoryFilePath)
{
    Path directory = Paths.get(directoryFilePath);

    if (Files.exists(directory))
    {
        try {
			Files.walkFileTree(directory, new SimpleFileVisitor<Path>()
			{
			    @Override
			    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException
			    {
			        Files.delete(path);
			        return FileVisitResult.CONTINUE;
			    }

			    @Override
			    public FileVisitResult postVisitDirectory(Path directory, IOException ioException) throws IOException
			    {
			        Files.delete(directory);
			        return FileVisitResult.CONTINUE;
			    }
			});
		} catch (IOException e) {
			
		}
    }
}


	private void drop(String table) {

		if (Keyword.current_db != null) {
			Properties p = new Properties();
			if(is_terminator(table)) table = table.substring(0,table.length() -1);
			try {
				p.load(new FileInputStream(configProperties.getProperty(Keyword.current_db)));
				String path = p.getProperty(table);
				path = path.substring(0,path.length()-12-table.length());
				p.remove(table);
				p.store(new FileOutputStream(configProperties.getProperty(Keyword.current_db)), null);
				deleteDirectory(path);
				NewJFrame.jLabel1.setText("<html><body>Terminal Output<br>query affected, one table dropped successfully!</body></html>");
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			NewJFrame.jLabel1.setText("<html><body>Terminal Output<br>Error 400 : No database selected</body></html>");
		}
	}
	private void insert(String table, Object values[]) {
		Properties p = new Properties();
		if (Keyword.current_db != null) {
			try {
				p.load(new FileInputStream(configProperties.getProperty(Keyword.current_db))); // loading current db to
																								// check table present
																								// or not
				if (p.getProperty(table) != null) {

					p.load(new FileInputStream(p.getProperty(table))); // loading current table
					Properties p1 = new Properties();
					for (int i = 0; i < values.length; ++i) {
						p1.load(new FileInputStream(p.getProperty((String) values[i++]))); // loading column name
																							// property file
						p1.put(p1.size() + 1 + "", (String) values[i]);
						p1.store(new FileOutputStream(p.getProperty((String) values[i - 1])), null);
						p1.clear();
					}
					NewJFrame.jLabel1.setText("<html><body>Terminal Output<br>query affected value inserted successfully!</body></html>");
				} 
				else {
					NewJFrame.jLabel1.setText("<html><body>Terminal Output<br>Error 404 : No such table found</body></html>");
				}

			} catch (IOException e) {
				NewJFrame.jLabel1.setText("<html><body>Terminal Output<br>Error 404 : No such column found</body></html>");

			}
		} else {
			NewJFrame.jLabel1.setText("<html><body>Terminal Output<br>Error 400 : No database selected</body></html>");
		}

	}

	private boolean is_terminator(String s) {
		if (s.endsWith(";"))
			return true;
		return false;
	}

	private void select(Object column_name[], String table_name) throws FileNotFoundException, IOException {
		ArrayList<ArrayList<String>> rows;
		String col_info[] = null;
		if (is_terminator(table_name))
			table_name = table_name.substring(0, table_name.length() - 1);
		Properties checkpProperties = new Properties();
		if(Keyword.current_db != null) checkpProperties.load(new FileInputStream(configProperties.getProperty(Keyword.current_db)));
		if (Keyword.current_db == null) {
			NewJFrame.jLabel1.setText("<html><body>Terminal Output<br>Error 400 : No database selected</body></html>");
		} else if (!checkpProperties.containsKey(table_name.toLowerCase())) {
			NewJFrame.jLabel1
					.setText("<html><body>Terminal Output<br>Error 404 : No such table found</body></html>");
		} else if (((String) column_name[0]).contains("*")) {
			rows = new ArrayList<>();
			Properties readProperties = new Properties();
			readProperties.load(new FileInputStream(Keyword.db + Keyword.slash + Keyword.current_db + Keyword.slash
					+ table_name + Keyword.slash + table_name + Keyword.dot));
			Enumeration<Object> column_key = readProperties.keys();
			Enumeration<Object> column_data = readProperties.elements();
			ArrayList<String> columns = new ArrayList<>();
			while (column_key.hasMoreElements()) {
				columns.add((String) column_key.nextElement());
			}
			col_info = new String[columns.size()];
			for (int i = 0; i < columns.size(); i++) {
				col_info[i] = columns.get(i);
			}

			while (column_data.hasMoreElements()) {
				Properties row_properties = new Properties();
				row_properties.load(new FileInputStream((String) column_data.nextElement()));
				Enumeration<Object> row_element = row_properties.elements();
				ArrayList<String> temp_data = new ArrayList<>();
				while (row_element.hasMoreElements()) {
					temp_data.add((String) row_element.nextElement());
				}
				rows.add(temp_data);
			}
			String table_info[][] = new String[rows.get(0).size()][rows.size()];
			for (int i = 0; i < rows.size(); i++) {
				for (int j = 0; j < rows.get(0).size(); j++) {
					table_info[j][i] = rows.get(i).get(j);
				}
			}
			NewJFrame.jLabel1.setText(
					"<html><body>  Terminal Output<br> <p style='color:blue'> Execution success query affected 0 rows ,0 columns affected </p></body></html>");

			NewJFrame.dynamicTable(table_info, col_info);
		} else {
			rows = new ArrayList<>();
			ArrayList<String> col_data = new ArrayList<>();
			Properties readProperties = new Properties();
			readProperties.load(new FileInputStream(Keyword.db + Keyword.slash + Keyword.current_db + Keyword.slash
					+ table_name + Keyword.slash + table_name + Keyword.dot));
			Properties colpProperties = new Properties();
			for (int i = 0; i < column_name.length; i++) {
				if (readProperties.containsKey(column_name[i])) {
					colpProperties
							.load(new FileInputStream(Keyword.db + Keyword.slash + Keyword.current_db + Keyword.slash
									+ table_name + Keyword.slash + (String) column_name[i] + Keyword.dot));

					Enumeration<Object> enumeration = colpProperties.elements();
					while (enumeration.hasMoreElements()) {
						col_data.add((String) enumeration.nextElement());
					}
					ArrayList<String> temp = (ArrayList<String>) col_data.clone();
					rows.add(temp);
					col_data.clear();

				} else {
					
					NewJFrame.jLabel1.setText(
							"<html><body>Terminal Output<br>Error 404 : No such column present in table</body></html>");
					break;
				}
			}

			String[][] table_tempinfo = rows.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
			String[][] table_info = new String[table_tempinfo[0].length][table_tempinfo.length];
			for (int i = 0; i < table_tempinfo[0].length; i++) {
				for (int j = 0; j < table_tempinfo.length; j++) {
					table_info[i][j] = table_tempinfo[j][i];
				}
			}

			NewJFrame.jLabel1.setText(
					"<html><body> Terminal Output<br> Execution success query affected 0 rows ,0 columns affected</body></html>");
			NewJFrame.dynamicTable(table_info, column_name);

		}

	}

	private void delete(String tableName, String column, String value) {
		Properties temp = new Properties();
		if(is_terminator(value)) value = value.substring(0,value.length()-1); //delete from table where name = jahid;
		if (Keyword.current_db == null)
			NewJFrame.jLabel1.setText("<html><body> Terminal Output<br>Error 400 : No database selected</body></html>");
		else {
			try {
				temp.load(new FileInputStream(configProperties.getProperty(Keyword.current_db)));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if (temp.getProperty(tableName) == null)
				NewJFrame.jLabel1
						.setText("<html><body> Terminal Output<br> Error 404 : No such table found</body></html>");
			else {
				Properties p = new Properties();
				try {
					p.load(new FileInputStream(temp.getProperty(tableName)));
					if (p.getProperty(column) == null)
						NewJFrame.jLabel1.setText(
								"<html><body> Terminal Output<br>Error 404 : No such column exist</body></html>");
					else {
						
						Properties p1 = new Properties();
						p1.load(new FileInputStream(p.getProperty(column)));
						int unique_key = 0;
						
						for (int i = 1; i <= p1.size(); i++) {
							if (((String) p1.getProperty(i + "")).equalsIgnoreCase(value))
								unique_key = i;
						}
						
						if (unique_key != 0) {
							Enumeration<Object> e = p.elements();
							while (e.hasMoreElements()) {
								String path = (String) e.nextElement();
								p1 = new Properties();
								p1.load(new FileInputStream(path));
								for (int i = unique_key; i < p1.size(); i++) {
									p1.replace(unique_key + "", p1.getProperty(i + 1 + ""));
									p1.store(new FileOutputStream(path), null);
								}
								p1.remove((p1.size() + ""));
								p1.store(new FileOutputStream(path), null);
							}
							NewJFrame.jLabel1.setText("<html><body>Terminal Output<br>query affected value deleted successfully!</body></html>");
						} else
						NewJFrame.jLabel1.setText("<html><body>Terminal Output<br>Error 404 : No such value found</body></html>");


					}
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}

	}

	private void create(Object[] info) {
		Properties p = new Properties();
		switch (((String) info[0]).toLowerCase()) {
			case "database":
				try {
					p.load(new FileInputStream(Keyword.config));
					p.setProperty((String) info[1],
							Keyword.db + Keyword.slash + (String) info[1] + Keyword.slash + (String) info[1]
									+ Keyword.dot);
					p.store(new FileOutputStream(Keyword.config), null);
					new File(Keyword.db + Keyword.slash + (String) info[1]).mkdir();
					new File(Keyword.db + Keyword.slash + (String) info[1] + Keyword.slash + (String) info[1]
							+ Keyword.dot)
							.createNewFile();
				} catch (IOException e) {

					e.printStackTrace();
				}NewJFrame.jLabel1.setText("<html><body>Terminal Output<br>query affected database successfully created</body></html>");
				break;

			case "table":
				if (Keyword.current_db != null) {

					try {
						new File(Keyword.db + Keyword.slash + Keyword.current_db + Keyword.slash + (String) info[1])
								.mkdir();
						new File(Keyword.db + Keyword.slash + Keyword.current_db + Keyword.slash + (String) info[1] // mading
																													// table
																													// file
																													// and
																													// folder
								+ Keyword.slash + (String) info[1] + Keyword.dot).createNewFile();

						p.load(new FileInputStream(Keyword.db + Keyword.slash + Keyword.current_db + Keyword.slash
								+ Keyword.current_db + Keyword.dot));
						p.setProperty((String) info[1], Keyword.db + Keyword.slash + Keyword.current_db + Keyword.slash // loading
																														// and
																														// storing
																														// table
																														// info
																														// in
																														// current
																														// db
								+ (String) info[1] + Keyword.slash + (String) info[1] + Keyword.dot);
						p.store(new FileOutputStream(Keyword.db + Keyword.slash + Keyword.current_db + Keyword.slash
								+ Keyword.current_db + Keyword.dot), null);

						Properties p1 = new Properties();
						p1.load(new FileInputStream(
								Keyword.db + Keyword.slash + Keyword.current_db + Keyword.slash + (String) info[1] // loading
																													// table
										+ Keyword.slash + (String) info[1] + Keyword.dot));
						for (int i = 2; i < info.length; i++) {
							new File(Keyword.db + Keyword.slash + Keyword.current_db + Keyword.slash + (String) info[1]
									+ Keyword.slash + (String) info[i] + Keyword.dot).createNewFile(); // making column
																										// file
							// properties
							p1.setProperty((String) info[i],
									Keyword.db + Keyword.slash + Keyword.current_db + Keyword.slash
											+ (String) info[1] + Keyword.slash + (String) info[i] + Keyword.dot); // storing
																													// column
																													// path
																													// in
																													// table
						}
						p1.store(new FileOutputStream(
								Keyword.db + Keyword.slash + Keyword.current_db + Keyword.slash + (String) info[1]
										+ Keyword.slash + (String) info[1] + Keyword.dot),
								null);
					NewJFrame.jLabel1.setText("<html><body>Terminal Output<br>query affected table successfully created</body></html>");
					} catch (IOException e) {
						System.out.println("Error");
					}

				} else {
					NewJFrame.jLabel1
							.setText("<html><body>Terminal Output<br>Error 400 : No database selected</body></html>");
	
				}

				break;
		}

	}

	private void use(String dbase) {
		if (is_terminator(dbase))
			dbase = dbase.substring(0, dbase.length() - 1);
		if (configProperties.containsKey(dbase)) {
			Keyword.current_db = dbase;
			NewJFrame.jLabel1.setText("<html><body>Terminal Output<br>database selected</body></html>");
		} else {
			NewJFrame.jLabel1
					.setText("<html><body>Terminal Output<br>Error 404 : No such database found</body></html>");

		}
	}
}