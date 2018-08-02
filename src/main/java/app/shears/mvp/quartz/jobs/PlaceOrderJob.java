package app.shears.mvp.quartz.jobs;

import app.shears.mvp.web.firebase.FcmClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class PlaceOrderJob implements Job {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Environment environment;

    @Autowired
    private FcmClient fcmClient;

    @Autowired
    private WebClient webClient;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap map = context.getMergedJobDataMap();

        Map<String, String> messageMap = new HashMap<String, String>(1) {{
            put("message", "Hello World!");
        }};


        // Send notification to client
        try {
            fcmClient.send(messageMap);
        } catch (InterruptedException | ExecutionException e) {
            throw new JobExecutionException(e);
        }
    }
}
