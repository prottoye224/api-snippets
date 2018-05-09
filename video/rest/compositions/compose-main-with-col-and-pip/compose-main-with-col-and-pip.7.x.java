// Install the Java helper library from twilio.com/docs/java/install
import com.twilio.Twilio;
import com.twilio.rest.video.v1.Composition;
import com.twilio.rest.video.v1.Composition.Format;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PipComposition{

  // Find your credentials at twilio.com/console
  public static final String ACCOUNT_SID = "ACXXXX";
  public static final String API_KEY_SID = "SKXXXX";
  public static final String API_KEY_SECRET = "your_api_key_secret";

  public static void main( String[] args ) throws IOException{
      // Initialize the client
      Twilio.init(API_KEY_SID, API_KEY_SECRET, ACCOUNT_SID);

      final String videoLayout =
                        "{"
                      + "   \"main\": {"
                      + "       \"z_pos\": 1,"
                      + "       \"video_sources\":[\"teacher-screen-video\"]"
                      + "    },"
                      + "   \"pip\": {"
                      + "       \"z_pos\": 2,"
                      + "       \"x_pos\": 1000,"
                      + "       \"y_pos\": 30,"
                      + "       \"width\": 240,"
                      + "       \"height\": 180,"
                      + "       \"video_sources\": [\"teacher-webcam-video\"]"
                      + "    },"
                      + "   \"column\": {"
                      + "       \"z_pos\": 3,"
                      + "       \"x_pos\": 40,"
                      + "       \"y_pos\": 10,"
                      + "       \"width\": 190,"
                      + "       \"height\": 700,"
                      + "       \"max_rows\": 5,"
                      + "       \"max_columns\": 1,"
                      + "       \"reuse\": \"show_newest\","
                      + "       \"video_sources\": [\"student-*\"]"
                      + "    }"
                      + "}";

      Composition composition = Composition.creator()
        								.setRoomSid("RMXXXX")
                        .setAudioSources("*")
                        .setVideoLayout(new ObjectMapper().readValue(videoLayout, HashMap.class))
        								.setStatusCallback("http://my.server.org/callbacks")
                        .setResolution("1280x720")
        								.setFormat(Format.MP4)
        								.create();

      System.out.println("Created composition with SID=" + composition.getSid());
  }
}
