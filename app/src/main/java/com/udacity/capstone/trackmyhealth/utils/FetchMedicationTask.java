package com.udacity.capstone.trackmyhealth.utils;

import android.app.Dialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.ui.MedicationEditActivity;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.StringReader;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class FetchMedicationTask extends AsyncTask<String, Void, String> {

    private final MedicationEditActivity context;
    String name;

    public FetchMedicationTask(MedicationEditActivity ctx, String name)
    {
        context = ctx;
        this.name = name;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {

        String retVal = "";
        try {
            URL url = NetworkUtils.buildUrl(context, name);
            String xml = NetworkUtils.getResponseFromHttpUrl(url);

            if (xml != null) {
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                InputSource src = new InputSource();
                src.setCharacterStream(new StringReader(xml));
                Document doc = builder.parse(src);
                NodeList comment = doc.getElementsByTagName("comment");

                //  user entered the full name of the medicine
                if (comment == null || comment.item(0).getTextContent().isEmpty()) {
                    retVal = doc.getElementsByTagName("inputTerm").item(0).getTextContent();
                }
                //  else
                if (comment != null && comment.getLength() > 0 && comment.item(0) != null) {
                    String medName = comment.item(0).getTextContent();
                    //  no matching medicine found
                    if (medName.contains(context.getString(R.string.no_med_found))) {
                        retVal = "";
                    }
                    // else extract the medicine name from comment attribute
                    if (medName.contains("with")) {
                        String[] strArr = medName.split("with");
                        if (strArr.length > 1) {
                            String[] s = strArr[1].split(";");
                            retVal = s[0];
                        }
                    }
                }

            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    protected void onPostExecute(String medName)
    {
        super.onPostExecute(medName);
        if (medName.isEmpty())
        {
            medName = context.getString(R.string.med_not_found_user_displ);
            context.name.setError(medName);
            Toast.makeText(context, R.string.med_not_found_user_displ, Toast.LENGTH_LONG).show();
        }
        else {
            context.name.setText(medName.trim().toUpperCase());
        }
    }
}
