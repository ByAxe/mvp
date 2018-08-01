package app.shears.mvp.quartz.jobs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class PlaceOrderJob implements Job {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Environment environment;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap map = context.getMergedJobDataMap();
        System.out.format("Map: [%s]\n", map.getWrappedMap());
    }
}
