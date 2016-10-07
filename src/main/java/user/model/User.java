package user.model;

public class User extends Entity {

    private String login;
    private String pass;

    public User(){


	login=null;
	pass=null;
    }

    public User(String log, String pas){

	login=log;
	pass=pas; 
    }
    public User(int id,String log, String pas){
	super(id);
	login=log;
	pass=pas; 
    }
    public boolean isNew(){
	return id==0;
    }

    @Override
    public String toString() {
	return "User [\nID=" + id + ", \nLogin =" + login + ", \nPassword=" + pass + "\n]";
    }


    /**
     * @return the login
     */
    public String getLogin() {
	return login;
    }
    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
	this.login = login;
    }
    /**
     * @return the pass
     */
    public String getPass() {
	return pass;
    }
    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
	this.pass = pass;
    }

    /**
     * @return the id
     */
    public int getId() {
	return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
	this.id = id;
    }


}
