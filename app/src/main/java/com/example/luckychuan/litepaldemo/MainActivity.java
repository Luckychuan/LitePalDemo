package com.example.luckychuan.litepaldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建数据库的按钮
        ((Button) findViewById(R.id.create_db_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });

        //添加数据的按钮
        ((Button) findViewById(R.id.insert_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(123);
                book.setPrice(10.00);
                book.save();
            }
        });

        //查询所有数据的按钮
        ((Button) findViewById(R.id.query_all_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = DataSupport.findAll(Book.class);
                for (Book book : books) {
                    Log.d(TAG, "onClick: " + book.getId());
                    Log.d(TAG, "onClick: " + book.getName());
                    Log.d(TAG, "onClick: " + book.getAuthor());
                    Log.d(TAG, "onClick: " + book.getPages());
                    Log.d(TAG, "onClick: " + book.getPrice());
                }
            }
        });

        //查询带条件数据按钮
        ((Button) findViewById(R.id.query_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询第一条数据
                Book firstBook = DataSupport.findFirst(Book.class);
                Log.d(TAG, "onClick: " + firstBook.getId());
                Log.d(TAG, "onClick: " + firstBook.getName());
                Log.d(TAG, "onClick: " + firstBook.getAuthor());
                Log.d(TAG, "onClick: " + firstBook.getPages());
                Log.d(TAG, "onClick: " + firstBook.getPrice());

                //查询最后一条数据
                Book lastBook = DataSupport.findLast(Book.class);
                Log.d(TAG, "onClick: " + lastBook.getId());
                Log.d(TAG, "onClick: " + lastBook.getName());
                Log.d(TAG, "onClick: " + lastBook.getAuthor());
                Log.d(TAG, "onClick: " + lastBook.getPages());
                Log.d(TAG, "onClick: " + lastBook.getPrice());

                //select()方法用于查询哪几列
                List<Book> selectBooks = DataSupport.select("name","author").find(Book.class);
                for (Book book : selectBooks) {
                    Log.d(TAG, "onClick: " + book.getName());
                    Log.d(TAG, "onClick: " + book.getAuthor());
                }

                List<Book> whereBooks = DataSupport.where("name = ?","The Da Vinci Code").find(Book.class);
                for (Book book : whereBooks) {
                    Log.d(TAG, "onClick: " + book.getId());
                    Log.d(TAG, "onClick: " + book.getName());
                    Log.d(TAG, "onClick: " + book.getAuthor());
                    Log.d(TAG, "onClick: " + book.getPages());
                    Log.d(TAG, "onClick: " + book.getPrice());
                }

                //order()方法用于对查询结果的排列方式
                List<Book> orderBooks = DataSupport.order("price desc").find(Book.class);
                for (Book book : orderBooks) {
                    Log.d(TAG, "onClick: " + book.getId());
                    Log.d(TAG, "onClick: " + book.getName());
                    Log.d(TAG, "onClick: " + book.getAuthor());
                    Log.d(TAG, "onClick: " + book.getPages());
                    Log.d(TAG, "onClick: " + book.getPrice());
                }

                //limit()方法用于对查询结果的限制，如limit(3)表示只查询前三条数据
                List<Book> limitBooks = DataSupport.limit(2).find(Book.class);
                for (Book book : limitBooks) {
                    Log.d(TAG, "onClick: " + book.getId());
                    Log.d(TAG, "onClick: " + book.getName());
                    Log.d(TAG, "onClick: " + book.getAuthor());
                    Log.d(TAG, "onClick: " + book.getPages());
                    Log.d(TAG, "onClick: " + book.getPrice());
                }

                //offset()方法用于查询结果的偏移量，如offset(1)表示偏移1条数据，从第2条数据开始查询
                //offset()方法和limit()方法连用，共同组成了SQL当中的limit关键字
                List<Book> offsetBooks = DataSupport.limit(2).offset(1).find(Book.class);
                for (Book book : offsetBooks) {
                    Log.d(TAG, "onClick: " + book.getId());
                    Log.d(TAG, "onClick: " + book.getName());
                    Log.d(TAG, "onClick: " + book.getAuthor());
                    Log.d(TAG, "onClick: " + book.getPages());
                    Log.d(TAG, "onClick: " + book.getPrice());
                }

            }
        });

        //删除数据的按钮
        ((Button) findViewById(R.id.delete_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除所有数据
                DataSupport.deleteAll(Book.class);

                //满足条件的删除
                DataSupport.deleteAll(Book.class, "price < ?", "100");
            }
        });

        //更新数据的按钮
        ((Button) findViewById(R.id.update_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setPages(233);
                book.updateAll("name = ? and author = ?", "The Da Vinci Code", "Dan Brown");
            }
        });


    }
}
