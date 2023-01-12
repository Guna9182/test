package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Account;
import com.cg.exception.NoSuchAccountFoundException;
import com.cg.repository.AccountRepo;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepo accRepo;

	@Override
	public Account addAccount(Account acc) {
		return accRepo.save(acc);
	}

	@Override
	public List<Account> getAllAccounts() {
		return accRepo.findAll();
	}

	@Override
	public Account updateAccount(int accNo, Account acc) throws NoSuchAccountFoundException {
		Account existingAcc = findAccountByAccNo(accNo);
		existingAcc.setBankName(acc.getBankName());
		existingAcc.setAccBal(acc.getAccBal());
		return accRepo.save(existingAcc);
	}

	@Override
	public boolean deleteAccount(int accNo) throws NoSuchAccountFoundException {
		accRepo.deleteById(accNo);
		return !(accRepo.existsById(accNo));
	}

	@Override
	public Account findAccountByAccNo(int accNo) throws NoSuchAccountFoundException {
		Optional<Account> acc = accRepo.findById( accNo);
		if (acc.isPresent())
			return acc.get();
		else
			throw new NoSuchAccountFoundException("Account not found with id " + accNo);
	}

}