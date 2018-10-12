package com.example.lenovo.testfirstsqliteapplication;

import android.Manifest;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import Adapter.MyListViewAdapter;
import Model.Employee;
import Model.Hero;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText mId,mName,mMobileNo,mAddress;
    Button bAddData,bDelete,bUpdate,bClose,bListView,bRecycleView,bAlertDialogBox;
    ListView listView;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Employee> employeeList;
    List<Hero> heroList;
    MyAdapter myAdapter;
    MyListViewAdapter myListViewAdapter;
    MyDatabase database;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mId=(EditText)findViewById(R.id.id);
        mName=(EditText)findViewById(R.id.name);
        mMobileNo=(EditText)findViewById(R.id.mobileNo);
        mAddress=(EditText)findViewById(R.id.address);
        bAddData=(Button)findViewById(R.id.addData);
        bDelete=(Button)findViewById(R.id.deleteData);
        bUpdate=(Button)findViewById(R.id.updateData);
        bClose=(Button)findViewById(R.id.closeData);
        bListView=(Button)findViewById(R.id.bListView);
        bRecycleView=(Button)findViewById(R.id.bRecycleView);
        bAlertDialogBox=(Button)findViewById(R.id.bAlertDialogBox);
        database=new MyDatabase(this);
        sqLiteDatabase=database.getWritableDatabase();
        employeeList=new ArrayList<>();
        heroList=new ArrayList<>();
        listView=(ListView)findViewById(R.id.listView);
        recyclerView=(RecyclerView)findViewById(R.id.recycleView);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        bAddData.setOnClickListener(this);
        bClose.setOnClickListener(this);
        bUpdate.setOnClickListener(this);
        bDelete.setOnClickListener(this);
        bListView.setOnClickListener(this);
        bRecycleView.setOnClickListener(this);
        bAlertDialogBox.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        String id=mId.getText().toString().trim();
        String name=mName.getText().toString().trim();
        String mobile=mMobileNo.getText().toString().trim();
        String address=mAddress.getText().toString().trim();
        if(view==bAddData)
        {
            if(!TextUtils.isEmpty(id))
            {
                if (!TextUtils.isEmpty(name))
                {
                    if(!TextUtils.isEmpty(mobile))
                    {
                        if (!TextUtils.isEmpty(address))
                        {
                            ContentValues cv=new ContentValues();
                            cv.put(MyDatabase.COL1,id);
                            cv.put(MyDatabase.COL2,name);
                            cv.put(MyDatabase.COL3,mobile);
                            cv.put(MyDatabase.COL4,address);
                            long result=sqLiteDatabase.insert(MyDatabase.TableName,null,cv);
                            if(result!=-1)
                            {
                                Toast.makeText(MainActivity.this,"Data is Inserted Properly",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }

        }
        if(view==bDelete)
        {
            int data=database.delete(id);
            if(data>0)
            {
               Toast.makeText(MainActivity.this,"Data is deleted",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(MainActivity.this,"Error ! No Data",Toast.LENGTH_LONG).show();
            }
        }
        if(view==bUpdate)
        {
            int res=database.updateData(id,name,mobile,address);
            if(res>0)
            {
                Toast.makeText(MainActivity.this,"Data is Updated Successfully"+"\n\n",Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this,"The No of Row Affected ="+res,Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
            }
        }
        if(view==bClose)
        {
            finish();
        }
        if (view==bAlertDialogBox)
        {
             Cursor res=database.getAllData();
            StringBuffer buffer = new StringBuffer();
             if(res.getCount()!=0)
             {
                 while (res.moveToNext()) {

                     buffer.append("ID=" + res.getString(0) + "\n"
                             + "NAME=" + res.getString(1) + "\n"
                             + "Mobile No =" + res.getString(2)+"\n"
                             + "Address=" + res.getString(3)+"\n\n");
                 }
                 showDataInAlertDialogBox("Data",buffer.toString() );
             }
             else
             {
                 showDataInAlertDialogBox("Error","No Data");
             }
        }
        if(view==bRecycleView)
        {
            Cursor res=database.getAllData();
            if(res.getCount()==0)
            {
                showDataInAlertDialogBox("Error","No Data");
            }
            else
            {
                while (res.moveToNext())
                {
                    String rId = res.getString(0);
                    String rName=res.getString(1);
                    String rMobile=res.getString(2);
                    String rAddress=res.getString(3);
                    Employee employee=new Employee(rId,rName,rMobile,rAddress);
                    employeeList.add(employee);
                }
            }
            myAdapter=new MyAdapter(MainActivity.this,employeeList);
            recyclerView.setAdapter(myAdapter);
        }
        if(view==bListView)
        {
            Cursor res=database.getAllData();
            if(res.getCount()==0)
            {
                showDataInAlertDialogBox("Error","No Data");
            }
            else
            {
                while (res.moveToNext())
                {
                    String ka = res.getString(0);
                    String kb=res.getString(1);
                    String kc=res.getString(2);
                    String kd=res.getString(3);
                    Hero hero=new Hero(ka,kb,kc,kd);
                    heroList.add(hero);
                }
                myListViewAdapter=new MyListViewAdapter(MainActivity.this,heroList);
                listView.setAdapter(myListViewAdapter);
            }
        }
        mId.setText("");
        mName.setText("");
        mMobileNo.setText("");
        mAddress.setText("");
        mId.requestFocus();
    }
    public void showDataInAlertDialogBox(String title,String message)
    {
        AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setCancelable(true);
        adb.setIcon(R.drawable.file);
        adb.setTitle(title);
        adb.setMessage(message);
        adb.show();
    }


}
