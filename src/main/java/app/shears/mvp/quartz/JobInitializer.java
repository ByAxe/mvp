package app.shears.mvp.quartz;

import app.shears.mvp.cores.enums.Frequency;
import app.shears.mvp.models.Order;
import app.shears.mvp.quartz.jobs.PlaceOrderJob;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.impl.matchers.GroupMatcher.jobGroupEquals;

@Service
public class JobInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private static final String JOB_PREFIX = "_JOB";
    private static final String TRIGGER_PREFIX = "_TRIGGER";
    private static final String GROUP_NAME = "ORDERS";

    @Autowired
    private ICronService cronService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (schedulerFactoryBean.isRunning()) return;
        schedulerFactoryBean.start();
    }

    public void removeJobGroup(long orderId) {
        try {
            Set<JobKey> jobKeys = schedulerFactoryBean.getScheduler().getJobKeys(jobGroupEquals(GROUP_NAME));

            for (JobKey jobKey : jobKeys) {
                if (jobKey.getName().equals(orderId + JOB_PREFIX)) {
                    schedulerFactoryBean.getScheduler().deleteJob(jobKey);
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void placeOrderJob(Order order) {
        long orderId = order.getId();
        String cronExpression = cronService.buildCronByDate(order.getDateTime(), Frequency.MONTHLY);

        // remove job
        removeJobGroup(orderId);
        Set<Trigger> triggerSet = new HashSet<>();
        Trigger trigger;

        TriggerBuilder triggerBuilder = newTrigger()
                .withIdentity(orderId + TRIGGER_PREFIX, GROUP_NAME)
                .startAt(new Date());

        // TODO Нужно придумать, как удалять триггер, иначе будет создавать заказ снова и снова!

        trigger = triggerBuilder
                .withSchedule(cronSchedule(cronExpression)
                        .withMisfireHandlingInstructionDoNothing())
                .build();

        triggerSet.add(trigger);

        final Map<String, String> jobData = new HashMap<>();

        final JobDetail job = newJob(PlaceOrderJob.class)
                .withIdentity(orderId + JOB_PREFIX, GROUP_NAME)
                .setJobData(new JobDataMap(jobData))
                .build();

        try {
            schedulerFactoryBean.getScheduler().scheduleJob(job, triggerSet, true);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
