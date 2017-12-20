package bean;

import java.util.List;

import mybase.Basebean;

/**
 * Created by 地地 on 2017/12/1.
 * 邮箱：461211527@qq.com.
 */

public class TuijianBean extends Basebean {


    public  String cover;
    public  String workDesc;
    public  String createTime;
    public  String videoUrl;

    public List<Pinglun> comments;
    public   int hight;
    public   int uid;
    public   int wid;
    public  UserBean user;

public  class Pinglun{

    public   int cid;
    public   int uid;
    public  String content;
    public  String nickname;

}
}
