package me.maiz.ittrainning.simplecrawlerboot.web.form;

public class ConfigQueryForm extends QueryForm {
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ConfigQueryForm{" +
                "userId=" + userId +
                '}';
    }
}
