package io.lvlvforever.util;


/**
 * ClassName:ResponseWrapper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年10月26日 下午11:16:49 <br/>
 * @author   lvlv
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class  ResponseWrapper {

	private Object object;
	private String result ;
	private String message;
	public ResponseWrapper(Object object, String result, String message) {
		super();
		this.object = object;
		this.result = result;
		this.message = message;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ResponseWrapper [object=" + object + ", result=" + result
				+ ", message=" + message + "]";
	}
	
}

