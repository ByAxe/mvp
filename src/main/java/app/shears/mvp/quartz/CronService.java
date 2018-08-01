package app.shears.mvp.quartz;

import app.shears.mvp.cores.enums.Frequency;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Сервис по построению CRON выражений
 */
@Service
public class CronService implements ICronService {

    /**
     * По переданной дате и типу периода строим Cron-выражение
     *
     * @param startDate - дата
     * @param frequency - тип периода {@link Frequency}
     * @return - cron выражение
     */
    @Override
    public String buildCronByDate(LocalDateTime startDate, Frequency frequency) {
        String result;
        final int second = startDate.getSecond();
        final int minute = startDate.getMinute();
        final int hour = startDate.getHour();
        final int day = startDate.getDayOfMonth();
        final int dayOfWeek = startDate.getDayOfWeek().ordinal(); // Числовой номер дня в неделе

        result = second + " " + minute + " " + hour + " ";

        switch (frequency) {
            case DAILY:
                result += "1/1 * ? *"; // Пример результата: "0 0 12 1/1 * ? *"
                break;
            case WEEKLY:
                result += "? * " + dayOfWeek + " *"; // Пример результата: "0 0 12 ? * 1 *"
                break;
            case MONTHLY:
                result += day + " 1/1 ? *"; // Пример результата: "0 0 12 12 1/1 ? *"
                break;
            default:
                throw new RuntimeException("Should not occur!");
        }

        return result;
    }
}
