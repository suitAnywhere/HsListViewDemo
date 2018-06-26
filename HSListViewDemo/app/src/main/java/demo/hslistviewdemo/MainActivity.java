package demo.hslistviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import demo.hslistviewdemo.view.HVListView;

public class MainActivity extends AppCompatActivity {

    private HVListView mListView;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (HVListView) findViewById(R.id.list);
        //设置列头
        mListView.mListHead = (LinearLayout) findViewById(R.id.head);
        //设置数据
        mListView.setAdapter(new DataAdapter());

        mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    private class DataAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 50;//固定显示50行数据
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item, null);
            }

            for (int i = 0; i < 7; i++) {
                ((TextView) convertView.findViewById(R.id.item3 + i)).setText("数据" + position + "--" + (i + 1));
            }

            //校正（处理同时上下和左右滚动出现错位情况）
            View child = ((ViewGroup) convertView).getChildAt(mListView.startScrollList);
            int head = mListView.getHeadScrollX();
            if (child.getScrollX() != head) {
                child.scrollTo(mListView.getHeadScrollX(), 0);
            }
            return convertView;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }
}
