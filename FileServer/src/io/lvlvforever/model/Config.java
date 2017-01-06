package io.lvlvforever.model;
/**
 * ClassName:Config <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年11月23日 下午10:20:24 <br/>
 * @author   lvlv
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Config {
	private int id;
	private String key;
	private String value;
	@Override
	public String toString() {
		return "Config [id=" + id + ", key=" + key + ", value=" + value + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Config() {
		super();
	}
	
}

