package controllers;

import outils.Controller;
import outils.Get;
import outils.ModelView;
import outils.MySession;
import outils.Url;

@Controller
public class LoginController {
    
    
    @Get    
    @Url(path ="/login")
    public ModelView accueil(MySession session) throws Exception {
        
        
         
        return null;    
    }

}
