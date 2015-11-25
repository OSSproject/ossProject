package embedded.kookmin.ac.kr.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import embedded.kookmin.ac.kr.projectopensource.R;

/**
 * Created by 승수 on 2015-11-22.
 */
public class CustomAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<InfoClass> infoList;
    private ViewHolder viewHolder;

    public CustomAdapter(Context c , ArrayList<InfoClass> array){
        inflater = LayoutInflater.from(c);
        infoList = array;
    }

    @Override
    public int getCount() {
        return infoList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {

        View v = convertview;

        if(v == null){
            viewHolder = new ViewHolder();
            v = inflater.inflate(R.layout.page, null);
            viewHolder.name = (TextView)v.findViewById(R.id.page_num);
            v.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder)v.getTag();
        }

        viewHolder.name.setText(infoList.get(position).name);

        return v;
    }

    public void setArrayList(ArrayList<InfoClass> arrays){
        this.infoList = arrays;
    }

    public ArrayList<InfoClass> getArrayList(){
        return infoList;
    }

    /*
     * ViewHolder
     */
    class ViewHolder{
        TextView name;
    }


}





