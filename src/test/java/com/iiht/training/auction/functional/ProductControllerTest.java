package com.iiht.training.auction.functional;

import static com.iiht.training.auction.testutils.TestUtils.businessTestFile;
import static com.iiht.training.auction.testutils.TestUtils.currentTest;
import static com.iiht.training.auction.testutils.TestUtils.testReport;
import static com.iiht.training.auction.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.training.auction.controller.CustomerController;
import com.iiht.training.auction.controller.ProductController;
import com.iiht.training.auction.controller.SellerController;
import com.iiht.training.auction.dto.CustomerDto;
import com.iiht.training.auction.dto.ProductDto;
import com.iiht.training.auction.dto.SellerDto;
import com.iiht.training.auction.service.CustomerService;
import com.iiht.training.auction.service.ProductService;
import com.iiht.training.auction.service.SellerService;
import com.iiht.training.auction.testutils.MasterData;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testSaveProduct() throws Exception {
		ProductDto productDto = MasterData.getProductDto();
		ProductDto savedProductDto = MasterData.getProductDto();

		savedProductDto.setProductId(1L);
		savedProductDto.setLastDateOfBidding(LocalDate.now().plusYears(10));

		when(this.productService.saveProduct(productDto)).thenReturn(savedProductDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products/register")
				.content(MasterData.asJsonString(productDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedProductDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testRegisterProductIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		ProductDto productDto = MasterData.getProductDto();
		ProductDto savedProductDto = MasterData.getProductDto();
		savedProductDto.setProductId(1L);
		savedProductDto.setLastDateOfBidding(LocalDate.now().plusYears(10));

		when(this.productService.saveProduct(productDto)).then(new Answer<ProductDto>() {

			@Override
			public ProductDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedProductDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products/register")
				.content(MasterData.asJsonString(productDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetAllProducts() throws Exception {
		List<ProductDto> productDtos = MasterData.getProductDtoList();

		when(this.productService.getAllProducts()).thenReturn(productDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/get/all")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(productDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllProductsIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<ProductDto> productDtos = MasterData.getProductDtoList();
		when(this.productService.getAllProducts()).then(new Answer<List<ProductDto>>() {

			@Override
			public List<ProductDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return productDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/get/all")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetProductById() throws Exception {
		ProductDto productDto = MasterData.getProductDto();
		when(this.productService.getProductById(1L)).thenReturn(productDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/get/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(productDto)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetProductsByIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		ProductDto productDto = MasterData.getProductDto();
		productDto.setProductId(1L);
		when(this.productService.getProductById(1L)).then(new Answer<ProductDto>() {

			@Override
			public ProductDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return productDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/get/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testUpdateProduct() throws Exception {
		ProductDto productDto = MasterData.getProductDto();
		ProductDto savedProductDto = MasterData.getProductDto();

		savedProductDto.setProductId(1L);
		savedProductDto.setLastDateOfBidding(LocalDate.now().plusYears(10));

		when(this.productService.updateProduct(productDto)).thenReturn(savedProductDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/products/update")
				.content(MasterData.asJsonString(productDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedProductDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testUpdateProductIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		ProductDto productDto = MasterData.getProductDto();
		ProductDto savedProductDto = MasterData.getProductDto();
		savedProductDto.setProductId(1L);
		savedProductDto.setLastDateOfBidding(LocalDate.now().plusYears(10));

		when(this.productService.updateProduct(productDto)).then(new Answer<ProductDto>() {

			@Override
			public ProductDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedProductDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/products/update")
				.content(MasterData.asJsonString(productDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}


	@Test
	public void testDeleteProduct() throws Exception {
		ProductDto productDto = MasterData.getProductDto();
		productDto.setProductId(1L);
		when(this.productService.deleteProduct(1L)).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/products/delete/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(true)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testDeleteProductIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		ProductDto productDto = MasterData.getProductDto();
		productDto.setProductId(1L);
		when(this.productService.deleteProduct(1L)).then(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return true;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/products/delete/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}
	
	@Test
	public void testGetAllProductsBySeller() throws Exception {
		List<ProductDto> productDtos = MasterData.getProductDtoList();

		when(this.productService.getProductsBySeller(1L)).thenReturn(productDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/get/by-seller/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(productDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllProductsBySellerIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<ProductDto> productDtos = MasterData.getProductDtoList();
		when(this.productService.getProductsBySeller(1L)).then(new Answer<List<ProductDto>>() {

			@Override
			public List<ProductDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return productDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/get/by-seller/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}
	
	@Test
	public void testGetAllProductsByCategory() throws Exception {
		List<ProductDto> productDtos = MasterData.getProductDtoList();

		when(this.productService.getProductsByCategory("Mobile")).thenReturn(productDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/get/by-category/Mobile")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(productDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllProductsByCategoryIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<ProductDto> productDtos = MasterData.getProductDtoList();
		when(this.productService.getProductsByCategory("Mobile")).then(new Answer<List<ProductDto>>() {

			@Override
			public List<ProductDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return productDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/get/by-category/Mobile")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

}
