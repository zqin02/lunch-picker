package com.example.lunch.controller;

import com.example.lunch.bean.CreateSessionRequest;
import com.example.lunch.service.RestaurantService;
import com.example.lunch.service.SessionInfoService;
import com.example.lunch.service.SessionService;
import com.example.lunch.service.WebSocketService;
import com.example.lunch.utils.JsonConvertor;
import com.example.lunch.utils.SessionDirector;
import com.example.lunch.utils.UUIDGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
class SessionControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    SessionService sessionService;
    @MockBean
    RestaurantService restaurantService;
    @MockBean
    WebSocketService webSocketService;
    @MockBean
    SessionInfoService sessionInfoService;


    @Test
    void createSession() throws Exception {
        String alias = "alias-unittest";
        Mockito.when(sessionService.createSession(alias)).thenReturn(SessionDirector.prepareSession(alias));
        mvc.perform( MockMvcRequestBuilders
                        .post("/session/create")
                        .content(JsonConvertor.asJsonString(new CreateSessionRequest(alias)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.uuid").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.user").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userAlias").value(alias));
    }


    @Test
    void getSessionsByUUID() throws Exception {
        String alias = "alias-unittest";
        String uuid = UUIDGenerator.generateUUID();
        Mockito.when(sessionInfoService.getSession(uuid,alias)).thenReturn(SessionDirector.prepareSessionInfo(alias));
        mvc.perform( MockMvcRequestBuilders
                        .post("/session/connect/"+uuid)
                        .content(JsonConvertor.asJsonString(new CreateSessionRequest(alias)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.uuid").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.user").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userAlias").value(alias));
    }

}