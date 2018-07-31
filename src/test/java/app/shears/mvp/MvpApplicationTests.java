package app.shears.mvp;

import app.shears.mvp.cores.enums.Gender;
import app.shears.mvp.models.Customer;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

        String master = mvc.perform(get("/master/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(master);

    }

    @Test
    public void createThenFindAndDeleteMaster() throws Exception {
        String login = "login3";
        String password = "password3";
        String masterJson = objectMapper.writeValueAsString(new Master(null, null, login, password, "New Username", "+375-25-345245234", "email", Gender.UNDEFINED, 99, null, false, false, new BigDecimal(4.5)));

        mvc.perform(post("/master")
                .contentType(contentType)
                .content(masterJson))
                .andExpect(status().isCreated());


        String masterAsString = mvc.perform(get("/master/find")
                .contentType(contentType)
                .param("login", login)
                .param("password", password))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Master master = objectMapper.readValue(masterAsString, Master.class);

        mvc.perform(delete("/master/" + master.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void existsByLoginAndPasswordMaster() throws Exception {
        String login = "login3";
        String password = "password3";

        String master = mvc.perform(get("/master")
                .contentType(contentType)
                .param("login", login)
                .param("password", password))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(master);
    }

    @Test
    public void existsByLoginMaster() throws Exception {
        String login = "login3";

        String master = mvc.perform(get("/master")
                .contentType(contentType)
                .param("login", login))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(master);
    }


    @Test
    public void findCustomer() throws Exception {
        String id = "1";

        String customer = mvc.perform(get("/customer/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(customer);

    }

    @Test
    public void createThenFindAndDeleteCustomer() throws Exception {
        String login = "login3";
        String password = "password3";
        String customerJson = objectMapper.writeValueAsString(new Customer(login, password, "New Username", "+375-25-345245234", "email", Gender.UNDEFINED, 99, null));

        mvc.perform(post("/customer")
                .contentType(contentType)
                .content(customerJson))
                .andExpect(status().isCreated());


        String contentAsString = mvc.perform(get("/customer/find")
                .contentType(contentType)
                .param("login", login)
                .param("password", password))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Customer customer = objectMapper.readValue(contentAsString, Customer.class);

        mvc.perform(delete("/customer/" + customer.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void existsByLoginAndPasswordCustomer() throws Exception {
        String login = "login3";
        String password = "password3";

        String customer = mvc.perform(get("/customer")
                .contentType(contentType)
                .param("login", login)
                .param("password", password))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(customer);
    }

    @Test
    public void existsByLoginCustomer() throws Exception {
        String login = "login3";

        String customer = mvc.perform(get("/customer")
                .contentType(contentType)
                .param("login", login))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(customer);
    }


}
