package present;

import javax.inject.Inject;

import bean.VisionBean;
import m.UpModel;
import mInterface.UpVisonv;
import mybase.Basebean;
import mybase.Baseview;

/**
 * Created by 地地 on 2017/12/14.
 * 邮箱：461211527@qq.com.
 */

public class UpVisonP {
    @Inject
    UpModel upModel;

    private UpVisonv upVisonv;

    @Inject
    public UpVisonP(UpVisonv upVisonv) {
        this.upVisonv = upVisonv;
    }
    public  void  getupvision(){
        upModel.upvision(new UpModel.UpVisonBack() {
            @Override
            public void upvisionccess(Basebean<VisionBean> visionBeanBasebean) {
                if(visionBeanBasebean.code.equals("0")){
                    upVisonv.upsuccess(visionBeanBasebean.data);
                }else {
                    upVisonv.fail(visionBeanBasebean.msg);
                }
            }

            @Override
            public void fail(Throwable t) {
                upVisonv.fail(t.toString());
            }
        });
       }
}
