package bmt;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@RunWith(PowerMockRunner.class)
@PrepareForTest(LLM.class)
public class LLMTest {

    @Test
    public void testChatGPT() throws Exception {
        // Prepare data to return
        String fakeResponse = "{\"choices\": [{\"text\": \"I am fine, thank you!\"}]}";
        InputStream fakeInput = new ByteArrayInputStream(fakeResponse.getBytes());
        
        // Mock URL and HttpURLConnection
        URL url = PowerMockito.mock(URL.class);
        HttpURLConnection connection = mock(HttpURLConnection.class);
        PowerMockito.whenNew(URL.class).withAnyArguments().thenReturn(url);
        when(url.openConnection()).thenReturn(connection);
        when(connection.getInputStream()).thenReturn(fakeInput);

        // Setup OutputStream to capture the sent data
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        when(connection.getOutputStream()).thenReturn(outputStream);

        // Execute
        LLM.chatGPT("Hello, how are you?");

        // Verify output
        System.out.println(outputStream.toString());  // Check what data was sent
        verify(connection).getOutputStream();  // Verify that getOutputStream was called
        verify(connection).getInputStream();   // Verify that getInputStream was called
    }
}
