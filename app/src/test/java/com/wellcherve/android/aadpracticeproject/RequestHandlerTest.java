package com.wellcherve.android.aadpracticeproject;

import android.net.Uri;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
public class RequestHandlerTest {

    @Mock
    private Uri uri ;
    @Mock
    private Uri learners;

    @PrepareForTest({Uri.class})
    @Test
    public void buildURLTest() throws MalformedURLException {
        uri = Uri.parse(RequestHandler.BASE_URL);

//        Mockito.doCallRealMethod().when(uri).withAppendedPath(uri, RequestHandler.LEARNING_LEADERS_PATH);
        URL url = new URL("https://gadsapi.herokuapp.com/api/hour");
        URL url_skill_iq = new URL("https://gadsapi.herokuapp.com/api/hour");
        assertEquals(RequestHandler.buildingURL(RequestHandler.LEARNING_LEADERS_PATH), url);
        assertEquals(RequestHandler.buildingURL(RequestHandler.SKILL_IQ_LEADERS_PATH), url_skill_iq);
    }

//    public void makeRequestTest() {
//        Path learning_leader_path = Paths.get("$HOME", "Desktop", "Assets", "learning_leaders.json");
//        try {
//            assertEquals(RequestHandler.makeRequest(RequestHandler.LEARNING_LEADERS_PATH),
//                    new JSONObject(learning_leader_path.toAbsolutePath().toString()));
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }
//    }
}