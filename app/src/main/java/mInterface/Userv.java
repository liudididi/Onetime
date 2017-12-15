package mInterface;

import java.util.List;

import bean.TuijianBean;

/**
 * Created by 地地 on 2017/12/13.
 * 邮箱：461211527@qq.com.
 */

public interface Userv {
    void  getdatasuess(List<TuijianBean> list);
    void  getdatafail(String msg);
}
