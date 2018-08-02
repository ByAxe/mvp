package app.shears.mvp;

import app.shears.mvp.quartz.AutowiringSpingBeanJobFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"app.shears.mvp.repositories"}
)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableScheduling
public class MvpApplication {
    public static final Logger logger = LoggerFactory.getLogger("app.shears.mvp");

    public static void main(String[] args) {
        SpringApplication.run(MvpApplication.class, args);
    }

    @Bean("mySchedulerFactory")
    public SchedulerFactoryBean schedulerFactory(ApplicationContext applicationContext) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        AutowiringSpingBeanJobFactory jobFactory = new AutowiringSpingBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);

        factoryBean.setJobFactory(jobFactory);    // Set jobFactory to AutowiringSpringBeanJobFactory
        return factoryBean;
    }
}
