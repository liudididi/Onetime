package mInterface;


import bean.UserBean;
import mybase.Baseview;

/**
 * Created by 地地 on 2017/11/12.
 * 邮箱：461211527@qq.com.
 */

public interface Lognview extends Baseview {
   void  lognsuess(UserBean userBean);
   void  lognfail(String msg);
}
