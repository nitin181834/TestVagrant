import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Validating {

    JSONParser jsonParser;
    FileReader fileReader;
    JSONObject jsonObject;
    JSONArray jsonArray;

    @BeforeClass
    public void validating(){
        jsonParser = new JSONParser();
        try {
             fileReader = new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\input.json");
             jsonObject= (JSONObject) jsonParser.parse(fileReader);
             jsonArray = (JSONArray) jsonObject.get("player");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 1)
    public void validatingForeginPlayer(){
        int count=0;
        for (int i=0;i<jsonArray.size();i++){
            JSONObject obj = (JSONObject) jsonArray.get(i);
            String country = (String) obj.get("country");
            if(!country.equals("India")){
                count++;
            }
        }
        Assert.assertTrue(count==4);
        System.out.println("Team is containg 4  foreign players");
    }

    @Test(priority = 2)
    public void validatingWicketKeeper(){
        int count=0;
        for (int i=0;i<jsonArray.size();i++){
            JSONObject obj = (JSONObject) jsonArray.get(i);
            String role = (String) obj.get("role");
            if(role.equals("Wicket-keeper")){
                count++;
            }
        }
        Assert.assertTrue(count>=1);
        System.out.println("Team is containg at least one wicket keeper");
    }

}
