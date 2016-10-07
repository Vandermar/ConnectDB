package common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import user.dao.Repository;
import user.model.User;

public class App {

    public static void main(String[] args) {
	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	Repository <User,Integer>userDAO = (Repository<User, Integer>) context.getBean("userDAO");
	Map<String,Object> map = new HashMap<String, Object>();
	map.put("Login","MyTest@login.com");
	map.put("Pass", "MyPass");


	List<User> list= userDAO.findAllBy(map);
	for(User u: list){
	    System.out.println(u);
	}

    }

}
