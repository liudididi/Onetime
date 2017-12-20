package present;

import java.util.List;

import javax.inject.Inject;

import bean.GuanzhuBean;
import m.IGuanzhumodle;
import m.Sousuomodle;
import mInterface.IGuanzhuv;
import mInterface.Sousuov;
import mybase.Basebean;

/**
 * Created by 地地 on 2017/12/15.
 * 邮箱：461211527@qq.com.
 */

public class Sousuop {
    @Inject
    Sousuomodle  sousuomodle;
    private Sousuov sousuov;
    @Inject
    public Sousuop(Sousuov sousuov) {
        this.sousuov = sousuov;
    }
    public void  getsousuodata(String ketword,String page){
        sousuomodle.sousuodata(ketword, page, new Sousuomodle.RequestBack() {
            @Override
            public void getdatasuccess(Basebean<List<GuanzhuBean>> listBasebean) {
                sousuov.getisousuosuccess(listBasebean.data);
            }

            @Override
            public void fail(Throwable t) {
                sousuov.getisousuofail(t.toString());
            }
        });
    }

}
