package mseries.jacksontest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Optional;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public class OptionalTest {

	ObjectMapper mapper = new ObjectMapper().registerModule(new Jdk8Module());

	@Test
	public void givenPresentOptional_whenSerializing_thenValueInJson() throws JsonProcessingException {

		String subTitle = "The Parish Boy's Progress";
		Book book = new Book();
		book.setTitle("Oliver Twist");
		book.setSubTitle(Optional.of(subTitle));

		String result = mapper.writeValueAsString(book);
		System.out.println(result);

	}

	@Test
	public void givenEmptyOptional_whenSerializing_thenNullValue() throws JsonProcessingException {

		Book book = new Book();
		book.setTitle("Oliver Twist");
		book.setSubTitle(Optional.empty());

		String result = mapper.writeValueAsString(book);
		System.out.println(result);
	}

	@Test
	public void givenField_whenDeserializingIntoOptional_thenIsPresentWithValue() throws IOException {

		String subTitle = "The Parish Boy's Progress";
		String book = "{ \"title\": \"Oliver Twist\", \"subTitle\": \"" + subTitle
				+ "\" , \"clientId\":\"ef846512-b14c-4122-85f1-9981bbd995ea\","
				+ "\"clientIdUUID\":\"ef846512-b14c-4122-85f1-9981bbd995ea\"}";

		Book result = mapper.readValue(book, Book.class);
		assertNotNull(result.getClientId());
		assertEquals("ef846512-b14c-4122-85f1-9981bbd995ea", result.getClientId().get().toString());
		assertNotNull(result.getClientIdUUID());
		
	}
	@Test
	public void givenField_whenDeserializingIntoOptional_noUUID() throws IOException {

		String subTitle = "The Parish Boy's Progress";
		String book = "{ \"title\": \"Oliver Twist\", \"subTitle\": \"" + subTitle
				+ "\" , \"clientId\":\"ef846512-b14c-4122-85f1-9981bbd995ea\"}";

		Book result = mapper.readValue(book, Book.class);
		assertNotNull(result.getClientId());
		assertEquals("ef846512-b14c-4122-85f1-9981bbd995ea", result.getClientId().get().toString());
		assertNull(result.getClientIdUUID());
		
	}
	
	/*
	 * SubTitle is passed as null and the value in the deserialized object is set to not null, it's
	 * a Optional object but it's value is not present
	 * 
	 * clientId is not specified and this is set to null in the object despite it being an Optional
	 * 
	 * clientIdUUID is not an Optional and get set to null when not specified
	 */
	@Test
	public void givenNullField_whenDeserializingIntoOptional_thenIsEmpty() throws IOException {

		String book = "{ \"title\": \"Oliver Twist\", \"subTitle\": null }";

		Book result = mapper.readValue(book, Book.class);
		assertNull(result.getClientId());

		assertNotNull(result.getSubTitle());
		assertFalse(result.getSubTitle().isPresent());

		assertNull(result.getClientIdUUID());

	}
}
