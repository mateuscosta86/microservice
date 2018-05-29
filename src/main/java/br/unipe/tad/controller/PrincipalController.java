package br.unipe.tad.controller;

import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ss")
public class PrincipalController {
	
	@RequestMapping(path="/", method= RequestMethod.GET)
	public String principal() {
		
		return "PrincipalView";		
	}

}
