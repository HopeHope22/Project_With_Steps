import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class BaseLightTest {

    @Test
    void testListener(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browserSize = "1920x1080";
        open("https://youtube.com/");
        sleep(2000);
        String chanelName = "Soothing Relaxation";
        $("input#search").should(Condition.visible).setValue(chanelName);
        $("input#search").pressEnter();
        $("ytd-channel-name#channel-title").click();
        $x("(//div[@class='tab-content style-scope tp-yt-paper-tab'])[6]").click();
        String creationDate = $x("(//div[@id=\"right-column\"]//span[@dir=\"auto\"][@class=\"style-scope yt-formatted-string\"])[2]").getText();
        System.out.println("Дата создания : " + creationDate);
    }
}