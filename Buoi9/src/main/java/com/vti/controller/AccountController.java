package com.vti.controller;

import java.util.List;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "api/accounts")
public class AccountController {
	
	@Autowired
	private IAccountService acService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	@Transactional
	public List<AccountDTO> getListAccounts() {
		
		List<Account> accounts = acService.getListAccount();
		
		List<AccountDTO> listAcDTO = modelMapper.map(
			accounts,   
			new TypeToken <List<AccountDTO>> () {}.getType()
		);
		
		return listAcDTO;
	}
	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@GetMapping("/paging")
//	public Page<AccountDTO> getListAccountsPaging(Pageable pageable) {
//		
//		Page<Account> pageAccount = acService.getListAccountsPaging(pageable);
//		
//		List<AccountDTO> listAcDTO = modelMapper.map(
//			pageAccount.getContent(),  
//			new TypeToken <List<AccountDTO>> () {}.getType()
//		);
//		Page<AccountDTO> pageAcDTO = new PageImpl(listAcDTO, pageable, pageAccount.getTotalElements());
//		
//		return pageAcDTO;
//	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/paging")
	public Page<AccountDTO> getListAccountsPaging(
			Pageable pageable,
			@RequestParam(value = "nameSearching") String nameSearching
	) {
		
		Page<Account> pageAccount = acService.getListAccountsPaging(pageable, nameSearching);
		
		List<AccountDTO> listAcDTO = modelMapper.map(
			pageAccount.getContent(),  
			new TypeToken <List<AccountDTO>> () {}.getType()
		);
		Page<AccountDTO> pageAcDTO = new PageImpl(listAcDTO, pageable, pageAccount.getTotalElements());
		
		return pageAcDTO;
	}
	
	
	@GetMapping("/{id}")
	public AccountDTO getAccountById(@PathVariable(name = "id") int id) {
		Account ac = acService.getAccountById(id);
		AccountDTO acDTO = modelMapper.map(ac, AccountDTO.class);
		return acDTO;
	}
	
	@GetMapping("/searching")
	public ResponseEntity<?> getAccountByUsername(@RequestParam(value = "username") String username) {
		
		System.out.println("Rearching username: " + username);
		
		Account ac = acService.getAccountByUsername(username);
		
		System.out.println("Result get account by username: ");
		System.out.println(ac);//Entity account must override method toString()
		System.out.println("-----------------------------------");
		
		if (ac == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found");
		
		System.out.println("Mapp account DTO by username: ");
		
		AccountDTO acDTO = modelMapper.map(ac, AccountDTO.class);
		
		System.out.println(acDTO);
		System.out.println("-----------------------------------");
		
		JSONObject message = new JSONObject();
		message.put("ResultText", "Account found");
		message.put("account", "123abc");
		
		return ResponseEntity.status(HttpStatus.OK).body(message.toString());
	}
	
	
//	@GetMapping("/searching")
//	public AccountDTO getAccountByUsername(@RequestParam(value = "username") String username) {
//		System.out.println("Rearching username: " + username);
//		Account ac = acService.getAccountByUsername(username);
//		System.out.println("Result get account by username: ");
//		System.out.println(ac.getUsername());
//		System.out.println("-----------------------------------");
//		AccountDTO acDTO = modelMapper.map(ac, AccountDTO.class);
//		return acDTO;
//	}
	
}
