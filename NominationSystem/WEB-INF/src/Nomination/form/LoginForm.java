package Nomination.form;
import org.apache.struts.validator.ValidatorForm;

public final class LoginForm extends ValidatorForm  {

	private String id;
	private String name;
	private String password;

	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}
}