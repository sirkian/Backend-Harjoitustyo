package backend.harjoitustyo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class RestTests {
    
    @Autowired
    private WebApplicationContext webAppContext;
    
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }
    
    @Test
    public void checkStatus() throws Exception {
       this.mockMvc.perform(get("/rest/images")).andExpect(status().isOk());
    }
    
    @Test 
    public void checkResponseTypeJSON() throws Exception {
        this.mockMvc.perform(get("/rest/images")).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    
    @Test
    public void apiStatusIsOk() throws Exception {
        this.mockMvc.perform(get("/api/images")).andExpect(status().isOk());
    }

}
