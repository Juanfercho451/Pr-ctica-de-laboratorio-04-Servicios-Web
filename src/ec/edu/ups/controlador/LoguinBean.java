package ec.edu.ups.controlador;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class LoguinBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("accesos", 1);
	}
	
	public void logout() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("accesos", null);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
}
