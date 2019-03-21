package com.example.backendninja.service;

import java.util.List;

import com.example.backendninja.entity.Contact;
import com.example.backendninja.model.ContactModel;

public interface ContactService {
	
	public abstract ContactModel addContact(ContactModel contactModel);
	public abstract List<ContactModel> ListAllContacts();
	public abstract Contact findContactById(int id);
	public abstract ContactModel findContactByIdModel(int id);
	public abstract void removeContact(int id);
	
}
