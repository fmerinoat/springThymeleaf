package com.example.backendninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.backendninja.constant.ViewConstant;
import com.example.backendninja.converter.ContactConverter;
import com.example.backendninja.entity.Contact;
import com.example.backendninja.model.ContactModel;
import com.example.backendninja.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	
	private final static Log LOG = LogFactory.getLog(ContactController.class);
	
	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;
	
	//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/contactform")
	public String redirectConctForm(@RequestParam(name="id", required=false)int id,
			Model model){
		
		ContactModel contactModel = new ContactModel();
		if(id != 0) {
			contactModel = contactService.findContactByIdModel(id);
		}
		model.addAttribute("contactModel", contactModel);
		return ViewConstant.CONTACT_FORM;//"contactform";
	}
	
	@GetMapping("/cancel")
	public String cancel(){
		LOG.info("contact controller -- redirectConctats()");
		//return ViewConstant.CONTACTS;//"contacts";
		return "redirect:/contacts/showcontacts";
	}
	
	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel,
			Model model) {
		LOG.info("METHOD: addContact() -- PARAMS contactModel: " + contactModel.toString());
		
		if(contactService.addContact(contactModel) != null){
			model.addAttribute("result", 1);
		}else {
			model.addAttribute("result", 0);
		}
		
		//return ViewConstant.CONTACTS;
		return "redirect:/contacts/showcontacts";
	}
	
	@GetMapping("/showcontacts")
	public ModelAndView showContacts () {
		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("username", user.getUsername());
		
		mav.addObject("contacts", contactService.ListAllContacts());
		
		return mav;
	}
	
	@GetMapping("/removecontact")
	public ModelAndView removeContact(@RequestParam(name="id", required=true)int id) {
		contactService.removeContact(id);
		return showContacts();
	}
	/*
	 * es otra forma de hacer la funcion pero tambien habria que cmabiar el html 
	 * th:href="@{/contacts/removecontact/__${contact.id}__}"
	@GetMapping("/removecontact/{id}")
	public ModelAndView removeContact(@PathVariable(name="id", required=true)int id) {
		contactService.removeContact(id);
		return showContacts();
	}
	*/
	

}
