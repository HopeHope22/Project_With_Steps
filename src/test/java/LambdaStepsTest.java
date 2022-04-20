import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class LambdaStepsTest {
    @Test
    void baseTestLambdaStep(){
        String channelName = "Soothing Relaxation";
        SelenideElement confirmedTooltip = $x("//div[@id=\"tooltip\"][@style-target=\"tooltip\" and  .='Подтверждено']");

        step("Open main page", () -> {
            Configuration.browserSize = "1920x1080";
            open("https://youtube.com/");
            sleep(2000);
        });

        step("Find music channel" + channelName, () -> {
            $("input#search").should(Condition.visible).setValue(channelName);
            $("input#search").pressEnter();
            $("ytd-channel-name#channel-title").click();
        });

        step("Check channel name and open channel page " + channelName, () -> {
            $("ytd-channel-name#channel-title").should(text("Soothing Relaxation"));
            $("ytd-channel-name#channel-title").click();
        });

        step("Check channel name on channel page " + channelName, () -> {
            $x("(//ytd-channel-name[@id=\"channel-name\"][@wrap-text])[1]").should(text("Soothing Relaxation"));
        });

        step("Convert string to float and check conformed", () -> {
            String subscribersCount = String.valueOf($("yt-formatted-string#subscriber-count"));
            String[] strs = subscribersCount.split(" ");
            float nSubscribers = 0;

            if (strs[1].contains("млн")){
                nSubscribers = Float.parseFloat(strs[0].replace(",",".")) * 1000000;
            } else if (strs[1].contains("тыс.")){
                nSubscribers = Float.parseFloat(strs[0].replace(",",".")) * 1000;
            }
            System.out.println(nSubscribers);

            if (nSubscribers > 100000.00){
                $("channel-container").shouldHave((Condition) confirmedTooltip);
            }
        });

    }
}

