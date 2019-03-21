package com.example.backendninja.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.backendninja.converter.ContactConverter;
import com.example.backendninja.entity.Contact;
import com.example.backendninja.model.ContactModel;
import com.example.backendninja.repository.ContactRepository;
import com.example.backendninja.service.ContactService;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService  {
	
	@Autowired
	@Qualifier("contactServiceImpl")
	ContactService contactSerice;
	
	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;

	@Override
	public ContactModel addContact(ContactModel contactModel) {
		 Contact contact =  contactRepository.save(contactConverter.convertContactModel2Contact(contactModel));
		 return contactConverter.convertContact2ContactModel(contact);
	}

	@Override
	public List<ContactModel> ListAllContacts() {
		
		List<ContactModel> contacstModel= new ArrayList<ContactModel>();
		List<Contact> contacts = contactRepository.findAll();
		
		for (Contact contact : contacts) {
			contacstModel.add(contactConverter.convertContact2ContactModel(contact));
		}
		
		return contacstModel;
	}

	@Override
	public Contact findContactById(int id) {
		return contactRepository.findById(id);
	}
	
	@Override
	public ContactModel findContactByIdModel(int id) {
		return contactConverter.convertContact2ContactModel(contactRepository.findById(id));
	}

	@Override
	public void removeContact(int id) {
		Contact contact = findContactById(id);
		if(contact != null){
			contactRepository.delete(contact);
		}
	}
}
  