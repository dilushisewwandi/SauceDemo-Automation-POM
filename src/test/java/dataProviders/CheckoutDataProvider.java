package dataProviders;

import org.testng.annotations.DataProvider;

public class CheckoutDataProvider {
    @DataProvider
    public Object[][] checkoutData(){
        Object[][] continueData = {{"standard_user","secret_sauce","Dilu","Sewwandi","1234"}};
        return continueData;
    }
}
