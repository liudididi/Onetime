package mInterface;

import java.util.List;

import bean.Duanzibean;
import bean.TuijianBean;
import fragment.Tuijian;
import mybase.Baseview;

/**
 * Created by 地地 on 2017/11/28.
 * 邮箱：461211527@qq.com.
 */

public interface Tuijianview extends Baseview {
    void  getdatasuess(List<TuijianBean> list);
    void  getdatafail(String msg);
}
