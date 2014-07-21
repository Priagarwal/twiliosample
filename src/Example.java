import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Sms;
import com.twilio.sdk.resource.list.CallList;
import com.twilio.sdk.resource.list.RecordingList;
import com.twilio.sdk.resource.list.SmsList;
import com.twilio.sdk.resource.list.TranscriptionList;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.instance.Recording;
import com.twilio.sdk.resource.instance.Transcription;
import com.twilio.sdk.resource.factory.CallFactory;

import java.util.HashMap;
import java.util.Map;
 
public class Example {
 
  // Find your Account Sid and Token at twilio.com/user/account
  public static final String ACCOUNT_SID = "AC1a3ce70025e10ba7f71b9336ff73c6f3";
  public static final String AUTH_TOKEN = "904b3f7cae87adfba438a8a3fed4ef92";
 
  public static void message(String from, String to, String message_body) throws TwilioRestException {
	  TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
	  Map<String, String> params = new HashMap<String, String>();
	  params.put("Body", message_body);
	  params.put("To", to);
	  params.put("From", from);
	  SmsFactory messageFactory = client.getAccount().getSmsFactory();
	  Sms message = messageFactory.create(params);
	  System.out.println(message.getSid());	  
  }
  
  public static void call(String from, String to, String Url) throws TwilioRestException {
	  TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
	  Account mainAccount = client.getAccount();
      CallFactory callFactory = mainAccount.getCallFactory();
      Map<String, String> callParams = new HashMap<String, String>();
      callParams.put("Url", Url);
      callParams.put("To", to);
      callParams.put("From", from);
      //callParams.put("Record", true);
      // Make the call
      Call call = callFactory.create(callParams);
      // Print the call SID (a 32 digit hex like CA123..)
      System.out.println(call.getSid());	  
      Recording recording = client.getAccount().getRecording("RE557ce644e5ab84fa21cc21112e22c485");
  }
  
  public static void CallRetriever() throws TwilioRestException {
	  TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
      Account mainAccount = client.getAccount();
      CallList calls = mainAccount.getCalls();
      for (Call call : calls) {
          System.out.println("From: " + call.getFrom() + " To: " + call.getTo());
      }	  
  }
  
  public static Recording getARecording(String recordId) throws TwilioRestException {
	  TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
	  
	    // Get an object from its sid. If you do not have a sid,
	    // check out the list resource examples on this page
	    Recording recording = client.getAccount().getRecording("RE557ce644e5ab84fa21cc21112e22c485");
	    return recording;	  
  }
  
  public static RecordingList getAllRecordings() {
	  TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN); 	     
	  RecordingList recordings = client.getAccount().getRecordings();
	  return recordings;
  }
  
  public static RecordingList getRecordingsDated(String forDate) {
	  TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
	  // Build a filter for the RecordingList
	  Map<String, String> params = new HashMap<String, String>();
	  params.put("DateCreated", forDate);
	  RecordingList recordings = client.getAccount().getRecordings(params);
	  return recordings;	  
  }
  
  public static Transcription getATranscription(String recordId) throws TwilioRestException {
	  TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
	  Transcription transcription = client.getAccount().getTranscription("TR8c61027b709ffb038236612dc5af8723");
	  return transcription;	  
  }
  
  public static TranscriptionList getAllTranscriptions() {
	  TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);    
	  TranscriptionList transcriptions = client.getAccount().getTranscriptions();
	  return transcriptions;
  }
  
  public static void main(String[] args) throws TwilioRestException {
	  
      call("+19193283053","+919962254307", "http://5b7c0fba.ngrok.com/HackathonIdea/twilio/response");
      //CallRetriever();
      
  }
  
}