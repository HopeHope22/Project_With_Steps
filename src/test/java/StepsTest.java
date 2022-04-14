import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.StepwisePartitioning;

import static com.codeborne.selenide.Selenide.*;

public class StepsTest {

    @BeforeEach
    void openPage() {
        Configuration.browserSize = "1920x1080";
        open("https://youtube.com/");
        sleep(1500);
    }

    @Test
    @Description("Совпадение заголовка канала со значением в поиске")
    public void FirstTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        StepwisePartitioning Step = new StepwisePartitioning();
        String channelName = "Chuck Review";
        Step.setChannelName(channelName);
        Step.openChannel();
        $("div#meta.style-scope.ytd-c4-tabbed-header-renderer").shouldHave(Condition.text(channelName));
    }

}
