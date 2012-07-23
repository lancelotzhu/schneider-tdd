package com.schneider.tdd.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.appfuse.dao.BaseDaoTestCase;
import org.appfuse.dao.UserDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.schneider.tdd.model.Account;

public class AccountDaoTest extends BaseDaoTestCase {
	
	@Autowired
	private AccountDao target = null;
	
	@Autowired
	private UserDao userDao = null;
	
	@Test
	public void testCRUD() {
		Account account = new Account();
		account.setBalance(new BigDecimal("100000"));
		account.setStatus("VALID");
		account.setDateCreated(new Date());
		account.setUser(userDao.get(-1L));
		account = target.create(account);
		
		account.setBalance(new BigDecimal("90000"));
		target.update(account);		
		account = target.getByUsername("user").iterator().next();
		assertEquals(new BigDecimal("90000"), account.getBalance());
		
		target.delete(account.getId());
		try {
			target.get(account.getId());
		} catch (ObjectRetrievalFailureException ignored) {
		}
	}

}
