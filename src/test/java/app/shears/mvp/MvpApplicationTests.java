package app.shears.mvp;

import app.shears.mvp.cores.enums.Gender;
import app.shears.mvp.models.Customer;
import app.shears.mvp.models.Master;
import app.shears.mvp.models.Order;
import app.shears.mvp.models.Service;
import app.shears.mvp.services.api.IServiceService;
import com.fasterxml.jackson.core.type.TypeReference;
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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Set;

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

    private final String login = "login3";
    private final String password = "password3";

    private final Customer testCustomer = new Customer(login, password, "New Username", "+375-25-345245234", "email", Gender.UNDEFINED, 99, null);
    private final Master testMaster = new Master(null, null, login, password, "New Username", "+375-25-345245234", "email", Gender.UNDEFINED, 99, null, false, false, new BigDecimal(4.5));


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
        String masterJson = objectMapper.writeValueAsString(testMaster);

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

    private void createCustomer(Customer customer) throws Exception {
        String customerJson = objectMapper.writeValueAsString(testCustomer);

        mvc.perform(post("/customer")
                .contentType(contentType)
                .content(customerJson))
                .andExpect(status().isCreated());
    }

    private Customer findCustomer(String login, String password) throws Exception {
        String contentAsString = mvc.perform(get("/customer/find")
                .contentType(contentType)
                .param("login", login)
                .param("password", password))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Customer customer = objectMapper.readValue(contentAsString, Customer.class);
        return customer;
    }

    private void deleteCustomer(Long id) throws Exception {
        mvc.perform(delete("/customer/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    public void createThenFindAndDeleteCustomer() throws Exception {
        createCustomer(testCustomer);
        Customer customer = findCustomer(login, password);
        deleteCustomer(customer.getId());
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

    @Test
    public void createCustomerThenPlaceAnOrder() throws Exception {

        // Create customer
        createCustomer(testCustomer);
        Customer customer = findCustomer(login, password);

        // Place an order
        Set<Service> services = findServices();

        LocalDateTime orderDateTime = LocalDateTime.now().plus(15, ChronoUnit.SECONDS);

        Order order = new Order(customer, services, "42.234134, 55.223447", orderDateTime);
        String orderJson = objectMapper.writeValueAsString(order);

        System.out.println(orderJson);

        mvc.perform(post("/order/place")
                .contentType(contentType)
                .content(orderJson))
                .andExpect(status().isCreated());

        // Remove Customer
        deleteCustomer(customer.getId());
    }

    //    @Test
    public Set<Service> findServices() throws Exception {
        String contentAsString = mvc.perform(get("/service")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Set<Service> services = objectMapper.readValue(contentAsString, new TypeReference<Set<Service>>() {
        });
        return services;
    }
}
