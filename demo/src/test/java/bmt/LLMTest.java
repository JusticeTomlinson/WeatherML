package bmt;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(LLM.class)
public class LLMTest {

    @Test
    public void testChatGPT() throws Exception {

        // Prepare data to return
        String fakeResponse = "{\"choices\": [{\"text\": \"I am fine, thank you!\"}]}";
        InputStream fakeInput = new ByteArrayInputStream(fakeResponse.getBytes());
        
        URL url = PowerMockito.mock(URL.class);
        HttpURLConnection connection = mock(HttpURLConnection.class);
        PowerMockito.whenNew(URL.class).withAnyArguments().thenReturn(url);
        when(url.openConnection()).thenReturn(connection);
        when(connection.getInputStream()).thenReturn(fakeInput);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        when(connection.getOutputStream()).thenReturn(outputStream);

        LLM.chatGPT("Hello, how are you?");

        // Verify output
        System.out.println(outputStream.toString());
        verify(connection).getOutputStream();  
        verify(connection).getInputStream(); 
    }
}
