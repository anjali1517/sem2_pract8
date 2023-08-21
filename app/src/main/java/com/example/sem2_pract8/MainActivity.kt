package com.example.sem2_pract8

import android.annotation.SuppressLint
import android.app.LoaderManager
import android.content.Context
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.loader.app.LoaderManager.LoaderCallbacks

class MainActivity : AppCompatActivity(),LoaderManager.LoaderCallbacks<Cursor> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      loaderManager.initLoader(1,null,this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor>? {

        Log.i("Anjali","onCreateLoader")

        return CursorLoader(this,
            ContactsContract.Contacts.CONTENT_URI,null,null,null,ContactsContract.Contacts.SORT_KEY_PRIMARY)
    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {
        Log.i("Anjali","onLoaderReset")
    }

    @SuppressLint("Range")
    override fun onLoadFinished(loader: Loader<Cursor>?, data: Cursor?) {

        if (data!!.count>0) {
            if (data.moveToFirst()) {
                do {
                    val name: String =
                        data.getString(data.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    val number: String =
                        data.getString(data.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    Log.i("Anjali", "$name :- $number")
                } while (data.moveToNext())
            }
        }
    }

}




