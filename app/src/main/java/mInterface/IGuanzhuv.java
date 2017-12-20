package mInterface;

import java.util.List;

import bean.GuanzhuBean;
import mybase.Basebean;

/**
 * Created by 地地 on 2017/12/15.
 * 邮箱：461211527@qq.com.
 */

public interface IGuanzhuv {
    void  getiguanzhusuccess(List<GuanzhuBean> listBasebean);
    void  getiguanzhufail(String msg);
}
