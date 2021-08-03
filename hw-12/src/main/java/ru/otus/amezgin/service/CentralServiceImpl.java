package ru.otus.amezgin.service;

import org.springframework.stereotype.Service;
import ru.otus.amezgin.domain.Report;
import ru.otus.amezgin.domain.ReportStatus;


@Service
public class CentralServiceImpl implements CentralService{

    @Override
    public Report sendPurpose(String purpose) {
        if (purpose.equals("Татуин")) {
            return new Report("Слишком много повстанцев! Нужна помощь!", ReportStatus.IN_PROCESS, "Имперский флот");
        } else if (purpose.equals("Корусант")) {
            return new Report("Информация не верна. Повстанцу не обнаружены!", ReportStatus.SUCCESS, "Штурмовой корпус");
        } else if (purpose.equals("Аапрей")) {
            return new Report("Мы разбиты!", ReportStatus.FAIL, "Корпус дроидов");
        } else {
            return new Report("Цель не найдена!", ReportStatus.FAIL, "Корпус дроидов");
        }
    }
}
