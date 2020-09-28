package ru.appline.framework.managers;


import ru.appline.framework.pages.MortgageFormPage;
import ru.appline.framework.pages.StartPage;

public class PageManager {


    private static PageManager pageManager;

    private StartPage startPage;

    private MortgageFormPage mortgageFormPage;


    private PageManager() {
    }

    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }


    public MortgageFormPage getMortgageFormPage() {
        if (mortgageFormPage == null) {
            mortgageFormPage = new MortgageFormPage();
        }
        return mortgageFormPage;
    }
}




