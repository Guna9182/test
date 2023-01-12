package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Account;
import com.cg.exception.NoSuchAccountFoundException;
import com.cg.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService accService;

	@PostMapping("/addaccount")
	public ResponseEntity<Account> addAccount(@RequestBody Account acc) {
		Account newAcc = accService.addAccount(acc);
		return new ResponseEntity<>(newAcc, HttpStatus.CREATED);
	}

	@GetMapping("/allaccounts")
	public ResponseEntity<List<Account>> getAllAccounts() {
		List<Account> accList = accService.getAllAccounts();
		return new ResponseEntity<>(accList, HttpStatus.OK);
	}

	@PutMapping("/updateaccount/{accNo}")
	public ResponseEntity<Account> updateAccount(@PathVariable("accNo") int accNo, @RequestBody Account acc) {
		try {
			Account updatedAcc = accService.updateAccount(accNo, acc);
			return new ResponseEntity<>(updatedAcc, HttpStatus.OK);
		} catch (NoSuchAccountFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteaccount/{accNo}")
	public ResponseEntity<Void> deleteAccount(@PathVariable("accNo") int accNo) {
		try {
			boolean deleted = accService.deleteAccount(accNo);
			if (deleted) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (NoSuchAccountFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/findaccby/{accNo}")
	public ResponseEntity<Account> findAccountByAccNo(@PathVariable("accNo") int accNo) {
		try {
			Account acc = accService.findAccountByAccNo(accNo);
			return new ResponseEntity<>(acc, HttpStatus.OK);
		} catch (NoSuchAccountFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}