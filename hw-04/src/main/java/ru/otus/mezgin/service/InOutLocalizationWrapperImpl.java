package ru.otus.mezgin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.mezgin.errors.ReadInputLineException;

@Service
public class InOutLocalizationWrapperImpl implements InOutLocalizationWrapper {

    private final InOutService inOutService;

    private final LocalizationService localizationService;

    @Autowired
    public InOutLocalizationWrapperImpl(InOutService inOutService, LocalizationService localizationService) {
        this.inOutService = inOutService;
        this.localizationService = localizationService;
    }

    @Override
    public void print(String var) {
        print(var, null);
    }

    @Override
    public void print(String var, String[] args) {
        inOutService.print(getLocalizedString(var, args));
    }

    @Override
    public void println(String var) {
        println(var, null);
    }

    @Override
    public void println(String var, String[] args) {
        inOutService.println(getLocalizedString(var, args));
    }

    @Override
    public void printDefault(String var) {
        inOutService.println(var);
    }

    @Override
    public String readLine() throws ReadInputLineException {
        return inOutService.readLine();
    }

    @Override
    public String getLocalizedString(String var) {
        return getLocalizedString(var, null);
    }

    @Override
    public String getLocalizedString(String var, String... args) {
        return localizationService.getLocalizedString(var, args);
    }
}
