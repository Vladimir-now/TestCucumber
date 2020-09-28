package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.appline.framework.managers.PageManager;

public class Steps {

    private PageManager app = PageManager.getPageManager();

    @Когда("^Загружена стартовая страница$")
    public void getInitialPage(){
        app.getStartPage();
    }

    @Когда("^Переход в меню '(.*)'$")
    public void selectMenu(String nameMenu) {app.getStartPage().selectMenu(nameMenu);}

    @Когда("^В подменю выбираем '(.*)'$")
    public void selectSubMenu(String nameSubMenu) {app.getStartPage().selectSubMenu(nameSubMenu);}

    @Когда("^Заполняем форму поле/значение$")
    public void fillField(DataTable dataTable) {
        dataTable.cells().forEach(
                raw -> {
                    app.getMortgageFormPage().fillField(raw.get(0), raw.get(1));
                });
    }

    @Когда("^Устанавливаем переключатели$")
    public void switchButton(DataTable dataTable) {
        dataTable.cells().forEach(
                raw -> {
                    app.getMortgageFormPage().switchButton(raw.get(0), raw.get(1));
                });
    }

    @Тогда("^Сравниваем значения$")
    public void checkFieldValue(DataTable dataTable) {
        dataTable.cells().forEach(
                raw -> {
                    app.getMortgageFormPage().checkFieldValue(raw.get(0), raw.get(1));
                });
    }
}
