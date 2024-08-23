package org.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.example.common.enums.ExceptionEnum;
import org.example.exception.BusinessException;
import org.example.model.response.CommonResponse;
import org.example.model.view.UserView;
import org.example.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testRegister_Success() throws Exception{
        String username = "Test" + System.currentTimeMillis();

        Mockito.doNothing().when(userService).add(any());

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"username\": \"" + username + "\",\n" +
                                "\t\"password\": \"Abc@123\",\n" +
                                "\t\"email\": \"Test@gmail.com\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testRegister_Fail_Password_Illegal() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"username\": \"Mary\",\n" +
                                "\t\"password\": \"123\",\n" +
                                "\t\"email\": \"Test@gmail.com\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andReturn();
        BusinessException e = JSON.parseObject(result.getResponse().getContentAsString(), BusinessException.class);
        Assert.assertEquals(e.getErrorCode(), ExceptionEnum.PARAM_ILLEGAL.getErrorCode());
        Assert.assertEquals(e.getErrorMsg(), "The password must contain uppercase, lowercase, numbers and special characters and be at least 6 characters long");
    }

    @Test
    public void testRegister_Fail_Email_Illegal() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
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
    public void testRegister_Fail_Username_Null() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"password\": \"Abc@123456\",\n" +
                                "\t\"email\": \"Test@gmail.com\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andReturn();
        BusinessException e = JSON.parseObject(result.getResponse().getContentAsString(), BusinessException.class);
        Assert.assertEquals(e.getErrorCode(), ExceptionEnum.PARAM_ILLEGAL.getErrorCode());
        Assert.assertEquals(e.getErrorMsg(), "username can not be null");
    }

    @Test
    public void testUpdate_Success() throws Exception{
        String username = "Test" + System.currentTimeMillis();

        Mockito.doNothing().when(userService).update(any(), any());

        mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"username\": \"" + username + "\",\n" +
                                "\t\"password\": \"Abc@123\",\n" +
                                "\t\"email\": \"Test@gmail.com\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testUpdate_Fail_Id_Illegal() throws Exception{
        String username = "Test" + System.currentTimeMillis();

        Mockito.doNothing().when(userService).update(any(), any());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/user/abc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"username\": \"" + username + "\",\n" +
                                "\t\"password\": \"Abc@123\",\n" +
                                "\t\"email\": \"Test@gmail.com\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andReturn();
        BusinessException e = JSON.parseObject(result.getResponse().getContentAsString(), BusinessException.class);
        Assert.assertEquals(e.getErrorCode(), ExceptionEnum.PARAM_ILLEGAL.getErrorCode());
    }

    @Test
    public void testUpdate_Fail_Password_Illegal() throws Exception{
        String username = "Test" + System.currentTimeMillis();

        Mockito.doNothing().when(userService).update(any(), any());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"username\": \"" + username + "\",\n" +
                                "\t\"password\": \"123\",\n" +
                                "\t\"email\": \"Test@gmail.com\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andReturn();
        BusinessException e = JSON.parseObject(result.getResponse().getContentAsString(), BusinessException.class);
        Assert.assertEquals(e.getErrorCode(), ExceptionEnum.PARAM_ILLEGAL.getErrorCode());
        Assert.assertEquals(e.getErrorMsg(), "The password must contain uppercase, lowercase, numbers and special characters and be at least 6 characters long");
    }

    @Test
    public void testUpdate_Fail_Email_Illegal() throws Exception{
        String username = "Test" + System.currentTimeMillis();

        Mockito.doNothing().when(userService).update(any(), any());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"username\": \"" + username + "\",\n" +
                                "\t\"password\": \"Abc@123\",\n" +
                                "\t\"email\": \"Test\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andReturn();
        BusinessException e = JSON.parseObject(result.getResponse().getContentAsString(), BusinessException.class);
        Assert.assertEquals(e.getErrorCode(), ExceptionEnum.PARAM_ILLEGAL.getErrorCode());
        Assert.assertEquals(e.getErrorMsg(), "Email format error");
    }

    @Test
    public void testDelete_Success_IdList() throws Exception{
        Mockito.doNothing().when(userService).delete(any());

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1,2,3"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testDelete_Success_Id() throws Exception{
        Mockito.doNothing().when(userService).delete(any());

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testDelete_Param_Illegal() throws Exception{
        Mockito.doNothing().when(userService).delete(any());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/users/abc"))
                .andExpect(status().isOk())
                .andReturn();
        BusinessException e = JSON.parseObject(result.getResponse().getContentAsString(), BusinessException.class);
        Assert.assertEquals(e.getErrorCode(), ExceptionEnum.PARAM_ILLEGAL.getErrorCode());
    }

    @Test
    public void testQueryById_Success() throws Exception{
        UserView user = new UserView();
        user.setUserId(1L);
        user.setUsername("Test");
        user.setPassword("Test@123");
        user.setEmail("test@gmail.com");
        Mockito.doReturn(user).when(userService).queryByUserId(any());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
                .andExpect(status().isOk())
                .andReturn();
        CommonResponse<UserView> commonResponse = JSON.parseObject(result.getResponse().getContentAsString(),
                new TypeReference<CommonResponse<UserView>>(){}.getType());

        Assert.assertTrue(1L == commonResponse.getData().getUserId());
        Assert.assertEquals("Test", commonResponse.getData().getUsername());
    }

    @Test
    public void testQueryById_Fail_Param_Illegal() throws Exception{
        Mockito.doNothing().when(userService).delete(any());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/abc"))
                .andExpect(status().isOk())
                .andReturn();
        BusinessException e = JSON.parseObject(result.getResponse().getContentAsString(), BusinessException.class);
        Assert.assertEquals(e.getErrorCode(), ExceptionEnum.PARAM_ILLEGAL.getErrorCode());
    }

    @Test
    public void testQuery_Success() throws Exception{
        List<UserView> userViewList = new ArrayList<>();
        UserView user = new UserView();
        user.setUserId(1L);
        user.setUsername("Test");
        user.setPassword("Test@123");
        user.setEmail("test@gmail.com");
        userViewList.add(user);

        UserView user1 = new UserView();
        user1.setUserId(2L);
        user1.setUsername("Test2");
        user1.setPassword("Test@222");
        user1.setEmail("test222@gmail.com");
        userViewList.add(user1);
        Mockito.doReturn(userViewList).when(userService).userList(any());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"username\": \"T\",\n" +
                                "    \"userIdList\": [1, 2]\n" +
                                "}"))
                .andExpect(status().isOk())
                .andReturn();
        CommonResponse<List<UserView>> commonResponse = JSON.parseObject(result.getResponse().getContentAsString(),
                new TypeReference<CommonResponse<List<UserView>>>(){}.getType());

        Assert.assertNotNull(commonResponse.getData());
        Assert.assertTrue(2 == commonResponse.getData().size());
        Assert.assertEquals("Test", commonResponse.getData().get(0).getUsername());
    }
}
