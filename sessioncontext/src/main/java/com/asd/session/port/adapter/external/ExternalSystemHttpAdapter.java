package main.java.com.asd.session.port.adapter.external;


import org.springframework.boot.autoconfigure.gson.GsonProperties;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ExternalSystemHttpAdapter {
    private static final String BASE_URI = "localhost:8080";

    private static ExternalSystemHttpAdapter instance = new ExternalSystemHttpAdapter();
    
    public static final ExternalSystemHttpAdapter instance() {
        return instance;
    }
    
    public ExternalSystemHttpAdapter() {

    }


    public UUID makeReservation(UUID spaceId, LocalDateTime startDateTime, LocalDateTime endDateTime) throws IOException {
        final String reservationUri = BASE_URI + String.format("/space/%s/reserve", spaceId);
        URL url = new URL(reservationUri);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        OutputStream outStream = con.getOutputStream();
        OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");
        outStreamWriter.write(String.format("{startDateTime:%s, endDateTime:%s}", startDateTime, endDateTime));
        outStreamWriter.flush();
        outStreamWriter.close();
        outStream.close();
        con.connect();

        String result;
        BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result2 = bis.read();
        while(result2 != -1) {
            buf.write((byte) result2);
            result2 = bis.read();
        }
        result = buf.toString();
        System.out.println(result);



        return UUID.randomUUID();
    }
}
