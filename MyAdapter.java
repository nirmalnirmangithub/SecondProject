package Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.testfirstsqliteapplication.R;

import java.util.List;

import Model.Employee;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    private Context context;
    private List<Employee> employeeList;

    public MyAdapter(Context context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        Employee employee=employeeList.get(position);
        holder.id.setText(employee.getId());
        holder.name.setText(employee.getName());
        holder.mobile.setText(employee.getMobile_no());
        holder.address.setText(employee.getAddress());
    }
    @Override
    public int getItemCount() {
        return employeeList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView id,name,mobile,address;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            id=(TextView)itemView.findViewById(R.id.id);
            name=(TextView)itemView.findViewById(R.id.name);
            mobile=(TextView)itemView.findViewById(R.id.mob);
            address=(TextView)itemView.findViewById(R.id.add);


        }
    }
}
