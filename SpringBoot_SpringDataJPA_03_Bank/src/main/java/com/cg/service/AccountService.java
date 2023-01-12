package com.cg.service;

import java.util.List;

import com.cg.entity.Account;
import com.cg.exception.NoSuchAccountFoundException;

public interface AccountService {

	public Account addAccount(Account acc);

	public List<Account> getAllAccounts();

	public Account updateAccount(int accNo, Account acc) throws NoSuchAccountFoundException;

	public boolean deleteAccount(int accNo) throws NoSuchAccountFoundException;

	public Account findAccountByAccNo(int accNo) throws NoSuchAccountFoundException;
}