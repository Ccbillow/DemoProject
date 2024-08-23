package org.example.controller;

import com.alibaba.fastjson.JSON;
import org.example.common.enums.ExceptionEnum;
import org.example.exception.BusinessException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testRegister_Success() throws Exception{
        String username = "Test" + System.currentTimeMillis();
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"username\": \"" + username + "\",\n" +
                                "\t\"password\": \"Chengt@223635\",\n" +
                                "\t\"email\": \"ctaoaoo@icloud.com\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testRegister_Fail_Password_Illegal() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"username\": \"Mary\",\n" +
                                "\t\"password\": \"123\",\n" +
                                "\t\"email\": \"ctaoaoo@icloud.com\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andReturn();
        BusinessException e = JSON.parseObject(result.getResponse().getContentAsString(), BusinessException.class);
        Assert.assertEquals(e.getErrorCode(), ExceptionEnum.PARAM_ILLEGAL.getErrorCode());
        Assert.assertEquals(e.getErrorMsg(), "The password must contain uppercase, lowercase, numbers and special characters and be at least 6 characters long");
    }

    @Test
    public void testRegister_Fail_Email_Illegal() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"username\": \"Mary\",\n" +
                                "\t\"password\": \"Test@123\",\n" +
                                "\t\"email\": \"312123\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andReturn();
        BusinessException e = JSON.parseObject(result.getResponse().getContentAsString(), BusinessException.class);
        Assert.assertEquals(e.getErrorCode(), ExceptionEnum.PARAM_ILLEGAL.getErrorCode());
        Assert.assertEquals(e.getErrorMsg(), "Email format error");
    }

    @Test
    public void testRegister_Fail_User_Null() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"password\": \"Chengt@223635\",\n" +
                                "\t\"email\": \"ctaoaoo@icloud.com\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andReturn();
        BusinessException e = JSON.parseObject(result.getResponse().getContentAsString(), BusinessException.class);
        Assert.assertEquals(e.getErrorCode(), ExceptionEnum.PARAM_ILLEGAL.getErrorCode());
        Assert.assertEquals(e.getErrorMsg(), "username can not be null");
    }
}
