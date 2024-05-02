public class Keyword {

	public static String current_db = null ;
	public static final String db = "databases";
    public static final char slash = '/';
    public static final String dot = ".properties";
    public static final String config = "config.properties";

	private static final int create = 1;
	private static final int select = 2;
	private static final int desc = 7;
	private static final int delete = 4;
	private static final int use = 5;
	private static final int alter = 3;
	private static final int drop = 8;
	private static final int show = 9;
	private static final int insert = 6;
	
	public int getMethodCode(String query) {

		if(query.equalsIgnoreCase("create")) return create;
		if(query.equalsIgnoreCase("desc")) return desc;
		if(query.equalsIgnoreCase("delete")) return delete;
		if(query.equalsIgnoreCase("select")) return select;
		if(query.equalsIgnoreCase("use")) return use;
		if(query.equalsIgnoreCase("alter")) return alter;
		if(query.equalsIgnoreCase("drop")) return drop;
		if(query.equalsIgnoreCase("show")) return show;
		if(query.equalsIgnoreCase("insert")) return insert;

		
		return 0;
		
	}
}
