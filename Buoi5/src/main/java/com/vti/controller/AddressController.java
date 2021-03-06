package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AddressDTO;
import com.vti.entity.Address;
import com.vti.service.AddressService;
import com.vti.service.IAddressService;

@RestController
@RequestMapping(value = "api/addresses")
public class AddressController {
	private IAddressService addressService;
	
	public AddressController() {
		addressService = new AddressService();
	}
	
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	@GetMapping
	public List<AddressDTO> getListAddresses() {
		
		List<Address> addresses = addressService.getListAddresses();
		
		List<AddressDTO> listAddressDTO = new ArrayList();
		
		for (Address add : addresses) {
			AddressDTO addDTO = new AddressDTO(
				add.getId(), 
				add.getStreet(), 
				add.getCity(),
				"root"
				//add.getAccounts().toString()
			);
			listAddressDTO.add(addDTO);
		}
		
		return listAddressDTO;
	}
	
}
