package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class StepwisePartitioning {

    @Step("Find a channel")
    public void setChannelName(String chanelName){
        $("input#search").setValue(chanelName);
        $("input#search").pressEnter();

    }

    @Step("Open channel page")
    public void openChannel(){
        $("ytd-channel-name#channel-title").click();
    }

}