package daos;

import java.sql.Connection;
import dbutils.DBUtil;

public class Dao implements AutoCloseable {
	protected Connection con;
	
	protected Dao() throws Exception {
		con = DBUtil.getConnection();
	}
	
	@Override
	public void close() {
		try {
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}