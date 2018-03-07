package com.example.android.brand_i;

/**
 * Created by Hana Kari on 3/4/2018.
 */

public class CustomFeedClass {

    int BrandLogo;
    String BrandName;
    String Topic;

    public CustomFeedClass(int BrandLogo, String BrandName, String Topic){
        this.BrandLogo = BrandLogo;
        this.BrandName = BrandName;
        this.Topic = Topic;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public int getBrandLogo() {
        return BrandLogo;
    }

    public void setBrandLogo(int brandLogo) {
        BrandLogo = brandLogo;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }


}
