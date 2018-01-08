package bean;

/**
 * Created by 地地 on 2017/12/22.
 * 邮箱：461211527@qq.com.
 */

public class LiaoTianBean {
  public  int type;
  public  String text;
  public  String time;

  public LiaoTianBean(int type, String text, String time) {
    this.type = type;
    this.text = text;
    this.time = time;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }
}
