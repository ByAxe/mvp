package app.shears.mvp;

import app.shears.mvp.cores.enums.Gender;
import app.shears.mvp.models.Master;
import app.shears.mvp.services.api.IServiceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Arrays;

import static junit.framework.TestCase.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MvpApplication.class)
@AutoConfigureMockMvc
public class MvpApplicationTests {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private MockMvc mvc;

    @Autowired
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private IServiceService serviceService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.stream(converters)
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void findMaster() throws Exception {
        String id = "1";

        mvc.perform(get("/master/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }

    @Test
    public void createMaster() throws Exception {
        String masterJson = objectMapper.writeValueAsString(new Master(null, null, "login3", "password3", "New Username", "+375-25-345245234", "email", Gender.UNDEFINED, 99, null, false, false, new BigDecimal(4.5)));

        mvc.perform(post("/master")
                .contentType(contentType)
                .content(masterJson))
                .andExpect(status().isCreated());
    }

}
