package user.model;

public abstract class Entity {
    private final static int N=0;
    protected int id;
    public Entity(){
	id=N;
    }
    public Entity(int id){
	this.id=id;
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
