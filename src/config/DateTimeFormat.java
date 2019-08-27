package config;

/**
 * @Author: zr
 * @Description:
 * @Date: 11:50 2019/8/27
 * @Modified by:
 */
public enum DateTimeFormat {

    DEFAULT("yyyy-MM-dd HH:mm:ss","default");


    private String dataformat;
    private String desc;

    public String getDataformat() {
        return dataformat;
    }

    public void setDataformat(String dataformat) {
        this.dataformat = dataformat;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    DateTimeFormat(String dataformat, String desc) {
        this.dataformat = dataformat;
        this.desc = desc;
    }
}
