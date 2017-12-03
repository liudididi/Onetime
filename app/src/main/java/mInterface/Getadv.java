package mInterface;

import java.util.List;

import bean.Guanggao;
import mybase.Baseview;

/**
 * Created by 地地 on 2017/12/1.
 * 邮箱：461211527@qq.com.
 */

public interface Getadv extends Baseview{
  void  adsuccess(List<Guanggao> list);
  void  adfail(String msg);
}
