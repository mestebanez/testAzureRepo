package net.dovale.okta.secure_rest_api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloWorldController {

    @Autowire
    private mYprop prop;
/*
    @GetMapping("/")
    public String helloWorld(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
*/
    @GetMapping(value = "/wel/json", produces = "application/json")
    public TestJson testJson()
    {
        return new TestJson();
    }

    @GetMapping("/wel/{key}")
    public ModelAndView helloWorld(@PathVariable("key") String key, HttpServletRequest request){
        HttpSession session = request.getSession();
        session = request.getSession(true);

        List arrLst = new ArrayList();

        if (session.getAttribute("testAtt")==null) {
            session.setAttribute("testAtt", arrLst);
        }
        else {
            ((List)session.getAttribute("testAtt")).add(prop.getClientId());
        }

        ModelAndView  modelView =  new ModelAndView("welcome");

        modelView.getModelMap().put("key", key);
        modelView.getModelMap().put("attLst", session.getAttribute("testAtt"));

        return modelView;
    }



    @GetMapping("/jasmine")
    public String helloWorldJas(){
        return "redirect://http/://www.yahoo.co.uk";
    }

    @GetMapping("/owen")
    public ModelAndView helloWorldOwen(ModelMap model, HttpServletRequest request){
        HttpSession session = request.getSession(true);
        return new ModelAndView("redirect:http://www.google.co.uk?testAtt=" + session.getAttribute("testAttribute"), model);
    }
}
