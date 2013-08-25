package Nomination.form;

import org.apache.struts.action.ActionForm;

public final class TopForm extends ActionForm{
	private int[] cbox;

	public void setcbox(int[] cbox) {
		this.cbox = cbox;
	}
	public int[] getcbox() {
		return this.cbox;
	}
	
}
