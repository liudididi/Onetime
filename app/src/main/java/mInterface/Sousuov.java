package mInterface;

import java.util.List;

import bean.GuanzhuBean;

/**
 * Created by 地地 on 2017/12/15.
 * 邮箱：461211527@qq.com.
 */

public interface Sousuov {
    void  getisousuosuccess(List<GuanzhuBean> listBasebean);
    void  getisousuofail(String msg);
}
