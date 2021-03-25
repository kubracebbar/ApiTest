package tests;


import extensions.RetryAnalyzer;
import org.testng.annotations.Test;
import pages.ArtistClass;
import pages.PlayListClass;
import pages.SearchClass;
import pages.UserClass;

import java.io.IOException;
import java.util.List;


@Test(retryAnalyzer = RetryAnalyzer.class)
public class TestClassOne {
 public void Test() throws IOException {

     UserClass user= new UserClass();
     String userID= user.getUser();
     user.getUserProfile(userID);
     PlayListClass playList= new PlayListClass();
     String playListId=playList.createPlayList(userID,"name","kubra");
     playList.controlPlayListIsEmpty(playListId);
     SearchClass search= new SearchClass();
     List<Object> tracks=search.searchSomething();
     playList.addPlayListTracks(playListId,tracks);
     playList.updatePlayLists(playListId,"name","deneme");// ek case
     playList.deletePlayLists(playListId,tracks.get(2));
     playList.validatePlayListDeletion(playListId);




 }


}
