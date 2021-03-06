package com.example.Contact_varinder_C0779368_android;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

public class PersonActivity extends AppCompatActivity {

    private DatabaseHelper mDataBase;
    private ArrayList<PersonData> personsList;
    private ArrayList<PersonData> searchList = new ArrayList<>();
    private SwipeMenuListView listView;
    private EditText searchText;
    private PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        listView = findViewById(R.id.listView);
        personsList = new ArrayList<>();
        searchText = findViewById(R.id.searchText);
        mDataBase = new DatabaseHelper(this);
        personAdapter = new PersonAdapter(this, R.layout.list_layout, personsList, mDataBase);
        listView.setAdapter(personAdapter);
        loadPersons();

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String searchText = s.toString();
                searchList.clear();
                if (!searchText.isEmpty()) {

                    for (PersonData person : personsList) {
                        if (person.getFirstName().contains(searchText)) {
                            searchList.add(person);
                        }
                    }
                } else {
                    searchList.addAll(personsList);

                }

                personAdapter = new PersonAdapter(PersonActivity.this, R.layout.list_layout, searchList, mDataBase);
                listView.setAdapter(personAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem editItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                editItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                editItem.setWidth((250));

                // set item title fontsize
                editItem.setTitleSize(18);
                // set item title font color
                editItem.setTitleColor(Color.WHITE);
                // add to menu

                editItem.setIcon(R.drawable.ic_edit);
                menu.addMenuItem(editItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth((250));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);

            }
        };

        listView.setMenuCreator(creator);


        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // update
                        Toast.makeText(PersonActivity.this, "update clicked", Toast.LENGTH_SHORT).show();
                        PersonData person1 = personsList.get(position);
                        personAdapter.updatePerson(person1);
                        personsList.clear();
                        personAdapter.notifyDataSetChanged();
                        listView.invalidateViews();
                        listView.refreshDrawableState();
                        break;
                    case 1:
                        // delete
                        Toast.makeText(PersonActivity.this, "delete clicked", Toast.LENGTH_SHORT).show();
                        PersonData person = personsList.get(position);
                        int id2 = person.getId();
                        if (mDataBase.deletePerson(id2))
                            loadPersons();
//                            personsList.remove(position);
//                        personAdapter.notifyDataSetChanged();
                        break;
                }
                return true;
            }
        });

    }


    private void loadPersons() {
        personsList.clear();
        Cursor cursor = mDataBase.getAllPersons();
        if (cursor.moveToFirst()) {
            do {
                personsList.add(new PersonData
                        (cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                ));


            } while (cursor.moveToNext());
            cursor.close();
            //show item in a listView
            //we use a custom adapter to show employees

        }
        searchText.setText("a");
        searchText.setText("");
        personAdapter.notifyDataSetChanged();
    }


}
