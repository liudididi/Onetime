package mInterface;

import java.util.List;

import bean.TuijianBean;
import mybase.Baseview;

/**
 * Created by 地地 on 2017/12/7.
 * 邮箱：461211527@qq.com.
 */

public interface SpHotv  extends Baseview{
    void  getdatasuess(List<TuijianBean> list);
    void  getdatafail(String msg);
}
