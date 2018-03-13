package com.example.xmlcreatertest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private String xmlName;
    private final static String fileName = "testGpx.gpx";
    private String gpxName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkStoragePermission();

        Button saveXml = findViewById(R.id.button);
        saveXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createXml();
            }
        });
    }

    private void checkStoragePermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {


                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},23
                );
            }
        }
    }

    private void createXml(){
        //成功之後把new.xml改成自訂名稱.xml
//        File newxmlfile = new File( Environment.getExternalStorageDirectory().getPath() + "/data/new.xml");
//        File newxmlfile = new File(this.getFilesDir(), "new.xml");

//        try{
//            newxmlfile.createNewFile();
//        }catch(IOException e)
//        {
//            Log.e("IOException", "Exception in create new File(");
//        }
        gpxName = "textGpx";


        FileOutputStream fileos = null;
        try{
            fileos = openFileOutput(fileName, MODE_PRIVATE);

        }catch(FileNotFoundException e)
        {
            Log.e("FileNotFoundException",e.toString());
        }
        XmlSerializer serializer = Xml.newSerializer();
        try{
            serializer.setOutput(fileos, "UTF-8");
            serializer.startDocument("UTF-8", Boolean.valueOf(true));
//            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
//            serializer.startTag(null, "root");
//            serializer.startTag(null, "Child1");
//            serializer.endTag(null, "Child1");
//            serializer.startTag(null, "Child2");
//            serializer.attribute(null, "attribute", "value");
//            serializer.endTag(null, "Child2");
//            serializer.startTag(null, "Child3");
//            serializer.text("Some text inside child 3");
//            serializer.endTag(null,"Child3");
//            serializer.endTag(null,"root");
//            serializer.endDocument();
//            serializer.flush();
            serializer.startTag(null, "gpx");
            serializer.attribute(null, "creator", "Hiking Biji");
            serializer.attribute(null, "vserion", "1.0");
//            creator="Hiking Biji"
            serializer.startTag(null, "trk");
//            serializer.attribute(null, "name", gpxName);
            serializer.startTag(null, "name");
            serializer.text(gpxName);
            serializer.endTag(null, "name");
            serializer.startTag(null, "trkseg");

//之後改成用迴圈(rxJava)加入點
//**************************************************************************************************************************************
            //location1
//            \" = "
            serializer.startTag(null, "trkpt");
            serializer.attribute(null, "lat", "25.12345");
            serializer.attribute(null, "lon", "-121.654321");
//            serializer.attribute(null, "ele", "1.6");
//            serializer.attribute(null, "time", "2012-05-23T06:00:00Z");
            serializer.startTag(null, "ele");
            serializer.text("1.6");
            serializer.endTag(null, "ele");
            serializer.startTag(null, "time");
            serializer.text("2012-05-23T06:00:00Z");
            serializer.endTag(null, "time");
            serializer.endTag(null, "trkpt");
            //location2
//            serializer.startTag(null, "trkpt lat=\"" + "25.12345" + "\" lon=\"" + "-121.54321" + "\"");
////            serializer.attribute(null, "lat", "25.12345");
////            serializer.attribute(null, "lon", "-121.654321");
//            serializer.attribute(null, "ele", "1.6");
//            serializer.attribute(null, "time", "2012-05-23T06:00:00Z");
//            serializer.endTag(null, "trkpt");
//**************************************************************************************************************************************

            serializer.endTag(null, "trkseg");
            serializer.endTag(null, "trk");
            serializer.endTag(null, "gpx");
            serializer.endDocument();
            serializer.flush();
            fileos.close();
            //TextView tv = (TextView)findViewById(R.);

        }catch(Exception e)
        {
            Log.e("Exception","Exception occured in wroting");
        }
    }
}
