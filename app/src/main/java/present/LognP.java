package present;



import m.LognModle;
import mInterface.Lognview;
import mybase.Basepresent;


/**
 * Created by 地地 on 2017/11/12.
 * 邮箱：461211527@qq.com.
 */

public class   LognP  extends Basepresent {
    private LognModle lognModle;
    public LognP(Lognview viewmodel) {
        super(viewmodel);
        if(lognModle==null){
            lognModle=new LognModle();
        }
    }

}
