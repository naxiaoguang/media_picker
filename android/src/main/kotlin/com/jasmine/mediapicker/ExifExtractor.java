package com.jasmine.mediapicker;

import android.media.ExifInterface;
import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.media.ExifInterface.TAG_APERTURE;
import static android.media.ExifInterface.TAG_DATETIME;
import static android.media.ExifInterface.TAG_DATETIME_DIGITIZED;
import static android.media.ExifInterface.TAG_EXPOSURE_TIME;
import static android.media.ExifInterface.TAG_FLASH;
import static android.media.ExifInterface.TAG_FOCAL_LENGTH;
import static android.media.ExifInterface.TAG_GPS_ALTITUDE;
import static android.media.ExifInterface.TAG_GPS_ALTITUDE_REF;
import static android.media.ExifInterface.TAG_GPS_DATESTAMP;
import static android.media.ExifInterface.TAG_GPS_LATITUDE;
import static android.media.ExifInterface.TAG_GPS_LATITUDE_REF;
import static android.media.ExifInterface.TAG_GPS_LONGITUDE;
import static android.media.ExifInterface.TAG_GPS_LONGITUDE_REF;
import static android.media.ExifInterface.TAG_GPS_PROCESSING_METHOD;
import static android.media.ExifInterface.TAG_GPS_TIMESTAMP;
import static android.media.ExifInterface.TAG_IMAGE_LENGTH;
import static android.media.ExifInterface.TAG_IMAGE_WIDTH;
import static android.media.ExifInterface.TAG_ISO;
import static android.media.ExifInterface.TAG_MAKE;
import static android.media.ExifInterface.TAG_MODEL;
import static android.media.ExifInterface.TAG_ORIENTATION;
import static android.media.ExifInterface.TAG_SUBSEC_TIME;
import static android.media.ExifInterface.TAG_SUBSEC_TIME_DIG;
import static android.media.ExifInterface.TAG_SUBSEC_TIME_ORIG;
import static android.media.ExifInterface.TAG_WHITE_BALANCE;

class ExifExtractor {

    static JSONObject extract(String path) throws IOException {
        JSONObject exifData = new JSONObject();

        List<String> attributes = getBasicAttributes();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            attributes.addAll(getLevel23Attributes());
        }

        ExifInterface exif = new ExifInterface(path);

        for (String attribute : attributes) {
            String value = exif.getAttribute(attribute);
            try {
                exifData.put(attribute, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return exifData;
    }

    private static List<String> getBasicAttributes() {
        return new ArrayList<>(Arrays.asList(
                TAG_APERTURE,
                TAG_DATETIME,
                TAG_EXPOSURE_TIME,
                TAG_FLASH,
                TAG_FOCAL_LENGTH,
                TAG_GPS_ALTITUDE,
                TAG_GPS_ALTITUDE_REF,
                TAG_GPS_DATESTAMP,
                TAG_GPS_LATITUDE,
                TAG_GPS_LATITUDE_REF,
                TAG_GPS_LONGITUDE,
                TAG_GPS_LONGITUDE_REF,
                TAG_GPS_PROCESSING_METHOD,
                TAG_GPS_TIMESTAMP,
                TAG_IMAGE_LENGTH,
                TAG_IMAGE_WIDTH,
                TAG_ISO,
                TAG_MAKE,
                TAG_MODEL,
                TAG_ORIENTATION,
                TAG_WHITE_BALANCE
        ));
    }

    private static List<String> getLevel23Attributes() {
        return new ArrayList<>(Arrays.asList(
                TAG_DATETIME_DIGITIZED,
                TAG_SUBSEC_TIME,
                TAG_SUBSEC_TIME_DIG,
                TAG_SUBSEC_TIME_ORIG
        ));
    }
}