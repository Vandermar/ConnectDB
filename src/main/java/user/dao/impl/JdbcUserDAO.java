package user.dao.impl;
import user.connection.ConnectionFactory;
import user.dao.Repository;
import user.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JdbcUserDAO implements Repository<User, Integer> {

    private ConnectionFactory data;
    public void setData(ConnectionFactory data) {
	this.data = data;
    }
    public boolean delete(Integer key){
	String sql = "DELETE FROM USERS WHERE USERID =?";
	int res=0;
	Connection con=null;
	try {
	    con= data.openConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setInt(1,key);
	    res= ps.executeUpdate();
	    ps.close();
	}catch (SQLException e) {
	    e.printStackTrace();


	} finally {
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {}
	    }
	}
	return (res==0)?false:true;
    }

    private String createQuery(Map<String, Object> args){
	String sql = "SELECT * FROM USERS WHERE";
	String tmp = new String();
	Entry<String, Object> entry;

	Iterator<Entry<String, Object>> it = args.entrySet().iterator();
	while (it.hasNext()) {
	    entry = it.next();
	    tmp += entry.getKey() + " =? ";
	    if(it.hasNext())
		tmp+=" AND ";
	}

	sql+= " " + tmp;


	return sql;
    }
    private PreparedStatement createStatement(Map<String, Object> args,String sql) 
    {
	int index = 1;
	PreparedStatement ps=null;
	Entry<String, Object> entry;
	try {
	    Iterator<Entry<String, Object>> it = args.entrySet().iterator();

	    ps = data.openConnection().prepareStatement(sql);

	    it = args.entrySet().iterator();
	    while (it.hasNext()) {
		entry = it.next();
		Object value = entry.getValue();
		ps.setObject(index++, value);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return ps;
    }

    public List<User> findAllBy(Map<String,Object> args) {


	List<User> users=new ArrayList<User>();
	try {
	    String sql = createQuery(args); 
	    PreparedStatement ps = createStatement(args,sql);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		users.add( new User(
			rs.getInt("USERID"),
			rs.getString("LOGIN"), 
			rs.getString("PASS")
			));
	    }
	    rs.close();
	    ps.close();


	}catch (SQLException e) {
	    e.printStackTrace();

	} 
	return users;
    }


    public User saveOrUpdate(User entity){
	Connection conn = null;
	String sql = "INSERT INTO USERS (USERID, LOGIN, PASS) VALUES (?, ?, ?)"+
		"ON DUPLICATE KEY UPDATE LOGIN=VALUES(LOGIN), PASS=VALUES(PASS)";


	try {
	    conn = data.openConnection();
	    PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	    ps.setInt(1, entity.getId());
	    ps.setString(2, entity.getLogin());
	    ps.setString(3, entity.getPass());
	    ps.executeUpdate();
	    if(entity.isNew()){
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {

		    entity.setId(rs.getInt(1));
		}
		rs.close();
	    }
	    ps.close();


	} catch (SQLException e) {
	    e.printStackTrace();

	} finally {
	    if (conn != null) {
		try {
		    conn.close();
		} catch (SQLException e) {}
	    }
	}
	return entity;

    }

    public User getByKey(Integer key) {
	Map<String, Object> args = new HashMap<String, Object>();
	User user=null;
	try {
	    args.put("USERID", key);

	    String sql = createQuery(args);


	    PreparedStatement ps = createStatement(args,sql);
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
		user = new User(
			rs.getInt("USERID"),
			rs.getString("LOGIN"), 
			rs.getString("PASS")
			);
	    }
	    rs.close();
	    ps.close();


	}catch (SQLException e) {
	    e.printStackTrace();

	} 
	return user;

    }


    public  User findBy(Map<String,Object> args){
	User user=null;
	try {
	    String sql = createQuery(args); 
	    sql+=" LIMIT 1";
	    PreparedStatement ps = createStatement(args,sql);
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
		user = new User(
			rs.getInt("USERID"),
			rs.getString("LOGIN"), 
			rs.getString("PASS")
			);
	    }
	    rs.close();
	    ps.close();


	}catch (SQLException e) {
	    e.printStackTrace();;

	}

	return user;
    }



}
