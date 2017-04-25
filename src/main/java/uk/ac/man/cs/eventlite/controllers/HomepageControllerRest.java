package uk.ac.man.cs.eventlite.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/")
public class HomepageControllerRest {

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getRoot(Model model, UriComponentsBuilder b) {

		UriComponents link = b.path("/").build();
		model.addAttribute("self_link", link.toUri());

		return "home/index";
	}
}
