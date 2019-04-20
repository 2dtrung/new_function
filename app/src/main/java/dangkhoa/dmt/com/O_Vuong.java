package dangkhoa.dmt.com;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;

public class O_Vuong extends ImageView {

    public ImageView imgHinh;

    public O_Vuong(Context context) {
        super(context);
    }

    public O_Vuong(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public O_Vuong(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ArrayList<Integer> arrayHinh;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int dai = getMeasuredWidth();
        setMeasuredDimension(dai - 7, dai - 7);

    }

    public void setHinh(int so,int loai)
    {
        arrayHinh = new ArrayList<>();
        arrayHinh.add(R.drawable.a);
        arrayHinh.add(R.drawable.dat);
        arrayHinh.add(R.drawable.dong);
        arrayHinh.add(R.drawable.bac);
        arrayHinh.add(R.drawable.vang);
        arrayHinh.add(R.drawable.hongngoc);
        arrayHinh.add(R.drawable.kimcuong);
        imgHinh = (ImageView)findViewById(R.id.txvOVuong);
        if(so > 0) {
            imgHinh.setImageResource(arrayHinh.get((int) (Math.log(so) / Math.log(2))));
            if(loai == 1) {
                MainActivity.getMyActivity().phongTo(imgHinh);
            }
            else if(loai == 2)
            {
                MainActivity.getMyActivity().xuatHien(imgHinh);
            }
        }
        else
        {
            imgHinh.setImageResource(arrayHinh.get(0));
        }
    }
}
