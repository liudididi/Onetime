package present;

import java.util.List;

import bean.TuijianBean;
import m.Getdatamodle;
import mInterface.SpHotv;
import mybase.Basepresent;

/**
 * Created by 地地 on 2017/12/7.
 * 邮箱：461211527@qq.com.
 */

public class SpHotp extends Basepresent {
    private Getdatamodle getdatamodle;
   private   SpHotv spHotv;

    public SpHotp(Object viewmodel) {
        super(viewmodel);
          this.spHotv= (SpHotv) viewmodel;
        if(getdatamodle==null){
            getdatamodle=new Getdatamodle();
        }
    }


    public  void  getSpHotdata(int page){
        getdatamodle.getspremen(page, new Getdatamodle.requesttuijianBack() {
            @Override
            public void success(List<TuijianBean> list) {
                spHotv.getdatasuess(list);
            }

            @Override
            public void fail(Throwable e) {
              spHotv.getdatafail(e.toString());
            }
        });
    }
}
