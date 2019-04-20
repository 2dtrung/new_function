package dangkhoa.dmt.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myActivity = this;

        anhXa();
        khoiTao();
        setData();


    }

    private static MainActivity myActivity;

    public static MainActivity getMyActivity()
    {
        return myActivity;
    }

    private Animation animScale, animScale_appear;

    private GridView gdvGamePlay;

    private O_So_Adapter adapter;

    private Button myNewGame;

    private int diem = 0;

    private int diem_2 = 0;

    private TextView myDiem;

    private float x0, y0, x, y;

    private void anhXa(){
        gdvGamePlay = (GridView)findViewById(R.id.gdvGamePlay);
        myDiem = (TextView)findViewById(R.id.myDiem);
        myNewGame = (Button)findViewById(R.id.myNewGame);
        animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        animScale_appear = AnimationUtils.loadAnimation(this, R.anim.anim_scale_appear);
    }

    private void khoiTao(){
        DataGame.getDatagame().intt(MainActivity.this);
        adapter = new O_So_Adapter(MainActivity.this, 0, DataGame.getDatagame().getArrSo());
        gdvGamePlay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x0 = event.getX();
                        y0 = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        x = event.getX();
                        y = event.getY();
                        if (Math.abs(x - x0) > Math.abs(y - y0)) {
                            if (x > x0) {                           // Vuốt phải
                                DataGame.getDatagame().vuotPhai();
                                adapter.notifyDataSetChanged();
                            } else if(x < x0){                                //Vuốt trái
                                DataGame.getDatagame().vuotTrai();
                                adapter.notifyDataSetChanged();
                            }
                        } else if(Math.abs(x - x0) < Math.abs(y - y0)){
                            if (y > y0) {                            // Vuốt xuống
                                DataGame.getDatagame().vuotXuong();
                                adapter.notifyDataSetChanged();
                            } else if(y < y0) {                                 // Vuốt lên
                                DataGame.getDatagame().vuotLen();
                                adapter.notifyDataSetChanged();
                            }
                        }
                        diem_2 = diem;
                        diem = diem + 10;
                        myDiem.setText(""+diem);
                        if(DataGame.getDatagame().kiemTra() == 0)
                        {
                            Toast.makeText(MainActivity.this, "GAME OVER", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
                return true;
            }
        });


        myNewGame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    DataGame.getDatagame().restart();
                    adapter.notifyDataSetChanged();
                    diem = 0;
                    myDiem.setText(""+diem);
                    setData();
                }
                return true;
            }
        });



    }

    private void setData(){
        gdvGamePlay.setAdapter(adapter);
    }

    public void phongTo(ImageView img)
    {
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);
            }
        });
    }

    public void xuatHien(ImageView img)
    {
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale_appear);
            }
        });
    }
    public void thongBao1()
    {
        Toast.makeText(MainActivity.this, "Loai 1", Toast.LENGTH_LONG).show();
    }
    public void thongBao2()
    {
        Toast.makeText(MainActivity.this, "Loai 2", Toast.LENGTH_LONG).show();
    }
}