package app.shears.mvp.quartz;

import app.shears.mvp.cores.enums.Frequency;

import java.time.LocalDateTime;

public interface ICronService {
    String buildCronByDate(LocalDateTime startDate, Frequency frequency);
}
