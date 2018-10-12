package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lenovo.testfirstsqliteapplication.R;

import java.util.List;

import Model.Hero;

public class MyListViewAdapter extends ArrayAdapter<Hero>
{
    private Context context;
    private List<Hero> heroList;

    public MyListViewAdapter(@NonNull Context context, List<Hero> heroList)
    {
        super(context, R.layout.li_item,heroList);
        this.context=context;
        this.heroList=heroList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View listItem=inflater.inflate(R.layout.li_item,null);
                 TextView id=(TextView)listItem.findViewById(R.id.i);
                 TextView name=(TextView)listItem.findViewById(R.id.n);
                 TextView mobile=(TextView)listItem.findViewById(R.id.m);
                 TextView address=(TextView)listItem.findViewById(R.id.a);
                 Hero hero=heroList.get(position);
                 id.setText(hero.getId());
                 name.setText(hero.getName());
                 mobile.setText(hero.getMobile_no());
                 address.setText(hero.getAddress());
                 return listItem;
    }
}
