package com.dcp.test;

import com.test.Application;
import com.test.entity.Author;
import com.test.entity.Book;
import com.test.entity.Contact;
import com.test.repository.AuthorRepository;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthorRepositoryTest {

	public static String author_id;

	@Autowired
	private AuthorRepository authorRepo;

	@Test
	@Transactional
	@Rollback(false)
	public void test1() {
		Author author = new Author();
		author.setName("Test Name");
		author.setEmail("test@mail.com");

		//Add 1 book
		Book book = new Book();
		book.setAuthor(author);
		author.getBooks().add(book);

		//Add 2 contacts
		Contact c1 = new Contact();
		c1.setAuthor(author);
		Contact c2 = new Contact();
		c2.setAuthor(author);
		author.getContacts().add(c1);
		author.getContacts().add(c2);

		author = authorRepo.save(author);
		author_id = author.getId();

		Assert.assertNotNull(author_id);
		Assert.assertEquals(1, author.getBooks().size());
		Assert.assertEquals(2, author.getContacts().size());

	}

	@Test
	@Transactional
	@Rollback(false)
	public void test2() {

		Author author = authorRepo.findOne(author_id);
		Assert.assertEquals(1, author.getBooks().size());
		Assert.assertEquals(2, author.getContacts().size());

	}

	@Test
	@Transactional
	@Rollback(false)
	public void test3() {
		Author author = authorRepo.findById(author_id);
		Assert.assertEquals(1, author.getBooks().size());
		Assert.assertEquals(2, author.getContacts().size());
	}

	@Test
	@Transactional
	@Rollback(false)
	public void test4() {

		authorRepo.delete(author_id);
		Author author = authorRepo.findById(author_id);
		Assert.assertNull(author);
	}

}
