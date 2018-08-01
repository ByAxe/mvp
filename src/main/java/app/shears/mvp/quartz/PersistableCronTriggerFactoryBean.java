package app.shears.mvp.quartz;

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

import java.text.ParseException;

public class PersistableCronTriggerFactoryBean extends CronTriggerFactoryBean {

    @Override
    public void afterPropertiesSet() throws ParseException {
        super.afterPropertiesSet();
    }
}
