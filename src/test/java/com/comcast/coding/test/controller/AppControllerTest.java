package com.comcast.coding.test.controller;

import static org.junit.Assert.*;

import java.lang.annotation.Target;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.core.Is;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import com.comcast.coding.test.common.Post;
import com.comcast.coding.test.persistence.entity.MovieDetailsEntity;
import com.comcast.coding.test.service.MovieDetailsService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AppController.class)
public class AppControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private MovieDetailsService movieDetailsService;
	
	@Mock
	private MovieDetailsEntity movieDetailsEntity;
	
	@Mock
	private RestTemplate restTemplate;
	
	@Test
	public void testPing() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/service/ping"))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void testPrintFibonacciNumbers() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/service/printFibonacciNumbers/5"))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void testDealLockScenario() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/service/printFibonacciNumbers/5"))
		.andExpect(MockMvcResultMatchers.status().is(200));	}

	@Test
	public void testInsertMovieDetails() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.post("/service/insertMovieDetails")
				.contentType(MediaType.APPLICATION_JSON)
	            .content("{\n" + 
	            		"	\"movieName\": \"nu\",\n" + 
	            		"	\"movieRating\": \"3\"\n" + 
	            		"}"))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}

	@Test
	public void testViewAllMovieDetails() throws Exception {	
		Mockito.when(movieDetailsService.showAllMovieDetails()).thenReturn(prepareMockDataList());
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/service/viewAllMovieDetails")).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testViewMovieDetailsByMovieId() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/service/viewMovieDetailsByMovieId/1")).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
//		assertEquals("http://localhost:8080/service/viewMovieDetailsByMovieId/1",
//		response.getHeader(HttpHeaders.LOCATION));
	}

	@Test
	public void testViewMovieDetailsByMovieName() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/service/viewMovieDetailsByMovieName/Inception")).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.FOUND.value(), response.getStatus());
	}

	@Test
	public void testDeleteSingleMovieDetails() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/service/deleteSingleMovieDetails/Inception")).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testDeleteAllMovieDetails() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/service/deleteAllMovieDetails")).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());	
	}

	@Test
	public void testConsumeExternalRestAPI() throws Exception {
		String responseMessage = "{\"userId\":1,\"id\":1,\"title\":\"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\"body\":\"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"}";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/service/consumeExternalRestAPI")).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(responseMessage, result.getResponse().getContentAsString());
	}
	
	
	/**
	 *  Mock List of MovieDetailsEntity
	 * @return
	 */
	private List<MovieDetailsEntity> prepareMockDataList() {
		List<MovieDetailsEntity> list = new ArrayList<MovieDetailsEntity>();;
	    MovieDetailsEntity movieDetailsEntity = new MovieDetailsEntity();
	    MovieDetailsEntity movieDetailsEntity1 = new MovieDetailsEntity();
		
	    movieDetailsEntity.setId(1);
		movieDetailsEntity.setMovieName("Inception");
		movieDetailsEntity.setMovieRating(4.5F);
		list.add(movieDetailsEntity);
		
		movieDetailsEntity1.setId(1);
		movieDetailsEntity1.setMovieName("Troy");
		movieDetailsEntity1.setMovieRating(4.2F);
		list.add(movieDetailsEntity1);

		return list;
	}

}
