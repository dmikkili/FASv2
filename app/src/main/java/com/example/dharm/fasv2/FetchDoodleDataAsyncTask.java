package com.example.dharm.fasv2;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * An asynchronous task object to fetch data about the Google doodles.
 */
public class FetchDoodleDataAsyncTask extends AsyncTask<String, Void, ArrayList<DoodleData>> {

    private static final String LOG_TAG = FetchDoodleDataAsyncTask.class.getSimpleName();
    private static final String FAS_API_BASE_URL = "https://fas-api.appspot.com/";
    private static final String SORT_PARAMETER = "sort_order";
    private static final String ID_PARAMETER = "item_id";
    private static final String TITLE_PARAMETER = "title";
    private static final String RELEASE_DATE_PARAMETER = "release_date";
    private static final String DESCRIPTION_PARAMETER = "description";
    private static final String PRICE_PARAMETER = "price";
    private static final String IMAGE_URL_PARAMETER = "image_url";

    private RecyclerView mRecyclerView;

    /**
     * Constructor for the AsyncParcelableFetchDoodleDataTask object.
     *
     * @param recyclerView The recycler view to attach the adapter with data to after the async task
     *                     finishes retrieving the data.
     */
    public FetchDoodleDataAsyncTask(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    @Override
    protected ArrayList<DoodleData> doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String doodleDataJsonResponse = null;

        try {
            // The first parameter is the sort order, so for popular Doodles it would be
            // 'rating.desc', while for recent Doodles it would be 'release_date.desc'. The second
            // parameter is one of 'recent', or 'vintage' to show only those Doodles.
            Uri.Builder uriBuilder = Uri.parse(FAS_API_BASE_URL).buildUpon()
                    .appendQueryParameter(SORT_PARAMETER, params[0]);
            if (params.length == 2) uriBuilder.appendQueryParameter(params[1], "true");
            Uri builtUri = uriBuilder.build();

            // Connect to FAS API to retrieve data about the doodles.
            URL url = new URL(builtUri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // See if the input stream is not null and a connection could be made. If it is null, do
            // not process any further.
            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null) {
                return null;
            }

            // Read the input stream to see if any valid response was given.
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }
            if (buffer.length() == 0) {
                // If the stream is empty, do not process any further.
                return null;
            }

            doodleDataJsonResponse = buffer.toString();

        } catch (IOException e) {
            // If there was no valid Google Doodle data returned, there is no point in attempting to
            // parse it.
            Log.e(LOG_TAG, "IOException fetching Doodle data.", e);
            return null;
        } finally {
            // Make sure to always try to close the connection and the reader if they are not null.
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing reader.", e);
                }
            }
        }

        // If valid data was returned, return the parsed data.
        try {
            return parseDoodleDataJsonResponse(doodleDataJsonResponse);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }

        // In case an error was not caught, and the code gets to this point, return null so the
        // method finishes.
        return null;
    }

    @Override
    /**
     * Override the onPostExecute method to create a new RecyclerViewAdapter to attach to the actual
     * RecyclerView in the ViewPager.
     * @param doodleDataArrayList A list of objects with information about the Google doodles.
     */
    public void onPostExecute(ArrayList<DoodleData> doodleDataArrayList) {
        mRecyclerView.setAdapter(new RecyclerViewAdapter(doodleDataArrayList));
    }

    /**
     * Parses the JSON response for information about the Google doodles.
     * @param doodleDataJsonResponse A JSON string which needs to be parsed for data about the
     *                               Google doodles.
     */
    private ArrayList<DoodleData> parseDoodleDataJsonResponse(String doodleDataJsonResponse)
            throws JSONException {
        try {
            JSONArray doodlesInfo = new JSONArray(doodleDataJsonResponse);
            ArrayList<DoodleData> doodleDataArrayList = new ArrayList<>();
            for (int index = 0; index < doodlesInfo.length(); index++) {
                JSONObject doodleDataJson = doodlesInfo.getJSONObject(index);
                DoodleData doodleData = new DoodleData(doodleDataJson.getString(ID_PARAMETER),
                        doodleDataJson.getString(TITLE_PARAMETER),
                        doodleDataJson.getString(RELEASE_DATE_PARAMETER),
                        doodleDataJson.getString(DESCRIPTION_PARAMETER),
                        doodleDataJson.getString(PRICE_PARAMETER),
                        doodleDataJson.getString(IMAGE_URL_PARAMETER));
                doodleDataArrayList.add(doodleData);
            }
            return doodleDataArrayList;
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
            return null;
        }
    }

}