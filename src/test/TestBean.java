package test;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/15.
 */
@XmlRootElement
public class TestBean {
    private int id;
    private String message;
    public TestBean() {
        this.id = 0;
        this.message = "test success";
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
