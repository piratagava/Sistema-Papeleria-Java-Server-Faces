package ClasesAuxiliares;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anonymous
 */
public class filtroUrl implements PhaseListener{
  
    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        
        //Capturamos  el nombre de la pagina actual 
        String currentPage=facesContext.getViewRoot().getViewId();
        
        //Variable Boolean comprobar si es la pagina de login la que se capturo
        boolean isPageLogin= currentPage.lastIndexOf("login.xhtml")> -1 ? true:false;
        
        HttpSession session=(HttpSession) facesContext.getExternalContext().getSession(true);
        
        //recuperamos el objeto de string que se guardo en loginBean
        Object usuario=session.getAttribute("usuario");
        
        //sino es la pagina de logeo y usuario es nulo lo redirigimos a la pag login
        if(!isPageLogin && usuario==null){
            NavigationHandler nHandler= facesContext.getApplication().getNavigationHandler();
            nHandler.handleNavigation(facesContext, null,"/login.xhtml");
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {        
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
