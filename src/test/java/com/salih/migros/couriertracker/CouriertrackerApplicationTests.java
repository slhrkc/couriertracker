package com.salih.migros.couriertracker;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CouriertrackerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testCourierLocationSave() throws Exception {
		mockMvc.perform(post("/courierLocation/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"courier\":{\n" +
								"        \"courierId\":1\n" +
								"    },\n" +
								"    \"lat\":  40.9923307,\n" +
								"    \"lon\":  29.1244229,\n" +
								"    \"transactionTime\":\"2021-09-27T18:54:08.125\"\n" +
								"}"))
				.andExpect(status().isOk());

		mockMvc.perform(post("/courierLocation/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"courier\":{\n" +
								"        \"courierId\":1\n" +
								"    },\n" +
								"    \"lat\":  400.9923307,\n" +
								"    \"lon\":  29.1244229,\n" +
								"    \"transactionTime\":\"2021-09-27T18:54:08.125\"\n" +
								"}"))
				.andExpect(status().isBadRequest());

		mockMvc.perform(post("/courierLocation/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"courier\":{\n" +
								"    },\n" +
								"    \"lat\":  40.9923307,\n" +
								"    \"lon\":  29.1244229,\n" +
								"    \"transactionTime\":\"2021-09-27T18:54:08.125\"\n" +
								"}"))
				.andExpect(status().isBadRequest());

		mockMvc.perform(post("/courierLocation/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"courier\":{\n" +
								"    },\n" +
								"    \"lat\":  40.9923307,\n" +
								"    \"lon\":  29.1244229,\n" +
								"    \"transactionTime\":\"2021-09-27T18:54:08.125\"\n" +
								"}"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testGetTotalDistance() throws Exception {
		mockMvc.perform(post("/courierLocation/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"courier\":{\n" +
								"        \"courierId\":1\n" +
								"    },\n" +
								"    \"lat\":  40.9923307,\n" +
								"    \"lon\":  29.1244229,\n" +
								"    \"transactionTime\":\"2021-09-27T18:54:08.125\"\n" +
								"}"))
				.andExpect(status().isOk());
		mockMvc.perform(post("/courierLocation/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"courier\":{\n" +
								"        \"courierId\":1\n" +
								"    },\n" +
								"    \"lat\":  40.9923307,\n" +
								"    \"lon\":  29.2244229,\n" +
								"    \"transactionTime\":\"2021-09-27T18:55:08.125\"\n" +
								"}"))
				.andExpect(status().isOk());
		mockMvc.perform(post("/courierLocation/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"courier\":{\n" +
								"        \"courierId\":1\n" +
								"    },\n" +
								"    \"lat\":  40.9923307,\n" +
								"    \"lon\":  29.2254229,\n" +
								"    \"transactionTime\":\"2021-09-27T18:55:08.125\"\n" +
								"}"))
				.andExpect(status().isOk());

		mockMvc.perform(get("/courierLocation/travelledDistance?courierId=1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.totalDistance").value("8476.893222613184"));
	}

	@Test
	public void testStoreEntrances() throws Exception {
		mockMvc.perform(post("/courierLocation/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"courier\":{\n" +
								"        \"courierId\":1\n" +
								"    },\n" +
								"    \"lat\":  41.0066851,\n" +
								"    \"lon\":  28.6552262,\n" +
								"    \"transactionTime\":\"2021-09-27T18:54:08.125\"\n" +
								"}"))
				.andExpect(status().isOk());
		mockMvc.perform(post("/courierLocation/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"courier\":{\n" +
								"        \"courierId\":1\n" +
								"    },\n" +
								"    \"lat\":  41.0066851,\n" +
								"    \"lon\":  28.6552262,\n" +
								"    \"transactionTime\":\"2021-09-27T18:56:08.125\"\n" +
								"}"))
				.andExpect(status().isOk());
		mockMvc.perform(post("/courierLocation/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"courier\":{\n" +
								"        \"courierId\":1\n" +
								"    },\n" +
								"    \"lat\":  41.0066851,\n" +
								"    \"lon\":  28.6552262,\n" +
								"    \"transactionTime\":\"2021-09-27T18:56:48.125\"\n" +
								"}"))
				.andExpect(status().isOk());

		mockMvc.perform(get("/courierLocation/storeEntrances?courierId=1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value("2"));
	}

	@Test
	void contextLoads() {
	}

}
